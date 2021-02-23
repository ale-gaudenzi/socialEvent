package social;

import java.io.IOException;
import java.util.HashMap;

import utility.JsonUtil;

/**
 * Classe contenente le informazioni di configurazione, come i percorsi dei file di salvataggio
 * 
 * @author Alessandro Gaudenzi
 *
 */
public class Config implements Jsonable {
	private final static String FILE_CONFIG = "config.json";
	private final static String KEY_RACCOLTAE = "raccoltaEventi";
	private final static String KEY_RACCOLTAU = "raccoltaUtenti";
	private static Config instance = new Config();
	private String percorsoRaccoltaE;
	private String percorsoRaccoltaU;
	private HashMap<String, String> mapConfig;
	
	/**
	 * Costrutture privato (singleton) dell'oggetto config
	 */
	private Config() {
		try {
			mapConfig = JsonUtil.loadMapStringJSON(FILE_CONFIG);
			percorsoRaccoltaE = mapConfig.get(KEY_RACCOLTAE);
			percorsoRaccoltaU = mapConfig.get(KEY_RACCOLTAU);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che ritorna l'unica istanza ammessa dell'oggetto config
	 */
	public static Config getInstance() {
		return instance;
	}
	
	
	public String getRaccoltaE() {
		return percorsoRaccoltaE;
	}

	public void setRaccoltaE(String raccoltaE) {
		this.percorsoRaccoltaE = raccoltaE;
	}

	public String getRaccoltaU() {
		return percorsoRaccoltaU;
	}

	public void setRaccoltaU(String raccoltaU) {
		this.percorsoRaccoltaU = raccoltaU;
	}
}
