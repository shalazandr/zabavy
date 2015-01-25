package club.zabavy.core.controller;

import club.zabavy.core.domain.exceptions.ForbiddenActionException;
import club.zabavy.core.domain.exceptions.NotAuthenticatedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExceptionHandlingController {

	@ExceptionHandler(ForbiddenActionException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map handleError(ForbiddenActionException exception) {
		HashMap error = new HashMap();
		error.put("message", exception.getMessage());
		return error;
	}

	@ExceptionHandler(NotAuthenticatedUserException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Map handleError(NotAuthenticatedUserException exception) {
		HashMap error = new HashMap();
		error.put("message", exception.getMessage());
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map handleError(HttpServletRequest req, Exception exception) {
		HashMap error = new HashMap();
		error.put("message", exception.getMessage());
		return error;
	}
}