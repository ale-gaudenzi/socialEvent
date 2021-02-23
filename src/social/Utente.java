package social;

/**
 * Classe che racchiude le informazioni relative a un utente del social network
 * 
 * @author Alessandro Gaudenzi
 */
public class Utente implements Jsonable {
	private String nome;
	private String password;
	private SpazioPersonale spazioP;
	private Profilo profilo;

	/**
	 * Costruttore per il tipo utente
	 * 
	 * @param nome
	 * @param password
	 * @param profilo
	 */
	public Utente(String nome, String password, Profilo profilo) {
		this.setNome(nome);
		this.setPassword(password);
		this.spazioP = new SpazioPersonale(this);
		this.profilo = profilo;
	}

	public Utente() {

	}

	/**
	 * Override del metodo equals per confrontare l'uguaglianza tra due utenti
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj != null && obj instanceof Utente) {
			isEqual = this.getNome().equals(((Utente) obj).getNome());
		}

		return isEqual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SpazioPersonale getSpazioP() {
		return spazioP;
	}

	public Profilo getProfilo() {
		return profilo;
	}

	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
}
