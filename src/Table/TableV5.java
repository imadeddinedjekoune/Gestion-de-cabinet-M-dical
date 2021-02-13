package Table;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import Classes.Date;
import Classes.Patient;
import GUI.Frames.MainFrame;

public class TableV5 extends TablePaire implements ActionListener
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
		menuItemAffich = new JMenuItem("Consulter");
		menuItemAffich.addActionListener(this);
		menuPopup.add(menuItemAffich);
		menuItemAffich.setIcon(new ImageIcon(Ico));
		table.setComponentPopupMenu(menuPopup);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;
			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
				
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (Patient.existe((String)table.getValueAt(row, 0), (String)table.getValueAt(row, 1),MainFrame.medcin))
		        {
		        	
		        	Date d = Date.StringtoDate((String) table.getValueAt(row, 4));
					String id1 = d.getAnnee()+""+d.getMois()+""+d.getJour();
					id1 += (String) table.getValueAt(row, 5);
		        	
		        	if (MainFrame.medcin.consultExiste(id1))
		        	{
		        		c.setBackground(new Color(153,255,153));
		        	}else
		        	{
		        		c.setBackground(Color.LIGHT_GRAY);
		        	}
		        }else
		        {
		        	if (Patient.Ex((String)table.getValueAt(row, 0), (String)table.getValueAt(row, 1))!= null)
			        {
			        	c.setBackground(new Color(153,204,255));
			        }else
			        {
			        	c.setBackground(new Color(255,102,102));
			        }
		        	
		        }
		       
		        return c;
		    }
		});
		
	}
	
	public TableV5(TableModel m) {
		
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
	
	public void repaintTableV5(TableModel m)
	{
		
		removeAll();
		updateUI();
		super.init(m);
		super.add();
		Init();
		
	}

}
