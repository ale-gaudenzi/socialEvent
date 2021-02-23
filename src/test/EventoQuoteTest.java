package test;

import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.Test;

import social.EventoQuote;
import social.Utente;
import utility.JsonUtil;

public class EventoQuoteTest {
	String fileEvento = "testJson\\eventoQuote.json";
	String fileUtente = "testJson\\utenteQuote.json";

	@Test
	public void iscriviUtenteTest() throws Exception {
		EventoQuote e = (EventoQuote) JsonUtil.loadJSON(fileEvento);
		HashMap<String, Double> quoteUtente = new HashMap<String, Double>();
		quoteUtente.put("quota1", 11.0);
		quoteUtente.put("quota2", 12.0);
		quoteUtente.put("quota3", 13.0);
		
		Utente daIscrivere = (Utente) JsonUtil.loadJSON(fileUtente);
		
		e.iscriviUtente(daIscrivere, quoteUtente);
		
		assertTrue(daIscrivere.getProfilo().getPartecipato().contains(e.getTipoEvento()));
		assertTrue(daIscrivere.getSpazioP().getEventiP().contains(e.getCode()));
		assertTrue(e.isPartecipante(daIscrivere));
		assertEquals(e.getQuoteUtenti().get(daIscrivere.getNome()), (Double) 36.0);
	}

}
