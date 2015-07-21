package com.lacueva.control.converter;

import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.dto.PriceDTO;

public class PriceToPriceDTOConverter {

    public static PriceDTO convert(Price price) {
	PriceDTO priceDTO = new PriceDTO();
	priceDTO.setId(price.getId());
	priceDTO.setPriceItem(price.getPriceItem());
	priceDTO.setPriceValue(price.getPriceValue());
	return priceDTO;
    }
}
