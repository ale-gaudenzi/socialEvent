package controller;

import java.time.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import javax.swing.JTextField;
import gui.EventoView;
import myGuiLib.DatePanelGMA;
import myGuiLib.DurationPanelGOM;
import myGuiLib.TimePanelOM;
import myGuiLib.TableQuoteAggiuntive;
import social.Evento;
import social.EventoBase;
import social.EventoQuote;
import social.RaccoltaEventi;
import social.RaccoltaUtenti;
import social.Utente;
import utility.InputUtil;

/**
 * Classe controller per la vista della creazione di un nuovo evento
 * 
 * @author Alessandro Gaudenzi
 */
public class EventoController implements Controller {
	private EventoView view;
	private RaccoltaUtenti raccoltaU;
	private RaccoltaEventi raccoltaE;
	private Utente utenteAcceduto;
	private Random random;

	Evento nuovoEvento;
	int maxPartecipanti;
	LocalDate scadenzaIscrizione;
	LocalDate dataInizio;
	LocalTime oraInizio;
	String luogo;
	double quota;
	int tipoEvento;
	boolean isQuote;

	String nome;
	LocalDate dataFine;
	LocalDate termineRitiro;
	LocalTime oraFine;
	Duration durata;
	String compresoQuota;
	String note;
	int tolleranza;
	boolean invito;

