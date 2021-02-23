package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import social.Evento;
import social.RaccoltaUtenti;
import social.Utente;
import utility.JsonUtil;
import utility.ToStringUtil;

public class RaccoltaUtentiTest {

	String fileEventoPartita = "testJson\\eventoPresente.json";
	String fileEventoMoto = "testJson\\eventoAssente.json";
	String fileRaccoltaU = "testJson\\raccoltaUtenti.json";
	String fileUtentePresente = "testJson\\utentePresente.json";
	String fileUtenteAssente = "testJson\\utenteAssente.json";

	@Test
	public void invitaTest() throws IOException {
		RaccoltaUtenti u = new RaccoltaUtenti(fileRaccoltaU);
		Evento eventoPartita = (Evento) JsonUtil.loadJSON(fileEventoPartita);
		String notifica1 = ToStringUtil.notificaInvito(eventoPartita,  u.getRaccoltaU().get(0));
		String notifica2 = ToStringUtil.notificaInvito(eventoPartita,  u.getRaccoltaU().get(1));
		String notifica3 = ToStringUtil.notificaInvito(eventoPartita,  u.getRaccoltaU().get(2));

		u.invita(false, eventoPartita);

		ArrayList<String> notifiche1 = u.getRaccoltaU().get(0).getSpazioP().getNotifiche();
		ArrayList<String> notifiche2 = u.getRaccoltaU().get(1).getSpazioP().getNotifiche();
		ArrayList<String> notifiche3 = u.getRaccoltaU().get(2).getSpazioP().getNotifiche();

		assertEquals(notifica1, notifiche1.get(notifiche1.size() - 1));
		assertEquals(notifica2, notifiche1.get(notifiche2.size() - 1));
		assertNotEquals(notifica3, notifiche1.get(notifiche3.size() - 1));
	}
	
	@Test
	public void nomeEsistenteTest() throws IOException {
		RaccoltaUtenti u = new RaccoltaUtenti(fileRaccoltaU);
		assertTrue(u.nomeEsistente("ale"));
		assertFalse(u.nomeEsistente("pippo"));	
	}
	
	@Test
	public void accessoTest() throws IOException {
		RaccoltaUtenti u = new RaccoltaUtenti(fileRaccoltaU);
		assertTrue(u.accessoConsentito("ale", "gau"));
		assertFalse(u.accessoConsentito("ale", "bau"));	
		assertFalse(u.accessoConsentito("ele", "gau"));	
	}
	
	@Test
	public void utenteByNameTest() throws IOException {
		RaccoltaUtenti u = new RaccoltaUtenti(fileRaccoltaU);
		Utente presente = (Utente) JsonUtil.loadJSON(fileUtentePresente);
		Utente trovato = u.getUtenteByName("ale");
		Utente nonTrovato = u.getUtenteByName("xxx");
		assertEquals(presente, trovato);	
		assertEquals(null, nonTrovato);
	}
	
	
	@Test
	public void indiceUtenteTest() throws IOException {
		RaccoltaUtenti u = new RaccoltaUtenti(fileRaccoltaU);
		Utente presente = (Utente) JsonUtil.loadJSON(fileUtentePresente);

		Integer indiceTrovato = u.indiceUtente(presente);
		assertEquals(indiceTrovato, (Integer) 0);	
	}
	
	
	
	
	

}
