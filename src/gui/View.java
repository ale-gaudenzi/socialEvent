package gui;

import javax.swing.JPanel;

public interface View {
	static final String USER_MESSAGE = "Nome utente o password errati";

	static final String INPUT_ERROR_MESSAGE = "Input non valido";
	static final String INPUT_OBLIGATORY_ERROR_MESSAGE = "Input obbligatorio mancante";
	static final String WARNING = "Attenzione!";
	
	public JPanel getMainPanel();
	public void buildPanel();
}
