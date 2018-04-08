package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Mjesto {
	private String naziv;
	private Zupanija zupanija;
	private VrstaMjesta vrstaMjesta;
	private List<MjernaPostaja> listaMjernihPostaja;

	public Mjesto(String naziv, Zupanija zupanija) {
		super();
		this.naziv = naziv;
		this.zupanija = zupanija;
		this.listaMjernihPostaja = new ArrayList<>();
	}

	public VrstaMjesta getVrstaMjesta() {
		return vrstaMjesta;
	}

	public void setVrstaMjesta(VrstaMjesta vrstaMjesta) {
		this.vrstaMjesta = vrstaMjesta;
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

	public List<MjernaPostaja> getListaMjernihPostaja() {
		return listaMjernihPostaja;
	}
	
}
