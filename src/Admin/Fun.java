package Admin;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Classes.Analyse;
import Classes.Consultation;
import Classes.Maladie;
import Classes.Patient;
import Classes.Personne;
import Classes.RDV;
import Classes.TypeMaladie;
import SQLite.SQLite;

public class Fun 
{
	
	public static void updateMed (String nomMed,String prenomMed,String Email
			,String Tel,String NvNom,String NvPrenom,String NvSpe)
	{
		SQLite.updatePersonneSpe(nomMed,prenomMed,Email
				,Tel,NvNom,NvPrenom,NvSpe);
	}
	
	public static DefaultTableModel AnalyseListToTableModel (ArrayList<Analyse> a )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[a.size()][4] ;
		
		String col[] = new String[4];
		for ( int i = 0 ; i < a.size() ; i ++ )
		{
			data[i][0] = a.get(i).getNom();
			data[i][1] = a.get(i).getCommentaire();
			data[i][2] = a.get(i).getResultas();
			data[i][3] = a.get(i).getC().getIdConsultation();
			
		}
		col[0] = "Nom";
		col[1] = "Commentaire";
		col[2] = "Resultas";
		col[3] = "Consultation";
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel PersonneListToTableModel (ArrayList<RDV> a )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[a.size()][6] ;
		
		String col[] = new String[6];
		for ( int i = 0 ; i < a.size() ; i ++ )
		{
			data[i][0] = a.get(i).getPersonne().getNom();
			data[i][1] = a.get(i).getPersonne().getPrenom();
			data[i][2] = a.get(i).getPersonne().getEmail();
			data[i][3] = a.get(i).getPersonne().getTel();
			data[i][4] = a.get(i).getDateDeConsultation().toString();
			data[i][5] = ""+a.get(i).getNbConsult();		
			
		}
		col[0] = "Nom";
		col[1] = "Prenom";
		col[2] = "Email";
		col[3] = "Tel";
		col[4] = "Date";
		col[5] = "NbConsult";
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel PersonnesListToTableModel (ArrayList<Personne> a )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[a.size()][4] ;
		
		String col[] = new String[4];
		for ( int i = 0 ; i < a.size() ; i ++ )
		{
			data[i][0] = a.get(i).getNom();
			data[i][1] = a.get(i).getPrenom();
			data[i][2] = a.get(i).getEmail();
			data[i][3] = a.get(i).getTel();
			
		}
		col[0] = "Nom";
		col[1] = "Prenom";
		col[2] = "Email";
		col[3] = "Tel";
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel PatientStatsListToTableModel (ArrayList<Patient> p )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[p.size()][4] ;
		
		String col[] = new String[4];
		for ( int i = 0 ; i < p.size() ; i ++ )
		{
			
			data[i][0] = ""+p.get(i).getCodePatient();
			data[i][1] = p.get(i).getNom();
			data[i][2] = p.get(i).getPrenom();
			data[i][3] = ""+p.get(i).getSexe();
		}
		
		col[0] = "Id";
		col[1] = "Nom";
		col[2] = "Prenom";
		col[3] = "Sexe";
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel PatientListToTableModel (ArrayList<Patient> p )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[p.size()][10] ;
		
		String col[] = new String[10];
		for ( int i = 0 ; i < p.size() ; i ++ )
		{
			
			data[i][0] = ""+p.get(i).getCodePatient();
			data[i][1] = p.get(i).getNom();
			data[i][2] = p.get(i).getPrenom();
			data[i][3] = ""+p.get(i).getSexe();
			data[i][4] = p.get(i).getDateNaiss().toString();
			data[i][5] = p.get(i).getGroupSanguin();
			data[i][6] = p.get(i).getAdresse();
			data[i][7] = p.get(i).getDateInsc().toString();
			data[i][8] = p.get(i).getEmail();
			data[i][9] = p.get(i).getTel();
									
		}
		
		col[0] = "Id";
		col[1] = "Nom";
		col[2] = "Prenom";
		col[3] = "Sexe";
		col[4] = "DateNaiss";
		col[5] = "Grp";
		col[6] = "Adresse";
		col[7] = "DateInsc";
		col[8] = "Email";
		col[9] = "Tel";
		
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel ConsultationListToTableModel (ArrayList<Consultation> c )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[c.size()][7] ;
		
