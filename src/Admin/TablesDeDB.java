package Admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Classes.Admin;
import Medcin.Fun;
import SQLite.SQLite;
import Table.TablePaire;

public class TablesDeDB  extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTabbedPane table ;
	private TablePaire T[] ;
	private JButton update ;
	private String Tables_Names[] = {"Personne","Analyse","Consultation",
			"Diagnostique","Malade_de","Maladie","Medcin","Medicament",
			"Patient","RDV","Secretaire","Traitement"};
	
	public TablesDeDB()
	{
		update = new JButton("Refrech");
		table = new JTabbedPane();
		T = Admin.getTables();
		
		for ( int i = 0 ; i < T.length ; i++ )
		{
			table.add(Tables_Names[i],T[i]);
		}
		
		setLayout(new BorderLayout());
		
		add(table,BorderLayout.CENTER);
		add(update,BorderLayout.SOUTH);
		
		update.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == update)
		{
			int i =  table.getSelectedIndex() ;
			switch (i)
			{
			case 0 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TablePersonne()));
				break ;
				
			case 1 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableAnalyse()));
				break ;
				
			case 2 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableConsultation()));
				break ;
				
			case 3 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableDiagnostique()));
				break ;
				
			case 4 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableMalade_de()));
				break ;
				
			case 5 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableMaladie()));
				break ;
				
			case 6 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableMedcin()));
				break ;
				
			case 7 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableMedicament()));
				break ;
				
			case 8 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TablePatient()));
				break ;
				
			case 9 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableRDV()));
				break ;
				
			case 10 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableSecretaire()));
				break ;
				
			case 11 :
				T[i].repaintTable(Fun.buildTableModel(SQLite.select_TableTraitement()));
				break ;
			}
			
		}
	}

}
