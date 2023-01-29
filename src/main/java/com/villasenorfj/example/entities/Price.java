package com.villasenorfj.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for table PRICES. 
 */
@Entity @Table(name = "PRICES", indexes = @Index(columnList = "BRAND_ID,PRODUCT_ID"))
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Price {
	/** PRICE_LIST: Identificador de la tarifa de precios aplicable. */
	@Id @Column(nullable = false, length = 10, name = "PRICE_LIST")
	private Integer id;
	
	/** BRAND_ID: foreign key de la cadena del grupo (1 = CADENA1). */
	@Column(nullable = false, length = 5, name = "BRAND_ID")
	private Integer brandId;
	
	/** START_DATE: rango de fechas (inicio) en el que aplica el precio tarifa indicado. */
	@Column(nullable = false, name = "START_DATE")
	private Date startDate;
	
	/** END_DATE: rango de fechas (fin) en el que aplica el precio tarifa indicado. */
	@Column(nullable = false, name = "END_DATE")
	private Date endDate;
	
	/** PRODUCT_ID: Identificador código de producto. */
	@Column(nullable = false, length = 4, name = "PRODUCT_ID")
	private Integer productId;
	
	/** PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico). */
	@Column(nullable = false, length = 2, name = "PRIORITY")
	private Integer priority;
	
	/** PRICE: precio final de venta. */
	@Column(nullable = false, name = "PRICE")
	private Double ammount;

	/** CURR: iso de la moneda. */
	@Column(nullable = false, length = 3, name = "CURR")
	private String curr;
}
