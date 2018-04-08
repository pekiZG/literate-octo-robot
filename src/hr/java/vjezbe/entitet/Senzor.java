package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public abstract class Senzor {

	private String mjernaJedinica;
	private Byte preciznost;
	private BigDecimal vrijednost;
	private RadSenzora radSenzora;

	public Senzor(String mjernaJedinica, Byte preciznost) {
		super();
		this.mjernaJedinica = mjernaJedinica;
		this.preciznost = preciznost;
	}

	public RadSenzora getRadSenzora() {
		return radSenzora;
	}

	public void setRadSenzora(RadSenzora radSenzora) {
		this.radSenzora = radSenzora;
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
