package club.zabavy.core.service.impl;

import club.zabavy.core.dao.CredentialDAO;
import club.zabavy.core.domain.Vendor;
import club.zabavy.core.domain.entity.Credential;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.domain.exceptions.*;
import club.zabavy.core.service.AuthService;
import club.zabavy.core.service.SocialNetworkUtil;
import club.zabavy.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	SocialNetworkUtil socialNetworkUtil;

	@Autowired
	CredentialDAO credentialDAO;

	@Autowired
	UserService userService;

	@Value("${app.secret}") private String appSecret;

	public String createAuthToken(long userId) {
		String authToken = DigestUtils.md5DigestAsHex((userId + appSecret).getBytes());
		authToken = DigestUtils.md5DigestAsHex((userId + authToken).getBytes());
		return userId + ">>" + authToken;
	}

	public boolean isAuthTokenValid(String token) {
		String values[] = token.split(">>");
		return token.equals(createAuthToken(Long.parseLong(values[0])));
	}

	public void login(String vendor, String code, HttpServletResponse response) throws IOException {
		String token = socialNetworkUtil.getToken(vendor, code, "login");
		long vendorUserId = socialNetworkUtil.getVendorUserId(vendor, token);
		Credential credential = credentialDAO.find(Vendor.valueOf(vendor.toUpperCase()), vendorUserId);
		if(credential == null) {
			throw new CredentialDoesNotExistException();
		} else {
			Cookie cookie = new Cookie("zabavy.auth", createAuthToken(credential.getUser().getId()));
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			cookie.setMaxAge(2592000); // 30 days
			response.addCookie(cookie);
		}
	}

	@Transactional
	public void register(String vendor, String code, HttpServletResponse response) throws IOException {
		String token = socialNetworkUtil.getToken(vendor, code, "register");
		long vendorUserId = socialNetworkUtil.getVendorUserId(vendor, token);
		Credential credential = credentialDAO.find(Vendor.valueOf(vendor.toUpperCase()), vendorUserId);
		if(credential == null) {
			User user = socialNetworkUtil.getVendorUserInfo(vendor, token);
			userService.insert(user);
			credential = new Credential(user, Vendor.valueOf(vendor.toUpperCase()), vendorUserId);
			credentialDAO.insert(credential);

			Cookie cookie = new Cookie("zabavy.auth", createAuthToken(user.getId()));
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			cookie.setMaxAge(2592000); // 30 days
			response.addCookie(cookie);
		} else {
			throw new CredentialAlreadyExistException();
		}
	}

	@Transactional
	public void connect(User user, String vendor, String code) throws IOException {
		String token = socialNetworkUtil.getToken(vendor, code, "connect");
		long vendorUserId = socialNetworkUtil.getVendorUserId(vendor, token);
		Credential credential = credentialDAO.find(Vendor.valueOf(vendor.toUpperCase()), vendorUserId);
		if(credential == null) {
			credential = new Credential(user, Vendor.valueOf(vendor.toUpperCase()), vendorUserId);
			credentialDAO.insert(credential);
		} else {
			throw new CredentialAlreadyExistException();
		}
	}

	public User getUserFromCookie(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, "zabavy.auth");
		if(cookie == null) {
			throw new NotAuthenticatedUserException();
		} else {
			if(isAuthTokenValid(cookie.getValue())) {
				return userService.findById(Long.parseLong(cookie.getValue().split(">>")[0]));
			} else {
				throw new InvalidAuthTokenException();
			}
		}
	}

}