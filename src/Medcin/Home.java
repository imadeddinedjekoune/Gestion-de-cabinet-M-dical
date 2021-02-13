package Medcin;

import javax.swing.JPanel;

import Stock.StaticsMultiPanel;



public class Home extends JPanel
{
	private static final long serialVersionUID = 1L;
	private DocIntro docintro ;
	private TestPatient testp ;
	public static StaticsMultiPanel sp ;
	
	public Home(String nom , String prenom)
	{
		docintro = new DocIntro(nom,prenom);
		testp = new TestPatient();
		sp =  new StaticsMultiPanel();
		
		setLayout(null);
		docintro.setBounds(1,1,1080,90);
		testp.setBounds(360,110,705,390);
		sp.setBounds(1,110,350,390);
		
		add(testp);
		add(docintro);
		add(sp);
	}
}
