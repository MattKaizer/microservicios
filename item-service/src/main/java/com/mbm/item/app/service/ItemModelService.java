/**
 * 
 */
package com.mbm.item.app.service;

import java.util.List;

import com.mbm.item.app.models.ItemModel;

/**
 * @author MBMJ
 *
 */
public interface ItemModelService {

	public List<ItemModel> findAll();
	public ItemModel findById(Long id, Integer quantity);
}
