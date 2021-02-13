package GUI.Frames;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Admin.AdminPanel;
import GUI.Main;


public class AdminFrame extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private static int width = 1100 ;
	private static int height = 600 ;
	private AdminPanel p ;
	
	public AdminFrame()
	{
		p = new AdminPanel();
		
		setTitle("Admin");
		setSize(width,height);
		setVisible(true);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
		setLayout(new BorderLayout());
		
		addWindowListener(this);
		
		add(p,BorderLayout.CENTER);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Main.F = new LoginFrame();
			}
		});
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
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
