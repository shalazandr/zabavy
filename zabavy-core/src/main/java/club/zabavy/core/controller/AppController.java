package club.zabavy.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}
