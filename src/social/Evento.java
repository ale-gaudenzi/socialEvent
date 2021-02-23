package social;

import java.util.*;
import java.time.*;

public interface Evento extends Jsonable {
	public boolean isPartecipante(Utente daVerificare);

	public void removePartecipante(Utente daRimuovere);

	public void iscriviUtente(Utente daIscrivere) throws Exception;

	public void notificaRitiro();

	public boolean equals(Evento e);

	public int getCode();

	public void setCode(int code);

	public String getNome();

	public void setNome(String nome);

	public int getTipoEvento();

	public void setTipoEvento(int tipoEvento);

	public int getStatoEvento();

	public void setStatoEvento(int statoEvento);

	public int getMaxPartecipanti();

	public void setMaxPartecipanti(int maxPartecipanti);

	public LocalDate getScadenzaIscrizione();

	public void setScadenzaIscrizione(LocalDate scadenzaIscrizione);

	public LocalDate getDataInizio();

	public void setDataInizio(LocalDate dataInizio);

	public LocalDate getDataFine();

	public void setDataFine(LocalDate dataFine);

	public Duration getDurata();

	public void setDurata(Duration durata);

	public String getLuogo();

	public void setLuogo(String luogo);

	public LocalTime getOraInizio();

	public void setOraInizio(LocalTime oraInizio);

	public LocalTime getOraFine();

	public void setOraFine(LocalTime oraFine);

	public double getQuota();

	public void setQuota(int quota);

	public String getCompresoQuota();

	public void setCompresoQuota(String compresoQuota);

	public String getNote();

	public void setNote(String note);

	public Utente getCreatore();

	public void setCreatore(Utente creatore);

	public ArrayList<Utente> getPartecipanti();

	public void setPartecipanti(ArrayList<Utente> partecipanti);

	public LocalDate getTermineRitiro();

	public void setTermineRitiro(LocalDate termineRitiro);

	public int getTolleranza();

	public void setTolleranza(int tolleranza);
}
