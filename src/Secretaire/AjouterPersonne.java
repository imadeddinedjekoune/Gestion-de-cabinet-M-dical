package Secretaire;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Personne;


public class AjouterPersonne extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField tnom , tprenom , temail , ttel , tnb ;
	private JLabel nom , prenom , email , tel , nb ;
	private String nom_Tapee , prenom_Tapee ;
	private int cpt = 0 ;
	
	private void init (int nbPers)
	{
		tnom = new JTextField();
		tprenom = new JTextField();
		temail = new JTextField();
		ttel = new JTextField();
		tnb = new JTextField();
		
		nom = new JLabel("nom");
		prenom = new JLabel("prenom");
		email = new JLabel("email");
		tel = new JLabel("telephone");
		nb = new JLabel("nb consult");
		
		
		setLayout(null);
		
		nom.setBounds(10, 10, 75, 20);
		prenom.setBounds(10, 50, 75, 20);
		email.setBounds(10, 90, 75, 20);
		tel.setBounds(10, 140, 75, 20);
		nb.setBounds(10, 190, 75, 20);
		
		tnom.setBounds(100, 10, 120, 30);
		tprenom.setBounds(100, 50, 120, 30);
		temail.setBounds(100, 90, 120, 30);
		ttel.setBounds(100, 140, 120, 30);
		tnb.setBounds(100, 190, 120, 30);
		
		nom.setFont(new Font("Serif", Font.BOLD, 14));
		prenom.setFont(new Font("Serif", Font.BOLD, 14));
		email.setFont(new Font("Serif", Font.BOLD, 14));
		tel.setFont(new Font("Serif", Font.BOLD, 14));
		nb.setFont(new Font("Serif", Font.BOLD, 14));
		tnb.setText(""+nbPers);
	}
	
	private void add ()
	{	
		add(nom);
		add(prenom);
		add(email);
		add(tel);
		add(nb);
		add(tnom);
		add(tprenom);
		add(temail);
		add(ttel);
		add(tnb);
	}
	
	public AjouterPersonne(int nbPers)
	{
		init(nbPers);
		Listener();
		add();
		
	}
	
	
	private void Listener ()
	{
		tnom.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				Trouvee();
			}
			
			

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tprenom.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				Trouvee();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void Trouvee() 
	{
		nom_Tapee = tnom.getText();
		prenom_Tapee = tprenom.getText();	
		if (Personne.existe(nom_Tapee, prenom_Tapee))
		{
			Personne p = new Personne(nom_Tapee,prenom_Tapee);
			ttel.setEditable(false);
			temail.setEditable(false);
			ttel.setText(p.getTel());
			temail.setText(p.getEmail());
			cpt = 0 ;
		}else
		{
			if (cpt == 0)
			{
				ttel.setEditable(true);
				temail.setEditable(true);
				ttel.setText("");
				temail.setText("");
				cpt++;
			}
		}
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
	public String getNb ()
	{
		return tnb.getText();
	}
	

}
