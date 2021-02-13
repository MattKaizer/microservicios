package com.mbm.item.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(ItemModelController.class);

	@Autowired
	@Qualifier("feignService")
	private ItemModelService itemService;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Autowired
	private Environment env;	
	
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
	
	@GetMapping("/get-config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String puerto) {
		Map<String, String> json = new HashMap<String, String>();
		json.put("text", texto);
		json.put("port", puerto);
		
		log.info("Puerto: " + puerto);
		
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.mail", env.getProperty("configuracion.autor.mail"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
}
