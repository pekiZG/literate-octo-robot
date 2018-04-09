package hr.java.vjezbe.entitet;

public class SenzorVlage extends Senzor {

	public SenzorVlage(RadSenzora radSenzora) {
		super(new String("%"), (byte) Integer.parseInt("1"), radSenzora);
	}

	@Override
	public String dohvatiPodatkeSenzora() {

		return "Vrijednost: " + super.getVrijednost().toString() + " " + super.getMjernaJedinica().toString()
				+ "vlage zraka";

	}

}
