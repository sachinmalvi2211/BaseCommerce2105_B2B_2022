/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.demo.facades.populators;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.classification.features.FeatureValue;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.annotation.Resource;


/**
 * Sustainable cart populator.
 */
public class SustainableCartEntryPopulator implements Populator<AbstractOrderEntryModel, OrderEntryData>
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger( SustainableCartEntryPopulator.class.getName() );

	// Concrete implementation of the SustainableCartPopulator that should be used for further customizations
	@Override
	public void populate(AbstractOrderEntryModel source, OrderEntryData target) throws ConversionException {
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		if(null != source.getIsSustainableEntry())
		{
			target.setIsSustainableEntry(source.getIsSustainableEntry());
		}
		if(null != source.getCarbonOffsetCredit())
		{
			target.setCarbonOffsetCredit(source.getCarbonOffsetCredit());
		}
		if(null != source.getCarbonFootPrintWeight())
		{
			target.setCarbonFootPrintWeight(source.getCarbonFootPrintWeight());
		}
		if(null != source.getEntryTotalSustanabilityValue())
		{
			target.setEntryTotalSustanabilityValue(source.getEntryTotalSustanabilityValue());
		}
	}
}
