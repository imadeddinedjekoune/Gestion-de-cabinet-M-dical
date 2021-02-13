package Stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Calendrier.TexteDate;
import Classes.Date;
import GUI.Frames.MainFrame;
import Medcin.Fun;
import Medcin.Home;

public class StaticsPanelIntGUI extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private TexteDate d1 , d2 ;
	private JLabel ldebut , lfin , design , shortcut ;
	private JButton get ;
	private JComboBox<String> c ;
	private JButton shortcutB1 , shortcutB2 ;
	
	
	private void init ()
	{
		ldebut = new JLabel("Date Debut    :");
		lfin = new JLabel("Date Fin          :");
		design = new JLabel("Statistiques");
		shortcut = new JLabel("shortcut");
		design.setFont(new Font("Serif", Font.ROMAN_BASELINE, 23));
		design.setForeground(Color.black);
		
		shortcutB1 = new JButton("mois");
		shortcutB2 = new JButton("semaine");
		
		
		d1 = new TexteDate();
		d2 = new TexteDate();
		
		String args[] = {"Nombre De Patient","Nombre De Consultation","Patient Par Maladie"};
		
		c = new JComboBox<String>(args);
		get = new JButton("Get");
		
		ldebut.setBounds(30, 190, 100, 20);
		lfin.setBounds(30, 240, 100, 20);
		
		d1.setBounds(150, 180, 140, 40);
		d2.setBounds(150, 233, 140, 40);
		
		design.setBounds(125, 20, 200, 50);
		
		get.setBounds(240, 100, 100, 30);
		c.setBounds(20, 100, 210, 30);
		
		shortcutB1.setBounds(110, 290, 90, 20);
		shortcutB2.setBounds(210, 290, 90, 20);
		shortcut.setBounds(30, 290, 90, 20);
	}
	
	private void Listeners ()
	{
		
		get.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (c.getSelectedIndex() == 0)
				{
					StaticsMultiPanel.p2 = new AffichStatsPatient(Fun.PatientStatsListToTableModel(MainFrame.medcin.
							getPatientConuslter(Date.StringtoDate(d1.getText()) ,Date.StringtoDate(d2.getText()) )));
					Home.sp.switchPanel(StaticsMultiPanel.p2);
				}
				
				if (c.getSelectedIndex() == 1)
				{
					StaticsMultiPanel.p2 = new AffichStatsPatient(Fun.ConsultationStatsListToTableModel
							(MainFrame.medcin.getConsultations(Date.StringtoDate(d1.getText())
									,Date.StringtoDate(d2.getText()))));
					Home.sp.switchPanel(StaticsMultiPanel.p2);
				}
				if (c.getSelectedIndex() == 2)
				{
					StaticsMultiPanel.p3 = new 	AffichStatsMaladiePatient(Date.StringtoDate(d1.getText()),
							Date.StringtoDate(d2.getText()));
					Home.sp.switchPanel(StaticsMultiPanel.p3);
				}
				
			}
		});
		shortcutB1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Calendar cal = Calendar.getInstance();
				Date d1TextDebut = new Date(1, cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
				Date d1TextFin = new Date(cal.getActualMaximum(Calendar.DATE), 
						cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
				
				d1.setText(d1TextDebut.toString());
				d2.setText(d1TextFin.toString());
			}
		});
		
		shortcutB2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Calendar cal = Calendar.getInstance();
				int jour = cal.get(Calendar.DATE) , debut = 0 , fin = 0;
				System.out.println(jour);
				if ( jour > 0 && jour <8 )
				{
					debut = 0 ;
					fin = 7 ;
				}
				if ( jour > 7 && jour <15 )
				{
					debut = 8 ;
					fin = 14 ;
				}
				if ( jour > 14 && jour <22 )
				{
					debut = 15 ;
					fin = 21 ;
				}
				if ( jour > 23 && jour <32 )
				{
					debut = 22 ;
					fin = cal.getActualMaximum(Calendar.DATE);
				}
					
				Date d1TextDebut = new Date(debut, cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
				Date d1TextFin = new Date(fin,cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
				
				d1.setText(d1TextDebut.toString());
				d2.setText(d1TextFin.toString());
			}
		});
		
	}
	
	private void add()
	{
		setLayout(null);
		add(ldebut);
		add(lfin);
		add(d1);
		add(d2);
		add(get);
		add(c);
		add(design);
		add(shortcutB1);
		add(shortcutB2);
		add(shortcut);
	}

	public StaticsPanelIntGUI()
	{
		init();
		Listeners();
		add();
	}

	@Override
    protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(new Color(102,178,255));
        g.draw3DRect(10, 30, 335, 348, false );
        g.draw3DRect(11, 31, 333, 346, true );
    }

}
