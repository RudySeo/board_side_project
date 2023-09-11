package sideproject.board.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String id) {
		super("Error message" + id);
	}
}