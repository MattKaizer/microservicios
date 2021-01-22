/**
 * 
 */
package com.mbm.item.app.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author MBMJ
 *
 */

@Configuration
public class AppConfiguration {

	@Bean("restClient")
	@LoadBalanced
	public RestTemplate restTemplateRegister() {
		return new RestTemplate();
	}
}
