package utility;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class InputDati {
	private static Scanner lettore = creaScanner();

	private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	private final static String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_STRINGA_VUOTA = "Attenzione: non hai inserito alcun carattere";
	private final static String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
	private final static String ERRORE_DATA_PRE = "Attenzione: e' richiesta una data antecedente a ";
	private final static String MESSAGGIO_AMMISSIBILI = "Attenzione: i caratteri ammissibili sono: ";
	private final static char RISPOSTA_SI = 'S';
	private final static char RISPOSTA_NO = 'N';
	private final static char RISPOSTA_MASCHIO = 'M';
	private final static char RISPOSTA_FEMMINA = 'F';	

	private static Scanner creaScanner() {
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}
	
	public static String leggiStringa(String messaggio) {
		System.out.print(messaggio);
		return lettore.next();
	}

	public static String leggiStringaNonVuota(String messaggio) {
		boolean finito = false;
		String lettura = null;
		do {
			lettura = leggiStringa(messaggio);
			lettura = lettura.trim();
			if (lettura.length() > 0)
				finito = true;
			else
				System.out.println(ERRORE_STRINGA_VUOTA);
		} while (!finito);

		return lettura;
	}

	public static char leggiChar(String messaggio) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			System.out.print(messaggio);
			String lettura = lettore.next();
			if (lettura.length() > 0) {
				valoreLetto = lettura.charAt(0);
				finito = true;
			} else {
				System.out.println(ERRORE_STRINGA_VUOTA);
			}
		} while (!finito);
		return valoreLetto;
	}

	public static char leggiUpperChar(String messaggio, String ammissibili) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			valoreLetto = leggiChar(messaggio);
			valoreLetto = Character.toUpperCase(valoreLetto);
			if (ammissibili.indexOf(valoreLetto) != -1)
				finito = true;
			else
				System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
		} while (!finito);
		return valoreLetto;
	}

	public static int leggiIntero(String messaggio) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextInt();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				@SuppressWarnings("unused")
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static int leggiInteroPositivo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 1);
	}

	public static int leggiInteroNonNegativo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 0);
	}

	public static int leggiInteroConMinimo(String messaggio, int minimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);
		return valoreLetto;
	}
	
	public static int leggiInteroConMassimo(String messaggio, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto <= massimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	public static double leggiDouble(String messaggio) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextDouble();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				@SuppressWarnings("unused")
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static double leggiDoubleConMinimo(String messaggio, double minimo) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			valoreLetto = leggiDouble(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static boolean yesOrNo(String messaggio) {
		String mioMessaggio = messaggio + "(" + RISPOSTA_SI + " o " + RISPOSTA_NO + ") ";
		char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

		if (valoreLetto == RISPOSTA_SI)
			return true;
		else
			return false;
	}

	public static boolean maleOrFemale(String messaggio) {
		String mioMessaggio = messaggio + "(" + RISPOSTA_MASCHIO + "/" + RISPOSTA_FEMMINA + ")";
		char valoreLetto = leggiUpperChar(mioMessaggio,
				String.valueOf(RISPOSTA_MASCHIO) + String.valueOf(RISPOSTA_FEMMINA));

		if (valoreLetto == RISPOSTA_MASCHIO)
			return true;
		else
			return false;
	}
	
	public static LocalDate leggiData(String messaggio) {
		System.out.println(messaggio);
		int giorno = leggiIntero("Giorno: ", 1, 31);
		int mese = leggiIntero("Mese: ", 1, 12);
		int anno = leggiInteroConMinimo("Anno: ", LocalDate.now().getYear());
		return LocalDate.of(anno, mese, giorno);
	}
	
	public static LocalDate leggiDataPre(String messaggio, LocalDate limite) {
		System.out.println(messaggio);
		boolean finito = false;
		LocalDate inserita;
		do {
			int giorno = leggiIntero("Giorno: ", 1, 31);
			int mese = leggiIntero("Mese: ", 1, 12);
			int anno = leggiInteroConMinimo("Anno: ", LocalDate.now().getYear());
			inserita = LocalDate.of(anno, mese, giorno);
			if(limite.isAfter(inserita))
				finito = true;
			else 
				System.out.println(ERRORE_DATA_PRE + StringFormatter.toStringDate(limite));
		} while (!finito);
		
		return inserita;
	}
	
	public static LocalTime leggiTime(String messaggio) {
		System.out.println(messaggio);
		int ora = leggiIntero("Ora: ", 0, 23);
		int minuto = leggiIntero("Minuto: ", 0, 59);
		return LocalTime.of(ora, minuto); 
	}
	
	public static Duration leggiDurata(String messaggio) {
		System.out.println(messaggio);
		int giorni = leggiIntero("Giorni: ");
		int ore = leggiIntero("Ore: ", 0, 23);
		int minuti = leggiIntero("Minuti: ", 0, 59);
		long secondi = minuti * 60 + ore * 3600 + giorni * 86400;
		return Duration.ofSeconds(secondi);
	}
}
