package utility;

public class Menu {
	final private static String VOCE_USCITA = "Esci";
	final private static String CORNICE = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

	private String titolo;
	private String[] voci;
	private String voceUscita;

	public Menu(String titolo, String[] voci) {
		this.titolo = titolo;
		this.voci = voci;
		this.voceUscita = VOCE_USCITA;
	}

	public int scegli() {
		stampaMenu();
		return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
	}

	public void stampaMenu() {
		System.out.println(CORNICE);
		System.out.println(titolo);
		System.out.println(CORNICE);
		for (int i = 0; i < voci.length; i++) {
			System.out.println((i + 1) + "\t" + voci[i]);
		}
		System.out.println("0\t" + voceUscita);
		System.out.println(CORNICE);
	}

}
