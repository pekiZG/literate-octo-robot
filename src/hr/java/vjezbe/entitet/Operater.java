package hr.java.vjezbe.entitet;

import hr.java.vjezbe.util.OIB;

public class Operater {
	
	String oib;
	String ime;
	String prezime;
	
	public Operater(String oib, String ime, String prezime) {
		super();
		this.oib = oib;
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public String getOIB() {
		return oib;
	}
	
	public void setOIB(String oIB) {
		oib = oIB;
	}
	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public boolean provjeriOIB() {
		return OIB.checkOIB(this.oib);
	}
	
	

}



