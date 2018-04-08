package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Zupanija;

public class ZupanijaSorter implements Comparator<Zupanija> {

	public ZupanijaSorter() {

	}

	@Override
	public int compare(Zupanija prvaZupanija, Zupanija drugaZupanija) {
		
		//int compareTo = prvaZupanija.getNaziv().compareTo(drugaZupanija.getNaziv());
		//return compareTo;
		
		return prvaZupanija.getNaziv().compareTo(drugaZupanija.getNaziv());
	}

}
