package com.villasenorfj.example.logic;

import java.util.Date;

import com.villasenorfj.example.entities.Price;

/**
 * Price service interface
 */
public interface PriceService {
	
	/**
	 * Get appliying price.
	 * @param brandId identificador de cadena
	 * @param date fecha de aplicación
	 * @param productId identificador de producto
	 * @return precio a aplicar
	 */
	public Price applyingPrice(Integer brandId, Date date, Integer productId);
}
