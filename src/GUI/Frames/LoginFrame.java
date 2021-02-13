package GUI.Frames;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import GUI.Login.LoginPanel;



public class LoginFrame extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private static int width = 610 ;
	private static int height = 320 ;
	private LoginPanel p ;
	public static JFrame log ;
	//TextPanel t;
	public LoginFrame()
	{
		log = this ;
		p = new LoginPanel(this);
		setTitle("Cabinet Medicale");
		setSize(width,height);
		this.addWindowListener(this);
		setVisible(true);
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
		setLayout(new BorderLayout());
		add(p,BorderLayout.CENTER);
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
