/**
 * 
 */
package com.mbm.products.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbm.products.app.models.entity.Product;

/**
 * @author MBMJ
 *
 */

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

}
