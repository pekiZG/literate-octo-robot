package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public abstract class Senzor {
	
	String mjernaJedinica;
	Byte preciznost;
	BigDecimal vrijednost;
	
	public Senzor(String mjernaJedinica, Byte preciznost) {
		super();
		this.mjernaJedinica = mjernaJedinica;
		this.preciznost = preciznost;
	}

	public String getMjernaJedinica() {
		return mjernaJedinica;
	}

	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}

	public Byte getPreciznost() {
		return preciznost;
	}

	public void setPreciznost(Byte preciznost) {
		this.preciznost = preciznost;
	}

	public BigDecimal getVrijednost() {
		return vrijednost;
	}

	public void setVrijednost(BigDecimal vrijednost) {
		this.vrijednost = vrijednost;
	}
	
	public abstract String dohvatiPodatkeSenzora();
	
}
