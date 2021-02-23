package social;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RaccoltaEventi.class, name = "RaccoltaEventi"),
    @JsonSubTypes.Type(value = RaccoltaUtenti.class, name = "RaccoltaUtenti"),
    @JsonSubTypes.Type(value = Utente.class, name = "Utente"),
    @JsonSubTypes.Type(value = Evento.class, name = "Evento"),
    @JsonSubTypes.Type(value = EventoQuote.class, name = "EventoQuote"),
    @JsonSubTypes.Type(value = EventoBase.class, name = "EventoBase"),
    @JsonSubTypes.Type(value = SpazioPersonale.class, name = "SpazioPersonale"),
    @JsonSubTypes.Type(value = Profilo.class, name = "Profilo"),
    @JsonSubTypes.Type(value = Config.class, name = "Config"),
})

/**
 * Interfaccia che devono implementare gli oggetti da salvare in JSON
 * 
 * @author Alessandro Gaudenzi
 *
 */
public interface Jsonable {
	
}
