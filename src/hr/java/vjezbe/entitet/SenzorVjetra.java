package hr.java.vjezbe.entitet;

public class SenzorVjetra extends Senzor {

	String opisJacineVjetra;

	public SenzorVjetra(String opisJacineVjetra) {
		super(new String("km/h"), (byte) Integer.parseInt("3"));
		this.opisJacineVjetra = opisJacineVjetra;
	}

	@Override
	public String dohvatiPodatkeSenzora() {

		return "Veličina: " + opisJacineVjetra + ", vrijednost: " + super.getVrijednost().toString() + " "
				+ super.getMjernaJedinica().toString();

	}

}
