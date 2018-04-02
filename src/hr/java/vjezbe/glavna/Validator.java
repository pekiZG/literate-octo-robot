package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;

public class Validator {

	public static Integer unesiInteger(Scanner scanner) {
		Integer broj = null;
		while (broj == null) {
			try {
				broj = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Nije unešen broj; pokušajte opet :)");
				scanner.nextLine();
			}
		}
		return broj;
	}

	public static BigDecimal unesiBigDecimal(Scanner scanner) {
		BigDecimal broj = null;
		while (broj == null) {
			try {
				broj = scanner.nextBigDecimal();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Nije unešen broj; pokušajte opet :)");
				scanner.nextLine();
			}
		}
		return broj;
	}

}
