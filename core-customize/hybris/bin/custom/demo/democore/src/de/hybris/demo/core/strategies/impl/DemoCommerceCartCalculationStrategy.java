package de.hybris.demo.core.strategies.impl;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.classification.features.FeatureValue;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.constants.CommerceServicesConstants;
import de.hybris.platform.commerceservices.order.hook.CommerceCartCalculationMethodHook;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.tx.Transaction;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DemoCommerceCartCalculationStrategy extends DefaultCommerceCartCalculationStrategy
{
    private boolean calculateExternalTaxes = false;
    @Resource
    private ConfigurationService configurationService;
    @Resource
    private CMSSiteService cmsSiteService;
    @Resource
    private ClassificationService classificationService;
    @Resource
    private ProductService productService;
    @Resource
    private CommonI18NService commonI18NService;
    private static final Logger LOG = Logger.getLogger(DemoCommerceCartCalculationStrategy.class);

    public String getCarbonProduct()
    {
        return configurationService.getConfiguration().getString("demo.sustainable.dummy.product.id", "CarbonOffsetProduct");
    }

    @Resource
    private ModelService modelService;
    /**
     * @deprecated Since 5.2.
     */
    @Override
    @Deprecated(since = "5.2", forRemoval = true)
    public boolean calculateCart(final CartModel cartModel)
    {
        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setEnableHooks(true);
        parameter.setCart(cartModel);
        return this.calculateCart(parameter);
    }

    public void calculateSustainabilityDetails(AbstractOrderModel orderModel)
    {
        ProductModel carbonProduct = productService.getProductForCode(getCarbonProduct());

        if(carbonProduct == null)
        {
            LOG.error("DemoCalculationService CARBON_OFFSET_PRODUCT not exist in System. Please add CARBON_OFFSET_PRODUCT product");
            return;
        }
        Double carbonProductPrice = 0d;
        Double orderOffsetAmount = 0d;
        Double totalCartItemsWeight = 0d;

        final CurrencyModel curr = orderModel.getCurrency();
        final int digits = curr.getDigits().intValue();

        CMSSiteModel cmsSite = cmsSiteService.getCurrentSite();
        String carbonCreditValueInSite = "0";
        if(null != cmsSite)
        {
            carbonCreditValueInSite = (null != cmsSite.getCarbonCreditAmount() ? cmsSite.getCarbonCreditAmount()  : "") ;
        }
        try
        {
            carbonProductPrice = Double.valueOf(carbonCreditValueInSite);
        }catch(NumberFormatException nfe)
        {
            LOG.error("Exception while fetching carbonCreditValueInSite : "+carbonProductPrice+ " for site : "+cmsSite.getName());
        }

        // Override site level price with carbonProductPrice

        Collection<PriceRowModel> priceRows =  carbonProduct.getEurope1Prices();
        if(CollectionUtils.isNotEmpty(priceRows))
        {
            carbonProductPrice = priceRows.iterator().next().getPrice();
            LOG.info("carbonProductPrice fetched from ProductPrices : "+carbonProductPrice);
        }

        List<AbstractOrderEntryModel> cartEntries = orderModel.getEntries();
        for(AbstractOrderEntryModel entry : cartEntries)
        {
            Boolean checkSustainProduct = null != entry.getIsSustainableEntry() ? entry.getIsSustainableEntry() : Boolean.TRUE;
            if(!entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()))
            {
                CustomerModel customer = (CustomerModel) orderModel.getUser();
                Boolean isCustomerPermanentSustainMember = null != customer.getIsPermanentSustainable() ? customer.getIsPermanentSustainable()  : Boolean.FALSE;
                if(checkSustainProduct || isCustomerPermanentSustainMember)
                {
                    String carbonFootPrintWeight = "";
                    /*FeatureList featuresLst = classificationService.getFeatures(entry.getProduct());
                    Feature CFfeature = featuresLst.getFeatureByCode("PowertoolsClassification/1.0/sustainability.carbon_footprint");

                    if(null != CFfeature)
                    {
                        FeatureValue fv = CFfeature.getValue();
                        if(null != fv)
                        {
                            carboonFootPrintWeight = (null!= fv.getValue() ? fv.getValue().toString() : "");
                        }
                    }*/
                    if(StringUtils.isNotEmpty(entry.getProduct().getCarbonFootPrintWeight()))
                    {
                        carbonFootPrintWeight = entry.getProduct().getCarbonFootPrintWeight();
                    }

                    if(StringUtils.isNotEmpty(carbonFootPrintWeight))
                    {
                        double weight = Double.valueOf(carbonFootPrintWeight) * entry.getQuantity();
                        double totalEntryOffsetAmount = commonI18NService
                                .roundCurrency(weight * carbonProductPrice, digits) ;
                        totalCartItemsWeight = totalCartItemsWeight + weight;
                        entry.setCarbonFootPrintWeight(carbonFootPrintWeight);
                        entry.setCarbonOffsetCredit(String.valueOf(carbonProductPrice));
                        entry.setEntryTotalSustanabilityValue(String.valueOf(totalEntryOffsetAmount));
                        orderOffsetAmount = orderOffsetAmount + totalEntryOffsetAmount;
                        modelService.save(entry);
                    }
                    if(isCustomerPermanentSustainMember)
                    {
                        // When permanent level sustainability is turned ON then mark IsSustainableEntry = True
                        entry.setIsSustainableEntry(Boolean.TRUE);
                        modelService.save(entry);
                    }
                }
                else
                {
                    entry.setCarbonFootPrintWeight("0");
                    entry.setCarbonOffsetCredit("0");
                    entry.setEntryTotalSustanabilityValue("0");
                    modelService.save(entry);
                }
            }
        }
        for(AbstractOrderEntryModel entry : cartEntries)
        {
            if(entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()))
            {
                entry.setTotalPrice(orderOffsetAmount);
                entry.setQuantity(1l);
                entry.setCarbonOffsetCredit(String.valueOf(carbonProductPrice));
                entry.setCarbonFootPrintWeight(String.valueOf(totalCartItemsWeight));
                modelService.save(entry);
            }
        }

        List<AbstractOrderEntryModel> cartEntriesToRemoveZeroDollorCarbonProduct = orderModel.getEntries();
        List<AbstractOrderEntryModel> finalEntries = new ArrayList<>();
        for(AbstractOrderEntryModel entry : cartEntriesToRemoveZeroDollorCarbonProduct)
        {
            if(!(entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()) && entry.getTotalPrice() == 0d ))
            {
                finalEntries.add(entry);
            }
        }
        orderModel.setEntries(finalEntries);
        modelService.save(orderModel);

        normalizeEntryNumbers(orderModel);
    }

    protected void normalizeEntryNumbers(final AbstractOrderModel abstractOrderModel)
    {
        final List<AbstractOrderEntryModel> entries = new ArrayList<AbstractOrderEntryModel>(abstractOrderModel.getEntries());
        Collections.sort(entries, new BeanComparator(AbstractOrderEntryModel.ENTRYNUMBER, new ComparableComparator()));
        for (int i = 0; i < entries.size(); i++)
        {
            entries.get(i).setEntryNumber(Integer.valueOf(i));
            modelService.save(entries.get(i));
        }
    }
    @Override
    public boolean calculateCart(final CommerceCartParameter parameter)
    {
        final CartModel cartModel = parameter.getCart();
      //  calculateSustainabilityDetails(cartModel);
        if(null == cartModel.getStatus())
        {
            cartModel.setStatus(OrderStatus.CREATED);
        }
        validateParameterNotNull(cartModel, "Cart model cannot be null");

        final CalculationService calcService = getCalculationService();
        boolean recalculated = false;
        if (calcService.requiresCalculation(cartModel))
        {
            final Transaction tx = Transaction.current();
            tx.begin();
            boolean rollbackNeeded = true;
            try
            {
                try
                {
                    parameter.setRecalculate(false);
                    beforeCalculate(parameter);
                    calcService.calculate(cartModel);
                    getPromotionsService().updatePromotions(getPromotionGroups(), cartModel, true, PromotionsManager.AutoApplyMode.APPLY_ALL,
                            PromotionsManager.AutoApplyMode.APPLY_ALL, getTimeService().getCurrentTime());

                    rollbackNeeded = false;
                }
                catch (final CalculationException calculationException)
                {
                    throw new IllegalStateException(
                            "Cart model " + cartModel.getCode() + " was not calculated due to: " + calculationException.getMessage(),
                            calculationException);
                }
                finally
                {
                    afterCalculate(parameter);
                }
                recalculated = true;
            }
            finally
            {
                if (rollbackNeeded || tx.isRollbackOnly())
                {
                    tx.rollback();
                }
                else
                {
                    tx.commit();
                }
            }
        }
        if (calculateExternalTaxes)
        {
            getExternalTaxesService().calculateExternalTaxes(cartModel);
        }

        return recalculated;
    }
