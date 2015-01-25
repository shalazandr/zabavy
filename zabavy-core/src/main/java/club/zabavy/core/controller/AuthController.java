package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.User;
import club.zabavy.core.domain.exceptions.CredentialDoesNotExistException;
import club.zabavy.core.domain.exceptions.NotAuthenticatedUserException;
import club.zabavy.core.service.AuthService;
import club.zabavy.core.service.SocialNetworkUtil;
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

	@Autowired
	SocialNetworkUtil socialNetworkUtil;

	@RequestMapping(value = "/login/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void login(	HttpServletRequest request,
						HttpServletResponse response,
						@PathVariable("vendor") String vendor,
						@RequestParam(value = "code", required = false) String code) throws IOException {

		if(code == null) {
			response.sendRedirect(socialNetworkUtil.getAuthLink(vendor, "login"));
		} else {
			try {
				authService.login(vendor, code, response);
				response.sendRedirect("/");
			} catch(CredentialDoesNotExistException e) {
				response.sendRedirect("/users/new/" + vendor);	// FIXME: it's bad for non browser client
			}
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = new Cookie("zabavy.auth", "");
		cookie.setPath("/");
		cookie.setMaxAge(1);
		response.addCookie(cookie);
		response.sendRedirect("/");	// FIXME: it's bad for non browser client
	}

	@RequestMapping(value = "/register/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void register(	HttpServletRequest request,
							HttpServletResponse response,
							@PathVariable("vendor") String vendor,
							@RequestParam(value = "code", required = false) String code) throws IOException {
		if(code == null) {
			response.sendRedirect(socialNetworkUtil.getAuthLink(vendor, "register"));
		} else {
			authService.register(vendor, code, response);
			response.sendRedirect("/");	// FIXME: it's bad for non browser client
		}
	}

	@RequestMapping(value = "/connect/{vendor}", method = RequestMethod.GET)
	@ResponseBody
	public void connect(HttpServletRequest request,
						HttpServletResponse response,
						@PathVariable("vendor") String vendor,
						@RequestParam(value = "code", required = false) String code) throws IOException {
		if(code == null) {
			response.sendRedirect(socialNetworkUtil.getAuthLink(vendor, "connect"));
		} else {
			User user = authService.getUserFromCookie(request);
			if(user == null) throw new NotAuthenticatedUserException();
			authService.connect(user, vendor, code);
		}
	}
}
