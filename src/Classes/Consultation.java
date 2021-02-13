package Classes;

import java.util.ArrayList;

import SQLite.SQLite;

public class Consultation 
{
	private String idConsultation , observation ;
	private float prix , poid , taille , meusureDeTention , tauxDeDiabete ;
	private Date dateDeConsultation;
	private Medcin medcin ;
	private Patient patient ;
	private ArrayList<Maladie> maladies ;
	private ArrayList<Analyse> analyses ;
	private Ordonance ordonance ;
	
	public Consultation(String IDCon) 
	{
		
		Consultation c = SQLite.selectConsultation(IDCon);
		this.idConsultation = c.idConsultation;
		this.observation = c.observation;
		this.prix = c.prix;
		this.poid = c.poid;
		this.taille = c.taille;
		this.meusureDeTention = c.meusureDeTention;
		this.tauxDeDiabete = c.tauxDeDiabete;
		this.dateDeConsultation = c.dateDeConsultation;
		this.medcin = c.medcin;
		this.patient = c.patient;
		
	}
	
	public Consultation(String idConsultation, String observation, float prix, float poid, float taille,
			float meusureDeTention, float tauxDeDiabete, Date dateDeConsultation, Medcin medcin, Patient patient) {
		super();
		this.idConsultation = idConsultation;
		this.observation = observation;
		this.prix = prix;
		this.poid = poid;
		this.taille = taille;
		this.meusureDeTention = meusureDeTention;
		this.tauxDeDiabete = tauxDeDiabete;
		this.dateDeConsultation = dateDeConsultation;
		this.medcin = medcin;
		this.patient = patient;
	}
	
	
	public String getIdConsultation() {
		return idConsultation;
	}
	public void setIdConsultation(String idConsultation) {
		this.idConsultation = idConsultation;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public float getPoid() {
		return poid;
	}
	public void setPoid(float poid) {
		this.poid = poid;
	}
	public float getTaille() {
		return taille;
	}
	public void setTaille(float taille) {
		this.taille = taille;
	}
	public float getMeusureDeTention() {
		return meusureDeTention;
	}
	public void setMeusureDeTention(float meusureDeTention) {
		this.meusureDeTention = meusureDeTention;
	}
	public float getTauxDeDiabete() {
		return tauxDeDiabete;
	}
	public void setTauxDeDiabete(float tauxDeDiabete) {
		this.tauxDeDiabete = tauxDeDiabete;
	}
	public Date getDateDeConsultation() {
		return dateDeConsultation;
	}
	public void setDateDeConsultation(Date dateDeConsultation) {
		this.dateDeConsultation = dateDeConsultation;
	}
	public Medcin getMedcin() {
		return medcin;
	}
	public void setMedcin(Medcin medcin) {
		this.medcin = medcin;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public ArrayList<Maladie> getMaladies() 
	{
		return maladies;
	}
	public void setMaladies(ArrayList<Maladie> maladies) 
	{
		this.maladies = maladies;
	}
	public ArrayList<Analyse> getAnalyses() {
		return analyses;
	}
	public void setAnalyses(ArrayList<Analyse> analyses) {
		this.analyses = analyses;
	}
	public Ordonance getOrdonance() {
		return ordonance;
	}
	public void setOrdonance(Ordonance ordonance) {
		this.ordonance = ordonance;
	}
	
	
	@Override
	public String toString() {
		return "Consultation [idConsultation=" + idConsultation + ", observation=" + observation + ", prix=" + prix
				+ ", poid=" + poid + ", taille=" + taille + ", meusureDeTention=" + meusureDeTention
				+ ", tauxDeDiabete=" + tauxDeDiabete + ", dateDeConsultation=" + dateDeConsultation +  "]";
	}

	public void loadMaladies ()
	{
		this.maladies = SQLite.selectMaladie(this) ;
	}

	public void loadAnalyse ()
	{
		this.analyses = SQLite.selectAnalyse(this) ;
	}
	
	public void loadOrdonance ()
	{
		this.ordonance = new Ordonance(this.getIdConsultation()) ;
	}

	
	public void saveMaladies ()
	{
		for (int i = 0 ; i < maladies.size() ; i++ )
		{
			SQLite.insertTableDig(this.idConsultation, maladies.get(i).getNomMaladie());
		}
	}
	public void saveAnalyse ()
	{
		Consultation c = new Consultation(this.getIdConsultation());
		for (int i = 0 ; i < analyses.size() ; i++ )
		{
			SQLite.insertTableAnalyse(analyses.get(i).getNom(), analyses.get(i).getResultas(),
					analyses.get(i).getCommentaire(), this.idConsultation,
					c.getMedcin().getNom(),c.getMedcin().getPrenom());
		}
	}
	public void saveOrdonance (ArrayList<Medicament> m)
	{
		for ( int i = 0 ; i < m.size() ; i++ )
		{
			SQLite.insertTableTraitement(m.get(i).getNomMed(), 
					idConsultation, m.get(i).getD().getDure(), 
					m.get(i).getD().getDose());
		}
	}
	
	
	
	
}
