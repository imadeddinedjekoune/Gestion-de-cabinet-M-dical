package Medcin;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AjouterMaladieQlqPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField text ;
	
	public AjouterMaladieQlqPanel()
	{
		text = new JTextField();
		
		setLayout(new BorderLayout());
		
		add(text,BorderLayout.CENTER);
	}
	
	public String getText ()
	{
		return text.getText();
	}
	

}
