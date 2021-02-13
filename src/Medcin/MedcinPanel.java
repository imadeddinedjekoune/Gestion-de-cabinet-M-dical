package Medcin;


import java.awt.BorderLayout;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI.Frames.MainFrame;
import Stock.MaladiePanel;


public class MedcinPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public static JTabbedPane tp ;
	private JMenuBar mb ;
	private JMenu m ;
	private JMenuItem i , i2 , i3 ;
	private AgendaTest t ;
	private MaladiePanel mp ;
	private InfoPerso ip ;
	private Home home ;
	
	public MedcinPanel() 
	{
		
		tp = new JTabbedPane();
		home = new Home(MainFrame.medcin.getNom(),MainFrame.medcin.getPrenom());
		i = new JMenuItem("Gerer Le Stock");
		i2 = new JMenuItem("Iformations Personnelles");
		i3 = new JMenuItem("Quiter");
		
		i.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);
		
		m = new JMenu("Menu");
		mb = new JMenuBar();
		t = new AgendaTest();
		
		mp = new MaladiePanel(MainFrame.medcin.getNom());
		ip = new InfoPerso();
		
		m.add(i);
		m.add(i2);
		m.add(i3);
		
		mb.add(m);
		
		tp.add("Home",home);
		tp.add("Agenda",t);
		
		
		setLayout(new BorderLayout());
		add(tp,BorderLayout.CENTER);
		add(mb,BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == i )
		{
			JDialog d = new JDialog();
			d.setTitle("Ajout");
			d.setLayout(new BorderLayout());
			d.add(mp,BorderLayout.CENTER);
			d.setSize(655,500); 
			d.setResizable(false);
	        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-670)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
	        d.setVisible(true); 
		}
		if (e.getSource() == i2 )
		{
			JDialog d = new JDialog();
			d.setTitle("Infos");
			d.setLayout(new BorderLayout());
			d.add(ip,BorderLayout.CENTER);
			d.setSize(330,320); 
			d.setResizable(false);
	        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-330)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-320)/2);
	        d.setVisible(true); 
		}
		if (e.getSource() == i3 )
		{
			System.exit(0);
		}
		
		
	}
	
	
	
}
