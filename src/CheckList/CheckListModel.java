package CheckList;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;





public class CheckListModel extends JPanel {
	private static final long serialVersionUID = 1L;
	public String[] strs ;
	public JList<?> list ;
	
	
	private CheckListBool myC ;
	
	
	private void Init(String str[])
	{
		
		strs = str;
		list = new JList<>(createData(strs));
		
		list.setCellRenderer(new CheckListRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setBorder(new EmptyBorder(0, 4, 0, 0));
	   
	}
	
	private void Listener ()
	{
		list.addMouseListener(new MouseAdapter() 
	    {
	    	public void mouseClicked(MouseEvent e) 
	    	{
	            if (e.getButton() == 1)
	            {
	            	int index = list.locationToIndex(e.getPoint());
		            CheckableItem item = (CheckableItem) list.getModel()
		                .getElementAt(index);
		            item.setSelected(!item.isSelected());
		            Rectangle rect = list.getCellBounds(index, index);
		            list.repaint(rect);
	            }
	            
	            if (myC != null)
	            {
	            	myC.getButton(e.getButton());
	            }
	    	}
		});
		
		
	}
	
	private void Add()
	{
		JScrollPane sp = new JScrollPane(list);
	    setLayout(new BorderLayout());
	    add(sp,BorderLayout.CENTER);
	   
	}
	
	public CheckListModel(String str[])
	{
		Init(str);
	    Listener();
	    Add();
	}
	
	 private CheckableItem[] createData(String[] strs) 
	 {
		    int n = strs.length;
		    CheckableItem[] items = new CheckableItem[n];
		    for (int i = 0; i < n; i++) 
		    {
		      items[i] = new CheckableItem(strs[i]);
		    }
		    return items;
	 }

	public void getClick( CheckListBool c) {
		myC = c ;
	}



	public void repaintPanel(String str[]) 
	{
		Init(str);
	    Listener();
		removeAll();
		updateUI();
		Add();
	}

	
}
