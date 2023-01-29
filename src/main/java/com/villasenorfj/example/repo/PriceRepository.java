package com.villasenorfj.example.repo;

import java.util.Date;
import java.util.List;

import com.villasenorfj.example.entities.Price;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Repository for Price Entity.
 */
public interface PriceRepository extends JpaRepository<Price, Integer> {

	/**
	 * Get appliying prices.
	 * @param brandId identificador de cadena
	 * @param date fecha de aplicación
	 * @param productId identificador de producto
	 * @param pageable paginacion
	 * @return lista de precios a aplicar
	 */
	@Query("SELECT p from Price p where p.brandId = :brandId"
			+ " and :date BETWEEN p.startDate and p.endDate"
			+ " and p.productId = :productId "
			+ " ORDER BY p.priority DESC, p.id") 
	public List<Price> applyingPrice(@Param("brandId") Integer brandId,
			@Param("date") Date date,
			@Param("productId") Integer productId,
			Pageable pageable);

}
