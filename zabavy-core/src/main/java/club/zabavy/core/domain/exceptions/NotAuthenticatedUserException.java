package club.zabavy.core.domain.exceptions;

public class NotAuthenticatedUserException extends RuntimeException {

	public NotAuthenticatedUserException() {}

	public NotAuthenticatedUserException(String message) {
		super(message);
	}
}
