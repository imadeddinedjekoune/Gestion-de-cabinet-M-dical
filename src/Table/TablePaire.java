package Table;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class TablePaire extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public JTable table ;
	JScrollPane sp ;
	DefaultTableModel myModel ; 
	
	
	public TablePaire(TableModel m)
	{
		init(m);
		add();
	}

	
	void init(TableModel m)
	{
		
		myModel = (DefaultTableModel) m ;
		table = new JTable(m);
		
		table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        table.setRowHeight(20);
        table.setEnabled(false);
		table.addMouseListener(new TableMouseListener(table));
		sp = new JScrollPane(table);
	}
	
	
	
	protected void add()
	{
		setLayout(new BorderLayout());
		add(sp,BorderLayout.CENTER);
	}


	public void repaintTable(DefaultTableModel buildTableModel) {
		removeAll();
		updateUI();
		init(buildTableModel);
		add();
		
	}



}
