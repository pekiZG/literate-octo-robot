package hr.java.vjezbe.entitet;

import java.util.List;

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {

	Integer visina;

	public RadioSondaznaMjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
		super(naziv, mjesto, geografskaTocka, senzori);
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
