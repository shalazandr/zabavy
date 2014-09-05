package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.User;
import club.zabavy.core.domain.exceptions.NotAuthenticatedUserException;
import club.zabavy.core.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthService authService;

	@RequestMapping(value = "/login/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void login(	HttpServletRequest request,
						HttpServletResponse response,
						@PathVariable("vendor") String vendor,
						@RequestParam(value = "code", required = false) String code) throws IOException {

		if(code == null) {
			response.sendRedirect(authService.getAuthLink(vendor, "login"));
		} else {
			authService.login(vendor, code, response);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("zabavy.auth", "");
		cookie.setPath("/");
		cookie.setMaxAge(1);
		response.addCookie(cookie);
	}

	@RequestMapping(value = "/register/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void register(	HttpServletRequest request,
							HttpServletResponse response,
							@PathVariable("vendor") String vendor,
							@RequestParam(value = "code", required = false) String code) throws IOException {
		if(code == null) {
			response.sendRedirect(authService.getAuthLink(vendor, "register"));
		} else {
			authService.register(vendor, code, response);
		}
	}

	@RequestMapping(value = "/connect/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void connect(HttpServletRequest request,
						HttpServletResponse response,
						@PathVariable("vendor") String vendor,
						@RequestParam(value = "code", required = false) String code) throws IOException {
		if(code == null) {
			response.sendRedirect(authService.getAuthLink(vendor, "connect"));
		} else {
			User user = authService.getUserFromCookie(request);
			if(user == null) throw new NotAuthenticatedUserException();
			authService.connect(user, vendor, code);
		}
	}
}
