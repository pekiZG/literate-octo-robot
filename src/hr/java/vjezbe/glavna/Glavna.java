package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.GeografskaTocka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.RadSenzora;
import hr.java.vjezbe.entitet.RadioSondaznaMjernaPostaja;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.SenzorTemperature;
import hr.java.vjezbe.entitet.SenzorVjetra;
import hr.java.vjezbe.entitet.SenzorVlage;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {

	private static final int BROJ_KLASICNIH_MJERNIH_POSTAJA = 2;
	private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;

	// static { System.setProperty("logback.configurationFile", "logback.xml");}
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Integer brojMjernihPostaja = BROJ_KLASICNIH_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;
		List<MjernaPostaja> mjernePostaje = new ArrayList<>();

		// Create
		for (int i = 0; i < brojMjernihPostaja; i++) {
			System.out.println("Unesite " + (i + 1) + ". mjernu postaju:");

			if (i < BROJ_KLASICNIH_MJERNIH_POSTAJA) {
				MjernaPostaja klasicnaMjernaPostaja = kreirajKlasicnuMjernuPostaju(scanner);
				mjernePostaje.add(klasicnaMjernaPostaja);
			} else {
				mjernePostaje.add(kreirajRadioSondaznuMjernuPostaju(scanner));
			}
		}
		
		// List out		
		for (MjernaPostaja mjernaPostaja : mjernePostaje) {
			System.out.println("\n--------------------");
			System.out.println("Naziv mjerne postaje: " + mjernaPostaja.getNaziv());

			System.out.println("Postaja se nalazi u mjestu " + mjernaPostaja.getMjesto().getNaziv() + ", županiji "
					+ mjernaPostaja.getMjesto().getZupanija().getNaziv() + ", državi "
					+ mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv());

			System.out.println("Točne koordinate postaje su x:" + mjernaPostaja.getGeografskaTocka().getX() + " y:"
					+ mjernaPostaja.getGeografskaTocka().getY());

			List<Senzor> sortiraniSenzori = mjernaPostaja.dohvatiSenzore();
			
			for (Senzor senzor : sortiraniSenzori) {
				System.out.println(senzor.dohvatiPodatkeSenzora());
			}
		}

		while (true) {
			System.out.println("Generiram nasumične vrijednost senzora temperature");
			for (MjernaPostaja mjernaPostaja : mjernePostaje) {
				List<Senzor> sortiraniSenzori = mjernaPostaja.dohvatiSenzore();
				for (Senzor senzor : sortiraniSenzori) {
					if (senzor instanceof SenzorTemperature) {
						SenzorTemperature senzorTemperature = (SenzorTemperature) senzor;
						try {
							senzorTemperature.generirajVrijednost();
						} catch (VisokaTemperaturaException e) {
							System.out.println("Dogodila se greška: " + e.getMessage());
							System.out.println("Na mjernoj postaji: " + mjernaPostaja.getNaziv());

							logger.error(null, null, null, e);

						} catch (NiskaTemperaturaException e) {
							System.out.println("Dogodila se greška: " + e.getMessage());
							System.out.println("Na mjernoj postaji: " + mjernaPostaja.getNaziv());

							logger.error("Ping", null, null, e);

						}
					}
				}
			}

			try {
				long vrijemeSpavanja = 1 * 1000;
				Thread.sleep(vrijemeSpavanja);
				System.out.println("Spavam");
				logger.info("Spavam");
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}

		// scanner.close();

	}

	private static RadSenzora kreirajRadSenzora(Scanner scanner) {
		for (int i = 0; i < RadSenzora.values().length - 1; i++) {
			System.out.println((i + 1) + ". " + RadSenzora.values()[i]);
		}

		Integer redniBrojSenzora = null;

		while (true) {
			System.out.print("Odabir rada senzora >> ");
			try {
				redniBrojSenzora = scanner.nextInt();
				break;
			} catch (InputMismatchException ex) {
				System.out.println("Neispravan unos!");
				logger.error("Neispravan unos rada senzora!", ex);
			}
		}

		RadSenzora radSenzora;
		if (redniBrojSenzora >= 1 && redniBrojSenzora < RadSenzora.values().length) {
			radSenzora = RadSenzora.values()[redniBrojSenzora - 1];
		} else {
			radSenzora = RadSenzora.OSTALO;
		}
		
		return radSenzora;
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

		Mjesto mjesto = new Mjesto(nazivMjesta, zupanija);
		
		VrstaMjesta vrstaMjesta = kreirajVrstuMjesta(scanner);
		mjesto.setVrstaMjesta(vrstaMjesta);
		
		return mjesto;		
	}

	private static VrstaMjesta kreirajVrstuMjesta(Scanner scanner) {
		
		for (int i = 0; i < VrstaMjesta.values().length - 1; i++) {
			System.out.println((i + 1) + ". " + VrstaMjesta.values()[i]);
		}

		
		Integer redniBrojSenzora = null;
		while (true) {
			System.out.print("Odabir vrste mjesta >> ");
			try {
				redniBrojSenzora = scanner.nextInt();
				break;
			} catch (InputMismatchException ex) {
				System.out.println("Neispravan unos!");
				logger.error("Neispravan unos rada senzora!", ex);
			}
		}

		
		VrstaMjesta vrstaMjesta;
		if (redniBrojSenzora >= 1 && redniBrojSenzora < RadSenzora.values().length) {
			vrstaMjesta = VrstaMjesta.values()[redniBrojSenzora - 1];
		} else {
			vrstaMjesta = VrstaMjesta.OSTALO;
		}
		
		return vrstaMjesta;
	}

	public static GeografskaTocka kreirajGeografskuTocku(Scanner scanner) {

		System.out.println("Unesite Geo koordinatu X:");
		BigDecimal x = Validator.unesiBigDecimal(scanner);

		System.out.println("Unesite Geo koordinatu Y:");
		BigDecimal y = Validator.unesiBigDecimal(scanner);

		return new GeografskaTocka(x, y);
	}

	public static List<Senzor> kreirajSenzore(Scanner scanner) {
		
		List<Senzor> senzori = new ArrayList<>();

		senzori.add(kreirajSenzorTemperature(scanner));		
		senzori.add(kreirajSenzorVlage(scanner));
		senzori.add(kreirajSenzorVjetra(scanner));

		return senzori;
	}

	private static SenzorVjetra kreirajSenzorVjetra(Scanner scanner) {
		System.out.println("Unesite veličinu senzora brzine vjetra");
		String velicinaSenzoraVjetra = scanner.nextLine();

		System.out.println("Unesite vrijednost senzora vjetra");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		SenzorVjetra senzorVjetra = new SenzorVjetra(velicinaSenzoraVjetra);
		senzorVjetra.setVrijednost(vrijednost);
		
		RadSenzora radSenzora = kreirajRadSenzora(scanner);
		senzorVjetra.setRadSenzora(radSenzora);

		return senzorVjetra;
	}

	private static SenzorVlage kreirajSenzorVlage(Scanner scanner) {
		System.out.println("Unesite vrijednost senzora vlage:");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		SenzorVlage senzorVlage = new SenzorVlage();
		senzorVlage.setVrijednost(vrijednost);

		RadSenzora radSenzora = kreirajRadSenzora(scanner);
		senzorVlage.setRadSenzora(radSenzora);
		
		return senzorVlage;
	}

	private static SenzorTemperature kreirajSenzorTemperature(Scanner scanner) {
		System.out.println("Unesite elektroničku komponentu za senzor temperature:");
		String nazivKonponente = scanner.nextLine();

		System.out.println("Unesite vrijednost senzora temperature:");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		SenzorTemperature senzorTemperature = new SenzorTemperature(nazivKonponente);
		senzorTemperature.setVrijednost(vrijednost);
		
		RadSenzora radSenzora = kreirajRadSenzora(scanner);
		senzorTemperature.setRadSenzora(radSenzora);
		
		return senzorTemperature;
	}

	public static MjernaPostaja kreirajKlasicnuMjernuPostaju(Scanner scanner) {

		System.out.println("Unesite naziv mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		List<Senzor> senzori = kreirajSenzore(scanner);

		return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
	}

	public static MjernaPostaja kreirajRadioSondaznuMjernuPostaju(Scanner scanner) {

		System.out.println("Unesite naziv radio sondažne mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		List<Senzor> senzori = kreirajSenzore(scanner);

		return new RadioSondaznaMjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
	}

}
