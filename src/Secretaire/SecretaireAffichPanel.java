package Secretaire;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Classes.Medcin;
import Classes.Patient;
import Classes.Personne;

public class SecretaireAffichPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JLabel nom , prenom , email , tel ;
	private JLabel patient , NomMed;
	private JComboBox<String> c ;
	
	
	public SecretaireAffichPanel(Personne p)
	{
		nom = new JLabel("nom : "+p.getNom());
		prenom = new JLabel("prenom : "+p.getPrenom());
		email = new JLabel("email : "+p.getEmail());
		tel = new JLabel("tel : "+p.getTel());
		
		boolean ex = Patient.Ex(p.getNom(), p.getPrenom()) != null;
		patient = new JLabel("il est patient : "+ex);
		
		
		if (ex)
		{
			Patient pat = new Patient(p.getNom(), p.getPrenom());
			ArrayList< Medcin > meds = pat.getMedcinExaminee();
			String Name[] = new String[meds.size()];
			
			for (int i = 0 ; i < meds.size() ; i++ )
			{
				Name[i] = new String(meds.get(i).getNom()+" "+meds.get(i).getPrenom());
			}
			
			c = new JComboBox<String>(Name);
			NomMed = new JLabel("Nom des Medcins Consulter : ");
		}
		
		setLayout(null);
		
		nom.setBounds(10, 10, 300, 30);
		prenom.setBounds(10, 50, 300, 30);
		email.setBounds(10, 90, 300, 30);
		tel.setBounds(10, 130, 300, 30);
		patient.setBounds(10, 170 , 300, 30);
		if (ex)
		{
			c.setBounds(100, 260 , 150, 30);
			NomMed.setBounds(10, 215 , 300, 30);
			NomMed.setFont(new Font("Serif", Font.BOLD, 15));
			NomMed.setForeground(Color.black);
		}
		
		nom.setFont(new Font("Serif", Font.BOLD, 15));
		nom.setForeground(Color.black);
		
		prenom.setFont(new Font("Serif", Font.BOLD, 15));
		prenom.setForeground(Color.black);
		
		email.setFont(new Font("Serif", Font.BOLD, 15));
		email.setForeground(Color.black);
		
		tel.setFont(new Font("Serif", Font.BOLD, 15));
		tel.setForeground(Color.black);
		
		patient.setFont(new Font("Serif", Font.BOLD, 15));
		patient.setForeground(Color.black);
		
		
		add(nom);
		add(prenom);
		add(email);
		add(tel);
		add(patient);
		if (ex)
		{
			add(c);
			add(NomMed);
		}
		
	}
	
	
}
