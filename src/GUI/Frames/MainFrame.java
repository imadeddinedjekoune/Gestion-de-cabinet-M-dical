package GUI.Frames;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import Classes.Medcin;
import Medcin.MedcinPanel;
//import Medcin.MedcinPanel;




public class MainFrame extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private static int width = 1100 ;
	private static int height = 600 ;
	private MedcinPanel m ;
	public static JFrame f;
	public static Medcin medcin ;
	
	
	public MainFrame(Medcin med) 
	{
		medcin = med ;
		
		f = this ;
		m = new MedcinPanel();
		setTitle("Cabinet");
		setSize(width,height);
		setVisible(true);
		addWindowListener(this);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
		setLayout(new BorderLayout());
		add(m,BorderLayout.CENTER);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) 
	{	
		
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
