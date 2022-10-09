package com.api.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {

	@RequestMapping(value = "/gwi/**/{[path:[^\\.]*}", method = RequestMethod.GET)
	public String redirect() {
		// Forward to home page so that route is preserved.
		return "forward:/";
	}
}
