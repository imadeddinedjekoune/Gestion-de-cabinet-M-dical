package Calendrier;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;





public class Days extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> labelArray ;
	private ArrayList<JLabel> Daysout ;
	private static int annee , mois ;
	private DaysClick myD ;
	private ArrayList<Date> TabC001 ;
	
	public Days (int annee , int mois )
	{
		repaintPanel(annee, mois);
	}

	void repaintPanel(int annee , int mois )
	{
		labelArray = new ArrayList<JLabel>();
		Daysout = new ArrayList<JLabel>();
		
		Days.annee = annee;
		Days.mois = mois ;
		
		setLayout(new GridLayout( 0 , 7 ));
		int i ;
		for (i = 0 ; i < Fun.Day(annee, mois) ; i++ )
		{
			Daysout.add(new JLabel(" "));
			Border innerBorder = BorderFactory.createEtchedBorder();
			Border outBorder = BorderFactory.createEmptyBorder();
			Daysout.get(i).setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
			Daysout.get(i).setOpaque(true);
			Daysout.get(i).setBackground(new Color(224,224, 224));
			add(Daysout.get(i));
		}
		for (i = 1; i <= Fun.Max(annee, mois); i++)
		{
			labelArray.add(new JLabel(String.valueOf(i)));
			labelArray.get(i-1).addMouseListener(this);
			labelArray.get(i-1).setHorizontalAlignment(JLabel.CENTER);
			labelArray.get(i-1).setFont(new Font("Verdana", Font.PLAIN, 20));
			labelArray.get(i-1).setOpaque(true);
			Border innerBorder = BorderFactory.createEtchedBorder();
			Border outBorder = BorderFactory.createEmptyBorder();
			labelArray.get(i-1).setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
			add(labelArray.get(i-1));
			
		}
		i = (i+Fun.Day(annee, mois));
		while ((i-1)%7 != 0)
		{
			JLabel L = new JLabel();
			Border innerBorder = BorderFactory.createEtchedBorder();
			Border outBorder = BorderFactory.createEmptyBorder();
			L.setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
			L.setOpaque(true);
			L.setBackground(new Color(224,224, 224));
			add(L);
			i++;
		}
		
		Color(TabC001); // Setting Colors //
	}

	public void Color(ArrayList<Date> tab) 
	{
		TabC001 = tab;
		int i = 0 ;
		if (tab != null )
		{
			for ( i = 0 ; i < tab.size() ; i++ )
			{
				if (tab.get(i).annee == annee)
				{
					
					if (tab.get(i).mois == mois)
					{
						labelArray.get(tab.get(i).jour).setBackground(new Color(51,255, 153));
					}
				}
			}
		}
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JLabel L = (JLabel) e.getSource();
		if ( myD != null )
		{
			myD.get(Integer.parseInt(L.getText()),e.getButton());
		}
	}
	
	public void clicked(DaysClick d) {
		myD = d ;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		JLabel L = (JLabel) e.getSource();
		L.setBackground(new Color(204, 255, 255));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		removeAll();
		updateUI();
		repaintPanel(annee,mois);
	}

	public void Rep(int annee, int mois) {
		removeAll();
		updateUI();
		repaintPanel(annee, mois);
	}
	
	public void Rep ()
	{
		removeAll();
		updateUI();
		repaintPanel(annee,mois);
	}

}
