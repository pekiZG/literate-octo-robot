package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {

	public SenzorVlage() {
		super(new String("%"), (byte) Integer.parseInt("1"));
	}

	@Override
	public String dohvatiPodatkeSenzora() {

		return "Vrijednost: " + super.getVrijednost().toString() + " " + super.getMjernaJedinica().toString()
				+ "vlage zraka";

	}

}
