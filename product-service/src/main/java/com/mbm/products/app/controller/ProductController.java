/**
 * 
 */
package com.mbm.products.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mbm.products.app.models.entity.Product;
import com.mbm.products.app.service.ProductService;

/**
 * @author MBMJ
 *
 */

@RestController
public class ProductController {
	
//	@Autowired
//	private Environment env;

	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public List<Product> findAll() {
		return productService.findAll().stream().map(product -> {
//			product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id) {
		Product product = productService.findById(id);
//		product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(port);
		
		//Simulación de error para probar hystrix
//		boolean ok = false;
//		if ( ok == false) {
//			throw new Exception("No se pudo cargar el producto");
//		}
//		Simulación de timeOut
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return product;
	}
}
