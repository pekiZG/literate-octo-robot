package hr.java.vjezbe.entitet;

public class Zupanija {
	String naziv;
	Drzava drzava;

	public Zupanija(String naziv, Drzava drzava) {
		super();
		this.naziv = naziv;
		this.drzava = drzava;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

}
