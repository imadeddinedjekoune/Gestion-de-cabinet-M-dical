package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Classes.Admin;
import Table.TablePaire;

public class RQT_SQL extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private JTextArea tRequete , error;
	private JScrollPane sp1  ;
	private JLabel lrq  ; 
	private JButton b ; 
	private TablePaire tres ;
	
	public RQT_SQL()
	{
		lrq = new JLabel("Requete : ");
		tres = new TablePaire(Admin.buildTableModel(Admin.excucte("",new String[1])));
		
		tres.setBorder(BorderFactory.createTitledBorder("Resultats : "));
		
		b = new JButton("Execute !");
		error = new JTextArea();
		
		tRequete =new JTextArea();
		tRequete.setFont(new Font("Verdana", Font.PLAIN, 18));
		tRequete.setBorder(BorderFactory.createEtchedBorder());
		
		sp1 = new JScrollPane(tRequete);
		
		setLayout(null);
		
		lrq.setBounds(10, 10, 100, 30);
		sp1.setBounds(13, 40, 1057, 100);
		tres.setBounds(10, 160, 1060, 360);
		
		
		b.setBounds(900, 10, 90, 25);
		
		b.addActionListener(this);
		
		add(lrq);
		add(sp1);
		add(tres);
	
		add(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b)
		{
			if (tRequete.getText().equals("EXIT"))
			{
				System.exit(0);
			}else
			{
				String T[] = new String [1];
				T[0] = "No" ;
				tres.repaintTable(Admin.buildTableModel(Admin.excucte(getReq(),T)));
				
				
				if (!T[0].equals("No"))
				{
					
					JDialog d = new JDialog();
					d.setTitle("Error");
					
					d.setLayout(new BorderLayout());
		
					error.setText(T[0]);
					error.setCaretPosition(0);
					JScrollPane sp = new JScrollPane(error);
					error.setFont(new Font("Verdana", Font.PLAIN, 14));
					error.setBackground(Color.LIGHT_GRAY);
					error.setForeground(Color.RED);
					d.add(sp);
					
					d.setSize(500,100); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-500)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-100)/2);
			        d.setVisible(true); 
				}
			}
			
			
		}
	}
	

	public void setReq (String t)
	{
		tRequete.setText(t);
	}
	
	public String getReq ()
	{
		return tRequete.getText();
	}
	
}
