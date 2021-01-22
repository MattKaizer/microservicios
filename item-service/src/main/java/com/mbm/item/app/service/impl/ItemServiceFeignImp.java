/**
 * 
 */
package com.mbm.item.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbm.item.app.clients.ProductRestClient;
import com.mbm.item.app.models.ItemModel;
import com.mbm.item.app.service.ItemModelService;

/**
 * @author MBMJ
 *
 */

@Service("feignService")
public class ItemServiceFeignImp implements ItemModelService {
	
	@Autowired
	private ProductRestClient feignRestclient;

	@Override
	public List<ItemModel> findAll() {
		return feignRestclient.findAll().stream().map(product -> new ItemModel(product, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemModel findById(Long id, Integer quantity) {
		return new ItemModel(feignRestclient.findById(id), quantity);
	}

}
