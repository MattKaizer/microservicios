/**
 * 
 */
package com.mbm.products.app.service;

import java.util.List;

import com.mbm.products.app.models.entity.Product;

/**
 * @author MBMJ
 *
 */

public interface ProductService {
	public List<Product> findAll();
	public Product findById(Long id);
}
