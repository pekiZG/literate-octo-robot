package hr.java.vjezbe.iznimke;

public class NiskaTemperaturaException extends RuntimeException {

	private static final long serialVersionUID = 4787462950417267751L;

	public NiskaTemperaturaException() {
		super();
	}

	public NiskaTemperaturaException(String message) {
		super(message);
	}

	public NiskaTemperaturaException(Throwable cause) {
		super(cause);
	}

	public NiskaTemperaturaException(String message, Throwable cause) {
		super(message, cause);
	}

}
