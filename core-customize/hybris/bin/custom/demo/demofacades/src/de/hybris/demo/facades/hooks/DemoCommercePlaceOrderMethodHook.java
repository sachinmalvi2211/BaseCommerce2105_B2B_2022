package de.hybris.demo.facades.hooks;

import de.hybris.demo.core.model.SustainablePurchaseRewardModel;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DemoCommercePlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
    private static final Logger LOG = Logger.getLogger(DemoCommercePlaceOrderMethodHook.class);

    private OrderService orderService;
    private ModelService modelService;
    private BaseStoreService baseStoreService;
    private BaseSiteService baseSiteService;
    private PromotionsService promotionsService;
    private CommonI18NService commonI18NService;
    private CalculationService calculationService;
    @Resource
    private ConfigurationService configurationService;

    public String getCarbonProduct()
    {
        return configurationService.getConfiguration().getString("demo.sustainable.dummy.product.id", "CarbonOffsetProduct");
    }
    @Override
    public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult orderModel)
    {
        validateParameterNotNull(parameter, "parameters cannot be null");
        if(null != orderModel && null != orderModel.getOrder())
        {
            CustomerModel customer = (CustomerModel) orderModel.getOrder().getUser();

            SustainablePurchaseRewardModel rewardInfo = customer.getEarnedRewards();

            if(null == rewardInfo)
            {
                rewardInfo = modelService.create(SustainablePurchaseRewardModel.class);
                rewardInfo.setEarnedSustainableRewards("0.00");
            }
            OrderModel order = orderModel.getOrder();

            AbstractOrderEntryModel entry = order.getEntries().get(order.getEntries().size()-1);

            if(entry.getProduct().getCode().equalsIgnoreCase(getCarbonProduct()))
            {
                if(StringUtils.isNoneEmpty(rewardInfo.getEarnedSustainableRewards()))
                {
                   double finalReward =  Double.valueOf(rewardInfo.getEarnedSustainableRewards()) ;
                    double orderCarbonWeight = 0;
                    if(StringUtils.isNoneEmpty(entry.getCarbonFootPrintWeight()))
                   {
                        orderCarbonWeight = Double.valueOf(entry.getCarbonFootPrintWeight());
                   }
                    finalReward = finalReward + orderCarbonWeight;
                    rewardInfo.setEarnedSustainableRewards(String.valueOf(BigDecimal.valueOf(finalReward).setScale(2, RoundingMode.HALF_UP)));
                    customer.setEarnedRewards(rewardInfo);
                    modelService.saveAll(rewardInfo, customer);
                }
            }

        }
    }

    @Override
    public void beforePlaceOrder(@Nonnull final CommerceCheckoutParameter parameter) throws InvalidCartException
    {

    }

    @Override
    public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
            throws InvalidCartException
    {

    }

    protected OrderService getOrderService()
    {
        return orderService;
    }

    @Required
    public void setOrderService(final OrderService orderService)
    {
        this.orderService = orderService;
    }

    protected ModelService getModelService()
    {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    protected BaseStoreService getBaseStoreService()
    {
        return baseStoreService;
    }

    @Required
    public void setBaseStoreService(final BaseStoreService baseStoreService)
    {
        this.baseStoreService = baseStoreService;
    }

    protected BaseSiteService getBaseSiteService()
    {
        return baseSiteService;
    }

    @Required
    public void setBaseSiteService(final BaseSiteService baseSiteService)
    {
        this.baseSiteService = baseSiteService;
    }

    protected PromotionsService getPromotionsService()
    {
        return promotionsService;
    }

    @Required
    public void setPromotionsService(final PromotionsService promotionsService)
    {
        this.promotionsService = promotionsService;
    }

    protected CommonI18NService getCommonI18NService()
    {
        return commonI18NService;
    }

    @Required
    public void setCommonI18NService(final CommonI18NService commonI18NService)
    {
        this.commonI18NService = commonI18NService;
    }

    protected CalculationService getCalculationService()
    {
        return calculationService;
    }

    @Required
    public void setCalculationService(final CalculationService calculationService)
    {
        this.calculationService = calculationService;
    }
}
