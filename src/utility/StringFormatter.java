package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringFormatter {

	private final static String SPAZIO = " ";
	private final static String CORNICE = "---------------------------------------------------------------------------------------------------------------------------------------";
	private final static String ACAPO = "\n";

	public static String incornicia(String s) {
		StringBuffer res = new StringBuffer();
		res.append(CORNICE + ACAPO);
		res.append(s + ACAPO);
		res.append(CORNICE + ACAPO);
		return res.toString();
	}

	public static String incorniciaSopra(String s) {
		StringBuffer res = new StringBuffer();
		res.append(CORNICE + ACAPO);
		res.append(s + ACAPO);
		return res.toString();
	}

	public static String incolonna(String s, int larghezza) {
		StringBuffer res = new StringBuffer(larghezza);
		int numCharDaStampare = Math.min(larghezza, s.length());
		res.append(s.substring(0, numCharDaStampare));
		for (int i = s.length() + 1; i <= larghezza; i++)
			res.append(SPAZIO);
		return res.toString();
	}

	public static String centrata(String s, int larghezza) {
		StringBuffer res = new StringBuffer(larghezza);

		if (larghezza <= s.length())
			res.append(s.substring(larghezza));
		else {
			int spaziPrima = (larghezza - s.length()) / 2;
			int spaziDopo = larghezza - spaziPrima - s.length();
			for (int i = 1; i <= spaziPrima; i++)
				res.append(SPAZIO);

			res.append(s);

			for (int i = 1; i <= spaziDopo; i++)
				res.append(SPAZIO);
		}

		return res.toString();
	}

	public static String ripetiChar(char elemento, int larghezza) {
		StringBuffer result = new StringBuffer(larghezza);

		for (int i = 0; i < larghezza; i++) {
			result.append(elemento);
		}

		return result.toString();
	}

	public static String rigaIsolata(String daIsolare) {
		StringBuffer result = new StringBuffer();
		result.append(ACAPO);
		result.append(daIsolare);
		result.append(ACAPO);
		return result.toString();
	}

	public static String toStringDate(LocalDate data, LocalTime time) {
		LocalDateTime dataOra = LocalDateTime.of(data, time);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH:mm");
		String formatted = formatter.format(dataOra);
		return formatted;
	}
	
	public static String toStringDate(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy");
		String formatted = formatter.format(data);
		return formatted;
	}
	
	public static String creaLogStato(String stato1, String stato2) {
		StringBuffer out = new StringBuffer();
		LocalDateTime dataPassaggio = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy");
		String dataFormatted = formatter.format(dataPassaggio);
		out.append("Passaggio da " + stato1 + " a " + stato2 + " in data: " + dataFormatted);
		return out.toString();
	}
	
	public static String creaLogCreazione() {
		StringBuffer out = new StringBuffer();
		LocalDateTime dataPassaggio = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy");
		String dataFormatted = formatter.format(dataPassaggio);
		out.append("Creato in data: " + dataFormatted);
		return out.toString();
	}
}
