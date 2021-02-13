package Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;

public class TableV3 extends TablePaire implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemAffich ;
	private JPopupMenu menuPopup  ;
	private gerRowValue myR;
	private void Init()
	{
		menuPopup = new JPopupMenu();
		menuItemAffich = new JMenuItem("Modif");
		menuItemAffich.addActionListener(this);
		menuPopup.add(menuItemAffich);
		table.setComponentPopupMenu(menuPopup);
		
		
	}
	
	public TableV3(TableModel m) {
		
		super(m);
		Init();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuItemAffich)
		{
			if (myR != null)
			{
				String IDAn = (String) table.getValueAt(table.getSelectedRow(), 0);
				myR.getVal(IDAn);
			}
			
		}
	}
	public void update(gerRowValue r) {
		myR = r ;
	}
	
	public void repaintTable(TableModel m)
	{
		
		removeAll();
		updateUI();
		super.init(m);
		super.add();
		Init();
	}

}
