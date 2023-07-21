package de.hybris.demo.facades.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DemoCustomerReversePopulator implements Populator<CustomerData, CustomerModel>
{
    @Override
    public void populate(final CustomerData source, final CustomerModel target) throws ConversionException
    {
        validateParameterNotNull(source, "Parameter source cannot be null.");
        validateParameterNotNull(target, "Parameter target cannot be null.");

        target.setIsPermanentSustainable(source.getIsPermanentSustainable());
    }
}
