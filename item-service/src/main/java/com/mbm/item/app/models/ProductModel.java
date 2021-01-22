/**
 * 
 */
package com.mbm.item.app.models;

import java.util.Date;

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
public class ProductModel {

	private Long id;
	private String name;
	private Double price;
	private Date createdAt;
}
