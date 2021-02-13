package CheckList;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;





public class CheckList extends JPanel {
	private static final long serialVersionUID = 1L;
	public String[] strs ;
	public JList<?> list ;
	private JMenuItem i1 ;
	private JMenu menu ;
	private JMenuBar menuBar ;
	
	private CheckListBool myC ;
	private Btn myBtn ;
	
	public void updateI (Btn n)
	{
		myBtn = n ;
	}
	
	private void Init(String str[])
	{
		i1 = new JMenuItem("Ajouter");
		
		
		menu = new JMenu("Menu");
		menu.add(i1);
		
		menuBar = new JMenuBar();
		menuBar.add(menu);
		
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
		
		i1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myBtn != null)
				{
					myBtn.getButton(1);
				}
			}
		});
		
	}
	
	private void Add()
	{
		JScrollPane sp = new JScrollPane(list);
	    setLayout(new BorderLayout());
	    add(sp,BorderLayout.CENTER);
	    add(menuBar,BorderLayout.NORTH);
	}
	
	public CheckList(String str[])
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
		
		removeAll();
		updateUI();
		Init(str);
	    Listener();
		Add();
	}
	
	
	public static ArrayList<String> Print(CheckList L)
	{
		ListModel<?> model = L.list.getModel();
        int n = model.getSize();
        ArrayList<String> Copie = new ArrayList<String>();
        
        for (int i = 0; i < n; i++) 
        {
        	CheckableItem item = (CheckableItem) model.getElementAt(i);
        	if (item.isSelected()) 
        	{
        		Copie.add(item.toString());
        	}
        }
        
        return Copie ;
        
	}

	public static ArrayList<String> Print(CheckListModel L)
	{
		ListModel<?> model = L.list.getModel();
        int n = model.getSize();
        ArrayList<String> Copie = new ArrayList<String>();
        
        for (int i = 0; i < n; i++) 
        {
        	CheckableItem item = (CheckableItem) model.getElementAt(i);
        	if (item.isSelected()) 
        	{
        		Copie.add(item.toString());
        	}
        }
        
        return Copie ;
        
	}
	
}
