package Secretaire;


import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Main;
import GUI.Frames.LoginFrame;
import GUI.Frames.SceretaireFrame;

public class SecretaireInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel nom , prenom , l  ;
	private BufferedImage Logout = null ;
	
	public SecretaireInfoPanel()
	{
		nom = new JLabel("Nom : "+SceretaireFrame.s.getNom());
		prenom = new JLabel("Prenom : "+SceretaireFrame.s.getPrenom());
		l = new JLabel("Deconexion");
		
		try 
		{
			Logout  = ImageIO.read(new File("res/Logout.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		nom.setBounds(10,10,300,30);
		prenom.setBounds(10,40,300,30);
		l.setBounds(900,0,200,75);
		l.setIcon(new ImageIcon(Logout));
		
		nom.setFont(new Font("Verdana", Font.PLAIN, 18));
		prenom.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		// Listenners //
		
		l.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				JDialog d = new JDialog();
				d.setTitle("Deconnection");
				
				JLabel deco = new JLabel("Etes Vous Sure De Deconnecter ?");
				JButton yes = new JButton("oui"), no = new JButton("No") ;
				
				d.setLayout(null);
				
				deco.setBounds(30,10,270,30);
				yes.setBounds(40,90,90,30);
				no.setBounds(190,90,90,30);
				
				yes.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						d.dispose();
						SceretaireFrame.F.dispose();
						SwingUtilities.invokeLater(new Runnable() 
						{
							
							@Override
							public void run() {
								Main.F = new LoginFrame();
							}
						});
						
					}
				});
				
				no.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						d.dispose();
					}
				});
				
				deco.setFont(new Font("Serif", Font.BOLD, 17));
				
				d.setResizable(false);
				d.add(deco);
				d.add(no);
				d.add(yes);
				
				
				
				d.setSize(330,170); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-330)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-170)/2);
		        d.setVisible(true); 
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		setLayout(null);
		
		add(nom);
		add(prenom);
		add(l);
		
	}
	
	
	
}
