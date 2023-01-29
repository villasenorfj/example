package com.villasenorfj.example.rest;

import java.util.Date;

import com.villasenorfj.example.entities.Price;
import com.villasenorfj.example.logic.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



/**
 * Endpoint for prices.
 */
@RestController @RequestMapping(path = "/api/price") @Slf4j
public class PriceResource {
	
	/** repository fr Price. */
	@Autowired private PriceService srv;

	/**
	 * GET service.
	 * @param date fecha de aplicación
	 * @param productId identificador de producto
	 * @param brandId identificador de cadena
	 * @return precio a aplicar
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Price price(@RequestParam(name = "date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss") Date date,
			@RequestParam(name = "productId", required = true) Integer productId,
			@RequestParam(name = "brandId", required = true) Integer brandId) {
		log.debug("Search for price date = [" + date +"] productId = [" + productId + "] brandId = [" + brandId + "]");
		return srv.applyingPrice(brandId, date, productId);
	}
}
