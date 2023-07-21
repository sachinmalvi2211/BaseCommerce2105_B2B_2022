package de.hybris.demo.facades.hooks;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.hook.CommerceUpdateCartEntryHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DemoUpdateCartEntryHook implements CommerceUpdateCartEntryHook
{
    private static final Logger LOG = Logger.getLogger(DemoUpdateCartEntryHook.class);
    @Resource
    private ConfigurationService configurationService;
    @Resource
    private ProductService productService;
    @Resource
    private ModelService modelService;
    @Resource
    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;
    @Resource
    private ClassificationService classificationService;
    @Resource
    private CartFacade cartFacade;

    public String getCarbonProduct()
    {
        return configurationService.getConfiguration().getString("demo.sustainable.dummy.product.id", "CarbonOffsetProduct");
    }
    @Override
    public void beforeUpdateCartEntry(final CommerceCartParameter parameter)
    {
    }

    @Override
    public void afterUpdateCartEntry(final CommerceCartParameter parameter, final CommerceCartModification result)
    {
        final CartModel cart = parameter.getCart();

        ProductModel carbonProduct = productService.getProductForCode(getCarbonProduct());
        if(carbonProduct == null)
        {
            LOG.error("CARBON_OFFSET_PRODUCT : product not exist in System. Please add CARBON_OFFSET_PRODUCT product");
            return;
        }
        List<AbstractOrderEntryModel> cartEntries = cart.getEntries();
        Boolean isCarbonProductDoesNotExist = true;
        Boolean isCartContainsSustanableProduct = false;
        for(AbstractOrderEntryModel entry : cartEntries)
        {
             Boolean checkSustainProduct = null != entry.getIsSustainableEntry() ? entry.getIsSustainableEntry() : Boolean.TRUE;
            CustomerModel customer = (CustomerModel) cart.getUser();
            Boolean isCustomerPermanentSustainMember = null != customer.getIsPermanentSustainable() ? customer.getIsPermanentSustainable()  : Boolean.FALSE;
            if(checkSustainProduct || isCustomerPermanentSustainMember)
            {
                isCartContainsSustanableProduct = true;
            }
            if(entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()))
            {
                isCarbonProductDoesNotExist = false;
            }
        }
        if(isCarbonProductDoesNotExist && isCartContainsSustanableProduct)
        {
            try {
                cartFacade.addToCart(getCarbonProduct() , 1);
                LOG.info("Carbon Offset Product added to cart");
            } catch (CommerceCartModificationException e) {
                LOG.error("Error while adding Carbon Offset Product to cart : ",e);
            }
        }

        if(cart.getEntries().size() ==1)
        {
            for(AbstractOrderEntryModel entry : cart.getEntries())
            {
                if(entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()))
                {
                    modelService.remove(entry);
                }
            }
        }
    }

    protected AbstractOrderEntryModel getEntry(@Nonnull final CartModel cart, final long entryNumber)
    {
        if (CollectionUtils.isEmpty(cart.getEntries()))
        {
            throw new IllegalArgumentException("Cart " + cart.getCode() + " has no entries");
        }
        return cart.getEntries().stream()
                .filter(e -> e.getEntryNumber() != null)
                .filter(e -> entryNumber == e.getEntryNumber().longValue())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Entry #" + entryNumber + " was not found in cart " + cart.getCode()));
    }

    public void normalizeEntryNumbers(CartModel cartModel)
    {
        final List<AbstractOrderEntryModel> entries = new ArrayList<AbstractOrderEntryModel>(cartModel.getEntries());
        Collections.sort(entries, new BeanComparator(AbstractOrderEntryModel.ENTRYNUMBER, new ComparableComparator()));
        for (int i = 0; i < entries.size(); i++)
        {
            entries.get(i).setEntryNumber(Integer.valueOf(i));
            modelService.save(entries.get(i));
        }
    }

}
