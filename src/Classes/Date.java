package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date 
{
	private int jour , mois , annee ;

	// CONSTRUCTORS //
	public Date ()
	{
		
	}
	public Date ( int j , int m , int a )
	{
		if (valide(j,m,a))
		{
			jour = j ;
			mois = m ;
			annee = a ;
		}
	}
	
	// SETTERS AND GETTERS //
	public int getJour() 
	{
		return jour;
	}
	public void setJour(int jour) 
	{
		this.jour = jour;
	}
	public int getMois() 
	{
		return mois;
	}
	public void setMois(int mois) 
	{
		this.mois = mois;
	}
	public int getAnnee() 
	{
		return annee;
	}
	public void setAnnee(int annee) 
	{
		this.annee = annee;
	}
	
	// UTILITIES //
	private boolean valide (int j , int m , int a)
	{
		return ( j>0 && m>0 && a>=0 );
	}
	public String toString ()
	{
		return ""+annee+"-"+returnDateModify(mois)+"-"+returnDateModify(jour);
	}
	public static String returnDateModify(int i)
	{
		if ( i < 10 )
		{
			return "0"+i;
		}else
		{
			return ""+i;
		}
	}
	public static Date StringtoDate (String d)
	{
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new Date(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
	}
	public static boolean suppOuEgale (Date d1 , Date d2)
	{
		if (d1.annee >= d2.annee)
		{
			if (d1.annee == d2.annee)
			{
				if (d1.mois >= d2.mois)
				{
					if (d1.mois == d2.mois )
					{
						if (d1.jour >= d2.jour)
						{
							return true ;
						}else
						{
							return false;
						}
					}else
					{
						return true ;
					}
				}else
				{
					return false ;
				}
			}else
			{
				return true ;
			}
		}else
		{
			return false ;
		}
	}
	
	public static boolean infOuEgale (Date d1 , Date d2)
	{
		// D1 = 2001 09 05 -- D2 = 2000 09 05
		if (d1.annee <= d2.annee)
		{
			if (d1.annee == d2.annee)
			{
				if (d1.mois <= d2.mois)
				{
					if (d1.mois == d2.mois )
					{
						if (d1.jour <= d2.jour)
						{
							return true ;
						}else
						{
							return false;
						}
					}else
					{
						return true ;
					}
				}else
				{
					return false ;
				}
			}else
			{
				return true ;
			}
		}else
		{
			return false ;
		}
	}
}
