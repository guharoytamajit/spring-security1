package demo.exception;

public class AccountAlreadyExists extends RuntimeException {
	public AccountAlreadyExists() {
		super();
	}

	public AccountAlreadyExists(String message) {
		super(message);
	}

	public AccountAlreadyExists(Object message) {
		super(message.toString());
	}
}
