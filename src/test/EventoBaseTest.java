package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import social.EventoBase;
import social.Utente;
import utility.JsonUtil;
import utility.ToStringUtil;

public class EventoBaseTest {
	String fileEvento = "testJson\\evento.json";
	String fileUtenteAssente = "testJson\\utenteAssente.json";
	String fileUtentePresente = "testJson\\utentePresente.json";

	@Test
	public void isPartecipanteTest() throws IOException {
		EventoBase e = (EventoBase) JsonUtil.loadJSON(fileEvento);
		Utente assente = (Utente) JsonUtil.loadJSON(fileUtenteAssente);
		Utente presente = (Utente) JsonUtil.loadJSON(fileUtentePresente);

		assertTrue(e.isPartecipante(presente));
		assertFalse(e.isPartecipante(assente));
	}

	@Test
	public void iscriviUtente() throws Exception {
		EventoBase e = (EventoBase) JsonUtil.loadJSON(fileEvento);
		Utente assente = (Utente) JsonUtil.loadJSON(fileUtenteAssente);
		e.iscriviUtente(assente);
		
		assertTrue(assente.getProfilo().getPartecipato().contains(e.getTipoEvento()));
		assertTrue(assente.getSpazioP().getEventiP().contains(e.getCode()));
		assertTrue(e.isPartecipante(assente));
	}
	
	@Test
	public void rimuoviPartecipanteTest() throws IOException {
		EventoBase e = (EventoBase) JsonUtil.loadJSON(fileEvento);
		Utente presente = (Utente) JsonUtil.loadJSON(fileUtentePresente);
		e.removePartecipante(presente);
		
		
		assertFalse(e.isPartecipante(presente));
	}
	
	@Test
	public void notificaRitiroTest() throws IOException {
		EventoBase e = (EventoBase) JsonUtil.loadJSON(fileEvento);
		e.notificaRitiro();

		for(int i = 0; i < e.getPartecipanti().size(); i++) {
			Utente daNotificare = e.getPartecipanti().get(i);
			ArrayList<String> notifiche = daNotificare.getSpazioP().getNotifiche();
			assertEquals(notifiche.get(notifiche.size()-1), ToStringUtil.notificaRitirato(e, daNotificare));
		}	
	}
	
	
	
	
	
	
}
