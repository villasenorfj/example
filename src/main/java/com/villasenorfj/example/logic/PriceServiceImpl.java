package com.villasenorfj.example.logic;

import java.util.Date;
import java.util.List;

import com.villasenorfj.example.entities.Price;
import com.villasenorfj.example.ex.PriceNotFoundException;
import com.villasenorfj.example.repo.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Price service implementacion.
 */
@Service
public class PriceServiceImpl implements PriceService {
	
	/** repository fr Price. */
	@Autowired private PriceRepository repo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price applyingPrice(Integer brandId, Date date, Integer productId) {
		Pageable p = PageRequest.of(0, 1);
		List<Price> res = repo.applyingPrice(brandId, date, productId, p);
		if (res.isEmpty()) {
			throw new PriceNotFoundException();
		}
		return res.get(0);
	}

}
