package Classes;

import java.util.ArrayList;

import SQLite.SQLite;

public class Secretaire extends User
{
	private ArrayList<RDV> rdvs ;
	private Medcin medcin ;
	
	
	public Secretaire(String Nom, String Prenom, String Email, String Tel, String user, String pass,Medcin m) 
	{
		super(Nom, Prenom, Email, Tel, user, pass);
		rdvs = new ArrayList<RDV>();
		medcin = m ;
	}
	public Secretaire(String user, String pass) 
	{
		Secretaire s = SQLite.selectSecreatire(user,pass);
		setNom(s.getNom());
		setPrenom(s.getPrenom());
		setEmail(s.getEmail());
		setTel(s.getTel());
		setUser(s.getUser());
		setPass(s.getPass());
	}

	public ArrayList<RDV> getRdvs() 
	{
		return rdvs;
	}
	
	
	public ArrayList<Personne> getPersonnes() 
	{
		ArrayList<Personne> pers = new ArrayList<Personne>();
		
		for ( int i = 0 ; i < rdvs.size() ; i++ )
		{
			if (!pers.contains(rdvs.get(i).getPersonne()))
			{
				pers.add(rdvs.get(i).getPersonne());
			}
		}
		
		return pers;
	}
	
	public ArrayList<RDV> getRdvs(Date d1 , Date d2) 
	{
		ArrayList<RDV> buff = new ArrayList<RDV>();
		loadRDV();
		for (int i = 0 ; i < this.rdvs.size() ; i++ )
		{
			if (Date.infOuEgale(rdvs.get(i).getDateDeConsultation(), d2)
			&&  Date.suppOuEgale(rdvs.get(i).getDateDeConsultation(), d1))
			{
				buff.add(rdvs.get(i));
			}
		}
		return buff ;
	}
	
	public void setRdvs(ArrayList<RDV> rdvs) 
	{
		this.rdvs = rdvs;
	}
	public Medcin getMedcin() {
		return medcin;
	}
	public void setMedcin(Medcin medcin) {
		this.medcin = medcin;
	}
	// Utilties //
	
	public void loadRDV ()
	{
		this.rdvs = SQLite.selectRDV(this);
	}
	public void loadMedcin ()
	{
		this.medcin = SQLite.selectMedcin(this) ;
	}
	public String toString ()
	{
		return "Secretaire [Nom : "+this.getNom()+" , Prenom : "+this.getPrenom()+" , Email : "+this.getEmail()
				+	" , Tel : "+this.getTel()+" ]";
	}
	public boolean AjouterRendezVous (Personne P , RDV r)
	{
		SQLite.insertTablePersonne(P.getNom(), P.getPrenom(), P.getEmail(), P.getTel());
		boolean b = SQLite.insertTableRDV(P.getNom(), P.getPrenom(), this.getNom(), this.getPrenom(), 
				r.getNbConsult(), r.getDateDeConsultation());
		return b ;
	}
	
	public void suprimerRDV (RDV rdv,Personne p)
	{
		SQLite.deleteRDV(this.getNom(),this.getPrenom(),""+rdv.getNbConsult(),
				rdv.getDateDeConsultation().toString(),p.getNom(),p.getPrenom());	
	}
	
	public void ModifierPersonne (String old_Nom , String old_Prenom , 
									String nom , String prenom , String emil ,
									String Tel)
	{
		SQLite.updatePersonne(new Personne(old_Nom,old_Prenom ), new Personne(
				nom,prenom,Tel,emil));
	}
	
	
}
