package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;
import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.GeografskaTocka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.RadioSondaznaMjernaPostaja;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.SenzorTemperature;
import hr.java.vjezbe.entitet.SenzorVjetra;
import hr.java.vjezbe.entitet.SenzorVlage;
import hr.java.vjezbe.entitet.Zupanija;
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {

	private static final int BROJ_KLASICNIH_MJERNIH_POSTAJA = 2;
	private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;

	static { System.setProperty("logback.configurationFile", "logback.xml");}
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Integer brojMjernihPostaja = BROJ_KLASICNIH_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;
		MjernaPostaja[] mjernePostaje = new MjernaPostaja[brojMjernihPostaja];

		// Create
		for (int i = 0; i < brojMjernihPostaja; i++) {
			System.out.println("Unesite " + (i + 1) + ". mjernu postaju:");

			if (i < BROJ_KLASICNIH_MJERNIH_POSTAJA) {
				mjernePostaje[i] = kreirajKlasicnuMjernuPostaju(scanner);
			} else {
				mjernePostaje[i] = kreirajRadioSondaznuMjernuPostaju(scanner);
			}
		}

		// List out
		for (int i = 0; i < mjernePostaje.length; i++) {
			System.out.println("\n--------------------");
			System.out.println("Naziv mjerne postaje: " + mjernePostaje[i].getNaziv());

			System.out.println("Postaja se nalazi u mjestu " + mjernePostaje[i].getMjesto().getNaziv() + ", županiji "
					+ mjernePostaje[i].getMjesto().getZupanija().getNaziv() + ", državi "
					+ mjernePostaje[i].getMjesto().getZupanija().getDrzava().getNaziv());

			System.out.println("Točne koordinate postaje su x:" + mjernePostaje[i].getGeografskaTocka().getX() + " y:"
					+ mjernePostaje[i].getGeografskaTocka().getY());

			Senzor[] sortiraniSenzori = mjernePostaje[i].dohvatiSenzore();
			for (int j = 0; j < sortiraniSenzori.length; j++) {
				System.out.println(sortiraniSenzori[j].dohvatiPodatkeSenzora());
			}

		}

		while (true) {
			System.out.println("Generiram nasumične vrijednost senzora temperature");
			for (int i = 0; i < mjernePostaje.length; i++) {
				Senzor[] senzori = mjernePostaje[i].dohvatiSenzore();
				for (int j = 0; j < senzori.length; j++) {
					if (senzori[j] instanceof SenzorTemperature) {
						SenzorTemperature senzorTemperature = (SenzorTemperature) senzori[j];
						try {
							senzorTemperature.generirajVrijednost();
						} catch (VisokaTemperaturaException e) {
							System.out.println("Dogodila se greška: " + e.getMessage());
							System.out.println("Na mjernoj postaji: " + mjernePostaje[i].getNaziv());
						} catch (NiskaTemperaturaException e) {
							System.out.println("Dogodila se greška: " + e.getMessage());
							System.out.println("Na mjernoj postaji: " + mjernePostaje[i].getNaziv());
						}
					}
				}
			}

			try {
				Thread.sleep(1 * 1000);
				logger.info("Spavam");
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}

		// scanner.close();

	}

	public static Drzava kreirajDrzavu(Scanner scanner) {

		System.out.println("Unesite naziv države:");
		String nazivDrzave = scanner.nextLine();

		System.out.println("Unesite površinu države:");
		BigDecimal povrsinaDrzave = Validator.unesiBigDecimal(scanner);

		return new Drzava(nazivDrzave, povrsinaDrzave);
	}

	public static Zupanija kreirajZupaniju(Scanner scanner) {

		Drzava drzava = kreirajDrzavu(scanner);

		System.out.println("Unesite naziv županije:");
		String nazivZupanije = scanner.nextLine();

		return new Zupanija(nazivZupanije, drzava);
	}

	public static Mjesto kreirajMjesto(Scanner scanner) {

		Zupanija zupanija = kreirajZupaniju(scanner);

		System.out.println("Unesite naziv mjesta:");
		String nazivMjesta = scanner.nextLine();

		return new Mjesto(nazivMjesta, zupanija);
	}

	public static GeografskaTocka kreirajGeografskuTocku(Scanner scanner) {

		System.out.println("Unesite Geo koordinatu X:");
		BigDecimal x = Validator.unesiBigDecimal(scanner);

		System.out.println("Unesite Geo koordinatu Y:");
		BigDecimal y = Validator.unesiBigDecimal(scanner);

		return new GeografskaTocka(x, y);
	}

	public static Senzor[] kreirajSenzore(Scanner scanner) {

		Senzor[] senzori = new Senzor[3];

		senzori[0] = kreirajSenzorTemperature(scanner);
		senzori[1] = kreirajSenzorVlage(scanner);
		senzori[2] = kreirajSenzorVjetra(scanner);

		return senzori;

	}

	private static SenzorVjetra kreirajSenzorVjetra(Scanner scanner) {
		System.out.println("Unesite veličinu senzora brzine vjetra");
		String velicinaSenzoraVjetra = scanner.nextLine();

		System.out.println("Unesite vrijednost senzora vjetra");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		SenzorVjetra senzorVjetra = new SenzorVjetra(velicinaSenzoraVjetra);
		senzorVjetra.setVrijednost(vrijednost);

		return senzorVjetra;
	}

	private static SenzorVlage kreirajSenzorVlage(Scanner scanner) {
		System.out.println("Unesite vrijednost senzora vlage:");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		SenzorVlage senzorVlage = new SenzorVlage();
		senzorVlage.setVrijednost(vrijednost);

		return senzorVlage;
	}

	private static SenzorTemperature kreirajSenzorTemperature(Scanner scanner) {
		System.out.println("Unesite elektroničku komponentu za senzor temperature:");
		String nazivKonponente = scanner.nextLine();

		System.out.println("Unesite vrijednost senzora temperature:");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		SenzorTemperature senzorTemperature = new SenzorTemperature(nazivKonponente);
		senzorTemperature.setVrijednost(vrijednost);

		return senzorTemperature;
	}

	public static MjernaPostaja kreirajKlasicnuMjernuPostaju(Scanner scanner) {

		System.out.println("Unesite naziv mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		Senzor[] senzori = kreirajSenzore(scanner);

		return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
	}

	public static MjernaPostaja kreirajRadioSondaznuMjernuPostaju(Scanner scanner) {

		System.out.println("Unesite naziv radio sondažne mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		Senzor[] senzori = kreirajSenzore(scanner);

		return new RadioSondaznaMjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
	}

}