	/**
	 * Costruttore per la classe EventoController
	 * 
	 * @param view
	 * @param raccoltaE
	 * @param raccoltaU
	 */
	public EventoController(EventoView view, RaccoltaUtenti raccoltaU, RaccoltaEventi raccoltaE,
			Utente utenteAcceduto) {
		this.view = view;
		this.raccoltaU = raccoltaU;
		this.raccoltaE = raccoltaE;
		this.utenteAcceduto = utenteAcceduto;
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento
	 * dell'ui
	 */
	public void initController() {
		view.getConfirmButton().addActionListener(e -> insertEvento());
		view.getEscButton().addActionListener(e -> toMainMenu());
		view.getAggiungiButton().addActionListener(e -> aggiungiRigaQuota());
		view.getRimuoviButton().addActionListener(e -> rimuoviRigaQuota());
	}

	public void aggiungiRigaQuota() {
		((TableQuoteAggiuntive) view.getInsertQuotePanel()).addRow();
	}

	public void rimuoviRigaQuota() {
		((TableQuoteAggiuntive) view.getInsertQuotePanel()).removeRow();
	}

	public void toMainMenu() {
		MasterController.switchToMainMenuView();
	}

	public void insertEvento() {
		nuovoEvento = null;
		if (((TableQuoteAggiuntive) view.getInsertQuotePanel()).getDtm().getRowCount() != 0) {
			try {
				leggiQuoteAggiuntive();
			} catch (NumberFormatException e) {
				view.inputErrorMessage();
				e.printStackTrace();
				return;
			}
			isQuote = true;
		} else
			isQuote = false;

		try {
			obligatoryInput();
			facoltativeInput();
		} catch (InputMismatchException e) {
			view.inputErrorMessage();
			e.printStackTrace();
			return;

		} catch (NoSuchElementException b) {
			view.obligatoryInputErrorMessage();
			b.printStackTrace();
			return;
		}
		raccoltaU.invita(view.getCheckInvito().isSelected(), nuovoEvento);
		raccoltaE.addEvento(nuovoEvento);
		view.successMessage();
		MasterController.save();
		toMainMenu();
	}

	// ausiliarie
	public void obligatoryInput() throws InputMismatchException, NoSuchElementException {
		maxPartecipanti = InputUtil.leggiInt(view.getTextMaxPartecipanti());
		DatePanelGMA scadenzaPanel = (DatePanelGMA) view.getDataScadenzaPanel();
		ArrayList<JTextField> scadenzaArray = scadenzaPanel.getArrayText();
		scadenzaIscrizione = InputUtil.leggiDataGMA(scadenzaArray);
		DatePanelGMA dataInizioPanel = (DatePanelGMA) view.getDataInizioPanel();
		ArrayList<JTextField> dataInizioArray = dataInizioPanel.getArrayText();
		dataInizio = InputUtil.leggiDataGMA(dataInizioArray);
		TimePanelOM oraInizioPanel = (TimePanelOM) view.getOrarioInizioPanel();
		ArrayList<JTextField> oraInizioArray = oraInizioPanel.getArrayText();
		oraInizio = InputUtil.leggiTimeMO(oraInizioArray);
		luogo = InputUtil.leggiString(view.getTextFieldLuogo());
		quota = InputUtil.leggiDouble(view.getTextFieldQuota());
		tipoEvento = InputUtil.leggiIndiceCombo(view.getComboTipo());

		random = new Random();
		int codice = random.nextInt(1001);

		if (isQuote)
			nuovoEvento = new EventoQuote(leggiQuoteAggiuntive(), maxPartecipanti, scadenzaIscrizione, dataInizio,
					luogo, oraInizio, quota, utenteAcceduto, tipoEvento, codice);
		else
			nuovoEvento = new EventoBase(maxPartecipanti, scadenzaIscrizione, dataInizio, luogo, oraInizio, quota,
					utenteAcceduto, tipoEvento, codice);

	}

	public void facoltativeInput() throws InputMismatchException {
		if (tryNome() != null)
			nuovoEvento.setNome(tryNome());
		if (tryDataFine() != null)
			nuovoEvento.setDataFine(tryDataFine());
		if (tryOraFine() != null)
			nuovoEvento.setOraFine(tryOraFine());
		if (tryTolleranza() != 0)
			nuovoEvento.setTolleranza(tryTolleranza());
		if (tryTermineRitiro() != null)
			nuovoEvento.setTermineRitiro(tryTermineRitiro());
		if (tryDurata() != null)
			nuovoEvento.setDurata(tryDurata());
		if (tryNote() != null)
			nuovoEvento.setNote(tryNote());
		if (tryCompreso() != null)
			nuovoEvento.setCompresoQuota(tryCompreso());
	}

	public String tryNome() throws InputMismatchException {
		String nome = null;
		JTextField nomeT = view.getTextFieldNome();
		if (nomeT.getText().length() != 0)
			nome = InputUtil.leggiString(nomeT);
		return nome;
	}

	public LocalDate tryDataFine() throws InputMismatchException {
		LocalDate dataFine = null;
		if (!((DatePanelGMA) view.getDataScadenzaPanel()).isEmpty()) {
			DatePanelGMA dataFinePanel = (DatePanelGMA) view.getDataScadenzaPanel();
			ArrayList<JTextField> dataFineArray = dataFinePanel.getArrayText();
			dataFine = InputUtil.leggiDataGMA(dataFineArray);
		}
		return dataFine;
	}

	public LocalTime tryOraFine() throws InputMismatchException {
		LocalTime oraFine = null;
		if (!((TimePanelOM) view.getOrarioFinalePanel()).isEmpty()) {
			DatePanelGMA oraFinePanel = (DatePanelGMA) view.getOrarioFinalePanel();
			ArrayList<JTextField> oraFineArray = oraFinePanel.getArrayText();
			oraFine = InputUtil.leggiTimeMO(oraFineArray);
		}
		return oraFine;
	}

	public int tryTolleranza() throws InputMismatchException {
		int tolleranza = 0;
		JTextField tolleranzaText = view.getTextFieldTolleranza();
		if (tolleranzaText.getText().length() != 0)
			tolleranza = InputUtil.leggiInt(tolleranzaText);
		return tolleranza;
	}

	public LocalDate tryTermineRitiro() throws InputMismatchException {
		LocalDate termineRitiro = null;
		if (!((DatePanelGMA) view.getDataRitiroPanel()).isEmpty()) {
			DatePanelGMA termineRitiroPanel = (DatePanelGMA) view.getDataRitiroPanel();
			ArrayList<JTextField> termineRitiroArray = termineRitiroPanel.getArrayText();
			termineRitiro = InputUtil.leggiDataGMA(termineRitiroArray);
		}
		return termineRitiro;
	}

	public Duration tryDurata() throws InputMismatchException {
		Duration durata = null;
		if (!((DurationPanelGOM) view.getDurataPanel()).isEmpty()) {
			DurationPanelGOM durataPanel = (DurationPanelGOM) view.getDurataPanel();
			ArrayList<JTextField> durataArray = durataPanel.getArrayText();
			durata = InputUtil.leggiDurataGOM(durataArray);
		}
		return durata;
	}

	public String tryNote() throws InputMismatchException {
		String note = null;
		JTextField noteT = view.getTextNote();
		if (noteT.getText().length() != 0)
			note = InputUtil.leggiString(noteT);
		return note;
	}

	public String tryCompreso() throws InputMismatchException {
		String compreso = null;
		JTextField compresoT = view.getTextCompreso();
		if (compresoT.getText().length() != 0)
			compreso = InputUtil.leggiString(compresoT);
		return compreso;
	}

	public HashMap<String, Double> leggiQuoteAggiuntive() throws NumberFormatException {
		HashMap<String, Double> mapQuote = new HashMap<>();
		mapQuote = InputUtil.leggiMapStringDouble(((TableQuoteAggiuntive) view.getInsertQuotePanel()).getDtm());
		return mapQuote;
	}
}
