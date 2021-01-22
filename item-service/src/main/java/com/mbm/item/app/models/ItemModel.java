/**
 * 
 */
package com.mbm.item.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author MBMJ
 *
 */

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
	
	private ProductModel productModel;
	private Integer	quantity;
	
	public Double getTotalPrice() {
		return productModel.getPrice() * quantity.doubleValue();
	}

}
