package com.villasenorfj.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.villasenorfj.example.entities.Price;
import com.villasenorfj.example.error.AppError;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleApplicationTests {
	
	@Autowired private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void test() throws Exception {
		
		this.mockMvc
			.perform(get("/api/price"))
			.andExpect(status().is(400));
		
		byte[] resError404 = this.mockMvc
			.perform(get("/api/price?brandId=1&productId=35455&date=2020-06-13-00.00.00"))
			.andExpect(status().is(404))
			.andReturn().getResponse().getContentAsByteArray();
		
		AppError error404 = mapper.readValue(resError404, AppError.class);
		assertEquals(404, error404.getStatus().intValue());
		assertEquals("Price not found", error404.getMessage());
		
		// Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (CADENA1)
		byte[] resOk1 = this.mockMvc
			.perform(get("/api/price?brandId=1&productId=35455&date=2020-06-14-10.00.00"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsByteArray();
		Price p1 = mapper.readValue(resOk1, Price.class);
		assertEquals(1, p1.getId());
		
		// Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (CADENA1)
		byte[] resOk2 = this.mockMvc
			.perform(get("/api/price?brandId=1&productId=35455&date=2020-06-14-16.00.00"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsByteArray();
		Price p2 = mapper.readValue(resOk2, Price.class);
		assertEquals(2, p2.getId());
		
		// Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (CADENA1)
		byte[] resOk3 = this.mockMvc
			.perform(get("/api/price?brandId=1&productId=35455&date=2020-06-14-21.00.00"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsByteArray();
		Price p3 = mapper.readValue(resOk3, Price.class);
		assertEquals(1, p3.getId());
		
		// Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (CADENA1)
		byte[] resOk4 = this.mockMvc
			.perform(get("/api/price?brandId=1&productId=35455&date=2020-06-15-10.00.00"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsByteArray();
		Price p4 = mapper.readValue(resOk4, Price.class);
		assertEquals(3, p4.getId());
		
		// Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (CADENA1)
		byte[] resOk5 = this.mockMvc
			.perform(get("/api/price?brandId=1&productId=35455&date=2020-06-16-21.00.00"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsByteArray();
		Price p5 = mapper.readValue(resOk5, Price.class);
		assertEquals(4, p5.getId());
	}

}
