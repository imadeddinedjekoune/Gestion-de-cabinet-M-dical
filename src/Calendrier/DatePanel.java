package Calendrier;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatePanel  extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JLabel l1 , l2 , l3 , l4 , l5 , l6 ,l7 ;  
	private JLabel cd ;
	
	public DatePanel (String d)
	{
		l1 = new JLabel("Dim");
		l2 = new JLabel("Lun");
		l3 = new JLabel("Mar");
		l4 = new JLabel("Mer");
		l5 = new JLabel("Jeu");
		l6 = new JLabel("Ven");
		l7 = new JLabel("Sam");
		cd = new JLabel(d);
		
		Add();
		
	}
	
	private void Add ()
	{
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(new Font("Verdana", Font.BOLD, 15));
		
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setFont(new Font("Verdana", Font.BOLD, 15));
		
		l3.setHorizontalAlignment(JLabel.CENTER);
		l3.setFont(new Font("Verdana", Font.BOLD, 15));
		
		l4.setHorizontalAlignment(JLabel.CENTER);
		l4.setFont(new Font("Verdana", Font.BOLD, 15));
		
		l5.setHorizontalAlignment(JLabel.CENTER);
		l5.setFont(new Font("Verdana", Font.BOLD, 15));
		
		l6.setHorizontalAlignment(JLabel.CENTER);
		l6.setFont(new Font("Verdana", Font.BOLD, 15));
		
		l7.setHorizontalAlignment(JLabel.CENTER);
		l7.setFont(new Font("Verdana", Font.BOLD, 15));
		
		cd.setHorizontalAlignment(JLabel.CENTER);
		cd.setFont(new Font("Verdana", Font.BOLD, 17));
		
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		gc.fill = GridBagConstraints.BOTH; 
	    gc.insets = new Insets(7, 7, 7, 7); 
	    
		
		gc.gridx = 0;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l1, gc);
	    
	    gc.gridx = 1;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l2, gc);
	    
	    gc.gridx = 2;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l3, gc);
	    
	    gc.gridx = 3;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l4, gc);
	    
	    gc.gridx = 4;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l5, gc);
	    
	    gc.gridx = 5;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l6, gc);
	    
	    gc.gridx = 6;
	    gc.gridy = 1;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(l7, gc);
	    
	    gc.gridx = 0;
	    gc.gridy = 0;
	    gc.gridwidth = 7;
	    gc.gridheight = 1;
	    gc.weightx = gc.weighty = 1.0;
	    this.add(cd, gc);
	    

	}
	
	public void changeDate(String d)
	{
		removeAll();
		updateUI();
		cd = new JLabel(d);
		Add();
	}

	
}