/*
    public void calculateSustainabilityDetails(CartModel cartModel)
    {
        ProductModel carbonProduct = productService.getProductForCode(CARBON_OFFSET_PRODUCT);

        if(carbonProduct == null)
        {
            LOG.error("CARBON_OFFSET_PRODUCT not exist in System. Please add CARBON_OFFSET_PRODUCT product");
            return;
        }
        Double carbonProductPrice = 0d;
        Double orderOffsetAmount = 0d;
        Double totalCartItemsWeight = 0d;

        final CurrencyModel curr = cartModel.getCurrency();
        final int digits = curr.getDigits().intValue();

        CMSSiteModel cmsSite = cmsSiteService.getCurrentSite();
        String carbonCreditValueInSite = "0";
        if(null != cmsSite)
        {
            carbonCreditValueInSite = (null != cmsSite.getCarbonCreditAmount() ? cmsSite.getCarbonCreditAmount()  : "") ;
        }
        try
        {
            carbonProductPrice = Double.valueOf(carbonCreditValueInSite);
        }catch(NumberFormatException nfe)
        {
            LOG.error("Exception while fetching carbonCreditValueInSite : "+carbonProductPrice+ " for site : "+cmsSite.getName());
        }

        List<AbstractOrderEntryModel> cartEntries = cartModel.getEntries();
        Boolean isCarbonProductDoesNotExist = true;
        for(AbstractOrderEntryModel entry : cartEntries)
        {
             Boolean checkSustainProduct = null != entry.getIsSustainableEntry() ? entry.getIsSustainableEntry() : Boolean.TRUE;
            if(!entry.getProduct().getCode().equalsIgnoreCase(CARBON_OFFSET_PRODUCT))
            {
                CustomerModel customer = (CustomerModel) cartModel.getUser();
                Boolean isCustomerPermanentSustainMember = null != customer.getIsPermanentSustainable() ? customer.getIsPermanentSustainable()  : Boolean.FALSE;
                if(checkSustainProduct || isCustomerPermanentSustainMember)
                {
                    FeatureList featuresLst = classificationService.getFeatures(entry.getProduct());
                    Feature CFfeature = featuresLst.getFeatureByCode("PowertoolsClassification/1.0/sustainability.carbon_footprint");
                    String carboonFootPrintWeight = "";
                    if(null != CFfeature)
                    {
                        FeatureValue fv = CFfeature.getValue();
                        if(null != fv)
                        {
                            carboonFootPrintWeight = (null!= fv.getValue() ? fv.getValue().toString() : "");
                        }
                    }
                    if(StringUtils.isNotEmpty(carboonFootPrintWeight))
                    {
                        double weight = Double.valueOf(carboonFootPrintWeight) * entry.getQuantity();
                        double totalEntryOffsetAmount = commonI18NService
                                .roundCurrency(weight * carbonProductPrice, digits) ;
                        totalCartItemsWeight = totalCartItemsWeight + weight;
                        entry.setCarbonFootPrintWeight(carboonFootPrintWeight);
                        entry.setCarbonOffsetCredit(String.valueOf(carbonProductPrice));
                        entry.setEntryTotalSustanabilityValue(String.valueOf(totalEntryOffsetAmount));
                        orderOffsetAmount = orderOffsetAmount + totalEntryOffsetAmount;
                        modelService.save(entry);
                    }
                }
                else
                {
                    entry.setCarbonFootPrintWeight("0");
                    entry.setCarbonOffsetCredit("0");
                    entry.setEntryTotalSustanabilityValue("0");
                    modelService.save(entry);
                }

            }
        }
        for(AbstractOrderEntryModel entry : cartEntries)
        {
            if(entry.getProduct().getCode().equalsIgnoreCase(CARBON_OFFSET_PRODUCT))
            {
                entry.setTotalPrice(orderOffsetAmount);
                entry.setQuantity(1l);
                entry.setCarbonFootPrintWeight(String.valueOf(totalCartItemsWeight));
                modelService.save(entry);

                double cartTotal = cartModel.getSubtotal() + orderOffsetAmount;
                cartModel.setSubtotal(cartTotal);
                cartModel.setTotalPrice(cartTotal + cartModel.getDeliveryCost());
                cartModel.setCalculated(Boolean.TRUE);
                modelService.save(cartModel);
            }
        }
    }*/

    /**
     * @deprecated Since 5.2.
     */
    @Override
    @Deprecated(since = "5.2", forRemoval = true)
    public boolean recalculateCart(final CartModel cartModel)
    {
        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setEnableHooks(true);
        parameter.setCart(cartModel);
        return this.recalculateCart(parameter);
    }

    @Override
    public boolean recalculateCart(final CommerceCartParameter parameter)
    {
        final CartModel cartModel = parameter.getCart();

        if(null == cartModel.getStatus())
        {
            cartModel.setStatus(OrderStatus.CREATED);
        }
        final Transaction tx = Transaction.current();
        tx.begin();
        boolean rollbackNeeded = true;
        try
        {
            try
            {
                parameter.setRecalculate(true);
                beforeCalculate(parameter);
                getCalculationService().recalculate(cartModel);
                getPromotionsService().updatePromotions(getPromotionGroups(), cartModel, true, PromotionsManager.AutoApplyMode.APPLY_ALL,
                        PromotionsManager.AutoApplyMode.APPLY_ALL, getTimeService().getCurrentTime());
                rollbackNeeded = false;
            }
            catch (final CalculationException calculationException)
            {
                throw new IllegalStateException(String.format("Cart model %s was not calculated due to: %s ", cartModel.getCode(),
                        calculationException.getMessage()), calculationException);
            }
            finally
            {
                afterCalculate(parameter);
            }
        }
        finally
        {
            if (rollbackNeeded || tx.isRollbackOnly())
            {
                tx.rollback();
            }
            else
            {
                tx.commit();
            }
        }

        return true;
    }

    protected void beforeCalculate(final CommerceCartParameter parameter)
    {
        if (getCommerceCartCalculationMethodHooks() != null && (parameter.isEnableHooks() && getConfigurationService()
                .getConfiguration().getBoolean(CommerceServicesConstants.CARTCALCULATIONHOOK_ENABLED, true)))
        {
            for (final CommerceCartCalculationMethodHook commerceCartCalculationMethodHook : getCommerceCartCalculationMethodHooks())
            {
                commerceCartCalculationMethodHook.beforeCalculate(parameter);
            }
        }
    }

    protected void afterCalculate(final CommerceCartParameter parameter)
    {
        if (getCommerceCartCalculationMethodHooks() != null && (parameter.isEnableHooks() && getConfigurationService()
                .getConfiguration().getBoolean(CommerceServicesConstants.CARTCALCULATIONHOOK_ENABLED, true)))
        {
            for (final CommerceCartCalculationMethodHook commerceCartCalculationMethodHook : getCommerceCartCalculationMethodHooks())
            {
                commerceCartCalculationMethodHook.afterCalculate(parameter);
            }
        }
    }

    protected Collection<PromotionGroupModel> getPromotionGroups()
    {
        final Collection<PromotionGroupModel> promotionGroupModels = new ArrayList<PromotionGroupModel>();
        if (getBaseSiteService().getCurrentBaseSite() != null
                && getBaseSiteService().getCurrentBaseSite().getDefaultPromotionGroup() != null)
        {
            promotionGroupModels.add(getBaseSiteService().getCurrentBaseSite().getDefaultPromotionGroup());
        }
        return promotionGroupModels;
    }

    /**
     * @return the calculateExternalTaxes
     */
    public boolean isCalculateExternalTaxes()
    {
        return calculateExternalTaxes;
    }
}