package com.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * @author Ayansh Dash
*/

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("file.encoding", "UTF-8");
		return application.sources(ApiServicesApplication.class);
	}

}
