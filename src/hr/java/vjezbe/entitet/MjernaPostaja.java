package hr.java.vjezbe.entitet;

import java.util.Arrays;

public class MjernaPostaja {
	String naziv;
	Mjesto mjesto;
	GeografskaTocka geografskaTocka;
	Senzor[] senzori = null;

	public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, Senzor[] senzori) {
		super();
		this.naziv = naziv;
		this.mjesto = mjesto;
		this.geografskaTocka = geografskaTocka;
		this.senzori = senzori;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Mjesto getMjesto() {
		return mjesto;
	}

	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}

	public GeografskaTocka getGeografskaTocka() {
		return geografskaTocka;
	}

	public void setGeografskaTocka(GeografskaTocka geografskaTocka) {
		this.geografskaTocka = geografskaTocka;
	}

	public Senzor[] dohvatiSenzore() {
		Arrays.sort(this.senzori, (p1, p2) -> p1.getMjernaJedinica().compareTo(p2.getMjernaJedinica()));
		return this.senzori;
	}

}
