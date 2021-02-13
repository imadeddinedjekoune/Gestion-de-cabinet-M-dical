package Stock;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Date;
import GUI.Frames.MainFrame;
import Medcin.Fun;
import Medcin.Home;
import Table.TableColor;

public class AffichStatsMaladiePatient extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JLabel goBack  ;
	private BufferedImage goBackIco = null ;
	private TableColor table ;
	
	
	private void init(Date d1 , Date d2)
	{
		table = new TableColor(Fun.maladiesStatsListToTableModel(MainFrame.medcin.avoirToutesLesMaladies()),d1,d2);
		goBack = new JLabel("");
		try {
			goBackIco = ImageIO.read(new File("res/return.png"));
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	private void add()
	{
		setLayout(null);
		
		
		goBack.setBounds(10, 10, 50, 50);
		goBack.setIcon(new ImageIcon(goBackIco));
		
		table.setBounds(10, 70, 330, 280);
		
		add(goBack);
		add(table);
	}
	
	private void Listener()
	{
		goBack.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Home.sp.switchPanel(StaticsMultiPanel.p1);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) 
			{
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			}
		});
	}
	
	public AffichStatsMaladiePatient(Date d1 , Date d2)
	{
		init(d1,d2);
		Listener();
		add();
	}

}
