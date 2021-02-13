package GUI;

import javax.swing.SwingUtilities;

import GUI.Frames.LoginFrame;

public class Main 
{
	public static LoginFrame F ;
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() {
				F = new LoginFrame();
			}
		});
	}
	
}
