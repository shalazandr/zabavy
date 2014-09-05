package club.zabavy.core.service;

import club.zabavy.core.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {
	User getUserFromCookie(HttpServletRequest request);
	void login(String vendor, String code, HttpServletResponse response) throws IOException;
	void register(String vendor, String code, HttpServletResponse response) throws IOException;
	void connect(User user, String vendor, String code) throws IOException;
}
