package Classes;

import java.util.ArrayList;

import SQLite.SQLite;

public class Medcin extends User
{
	private String spe  ;
	private Secretaire secretaire ; 
	private ArrayList<Consultation> consultations;

	public Medcin (String user , String pass , int choix)
	{
		if (choix == 0)
		{
			Medcin m = SQLite.selectMedcin(user, pass);
			setNom(m.getNom());
			setPrenom(m.getPrenom());
			setEmail(m.getEmail());
			setTel(m.getTel());
			this.setUser(m.getUser());
			this.setPass(m.getPass());
			this.spe = m.spe;
			this.secretaire = m.secretaire;
			this.consultations = m.consultations;
		}else
		{
			Medcin m = SQLite.selectMedcinNom(user, pass);
			setNom(m.getNom());
			setPrenom(m.getPrenom());
			setEmail(m.getEmail());
			setTel(m.getTel());
			this.setUser(m.getUser());
			this.setPass(m.getPass());
			this.spe = m.spe;
			this.secretaire = m.secretaire;
			this.consultations = m.consultations;
		}
	}
	
	public Medcin(String Nom, String Prenom, String Email, String Tel, String user, String pass, String spe,
		 Secretaire secretaire, ArrayList<Consultation> consultations) {
		super(Nom, Prenom, Email, Tel, user, pass);
		this.spe = spe;
		this.secretaire = secretaire;
		this.consultations = consultations;
	}
	
	public String getSpe() {
		return spe;
	}
	public void setSpe(String spe) {
		this.spe = spe;
	}
	
