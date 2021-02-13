package Classes;

public class RDV 
{
	private int nbConsult ;
	private Date dateDeConsultation ;
	private Secretaire secretaire ;
	private Personne personne ;
	
	
	public RDV(int nbConsult, Date dateDeConsultation, Secretaire secretaire, Personne personne) 
	{
		this.nbConsult = nbConsult;
		this.dateDeConsultation = dateDeConsultation;
		this.secretaire = secretaire;
		this.personne = personne;
	}

	public RDV(int nbConsult, Date dateDeConsultation) 
	{
		this.nbConsult = nbConsult;
		this.dateDeConsultation = dateDeConsultation;
	}

	public int getNbConsult() 
	{
		return nbConsult;
	}
	public void setNbConsult(int nbConsult) 
	{
		this.nbConsult = nbConsult;
	}
	public Date getDateDeConsultation() 
	{
		return dateDeConsultation;
	}
	public void setDateDeConsultation(Date dateDeConsultation) 
	{
		this.dateDeConsultation = dateDeConsultation;
	}
	public Secretaire getSecretaire() 
	{
		return secretaire;
	}
	public void setSecretaire(Secretaire secretaire) 
	{
		this.secretaire = secretaire;
	}
	public Personne getPersonne() 
	{
		return personne;
	}
	public void setPersonne(Personne personne) 
	{
		this.personne = personne;
	}

	@Override
	public String toString() {
		return "RDV [nbConsult=" + nbConsult + ", dateDeConsultation=" + dateDeConsultation + ", secretaire="
				+ secretaire + ", personne=" + personne + "]";
	}
	
	
	
	
}
