package hr.java.vjezbe.entitet;

public class SenzorTemperature extends Senzor {

	String nazivKomponente;

	public SenzorTemperature(String nazivKonponente) {
		super(new String("C"), (byte) Integer.parseInt("2"));
		this.nazivKomponente = nazivKonponente;
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		// TODO Auto-generated method stub
		return null;
	}

}
