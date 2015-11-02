package demo.exception;

public class EmployeeAlreadyExists extends RuntimeException {

	public EmployeeAlreadyExists() {
		super();
	}

	public EmployeeAlreadyExists(String message) {
		super(message);
	}

	public EmployeeAlreadyExists(Object message) {
		super(message.toString());
	}
}
