package de.hybris.demo.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang3.StringUtils;

public class ProductSustainDetailsPopulator<SOURCE extends ProductModel, TARGET extends ProductData> extends
        AbstractProductPopulator<SOURCE, TARGET>
{

    @Override
    public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
    {
        if(StringUtils.isNoneEmpty(productModel.getSustainableMaterials()))
        {
            productData.setSustainableMaterials(productModel.getSustainableMaterials());
        }
        if(StringUtils.isNoneEmpty(productModel.getCarbonFootPrintWeight()))
        {
            productData.setCarbonFootPrintWeight(productModel.getCarbonFootPrintWeight());
        }
    }
}
