package Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;

public class TableV2 extends TablePaire implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemAffich ;
	private JPopupMenu menuPopup  ;
	private gerRowValue myR;
	private BufferedImage Ico;
	private void Init()
	{
		try 
		{
			Ico  = ImageIO.read(new File("res/DocPanel/addico.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		menuPopup = new JPopupMenu();
		menuItemAffich = new JMenuItem("Afficher");
		menuItemAffich.addActionListener(this);
		menuPopup.add(menuItemAffich);
		menuItemAffich.setIcon(new ImageIcon(Ico));
		table.setComponentPopupMenu(menuPopup);
		
		
	}
	
	public TableV2(TableModel m) {
		
		super(m);
		super.sp.setBorder(BorderFactory.createEtchedBorder());
		Init();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuItemAffich)
		{
			if (myR != null)
			{
				String ID = (String) table.getValueAt(table.getSelectedRow(), 0);
				myR.getVal(ID);
				
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
