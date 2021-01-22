package com.mbm.item.app.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mbm.item.app.models.ProductModel;

/**
 * @author MBMJ
 *
 */

@FeignClient(name = "products-service")
public interface ProductRestClient {
	/*
	 * feignClient point to the same mapping of service controller, in this case Product
	 */
	@GetMapping("/")
	public List<ProductModel> findAll();
	
	@GetMapping("/{id}")
	public ProductModel findById(@PathVariable Long id);
}
