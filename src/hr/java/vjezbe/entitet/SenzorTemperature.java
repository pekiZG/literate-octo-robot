package hr.java.vjezbe.entitet;

public class SenzorTemperature extends Senzor {

	String naziv;

	public SenzorTemperature(String naziv) {
		super(new String("C"), (byte) Integer.parseInt("2"));
		this.naziv = naziv;
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		// TODO Auto-generated method stub
		return null;
	}

}
