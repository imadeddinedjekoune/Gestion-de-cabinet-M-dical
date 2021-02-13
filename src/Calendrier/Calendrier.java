package Calendrier;

import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.JPanel;






public class Calendrier extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public ArrayList<Date> TabC; // Tab De Couleur //
	Days d ; // Pnael Des Jours //
	public DatePanel date ; // Sam Dim ... //
	private int a , m ; // Annnee , Mois //
	
	
	public int getA() 
	{
		return a;
	}

	public int getM() 
	{
		return m;
	}

	public Calendrier(int annee , int mois)
	{
		d = new Days(annee, mois);
		date = new DatePanel(""+(mois+1)+"-"+annee);
		a = annee ;
		m = mois ;
		
		setLayout(new BorderLayout());
		add(d,BorderLayout.CENTER);
		add(date,BorderLayout.NORTH);
	}
	

	public void getClick(DaysClick D) 
	{
		d.clicked(D);
	}
	
	public void setColor ()
	{
		d.Color(TabC);
		d.Rep();
	}
	
}
