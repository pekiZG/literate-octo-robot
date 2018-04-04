package hr.java.vjezbe.iznimke;

public class VisokaTemperaturaException extends Exception {

	private static final long serialVersionUID = -2464415359284430992L;

	public VisokaTemperaturaException() {
		super();
	}

	public VisokaTemperaturaException(String message) {
		super(message);
	}

	public VisokaTemperaturaException(Throwable cause) {
		super(cause);
	}

	public VisokaTemperaturaException(String message, Throwable cause) {
		super(message, cause);
	}

}
