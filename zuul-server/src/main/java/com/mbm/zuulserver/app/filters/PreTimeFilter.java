package com.mbm.zuulserver.app.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeFilter extends ZuulFilter {
//filtro q calcula el tiempo de inicio (PRE) antes que se resuelva la ruta
	private static Logger log = LoggerFactory.getLogger(PreTimeFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		Long initTime = System.currentTimeMillis();
		request.setAttribute("initTime", initTime);
		return null;
	}

	@Override
	public String filterType() {
		return "pre"; //minus!
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
