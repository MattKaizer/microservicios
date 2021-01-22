/**
 * 
 */
package com.mbm.products.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MBMJ
 *
 */

@Data
@EqualsAndHashCode
@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 5013550801527365839L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
//	esto es solo para visualizar el puerto utilizado, no necesito que sea persistente
	@Transient
	private int port;
}
