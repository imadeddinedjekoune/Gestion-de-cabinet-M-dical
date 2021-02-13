package Calendrier;
import java.util.Calendar;

public class Fun {
	static int Max (int annee , int mois )
	{
		Calendar c = Calendar.getInstance();
		c.set(annee, mois , 01);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH) ;
	}
	
	@SuppressWarnings("deprecation")
	static int Day (int annee , int mois )
	{
		Calendar c = Calendar.getInstance();
		c.set(annee, mois , 01);
		return c.getTime().getDay();
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
	
}
