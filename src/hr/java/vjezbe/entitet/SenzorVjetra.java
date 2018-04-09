package hr.java.vjezbe.entitet;

public class SenzorVjetra extends Senzor {

	String velicinaSenzoraVjetra;

	public SenzorVjetra(String velicinaSenzoraVjetra, RadSenzora radSenzora) {
		super(new String("km/h"), (byte) Integer.parseInt("3"), radSenzora);
		this.velicinaSenzoraVjetra = velicinaSenzoraVjetra;
	}

	@Override
	public String dohvatiPodatkeSenzora() {

		return "Veličina: " + velicinaSenzoraVjetra + ", vrijednost: " + super.getVrijednost().toString() + " "
				+ super.getMjernaJedinica().toString();

	}

}
