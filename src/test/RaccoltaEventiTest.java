package test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import org.junit.Test;
import social.Evento;
import social.RaccoltaEventi;
import utility.ClockUtil;
import utility.JsonUtil;

public class RaccoltaEventiTest {
	Clock fixedClock;
	String fileConclusi = "testJson\\conclusi.json";
	String fileFalliti = "testJson\\falliti.json";
	String fileChiusi = "testJson\\chiusi.json";
	String fileGeneric = "testJson\\generic.json";
	String fileEventoPresente = "testJson\\eventoPresente.json";
	String fileEventoAssente = "testJson\\eventoAssente.json";
	String fileVuoto = "testJson\\vuoto.json";

	@Test
	public void allEventiConclusiTest() {
		RaccoltaEventi e = new RaccoltaEventi(fileConclusi);
		LocalDate allConclusiDate = LocalDate.of(2020, 12, 30);
		ClockUtil.useFixedClockAt(allConclusiDate);

		e.eventiConclusi();
		boolean correct = true;
		int[] expected = { 3, 3, 3 };
		int[] result = statoEventi(e);

		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != result[i])
				correct = false;
		}

		assertTrue(correct);
	}

	@Test
	public void noEventiConclusiTest() {
		RaccoltaEventi e = new RaccoltaEventi(fileConclusi);
		LocalDate noConclusiDate = LocalDate.of(2020, 3, 30);
		ClockUtil.useFixedClockAt(noConclusiDate);

		e.eventiConclusi();
		boolean correct = true;
		int[] expected = { 0, 0, 0 };
		int[] result = statoEventi(e);

		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != result[i])
				correct = false;
		}

		assertTrue(correct);
	}

	@Test
	public void eventiConclusiTest() {
		RaccoltaEventi e = new RaccoltaEventi(fileConclusi);
		LocalDate conclusiDate = LocalDate.of(2020, 11, 30);
		ClockUtil.useFixedClockAt(conclusiDate);

		e.eventiConclusi();
		boolean correct = true;
		int[] expected = { 0, 3, 3 };
		int[] result = statoEventi(e);

		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != result[i])
				correct = false;
		}

		assertTrue(correct);
	}

	@Test
	public void eventiFallitiTest() {
		RaccoltaEventi e = new RaccoltaEventi(fileConclusi);
		LocalDate fallitiDate = LocalDate.of(2020, 11, 30);
		ClockUtil.useFixedClockAt(fallitiDate);

		e.eventiFalliti();
		boolean correct = true;
		int[] expected = { 0, 0, 2 };
		int[] result = statoEventi(e);

		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != result[i]) {
				correct = false;

			}
		}

		assertTrue(correct);
	}
	
	@Test
	public void eventiChiusiTest() {
		RaccoltaEventi e = new RaccoltaEventi(fileChiusi);
		LocalDate chiusiDate = LocalDate.of(2020, 10, 30);
		ClockUtil.useFixedClockAt(chiusiDate);
		e.eventiChiusi();
		boolean correct = true;
		int[] expected = { 0, 1, 1 };
		int[] result = statoEventi(e);

		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != result[i]) {
				correct = false;

			}
		}
		assertTrue(correct);
	}
	
	@Test
	public void getEventoFromCodeTest() {
		RaccoltaEventi e = new RaccoltaEventi(fileGeneric);
		Evento fromCode = e.getEventoFromCode(830);
		
		assertEquals(fromCode.getNome(), "ottocentotrenta");
	}
	
	@Test
	public void eventoPresenteTest() throws IOException {
		RaccoltaEventi e = new RaccoltaEventi(fileGeneric);
		Evento eventoPres = (Evento) JsonUtil.loadJSON(fileEventoPresente);
		Evento eventoAss = (Evento) JsonUtil.loadJSON(fileEventoAssente);
		
		boolean eventoP = e.eventoPresente(eventoPres);
		boolean eventoA = e.eventoPresente(eventoAss);
		
		assertFalse(eventoA);
		assertTrue(eventoP);
	}
	
	@Test
	public void addEventoTest() throws IOException {
		RaccoltaEventi e = new RaccoltaEventi(fileVuoto);
		Evento evento = (Evento) JsonUtil.loadJSON(fileEventoPresente);
		e.addEvento(evento);
		assertTrue(e.eventoPresente(evento));
	}
	
	@Test 
	public void ritiraEventoTest() throws IOException {
		RaccoltaEventi e = new RaccoltaEventi(fileGeneric);
		e.ritiraEvento(193);
		Evento daRitirare = (Evento) JsonUtil.loadJSON(fileEventoPresente);
		assertFalse(e.eventoPresente(daRitirare));
	}
	
	@Test 
	public void vuotoTest() throws IOException {
		RaccoltaEventi e = new RaccoltaEventi(fileVuoto);
		assertTrue(e.vuoto());
	}
	
	public int[] statoEventi(RaccoltaEventi e) {
		int stati[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < e.getRaccoltaE().size(); i++) {
			stati[i] = e.getRaccoltaE().get(i).getStatoEvento();
		}
		return stati;
	}

}
