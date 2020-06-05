package com.cts.training.netflixzuulapigatewayserver.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


public class ZuulPostLoggingFilter extends ZuulFilter{

	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Override
	public boolean shouldFilter() {
        logger.info("Should Filter method Start");
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext responseContext = RequestContext.getCurrentContext();
		InputStream responseDataStream = responseContext.getResponseDataStream();
		if(responseDataStream == null) {
			logger.info("Response --> {} and no body",responseContext.getResponse());
			return null;
		}
		String responseData;
		try {
			responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
			logger.info("Response --> {} and Body --> {}",responseContext.getResponse(),responseData);
			responseContext.setResponseBody(responseData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
