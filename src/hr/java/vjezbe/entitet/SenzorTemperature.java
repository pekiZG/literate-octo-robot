package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Random;

import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SenzorTemperature extends Senzor {

	private String nazivKomponente;

	private static final Logger logger = LoggerFactory.getLogger(SenzorTemperature.class);

	public SenzorTemperature(String nazivKonponente) {
		super(new String("°C"), (byte) Integer.parseInt("2"));
		this.nazivKomponente = nazivKonponente;
	}

	@Override // Ovo je dekorator
	public String dohvatiPodatkeSenzora() {

		return "Komponenta: " + this.nazivKomponente + ", vrijednost: " + super.getVrijednost().toString() + " "
				+ super.getMjernaJedinica().toString() + ", Način rada: " + super.getRadSenzora().name()  ;

	}

	public void generirajVrijednost() throws VisokaTemperaturaException {

		int nasumicmaVrijednost = (new Random().nextInt(100)) - 50 + 1;
		BigDecimal vrijednost = new BigDecimal(nasumicmaVrijednost);

		super.setVrijednost(vrijednost);

		if (nasumicmaVrijednost > 40) {
			logger.warn("Opaca, Visoka temperatura");
			throw new VisokaTemperaturaException("Jako visoka temperature");
		}

		if (nasumicmaVrijednost < -10) {
			logger.warn("Opaca, Niska temperatura");
			throw new NiskaTemperaturaException("Jako niska temperatura");
		}
	}

}
