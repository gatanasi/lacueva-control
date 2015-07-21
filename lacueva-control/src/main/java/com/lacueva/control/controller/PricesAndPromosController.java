package com.lacueva.control.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.bean.dto.PriceDTO;
import com.lacueva.control.bean.dto.PromoDTO;
import com.lacueva.control.converter.PriceToPriceDTOConverter;
import com.lacueva.control.converter.PromoToPromoDTOConverter;
import com.lacueva.control.dao.PriceDao;
import com.lacueva.control.dao.PromoDao;

/**
 * Handles requests for the application prices.
 */
@Controller
@EnableWebMvc
public class PricesAndPromosController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private PriceDao priceDao;

    @Inject
    private PromoDao promoDao;

    @RequestMapping(value = "/prices/priceList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<PriceDTO> getPriceListinJSON(
	    @RequestParam(value = "shop") Long id) {
	Shop shop = new Shop();
	shop.setId(id);
	List<Price> prices = priceDao.findPricesByShop(shop);
	List<PriceDTO> pricesDTO = new ArrayList<PriceDTO>();
	for (Price price : prices) {
	    pricesDTO.add(PriceToPriceDTOConverter.convert(price));
	}
	return pricesDTO;
    }

    @RequestMapping(value = "/promos/promoList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<PromoDTO> getPromoListinJSON(
	    @RequestParam(value = "shop") Long id) {
	Shop shop = new Shop();
	shop.setId(id);
	List<Promo> promos = promoDao.findPromosByShop(shop);
	List<PromoDTO> promosDTO = new ArrayList<PromoDTO>();
	for (Promo price : promos) {
	    promosDTO.add(PromoToPromoDTOConverter.convert(price));
	}
	return promosDTO;
    }
}