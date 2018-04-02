package hr.java.vjezbe.entitet;

public class MjernaPostaja {
	String naziv;
	Mjesto mjesto;
	GeografskaTocka geografskaTocka;
	
	public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka) {
		super();
		this.naziv = naziv;
		this.mjesto = mjesto;
		this.geografskaTocka = geografskaTocka;
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
	
}
