package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Zupanija;

public class ZupanijaSorter implements Comparator<Zupanija> {

	public ZupanijaSorter() {
		
	}

	@Override
	public int compare(Zupanija prvaZupanija, Zupanija drugaZupanija) {
		return prvaZupanija.getNaziv().compareTo(drugaZupanija.getNaziv());
	}

}
