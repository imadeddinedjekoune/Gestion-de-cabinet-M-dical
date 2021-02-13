package Medcin;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Classes.Analyse;

public class AnalysesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextArea t1 , t2 ;
	private JLabel l1 , l2 ;
	private JScrollPane pane1 , pane2 ;
	private void init (Analyse a)
	{
		
		
		l1 = new JLabel("Resultats");
		l2 = new JLabel("Commentaire");
		
		t1 = new JTextArea();
		t2 = new JTextArea();
		pane1 = new JScrollPane(t1);
		pane2 = new JScrollPane(t2);
		
		l1.setBounds(10, 10, 70, 20);
		pane1.setBounds(10, 35, 264, 50);
		
		l2.setBounds(10, 90, 100, 20);
		pane2.setBounds(10, 115, 264, 100);
		
		t1.setText(a.getResultas());
		t2.setText(a.getCommentaire());
		
		
	}
	
	
	
	private void add()
	{
		setLayout(null);
		add(l1);
		add(pane1);
		add(l2);
		add(pane2);
		
	}
	
	public AnalysesPanel(Analyse a)
	{
		init(a);
		add();
	}
	
	public String getResult ()
	{
		return t1.getText();
	}
	
	public String getComment ()
	{
		return t2.getText();
	}
	
	public void setResult (String t)
	{
		t1.setText(t);
	}
	
	public void setComment (String t)
	{
		t2.setText(t);
	}

}
