package club.zabavy.core.controller;

import club.zabavy.core.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthService authService;

	@RequestMapping(value = "/login/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void login(	HttpServletResponse response,
						@PathVariable("vendor") String vendor,
						@RequestParam(value = "code", required = false) String code) throws IOException {

		if(code == null) {
			response.sendRedirect(authService.getAuthLink(vendor, "login"));
		} else {
			authService.login(vendor, code, response);
		}
	}

}
