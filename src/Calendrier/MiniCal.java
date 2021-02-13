package Calendrier;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


public class MiniCal extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private BufferedImage image , imagePresed , imagePerinted ;
	private SaveClick myC ;
	public MiniCal()
	{
		addMouseListener(this);
		try {
		image = ImageIO.read(new File("res/img.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try {
			imagePresed = ImageIO.read(new File("res/img2.jpg"));
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		imagePerinted = image ;
	}
	

	@Override
    public void paint(Graphics g){
        g.drawImage(imagePerinted, 0, 0, 40, 40, null);
    }

	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		JDialog d = new JDialog();
		JButton b = new JButton("Save");
		d.setTitle("Select Date");
    	d.setLayout(new BorderLayout());
    	
    	CalendrierAffiche c = new CalendrierAffiche(java.time.LocalDate.now().getYear(),java.time.LocalDate.now().getMonthValue()-1);
    	d.add(c);
    	d.add(b,BorderLayout.SOUTH);
    	
    	b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (myC != null)
				{
					if (c.getTabC()!=null)
					{
						myC.Click(c.getTabC().get(0));
					}
				}
				d.dispose();
			}
		});
    	
    	c.getClick(new DaysClick() {

			@Override
			public void get(int Days, int Button) {
				ArrayList<Date> D = new ArrayList<Date>();
				D.add(new Date(c.getA(), c.getM(), Days-1));
				c.setTabC(D);
				c.setColor();
			}
		});
    	
    	d.setSize(330, 305); 
        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-330)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-305)/2);
        d.setVisible(true); 
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		imagePerinted = imagePresed ;
		removeAll();
		updateUI();
	}


	@Override
	public void mouseExited(MouseEvent e) {
		imagePerinted = image ;
		removeAll();
		updateUI();
	}


	public void getSaveBtnClick(SaveClick c) {
		myC = c ;
	}
	
	

}
