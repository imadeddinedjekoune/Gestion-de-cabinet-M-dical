package Secretaire;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;

import Admin.Fun;
import Classes.Personne;
import GUI.Frames.SceretaireFrame;
import Table.TableV4;
import Table.getRowValueIndice;

public class InfoPersonne extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public static TableV4 t ;
	
	private void init()
	{
		t = new TableV4(Fun.PersonnesListToTableModel(SceretaireFrame.s.getPersonnes()));
		setLayout(new BorderLayout());
	}
	
	private void Listener()
	{
		t.update(new getRowValueIndice() {
			
			@Override
			public void getVal(String str, int indice) {
				if (indice == 1)
				{
					JDialog d = new JDialog();
					d.setTitle("Info Personne");
					
					String nom = (String) t.table.getValueAt(t.table.getSelectedRow(),0);
					String prenom = (String) t.table.getValueAt(t.table.getSelectedRow(),1);
					
					SecretaireAffichPanel s = new SecretaireAffichPanel(new Personne(nom,prenom));
					
					
					d.setLayout(new BorderLayout());
					d.add(s);
					d.setResizable(false);
					d.setSize(350,370); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-350)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-370)/2);
					d.setVisible(true);
				}else
				{
					JDialog d = new JDialog();
					d.setTitle("Modifier Personne");
					
					String nom = (String) t.table.getValueAt(t.table.getSelectedRow(),0);
					String prenom = (String) t.table.getValueAt(t.table.getSelectedRow(),1);
					
					SecretaireModifPanel sp = new SecretaireModifPanel(new Personne(nom,prenom));
					
					sp.update(new ClickBtn() {
						
						@Override
						public void click() {
							// Savgarder Les Changement + Fermer Le JDialog 
							SceretaireFrame.s.ModifierPersonne(nom, prenom, 
									sp.getNom(), sp.getPrenom(), sp.getEmail(), sp.getTel());
							
							// Reapinter Les Panels //
							SceretaireFrame.s.loadRDV();
							PersonnePanel.table.repaintTableV4(
									Fun.PersonneListToTableModel
									(SceretaireFrame.s.getRdvs()));
							t.repaintTableV4(Fun.PersonnesListToTableModel
									(SceretaireFrame.s.getPersonnes()));
							d.dispose();
						}
					});
					
					d.setLayout(new BorderLayout());
					d.add(sp);
					d.setResizable(false);
					d.setSize(300,300); 
					d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-300)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-300)/2);
					d.setVisible(true);
				}
			}
		});
	}
	
	private void add ()
	{
		add(t,BorderLayout.CENTER);
	}
	
	public InfoPersonne()
	{
		init();
		Listener();
		add();
	}
}
