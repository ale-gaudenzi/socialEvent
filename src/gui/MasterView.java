package gui;

import javax.swing.JFrame;


public class MasterView {
	private JFrame frame;

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 800;

	public MasterView() {
		
	}
	
	public void build() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setVisible(true);
	}
	
	public void changeView(View toChange) {
		frame.setSize(DEFAULT_WIDTH+1, DEFAULT_HEIGHT+1);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(toChange.getMainPanel());
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
