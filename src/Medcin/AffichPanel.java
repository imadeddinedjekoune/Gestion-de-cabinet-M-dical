package Medcin;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Analyse;
import Classes.Consultation;
import Classes.Patient;
import Classes.TypeMaladie;
import Consultation.AfficherConsultation;
import GUI.Frames.MainFrame;
import SQLite.SQLite;
import Table.TablePaire;
import Table.TableV2;
import Table.TableV3;
import Table.gerRowValue;

public class AffichPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JLabel nom , prenom , age , sexe , dateN , dateI , tel , email , group , adr , analyse ;
	private JLabel nbCons , AffichCons ;
	private Patient p;
	private Image imageSexe = null ;
	private Image wallPaper ;
	private JButton b , b2 , b3;
	private JComboBox<String> c ;
	
	public AffichPanel( Patient pat )
	{
		p = pat ;
		b = new JButton("Afficher");
		b2 = new JButton("Afficher");
		b3 = new JButton("Afficher");
		String [] s = {"Maladie","Maladie Chronique"};
		c = new JComboBox<String>(s);
		
		
		nom = new    JLabel("Nom                      :  "+p.getNom());
		adr = new JLabel("Adresse : "+p.getAdresse());
		prenom = new JLabel("Prenom                :  "+p.getPrenom());
		age = new JLabel("Age                        :  "+p.getAge(p.getDateNaiss()));
		sexe = new JLabel("Sexe                      :  "+p.getSexe());
		dateN = new JLabel("Date Naissance :  "+p.getDateNaiss().toString());
		dateI = new JLabel("Date Inscription :  "+p.getDateInsc().toString());
		tel = new JLabel("Telephone           :  "+p.getTel());
		email = new JLabel("Email                     :  "+p.getEmail());
		group = new JLabel("Grp                         :  "+p.getGroupSanguin());
		nbCons = new JLabel("nombre De Consultations :  "+p.getConsultations(MainFrame
				.medcin).size() );
		AffichCons = new JLabel("Consultation : ");
		analyse = new JLabel("analyses");
		
		nom.setBounds(18,40+130,300,20);
		prenom.setBounds(18,80+130,300,20);
		age.setBounds(18,120+130,300,20);
		sexe.setBounds(18,160+130,300,20);
		dateN.setBounds(240,10,300,20);
		dateI.setBounds(240,50,300,20);
		tel.setBounds(240,90,300,20);
		email.setBounds(240,130,300,20);
		group.setBounds(240,170,300,20);
		nbCons.setBounds(240,210,300,20);
		adr.setBounds(240,290,300,20);
		c.setBounds(240,330,140,20);
		b.setBounds(400,330,90,20);
		AffichCons.setBounds(240,250,90,20);
		b2.setBounds(400,250,90,20);
		
		b3.setBounds(400,370,90,20);
		analyse.setBounds(240,370,90,20);
		// Lecture D'image //
		
		try 
		{
			wallPaper = ImageIO.read(new File("res/wallpaper.jpg"));
			if(p.getSexe() == 'H')
			{
				imageSexe = ImageIO.read(new File("res/Homme.png"));
			}else
			{
				imageSexe = ImageIO.read(new File("res/Femme.png"));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Fin 			   //
		
		
		// Listener //
		
		// Table Des Maladies De Patient //
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog d = new JDialog();
				d.setTitle("Ajout");
				TablePaire table = new TablePaire(null) ;
			
				if (c.getSelectedIndex() == 0)
				{
					table = new TablePaire(Fun.maladiesListToTableModel(p.getMaladies(),TypeMaladie.Simple));
					
				}else
				{
					table = new TablePaire(Fun.maladiesListToTableModel(p.getMaladies(),TypeMaladie.Chronique));
				}
				
				
				d.setLayout(new BorderLayout());
				d.add(table);
				d.setSize(400,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-400)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true);
			}
		});
		
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				JDialog d = new JDialog();
				d.setTitle("Ajout");
				TableV2 table = null ;
				table = new TableV2(Fun.ConsultationListToTableModel(p.getConsultations(MainFrame.medcin)));
				
				
				table.update(new gerRowValue() {
					
					
					@Override
					public void getVal(String str) 
					{
						
						JDialog con = new JDialog();
						con.setTitle("Con");
						
						Consultation c = MainFrame.medcin.getConsult(str);
						c.loadAnalyse();
						c.loadMaladies();
						c.loadOrdonance();
						AfficherConsultation consult = new AfficherConsultation(c);
						
						con.setLayout(new BorderLayout());
						con.add(consult);
						con.setResizable(false);
						con.setSize(300,290); 
						con.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-290)/2);
						con.setVisible(true);
						
					}
				});
				
				d.setLayout(new BorderLayout());
				d.add(table);
				d.setSize(600,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-600)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true);
				
			}
			
		});
		
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				JDialog d = new JDialog();
				d.setTitle("Analyse");
				
				TableV3 t = new TableV3(Fun.AnalyseListToTableModel(MainFrame.medcin.lesAnalyseDePateint(p)));
				
				
				t.update(new gerRowValue() {
					
					@Override
					public void getVal(String str)
					{
						JDialog da = new JDialog();
						da.setTitle("AnalyseModif");
						
						int row = t.table.getSelectedRow();
						
						
						ArrayList<Analyse> an = SQLite.selectAnalyse("WHERE idanalyse = '"+t.table.getValueAt(row, 0)+"' "
								+ "and idconsult = '"+t.table.getValueAt(row, 3)+"'",(String)t.table.getValueAt(row, 3));
						
						
						AnalysesPanel panel = new AnalysesPanel(an.get(0));
						JButton boutonmodif = new JButton("Done");
						
						da.setLayout(new BorderLayout());
						da.add(panel,BorderLayout.CENTER);
						da.add(boutonmodif,BorderLayout.SOUTH);
						
						boutonmodif.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) 
							{
								an.get(0).changeAnalyse(panel.getComment(), panel.getResult());
								t.repaintTable(Fun.AnalyseListToTableModel(p.getAnalyses()));
								da.dispose();
							}
						});
						
						da.setResizable(false);
						da.setSize(300,285); 
						da.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
						da.setVisible(true);
					
					}
				});
				
				d.setLayout(new BorderLayout());
				d.add(t);
				d.setResizable(false);
				d.setSize(300,265); 
				d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
				d.setVisible(true);
				
			}
		});
		
		// End Lis  //
		
		
		setLayout(null);
		
		add(nom);
		add(prenom);
		add(age);
		add(sexe);
		add(dateN);
		add(dateI);
		add(tel);
		add(email);
		add(group);
		add(nbCons);
		add(adr);
		add(c);
		add(b);
		add(AffichCons);
		add(b2);
		add(b3);
		add(analyse);
	}
	
	protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(wallPaper, 0, 0, null);
	        if (p.getSexe() == 'H')
	        {
	        	g.drawImage(imageSexe, 10, 10, null);
	        }else
	        {
	        	g.drawImage(imageSexe, 35, 35, null);
	        }
	        g.draw3DRect(15, 15, 130, 130, true);
	        
	    }
	
	
	
}