	public Secretaire getSecretaire() {
		return secretaire;
	}
	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}
	public ArrayList<Consultation> getConsultations() {
		return consultations;
	}
	
	public ArrayList<Consultation> getConsultations(Patient p) {
		loadConsultation();
		ArrayList<Consultation> con = new ArrayList<Consultation>();
		for ( int i = 0 ; i < this.getConsultations().size() ; i++)
		{
			Consultation c = new Consultation(this.getConsultations().get(i).getIdConsultation());
			if (c.getPatient().equals(p))
			{
				con.add(c);
			}
		}
		return con ;
	}
	
	public ArrayList<Consultation> getConsultations(Date d1 , Date d2) 
	{
		loadConsultation();
		ArrayList<Consultation> con = new ArrayList<Consultation>();
		for ( int i = 0 ; i < this.getConsultations().size() ; i++)
		{
			if (Date.suppOuEgale(this.getConsultations().get(i).getDateDeConsultation(), d1)
					&& Date.infOuEgale(this.getConsultations().get(i).getDateDeConsultation(), d2))
			{
				con.add(this.getConsultations().get(i));
			}
		}
		return con ;
	}
	
	
	public void setConsultations(ArrayList<Consultation> consultations) {
		this.consultations = consultations;
	}

	@Override
	public String toString() {
		return "Medcin [spe=" + spe + ", user=" + getUser() + ", pass=" + getPass() + ", secretaire=" + secretaire
				+ ", consultations=" + consultations + ", toString()=" + super.toString() + "]";
	}

	public void loadSecretaire()
	{
		this.secretaire = SQLite.selectSecreatire(this) ;
	}
	public void loadConsultation()
	{
		this.consultations = SQLite.selectConsultation(this) ;
	}

	public boolean consultExiste (String id)
	{
		for ( int i = 0 ; i < consultations.size() ; i++ )
		{
			if (id.equals(consultations.get(i).getIdConsultation()))
			{
				return true;
			}
		}
		return false; 
	}
	
	public void ajouterMaladie(Maladie m )
	{
		int type = 0 ;
		if (m.getTypeMaladie() == TypeMaladie.Simple)
		{
			type = 1 ;
		}else
		{
			type = 2 ;
		}
		SQLite.insertTableMaladie(m.getNomMaladie(), type);	
	}
	
	public void effacerMaladie_Medicament(String str,int choix)
	{
		SQLite.deleteStock(str, choix);
	}
	
	public void assossierMaladieAuPatient (Maladie m , Patient p )
	{
		SQLite.insertTableMalade_de(m.getNomMaladie(),""+p.getCodePatient(), p.getNom(), p.getPrenom());
	}
	
	public void addConsultationAuPatient(Consultation con,Patient p)
	{
	   SQLite.insertTableConsultation(con , p.getNom(), p.getPrenom(), p.getCodePatient(), this.getNom(),
			   this.getPrenom());
	}
	
	public Consultation getConsult (String id)
	{
		return SQLite.selectConsultation(id);
	}
	
	public ArrayList<Maladie> avoirToutesLesMaladies()
	{
		ArrayList<Maladie> m = SQLite.selectAllMaladie();
		return m ;
	}
	public ArrayList<Patient> getPatientConuslter ()
	{
		ArrayList<Patient> pa = new ArrayList<Patient>();
		for ( int i = 0 ; i < consultations.size() ; i++)
		{
			Consultation c = new Consultation(consultations.get(i).getIdConsultation());
			Patient p = c.getPatient() ;
			
			if (!pa.contains(p))
			{
				pa.add(p);
			}
		}
		return pa ;
	}
	
	public ArrayList<Patient> getPatientConuslter (String cond , int choix )
	{
		ArrayList<Patient> pa = new ArrayList<Patient>();
		loadConsultation();
		for ( int i = 0 ; i < consultations.size() ; i++)
		{	
			Consultation c = new Consultation(consultations.get(i).getIdConsultation());
			Patient p = c.getPatient();
			String s = null ;
			if (choix == 0)
			{
				if (p.getNom().length() >= cond.length())
				{
					s = p.getNom().substring(0, cond.length());
				}
			}else
			{
				s = p.getPrenom().substring(0, cond.length());
			}
			if (p.getNom().length() >= cond.length())
			{
				if (s.equals(cond))
				{
					if (!pa.contains(p))
					{
						pa.add(p);
					}
				}
			}		
		}
		return pa ;
	}
	
	public void ajouterUnNouveauPatient (Patient p) 
	{
		SQLite.insertTablePatient(p.getNom(), p.getPrenom(), p.getCodePatient(), p.getSexe(),
				p.getDateNaiss(), p.getGroupSanguin() , p.getAdresse(), p.getDateInsc(),
				p.getEmail(), p.getTel());
	}
	
	public ArrayList<Analyse> lesAnalyseDePateint (Patient p)
	{
		p.loadConsultation();
		ArrayList<Consultation> c = p.getConsultations(this);
		ArrayList<Analyse> analyses = new ArrayList<Analyse>();
		
		for ( int i = 0 ; i < c.size() ; i++ )
		{
			Consultation con = c.get(i);
			con.loadAnalyse();
			for ( int j = 0 ; j < con.getAnalyses().size() ; j++)
			{
				analyses.add(con.getAnalyses().get(j));
			}
		}
		
		return analyses ;
	}
	
	public void effacerPatientDifinitivement (Patient p)
	{
		ArrayList<Consultation> con_De_P_Par_M = this.getConsultations(p);
		System.out.println(con_De_P_Par_M);
		for ( int i = 0 ; i < con_De_P_Par_M.size() ; i++ )
		{
			SQLite.deleteConsultation(con_De_P_Par_M.get(i));
			SQLite.deleteDaignostiquee(con_De_P_Par_M.get(i));
			SQLite.deleteAnalyse(con_De_P_Par_M.get(i));
			SQLite.deleteTaritement(con_De_P_Par_M.get(i));
		}
		this.loadSecretaire();
		SQLite.deletePatient(p.getCodePatient());
		SQLite.deleteRDV(this.getSecretaire(), p);
		
		// Verif Si Il Consult Seulement Avec Ce Medcin //
		if (SQLite.selectRDV(p).isEmpty())
		{
			SQLite.deletePersonne(p);
		}
		
	} 
	
	
	public ArrayList<Patient> getPatientConuslter (Date d1 , Date d2)
	{
		ArrayList<Patient> pa = getPatientConuslter ();
		ArrayList<Patient> ret = new ArrayList<Patient>();
		
		for ( int i = 0 ; i < pa.size() ; i++ )
		{
			if (Date.suppOuEgale(pa.get(i).getDateInsc(),d1)
					&& Date.infOuEgale(pa.get(i).getDateInsc(), d2))
			{
				ret.add(pa.get(i));
			}
		}
		return ret ;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		Medcin m = (Medcin) (obj);
		return (this.getNom().equals(m.getNom())
				&& this.getPrenom().equals(m.getPrenom())) ;
	}

	public void ajouterUnNouveauPers(Personne p , RDV r) 
	{
		loadSecretaire();
		SQLite.insertTablePersonne(p.getNom(),p.getPrenom(), p.getEmail(), p.getTel());
		SQLite.insertTableRDV(p.getNom(), p.getPrenom(), 
				this.getSecretaire().getNom(), this.getSecretaire().getPrenom(),
				r.getNbConsult(), r.getDateDeConsultation());
	}
	

	public int CombienDeRdvParDate(String date) 
	{
		return SQLite.getNbPatientRDV(this,date)+1;
	}
	
	
	public static void modifierPersonne (Patient patient , Patient p)
	{
		SQLite.updatePersonne(patient,p);
		
	}
	
	public void gerire (ArrayList<Maladie> m,Patient p)
	{
		for (int i = 0 ; i < m.size() ; i++ )
		{
			SQLite.deleteMaladies(m.get(i),p);
		}
	}
	
}
