package Calendrier;

public class Date 
{
	public int annee , mois , jour ;
	public Date (int a , int m , int j )
	{
		annee = a ;
		mois = m ;
		jour = j ;
	}
	public String toString()
	{
		return "["+annee+"-"+Fun.returnDateModify(mois)+"-"+Fun.returnDateModify(jour)+"]";
	}
	
}
