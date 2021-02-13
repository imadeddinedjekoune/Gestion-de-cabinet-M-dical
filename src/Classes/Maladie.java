package Classes;

import java.util.ArrayList;

public class Maladie 
{
	private String nomMaladie ;
	private TypeMaladie typeMaladie ;
	
	public Maladie(String nomMaladie, TypeMaladie typeMaladie) 
	{
		super();
		this.nomMaladie = nomMaladie;
		this.typeMaladie = typeMaladie;
	}

	public String getNomMaladie() 
	{
		return nomMaladie;
	}
	
	public TypeMaladie getTypeMaladie() 
	{
		return typeMaladie;
	}
	
	public static ArrayList<Maladie> MaladieAvecTypeUnique (ArrayList<Maladie> maladieMixe,TypeMaladie typeChoisie)
	{
		ArrayList<Maladie> ret = new ArrayList<Maladie>();
		for ( int i = 0 ; i < maladieMixe.size() ; i++ )
		{
			if (maladieMixe.get(i).typeMaladie == typeChoisie)
			{
				ret.add(maladieMixe.get(i));
			}
		}
		return ret ;
	}
	// Used In CheckLists //
		static public String [] maladieToString (ArrayList<Maladie> m)
		{
			if (m != null)
			{
				String str [] = new String[m.size()];
				int i = 0 ;
				while (i<str.length)
				{
					str[i] = m.get(i).getNomMaladie();
					i++;
				}
				return str ;
			}else
			{
				return null ;
			}
		}
		
		public boolean egale (String s)
		{
			return (this.nomMaladie.equals(s));
		}
		
	@Override
	public String toString() {
		return "Maladie [nomMaladie=" + nomMaladie + ", typeMaladie=" + typeMaladie + "]";
	}
}
