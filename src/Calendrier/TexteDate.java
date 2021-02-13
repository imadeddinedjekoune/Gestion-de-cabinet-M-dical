package Calendrier;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TexteDate extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField t1 ;
	private MiniCal mc ;
	private boolean act = true ;
	public TexteDate()
	{
		
		mc = new MiniCal();
		t1 = new JTextField();
		setLayout(null);
		
		t1.setBounds(0,0,100,40);
		mc.setBounds(100, 0,40, 40);
		
		mc.getSaveBtnClick(new SaveClick() {

			@Override
			public void Click(Date D) {
				if (act)
				{
					t1.setText(""+D.annee+"-"+Fun.returnDateModify(D.mois+1)+"-"+Fun.returnDateModify(D.jour+1));
				}
			}

			
		});
		
		add(t1);
		add(mc);
	}
	
	public String getText()
	{
		return t1.getText();
	}
	
	public void setText(String t)
	{
		t1.setText(t);
	}
	
	public void desactive ()
	{
		t1.setEditable(false);
		act = false ;
	}
	
	
}
