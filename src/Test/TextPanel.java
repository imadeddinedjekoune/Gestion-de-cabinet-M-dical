package Test;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea t ;
	private JButton b ;
	private TestInt MyB; 
	public TextPanel()
	{
		b = new JButton("Test Print");
		t = new JTextArea();
		setLayout(new BorderLayout());
		add(t,BorderLayout.CENTER);
		add(b,BorderLayout.SOUTH);
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( MyB != null )
				{
					MyB.Click(true);
				}
			}
		});	
	}
	
	public void setText (String Text)
	{
		t.setText(Text);
	}
	
	public void update (TestInt t)
	{
		MyB = t;
	}
	
	public void setText (JTable T)
	{
		int n = T.getRowCount() ;
		String Ligne = " ";
		for ( int i = 0 ; i < n ; i++ )
		{
			Ligne += "Nom Medicament : "+(String)(T.getValueAt(i, 0));
			Ligne += " , Dose : "+(String)(T.getValueAt(i, 1));
			Ligne += " , Duree : "+(String)(T.getValueAt(i, 2))+"\n";	
		}
		setText(Ligne);
	}
	
	private void printRecord(JPanel panel){
        // Create PrinterJob Here
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        // Set Printer Job Name
        printerJob.setJobName("Print Record");
        // Set Printable
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                // Check If No Printable Content
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                // Make 2D Graphics to map content
                Graphics2D graphics2D = (Graphics2D)graphics;
                // Set Graphics Translations
                // A Little Correction here Multiplication was not working so I replaced with addition
                graphics2D.translate(pageFormat.getImageableX()+10, pageFormat.getImageableY()+10);
                // This is a page scale. Default should be 0.3 I am using 0.5
                graphics2D.scale(0.5, 0.5);
                
                // Now paint panel as graphics2D
                panel.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }

			
        });
        // Store printerDialog as boolean
        boolean returningResult = printerJob.printDialog();
        // check if dilog is showing
        if(returningResult){
            // Use try catch exeption for failure
            try{
                // Now call print method inside printerJob to print
                printerJob.print();
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMessage());
            }
        }
    }
	
	public void print()
	{
		printRecord(this);
	}

}
