package Secretaire;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Personne;
import Classes.RDV;
import GUI.Frames.SceretaireFrame;
import Admin.*;
import Table.TableV6;
import Table.getRowValueIndiceDate;

public class PersonnePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static TableV6 table ;
	
	private JLabel recherche ;
	
	private void init()
	{
		
		
		recherche = new JLabel("Liste De Tous Les Rendez-Vous :");
		recherche.setBounds(10, 10, 700, 30);
		recherche.setFont(new Font("Serif", Font.BOLD, 24));
		
		
		table = new TableV6(Fun.PersonneListToTableModel(SceretaireFrame.s.getRdvs()));
		
		table.setBounds(10, 70, 1060, 450);
	}
	
	public PersonnePanel()
	{
		init ();
		Listener();
		add();
	}
	
	private void Listener ()
	{
		table.update(new getRowValueIndiceDate() {
			
			@Override
			public void getVal(String str, String str2, String nom, String prenom, int indice) {
				if ( indice == 1)
				{
					
					SceretaireFrame.s.suprimerRDV(new RDV(
							Integer.parseInt(str2),Classes.Date.StringtoDate(str)),
							new Personne(nom,prenom)); 
					SceretaireFrame.sec.rp();
					SceretaireFrame.s.loadRDV();
					table.repaintTableV4(Fun.PersonneListToTableModel(SceretaireFrame.s.getRdvs()));
				}
			}
		});
	}
	
	private void add()
	{
		setLayout(null);
		add(recherche);
		add(table);	
	}
	
	public void rep()
	{
		removeAll();
		updateUI();
		init();
		Listener();
		add();
	}

}
