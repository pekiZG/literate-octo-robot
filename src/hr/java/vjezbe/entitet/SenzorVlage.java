package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {
	
	String naziv;
	
	public SenzorVlage(String naziv) {
		super(new String("C"), (byte) Integer.parseInt("1"));
		this.naziv = naziv;
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		// TODO Auto-generated method stub
		return null;
	}

}
