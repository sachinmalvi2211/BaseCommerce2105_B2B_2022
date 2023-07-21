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
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SustainablePurchaseReward}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedSustainablePurchaseReward extends GenericItem
{
	/** Qualifier of the <code>SustainablePurchaseReward.earnedSustainableRewards</code> attribute **/
	public static final String EARNEDSUSTAINABLEREWARDS = "earnedSustainableRewards";
	/** Qualifier of the <code>SustainablePurchaseReward.rewardExpiryDate</code> attribute **/
	public static final String REWARDEXPIRYDATE = "rewardExpiryDate";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(EARNEDSUSTAINABLEREWARDS, AttributeMode.INITIAL);
		tmp.put(REWARDEXPIRYDATE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SustainablePurchaseReward.earnedSustainableRewards</code> attribute.
	 * @return the earnedSustainableRewards - Rewards earned from Sustainable Product's purchase
	 */
	public String getEarnedSustainableRewards(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EARNEDSUSTAINABLEREWARDS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SustainablePurchaseReward.earnedSustainableRewards</code> attribute.
	 * @return the earnedSustainableRewards - Rewards earned from Sustainable Product's purchase
	 */
	public String getEarnedSustainableRewards()
	{
		return getEarnedSustainableRewards( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SustainablePurchaseReward.earnedSustainableRewards</code> attribute. 
	 * @param value the earnedSustainableRewards - Rewards earned from Sustainable Product's purchase
	 */
	public void setEarnedSustainableRewards(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EARNEDSUSTAINABLEREWARDS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SustainablePurchaseReward.earnedSustainableRewards</code> attribute. 
	 * @param value the earnedSustainableRewards - Rewards earned from Sustainable Product's purchase
	 */
	public void setEarnedSustainableRewards(final String value)
	{
		setEarnedSustainableRewards( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SustainablePurchaseReward.rewardExpiryDate</code> attribute.
	 * @return the rewardExpiryDate - Reward Expiry Date
	 */
	public Date getRewardExpiryDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, REWARDEXPIRYDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SustainablePurchaseReward.rewardExpiryDate</code> attribute.
	 * @return the rewardExpiryDate - Reward Expiry Date
	 */
	public Date getRewardExpiryDate()
	{
		return getRewardExpiryDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SustainablePurchaseReward.rewardExpiryDate</code> attribute. 
	 * @param value the rewardExpiryDate - Reward Expiry Date
	 */
	public void setRewardExpiryDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, REWARDEXPIRYDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SustainablePurchaseReward.rewardExpiryDate</code> attribute. 
	 * @param value the rewardExpiryDate - Reward Expiry Date
	 */
	public void setRewardExpiryDate(final Date value)
	{
		setRewardExpiryDate( getSession().getSessionContext(), value );
	}
	
}
