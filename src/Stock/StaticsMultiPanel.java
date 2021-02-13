package Stock;

import java.awt.CardLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class StaticsMultiPanel extends JLayeredPane 
{
	private static final long serialVersionUID = 1L;
	
	public static AffichStatsPatient p2 ;
	public static StaticsPanelIntGUI p1 ;
	public static AffichStatsMaladiePatient p3 ;
	
	public StaticsMultiPanel()
	{
		// Init MultiPanel //
		setLayout(new CardLayout(0, 0));
		
		
		p1 = new StaticsPanelIntGUI();
		p2 = new AffichStatsPatient();
		p3 = new AffichStatsMaladiePatient(null,null);
		
		add(p1);
		add(p2);
		add(p3);
		
	}
	
	private void Init ()
	{
		p1 = new StaticsPanelIntGUI();
	}
	
	
	public void switchPanel (JPanel panel)
	{
		Init();
		removeAll();
		add(panel);
		repaint();
		revalidate();
	}
	
}
