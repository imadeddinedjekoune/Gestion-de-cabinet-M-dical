package Medcin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.Frames.MainFrame;

public class InfoPerso extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JButton b ;
	private JTextField tnom , tprenom , tspe , temail , ttel;
	private JLabel l1 , l2 ,l3 , l4 ,l5;
	
	private void init()
	{
		tnom = new JTextField(MainFrame.medcin.getNom());
		tprenom = new JTextField(MainFrame.medcin.getPrenom());
		tspe = new JTextField(MainFrame.medcin.getSpe());
		temail = new JTextField(MainFrame.medcin.getEmail());
		ttel = new JTextField(MainFrame.medcin.getTel());
		
		tnom.setEnabled(false);
		tprenom.setEnabled(false);
		tspe.setEnabled(false);
		temail.setEnabled(false);
		ttel.setEnabled(false);
		
		
		l1 = new JLabel("Nom  ");
		l2 = new JLabel("Prenom ");
		l3 = new JLabel("Spe ");
		l4 = new JLabel("email ");
		l5 = new JLabel("tele ");
		
		
		b = new JButton("modifer");
		
		tnom.setBounds(100,10,150,30);
		tprenom.setBounds(100,50,150,30);
		tspe.setBounds(100,90,150,30);
		temail.setBounds(100,140,150,30);
		ttel.setBounds(100,190,150,30);
		
		l1.setBounds(10,10,70,30);
		l2.setBounds(10,50,70,30);
		l3.setBounds(10,90,70,30);
		l4.setBounds(10,140,70,30);
		l5.setBounds(10,190,70,30);
		
		b.setBounds(150,240,90,30);
	}
	private void Listener()
	{
		
		b.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				tnom.setEnabled(!(tnom.isEnabled()));
				tprenom.setEnabled(!(tprenom.isEnabled()));
				temail.setEnabled(!(temail.isEnabled()));
				ttel.setEnabled(!(ttel.isEnabled()));
				
				if (b.getText().equals("modifer"))
				{
					b.setText("save");
				}else
				{
					b.setText("modifer");
					Admin.Fun.updateMed(MainFrame.medcin.getNom(),MainFrame.medcin.getPrenom(),temail.getText()
							,ttel.getText(),tnom.getText(),tprenom.getText(),tspe.getText());
					
				}
				
			}
			
			
		});
		
	}
	private void add()
	{
		setLayout(null);
		
		add(tnom);
		add(tprenom);
		add(tspe);
		add(temail);
		add(ttel);
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		
		
		add(b);
	}
	
	public InfoPerso()
	{
		init();
		Listener();
		add();
	}
}
