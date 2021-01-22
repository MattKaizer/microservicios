package com.mbm.zuulserver.app.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeFilter extends ZuulFilter {
//filtro q calcula el tiempo de inicio (PRE) antes que se resuelva la ruta
	private static Logger log = LoggerFactory.getLogger(PostTimeFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		log.info("Entrando a POST FILTER");
		
		Long initTime = (Long)request.getAttribute("initTime");
		Long finalTime = System.currentTimeMillis();
		Long elapsedTime = finalTime - initTime;
		request.setAttribute("initTime", initTime);
		
		log.info(String.format("Tiempo transcurrido en segundos %s seg."	, elapsedTime.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s mil."	, elapsedTime.doubleValue()));
		return null;
	}

	@Override
	public String filterType() {
		return "post"; //minus!
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
