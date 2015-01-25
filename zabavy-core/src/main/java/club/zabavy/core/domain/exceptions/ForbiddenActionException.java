package club.zabavy.core.domain.exceptions;

public class ForbiddenActionException extends RuntimeException {

	public ForbiddenActionException() {}

	public ForbiddenActionException(String message) {
		super(message);
	}
}
