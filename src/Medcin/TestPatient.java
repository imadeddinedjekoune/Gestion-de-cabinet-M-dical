package Medcin;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Date;
import Classes.Medcin;
import Classes.Patient;
import Classes.Personne;
import Classes.RDV;
import Consultation.ConultationPanel;
import GUI.Frames.MainFrame;
import Table.TableV4;
import Table.getRowValueIndice;

public class TestPatient extends JPanel {
	private static final long serialVersionUID = 1L;
	public static TableV4 t;
	private JTextField rech ;
	private JLabel lrech ;
	private JComboBox<String> c ;
	private JLabel add , rm ;
	private Image addn = null  , addp = null ;
	private Image rmn = null  , rmp = null ;
	Medcin m ;
	
	private void init ()
	{
		// Init Photos //
		
		try 
		{
			addn = ImageIO.read(new File("res/DocPanel/addPersNormal.png"));
			addp = ImageIO.read(new File("res/DocPanel/addPersClicked.png"));
			rmn = ImageIO.read(new File("res/DocPanel/removeNormal.png"));
			rmp = ImageIO.read(new File("res/DocPanel/removeClicked.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
				
				
		m = MainFrame.medcin;
		
		t = new TableV4(Fun.PatientListToTableModel(m.getPatientConuslter())) ;
		rech = new JTextField() ;
		lrech = new JLabel("Rechreche : ") ;
		rech = new JTextField();
		String s[] = {"Nom","Prenom"};
		c = new JComboBox<String>(s);
		
		t.setBounds(0, 50, 700, 330);
		lrech.setBounds(10, 10, 100, 30);
		rech.setBounds(130, 10, 150, 25);
		c.setBounds(300, 10, 120, 25);
		
		add = new JLabel();
		add.setBounds(450, 0, 55,51);
		add.setIcon(new ImageIcon(addn));
		
		
		rm = new JLabel();
		rm.setBounds(510, 0, 55,51);
		rm.setIcon(new ImageIcon(rmn));
		
		
	}
	
	private void listenners()
	{
		
		t.update(new getRowValueIndice() {
			
			@Override
			public void getVal(String str, int indice) {
				if (indice == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Info Patient");

					Patient pat = new Patient(Integer.parseInt(str));
					pat.loadMaladies();
					pat.loadConsultation();
					AffichPanel p = new AffichPanel(pat);
					
					d.setLayout(new BorderLayout());
					d.add(p,BorderLayout.CENTER);
					d.setSize(560,500); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-560)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
			        d.setVisible(true);
				}else
				{
					
					JDialog d = new JDialog();
					d.setTitle("Info Patient");
					
					
				    Patient p = new Patient(Integer.parseInt(str));
				    p.loadMaladies();
					
					
					MedcinAffichPanel modp = new MedcinAffichPanel(p);
					MedcinAffichPanel.cpt1 = 0 ;
					MedcinAffichPanel.cpt2 = 0 ;
					
					modp.updateSaveClick(new SaveClick() {

						@Override
						public void click(int i) {
							if ( i == 1 )
							{	
								d.dispose();	
							}
						}
					});

					d.setLayout(new BorderLayout());
					d.add(modp,BorderLayout.CENTER);
					d.setSize(650,500); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-650)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
			        d.setVisible(true);
					
				}
				
			}
		});
		
		
		rech.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (c.getSelectedIndex() == 0)
				{
					t.repaintTableV4(Fun.PatientListToTableModel(m.getPatientConuslter(rech.getText(),0)));
				}else
				{
					if (c.getSelectedIndex() == 1)
					{
						t.repaintTableV4(Fun.PatientListToTableModel(m.getPatientConuslter(rech.getText(),1)));
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				
			}
		});
	
		add.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				add.setIcon(new ImageIcon(addn));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				add.setIcon(new ImageIcon(addp));
				
			}
			private Patient pat;
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// Afficher La Fenetre Qui Ajoute Un Patient //
				// Setting Up The Dialog //
				JDialog d = new JDialog();
				d.setTitle("Ajout Patient");
				
				MedcinAddingPaenl p = new MedcinAddingPaenl(MainFrame.medcin);
			
				p.updateSaveClick(new SaveClick() 
				{
					@Override
					public void click(int i) 
					{
						if (i == 1)
						{
							// Creer La Date D'aujoudhui //
							Calendar c = Calendar.getInstance();
							Date da = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1,
									c.get(Calendar.YEAR));
							// Creer La Personne A Ajouter Avec Son RDV //
							Personne nvPers = new Personne(
									p.getNom(),p.getPrenom(),p.getTel(),p.getEmail());
							RDV nvRdv = new RDV(MainFrame.medcin.
									CombienDeRdvParDate(da.toString()),da );
							
							// Ajouter Les //
							MainFrame.medcin.ajouterUnNouveauPers(nvPers,nvRdv);
							
							d.dispose();
							
							// Pour La Consultation //
							pat = new Patient(Integer.parseInt(p.getCode()));
							JDialog d2 = new JDialog();
							d2.setTitle("Consultation");
					        
							pat.loadMaladies();
					        ConultationPanel paC = new ConultationPanel(pat);
							paC.have_RDV = false ;
							
					        paC.update(new SaveClick() {

								@Override
								public void click(int i) {
									if (i == 1)
									{
										d2.dispose();
									}
								}
							});
					        
							d2.setLayout(new BorderLayout());
							d2.add(paC);
							d2.setSize(1100,550); 
					        d2.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-1200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-550)/2);
					        d2.setVisible(true);
						}
					}
				});
				
				d.setLayout(new BorderLayout());
				d.add(p,BorderLayout.CENTER);
				d.setSize(650,500); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-650)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
		        d.setVisible(true); 
			}
		});
		
		rm.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int id = Integer.parseInt((String)(t.table.getValueAt(t.table.getSelectedRow(), 0)));
				Patient p =  new Patient(id);
				MainFrame.medcin.effacerPatientDifinitivement(p);
				MainFrame.medcin.loadConsultation();
				repaintPanel();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				rm.setIcon(new ImageIcon(rmn));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				rm.setIcon(new ImageIcon(rmp));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void Add()
	{
		setLayout(null);
		add(t);
		add(lrech);
		add(rech);
		add(c);
		add(add);
		add(rm);
	}
	
	public TestPatient()
	{
		init();
		listenners();
		Add();
	}
	
	public void repaintPanel()
	{
		removeAll();
		updateUI();
		init();
		listenners();
		Add();
	}

}

