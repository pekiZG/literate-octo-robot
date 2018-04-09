package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Mjesto {
	String naziv;
	Zupanija zupanija;
	VrstaMjesta vrstaMjesta;
	List<MjernaPostaja> mjernePostaje;

	public Mjesto(String naziv, Zupanija zupanija, VrstaMjesta vrstaMjesta) {
		super();
		this.naziv = naziv;
		this.zupanija = zupanija;
		this.vrstaMjesta = vrstaMjesta;
		this.mjernePostaje = new ArrayList<>();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Zupanija getZupanija() {
		return zupanija;
	}

	public void setZupanija(Zupanija zupanija) {
		this.zupanija = zupanija;
	}

	public VrstaMjesta getVrstaMjesta() {
		return vrstaMjesta;
	}

	public void setVrstaMjesta(VrstaMjesta vrstaMjesta) {
		this.vrstaMjesta = vrstaMjesta;
	}

	public List<MjernaPostaja> getMjernePostaje() {
		return mjernePostaje;
	}

}
