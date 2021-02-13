package Stock;

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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Maladie;
import Classes.Medicament;
import Classes.TypeMaladie;
import GUI.Frames.MainFrame;
import Medcin.Fun;
import SQLite.SQLite;
import Table.TableV1;
import Table.gerRowValue;

public class MaladiePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image wallpaper = null , Ico = null ;
	private JLabel Bienvenu , Date;
	private JLabel recherche;
	private JComboBox<String> c ;
	private JButton b ;
	private TableV1 table ;
	private JTextField rech ;
	
	public MaladiePanel(String nom )
	{
		InitTool(nom);
		Init();
		Listenners();
		add();
	}
	
	private void Listenners() 
	{
		
		rech.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				ArrayList<Maladie> arrm = MainFrame.medcin.avoirToutesLesMaladies();
				ArrayList<Maladie> arrmaff = new ArrayList<Maladie>();
				
				for ( int i = 0 ; i < arrm.size() ; i++ )
				{
					if (arrm.get(i).getNomMaladie().length() >= rech.getText().length())
					{
						String s = arrm.get(i).getNomMaladie().substring(0, rech.getText().length());
						if (s.equals(rech.getText()))
						{
							arrmaff.add(arrm.get(i));
						}
					}
				}
				
				if (c.getSelectedIndex() == 0)
				{
					table.repaintTablevV1(Fun.maladiesListToTableModel(arrmaff, TypeMaladie.Simple));
				}else
				{
					if (c.getSelectedIndex() == 1)
					{
						table.repaintTablevV1(Fun.maladiesListToTableModel(arrmaff, TypeMaladie.Chronique));
					}else
					{
						ArrayList<Medicament> Mt = Medicament.getAllStock();
						ArrayList<Medicament> Maff = new ArrayList<Medicament>();
						
						for ( int i = 0 ; i < Mt.size() ; i++ )
						{
							if (Mt.get(i).getNomMed().length() >= rech.getText().length())
							{
								String s = Mt.get(i).getNomMed().substring(0, rech.getText().length());
								if (s.equals(rech.getText()))
								{
									Maff.add(Mt.get(i));
								}
							}
						}
						table.repaintTablevV1(Fun.MedicamentListToTableModel(Maff));
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
						
						if (c.getSelectedIndex() == 0)
						{
							MainFrame.medcin.ajouterMaladie(new Maladie(p.getText(), TypeMaladie.Simple));
							table.repaintTablevV1(Fun.maladiesListToTableModel(MainFrame.medcin.avoirToutesLesMaladies(),
									TypeMaladie.Simple));
						}else
						{
							if (c.getSelectedIndex() == 1)
							{
								MainFrame.medcin.ajouterMaladie(new Maladie(p.getText(), TypeMaladie.Chronique));
								table.repaintTablevV1(Fun.maladiesListToTableModel(MainFrame.medcin.avoirToutesLesMaladies(),
										TypeMaladie.Chronique));
							}else
							{
								SQLite.insertTableMedicament(p.getText());
								table.repaintTablevV1(Fun.MedicamentListToTableModel(Medicament.getAllStock()));
							}
						}
						d.dispose();
					}
					
				});
				
			}
		});
		
		table.update(new gerRowValue() {

			@Override
			public void getVal(String str) {
				if (c.getSelectedIndex() == 0)
				{
					MainFrame.medcin.effacerMaladie_Medicament(str, 0);
					
					table.repaintTablevV1(Fun.maladiesListToTableModel
							(MainFrame.medcin.avoirToutesLesMaladies(), TypeMaladie.Simple));
				}else
				{
					if (c.getSelectedIndex() == 1)
					{
						MainFrame.medcin.effacerMaladie_Medicament(str, 0);
						table.repaintTablevV1(Fun.maladiesListToTableModel
								(MainFrame.medcin.avoirToutesLesMaladies(), TypeMaladie.Chronique));
					}else
					{
						MainFrame.medcin.effacerMaladie_Medicament(str, 1);
						table.repaintTablevV1(Fun.MedicamentListToTableModel(Medicament.getAllStock()));
					}
				}
			}
		});
		
		c.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c.getSelectedIndex() == 0)
				{
					table.repaintTablevV1(Fun.maladiesListToTableModel(MainFrame.medcin.avoirToutesLesMaladies(),
							TypeMaladie.Simple));
				}else
				{
					if (c.getSelectedIndex() == 1)
					{
						table.repaintTablevV1(Fun.maladiesListToTableModel(MainFrame.medcin.avoirToutesLesMaladies(),
								TypeMaladie.Chronique));
					}else
					{
						table.repaintTablevV1(Fun.MedicamentListToTableModel(Medicament.getAllStock()));
					}
				}
			}
		});
		
	}

	private void Init()
	{
		String Args[] = {"Maladie","Maladie Chronique","Medicament"};
		c = new JComboBox<String>(Args);
		c.setBounds(300, 120, 150, 30);
		
		recherche = new JLabel("Recherche");
		recherche.setBounds(10, 120, 150, 30);
		
		rech = new JTextField();
		rech.setBounds(100, 120, 150, 30);
		
		b = new JButton("Ajouter");
		b.setBounds(460, 120, 150, 30);
		
		
		
		
		table = new TableV1(Fun.maladiesListToTableModel(MainFrame.medcin.avoirToutesLesMaladies(), TypeMaladie.Simple));
		
		table.setBounds(10, 200, 620, 200);
		
	}
	
	private void add()
	{
		setLayout(null);
		add(c);
		add(b);
		add(table);
		add(recherche);
		add(rech);
	}
	
	private void InitTool(String n)
	{
		try 
		{
			wallpaper = ImageIO.read(new File("res/wallpaper.jpg"));
			Ico = ImageIO.read(new File("res/MedIco.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
		Bienvenu = new JLabel("Bienvenue Dr."+n);
		Bienvenu.setFont(new Font("Serif", Font.BOLD, 20));
		
		Date = new JLabel("Date : "+java.time.LocalDate.now());
		Date.setFont(new Font("Serif", Font.PLAIN, 20));
		

		Date.setBounds(180, 40, 300, 20);
		Bienvenu.setBounds(180, 15, 300, 20);
		
		
		setLayout(null);
		
		
		add(Date);
		add(Bienvenu);
		
		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wallpaper, 0, 0,null);
        g.drawImage(Ico, 50, 10,null);
        g.draw3DRect(7, 5, 624, 80, false);
        
    }
	
	

	
	
}
