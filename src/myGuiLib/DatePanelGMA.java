package myGuiLib;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DatePanelGMA extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textDay;
	private JTextField textMonth;
	private JTextField textYear;
	private ArrayList<JTextField> arrayText;

	public DatePanelGMA(Dimension maxDimension, Dimension twoDigit, Dimension fourDigit) {
		arrayText = new ArrayList<>(); 
		setMaximumSize(maxDimension);

		textDay = new JTextField();
		textDay.setPreferredSize(twoDigit);
		JLabel lblDay = new JLabel("G:");

		textMonth = new JTextField();
		textMonth.setPreferredSize(twoDigit);
		JLabel lblMonth = new JLabel("M:");

		textYear = new JTextField();
		textYear.setPreferredSize(fourDigit);

		add(lblDay);
		add(textDay);
		arrayText.add(textDay);
		
		add(lblMonth);
		add(textMonth);
		arrayText.add(textMonth);
		JLabel lblYear = new JLabel("A:");
		
				add(lblYear);
		add(textYear);
		arrayText.add(textYear);

	}
	
	public boolean isEmpty() {
		if(textYear.getText().length() == 0 || textMonth.getText().length() == 0 || textDay.getText().length() == 0)
			return true;
		else
			return false;
	}

	public JTextField getDay() {
		return textDay;
	}

	public JTextField getMonth() {
		return textMonth;
	}

	public JTextField getYear() {
		return textYear;
	}
	public void setArrayText(ArrayList<JTextField> arrayText) {
		this.arrayText = arrayText;
	}

	public ArrayList<JTextField> getArrayText() {
		return arrayText;
	}

}