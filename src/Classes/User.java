package Classes;

public class User extends Personne
{
	private String user , pass ;

	
	public User() 
	{
		
	}


	public User(String Nom, String Prenom, String Email, String Tel
					, String user, String pass) 
	{
		super(Nom, Prenom, Email, Tel);
		this.user = user;
		this.pass = pass;
	}
	
	
	public String getUser() 
	{
		return user;
	}
	public void setUser(String user) 
	{
		this.user = user;
	}
	public String getPass() 
	{
		return pass;
	}
	public void setPass(String pass) 
	{
		this.pass = pass;
	}
	

}
