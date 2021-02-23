package social;

import controller.MasterController;

/**
 * Classe main che crea e inizializza il controller principale
 * 
 * @author Alessandro Gaudenzi
 */
public class MainSocial {

	public static void main(String[] args) {	
		MasterController controller = new MasterController();
		controller.init();
	}
}