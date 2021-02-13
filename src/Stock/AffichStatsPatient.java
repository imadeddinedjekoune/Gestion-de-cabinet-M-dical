package Stock;


import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Medcin.Home;
import Table.TablePaire;

public class AffichStatsPatient extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static TablePaire t ;
	private JLabel goBack , nb ;
	private BufferedImage goBackIco = null ;
	
	private void init (DefaultTableModel m)
	{
		t = new TablePaire(m);
		goBack = new JLabel("");
		nb = new JLabel();
		
		try {
			goBackIco = ImageIO.read(new File("res/return.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void add ()
	{
		setLayout(null);
		
		t.setBounds(10, 70, 330, 240);
		goBack.setBounds(10, 10, 50, 50);
		nb.setBounds(50, 340, 200, 20);
		nb.setFont(new Font("Serif", Font.BOLD, 20));
		goBack.setIcon(new ImageIcon(goBackIco));
		
		add(t);
		add(goBack);
		add(nb);
		
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
	
	
	public AffichStatsPatient(DefaultTableModel m )
	{
		init(m);
		nb = new JLabel("Nombre = "+t.table.getRowCount());
		Listener();
		add();
	}
	
	public AffichStatsPatient( )
	{
		init(new DefaultTableModel());
		Listener();
		add();
	}
	
	
	
	
	public void repaintPanel (DefaultTableModel m)
	{
		removeAll();
		updateUI();
		init(m);
		add();
	}
}
