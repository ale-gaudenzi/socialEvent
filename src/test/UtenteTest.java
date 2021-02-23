package test;

import static org.junit.Assert.*;
import org.junit.Test;
import social.Profilo;
import social.Utente;

public class UtenteTest {
	@Test
	public void testEquals() {
		Profilo profilo = new Profilo("AAA");
		Utente utenteA = new Utente("Pippo", "123", profilo);
		Utente utenteB = new Utente("Pippo", "123", profilo);

		assertTrue(utenteA.equals(utenteB));
	}
	
	@Test
	public void testNotEquals() {
		Profilo profiloA = new Profilo("AAA");
		Profilo profiloB = new Profilo("BBB");
		Utente utenteA = new Utente("Pippo", "123", profiloA);
		Utente utenteB = new Utente("Pluto", "456", profiloB);
		
		assertFalse(utenteA.equals(utenteB));
	}
}