package CheckList;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class CheckListRenderer extends JCheckBox implements ListCellRenderer<Object> {
	private static final long serialVersionUID = 1L;
	static private ArrayList<Boolean> Checked ;

	public static  ArrayList<Boolean> getChecked() {
		return Checked;
	}

	public static void setChecked(ArrayList<Boolean> checked) {
		Checked = checked;
	}

	public CheckListRenderer() 
	{
      setBackground(UIManager.getColor("List.textBackground"));
      setForeground(UIManager.getColor("List.textForeground"));
    }

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, 
    		boolean hasFocus) 
    {
      setEnabled(list.isEnabled());
      if (Checked != null)
      {
    	  if (!Checked.isEmpty())
          {
        	  ((CheckableItem)(value)).setSelected(Checked.get(0));
        	  Checked.remove(0);
          }
      }
      setSelected(((CheckableItem) value).isSelected());
      setFont(list.getFont());
      setText(value.toString());
      return this;
    }
  }