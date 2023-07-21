/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.demo.facades.populators;

import de.hybris.demo.core.model.SustainablePurchaseRewardModel;
import de.hybris.platform.b2b.model.B2BCostCenterModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.model.B2BUserGroupModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.b2b.strategies.B2BUserGroupsLookUpStrategy;
import de.hybris.platform.b2bcommercefacades.company.data.B2BCostCenterData;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUnitData;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUserGroupData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.CustomerRewardData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.*;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


/**
 * Sustainable Customer populator.
 */
public class SustainableCustomerPopulator implements Populator<CustomerModel, CustomerData>
{
	@Override
	public void populate(final CustomerModel source, final CustomerData target) throws ConversionException
	{

		validateParameterNotNull(source, "Parameter source cannot be null.");
		validateParameterNotNull(target, "Parameter target cannot be null.");

			final CustomerModel customer = (CustomerModel) source;

			if (null != customer.getIsPermanentSustainable())
			{
				target.setIsPermanentSustainable(customer.getIsPermanentSustainable());
			}
			if(null != customer.getEarnedRewards())
			{
				SustainablePurchaseRewardModel rewardData = customer.getEarnedRewards();
				CustomerRewardData reward = new CustomerRewardData();
				reward.setEarnedSustainableRewards(rewardData.getEarnedSustainableRewards());
				reward.setRewardExpiryDate(rewardData.getRewardExpiryDate());
				target.setCustomerRewardData(reward);
			}
	}
}
