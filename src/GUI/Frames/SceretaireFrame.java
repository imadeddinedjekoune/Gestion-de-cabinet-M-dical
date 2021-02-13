package GUI.Frames;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Classes.Secretaire;
import Secretaire.InfoPersonne;
import Secretaire.PersonnePanel;
import Secretaire.SecretairePanel;

public class SceretaireFrame extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private static int width = 1100 ;
	private static int height = 600 ;
	public static Secretaire s;
	public static SecretairePanel sec ;
	private JTabbedPane tp ;
	public static PersonnePanel per ;
	public static JFrame F ;
	private static InfoPersonne infop ;
	
	public SceretaireFrame(Secretaire ss)
	{
		
		tp = new JTabbedPane();
		s = ss ;
		s.loadMedcin();
		s.loadRDV();
		F = this ;
		
		sec = new SecretairePanel();
		per = new PersonnePanel();
		infop = new InfoPersonne();
		
		tp.add("Agenda",sec);
		tp.add("Personnes Liste",infop);
		tp.add("RDV Liste",per);
		
		setTitle("Test");
		setSize(width,height);
		setVisible(true);
		addWindowListener(this);
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
		setLayout(new BorderLayout());
		add(tp,BorderLayout.CENTER);
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
