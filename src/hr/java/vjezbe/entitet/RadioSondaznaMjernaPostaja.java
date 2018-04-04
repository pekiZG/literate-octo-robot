package hr.java.vjezbe.entitet;

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {

	Integer visina;

	public RadioSondaznaMjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori, Operater operater) {
		super(naziv, mjesto, geografskaTocka, senzori, operater);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void podesiVisinuPostaje(Integer visina) {
		this.visina = visina;
	}

	@Override
	public Integer dohvatiVisinuPostaje() {
		return this.visina;
	}

}