		String col[] = new String[7];
		for ( int i = 0 ; i < c.size() ; i ++ )
		{
			data[i][0] = c.get(i).getIdConsultation();
			data[i][1] = c.get(i).getDateDeConsultation().toString();
			data[i][2] = ""+c.get(i).getPrix();
			data[i][3] = ""+c.get(i).getPoid();
			data[i][4] = ""+c.get(i).getTaille();
			data[i][5] = ""+c.get(i).getMeusureDeTention();
			data[i][6] = ""+c.get(i).getTauxDeDiabete();
			
		}
		col[0] = "Id";
		col[1] = "Date";
		col[2] = "Prix";
		col[3] = "Poid";
		col[4] = "Taille";
		col[5] = "Tention";
		col[6] = "Diabete";
		
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel ConsultationStatsListToTableModel (ArrayList<Consultation> c )
	{
		DefaultTableModel t = null;
		
		Object data[][] = new String[c.size()][5] ;
		
		String col[] = new String[5];
		for ( int i = 0 ; i < c.size() ; i ++ )
		{
			data[i][0] = c.get(i).getIdConsultation();
			data[i][1] = c.get(i).getPatient().getNom();
			data[i][2] = c.get(i).getPatient().getPrenom();
			data[i][3] = ""+c.get(i).getPatient().getSexe();
			data[i][4] = ""+c.get(i).getPrix();
			
			
		}
		col[0] = "Id";
		col[1] = "Nom";
		col[2] = "Prenom";
		col[3] = "Sexe";
		col[4] = "Prix";
		
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	
	public static DefaultTableModel maladiesListToTableModel (ArrayList<Maladie> m ,TypeMaladie type)
	{
		DefaultTableModel t = null;
		
		ArrayList<Maladie> maladiesSimples = new ArrayList<Maladie>();
		
		for ( int i = 0 ; i < m.size() ; i++ )
		{
			if (m.get(i).getTypeMaladie() == type)
			{
				maladiesSimples.add(m.get(i));
			}
		}
		
		Object data[][] = new String[maladiesSimples.size()][1] ;
		
		String col[] = new String[1];
		for ( int i = 0 ; i < maladiesSimples.size() ; i ++ )
		{
			data[i][0] = maladiesSimples.get(i).getNomMaladie();
		}
		col[0] = "Maladies";
		t = new DefaultTableModel(data,col);
		
		return t ;
	}
	
	public static DefaultTableModel maladiesStatsListToTableModel (ArrayList<Maladie> m )
	{
		DefaultTableModel t = null;
		
		ArrayList<Maladie> maladiesSimples = new ArrayList<Maladie>();
		
		for ( int i = 0 ; i < m.size() ; i++ )
		{
			maladiesSimples.add(m.get(i));
		}
		
		Object data[][] = new String[maladiesSimples.size()][1] ;
		
		String col[] = new String[1];
		for ( int i = 0 ; i < maladiesSimples.size() ; i ++ )
		{
			data[i][0] = maladiesSimples.get(i).getNomMaladie();
		}
		col[0] = "Maladies";
		t = new DefaultTableModel(data,col);
		
		return t ;
	}

	public static DefaultTableModel buildTableModel (ResultSet rs)
		{
			ResultSetMetaData metaData = null;
			
		    try 
		    {
				if (rs != null )
				{
					metaData = rs.getMetaData();
				}
			} catch (SQLException e) 
		    {
				e.printStackTrace();
			}
	
		    // Name Of Columns //
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = 0;
			try {
				if (metaData != null)
				{
					columnCount = metaData.getColumnCount();
				}
			}
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		    
			for (int column = 1; column <= columnCount; column++)
		    {
		        try 
		        {
					columnNames.add(metaData.getColumnName(column));
				}
		        catch (SQLException e) 
		        {
					e.printStackTrace();
				}
		    }
	
		    // Data Of The Table //
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    try {
				if (rs != null)
				{
					while (rs.next()) 
					{
					    Vector<Object> vector = new Vector<Object>();
					    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) 
					    {
					        vector.add(rs.getObject(columnIndex));
					    }
					    data.add(vector);
					}
				}
			}
		    catch (SQLException e) 
		    {
				e.printStackTrace();
			}
		    return new DefaultTableModel(data, columnNames);
		}

	
}
