package Test;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;

public class MainTest extends JFrame{
	private static final long serialVersionUID = 1L;
	private static int width = 500 ;
	private static int height = 400 ;
	private TextPanel t ;
	
	public MainTest(JTable table)
	{
		t = new TextPanel();
		t.setText(table);
		setTitle("Print");
		setSize(width,height);
		setVisible(true);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
		setLayout(new BorderLayout());
		add(t,BorderLayout.CENTER);
		
		t.update(new TestInt() {
			
			@Override
			public void Click(boolean b) {
				if ( b )
				{
					t.print();
				}
			}
		});
	}
}
