/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 19, 2022, 5:16:23 PM                    ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.demo.core.jalo;

import de.hybris.demo.core.constants.DemoCoreConstants;
import de.hybris.demo.core.jalo.ApparelProduct;
import de.hybris.demo.core.jalo.ApparelSizeVariantProduct;
import de.hybris.demo.core.jalo.ApparelStyleVariantProduct;
import de.hybris.demo.core.jalo.ElectronicsColorVariantProduct;
import de.hybris.demo.core.jalo.SustainablePurchaseReward;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.cms2.jalo.site.CMSSite;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.sap.sapcpiadapter.jalo.SAPCpiOutboundOrderItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>DemoCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDemoCoreManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("carbonCreditAmount", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.cms2.jalo.site.CMSSite", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("isSustainableEntry", AttributeMode.INITIAL);
		tmp.put("carbonFootPrintWeight", AttributeMode.INITIAL);
		tmp.put("carbonOffsetCredit", AttributeMode.INITIAL);
		tmp.put("entryTotalSustanabilityValue", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrderEntry", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("isPermanentSustainable", AttributeMode.INITIAL);
		tmp.put("earnedRewards", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.Customer", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("carbonFootPrintWeight", AttributeMode.INITIAL);
		tmp.put("sustainableMaterials", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("carbonFootPrintWeight", AttributeMode.INITIAL);
		tmp.put("carbonOffsetCredit", AttributeMode.INITIAL);
		tmp.put("entryTotalSustainabilityValue", AttributeMode.INITIAL);
		tmp.put("isSustainableEntry", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.sap.sapcpiadapter.jalo.SAPCpiOutboundOrderItem", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.carbonCreditAmount</code> attribute.
	 * @return the carbonCreditAmount
	 */
	public String getCarbonCreditAmount(final SessionContext ctx, final CMSSite item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.CMSSite.CARBONCREDITAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.carbonCreditAmount</code> attribute.
	 * @return the carbonCreditAmount
	 */
	public String getCarbonCreditAmount(final CMSSite item)
	{
		return getCarbonCreditAmount( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.carbonCreditAmount</code> attribute. 
	 * @param value the carbonCreditAmount
	 */
	public void setCarbonCreditAmount(final SessionContext ctx, final CMSSite item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.CMSSite.CARBONCREDITAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.carbonCreditAmount</code> attribute. 
	 * @param value the carbonCreditAmount
	 */
	public void setCarbonCreditAmount(final CMSSite item, final String value)
	{
		setCarbonCreditAmount( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.carbonFootPrintWeight</code> attribute.
	 * @return the carbonFootPrintWeight - The carbonFootPrint value in Weight is added to cartEntry.
	 */
	public String getCarbonFootPrintWeight(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.CARBONFOOTPRINTWEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.carbonFootPrintWeight</code> attribute.
	 * @return the carbonFootPrintWeight - The carbonFootPrint value in Weight is added to cartEntry.
	 */
	public String getCarbonFootPrintWeight(final AbstractOrderEntry item)
	{
		return getCarbonFootPrintWeight( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.carbonFootPrintWeight</code> attribute. 
	 * @param value the carbonFootPrintWeight - The carbonFootPrint value in Weight is added to cartEntry.
	 */
	public void setCarbonFootPrintWeight(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.CARBONFOOTPRINTWEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.carbonFootPrintWeight</code> attribute. 
	 * @param value the carbonFootPrintWeight - The carbonFootPrint value in Weight is added to cartEntry.
	 */
	public void setCarbonFootPrintWeight(final AbstractOrderEntry item, final String value)
	{
		setCarbonFootPrintWeight( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.carbonFootPrintWeight</code> attribute.
	 * @return the carbonFootPrintWeight - Carbon footprint in terms of Weight
	 */
	public String getCarbonFootPrintWeight(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.Product.CARBONFOOTPRINTWEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.carbonFootPrintWeight</code> attribute.
	 * @return the carbonFootPrintWeight - Carbon footprint in terms of Weight
	 */
	public String getCarbonFootPrintWeight(final Product item)
	{
		return getCarbonFootPrintWeight( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.carbonFootPrintWeight</code> attribute. 
	 * @param value the carbonFootPrintWeight - Carbon footprint in terms of Weight
	 */
	public void setCarbonFootPrintWeight(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.Product.CARBONFOOTPRINTWEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.carbonFootPrintWeight</code> attribute. 
	 * @param value the carbonFootPrintWeight - Carbon footprint in terms of Weight
	 */
	public void setCarbonFootPrintWeight(final Product item, final String value)
	{
		setCarbonFootPrintWeight( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.carbonFootPrintWeight</code> attribute.
	 * @return the carbonFootPrintWeight
	 */
	public String getCarbonFootPrintWeight(final SessionContext ctx, final SAPCpiOutboundOrderItem item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.CARBONFOOTPRINTWEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.carbonFootPrintWeight</code> attribute.
	 * @return the carbonFootPrintWeight
	 */
	public String getCarbonFootPrintWeight(final SAPCpiOutboundOrderItem item)
	{
		return getCarbonFootPrintWeight( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.carbonFootPrintWeight</code> attribute. 
	 * @param value the carbonFootPrintWeight
	 */
	public void setCarbonFootPrintWeight(final SessionContext ctx, final SAPCpiOutboundOrderItem item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.CARBONFOOTPRINTWEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.carbonFootPrintWeight</code> attribute. 
	 * @param value the carbonFootPrintWeight
	 */
	public void setCarbonFootPrintWeight(final SAPCpiOutboundOrderItem item, final String value)
	{
		setCarbonFootPrintWeight( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.carbonOffsetCredit</code> attribute.
	 * @return the carbonOffsetCredit - The carbonOffsetCredit value for product is added to cartEntry.
	 */
	public String getCarbonOffsetCredit(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.CARBONOFFSETCREDIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.carbonOffsetCredit</code> attribute.
	 * @return the carbonOffsetCredit - The carbonOffsetCredit value for product is added to cartEntry.
	 */
	public String getCarbonOffsetCredit(final AbstractOrderEntry item)
	{
		return getCarbonOffsetCredit( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.carbonOffsetCredit</code> attribute. 
	 * @param value the carbonOffsetCredit - The carbonOffsetCredit value for product is added to cartEntry.
	 */
	public void setCarbonOffsetCredit(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.CARBONOFFSETCREDIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.carbonOffsetCredit</code> attribute. 
	 * @param value the carbonOffsetCredit - The carbonOffsetCredit value for product is added to cartEntry.
	 */
	public void setCarbonOffsetCredit(final AbstractOrderEntry item, final String value)
	{
		setCarbonOffsetCredit( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.carbonOffsetCredit</code> attribute.
	 * @return the carbonOffsetCredit
	 */
	public String getCarbonOffsetCredit(final SessionContext ctx, final SAPCpiOutboundOrderItem item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.CARBONOFFSETCREDIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.carbonOffsetCredit</code> attribute.
	 * @return the carbonOffsetCredit
	 */
	public String getCarbonOffsetCredit(final SAPCpiOutboundOrderItem item)
	{
		return getCarbonOffsetCredit( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.carbonOffsetCredit</code> attribute. 
	 * @param value the carbonOffsetCredit
	 */
	public void setCarbonOffsetCredit(final SessionContext ctx, final SAPCpiOutboundOrderItem item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.CARBONOFFSETCREDIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.carbonOffsetCredit</code> attribute. 
	 * @param value the carbonOffsetCredit
	 */
	public void setCarbonOffsetCredit(final SAPCpiOutboundOrderItem item, final String value)
	{
		setCarbonOffsetCredit( getSession().getSessionContext(), item, value );
	}
	
	public ApparelProduct createApparelProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( DemoCoreConstants.TC.APPARELPRODUCT );
			return (ApparelProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelProduct createApparelProduct(final Map attributeValues)
	{
		return createApparelProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( DemoCoreConstants.TC.APPARELSIZEVARIANTPRODUCT );
			return (ApparelSizeVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelSizeVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final Map attributeValues)
	{
		return createApparelSizeVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( DemoCoreConstants.TC.APPARELSTYLEVARIANTPRODUCT );
			return (ApparelStyleVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelStyleVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final Map attributeValues)
	{
		return createApparelStyleVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( DemoCoreConstants.TC.ELECTRONICSCOLORVARIANTPRODUCT );
			return (ElectronicsColorVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ElectronicsColorVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final Map attributeValues)
	{
		return createElectronicsColorVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public SustainablePurchaseReward createSustainablePurchaseReward(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( DemoCoreConstants.TC.SUSTAINABLEPURCHASEREWARD );
			return (SustainablePurchaseReward)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SustainablePurchaseReward : "+e.getMessage(), 0 );
		}
	}
	
	public SustainablePurchaseReward createSustainablePurchaseReward(final Map attributeValues)
	{
		return createSustainablePurchaseReward( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.earnedRewards</code> attribute.
	 * @return the earnedRewards - Rewards earned from Sustainable Product's purchase
	 */
	public SustainablePurchaseReward getEarnedRewards(final SessionContext ctx, final Customer item)
	{
		return (SustainablePurchaseReward)item.getProperty( ctx, DemoCoreConstants.Attributes.Customer.EARNEDREWARDS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.earnedRewards</code> attribute.
	 * @return the earnedRewards - Rewards earned from Sustainable Product's purchase
	 */
	public SustainablePurchaseReward getEarnedRewards(final Customer item)
	{
		return getEarnedRewards( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.earnedRewards</code> attribute. 
	 * @param value the earnedRewards - Rewards earned from Sustainable Product's purchase
	 */
	public void setEarnedRewards(final SessionContext ctx, final Customer item, final SustainablePurchaseReward value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.Customer.EARNEDREWARDS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.earnedRewards</code> attribute. 
	 * @param value the earnedRewards - Rewards earned from Sustainable Product's purchase
	 */
	public void setEarnedRewards(final Customer item, final SustainablePurchaseReward value)
	{
		setEarnedRewards( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.entryTotalSustainabilityValue</code> attribute.
	 * @return the entryTotalSustainabilityValue
	 */
	public String getEntryTotalSustainabilityValue(final SessionContext ctx, final SAPCpiOutboundOrderItem item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.ENTRYTOTALSUSTAINABILITYVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.entryTotalSustainabilityValue</code> attribute.
	 * @return the entryTotalSustainabilityValue
	 */
	public String getEntryTotalSustainabilityValue(final SAPCpiOutboundOrderItem item)
	{
		return getEntryTotalSustainabilityValue( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.entryTotalSustainabilityValue</code> attribute. 
	 * @param value the entryTotalSustainabilityValue
	 */
	public void setEntryTotalSustainabilityValue(final SessionContext ctx, final SAPCpiOutboundOrderItem item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.ENTRYTOTALSUSTAINABILITYVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.entryTotalSustainabilityValue</code> attribute. 
	 * @param value the entryTotalSustainabilityValue
	 */
	public void setEntryTotalSustainabilityValue(final SAPCpiOutboundOrderItem item, final String value)
	{
		setEntryTotalSustainabilityValue( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.entryTotalSustanabilityValue</code> attribute.
	 * @return the entryTotalSustanabilityValue - The total sustainability value in of cartEntry.
	 */
	public String getEntryTotalSustanabilityValue(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.ENTRYTOTALSUSTANABILITYVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.entryTotalSustanabilityValue</code> attribute.
	 * @return the entryTotalSustanabilityValue - The total sustainability value in of cartEntry.
	 */
	public String getEntryTotalSustanabilityValue(final AbstractOrderEntry item)
	{
		return getEntryTotalSustanabilityValue( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.entryTotalSustanabilityValue</code> attribute. 
	 * @param value the entryTotalSustanabilityValue - The total sustainability value in of cartEntry.
	 */
	public void setEntryTotalSustanabilityValue(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.ENTRYTOTALSUSTANABILITYVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.entryTotalSustanabilityValue</code> attribute. 
	 * @param value the entryTotalSustanabilityValue - The total sustainability value in of cartEntry.
	 */
	public void setEntryTotalSustanabilityValue(final AbstractOrderEntry item, final String value)
	{
		setEntryTotalSustanabilityValue( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return DemoCoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.isPermanentSustainable</code> attribute.
	 * @return the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public Boolean isIsPermanentSustainable(final SessionContext ctx, final Customer item)
	{
		return (Boolean)item.getProperty( ctx, DemoCoreConstants.Attributes.Customer.ISPERMANENTSUSTAINABLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.isPermanentSustainable</code> attribute.
	 * @return the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public Boolean isIsPermanentSustainable(final Customer item)
	{
		return isIsPermanentSustainable( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.isPermanentSustainable</code> attribute. 
	 * @return the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public boolean isIsPermanentSustainableAsPrimitive(final SessionContext ctx, final Customer item)
	{
		Boolean value = isIsPermanentSustainable( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.isPermanentSustainable</code> attribute. 
	 * @return the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public boolean isIsPermanentSustainableAsPrimitive(final Customer item)
	{
		return isIsPermanentSustainableAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.isPermanentSustainable</code> attribute. 
	 * @param value the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public void setIsPermanentSustainable(final SessionContext ctx, final Customer item, final Boolean value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.Customer.ISPERMANENTSUSTAINABLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.isPermanentSustainable</code> attribute. 
	 * @param value the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public void setIsPermanentSustainable(final Customer item, final Boolean value)
	{
		setIsPermanentSustainable( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.isPermanentSustainable</code> attribute. 
	 * @param value the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public void setIsPermanentSustainable(final SessionContext ctx, final Customer item, final boolean value)
	{
		setIsPermanentSustainable( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.isPermanentSustainable</code> attribute. 
	 * @param value the isPermanentSustainable - Consider permanent Sustainable order placement
	 */
	public void setIsPermanentSustainable(final Customer item, final boolean value)
	{
		setIsPermanentSustainable( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute.
	 * @return the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public Boolean isIsSustainableEntry(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (Boolean)item.getProperty( ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.ISSUSTAINABLEENTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute.
	 * @return the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public Boolean isIsSustainableEntry(final AbstractOrderEntry item)
	{
		return isIsSustainableEntry( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute. 
	 * @return the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public boolean isIsSustainableEntryAsPrimitive(final SessionContext ctx, final AbstractOrderEntry item)
	{
		Boolean value = isIsSustainableEntry( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute. 
	 * @return the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public boolean isIsSustainableEntryAsPrimitive(final AbstractOrderEntry item)
	{
		return isIsSustainableEntryAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute. 
	 * @param value the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public void setIsSustainableEntry(final SessionContext ctx, final AbstractOrderEntry item, final Boolean value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.AbstractOrderEntry.ISSUSTAINABLEENTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute. 
	 * @param value the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public void setIsSustainableEntry(final AbstractOrderEntry item, final Boolean value)
	{
		setIsSustainableEntry( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute. 
	 * @param value the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public void setIsSustainableEntry(final SessionContext ctx, final AbstractOrderEntry item, final boolean value)
	{
		setIsSustainableEntry( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.isSustainableEntry</code> attribute. 
	 * @param value the isSustainableEntry - Consider Sustainable entry Boolean Field
	 */
	public void setIsSustainableEntry(final AbstractOrderEntry item, final boolean value)
	{
		setIsSustainableEntry( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.isSustainableEntry</code> attribute.
	 * @return the isSustainableEntry
	 */
	public String getIsSustainableEntry(final SessionContext ctx, final SAPCpiOutboundOrderItem item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.ISSUSTAINABLEENTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPCpiOutboundOrderItem.isSustainableEntry</code> attribute.
	 * @return the isSustainableEntry
	 */
	public String getIsSustainableEntry(final SAPCpiOutboundOrderItem item)
	{
		return getIsSustainableEntry( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.isSustainableEntry</code> attribute. 
	 * @param value the isSustainableEntry
	 */
	public void setIsSustainableEntry(final SessionContext ctx, final SAPCpiOutboundOrderItem item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.SAPCpiOutboundOrderItem.ISSUSTAINABLEENTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SAPCpiOutboundOrderItem.isSustainableEntry</code> attribute. 
	 * @param value the isSustainableEntry
	 */
	public void setIsSustainableEntry(final SAPCpiOutboundOrderItem item, final String value)
	{
		setIsSustainableEntry( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.sustainableMaterials</code> attribute.
	 * @return the sustainableMaterials - Sustainable Materials Description
	 */
	public String getSustainableMaterials(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, DemoCoreConstants.Attributes.Product.SUSTAINABLEMATERIALS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.sustainableMaterials</code> attribute.
	 * @return the sustainableMaterials - Sustainable Materials Description
	 */
	public String getSustainableMaterials(final Product item)
	{
		return getSustainableMaterials( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.sustainableMaterials</code> attribute. 
	 * @param value the sustainableMaterials - Sustainable Materials Description
	 */
	public void setSustainableMaterials(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, DemoCoreConstants.Attributes.Product.SUSTAINABLEMATERIALS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.sustainableMaterials</code> attribute. 
	 * @param value the sustainableMaterials - Sustainable Materials Description
	 */
	public void setSustainableMaterials(final Product item, final String value)
	{
		setSustainableMaterials( getSession().getSessionContext(), item, value );
	}
	
}
