package Medcin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Main;
import GUI.Frames.LoginFrame;
import GUI.Frames.MainFrame;

public class DocIntro extends JPanel
{
	private static final long serialVersionUID = 1L;
	private BufferedImage Ico = null ;
	private BufferedImage Logout = null ;
	private JLabel lnom , lprenom , l , date ;
	@SuppressWarnings("deprecation")
	public DocIntro(String nom , String Prenom)
	{
		
		try 
		{
			Ico  = ImageIO.read(new File("res/MedIco.png"));
			Logout  = ImageIO.read(new File("res/Logout.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		setLayout(null);
		
		l = new JLabel("Deconnecter");
		l.setIcon(new ImageIcon(Logout));
		l.setBounds(900, 0, 180, 90);
		
		
		lnom = new JLabel("Nom : "+nom);
		lprenom = new JLabel("Prenom : "+Prenom);
		
		lnom.setBounds(200, 10, 300, 30);
		lnom.setFont(new Font("Serif", Font.BOLD, 20));
		
		lprenom.setBounds(200,50, 300, 30);
		lprenom.setFont(new Font("Serif", Font.BOLD, 20));
		
		String s = new String();
		Date d=new Date();  
     
		switch (d.getDay()) 
		{
			case 0 :
				s = new String("Dimanche");
				break ;
			case 1 :
				s = new String("Lundi");
				break ;
			case 2 :
				s = new String("Mardi");
				break ;
			case 3 :
				s = new String("Mercredi");
				break ;
			case 4 :
				s = new String("Jeudi");
				break ;
			case 5 :
				s = new String("Vendredi");
				break ;
			case 6 :
				s = new String("Semedi");
				break ;		
		}
		
		date = new JLabel(java.time.LocalDate.now().toString()+" "+
				s);
		
		
		date.setBounds(500,25, 300, 30);
		date.setFont(new Font("Serif", Font.BOLD, 22));
		
		
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
						MainFrame.f.dispose();
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
		
		
		add(lnom);
		add(lprenom);
		add(l);
		add(date);
		
	}
	
	@Override
    protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.drawImage(Ico, 50, 10,null);
        g.setColor(new Color(102,178,255));
        g.draw3DRect(0, 0, 1078, 88, false );
        g.draw3DRect(1, 1, 1076, 86, true );
    }
}
