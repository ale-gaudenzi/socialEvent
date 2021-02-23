package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.lang3.time.DurationFormatUtils;

import social.Evento;
import social.EventoBase;
import social.EventoQuote;
import social.RaccoltaEventi;
import social.SpazioPersonale;
import social.Utente;

public class ToStringUtil {
	static final String DURATA_FORMAT = "dd 'giorni' HH 'ore' mm 'minuti'";
	static final String MSG_APERTO = "Evento APERTO:\n";
	static final String MSG_FALLITO = "Evento FALLITO, non verrà attuato:\n";
	static final String MSG_CONCLUSO = "Evento CONCLUSO:\n";
	static final String MSG_CHIUSO = "Evento CHIUSO, verrà attuato:\n";
	static final String MSG_RITIRATO = "Evento RITIRATO dal creatore. \n";
	static final String MSG_INVITO = "\nTi hanno invitato a questo evento, è tra i tuoi interessi:\n";
	private static final String NO_EVENTIC = "Non hai ancora creato eventi";
	private static final String NO_EVENTIP = "Non stai ancora parteciapando a eventi";
	private static final String NO_NOTIFICHE = "Non sono presenti notifiche";

	public static String toStringInfoEvento(Evento e) {
		String out = "";
		if (e.getNome() != null)
			out += out + "Nome: " + e.getNome() + "\n";
		out += "Tipo: " + RaccoltaEventi.TIPI_EVENTO[e.getTipoEvento()] + "\n";
		out += "Stato: " + RaccoltaEventi.STATI_EVENTO[e.getStatoEvento()] + "\n";
		out += "Ora: " + e.getOraInizio().toString() + "\n";
		out += "Data: " + e.getDataInizio().toString() + "\n";
		out += "Luogo: " + e.getLuogo() + "\n";
		out += "Scadenza iscrizione: " + e.getScadenzaIscrizione().toString() + "\n";
		if (e.getDurata() != null)
			out += "Durata: " + DurationFormatUtils.formatDuration(e.getDurata().toMillis(), DURATA_FORMAT) + "\n";
		if (e.getDataFine() != null)
			out += "Data conclusione: " + e.getDataFine().toString() + "\n";
		if (e.getOraFine() != null)
			out += "Ora conclusione: " + e.getOraFine().toString() + "\n";
		if ((Double) e.getQuota() != null)
			out += "Quota: " + e.getQuota() + "€\n";
		if (e.getCompresoQuota() != null)
			out += "Compreso nella quota: " + e.getCompresoQuota().toString() + "\n";
		if (e.getNote() != null)
			out += "Note: " + e.getNote().toString() + "\n";
		out += "Massimi partecipanti: " + e.getMaxPartecipanti() + "\n";
		out += "Creatore: " + e.getCreatore().getNome() + "\n";
		out += "Parteciperanno in: " + e.getPartecipanti().size();

		if (e instanceof EventoQuote && e.getClass() != EventoBase.class)
			out += "\nQuote aggiuntive: " + ((EventoQuote) e).getQuoteAggiuntive();

		return out;
	}

	public static String notificaInvito(Evento e, Utente u) {
		StringBuffer out = new StringBuffer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy");
		String dataFormatted = formatter.format(LocalDate.now());
		out.append(dataFormatted);
		out.append(MSG_INVITO);
		out.append(toStringInfoEventoRidotto(e, u));
		return out.toString();
	}

	public static String notificaAperto(Evento e, Utente u) {
		return MSG_APERTO + toStringInfoEventoRidotto(e, u);
	}

	public static String notificaChiuso(Evento e, Utente u) {
		return MSG_CHIUSO + toStringInfoEventoRidotto(e, u);
	}

	public static String notificaConcluso(Evento e, Utente u) {
		return MSG_CONCLUSO + toStringInfoEventoRidotto(e, u);
	}

	public static String notificaFallito(Evento e, Utente u) {
		return MSG_FALLITO + toStringInfoEventoRidotto(e, u);
	}

