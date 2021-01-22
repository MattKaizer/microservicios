/**
 * 
 */
package com.mbm.item.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mbm.item.app.models.ItemModel;
import com.mbm.item.app.models.ProductModel;
import com.mbm.item.app.service.ItemModelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author MBMJ
 *
 */

@RestController
public class ItemModelController {

	@Autowired
	@Qualifier("feignService")
	private ItemModelService itemService;
	
	@GetMapping("/")
	public List<ItemModel> findAll() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "alternativeMethod")
	@GetMapping("/{id}/quantity/{quantity}")
	public ItemModel getDetail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
	
	public ItemModel alternativeMethod(Long id, Integer quanity) {
		ItemModel item = new ItemModel();
		ProductModel product = new ProductModel();
		item.setQuantity(quanity);
		product.setId(id);
		product.setName("Sony PSP5");
		product.setPrice(500.00);
		item.setProductModel(product);
		return item;
	}
}
