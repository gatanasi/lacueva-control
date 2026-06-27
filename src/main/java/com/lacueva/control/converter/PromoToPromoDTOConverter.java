package com.lacueva.control.converter;

import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.dto.PromoDTO;

public class PromoToPromoDTOConverter {

    public static PromoDTO convert(Promo promo) {
	PromoDTO promoDTO = new PromoDTO();
	promoDTO.setId(promo.getId());
	promoDTO.setPromoItem(promo.getPromoItem());
	promoDTO.setPromoQuantity(promo.getPromoQuantity());
	promoDTO.setPromoValue(promo.getPromoValue());
	return promoDTO;
    }
}
