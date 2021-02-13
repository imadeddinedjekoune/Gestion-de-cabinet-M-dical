package Classes;

import SQLite.SQLite;

public class Analyse
{
	private String nom , resultas, commentaire;
	private Consultation c ;
	
	
	
	public Analyse(String nom, String resultas, String commentaire, Consultation c) 
	{
		super();
		this.nom = nom;
		this.resultas = resultas;
		this.commentaire = commentaire;
		this.c = c;
	}
	
	public Analyse(String nom, String resultas, String commentaire) 
	{
		super();
		this.nom = nom;
		this.resultas = resultas;
		this.commentaire = commentaire;
		this.c = null;
	}
	
	
	public String getNom() 
	{
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getResultas() {
		return resultas;
	}
	public void setResultas(String resultas) {
		this.resultas = resultas;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Consultation getC() {
		return c;
	}
	public void setC(Consultation c) {
		this.c = c;
	}


	
	// UTILITIES //
	
	@Override
	public String toString() 
	{
		if( this.c != null )
		{
			return "Analyse [nom=" + nom + ", resultas=" + resultas + 
				", commentaire=" + commentaire + ", IDc = " + c.getIdConsultation() + "]";
		}else
		{
			return "Analyse [nom=" + nom + ", resultas=" + resultas + 
				", commentaire=" + commentaire + "]";
		}
		
	}
	
	public void changeAnalyse (String comm , String resu)
	{
		SQLite.updateAnalyse(this, comm, resu);
	}
	
	
	
}
