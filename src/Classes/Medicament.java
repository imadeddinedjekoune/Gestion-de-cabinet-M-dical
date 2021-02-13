package Classes;

import java.util.ArrayList;

import SQLite.SQLite;

public class Medicament 
{
	private String nomMed ;
	private Details d ;
	
	// constructeur //
	public Medicament(String nom,Details d) {
		super();
		this.nomMed = nom;
		this.setD(d) ;
	}
		
	public Medicament(String nom) 
	{
		super();
		this.nomMed = nom;
	}
	
	public String getNomMed() {
		return nomMed;
	}
	public void setNomMed(String nomMed) {
		this.nomMed = nomMed;
	}

	public Details getD() 
	{
		return d;
	}
	public void setD(Details d) 
	{
		this.d = d;
	}

	
	
	@Override
	public String toString() 
	{
		return "Medicament [nomMed=" + nomMed + ", d=" + d + "]";
	}
	
	public static ArrayList<Medicament> getAllStock ()
	{
		return SQLite.selectStock();
	}
	
	// Used In CheckLists //
	
	static public String [] maladieToString (ArrayList<Medicament> m)
	{
		if (m != null)
		{
			String str [] = new String[m.size()];
			int i = 0 ;
			while (i<str.length)
			{
				str[i] = m.get(i).getNomMed();
				i++;
			}
			return str ;
		}else
		{
			return null ;
		}
	}
	
	
	
}
