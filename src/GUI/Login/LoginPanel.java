package GUI.Login;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Classes.Admin;
import Classes.Medcin;
import Classes.Secretaire;
import GUI.Main;
import GUI.Frames.AdminFrame;
import GUI.Frames.MainFrame;
import GUI.Frames.SceretaireFrame;
import SQLite.SQLite;


public class LoginPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Image wallpaper = null , user = null , password = null , Can = null , Ent = null ;
	private JLabel us , pa ;
	private JTextField tuser ;
	private JButton enter , cancel;
	private JPasswordField tpass ;
	private JFrame myF ;
	
	
	public LoginPanel(JFrame f)
	{
		myF = f ;
		try 
		{
			wallpaper = ImageIO.read(new File("res/Login/Login.jpg"));
			user = ImageIO.read(new File("res/Login/user.png"));
			password = ImageIO.read(new File("res/Login/pass.png"));
			Can = ImageIO.read(new File("res/Login/Cancel.png"));
			Ent = ImageIO.read(new File("res/Login/Enter.png"));	
		} 
		catch (IOException e) 
		{	
			e.printStackTrace();
		}
		
		
		
		cancel = new JButton("Cancel");
		cancel.setBounds(100, 140, 110, 30);
		cancel.setIcon(new ImageIcon(Can));
		
		enter = new JButton("Enter");
		enter.setBounds(230, 140, 110, 30);
		enter.setIcon(new ImageIcon(Ent));
		
		cancel.addActionListener(this);
		enter.addActionListener(this);
		
		us = new JLabel("");
		pa = new JLabel("");
		
		us.setBounds(50, 20, 40, 40);
		us.setIcon(new ImageIcon(user));
		
		pa.setBounds(50, 80, 40, 40);
		pa.setIcon(new ImageIcon(password));
		
		tuser = new JTextField();
		tuser.setBounds(110, 24, 200, 30);
		tuser.setFont(new Font("Monospaced", Font.BOLD, 16));
		tuser.setBorder(BorderFactory.createEtchedBorder());
		
		tpass = new JPasswordField();
		tpass.setBounds(110, 80, 200, 30);
		tpass.setFont(new Font("Monospaced", Font.BOLD, 16));
		tpass.setBorder(BorderFactory.createEtchedBorder());
		
		
		setLayout(null);
		
		
		add(us);
		add(tuser);
		add(pa);
		add(tpass);
		add(cancel);
		add(enter);
	}
	
	protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        g.drawImage(wallpaper, 0, 0,null);
       
    }

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter)
		{
			int role = Admin.Role(tuser.getText(), tpass.getText());
			if ( role  == 0 )
			{
				
				if (tuser.getText().equals("")&&tpass.getText().equals(""))
				{
					// Admin //
					
					Main.F.dispose();
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() 
						{
							new AdminFrame();
						}
					});
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Utilisateur ou Mot De Pass Eroné ",
						      "Login", JOptionPane.ERROR_MESSAGE);
				}
				
			}else
			{
				
				if (role == 1)
				{
					
					// Medcin //
					Medcin m = SQLite.selectMedcin(tuser.getText(),tpass.getText());
					m.loadSecretaire();
					m.loadConsultation();
					System.out.println(m.getPatientConuslter());
					
					// Assosier Son Sceratire //
					Main.F.dispose();
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							new MainFrame(m);
						}
					});
					
				}else
				{
					if (role == 2)
					{
						// Scertaire //
						
						Secretaire s = new Secretaire(tuser.getText(), tpass.getText());
						
						Main.F.dispose();
						SwingUtilities.invokeLater(new Runnable() 
						{
							@Override
							public void run() 
							{
								new SceretaireFrame(s);
							}
						});
					}
				}
				
			}
		}
		
		if (e.getSource() == cancel)
		{
			myF.dispatchEvent(new WindowEvent(myF, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	

}





