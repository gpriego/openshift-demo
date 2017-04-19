package com.isb.skeleton.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	@RequestMapping("/hello")
	public String hello(){
		return "Ey! I'm in Paas";
	}

}
