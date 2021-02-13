package Medcin;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Calendrier.TexteDate;
import CheckList.Btn;
import CheckList.CheckList;
import CheckList.CheckListRenderer;
import CheckList.CheckableItem;
import Classes.Maladie;
import Classes.Medcin;
import Classes.Patient;
import Classes.TypeMaladie;

public class MedcinAddingPaenl extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private CheckList maladieCheckList  , maladieChroniqueCheckList ;
	private JButton maladieButton , maladieChroniqueButton , save;
	private JLabel nom , prenom , datenaiss , dateinsc ,adrs , email , tel , mal , malch; 
	private JLabel codePateint , sexe , groupe;
	private JTextField tnom , tprenom , tadrs , temail , ttel , tcodePatient ;
	private TexteDate tDateNaiss , tDateInsc ;
	private JComboBox<String> c1  , c2 ;
	private Image image = null;
	private Image wallpaper = null , existe = null ;
	private JLabel Date;
	private JLabel l2 ;
	private Medcin medcin ;
	private SaveClick myC ;
	
	
	public MedcinAddingPaenl(Medcin med )
	{
		this.medcin = med ;
		InitTool(medcin.getNom());
		Init();
		Listeners();
		add();
	}
	
	private void InitTool(String n)
	{
		try 
		{
			image = ImageIO.read(new File("res/save.png"));
			wallpaper = ImageIO.read(new File("res/wallpaper.jpg"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
		
		
		Date = new JLabel("Date : "+java.time.LocalDate.now());
		Date.setFont(new Font("Serif", Font.PLAIN, 20));
		

		Date.setBounds(180, 40, 300, 20);
		
		
	}
	
	private void Init()
	{
		String s2[] = {"Homme","Femme"};
		c1 = new JComboBox<>(s2);
		
		String s3[] = {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
		c2 = new JComboBox<>(s3);
		
		System.out.println(medcin.avoirToutesLesMaladies());
		
		String str[] = Maladie.maladieToString(Maladie.MaladieAvecTypeUnique(medcin.avoirToutesLesMaladies()
				, TypeMaladie.Simple));
		
		maladieCheckList = new CheckList(str);
		
		str = Maladie.maladieToString(Maladie.MaladieAvecTypeUnique(medcin.avoirToutesLesMaladies()
				, TypeMaladie.Chronique));
		maladieChroniqueCheckList = new CheckList(str);
		
		
		maladieButton = new JButton("Malade");
		maladieChroniqueButton = new JButton("Maladie ch");
		
		
		codePateint = new JLabel("Code Patient");
		nom = new JLabel("Nom ");
		prenom = new JLabel("Prenom ");
		datenaiss = new JLabel("Date Naissance ");
		dateinsc = new JLabel("Date Inscription ");
		adrs = new JLabel("Adresse ");
		email = new JLabel("Email ");
		tel = new JLabel("Telephone ");
		mal = new JLabel("Maladie");
		malch = new JLabel("Maladiech");
		sexe = new JLabel("Sexe");
		groupe = new JLabel("GP Sanguin");
		
		// Setting Font //
		nom.setFont(new Font("Serif", Font.BOLD, 14));
		prenom.setFont(new Font("Serif", Font.BOLD, 14));
		datenaiss.setFont(new Font("Serif", Font.BOLD, 14));
		adrs.setFont(new Font("Serif", Font.BOLD, 14));
		email.setFont(new Font("Serif", Font.BOLD, 14));
		tel.setFont(new Font("Serif", Font.BOLD, 14));
		dateinsc.setFont(new Font("Serif", Font.BOLD, 14));
		
		codePateint.setFont(new Font("Serif", Font.BOLD, 14));
		mal.setFont(new Font("Serif", Font.BOLD, 14));
		malch.setFont(new Font("Serif", Font.BOLD, 14));
		groupe.setFont(new Font("Serif", Font.BOLD, 14));
		sexe.setFont(new Font("Serif", Font.BOLD, 14));
		
		
		tcodePatient = new JTextField();
		tnom = new JTextField();
		tprenom = new JTextField();
		tadrs = new JTextField();
		temail = new JTextField();
		ttel = new JTextField();
		tDateNaiss = new TexteDate();
		tDateInsc = new TexteDate();
		
		
		
		int x  = 100 ;
		codePateint.setBounds(350,13+x,100,20);
		nom.setBounds(10, 10+x, 100, 25);
		prenom.setBounds(10, 50+x, 100, 25);
		datenaiss.setBounds(10, 95+x, 100, 25);
		dateinsc.setBounds(10, 155+x, 110, 25);
		adrs.setBounds(10, 210+x, 100, 25);
		email.setBounds(10, 250+x, 100, 25);
		tel.setBounds(10, 300+x, 100, 25);
		mal.setBounds(350, 143+x, 100, 25);
		malch.setBounds(350,193+x , 100, 25);
		
		maladieButton.setBounds(450, 140+x, 100, 30);
		maladieChroniqueButton.setBounds(450, 190+x, 100, 30);
		save = new JButton("save");
		
		sexe.setBounds(350, 53+x, 100, 20);
		groupe.setBounds(350, 93+x, 100, 20);
		save.setBounds(470,260+x,130,80);
		save.setIcon(new ImageIcon(image));
		
		tcodePatient.setBounds(450, 10+x, 140, 30);
		c1.setBounds(450, 50 +x, 100, 30);
		c2.setBounds(450, 90 +x, 100, 30);
		
		
		tnom.setBounds(130, 8+x, 140, 30);
		tprenom.setBounds(130, 48+x, 140, 30);
		tDateNaiss.setBounds(130, 90+x , 140, 40);
		tDateInsc.setBounds(130, 150 +x, 140, 40);
		
		tadrs.setBounds(130, 208+x, 140, 30);
		temail.setBounds(130, 250+x, 140, 30);
		ttel.setBounds(130, 295+x, 140, 30);
		
		l2 = new JLabel("");
		
		l2.setBounds(600, 10+x, 30, 30);
		
		save.addActionListener(this);
	}
	
	private void Listeners()
	{	
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				if (!(tnom.getText().isEmpty() || tprenom.getText().isEmpty() || ttel.getText().isEmpty() ||
					tcodePatient.getText().isEmpty() || tDateNaiss.getText().isEmpty() || tDateInsc.getText().isEmpty()
					||  tadrs.getText().isEmpty()))
				{
					Patient p = new Patient(tnom.getText(),tprenom.getText(),ttel.getText(),
							temail.getText(),Integer.parseInt(tcodePatient.getText()),c2.getSelectedItem().toString(),
							tadrs.getText(),Classes.Date.StringtoDate(tDateNaiss.getText()),
							Classes.Date.StringtoDate(tDateInsc.getText()),
							c1.getSelectedItem().toString().charAt(0),null,null);			        
					
					medcin.ajouterUnNouveauPatient(p);
					
					// Maladie Simples //
					ListModel<?> model = maladieCheckList.list.getModel();
			        int n = model.getSize();
			        
			        for (int i = 0; i < n; i++) 
			        {
			        	CheckableItem item = (CheckableItem) model.getElementAt(i);
			        	if (item.isSelected()) 
			        	{
			        		medcin.assossierMaladieAuPatient(new Maladie(item.toString(),
			        				TypeMaladie.Simple), p);
			        	}
			        }
					
			        // Maladie Chronique //
			        model = maladieChroniqueCheckList.list.getModel();
			        n = model.getSize();
			      
			        for (int i = 0; i < n; i++) 
			        {
			        	CheckableItem item = (CheckableItem) model.getElementAt(i);
			        	if (item.isSelected()) 
			        	{
			        		medcin.assossierMaladieAuPatient(new Maladie(item.toString(),
			        				TypeMaladie.Chronique), p);
			        	}
			        }
			        
			        // Ajouter Une Consultation //
			        
				}else
				{
					JOptionPane.showMessageDialog(null, "Remplire Tous Les Champs", "Title", 0);
				}
			}
		});
		
		
		maladieButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				d.setTitle("Ajout");
				
				JButton b = new JButton("DONE");
				
				// Setting Up Commpenents //
				d.setLayout(new BorderLayout());
				d.add(maladieCheckList,BorderLayout.CENTER);
				d.add(b,BorderLayout.SOUTH);
				
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						d.dispose();
					}
				});
				
				d.setSize(200,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true); 
			}
		});
		
		maladieChroniqueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				d.setTitle("Ajout");
				
				JButton b = new JButton("DONE");
				
				// Setting Up Commpenents //
				d.setLayout(new BorderLayout());
				d.add(maladieChroniqueCheckList,BorderLayout.CENTER);
				d.add(b,BorderLayout.SOUTH);
				
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						d.dispose();
					}
				});
				
				d.setSize(200,300); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
		        d.setVisible(true); 
			}
		});
		
		maladieCheckList.updateI(new Btn() 
		{
			public void getButton(int b) 
			{
				if (b == 1)
				{
					// Setting Up The Dialog //
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					d.setSize(100,100); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-100)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-100)/2);
			        d.setVisible(true); 
					
					// Setting Up Commpenents //
					AjouterMaladieQlqPanel p = new AjouterMaladieQlqPanel();
					JButton btn = new JButton("Ajouter");
					
					// Adding Listteners To Btn //
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							// Sauvgarde Des Elemenets Selecter // 
							ListModel<?> model = maladieCheckList.list.getModel();
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
					        
					        // Repainting The CheckList //
					        medcin.ajouterMaladie(new Maladie(p.getText(),TypeMaladie.Simple));
					        CheckListRenderer.setChecked(Copie);
					        String str[] = Maladie.maladieToString(Maladie.MaladieAvecTypeUnique(medcin.avoirToutesLesMaladies()
									, TypeMaladie.Simple));
					        maladieCheckList.repaintPanel(str);
					        d.dispose();
						}
					});
					
					// Adding To Dialog //
					d.setLayout(new BorderLayout());
					d.add(p,BorderLayout.CENTER);
					d.add(btn,BorderLayout.SOUTH);
					
				}
			}
		});
		
		maladieChroniqueCheckList.updateI(new Btn() 
		{
			public void getButton(int b) 
			{
				if (b == 1)
				{
					// Setting Up The Dialog //
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					d.setSize(100,100); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-100)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-100)/2);
			        d.setVisible(true); 
					
					// Setting Up Commpenents //
					AjouterMaladieQlqPanel p = new AjouterMaladieQlqPanel();
					JButton btn = new JButton("Ajouter");
					
					// Adding Listteners To Btn //
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							// Sauvgarde Des Elemenets Selecter // 
							ListModel<?> model = maladieChroniqueCheckList.list.getModel();
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
					        
					        // Repainting The CheckList //
					        
							medcin.ajouterMaladie(new Maladie(p.getText(),TypeMaladie.Chronique));
					        CheckListRenderer.setChecked(Copie);
					        String str[] = Maladie.maladieToString(Maladie.MaladieAvecTypeUnique(medcin.avoirToutesLesMaladies()
									, TypeMaladie.Chronique));
					        maladieChroniqueCheckList.repaintPanel(str);
					        d.dispose();
						}
					});
					
					// Adding To Dialog //
					d.setLayout(new BorderLayout());
					d.add(p,BorderLayout.CENTER);
					d.add(btn,BorderLayout.SOUTH);
					
				}
			}
		});
		
		tcodePatient.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				try 
				{
					try 
					{
						if (Patient.existe(tcodePatient.getText()))
						{
							existe = ImageIO.read(new File("res/Cancel.png"));
						}else
						{
							existe = ImageIO.read(new File("res/True.png"));
						}	
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				catch (Exception rr)
				{
					try 
					{
						existe = ImageIO.read(new File("res/Cancel.png"));	
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				l2.setIcon(new ImageIcon(existe));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void add()
	{
		setLayout(null);
		add(nom);
		add(prenom);
		add(datenaiss);
		add(dateinsc);
		add(adrs);
		add(email);
		add(tel);
		add(mal);
		add(l2);
		
		add(tnom);
		add(tprenom);
		add(tadrs);
		add(temail);
		add(ttel);
		add(tDateNaiss);
		add(tDateInsc);
		
		add(malch);
		add(maladieChroniqueButton);
		add(maladieButton);
		
		
		add(codePateint);
		add(tcodePatient);
		add(sexe);
		add(groupe);
		
		add(c1);
		add(c2);
		add(save);
		
		add(Date);
		
	
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wallpaper, 0, 0,null);
    }
    
	
	public void setNom (String t)
	{
		tnom.setText(t);
	}
	public void setPrenom (String t)
	{
		tprenom.setText(t);
	}
	public void setTel (String t)
	{
		ttel.setText(t);
	}
	public void setEmail (String t)
	{
		temail.setText(t);
	}
	
	public String getNom()
	{
		return tnom.getText();
	}
	
	public String getDateNaiss()
	{
		return tDateNaiss.getText();
	}
	
	public String getAdresse()
	{
		return tadrs.getText();
	}
	
	public String getCode()
	{
		return tcodePatient.getText();
	}
	
	public String getPrenom()
	{
		return tprenom.getText();
	}
	
	public String getTel()
	{
		return ttel.getText();
	}
	
	public String getEmail()
	{
		return temail.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == save)
		{
			if ( myC != null )
			{
				myC.click(1);
			}
		}
	}
    
	public void updateSaveClick(SaveClick c)
	{
		myC = c ;
	}

	public void setNotNull() 
	{
		tnom.setEditable(false);
		tprenom.setEditable(false);
		tDateInsc.desactive();
		temail.setEditable(false);
		ttel.setEditable(false);
	}

	public void setDateInsc(String s) {
		tDateInsc.setText(s);
	}
	

}
