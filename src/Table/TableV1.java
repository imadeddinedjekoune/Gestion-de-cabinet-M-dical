package Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;

public class TableV1 extends TablePaire implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemSupp ;
	private JPopupMenu menuPopup  ;
	private gerRowValue myR;
	private void Init()
	{
		menuPopup = new JPopupMenu();
		menuItemSupp = new JMenuItem("Supprimer");
		menuItemSupp.addActionListener(this);
		menuPopup.add(menuItemSupp);
		table.setComponentPopupMenu(menuPopup);
		
		
	}
	
	public TableV1(TableModel m) {
		
		super(m);
		Init();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuItemSupp)
		{
			if (myR != null)
			{
				String nom = (String) table.getValueAt(table.getSelectedRow(), 0);
				myR.getVal(nom);
			}
		}
	}
	public void update(gerRowValue r) {
		myR = r ;
	}
	
	public void repaintTablevV1(TableModel m)
	{
		
		removeAll();
		updateUI();
		super.init(m);
		super.add();
		Init();
	}

}
