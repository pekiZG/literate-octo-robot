package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {
	
	String nazivKomponente;
	
	public SenzorVlage(String nazivKomponente) {
		super(new String("%"), (byte) Integer.parseInt("1"));
		this.nazivKomponente = nazivKomponente;
	}

	@Override
	public String dohvatiPodatkeSenzora() {
		
		
		
	}

}
