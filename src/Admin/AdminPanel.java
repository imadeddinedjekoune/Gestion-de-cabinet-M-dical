package Admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tp ;
	private TablesDeDB t ;
	private RQT_SQL r ;  
	
	
	public AdminPanel ()
	{
		
		t = new TablesDeDB();
		r = new RQT_SQL();
		
		
		tp = new JTabbedPane();
		tp.add("Tables De DB",t);
		tp.add("Requete SQL",r);
		
		
		setLayout(new BorderLayout());
		
		add(tp,BorderLayout.CENTER);
		
	}
	
}
