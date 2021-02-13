package Classes;

import java.util.ArrayList;

import SQLite.SQLite;

public class Personne 
{
	private String nom , prenom , tel , email ;
	private ArrayList<RDV> rdvs ;

	
	public Personne()
	{
		
	}
	public Personne(String nom , String Prenom)
	{
		Personne p = SQLite.selectPersonne(nom, Prenom);
		this.nom = nom;
		this.prenom = Prenom;
		this.tel = p.getTel();
		this.email = p.getEmail();
	}
	
	
	
	public Personne(String nom, String prenom, String tel, String email) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		rdvs = new ArrayList<RDV>();
	}

	public String getNom() 
	{
		return nom;
	}
	public String getPrenom() 
	{
		return prenom;
	}
	public String getTel() 
	{
		return tel;
	}
	public String getEmail() 
	{
		return email;
	}
	
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}
	public void setTel(String tel) 
	{
		this.tel = tel;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public ArrayList<RDV> getRdvs() 
	{
		return rdvs;
	}

	public static boolean existe (String n , String p)
	{
		return (SQLite.selectPersonne(n,p) != null);
	}
	public void loadRDV ()
	{
		this.rdvs = SQLite.selectRDV(this);
	}
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email 
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		Personne p = (Personne)(obj);
		
		return (p.nom.equals(this.nom) && p.prenom.equals(this.prenom) 
				&& p.tel.equals(this.tel) && p.email.equals(this.email));
	}
	
	
	
}
