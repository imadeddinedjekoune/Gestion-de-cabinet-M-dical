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

public class TableV4 extends TablePaire implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemAffich , menuItemModify ;
	private JPopupMenu menuPopup  ;
	private getRowValueIndice myR;
	private BufferedImage Ico , mod;
	private void Init()
	{
		try 
		{
			Ico  = ImageIO.read(new File("res/DocPanel/addico.png"));
			mod  = ImageIO.read(new File("res/DocPanel/Modify.png"));
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		menuPopup = new JPopupMenu();
		menuItemAffich = new JMenuItem("Afficher");
		menuItemModify = new JMenuItem("Modifier");
		menuItemAffich.addActionListener(this);
		menuItemModify.addActionListener(this);
		
		menuPopup.add(menuItemAffich);
		menuPopup.add(menuItemModify);
		
		menuItemAffich.setIcon(new ImageIcon(Ico));
		menuItemModify.setIcon(new ImageIcon(mod));
		
		
		table.setComponentPopupMenu(menuPopup);
		
		
	}
	
	public TableV4(TableModel m) {
		
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
				myR.getVal(""+ID,1);
			}
		}
		
		if (e.getSource() == menuItemModify)
		{
			if (myR != null)
			{
				String ID = (String) table.getValueAt(table.getSelectedRow(), 0);
				myR.getVal(""+ID,2);
			}
		}
		
	}
	public void update(getRowValueIndice r) {
		myR = r ;
	}
	
	public void repaintTableV4(TableModel m)
	{
		
		removeAll();
		updateUI();
		super.init(m);
		super.add();
		Init();
	}

}
