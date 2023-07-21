package de.hybris.demo.facades.hooks;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.classification.features.FeatureValue;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.order.hook.CommerceAddToCartMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

public class DemoAddToCartMethodHook implements CommerceAddToCartMethodHook
{
    @Resource
    private ClassificationService classificationService;
    @Resource
    private ConfigurationService configurationService;
    @Resource
    private ProductService productService;
    @Resource
    private CartFacade cartFacade;
    private static final Logger LOG = Logger.getLogger(DemoAddToCartMethodHook.class);
    @Resource
    private ModelService modelService;
    @Resource
    private CommerceCartService commerceCartService;
    @Resource
    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

    public String getCarbonProduct()
    {
        return configurationService.getConfiguration().getString("demo.sustainable.dummy.product.id", "CarbonOffsetProduct");
    }
    @Override
    public void beforeAddToCart(final CommerceCartParameter parameters) throws CommerceCartModificationException
    {
        LOG.debug("ProductConfig beforeAddToCart start");

        LOG.debug("ProductConfig beforeAddToCart end");
    }

    @Override
    public void afterAddToCart(final CommerceCartParameter parameters, final CommerceCartModification result)
            throws CommerceCartModificationException
    {
        LOG.debug("ProductConfig afterAddToCart start");
        if (parameters == null)
        {
            return;
        }
        ProductModel carbonProduct = productService.getProductForCode(getCarbonProduct());
        if(carbonProduct == null)
        {
            LOG.error("CARBON_OFFSET_PRODUCT product not exist in System. Please add CARBON_OFFSET_PRODUCT product");
            return;
        }

        List<AbstractOrderEntryModel> cartEntries = parameters.getCart().getEntries();
        Boolean isCarbonProductDoesNotExist = true;
        Boolean isCartContainsSustanableProduct = false;
        for(AbstractOrderEntryModel entry : cartEntries)
        {
            Boolean checkSustainProduct = null != entry.getIsSustainableEntry() ? entry.getIsSustainableEntry() : Boolean.TRUE;

            CustomerModel customer = (CustomerModel) parameters.getCart().getUser();
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
            cartFacade.addToCart(getCarbonProduct() , 1);
            LOG.info("Carbon Offset Product added to cart");
        }
        // Remove CARBON_OFFSET_PRODUCT if its single product in cart
        if(parameters.getCart().getEntries().size() ==1)
        {
            for(AbstractOrderEntryModel entry : parameters.getCart().getEntries())
            {
                if(entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()))
                {
                    modelService.remove(entry);
                }
            }
        }
        LOG.debug("Product afterAddToCart end");
    }

    protected ModelService getModelService()
    {
        return modelService;
    }

    /**
     * @param modelService
     *           injects the hybris model service
     */
    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    protected CommerceCartService getCommerceCartService()
    {
        return commerceCartService;
    }

    /**
     * @param commerceCartService
     *           ibjects the cart sevice for interaction with the cart
     */
    @Required
    public void setCommerceCartService(final CommerceCartService commerceCartService)
    {
        this.commerceCartService = commerceCartService;
    }
}