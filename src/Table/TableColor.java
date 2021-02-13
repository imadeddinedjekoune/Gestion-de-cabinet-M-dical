package Table;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import Classes.Date;
import Classes.Maladie;
import Classes.Patient;
import GUI.Frames.MainFrame;
//import LoginGUI.MainFrame;



public class TableColor extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JTable table ;
	int Tab[];
	private JScrollPane sp;
	private JButton get ;
	
	private void init (TableModel m)
	{
		
		get = new JButton("Afficher");
		
		Tab = new int[m.getRowCount()];
		
		table = new JTable(m);
		
		table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        table.setRowHeight(20);
        table.setEnabled(false);
        
        coloring();
		
		sp = new JScrollPane(table);
	}
	
	private void coloring()
	{
		 table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
			{
				private static final long serialVersionUID = 1L;
				@Override
			    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			    {
			        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        c.setBackground(Tab[row] == 1 ? new Color(153,255,153) : Color.WHITE);
			        return c;
			    }
			});
	}
	
	private void add()
	{
		setLayout(new BorderLayout());
		add(sp,BorderLayout.CENTER);
		add(get,BorderLayout.SOUTH);
	}
	
	private void Listener(Date d1 , Date d2)
	{
		table.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        int currentRow = table.rowAtPoint(point);
		        Tab[currentRow]++;
		        Tab[currentRow] = Tab[currentRow] % 2 ;
		        repaintTable();
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
		
		get.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<Patient> p = MainFrame.medcin.getPatientConuslter(d1,d2);
				
				String col [] = {"Nom","Prenom","Maladie"};
				ArrayList<String> data1 , data2 , data3;
				data1 = new ArrayList<String>();
				data2 = new ArrayList<String>();
				data3 = new ArrayList<String>();
				
				
				for (int i = 0 ; i < Tab.length ; i++ )
				{
					if (Tab[i] == 1)
					{
						
						for ( int j = 0 ; j < p.size() ; j++ )
						{
							p.get(j).loadMaladies();
							ArrayList<Maladie> m = p.get(j).getMaladies();
							for (int k = 0 ; k < m.size() ; k++ )
							{
								if (table.getValueAt(i, 0).equals(m.get(k).getNomMaladie()))
								{
									System.out.println(p.get(j).getNom()+" "+table.getValueAt(i, 0));
									data1.add(p.get(j).getNom());
									data2.add(p.get(j).getPrenom());
									data3.add((String)table.getValueAt(i, 0));
								}
							}
						}
						
					}
				}
				
				System.out.println(data1);
				System.out.println(data2);
				System.out.println(data3);
				
				
				
				// End Loops //
				String data[][] = new String[data1.size()][3];
				for ( int i = 0 ; i < data1.size() ; i++ )
				{
					data[i][0] = data1.get(i);
					data[i][1] = data2.get(i);
					data[i][2] = data3.get(i);
				}
				
				JDialog d = new JDialog();
				d.setTitle("Info Patient");
				
				JTable tableAffich = new JTable(data,col);
				JScrollPane ppp = new JScrollPane(tableAffich);
				
				d.setLayout(new BorderLayout());
				d.add(ppp,BorderLayout.CENTER);
				d.setSize(650,500); 
		        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-650)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
		        d.setVisible(true);
								
				
			}
		});
		
	}
	public TableColor(TableModel m,Date d1 , Date d2)
	{
		
		init (m);
		Listener(d1,d2);
		add();
		
	}
	
	private void repaintTable ()
	{
		removeAll();
		updateUI();
		coloring();
		add();
	}

}
