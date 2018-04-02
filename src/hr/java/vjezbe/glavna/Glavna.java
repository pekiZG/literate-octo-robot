package hr.java.vjezbe.glavna;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.GeografskaTocka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Zupanija;

public class Glavna {

	private static final int BROJ_MJERNIH_POSTAJA = 3;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		MjernaPostaja[] mjernePostaje = new MjernaPostaja[3];

		// Create
		for (int i = 0; i < BROJ_MJERNIH_POSTAJA; i++) {
			System.out.println("Unesite " + (i + 1) + ". mjernu postaju:");
			mjernePostaje[i] = kreirajMjernuPostaju(scanner);
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
		}

		scanner.close();

	}

	public static Drzava kreirajDrzavu(Scanner scanner) {
		
		System.out.println("Unesite naziv države:");
		String nazivDrzave = scanner.nextLine();

		System.out.println("Unesite površinu države:");
		BigDecimal povrsinaDrzave = scanner.nextBigDecimal();
		scanner.nextLine();
		
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
		BigDecimal x = scanner.nextBigDecimal();
		scanner.nextLine();

		System.out.println("Unesite Geo koordinatu Y:");
		BigDecimal y = scanner.nextBigDecimal();
		scanner.nextLine();
		
		return new GeografskaTocka(x, y);
	}
	
	public static MjernaPostaja kreirajMjernuPostaju(Scanner scanner) {
		
		System.out.println("Unesite naziv mjerne postaje:");
		String nazivMjernePostaje = scanner.nextLine();
		
		Mjesto mjesto = kreirajMjesto(scanner);
		GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
		
		return new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka);
	}
	
}
