package de.hybris.demo.facades.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class SearchResultSustainProductPopulator <SOURCE extends SearchResultValueData, TARGET extends ProductData> implements
        Populator<SOURCE, TARGET>
{
    private static final Logger LOG = Logger.getLogger(SearchResultSustainProductPopulator.class);

    @Override
    public void populate(final SOURCE source, final TARGET target)
    {
        validateParameterNotNullStandardMessage("source", source);
        validateParameterNotNullStandardMessage("target", target);
        try
        {
            final String productCO2Weight = this.getValue(source, ProductModel.CARBONFOOTPRINTWEIGHT);
            if(StringUtils.isNoneEmpty())
            {
                target.setCarbonFootPrintWeight(productCO2Weight);
            }
            else
            {
                target.setCarbonFootPrintWeight("N.A.");
            }
        }
        catch (ClassCastException e)
        {
            LOG.warn(e);
        }
    }

    protected <T> T getValue(final SOURCE source, final String propertyName)
    {
        if (source.getValues() == null)
        {
            return null;
        }

        // DO NOT REMOVE the cast (T) below, while it should be unnecessary it is required by the javac compiler
        return (T) source.getValues().get(propertyName);
    }
}
