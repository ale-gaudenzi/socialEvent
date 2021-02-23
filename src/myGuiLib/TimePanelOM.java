package myGuiLib;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimePanelOM extends JPanel {
	private static final long serialVersionUID = 1L;
	JTextField textHour;
	JTextField textMinute;
	private ArrayList<JTextField> arrayText;

	public TimePanelOM(Dimension maxDimension, Dimension twoDigit) {
		arrayText = new ArrayList<>(); 
		setMaximumSize(maxDimension);

		textHour = new JTextField();
		textHour.setPreferredSize(twoDigit);
		JLabel lblHour = new JLabel("H:");

		textMinute = new JTextField();
		textMinute.setPreferredSize(twoDigit);
		JLabel lblMinute = new JLabel("M:");

		add(lblHour);
		add(textHour);
		arrayText.add(textHour);
		
		add(lblMinute);
		add(textMinute);
		arrayText.add(textMinute);
	
	}
	
	public boolean isEmpty() {
		if(textHour.getText().length() == 0 || textMinute.getText().length() == 0)
			return true;
		else
			return false;
	}

	public JTextField getHour() {
		return textHour;
	}

	public JTextField getMinute() {
		return textMinute;
	}
	
	public ArrayList<JTextField> getArrayText() {
		return arrayText;
	}

	public void setArrayText(ArrayList<JTextField> arrayText) {
		this.arrayText = arrayText;
	}
}