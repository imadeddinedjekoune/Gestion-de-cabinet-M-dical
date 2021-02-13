package Secretaire;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Personne;

public class SecretaireModifPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JLabel nom , prenom , email , tel , Save ;
	private JTextField tnom , tprenom , temail , ttel ;
	private Image image = null ;
	private ClickBtn myB ;
	public SecretaireModifPanel(Personne p)
	{
		
		try {
			image = ImageIO.read(new File("res/save.png"));
		} catch (Exception e) 
		{
			System.out.println("ex : "+e);
		}
		
		nom = new JLabel("Nom : ");
		prenom = new JLabel("Prenom : ");
		email  = new JLabel("Email : ");
		tel = new JLabel("Tel : ");
		Save = new JLabel("Save");
		
		tnom = new JTextField(p.getNom());
		tprenom = new JTextField(p.getPrenom());
		temail  = new JTextField(p.getEmail());
		ttel = new JTextField(p.getTel());
		
		
		nom.setBounds(10,10,200,30);
		prenom.setBounds(10,50,200,30);
		email.setBounds(10,90,200,30);
		tel.setBounds(10,130,200,30);
		
		tnom.setBounds(120,8,120,30);
		tprenom.setBounds(120,48,120,30);
		temail.setBounds(120,88,120,30);
		ttel.setBounds(120,128,120,30);
		
		nom.setFont(new Font("Serif", Font.BOLD, 15));
		nom.setForeground(Color.black);
		
		prenom.setFont(new Font("Serif", Font.BOLD, 15));
		prenom.setForeground(Color.black);
		
		email.setFont(new Font("Serif", Font.BOLD, 15));
		email.setForeground(Color.black);
		
		tel.setFont(new Font("Serif", Font.BOLD, 15));
		tel.setForeground(Color.black);
		
		Save.setIcon(new ImageIcon(image));
		
		Save.setBounds(140, 140, 160, 135);
		
		Save.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (myB != null)
				{
					myB.click();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				Save.setBounds(140, 140, 155, 130);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				Save.setBounds(140, 140, 160, 135);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setLayout(null);
		
		add(nom);
		add(prenom);
		add(email);
		add(tel);
		add(tnom);
		add(tprenom);
		add(temail);
		add(ttel);
		add(Save);
	}
	
	public void update (ClickBtn b)
	{
		myB = b ;
	}
	
	public String getNom ()
	{
		return tnom.getText();
	}
	public String getPrenom ()
	{
		return tprenom.getText();
	}
	public String getEmail ()
	{
		return temail.getText();
	}
	public String getTel ()
	{
		return ttel.getText();
	}
	
}
