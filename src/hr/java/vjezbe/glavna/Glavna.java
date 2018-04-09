package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import hr.java.vjezbe.sortiranje.ZupanijaSorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {

	private static final int BROJ_KLASICNIH_MJERNIH_POSTAJA = 2;
	private static final int BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA = 1;

	// static { System.setProperty("logback.configurationFile", "logback.xml");}
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);
	private static List<MjernaPostaja> mjernePostaje;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Integer brojMjernihPostaja = BROJ_KLASICNIH_MJERNIH_POSTAJA + BROJ_RADIO_SONDAZNIH_MJERNIH_POSTAJA;
		mjernePostaje = new ArrayList<>();

		// Create
		for (int i = 0; i < brojMjernihPostaja; i++) {
			System.out.println("Unesite " + (i + 1) + ". mjernu postaju:");

			if (i < BROJ_KLASICNIH_MJERNIH_POSTAJA) {
				mjernePostaje.add(kreirajKlasicnuMjernuPostaju(scanner));
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

		
		// Ispis sortiranih zupanija bez duplikata
		System.out.println("Ispis sortiranih županija:");
		
		List<Zupanija> listaSvihZupanijaIzDrzava = new ArrayList<>();
		for (MjernaPostaja mjernaPostaja : mjernePostaje) {
			listaSvihZupanijaIzDrzava.addAll(mjernaPostaja.getMjesto().getZupanija().getDrzava().getZupanije());
		}
		
		Stream<Zupanija> distinctStream = listaSvihZupanijaIzDrzava.stream().distinct();
		List<Zupanija> distinctList = distinctStream.collect(Collectors.toList());
		distinctList.sort(new ZupanijaSorter());
		distinctList.forEach(z -> System.out.println(z.getNaziv()));

		
		// Ispis mape mjernih postaja sa vrijednostima senzora
		Map<Mjesto, List<Senzor>> collect = mjernePostaje.stream()
				.collect(Collectors.toMap(MjernaPostaja::getMjesto, MjernaPostaja::dohvatiSenzore,
						(mp1, mp2) -> {
							//System.out.println("Dupli ključevi");
							return mp1;
						}));

		for (Mjesto mjesto : collect.keySet()) {
			System.out.println("U mjestu " + mjesto + " su sljedeći senzori:");
			for (Senzor senzor : collect.get(mjesto)) {
				if (senzor instanceof SenzorTemperature) {
					System.out.println("Senzor temperature");
				}
				if (senzor instanceof SenzorVjetra) {
					System.out.println("Senzor brzine vjetra");
				}
				if (senzor instanceof SenzorVlage) {
					System.out.println("Senzor vlage");
				}
			}

		}

		
		// generiranje nasumičnih vrijednost
		while (true) {
			System.out.println("Generiram nasumične vrijednost senzora temperature");
			for (MjernaPostaja mjernaPostaja : mjernePostaje) {
				List<Senzor> senzori = mjernaPostaja.dohvatiSenzore();
				for (Senzor senzor : senzori) {
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

		Optional<MjernaPostaja> postaja = mjernePostaje.stream()
				.filter(p -> p.getMjesto().getZupanija().getNaziv().equals(nazivZupanije)).findFirst();
		if (postaja.isPresent())
			return postaja.get().getMjesto().getZupanija();

		Zupanija zupanija = new Zupanija(nazivZupanije, drzava);
		drzava.getZupanije().add(zupanija);

		return zupanija;
	}

	public static Mjesto kreirajMjesto(Scanner scanner) {

		Zupanija zupanija = kreirajZupaniju(scanner);
		System.out.println("Unesite naziv mjesta:");
		String nazivMjesta = scanner.nextLine();

		Optional<MjernaPostaja> postaja = mjernePostaje.stream()
				.filter(p -> p.getMjesto().getNaziv().equals(nazivMjesta)).findFirst();
		if (postaja.isPresent())
			return postaja.get().getMjesto();

		VrstaMjesta vrstaMjesta = kreirajVrstuMjesta(scanner);
		Mjesto mjesto = new Mjesto(nazivMjesta, zupanija, vrstaMjesta);
		zupanija.getMjesta().add(mjesto);

		return mjesto;
	}

	private static VrstaMjesta kreirajVrstuMjesta(Scanner scanner) {
		VrstaMjesta vrstaMjesta;
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
				logger.error("Neispravan unos vrste mjesta!", ex);
			}
		}

		if (redniBrojSenzora >= 1 && redniBrojSenzora < VrstaMjesta.values().length) {
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

		RadSenzora radSenzora = kreirajRadSenzora(scanner);

		SenzorVjetra senzorVjetra = new SenzorVjetra(velicinaSenzoraVjetra, radSenzora);
		senzorVjetra.setVrijednost(vrijednost);

		return senzorVjetra;
	}

	private static SenzorVlage kreirajSenzorVlage(Scanner scanner) {
		System.out.println("Unesite vrijednost senzora vlage:");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		RadSenzora radSenzora = kreirajRadSenzora(scanner);

		SenzorVlage senzorVlage = new SenzorVlage(radSenzora);
		senzorVlage.setVrijednost(vrijednost);

		return senzorVlage;
	}

	private static SenzorTemperature kreirajSenzorTemperature(Scanner scanner) {
		System.out.println("Unesite elektroničku komponentu za senzor temperature:");
		String nazivKonponente = scanner.nextLine();

		System.out.println("Unesite vrijednost senzora temperature:");
		BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

		RadSenzora radSenzora = kreirajRadSenzora(scanner);

		SenzorTemperature senzorTemperature = new SenzorTemperature(nazivKonponente, radSenzora);
		senzorTemperature.setVrijednost(vrijednost);

		return senzorTemperature;
	}

	private static RadSenzora kreirajRadSenzora(Scanner scanner) {
		RadSenzora radSenzora;
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

		if (redniBrojSenzora >= 1 && redniBrojSenzora < RadSenzora.values().length) {
			radSenzora = RadSenzora.values()[redniBrojSenzora - 1];
		} else {
			radSenzora = RadSenzora.OSTALO;
		}
		return radSenzora;
	}

	public static MjernaPostaja kreirajKlasicnuMjernuPostaju(Scanner scanner) {

		System.out.println("Unesite naziv mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Optional<MjernaPostaja> postaja = mjernePostaje.stream().filter(p -> p.getNaziv().equals(nazivMjernePostaje))
				.findFirst();
		if (postaja.isPresent())
			return postaja.get();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		List<Senzor> senzori = kreirajSenzore(scanner);

		MjernaPostaja mjernaPostaja = new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
		mjesto.getMjernePostaje().add(mjernaPostaja);

		return mjernaPostaja;
	}

	public static MjernaPostaja kreirajRadioSondaznuMjernuPostaju(Scanner scanner) {

		System.out.println("Unesite naziv radio sondažne mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();

		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		List<Senzor> senzori = kreirajSenzore(scanner);

		RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(nazivMjernePostaje,
				mjesto, geografskaTocka, senzori);
		mjesto.getMjernePostaje().add(radioSondaznaMjernaPostaja);

		return radioSondaznaMjernaPostaja;
	}

}
