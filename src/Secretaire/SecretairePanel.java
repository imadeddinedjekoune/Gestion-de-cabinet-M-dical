package Secretaire;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Calendrier.CalendrierAffiche;
import Calendrier.Date;
import Calendrier.DaysClick;
import Classes.Personne;
import Classes.RDV;
import GUI.Frames.SceretaireFrame;
import Admin.Fun;
import Table.TableV6;
import Table.getRowValueIndiceDate;

public class SecretairePanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private CalendrierAffiche c ;
	private TableV6 t ;
	private JButton b ;
	private Classes.Date date;
	private SecretaireInfoPanel panel ;
	
	private void color()
	{
		ArrayList<Date> dateColor = new ArrayList<Date>();
		SceretaireFrame.s.loadRDV();
		ArrayList<RDV> rdvs = GUI.Frames.SceretaireFrame.s.getRdvs();
		for ( int i = 0 ; i < rdvs.size() ; i++ )
		{
			dateColor.add(new Date(rdvs.get(i).getDateDeConsultation().getAnnee(),
					rdvs.get(i).getDateDeConsultation().getMois()-1,
					rdvs.get(i).getDateDeConsultation().getJour()-1));
		}
		
		c.setTabC(dateColor);
		c.setColor();
	}
	private void init()
	{
		c = new CalendrierAffiche(java.time.LocalDate.now().getYear(),java.time.LocalDate.now().getMonthValue()-1);
		b = new JButton("add");
		panel = new SecretaireInfoPanel();
		color();		
	}
	
	private void Add()
	{
		setLayout(null);
		c.setBounds(10,80,1060,440);
		panel.setBounds(10, 10, 1060, 100);
		add(c);
		add(panel);
		
	}
	
	private void Listeners ()
	{
		
		
		c.getClick(new DaysClick() {

			@Override
			public void get(int Days, int Button)
			{
				if ( Button == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Ce Jour");
					
					date = new Classes.Date(Days,c.getM()+1,c.getA() );
					
					
					t = new TableV6(Fun.PersonneListToTableModel(SceretaireFrame.s.getRdvs(date,date)));
					
					t.update(new getRowValueIndiceDate() {
						
						@Override
						public void getVal(String str, String str2, String nom, String prenom, int indice) {
							if ( indice == 1)
							{
								SceretaireFrame.s.suprimerRDV(new RDV(
										Integer.parseInt(str2),Classes.Date.StringtoDate(str)),
										new Personne(nom,prenom));
								t.repaintTableV4(Fun.PersonneListToTableModel(SceretaireFrame.s.getRdvs(date,date)));
								InfoPersonne.t.repaintTableV4(Fun.PersonnesListToTableModel(SceretaireFrame.s.getPersonnes()));
								rp();
								SceretaireFrame.per.rep();
							}
						}
					});
					
					d.setLayout(new BorderLayout());
					d.add(t,BorderLayout.CENTER);
					d.add(b,BorderLayout.SOUTH);
					
					d.setSize(650,500); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-650)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
			        d.setVisible(true);
				}else
				{
					
					Classes.Date date = new Classes.Date(Days,c.getM()+1,c.getA() );
					JDialog d = new JDialog();
					d.setTitle(date.toString());					
					JLabel l = new JLabel("   Il Y'a : "+SceretaireFrame.s.getRdvs(date, date).size());
					l.setFont(new Font("Serif", Font.ITALIC, 20));
					d.setLayout(new BorderLayout());
					d.add(l,BorderLayout.CENTER);
					d.setSize(150,100); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-150)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-100)/2);
			        d.setVisible(true);
			        
				}
			}
		});
		
		b.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog d = new JDialog();
				d.setTitle("Ajouter Personne");
				
				JButton btn = new JButton("Save");
				SceretaireFrame.s.loadMedcin();
				int a1 = SceretaireFrame.s.getMedcin().CombienDeRdvParDate(date.toString());
				AjouterPersonne p = new AjouterPersonne(a1);
				
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						d.dispose();
						boolean b =SceretaireFrame.s.AjouterRendezVous(new Personne(p.getNom(),
								p.getPrenom(),p.getTel(),p.getEmail()),
								new RDV(Integer.parseInt(p.getNb()),date));
						if (!b)
						{
							JOptionPane.showMessageDialog(null, "Ce Rendez Vous Existe Deja", "Note", 0);
						}
						
						t.repaintTableV4(Fun.PersonneListToTableModel(SceretaireFrame.s.getRdvs(date,date)));
						InfoPersonne.t.repaintTableV4(Fun.PersonnesListToTableModel(SceretaireFrame.s.getPersonnes()));
						rp();
						SceretaireFrame.per.rep();
					}
				});
				
				d.setResizable(false);
				d.setLayout(new BorderLayout());
				d.add(p,BorderLayout.CENTER);
				d.add(btn,BorderLayout.SOUTH);
				d.setSize(250,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-250)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true);
		        
			}
		});
		
	}
	
	public SecretairePanel()
	{
		init();
		Listeners();
		Add();
	}
	
	public void rp()
	{
		removeAll();
		updateUI();
		init();
		color();
		Listeners();
		Add();
	}
	
}
