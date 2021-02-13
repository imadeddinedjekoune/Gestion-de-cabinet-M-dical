package SQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Classes.Analyse;
import Classes.Consultation;
import Classes.Date;
import Classes.Details;
import Classes.Maladie;
import Classes.Medcin;
import Classes.Medicament;
import Classes.Ordonance;
import Classes.Patient;
import Classes.Personne;
import Classes.RDV;
import Classes.Secretaire;
import Classes.TypeMaladie;

public class SQLite 
{
	private static Connection con = DBConnection.connect();
	
	public static void updatePersonne(Personne fp , Personne p)
	{
		PreparedStatement ps = null ;
		try 
		{
			// Changer La Personne //
			String sql = "UPDATE Personne SET Nom='"+p.getNom()+"', Prenom='"+p.getPrenom()+"' "
					+ ", Email='"+p.getEmail()+"'"+ ",Tel='"+p.getTel()+"' " 
					+" WHERE Nom='"+fp.getNom()+"' AND prenom = '"+fp.getPrenom()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
			
			// Changer Les RDV //
			sql = "UPDATE RDV SET Nom='"+p.getNom()+"', Prenom='"+p.getPrenom()+"' "
					+ "WHERE Nom='"+fp.getNom()+"' AND prenom = '"+fp.getPrenom()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
			sql = "UPDATE Patient SET " + 
					"  Nom='"+p.getNom()+"' ," + 
					"  Prenom='"+p.getPrenom()+"' , " + 
					"  Email='"+p.getEmail()+"', " + 
					"  Tel='"+p.getTel()+"' " + 
					" WHERE Nom = '"+fp.getNom()+"' "
				  + "AND Prenom = '"+fp.getPrenom()+"'";
			ps = con.prepareStatement(sql);	
			ps.execute();
			
			sql = "UPDATE Consultation SET " + 
					
					"  Nom = '"+p.getNom()+"' , " + 
					"  Prenom='"+p.getPrenom()+"' " + 
					" WHERE Nom = '"+fp.getNom()+"' "
					  + "AND Prenom = '"+fp.getPrenom()+"'";
			ps = con.prepareStatement(sql);	
			ps.execute();
			
			// Modifier Les Maladies //
			
			sql = "UPDATE Malade_de SET "
				+ "Nom='"+p.getNom()+"', " + 
				"  Prenom='"+p.getPrenom()+"' "+
				" WHERE Nom = '"+fp.getNom()+"' "
				+ "AND Prenom = '"+fp.getPrenom()+"'";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	
	public static void updatePersonne (Patient fp , Patient p)
	{
		PreparedStatement ps = null ;
		try 
		{
			// Changer La Personne //
			String sql = "UPDATE Personne SET Nom='"+p.getNom()+"', Prenom='"+p.getPrenom()+"' "
					+ ", Email='"+p.getEmail()+"'"+ ",Tel='"+p.getTel()+"'" 
					+" WHERE Nom='"+fp.getNom()+"' AND prenom = '"+fp.getPrenom()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
			
			// Changer Les RDV //
			sql = "UPDATE RDV SET Nom='"+p.getNom()+"', Prenom='"+p.getPrenom()+"' "
					+ "WHERE Nom='"+fp.getNom()+"' AND prenom = '"+fp.getPrenom()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
			sql = "UPDATE Patient SET " + 
					"  Nom='"+p.getNom()+"' ," + 
					"  Prenom='"+p.getPrenom()+"' , " + 
					"  CodePatient='"+p.getCodePatient()+"', " + 
					"  Sexe='"+p.getSexe()+"',\r\n" + 
					"  DateDeNaissance='"+p.getDateNaiss()+"', " + 
					"  GroupSanguin='"+p.getGroupSanguin()+"', " + 
					"  Adresse='"+p.getAdresse()+"', " + 
					"  DateInsc='"+p.getDateInsc()+"', " + 
					"  Email='"+p.getEmail()+"', " + 
					"  Tel='"+p.getTel()+"' " + 
					" WHERE CodePatient= '"+fp.getCodePatient()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();
			
			sql = "UPDATE Consultation SET " + 
					
					"  Nom = '"+p.getNom()+"' , " + 
					"  Prenom='"+p.getPrenom()+"' " + 
					" WHERE CodePatient= '"+fp.getCodePatient()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();
			
			// Effacer Les Maladies //
			sql = "DELETE FROM Malade_de WHERE Nom='"+fp.getNom()+
					"' AND Prenom='"+fp.getPrenom()+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
			
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	
	public static void updatePersonneSpe (String fNom , String fPrenom , String fem , 
			String ftel, String nom , String prenom , String spe)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "UPDATE Medcin SET " + 
					"  Nom='"+nom+"'," + 
					"  Prenom='"+prenom+"'," + 
					"  Spe='"+spe+"'," + 
					"  Email='"+fem+"'," + 
					"  Tel='"+ftel+"'"+
					" WHERE Nom='"+fNom+"' AND prenom = '"+fPrenom+"' ";
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
			sql = "UPDATE Secretaire SET  Nom_Medcin='"+nom+"',  Prenom_Medcin='"+prenom+"' " + 
					"WHERE Nom_Medcin='"+fNom+"' and  Prenom_Medcin='"+fPrenom+"' ;";
			ps = con.prepareStatement(sql);	
			ps.execute();
		
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
	}
	
	
	public static ArrayList<Analyse> selectAnalyse(String condition,String idConsult) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Analyse> a = new ArrayList<Analyse>();
		try 
		{
			String sql = "SELECT * FROM Analyse "+condition;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			Consultation c = SQLite.selectConsultation(idConsult);
			while (rs.next())
			{
				a.add(new Analyse(rs.getString(1),rs.getString(2),rs.getString(3),c));
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"E");
		}
		return a ;
	}
	public static void updateAnalyse (Analyse a,String c , String r)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "UPDATE Analyse SET resultats = '"+r+"' , commentaire = '"+c+"' WHEre idconsult = '"+a.getC().getIdConsultation()+"' and idanalyse = '"+a.getNom()+"'";
			ps = con.prepareStatement(sql);	
			ps.execute();	
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	
	
	public static void insertTableAnalyse (String nom , String result , String com , String idcon,
			String nomM , String prenomM)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO Analyse (IDAnalyse,Resultats,Commentaire,"
					+ "IDconsult,Nom_Medcin,Prenom_Medcin) VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,nom);
			ps.setString(2,result);
			ps.setString(3,com);
			ps.setString(4,idcon);
			ps.setString(5,nomM);
			ps.setString(6,prenomM);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"Analyse");
		}
	}
	
