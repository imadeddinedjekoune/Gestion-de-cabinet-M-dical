package Medcin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JDateSwitch extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> mois , semaine ;
	private JTextField t ;
	private JButton add , add2 , add3;
	private getDateEx myD ;
	private JLabel la , lm , ls ;
	
	public JDateSwitch()
	{
		String m[] = {"Janvier","Fevrier","Mars","Avrile","Mai","juin","juillet","aout",
				    		"septembre","octobre","novembre","decembre"	};
		mois = new JComboBox<String>(m);
		String s[] = {"Semain 1","Semain 2","Semain 3","Semain 4"};
		semaine = new JComboBox<String>(s);
		t = new JTextField();
		add = new JButton("Get");
		add2 = new JButton("aujourd'hui");
		add3 = new JButton("cette semaine");
		
		la = new JLabel("annee : ");
		lm = new JLabel("mois : ");
		ls = new JLabel("semaine : ");
		
		
		
		add.addActionListener(this);
		add2.addActionListener(this);
		add3.addActionListener(this);
		
		setLayout(null);
	
		la.setBounds(0,10,75,25);
		t.setBounds(45,10,85,25);
		lm.setBounds(150,10,75,25);
		mois.setBounds(190,10,100,25);
		ls.setBounds(310,10,75,25);
		semaine.setBounds(370,10,100,25);
		add.setBounds(510,2,120,45);
		add2.setBounds(630,2,120,45);
		add3.setBounds(750,2,120,45);
		
		
		add(add2);
		add(add3);
		
		add(t);
		add(mois);
		add(semaine);
		add(ls);
		add(add);
		add(lm);
		add(la);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == add)
		{
			
			if (myD != null)
			{
				myD.getdatelabel(t.getText(),mois.getSelectedIndex(), semaine.getSelectedIndex());
			}
		}
		
		if (e.getSource() == add2)
		{
			
			if (myD != null)
			{
				myD.getdatelabel(t.getText(),-1, -1);
			}
		}
		
		if (e.getSource() == add3)
		{
			
			if (myD != null)
			{
				
				myD.getdatelabel(t.getText(),-1,-2);
			}
		}
		
	}
	
	public void update(getDateEx d)
	{
		myD = d  ;
	}

	public void setAllFlieds(String annee , int mois , int semaine) 
	{
		t.setText(annee);
		this.mois.setSelectedIndex(mois);
		this.semaine.setSelectedIndex(semaine);
	}
	
}
