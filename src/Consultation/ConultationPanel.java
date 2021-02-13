package Consultation;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import Calendrier.TexteDate;
import CheckList.Btn;
import CheckList.CheckList;
import CheckList.CheckListModel;
import CheckList.CheckListRenderer;
import CheckList.CheckableItem;
import Classes.Analyse;
import Classes.Consultation;
import Classes.Date;
import Classes.Details;
import Classes.Maladie;
import Classes.Medcin;
import Classes.Medicament;
import Classes.Patient;
import Classes.TypeMaladie;
import GUI.Frames.MainFrame;
import Medcin.AgendaTest;
import Medcin.AjouterMaladieQlqPanel;
import Medcin.Fun;
import Medcin.SaveClick;
import Medcin.TestPatient;
import Table.TableMouseListener;


public class ConultationPanel extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	private JTextField tpoid , ttaille , ttention , tdiabete , tprix ; 
	private JLabel poid , taille , tention , diabete , date , prix , lanalyse;
	private JLabel nom , prenom , observation , diag , medicament, Geuir ;
	private TexteDate tdate ; 
	private JTextArea obs ;
	private JScrollPane pane ;
	private JComboBox<String> c , c2 ;
	private JButton add , addMed , addG , save ;
	@SuppressWarnings("unused")
	private Image addBtn = null , image = null, wallpaper = null ;
	private CheckList MaladieDiag , MaladieChDaig   ;
	private CheckListModel MaladieGuérie , MaladieGuérieCh , MedicamentCheckList ;
	private boolean B = true;
	private JButton addDetails  , affichanalyse;
	private JScrollPane scrollpane , scrollpaneAnalyse ;
	private JTable t , tanalyse ;
	private DefaultTableModel modele ;
	private Patient patient ;
	private Medcin med = MainFrame.medcin ;
	private ArrayList<Medicament> meds = new ArrayList<Medicament>();
	public int nbConsult ;
	private SaveClick myC;
	private JPopupMenu popupMenu = new JPopupMenu();
	public Consultation consult = null ;
	public boolean have_RDV = true ;

	private JMenuItem menuItemAdd = new JMenuItem("Add");


	private JMenuItem menuItemRemove = new JMenuItem("Supp");
	
	public void update(SaveClick c)
	{
		myC = c ;
	}
	
	private void Listenners (Patient P)
	{
		
		obs.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (B)
				{
					B = false ;
					obs.setText("");
					Font font = obs.getFont();
					font = new Font("Serif", Font.PLAIN, 16);
			        obs.setFont(font);
			        obs.setForeground(Color.BLACK);
				}
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
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c.getSelectedIndex() == 0)
				{
					JDialog d = new JDialog();
					JButton done = new JButton("Done");
					
					d.setTitle("Ajout");
					d.setLayout(new BorderLayout());
					d.add(MaladieDiag,BorderLayout.CENTER);
					d.add(done,BorderLayout.SOUTH);
					
					
					
					done.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();	
						}
					});
					
					d.setSize(200,300); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
			        d.setVisible(true); 
					
					
				}else
				{
					JDialog d = new JDialog();
					JButton done = new JButton("Done");
					
					d.setTitle(" Ch");
					d.setLayout(new BorderLayout());
					d.add(MaladieChDaig,BorderLayout.CENTER);
					d.add(done,BorderLayout.SOUTH);
					
					done.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();
						}
					});
					
					d.setSize(200,300); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
			        d.setVisible(true); 
				}
			}
		});
		
		MaladieChDaig.updateI(new Btn() 
		{

			@Override
			public void getButton(int b) {
				if ( b == 1 )
				{
					// Ajouter Une Maladie Pour Cette Personne //
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					d.setSize(100,100); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-100)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-100)/2);
			        d.setVisible(true); 
			        
			        // Setting Up Commpenents //
					AjouterMaladieQlqPanel p = new AjouterMaladieQlqPanel();
					JButton btn = new JButton("Ajouter");
					
					d.add(p,BorderLayout.CENTER);
					d.add(btn,BorderLayout.SOUTH);
					
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							// Sauvgarde Des Elemenets Selecter // 
							ListModel<?> model = MaladieChDaig.list.getModel();
					        int n = model.getSize();
					        ArrayList<Boolean> Copie = new ArrayList<Boolean>();
					        
					        for (int i = 0; i < n; i++) 
					        {
					        	CheckableItem item = (CheckableItem) model.getElementAt(i);
					        	if (item.isSelected()) 
					        	{
					        		Copie.add(true);
					        	}else
					        	{
					        		Copie.add(false);
					        	}
					        }
					        
					        // Ajouter La Maladie Au Patient //
					        med.ajouterMaladie(new Maladie(p.getText(), TypeMaladie.Chronique));
					        med.assossierMaladieAuPatient(new Maladie(p.getText(), TypeMaladie.Chronique),patient);
					        
					        // Repainting The CheckList //
					        
					        CheckListRenderer.setChecked(Copie);
					        String str[] = Maladie.maladieToString(Maladie.MaladieAvecTypeUnique(P.getMaladies(), TypeMaladie.Simple));
					        MaladieChDaig.repaintPanel(str);
					        MaladieGuérieCh.repaintPanel(str);
					        d.dispose();
						}
					});
				}
			}
			
			
		});
		
		MaladieDiag.updateI(new Btn() {

			@Override
			public void getButton(int b) {
				if ( b == 1 )
				{
					// Ajouter Une Maladie Pour Cette Personne //
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					d.setSize(100,100); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-100)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-100)/2);
			        d.setVisible(true); 
			        
			        // Setting Up Commpenents //
					AjouterMaladieQlqPanel p = new AjouterMaladieQlqPanel();
					JButton btn = new JButton("Ajouter");
					
					d.add(p,BorderLayout.CENTER);
					d.add(btn,BorderLayout.SOUTH);
					
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							// Sauvgarde Des Elemenets Selecter // 
							ListModel<?> model = MaladieDiag.list.getModel();
					        int n = model.getSize();
					        ArrayList<Boolean> Copie = new ArrayList<Boolean>();
					        
					        for (int i = 0; i < n; i++) 
					        {
					        	CheckableItem item = (CheckableItem) model.getElementAt(i);
					        	if (item.isSelected()) 
					        	{
					        		Copie.add(true);
					        	}else
					        	{
					        		Copie.add(false);
					        	}
					        }
					        
					        // Ajouter La Maladie Au Patient //
					        med.ajouterMaladie(new Maladie(p.getText(), TypeMaladie.Simple));
					        med.assossierMaladieAuPatient(new Maladie(p.getText(), TypeMaladie.Simple),patient);
					        
					        // Repainting The CheckList //
					        
					        CheckListRenderer.setChecked(Copie);
					        String str[] = Maladie.maladieToString(Maladie.MaladieAvecTypeUnique(P.getMaladies(), TypeMaladie.Simple));
					        MaladieDiag.repaintPanel(str);
					        MaladieGuérie.repaintPanel(str);
					        d.dispose();
						}
					});
				}
			}
		});
		
		addG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c2.getSelectedIndex() == 0)
				{
					JDialog d = new JDialog();
					JButton done = new JButton("Done");
					
					d.setTitle("Ajout");
					d.setLayout(new BorderLayout());
					d.add(MaladieGuérie,BorderLayout.CENTER);
					d.add(done,BorderLayout.SOUTH);
					
					done.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();
						}
					});
					
					d.setSize(200,300); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
			        d.setVisible(true); 
					
					
				}else
				{
					JDialog d = new JDialog();
					JButton done = new JButton("Done");
					
					d.setTitle(" Ch");
					d.setLayout(new BorderLayout());
					d.add(MaladieGuérieCh,BorderLayout.CENTER);
					d.add(done,BorderLayout.SOUTH);
					
					done.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();
						}
					});
					
					d.setSize(200,300); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
			        d.setVisible(true); 
				}
			}
		});
		
		addMed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				JButton done = new JButton("Done");
				
				d.setTitle("Ajout");
				d.setLayout(new BorderLayout());
				d.add(MedicamentCheckList,BorderLayout.CENTER);
				d.add(done,BorderLayout.SOUTH);
				
				done.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						d.dispose();
						ArrayList<String> arr = CheckList.Print(MedicamentCheckList);
						for (int i = 0 ; i < arr.size() ; i++ )
						{
							boolean existe = false ;
							for ( int j = 0 ; j < meds.size() ; j++ )
							{
								if( arr.get(i) == meds.get(j).getNomMed())
								{
									existe = true ;
								}
							}
							if(!existe)
							{
								meds.add(new Medicament(arr.get(i),new Details(0,"")));
							}
						}
						
						for (int i = 0 ; i < meds.size() ; i++ )
						{
							boolean existe = false ;
							for (int j = 0 ; j < arr.size() ; j++ )
							{
								if (meds.get(i).getNomMed() == arr.get(j) )
								{
									existe = true ;
									break ;
								}
							}
							if(!existe)
							{
								meds.remove(i);
							}
						}

					}
				});
				
				d.setSize(200,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true); 
				
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			private String nom2;

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				float poid = 0 , taille = 0 , tention = 0 , diabete = 0 , prix = 0  ;
				String date = null , observation = null ;
				
				
				
				try {
					poid = Float.parseFloat(tpoid.getText()) ;
					taille = Float.parseFloat(ttaille.getText()) ;
					tention = Float.parseFloat(ttention.getText()) ;
					diabete = Float.parseFloat(tdiabete.getText()) ;
					prix = Float.parseFloat(tprix.getText()) ;
					date = tdate.getText();
					
					ArrayList<String> MaldD = CheckList.Print(MaladieDiag);
					ArrayList<String> MaldChD = CheckList.Print(MaladieChDaig);
					ArrayList<String> MalG = CheckList.Print(MaladieGuérie);
					ArrayList<String> MalChG = CheckList.Print(MaladieGuérieCh);
					
					if (B)
					{
						observation ="";
					}else
					{
						observation = obs.getText(); 
					}
					
					Date d = Date.StringtoDate(date);
					// Ajouter Les Premier Info //
					if (!have_RDV)
					{
						nbConsult = MainFrame.medcin.CombienDeRdvParDate (date) -1 ;
					}else
					{
						nbConsult = Integer.parseInt((String) AgendaTest.t.table.getValueAt(AgendaTest.rowselected, 5));
					}
					consult = new Consultation(""+d.getAnnee()+""+
							d.getMois()+""+d.getJour()+""+nbConsult, observation, prix, poid, taille, tention, diabete, 
							Date.StringtoDate(date),null,null);
					
					
					// Associer La Consultation Au Patient // 
					med.addConsultationAuPatient(consult, P);
					
					ArrayList< Maladie > MalDList = new ArrayList<Maladie>();
					for ( int i = 0 ; i < MaldD.size() ; i++ )
					{
						MalDList.add(new Maladie(MaldD.get(i), TypeMaladie.Simple));
					}
					
					for ( int i = 0 ; i < MaldChD.size() ; i++ )
					{
						MalDList.add(new Maladie(MaldChD.get(i), TypeMaladie.Chronique));
					}
					
					consult.setMaladies(MalDList);
					consult.saveMaladies();
					consult.saveOrdonance(meds);
					
				
					ArrayList<Analyse> ana = new ArrayList<Analyse>();
					for (int i = 0 ; i < tanalyse.getRowCount() ; i++)
					{
						nom2 = ((String)(tanalyse.getValueAt(i, 0)));
						String resultas = ((String)(tanalyse.getValueAt(i, 1)));
						String commentaire = ((String)(tanalyse.getValueAt(i, 2)));
						
						
						if ( (nom2 == null)||(!(nom2.equals(""))) )
						{
							if ((resultas == null)||(resultas.isEmpty()))
							{
								resultas = new String("Rien");
							}
							
							if (commentaire == null ||(commentaire.isEmpty()))
							{
								commentaire = new String("Rien");
							}
							
							ana.add(new Analyse(nom2, resultas, commentaire, consult));
						}
						
					}
					
					consult.setAnalyses(ana);
					
					consult.saveAnalyse();
					
					ArrayList<Maladie> ger = new ArrayList<Maladie>();
					for ( int i = 0 ; i < MalG.size() ; i++ )
					{
						ger.add(new Maladie(MalG.get(i), TypeMaladie.Simple));
					}
					for ( int i = 0 ; i < MalChG.size() ; i++ )
					{
						ger.add(new Maladie(MalChG.get(i), TypeMaladie.Chronique));
					}
					
					
					med.gerire(ger, P);
					
					JOptionPane.showMessageDialog(null, "Consultation Ajouter", "Fin", 1);
					
					MainFrame.medcin.loadConsultation();
					TestPatient.t.repaintTableV4(Fun.PatientListToTableModel(MainFrame.medcin.getPatientConuslter()));
					if (AgendaTest.dateFirst != null && AgendaTest.DateEnd != null)
					{
						AgendaTest.t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.
								medcin.getSecretaire().getRdvs(AgendaTest.dateFirst,AgendaTest.DateEnd)));
					}else
					{
						AgendaTest.t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.
								medcin.getSecretaire().getRdvs()));
					}
					
				} catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Erreur : "+e2, "", 0);
				}
				
				
				
			}
		});
		
		
		addDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JDialog d = new JDialog();
				d.setTitle("Ajout");
				
				JButton bsave = new JButton("save");
				int length = meds.size();
				Object data[][] = new String[length][3];
				for ( int i = 0 ; i < length ; i++ )
				{
					data[i][0] = meds.get(i).getNomMed();
					data[i][1] = ""+meds.get(i).getD().getDose();
					data[i][2] = meds.get(i).getD().getDure();
				}
				String columns [] = {"Nom","Dose","Duree"};
				
				
				t = new JTable(data,columns);
				scrollpane = new JScrollPane(t);
				
				
				d.setLayout(new BorderLayout());
				d.add(scrollpane,BorderLayout.CENTER);
				d.add(bsave,BorderLayout.SOUTH);
				
				bsave.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						int n = meds.size();
						meds = new ArrayList<Medicament>();
						for (int i = 0 ; i < n ; i++ )
						{
							String d = (String)(t.getValueAt(i, 2));
							Medicament mtest = new Medicament((String)(t.getValueAt(i, 0)), new Details(Float.parseFloat((String)(t.getValueAt(i, 1))), d));
							meds.add(mtest);
						}
						d.dispose();
					}
				});
				
				d.setSize(300,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true); 
			}
		});
		
		affichanalyse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JDialog d = new JDialog();
				d.setTitle("Ajout");
				
				
				d.setLayout(new BorderLayout());
				scrollpaneAnalyse = new JScrollPane(tanalyse);
				d.add(scrollpaneAnalyse,BorderLayout.CENTER);
				
				d.setSize(300,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true); 
			}
		});
	
	}
	
	private void init (Patient p)
	{ 
		// ---------------------------------------------------------------------------------------------------//
		
		// Init Personne //
		
		patient = p ;
		
		try 
		{
			addBtn = ImageIO.read(new File("res/addico.png"));
			image = ImageIO.read(new File("res/save.png"));
			wallpaper = ImageIO.read(new File("res/wallpaper.jpg"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		// CheckList //
		
		
		MaladieDiag = new CheckList(Maladie.maladieToString(Maladie.MaladieAvecTypeUnique
				(patient.getMaladies(),TypeMaladie.Simple)));
		
		MaladieChDaig = new CheckList(Maladie.maladieToString(Maladie.MaladieAvecTypeUnique
				(patient.getMaladies(),TypeMaladie.Chronique)));
		
		MaladieGuérie = new CheckListModel(Maladie.maladieToString(Maladie.MaladieAvecTypeUnique
				(patient.getMaladies(),TypeMaladie.Simple)));
		
		MaladieGuérieCh = new CheckListModel(Maladie.maladieToString(Maladie.MaladieAvecTypeUnique
				(patient.getMaladies(),TypeMaladie.Chronique)));
		
		
		MedicamentCheckList = new CheckListModel(Medicament.maladieToString(Medicament.getAllStock()));
		
		// TextField //
		
		String s [] = {"Maladie","Maladie Chronique"};
		Geuir = new JLabel("MaladieGuérie : ");
		add = new JButton("");
		addMed = new JButton("");
		save = new JButton("save");
		addG = new JButton("");
		c = new JComboBox<String>(s);
		c2 = new JComboBox<String>(s);
		obs = new JTextArea();
		tpoid = new JTextField();
		ttaille = new JTextField();
		ttention = new JTextField();
		tdiabete = new JTextField();
		tprix = new JTextField();
		tdate = new TexteDate();
		pane = new JScrollPane(obs);
		t = new JTable();
		addDetails = new JButton("Details");
		scrollpane = new JScrollPane(t);
		
		// Labels //
		
		medicament = new JLabel("Medicament : ");
		poid = new JLabel("Poid : ");
		taille = new JLabel("Taille : ");
		tention = new JLabel("Tention : ");
		diabete = new JLabel("Diabete : ");
		prix = new JLabel("Prix : ");
		date = new JLabel("Date : "); 
		nom = new JLabel("Nom      :  "+p.getNom());
		prenom = new JLabel("Prenom :  "+p.getPrenom());
		observation = new JLabel("Observation :");
		diag = new JLabel("Maladie Diagnosqué : ");
		
		affichanalyse = new JButton("Afficher");
		
		lanalyse = new JLabel("Analyse");
		
		int x = 30 , y = 90 ;
		
		tpoid.setBounds(120+x, 10+y, 140, 30);
		ttaille.setBounds(120+x, 50+y, 140, 30);
		ttention.setBounds(120+x, 90+y, 140, 30);
		tdiabete.setBounds(120+x, 140+y, 140, 30);
		tprix.setBounds(120+x, 190+y, 140, 30);
		tdate.setBounds(120+x, 240+y, 140, 40);
		
		
		nom.setBounds(50, 10, 200, 30);
		nom.setFont(new Font("Serif", Font.BOLD, 17));
		prenom.setBounds(50, 40, 200, 30);
		prenom.setFont(new Font("Serif", Font.BOLD, 17));
		
		poid.setBounds(10+x, 10+y, 100, 30);
		taille.setBounds(10+x, 50+y, 100, 30);
		tention.setBounds(10+x, 90+y, 100, 30);
		diabete.setBounds(10+x, 140+y, 100, 30);
		prix.setBounds(10+x, 190+y, 100, 30);
		date.setBounds(10+x, 240+y, 100, 30);
		observation.setBounds(700, 100, 100, 30);
		lanalyse.setBounds(350, 290, 100, 30);
		affichanalyse.setBounds(475, 290, 140, 30);
		
		
		pane.setBounds(800, 100, 220, 260);
		
		
		diag.setBounds(350, 100, 150, 30);
		c.setBounds(475, 100, 110, 30);
		add.setBounds(600, 100, 32, 32);
		add.setIcon(new ImageIcon(addBtn));
		
		Geuir.setBounds(350, 160, 150, 30);
		c2.setBounds(475, 160, 110, 30);
		addG.setBounds(600, 160, 32, 32);
		addG.setIcon(new ImageIcon(addBtn));
		
		addMed.setBounds(600, 220, 32, 32);
		addMed.setIcon(new ImageIcon(addBtn));
		medicament.setBounds(350, 220, 100, 30);
		addDetails.setBounds(475, 220, 110, 30);
		
		
		tdate.setText(java.time.LocalDate.now().toString());
		
		
		save.setBounds(850, 380, 130, 100);
		save.setIcon(new ImageIcon(image));
		save.addActionListener(this);
		//-------------------------------- Table D'Analyse -----------------------------------------//
	
        String[] columnNames = new String[] {"Nom", "Resultat", "Commentaire"};
        String[][] rowData = new String[][] {
            {"", "", ""}
        };
         
        modele = new DefaultTableModel(rowData, columnNames);
        tanalyse = new JTable(modele);
        popupMenu = new JPopupMenu();
        menuItemAdd.addActionListener(this);
        menuItemRemove.addActionListener(this);
        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        tanalyse.setComponentPopupMenu(popupMenu);
        tanalyse.addMouseListener(new TableMouseListener(tanalyse));
		
		
	}
	
	private void add()
	{
		setLayout(null);
		
		add(lanalyse);
		add(affichanalyse);
		
		add(save);
		add(addMed);
		add(c2);
		add(Geuir);
		add(addG);
		add(medicament);
		add(add);
		add(c);
		add(diag);
		add(poid);
		add(taille);
		add(tention);
		add(diabete);
		add(prix);
		
		add(addDetails);
		
		add(tpoid);
		add(ttaille);
		add(ttention);
		add(tdiabete);
		add(tprix);
		
		add(nom);
		add(prenom);
		
		add(date);
		add(tdate);
		
		obs.setText("Si Aucaune maladie \nDonc Ecrire Observation\nPar Ex : Simple Fievre .");
		Font font = obs.getFont();
		font = new Font("Segoe Script", Font.BOLD, 16 );
        obs.setFont(font);
        obs.setForeground(Color.BLUE);
        //float size = font.getSize() + 5.0f;
		//obs.setFont( font.deriveFont(size) );
		
		//obs.setFont( font.deriveFont(size) );
		
		
		
		add(pane);
		add(observation);
		
	}
	
	public ConultationPanel(Patient p)
	{
		init(p);
		Listenners(patient);
		add();
	}
	
	protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
       // g.drawImage(wallpaper, 0, 0,null);
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == menuItemAdd) {
            addNewRow();
        } else if (e.getSource() == menuItemRemove) 
        {
            if (tanalyse.getRowCount() > 1)
            {
            	removeCurrentRow();
            }
        }
		if (e.getSource() == save)
		{
			if(myC != null)
			{
				myC.click(1);
			}
			
		}
	}
	
	 private void addNewRow() 
	 {
		 modele.addRow(new String[0]);
	 }
	     
	 private void removeCurrentRow() 
	 {
	        int selectedRow = tanalyse.getSelectedRow();
	        modele.removeRow(selectedRow);
	 }

	public void setDateConsult(String s) {
		tdate.setText(s);
		tdate.desactive();
	}

	
}
