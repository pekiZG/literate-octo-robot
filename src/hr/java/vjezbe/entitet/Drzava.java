package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Drzava {
	String naziv;
	BigDecimal povrsina;

	public Drzava(String naziv, BigDecimal povrsina) {
		super();
		this.naziv = naziv;
		this.povrsina = povrsina;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public BigDecimal getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(BigDecimal povrsina) {
		this.povrsina = povrsina;
	}

}
