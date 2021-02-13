package Consultation;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Classes.Consultation;
import Classes.Maladie;
import Classes.TypeMaladie;
import Medcin.Fun;
import Table.TablePaire;
import Test.MainTest;

public class AfficherConsultation extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JLabel lnom , lprenom , lanalyse , lordonance , lmaladiediag , choix , lmaladiediagch , obs ;

	private void init (Consultation c)
	{
		setLayout(null);
		
		lnom = new JLabel("Nom : "+c.getPatient().getNom());
		lprenom = new JLabel("Prenom : "+c.getPatient().getPrenom());
		lanalyse = new JLabel("           - Analyses  "+c.getAnalyses().size());
		lordonance = new JLabel("           - Ordonance  "+c.getOrdonance().getMedicaments().size());
		lmaladiediag = new JLabel("           - Maladies Simples  "+Maladie.MaladieAvecTypeUnique(c.getMaladies(), TypeMaladie.Simple).size());
		lmaladiediagch = new JLabel("           - Maladies Chroniques  "+Maladie.MaladieAvecTypeUnique(c.getMaladies(), TypeMaladie.Chronique).size());
		obs = new JLabel("           - Observation");
		choix = new JLabel("----> Choix :");
		
		lnom.setBounds(10, 10, 200, 25);
		lprenom.setBounds(10, 40, 200, 25);
		
		
		
		lanalyse.setBounds(10, 100, 200, 25);
		lanalyse.setFont(new Font("Serif", Font.BOLD, 15));
		lanalyse.setForeground(Color.black);
		
		lordonance.setBounds(10, 130, 200, 25);
		lordonance.setFont(new Font("Serif", Font.BOLD, 15));
		lordonance.setForeground(Color.black);
		
		lmaladiediag.setBounds(10, 160, 200, 25);
		lmaladiediag.setFont(new Font("Serif", Font.BOLD, 15));
		lmaladiediag.setForeground(Color.black);
		
		lmaladiediagch.setBounds(10, 190, 220, 25);
		lmaladiediagch.setFont(new Font("Serif", Font.BOLD, 15));
		lmaladiediagch.setForeground(Color.black);
		
		obs.setBounds(10, 220, 220, 25);
		obs.setFont(new Font("Serif", Font.BOLD, 15));
		obs.setForeground(Color.black);
		
		choix.setBounds(10, 70, 200, 25);
		choix.setFont(new Font("Serif", Font.ITALIC, 16));
		choix.setForeground(Color.green);
	}
	
	private void add()
	{
		add(lnom);
		add(lprenom);
		add(lanalyse);
		add(lordonance);
		add(lmaladiediag);
		add(lmaladiediagch);
		add(obs);
		add(choix);
	}
	
	private void Listenner (Consultation c)
	{
		lanalyse.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if (e.getButton() == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					
					TablePaire t = new TablePaire(Fun.AnalyseListToTableModel(c.getAnalyses()));
	
					d.setLayout(new BorderLayout());
					d.add(t);
					d.setResizable(false);
					d.setSize(300,265); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
					d.setVisible(true);
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lanalyse.setFont(new Font("Serif", Font.BOLD, 15));
				lanalyse.setForeground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lanalyse.setFont(new Font("Serif", Font.BOLD, 16));
				lanalyse.setForeground(Color.red);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		obs.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if (e.getButton() == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Observation");
					
					JTextArea t = new JTextArea(c.getObservation());
	
					t.setEditable(false);
					d.setLayout(new BorderLayout());
					d.add(new JScrollPane(t));
					d.setResizable(false);
					d.setSize(300,265); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
					d.setVisible(true);
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				obs.setFont(new Font("Serif", Font.BOLD, 15));
				obs.setForeground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				obs.setFont(new Font("Serif", Font.BOLD, 16));
				obs.setForeground(Color.red);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lordonance.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					
					JButton b = new JButton("Imprimer");
					
					TablePaire t = new TablePaire(Fun.OrdonanceListToTableModel(c.getOrdonance()));
					JPanel panelPrint = new JPanel();
					panelPrint.setLayout(new BorderLayout());
					JTable tableCopie = new JTable(t.table.getModel()) ;
					JButton c = new JButton("Test");
					panelPrint.add(tableCopie,BorderLayout.CENTER);
					panelPrint.add(c,BorderLayout.SOUTH);
					
					
					d.setLayout(new BorderLayout());
					d.add(t,BorderLayout.CENTER);
					d.add(b,BorderLayout.SOUTH);
					
					b.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();
							SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
									new MainTest(t.table);
								}
							});
							
						}

					});
					
					d.setResizable(false);
					d.setSize(300,265); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
					d.setVisible(true);
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lordonance.setFont(new Font("Serif", Font.BOLD, 15));
				lordonance.setForeground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lordonance.setFont(new Font("Serif", Font.BOLD, 16));
				lordonance.setForeground(Color.red);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lmaladiediag.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (e.getButton() == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					
					TablePaire t = new TablePaire(Fun.maladiesListToTableModel(c.getMaladies(), TypeMaladie.Simple));
					
					d.setLayout(new BorderLayout());
					d.add(t);
					d.setResizable(false);
					d.setSize(300,265); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
					d.setVisible(true);
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lmaladiediag.setFont(new Font("Serif", Font.BOLD, 15));
				lmaladiediag.setForeground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lmaladiediag.setFont(new Font("Serif", Font.BOLD, 16));
				lmaladiediag.setForeground(Color.red);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lmaladiediagch.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Ajout");
					
					TablePaire t = new TablePaire(Fun.maladiesListToTableModel(c.getMaladies(), TypeMaladie.Chronique));
					
					d.setLayout(new BorderLayout());
					d.add(t);
					d.setResizable(false);
					d.setSize(300,265); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
					d.setVisible(true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lmaladiediagch.setFont(new Font("Serif", Font.BOLD, 15));
				lmaladiediagch.setForeground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lmaladiediagch.setFont(new Font("Serif", Font.BOLD, 16));
				lmaladiediagch.setForeground(Color.red);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
	}
	
	public AfficherConsultation(Consultation c)
	{
		init(c);
		Listenner(c);
		add();
	}
	
	
}
