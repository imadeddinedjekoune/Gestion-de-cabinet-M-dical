package Classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import SQLite.SQLite;

public class Patient extends Personne
{
	private int codePatient ;
	private String GroupSanguin , Adresse ;
	private Date dateNaiss , dateInsc ;
	private char sexe ;
	private ArrayList<Consultation> consultations ;
	private ArrayList<Maladie> maladies ;
	
	public Patient (int codePatient)
	{
		Patient p = SQLite.selectPatient(codePatient);
		if (p != null )
		{
			this.setNom(p.getNom());
			this.setPrenom(p.getPrenom());
			this.setEmail(p.getEmail());
			this.setTel(p.getTel());
			this.codePatient = p.getCodePatient();
			GroupSanguin = p.GroupSanguin;
			Adresse = p.Adresse;
			this.dateNaiss = p.dateNaiss ;
			this.dateInsc = p.dateInsc;
			this.sexe = p.sexe;
			this.consultations = p.consultations;
			this.maladies = p.maladies;
		}
	}
	
	public Patient (String nom,String Prenom)
	{
		Patient p = SQLite.selectPatient(nom, Prenom);
		if (p != null )
		{
			this.setNom(p.getNom());
			this.setPrenom(p.getPrenom());
			this.setEmail(p.getEmail());
			this.setTel(p.getTel());
			this.codePatient = p.getCodePatient();
			GroupSanguin = p.GroupSanguin;
			Adresse = p.Adresse;
			this.dateNaiss = p.dateNaiss ;
			this.dateInsc = p.dateInsc;
			this.sexe = p.sexe;
			this.consultations = p.consultations;
			this.maladies = p.maladies;
		}
	}
	public Patient(String nom, String prenom, String tel, String email,int codePatient,
			String groupSanguin, String adresse, Date dateNaiss, Date dateInsc, char sexe,
			ArrayList<Consultation> consultations, ArrayList<Maladie> maladies) 
	{
		super(nom, prenom, tel, email);
		this.codePatient = codePatient;
		GroupSanguin = groupSanguin;
		Adresse = adresse;
		this.dateNaiss = dateNaiss;
		this.dateInsc = dateInsc;
		this.sexe = sexe;
		this.consultations = consultations;
		this.maladies = maladies;
	}
	public int getCodePatient() 
	{
		return codePatient;
	}
	public void setCodePatient(int codePatient) {
		this.codePatient = codePatient;
	}
	public String getGroupSanguin() {
		return GroupSanguin;
	}
	public void setGroupSanguin(String groupSanguin) {
		GroupSanguin = groupSanguin;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public Date getDateInsc() {
		return dateInsc;
	}
	public void setDateInsc(Date dateInsc) {
		this.dateInsc = dateInsc;
	}
	public char getSexe() {
		return sexe;
	}
	public void setSexe(char sexe) {
		this.sexe = sexe;
	}
	public ArrayList<Consultation> getConsultations() {
		return consultations;
	}
	
	public ArrayList<Consultation> getConsultations(Medcin m) {
		ArrayList<Consultation> ret = new ArrayList<Consultation>();
		for ( int i = 0 ; i < this.consultations.size() ; i++ )
		{
			Consultation c = new Consultation(consultations.get(i).getIdConsultation());
			if (c.getMedcin().equals(m))
			{
				ret.add(c);
			}
		}
		return ret ;
	}
	
	public void setConsultations(ArrayList<Consultation> consultations) {
		this.consultations = consultations;
	}
	public ArrayList<Maladie> getMaladies() {
		return maladies;
	}
	public void setMaladies(ArrayList<Maladie> maladies) {
		this.maladies = maladies;
	}

	
	// Utilities //
	@Override
	public String toString() {
		return "Patient [codePatient=" + codePatient + ", GroupSanguin=" + GroupSanguin + ", Adresse=" + Adresse
				+ ", dateNaiss=" + dateNaiss + ", dateInsc=" + dateInsc + ", sexe=" + sexe + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ ", getTel()=" + getTel() + ", getEmail()=" + getEmail() + "]";
	}
	
	public void loadMaladies()
	{
		this.maladies = SQLite.selectMaladie(this);
	}
	
	public void loadConsultation()
	{
		this.consultations =  SQLite.selectConsultation(this) ;
	}
	
	public static boolean existe (String nom , String prenom , Medcin m)
	{
		Consultation c = SQLite.selectConsultation(nom,prenom,m);
		return !(c == null );
	}
	public static boolean existe (String code)
	{
		return SQLite.patientExiste(code);
	}
	public int getAge (Date d)
	{
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(d.getAnnee(),d.getMois(),d.getJour());
		Period p = Period.between(birthday, today);
		 
		return p.getYears();
	}
	public static Patient Ex (String nom,String Prenom) 
	{
		Patient p = SQLite.selectPatient(nom, Prenom);
		
		return p;
	}
	
	public ArrayList<Analyse> getAnalyses() 
	{
		ArrayList<Analyse> ret = new ArrayList<Analyse>();
		
		for ( int i = 0 ; i < consultations.size() ; i++)
		{
			consultations.get(i).loadAnalyse();
			ArrayList<Analyse> Buffer = consultations.get(i).getAnalyses();
			for ( int j = 0 ; j < Buffer.size() ; j++ )
			{
				ret.add(Buffer.get(j));
			}
		}
		
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		String sNom = ((Patient)(obj)).getNom() ;
		String snom = this.getNom();
		String sPrenom = ((Patient)(obj)).getPrenom() ;
		String sprenom = this.getPrenom();

		
		return sNom.equals(snom) && (sPrenom.equals(sprenom));
	}
	
	public ArrayList<Medcin> getMedcinExaminee ()
	{
		this.loadConsultation();
		
		ArrayList<Medcin> med = new ArrayList<Medcin>();
		for (int i = 0 ; i < consultations.size() ; i++ )
		{
			Consultation c = new Consultation(consultations.get(i).getIdConsultation());
			Medcin m = c.getMedcin();
			if (!med.contains(m))
			{
				med.add(m);
			}
		}
		return med ;
	}
}


