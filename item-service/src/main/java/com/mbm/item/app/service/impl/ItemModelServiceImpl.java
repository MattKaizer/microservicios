/**
 * 
 */
package com.mbm.item.app.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mbm.item.app.models.ItemModel;
import com.mbm.item.app.models.ProductModel;
import com.mbm.item.app.service.ItemModelService;

/**
 * @author MBMJ
 *
 */

@Service("restTemplateService")
public class ItemModelServiceImpl implements ItemModelService {
	
	@Autowired
	private RestTemplate restClient;

	@Override
	public List<ItemModel> findAll() {
//		TODO: this is like tight coupling, just for this example. Eureka/Spring CLoud is usually used to avoid this.
//		List<ProductModel> products = Arrays.asList(restClient.getForObject("http://localhost:10000", ProductModel[].class));
		List<ProductModel> products = Arrays.asList(restClient.getForObject("http://products-service", ProductModel[].class));
		return products.stream().map(product -> new ItemModel(product, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemModel findById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
//		TODO: this is like tight coupling, just for this example. Eureka is usually used to avoid this.
//		ProductModel productModel = restClient.getForObject("http://localhost:10000/{id}", ProductModel.class, pathVariables);
		ProductModel productModel = restClient.getForObject("http://products-service/{id}", ProductModel.class, pathVariables);
		return new ItemModel(productModel, quantity);
	}

}
