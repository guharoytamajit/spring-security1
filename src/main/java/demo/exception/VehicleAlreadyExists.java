package demo.exception;

public class VehicleAlreadyExists extends RuntimeException {

	public VehicleAlreadyExists() {
		super();
	}

	public VehicleAlreadyExists(String message) {
		super(message);
	}

	public VehicleAlreadyExists(Object message) {
		super(message.toString());
	}
}
