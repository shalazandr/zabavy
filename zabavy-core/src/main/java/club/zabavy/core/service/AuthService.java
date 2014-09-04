package club.zabavy.core.service;

import club.zabavy.core.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {
	String getAuthLink(String vendor, String action);
	User getUserFromCookie(HttpServletRequest request);
	void login(String vendor, String code, HttpServletResponse response) throws IOException;
	void register(String vendor, String code, HttpServletResponse response) throws IOException;
}