	public static String notificaRitirato(Evento e, Utente u) {
		return MSG_RITIRATO + toStringInfoEventoRidotto(e, u);
	}
	
	public static String toStringInfoEventoRidotto(Evento e, Utente u) {
		String out = "";
		if (e.getNome() != null)
			out += out + "Nome: " + e.getNome() + "\n";
		out += "Tipo: " + RaccoltaEventi.TIPI_EVENTO[e.getTipoEvento()] + "\n";
		out += e.getDataInizio().toString() + " " + e.getOraInizio().toString() + " " + e.getLuogo() + "\n";
		out += "Quota di partecipazione: " + e.getQuota() + "€";

		if (e instanceof EventoQuote && e.getClass() != EventoBase.class) {
			Double quotaAggiuntiva = ((EventoQuote) e).getQuoteUtenti().get(u.getNome());
			out += "\nQuota aggiuntiva: " + quotaAggiuntiva;
		}

		return out;
	}

	public static String toStringRaccoltaEventi(RaccoltaEventi raccolta) {
		StringBuffer out = new StringBuffer();
		boolean vuoto = true;
		ArrayList<Evento> e = raccolta.getRaccoltaE();

		for (int i = 0; i < RaccoltaEventi.TIPI_EVENTO.length; i++) {
			out.append(StringFormatter.incorniciaSopra("Eventi di tipo \"" + RaccoltaEventi.TIPI_EVENTO[i] + "\":"));
			for (int j = 0; j < e.size(); j++) {
				if (e.get(j).getTipoEvento() == i && e.get(j).getStatoEvento() == 0) {
					vuoto = false;
					out.append(StringFormatter
							.incorniciaSopra("Evento #" + (j + 1) + "\n" + toStringInfoEvento(e.get(j))));
				}
			}
			if (vuoto == true)
				out.append(StringFormatter.incorniciaSopra(
						"Purtroppo non ci sono eventi di tipo \"" + RaccoltaEventi.TIPI_EVENTO[i] + "\"."));
		}

		return out.toString();
	}

	public static String toStringEventiPartecipa(RaccoltaEventi raccolta, Utente utente) {
		String out = "";
		SpazioPersonale spazio = utente.getSpazioP();
		int numEventiP = spazio.getEventiP().size();
		if (numEventiP == 0) {
			out += (NO_EVENTIP);
		} else {
			for (int i = 0; i < numEventiP; i++) {
				int codiceEvento = spazio.getEventiP().get(i);
				Evento eventoP = raccolta.getEventoFromCode(codiceEvento);
				out += StringFormatter.incorniciaSopra(
						"#" + (i + 1) + "\n" + ToStringUtil.toStringInfoEventoRidotto(eventoP, utente) + "\n");
			}
		}
		return out;
	}

	public static String toStringEventiCreati(RaccoltaEventi raccolta, Utente utente) {
		String out = "";
		SpazioPersonale spazio = utente.getSpazioP();
		if (spazio.getEventiC().size() == 0) {
			out += (NO_EVENTIC);
		} else {
			for (int i = 0; i < spazio.getEventiC().size(); i++) {
				int codiceEvento = spazio.getEventiC().get(i);
				Evento eventoC = raccolta.getEventoFromCode(codiceEvento);
				out += StringFormatter.incorniciaSopra(
						"#" + (i + 1) + "\n" + ToStringUtil.toStringInfoEventoRidotto(eventoC, utente) + "\n");
			}
		}
		return out;
	}
	
	public static String toStringNotifiche(Utente utente) {
		String out = "";
		SpazioPersonale spazio = utente.getSpazioP();
		int numNotifiche = spazio.getNotifiche().size();
		if (numNotifiche == 0) {
			out += (NO_NOTIFICHE);
		} else {
			for (int i = 0; i < numNotifiche; i++) {
				String notifica = spazio.getNotifiche().get(i);
				out += StringFormatter.incorniciaSopra("#" + (i + 1) + "\n" + notifica + "\n");
			}
		}
		return out;
	}

}
