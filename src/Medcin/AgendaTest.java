package Medcin;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Classes.Date;
import Classes.Patient;
import Consultation.ConultationPanel;
import GUI.Frames.MainFrame;
import Table.TableV5;
import Table.gerRowValue;

public class AgendaTest extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public static TableV5 t ;
	private JDateSwitch p ;
	public static Date dateFirst = new Date(1, 1, 1), DateEnd = new Date(1, 1, 3000);
	public static int rowselected ;
	
	private void init ()
	{
		t = new TableV5(Fun.PersonneListToTableModel(MainFrame.medcin.getSecretaire().getRdvs(dateFirst,DateEnd)));
		p = new JDateSwitch();
		
	}
	
	private void listener ()
	{
		t.update(new gerRowValue() {
			
			
			@Override
			public void getVal(String str) {
				rowselected = t.table.getSelectedRow() ;
				// Creer Un Nouveau Patient //
				Patient patient = Patient.Ex((String)(t.table.getValueAt(t.table.getSelectedRow(), 0))
						,(String)(t.table.getValueAt(t.table.getSelectedRow(), 1))); 
				System.out.println(patient);
				
				if ( patient == null) // C'est Le Cas D'un Nouveau Patient //
				{
					JDialog d = new JDialog();
					d.setTitle("Info Patient");
					
					JOptionPane.showMessageDialog(null, "Ce Patient est Nouveau veuillez l'inscrire Avant le Consulter", "Note", 1);
					
					MedcinAddingPaenl p = new MedcinAddingPaenl(MainFrame.medcin);
					
					p.setNom(((String)(t.table.getValueAt(t.table.getSelectedRow(), 0))));
					p.setPrenom(((String)(t.table.getValueAt(t.table.getSelectedRow(), 1))));
					p.setTel(((String)(t.table.getValueAt(t.table.getSelectedRow(), 3))));
					p.setEmail(((String)(t.table.getValueAt(t.table.getSelectedRow(), 2))));
					p.setDateInsc(((String)(t.table.getValueAt(t.table.getSelectedRow(), 4))));
					p.setNotNull();
					
					p.updateSaveClick(new SaveClick() {
						ConultationPanel paC ;
						@Override
						public void click(int i) 
						{
							if ( i == 1)
							{
								d.dispose();
								
								if ((!p.getDateNaiss().isEmpty())&&(!p.getAdresse().isEmpty())&&(!p.getCode().isEmpty()))
								{
									JDialog d2 = new JDialog();
									
									d2.setTitle("Consultation");
									Date d = Date.StringtoDate((String) t.table.getValueAt(t.table.getSelectedRow(), 4));
									String id1 = d.getAnnee()+""+d.getMois()+""+d.getJour();
									id1 += (String) t.table.getValueAt(t.table.getSelectedRow(), 5);
									
									if (!MainFrame.medcin.consultExiste(id1)) 
									{
										Patient pact = new Patient(
											((String)(t.table.getValueAt(t.table.getSelectedRow(), 0))),
											((String)(t.table.getValueAt(t.table.getSelectedRow(), 1))));
										
										pact.loadMaladies();
										paC = new ConultationPanel(pact);
										paC.setDateConsult((String)(t.table.getValueAt(t.table.getSelectedRow(), 4)));
										paC.nbConsult = Integer.parseInt((String) t.table.getValueAt(t.table.getSelectedRow(), 5));
										
										paC.update(new SaveClick() {
											
											@Override
											public void click(int i) 
											{
												d2.dispose();
												MainFrame.medcin.loadConsultation();
												if (dateFirst != null && DateEnd !=null)
										        {
										        	t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.
										        			getSecretaire().getRdvs(dateFirst,DateEnd)));
										        }else
										        {
										        	t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.
										        			getSecretaire().getRdvs()));
										        }
												TestPatient.t.repaintTableV4(Fun.PatientListToTableModel(MainFrame.medcin.getPatientConuslter()));
											}
										});
										
										d2.setLayout(new BorderLayout());
										d2.add(paC);
										d2.setSize(1100,550); 
								        d2.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-1200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-550)/2);
								        d2.setVisible(true);
								        
								        if (dateFirst != null && DateEnd !=null)
								        {
								        	t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.
								        			getSecretaire().getRdvs(dateFirst,DateEnd)));
								        }else
								        {
								        	t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.
								        			getSecretaire().getRdvs()));
								        }
										TestPatient.t.repaintTableV4(Fun.PatientListToTableModel(MainFrame.medcin.getPatientConuslter()));
								        
									}else // Le Cas Ou Il Veut Ajouter La Consultation 2 fois //
									{
										JOptionPane.showMessageDialog(null, "Vous Avez Ajouter Cette Consultation", "Note", 0);
									}
									
								}
								
							}
							 
						}
					});
					
					d.setLayout(new BorderLayout());
					d.add(p,BorderLayout.CENTER);
					d.setSize(650,500); 
			        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-650)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-500)/2);
			        d.setVisible(true);
			      
				}else
				{
					JDialog d2 = new JDialog();
					d2.setTitle("Consultation");
					Date d = Date.StringtoDate((String) t.table.getValueAt(t.table.getSelectedRow(), 4));
					String id1 = d.getAnnee()+""+d.getMois()+""+d.getJour();
					id1 += (String) t.table.getValueAt(t.table.getSelectedRow(), 5);
					MainFrame.medcin.loadConsultation();
					System.out.println(id1);
					if (!MainFrame.medcin.consultExiste(id1))
					{
						Patient pact = patient;
						patient.loadConsultation();
						patient.loadMaladies();
						
						ConultationPanel paC = new ConultationPanel(pact);
						paC.setDateConsult((String)(t.table.getValueAt(t.table.getSelectedRow(), 4)));
						paC.nbConsult = Integer.parseInt((String) t.table.getValueAt(t.table.getSelectedRow(), 5));
						d2.setLayout(new BorderLayout());
						d2.add(paC);
						
						paC.update(new SaveClick() {
							
							@Override
							public void click(int i) {
								d2.dispose();
								MainFrame.medcin.loadConsultation();

								if (dateFirst != null && DateEnd !=null)
						        {
						        	t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.
						        			getSecretaire().getRdvs(dateFirst,DateEnd)));
						        }else
						        {
						        	t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.
						        			getSecretaire().getRdvs()));
						        }
								
								TestPatient.t.repaintTableV4(Fun.PatientListToTableModel(MainFrame.medcin.getPatientConuslter()));
							        
							}
						});
						
						d2.setSize(1100,550); 
				        d2.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-1200)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-550)/2);
				        d2.setVisible(true);
					}else
					{
						JOptionPane.showMessageDialog(null, "Vous Avez Ajouter Cette Consultation", "Note", 0);
					}
			        
				}
				
			}
		});
		
		p.update(new getDateEx() 
		{
			@Override
			public void getdatelabel(String a, int i, int j) {
				
				if (i!=-1)
				{
					if (j != -2)
					{
						int mois = i+1 ; 
						int annee = 0 ;
						try {
							 annee = Integer.parseInt(a) ;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Vous Devez Entrer Une Annee Juste", "Attention", 0);
						}
						int dj = 0 , fj = 0 ;
						
						if (j == 0)
						{
							dj = 1 ;
							fj = 7 ;
						}
						if (j == 1)
						{
							dj = 8 ;
							fj = 17 ;
						}
						if (j == 2)
						{
							dj = 15 ;
							fj = 21 ;
						}
						if (j == 3)
						{
							dj = 22 ;
							Calendar cal = Calendar.getInstance();
							cal.set(annee, mois,0);
						    int res = cal.getActualMaximum(Calendar.DATE);
							fj = res ;
						}
						if ( annee != 0 )
						{
							Date d1 = new Date(dj,mois,annee);
							Date d2 = new Date(fj,mois,annee);
							
							dateFirst = d1 ;
							DateEnd = d2 ;
							
							t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.getSecretaire().getRdvs
									(d1,d2)));
						}
					}
					
				}else
				{
					Date T[] = getActualRange();
					if ( j == -1 )
					{
						Calendar c = Calendar.getInstance();
						Date d1 = new Date(c.get(Calendar.DATE),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR));
						t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.getSecretaire().getRdvs
								(d1,d1)));
						p.setAllFlieds(""+T[0].getAnnee(),T[0].getMois()-1,(T[0].getJour()/7));
					}else
					{
						t.repaintTableV5(Fun.PersonneListToTableModel(MainFrame.medcin.getSecretaire().getRdvs
								(T[0],T[1])));
						p.setAllFlieds(""+T[0].getAnnee(),T[0].getMois()-1,(T[0].getJour()/7));
					}
					
					
				}
			}
		});
		
	}
	
	private void add()
	{
		setLayout(null);
		
		
		t.setBounds(0, 50, 1080, 450);
		p.setBounds(0, 0, 1100, 50);
		
		add(t);
		add(p);
	}
	
	public AgendaTest()
	{
		init();
		listener();
		add();
	}
	
	private static Date[] getActualRange()
	{
		Calendar c = Calendar.getInstance();
		int jour_Actuel = c.get(Calendar.DAY_OF_MONTH) ;
		int jour_Debut = 0 , jour_Fin = 0 ;
		Date T[] = new Date [2];
		if ( jour_Actuel > 0 && jour_Actuel <= 7)
		{
			jour_Debut = 1 ;
			jour_Fin =  7;
		}else
		{
			if ( jour_Actuel > 7 && jour_Actuel <= 14 )
			{
				jour_Debut = 8 ;
				jour_Fin =  14;
			}else
			{
				if ( jour_Actuel > 14 && jour_Actuel <= 21 )
				{
					jour_Debut = 15 ;
					jour_Fin =  21 ;
				}else
				{
					jour_Debut = 22 ;
					jour_Fin =  c.getActualMaximum(Calendar.DATE);;
				}
			}
		}
		T[0] = new Date(jour_Debut, c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
		T[1] = new Date(jour_Fin, c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
		
		return T ;
	}
	

}