	public static void insertTableDig (String idcon , String maladie)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO Diagnostique (IDconsult,NomMaldie) VALUES (?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,idcon);
			ps.setString(2,maladie);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"MalD");
		}
	}
	
	public static boolean patientExiste (String code)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		boolean result = true ;
		
		try 
		{
			String sql = "SELECT COUNT (*) FROM Patient WHERE codepatient = "+code;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			if (rs.getInt(1) == 1)
			{
				result = true ;
			}else
			{
				result = false ;
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
		return result ;
	}
	
	// SELECT //
	
	public static ArrayList<Medicament> selectStock ()
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Medicament> a = new ArrayList<Medicament>();
		try 
		{
			String sql = "SELECT * FROM Medicament ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				a.add(new Medicament(rs.getString(1)));
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"Med Stock");
		}
		return a ;
	}

	
	public static ArrayList<Analyse> selectAnalyse (Consultation c)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Analyse> a = new ArrayList<Analyse>() ;
		try 
		{
			String sql = "SELECT * FROM Analyse WHERE IDconsult = '"+c.getIdConsultation()+"'";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			if (!rs.isClosed())
			{
				while (rs.next())
				{
					a.add(new Analyse(rs.getString(1), rs.getString(2), rs.getString(3), c));
				}
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"Analyse");
		}
		return a ;
	}
	
	
	public static Consultation selectConsultation( String ID ) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Consultation c = null ;
		try 
		{
			String sql = "SELECT * FROM Consultation WHERE IDconsult = '"+ID+"'";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			if (!rs.isClosed())
			{
				Patient p = new Patient(rs.getInt(11)) ;
				c = new Consultation(rs.getString(1), rs.getString(2), rs.getFloat(4),rs.getFloat(5), rs.getFloat(6),
						rs.getFloat(7), rs.getFloat(8), Date.StringtoDate(rs.getString(3)),
						new Medcin(rs.getString(12), rs.getString(13),1),p);
			}
			
			
		}catch (SQLException e)
		{
			System.out.println(e+"Consultation");
		}
		return c ;
	}
	
	public static Consultation selectConsultation(String nom , String prenom, Medcin m ) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Consultation c = null ;
		try 
		{
			String sql = "SELECT * FROM Consultation where nom =  '"+nom+"' and "
					+ "prenom = '"+prenom+"' and "
					+ "nom_medcin = '"+m.getNom()+"' "
					+ "and prenom_medcin = '"+m.getPrenom()+"'";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			if (!rs.isClosed())
			{
				c = new Consultation(rs.getString(1), rs.getString(2), rs.getFloat(4),rs.getFloat(5), rs.getFloat(6),
						rs.getFloat(7), rs.getFloat(8), Date.StringtoDate(rs.getString(3)),
						new Medcin(rs.getString(12), rs.getString(13),1),new Patient(rs.getInt(11)));
			}
			
			
		}catch (SQLException e)
		{
			System.out.println(e+"ConsultationPatientMedcin");
		}
		return c ;
	}
	
	public static ArrayList<Consultation> selectConsultation (Patient p)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Consultation> c = new ArrayList<Consultation>();
		try 
		{
			String sql = "SELECT * FROM Consultation WHERE Nom = '"+p.getNom()+"' "
					+ "and Prenom = '"+p.getPrenom()+"' ";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				c.add(new Consultation(rs.getString(1), rs.getString(2), rs.getFloat(4),rs.getFloat(5), rs.getFloat(6),
						rs.getFloat(7), rs.getFloat(8), Date.StringtoDate(rs.getString(3)),null,p));
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"Consultation");
		}
		return c ;
	}
	public static ArrayList<Consultation> selectConsultation (Medcin m)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Consultation> c = new ArrayList<Consultation>();
		try 
		{
			String sql = "SELECT * FROM Consultation WHERE Nom_Medcin = '"+m.getNom()+"' "
					+ "and Prenom_Medcin = '"+m.getPrenom()+"' ";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				c.add(new Consultation(rs.getString(1), rs.getString(2), rs.getFloat(4),rs.getFloat(5), rs.getFloat(6),
						rs.getFloat(7), rs.getFloat(8), Date.StringtoDate(rs.getString(3)),m,null));
				
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"Consultation");
		}
		return c ;
	}
	
	public static Maladie selectMaladie (String nomMaladie)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Maladie m = null ;
		try 
		{
			String sql = "SELECT * FROM Maladie WHERE NomMaldie = '"+nomMaladie+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if (rs.getInt(2) == 1)
			{
				m = new Maladie(rs.getString(1),TypeMaladie.Simple);
			}else
			{
				m = new Maladie(rs.getString(1),TypeMaladie.Chronique);
			}
		}catch (SQLException e)
		{
			System.out.println(e+"Maladie");
		}
		return m ;
	}
	
	public static ArrayList<Maladie> selectAllMaladie ()
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Maladie> ml = new ArrayList<Maladie>();
		try 
		{
			String sql = "SELECT * FROM Maladie ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				Maladie m ;
				if (rs.getInt(2) == 1)
				{
					m = new Maladie(rs.getString(1),TypeMaladie.Simple);
				}else
				{
					m = new Maladie(rs.getString(1),TypeMaladie.Chronique);
				}
				ml.add(m);
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"Maladie");
		}
		return ml ;
	}
	
	public static ArrayList<Maladie> selectMaladie (Patient p)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Maladie> m = new ArrayList<Maladie>() ;
		try 
		{
			String sql = "SELECT * FROM Malade_de WHERE CodePatient = '"+p.getCodePatient()+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				m.add(SQLite.selectMaladie(rs.getString(1)));
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"MaladiePersonne");
		}
		return m ;
	}
	public static ArrayList<Maladie> selectMaladie (Consultation c)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Maladie> m = new ArrayList<Maladie>() ;
		try 
		{
			String sql = "SELECT * FROM Diagnostique WHERE IDconsult = '"+c.getIdConsultation()+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				m.add(SQLite.selectMaladie(rs.getString(2)));
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"MaladieConsultation");
		}
		return m ;
	}
	
	public static Medcin selectMedcin (Secretaire s)
	{
		PreparedStatement ps = null ;
		ResultSet rs  ;
		Medcin m = null ;
		try 
		{
			String sql = "SELECT * FROM Secretaire  WHERE Nom = '"+s.getNom()+"' "
					+ "AND Prenom = '"+s.getPrenom()+"' ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			if (rs.isClosed())
			{
				return null ;
			}
			m = new Medcin(rs.getString(7),
					rs.getString(8),1);
		}catch (SQLException e)
		{
			System.out.println(e+"Medcin");
		}
		return m ;
	}	
	public static Medcin selectMedcin (String nom , String Prenom)
	{
		PreparedStatement ps = null ;
		ResultSet rs  ;
		Medcin m = null ;
		try 
		{
			String sql = "SELECT * FROM Medcin  WHERE user = '"+nom+"' "
					+ "AND password = '"+Prenom+"' ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			if (rs.isClosed())
			{
				return null ;
			}
			m = new Medcin(rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(7),rs.getString(4),
					rs.getString(5),rs.getString(3),null,null);
		}catch (SQLException e)
		{
			System.out.println(e+"Medcin");
		}
		return m ;
	}
	
	public static Medcin selectMedcinNom (String nom , String Prenom)
	{
		PreparedStatement ps = null ;
		ResultSet rs  ;
		Medcin m = null ;
		try 
		{
			String sql = "SELECT * FROM Medcin  WHERE nom = '"+nom+"' "
					+ "AND prenom = '"+Prenom+"' ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			if (rs.isClosed())
			{
				return null ;
			}
			m = new Medcin(rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(7),rs.getString(4),
					rs.getString(5),rs.getString(3),null,null);
		}catch (SQLException e)
		{
			System.out.println(e+"Medcin");
		}
		return m ;
	}
	
	public static ArrayList<Medicament> selectMedicament (Ordonance or)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<Medicament> a = new ArrayList<Medicament>();
		try 
		{
			String sql = "SELECT * FROM Traitement WHERE IDconsult = "+or.getIdOrdonance();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while (rs.next())
			{
				a.add(new Medicament(rs.getString(1), new Details(rs.getFloat(3), rs.getString(4))));
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
		return a ;
	}
	
	public static Personne selectPersonne (String nom , String Prenom)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Personne p = null ;
		try 
		{
			String sql = "SELECT * FROM Personne WHERE Nom = '"+nom+"' AND "
					+ " Prenom = '"+Prenom+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if (!rs.isClosed())
			{
				p = new Personne(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3));
			}
		}catch (SQLException e)
		{
			System.out.println(e+"RDV");
		}
		return p ;
	}

	public static Patient selectPatient (int code)
	{
		PreparedStatement ps = null ;
		ResultSet rs  ;
		Patient p = null ;
		try 
		{
			String sql = "SELECT * FROM Patient WHERE CodePatient = "+code;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			if (rs.isClosed())
			{
				return null ;
			}
			
			p = new Patient(rs.getString(1), rs.getString(2),rs.getString(10), rs.getString(9), rs.getInt(3),
					rs.getString(6), rs.getString(7), Date.StringtoDate(rs.getString(5)), Date.StringtoDate(rs.getString(8)),
					rs.getString(4).charAt(0),null,null);
			
		}catch (SQLException e)
		{
			System.out.println(e+"Patient");
		}
		return p ;
	}
	
	public static Patient selectPatient (String nom , String Prenom)
	{
		PreparedStatement ps = null ;
		ResultSet rs  ;
		Patient p = null ;
		try 
		{
			String sql = "SELECT * FROM Patient WHERE nom = '"+nom+"' "
					+ "AND prenom = '"+Prenom+"' ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			if (rs.isClosed())
			{
				return null ;
			}
			
			p = new Patient(rs.getString(1), rs.getString(2),rs.getString(10), rs.getString(9), rs.getInt(3),
					rs.getString(6), rs.getString(7), Date.StringtoDate(rs.getString(5)), Date.StringtoDate(rs.getString(8)),
					rs.getString(4).charAt(0),null,null);
			
		}catch (SQLException e)
		{
			System.out.println(e+"Patient");
		}
		return p ;
	}
	
	
	public static ArrayList<RDV> selectRDV (Personne p)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<RDV> rdv = null ;
		try 
		{
			String sql = "SELECT * FROM RDV WHERE Nom = '"+
					p.getNom()+"' and Prenom = '"+p.getPrenom()+"' ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			rdv = new ArrayList<RDV>();			
			while (rs.next())
			{
				RDV r = new RDV( rs.getInt(5),Date.StringtoDate(rs.getString(6)));
				r.setPersonne(p);
				r.setSecretaire(new Secretaire(rs.getString(3),rs.getString(4)));
				rdv.add(r);
			}	
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
		return rdv ;
	}
	public static ArrayList<RDV> selectRDV (Secretaire s)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		ArrayList<RDV> rdv = null ;
		try 
		{
			String sql = "SELECT * FROM RDV WHERE Nom_Secretaire = '"+s.getNom()+"' "
					+ "AND Prenom_Secretaire = '"+s.getPrenom()+"' ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			rdv = new ArrayList<RDV>();			
			while (rs.next())
			{
				RDV r = new RDV( rs.getInt(5),Date.StringtoDate(rs.getString(6)));
				r.setSecretaire(s);
				r.setPersonne(new Personne(rs.getString(1),rs.getString(2)));
				rdv.add(r);
			}	
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
		return rdv ;
	}
	
	public static Secretaire selectSecreatire(Medcin m ) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Secretaire s = null ;
		try 
		{
			String sql = "SELECT * FROM Secretaire WHERE Nom_Medcin = '"+m.getNom()+"' "
					+ "and Prenom_Medcin = '"+m.getPrenom()+"' ";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			if (!rs.isClosed())
			{
				s = new Secretaire(rs.getString(1), rs.getString(2), rs.getString(6), rs.getString(5), 
						rs.getString(3), rs.getString(4),null);
			}
			
			
		}catch (SQLException e)
		{
			System.out.println(e+"Secretaire");
		}
		return s ;
	}
	public static Secretaire selectSecreatire(String user , String pass ) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Secretaire s = null ;
		try 
		{
			String sql = "SELECT * FROM Secretaire WHERE user = '"+user+"' and password = '"+pass+"' ";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();	
			
			if (!rs.isClosed())
			{
				s = new Secretaire(rs.getString(1), rs.getString(2), rs.getString(6), rs.getString(5), 
						rs.getString(3), rs.getString(4),null);
			}
			
			
		}catch (SQLException e)
		{
			System.out.println(e+"Secretaire");
		}
		return s ;
	}
	
	// INSERT //
	
	public static void insertTableMalade_de (String nomMaladie , String codePatient , String nom ,String prenom)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO Malade_de (NomMaldie,Nom,Prenom,CodePatient) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,nomMaladie);
			ps.setString(4,codePatient);
			ps.setString(2,nom);
			ps.setString(3,prenom);
			ps.execute();
			
		}catch (SQLException e)
		{
			System.out.println(e+"RDV");
		}
	}
	
	
	public static void insertTablePatient (String Nom,String Prenom,int CodePatient,char Sexe,Date DateDeNaissance,
			String GroupSanguin,String Adresse,Date DateInsc,String Email,String Tel)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO Patient (Nom,Prenom,CodePatient,Sexe,DateDeNaissance,"
					+ "GroupSanguin,Adresse,DateInsc,Email,Tel) VALUES (?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, Nom);
			ps.setString(2, Prenom);
			ps.setInt(3, CodePatient);
			ps.setString(4, ""+Sexe);
			ps.setString(5, DateDeNaissance.toString());
			ps.setString(6, GroupSanguin);
			ps.setString(7, Adresse);
			ps.setString(8, DateInsc.toString());
			ps.setString(9, Email);
			ps.setString(10, Tel);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"Personne");
		}
	}
	
	
	public static void insertTableMaladie (String nom , int type)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT OR IGNORE INTO Maladie (NomMaldie,Type) VALUES (?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,nom);
			ps.setInt(2,type);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
	}
	
	
	public static void insertTableMedicament (String nom)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT OR IGNORE INTO Medicament (NomMedciment) VALUES (?)";
			ps = con.prepareStatement(sql);
		
			ps.setString(1,nom);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
	}
	
	public static void insertTableTraitement (String nom,String idCon , String Duree , float dose )
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO Traitement (NomMedciment,IDconsult,Dose,Duree) VALUES (?,?,?,?) ";
			ps = con.prepareStatement(sql);
		
			ps.setString(1,nom);
			ps.setString(2,idCon);
			ps.setFloat(3, dose);
			ps.setString(4, Duree);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
	}
	
	
	public static void insertTablePersonne(String nom , String prenom , String email , String tel)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT OR IGNORE INTO Personne (Nom,Prenom,Email,Tel) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,nom);
			ps.setString(2,prenom);
			ps.setString(4,tel);
			ps.setString(3,email);
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"Personne");
		}
	}
	public static boolean insertTableRDV (String Nom,String Prenom,String Nom_Secretaire, String Prenom_Secretaire,
			int NbConslut,Date DateDeConsultation)
	{
		boolean b = true ;
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO RDV (Nom,Prenom,Nom_Secretaire,Prenom_Secretaire,NbConslut,DateDeConsultation) VALUES"
					+ " (?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,Nom);
			ps.setString(2,Prenom);
			ps.setString(3, Nom_Secretaire);
			ps.setString(4, Prenom_Secretaire);
			ps.setInt(5, NbConslut);
			ps.setString(6, DateDeConsultation.toString());
			
			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"RDV");
			b = false ;
		}
		return b ;
	}

	public static void insertTableConsultation (Consultation c,String nomP , String prenomP , int code ,
			String nomM , String prenomM)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "INSERT INTO Consultation (IDconsult,Observation,DateDeConsultation,"
					+ "Prix,Poid,Taille,MesureDeTention,TauxDeDiabete,Nom,Prenom,CodePatient,"
					+ "Nom_Medcin,Prenom_Medcin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1,c.getIdConsultation());
			ps.setString(2,c.getObservation());
			ps.setString(3,c.getDateDeConsultation().toString());
			
			ps.setFloat(4, c.getPrix());
			ps.setFloat(5, c.getPoid());
			ps.setFloat(6, c.getTaille());
			ps.setFloat(7, c.getMeusureDeTention());
			ps.setFloat(8, c.getTauxDeDiabete());
			
			ps.setString(9, nomP);
			ps.setString(10, prenomP);
			
			ps.setInt(11, code);
			
			ps.setString(12, nomM);
			ps.setString(13, prenomM);

			ps.execute();
		}catch (SQLException e)
		{
			System.out.println(e+"MalD");
		}
	}
	
	
	// Utilities //
	
	public static int getRole (String user , String pass)
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		int role = 0;
		try 
		{
			String sql = "SELECT COUNT(*) FROM Medcin where user = '"+user+"' and password = '"+pass+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if (rs.getInt(1) == 1)
			{
				return 1 ; // 1 pour le medcin //
			}
			
			sql = "SELECT COUNT(*) FROM Secretaire where user = '"+user+"' and password = '"+pass+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if (rs.getInt(1) == 1)
			{
				return 2 ; // 2 pour le medcin //
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
		return role ;
	}
	public static ResultSet excute (String requete , String Tab[] )
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try 
		{
			String sql = requete ;
			ps = con.prepareStatement(sql);
			
			if (ps.execute())
			{
				rs = ps.executeQuery();
			}
			
		}catch (SQLException e)
		{
			Tab[0] = e.getLocalizedMessage() ;
		}
		return rs ;
	}
	public static ResultSet select_TablePersonne()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Personne ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableAnalyse()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Analyse ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableConsultation()
	{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Consultation ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableDiagnostique()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Diagnostique  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableMalade_de ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Malade_de  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableMaladie ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Maladie  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableMedcin ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Medcin   ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableMedicament  ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Medicament ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TablePatient  ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Patient  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableRDV  ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM RDV  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableSecretaire  ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Secretaire  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	public static ResultSet select_TableTraitement  ()
		{
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try 
			{
				String sql = "SELECT * FROM Traitement  ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();	
				
			}catch (SQLException e)
			{
				System.out.println(e+"");
			}
			return rs ;
		}
	
	
	// DELETE //
	
	public static void deleteRDV(String nomSec , String prenomSec , String nbCon , String dateCon , String nom
			, String prenom) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try 
		{
			String sql = "SELECT COUNT (*) FROM RDV WHERE "
					+ "nom_secretaire = '"+nomSec+"' "
					+ "and prenom_secretaire = '"+prenomSec+"' "
					+ "and nom = '"+nom+"' "
					+ "and prenom = '"+prenom+"'";	
			ps = con.prepareStatement(sql);	
			rs = ps.executeQuery();	
			
			sql = "DELETE FROM RDV WHERE "
					+ "DateDeConsultation='"+dateCon+"' and nbconslut = "+nbCon+" "
							+ "and nom_secretaire = '"+nomSec+"' and prenom_secretaire = '"+prenomSec+"' ;";
			ps = con.prepareStatement(sql);	
			ps.execute();
			
			if (rs.getInt(1) == 1)
			{
				// Effacer La Personne + RDV  //
				sql = "DELETE FROM Personne WHERE Nom='"+nom+"' and prenom ='"+prenom+"';";
				ps = con.prepareStatement(sql);	
				ps.execute();
				
			}else
			{
				// Effacer Que RDV //
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deleteStock (String nomM , int choix)
	{
		PreparedStatement ps = null ;
		try 
		{
			if ( choix == 0 )
			{
				String sql = "DELETE FROM Malade_de WHERE NomMaldie='"+nomM+"' ";		
				ps = con.prepareStatement(sql);	
				ps.execute();	
				
				sql = "DELETE FROM Diagnostique WHERE nommaldie = '"+nomM+"'";		
				ps = con.prepareStatement(sql);	
				ps.execute();	
				
				sql = "DELETE FROM Maladie WHERE NomMaldie='"+nomM+"'";		
				ps = con.prepareStatement(sql);	
				ps.execute();	
			}else
			{
				String sql = "DELETE FROM Medicament WHERE NomMedciment='"+nomM+"' ";		
				ps = con.prepareStatement(sql);	
				ps.execute();	
				
				sql = "DELETE FROM Traitement WHERE NomMedciment='"+nomM+"'; ";		
				ps = con.prepareStatement(sql);	
				ps.execute();
			}
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deletePatient (int code)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Patient WHERE codepatient = '"+code+"' ;";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deleteRDV (Secretaire s , Personne p)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM RDV WHERE Nom = '"+p.getNom()+"' "
					+ 	 "AND Prenom = '"+p.getPrenom()+"' "
					+    "AND Nom_Secretaire = '"+s.getNom()+"' "
					+	 "AND Prenom_Secretaire = '"+s.getPrenom()+"' ";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deleteRDV (Personne p)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM RDV WHERE Nom = '"+p.getNom()+"' "
					+ 	 "AND Prenom = '"+p.getPrenom()+"' ";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deleteConsultation (Consultation c)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Consultation WHERE IDconsult='"+c.getIdConsultation()+"';";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			System.out.println("Deleted");
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	} 
	public static void deleteDaignostiquee (Consultation c)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Diagnostique WHERE IDconsult='"+c.getIdConsultation()+"';";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deleteAnalyse (Consultation c)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Analyse WHERE IDConsult = '"+c.getIdConsultation()+"';";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}
	public static void deleteTaritement (Consultation c)
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Traitement WHERE IDConsult = '"+c.getIdConsultation()+"';";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}	
	}

	public static int getNbPatientRDV(Medcin medcin, String date) 
	{
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		int nb = 0 ;
		try 
		{
			medcin.loadSecretaire();
			String sql = "SELECT COUNT(*) FROM RDV "
					+ "WHERE nom_secretaire = '"+medcin.getSecretaire().getNom()+"' "
					+ "and prenom_secretaire = '"+medcin.getSecretaire().getPrenom()+"' "
					+ "and datedeconsultation = '"+date+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			nb = rs.getInt(1);
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
		
		return nb;
	}

	public static void deletePersonne(Patient p) {
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Personne WHERE Nom = '"+p.getNom()+"' "
					+ "AND Prenom = '"+p.getPrenom()+"'";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
	}

	public static void deleteMaladies(Maladie m, Patient p) 
	{
		PreparedStatement ps = null ;
		try 
		{
			String sql = "DELETE FROM Malade_de WHERE Nom = '"+p.getNom()+"' "
					+ "AND Prenom = '"+p.getPrenom()+"' "
					+ "AND NomMaldie = '"+m.getNomMaladie()+"' ";		
			ps = con.prepareStatement(sql);	
			ps.execute();	
			
		}catch (SQLException e)
		{
			System.out.println(e+"");
		}
	}
	
	
}
