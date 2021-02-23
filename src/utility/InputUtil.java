package utility;

import java.time.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InputUtil {
	private static Scanner scanner;

	public static int leggiInt(JTextField daLeggere) throws InputMismatchException {
		String daConvertire = daLeggere.getText();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(daConvertire);
		int valoreLetto;
		valoreLetto = scanner.nextInt();
		return valoreLetto;
	}

	public static double leggiDouble(JTextField daLeggere) throws InputMismatchException {
		String daConvertire = daLeggere.getText();
		scanner = new Scanner(daConvertire);

		double valoreLetto = 0.0;
		if (scanner.hasNextDouble())
			valoreLetto = scanner.nextDouble();
		else if (scanner.hasNextInt()) {
			valoreLetto = scanner.nextInt() * 1.0;
		}
		return valoreLetto;

	}

	public static int leggiIntBounds(JTextField daLeggere, int min, int max) throws InputMismatchException {
		int letto = leggiInt(daLeggere);
		if (letto < min || letto > max)
			throw new InputMismatchException();
		else
			return letto;

	}

	public static int leggiIntMax(JTextField daLeggere, int max) throws InputMismatchException {
		int letto = leggiInt(daLeggere);
		if (letto > max)
			throw new InputMismatchException();
		else
			return letto;
	}

	public static int leggiIntMin(JTextField daLeggere, int min) throws InputMismatchException {
		int letto = leggiInt(daLeggere);
		if (letto < min)
			throw new InputMismatchException();
		else
			return letto;
	}

	public static String leggiStringNonVuota(JTextField daLeggere) throws InputMismatchException {
		String letta = leggiString(daLeggere);
		if (letta.length() == 0)
			throw new InputMismatchException();
		else
			return letta;
	}

	public static String leggiString(JTextField daLeggere) {
		return daLeggere.getText();
	}

	public static int leggiInteroDaCombo(JComboBox<Integer> combo) {
		return (int) combo.getSelectedItem();
	}

	public static int leggiIndiceCombo(JComboBox<String> combo) {
		return combo.getSelectedIndex();
	}

	public static LocalDate leggiDataGMA(ArrayList<JTextField> textfields) throws InputMismatchException {
		LocalDate date;
		int giorno = leggiIntBounds(textfields.get(0), 1, 31);
		int mese = leggiIntBounds(textfields.get(1), 1, 12);
		int anno = leggiIntMin(textfields.get(2), LocalDate.now().getYear());
		date = LocalDate.of(anno, mese, giorno);
		return date;
	}

	public static LocalTime leggiTimeMO(ArrayList<JTextField> textfields) throws InputMismatchException {
		LocalTime time;
		int minuto = leggiIntBounds(textfields.get(1), 0, 59);
		int ora = leggiIntBounds(textfields.get(0), 0, 23);
		time = LocalTime.of(ora, minuto);
		return time;
	}

	public static Duration leggiDurataGOM(ArrayList<JTextField> textfields) throws InputMismatchException {
		Duration durata;
		int giorni = leggiIntMin(textfields.get(0), 0);
		int ore = leggiIntBounds(textfields.get(1), 0, 23);
		int minuti = leggiIntBounds(textfields.get(2), 0, 59);
		long secondiTot = giorni * 86400 + ore * 3600 + minuti * 60;
		durata = Duration.ofSeconds(secondiTot);
		return durata;
	}

	public static HashMap<String, Double> leggiMapStringDouble(DefaultTableModel dtm) throws NumberFormatException {
		HashMap<String, Double> map = new HashMap<String, Double>();
		int rowCount = dtm.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			String name = (String) dtm.getValueAt(i, 0);
			Double val = Double.valueOf((String) dtm.getValueAt(i, 1));
			System.out.println(name + " val: " + val);
			map.put(name, val);
		}
		return map;
	}

	public static HashMap<String, Double> leggiMapStringDoubleCheck(DefaultTableModel dtm) {
		HashMap<String, Double> map = new HashMap<String, Double>();
		int rowCount = dtm.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			if ((boolean) dtm.getValueAt(i, 2)) {
				String name = (String) dtm.getValueAt(i, 0);
				Double val = (Double) dtm.getValueAt(i, 1);
				map.put(name, val);
			}
		}
		return map;
	}

}
