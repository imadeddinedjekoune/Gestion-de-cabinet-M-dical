package Calendrier;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CalendrierAffiche extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Calendrier c ;
	private JButton suiv , prec ;
	private JComboBox<String> comboBox ;
	private JPanel utilities ;
	private int a , m ;
	
	private void init (int annee , int mois)
	{
		c = new Calendrier(annee, mois);
		suiv = new JButton("SUIV");
		prec = new JButton("PREC");
		suiv.addActionListener(this);
		prec.addActionListener(this);
		
		
		String s1[] = {"Annnee","Mois"};
		comboBox = new JComboBox<String>(s1);
		
		a = c.getA();
		m = c.getM();
		
		utilities = new JPanel();
		utilities.add(comboBox);
		utilities.add(prec);
		utilities.add(suiv);
		
	}
	
	private void add()
	{
		setLayout(new BorderLayout());
		add(c,BorderLayout.CENTER);
		add(utilities,BorderLayout.SOUTH);
		
	}
	
	public CalendrierAffiche(int annee , int mois)
	{
		init(annee,mois);
		add();
	}
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public void getClick(DaysClick D)
	{
		c.getClick(D);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == suiv)
		{
			if (comboBox.getSelectedIndex() == 1)
			{
				if (m < 11)
				{
					m++;
					c.d.Rep(a,m);
					c.date.changeDate(""+(m+1)+"-"+a);
				}else
				{
					m = 0 ;
					a++;
					c.d.Rep(a,m);
					c.date.changeDate(""+(m+1)+"-"+a);
				}
			}else
			{
				a++;
				c.d.Rep(a,m);
				c.date.changeDate(""+(m+1)+"-"+a);
			}
			
		}else
		{
			if (comboBox.getSelectedIndex() == 1)
			{
				if (e.getSource() == prec)
				{
					if (m > 0)
					{
						m--;
						c.d.Rep(a,m);
						c.date.changeDate(""+(m+1)+"-"+a);
					}else
					{
						m = 11 ;
						a--;
						c.d.Rep(a,m);
						c.date.changeDate(""+(m+1)+"-"+a);
					}
				}
			}else
			{
				if (a > 0)
				{
					a--;
					c.d.Rep(a,m);
					c.date.changeDate(""+(m+1)+"-"+a);
				}
			}
		}
	}
	
	public void setTabC(ArrayList<Date> T)
	{
		c.TabC = T ;
	}
	
	public ArrayList<Date> getTabC ()
	{
		return c.TabC;
	}
	
	public void setColor()
	{
		c.setColor();
	}
	
	public void repaintC ()
	{
		removeAll();
		updateUI();
		init(this.a,this.m);
		add();
		c.setColor();
	}
	
	
	
}
