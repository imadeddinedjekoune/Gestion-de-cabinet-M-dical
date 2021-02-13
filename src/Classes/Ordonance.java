package Classes;

import java.util.ArrayList;

import SQLite.SQLite;

public class Ordonance 
{
	private String idOrdonance ;
	private ArrayList<Details> details ;
	
	public Ordonance(String idOrdonance) 
	{
		super();
		this.idOrdonance = idOrdonance;
	}
	
	
	public String getIdOrdonance() {
		return idOrdonance;
	}
	public void setIdOrdonance(String idOrdonance) {
		this.idOrdonance = idOrdonance;
	}
	public ArrayList<Details> getDetails() {
		return details;
	}
	public void setDetails(ArrayList<Details> details) {
		this.details = details;
	}
	
	public ArrayList<Medicament> getMedicaments() 
	{
		return SQLite.selectMedicament(this);
	}
	public void addMedciament (Medicament m)
	{
		SQLite.insertTableMedicament(m.getNomMed());
		SQLite.insertTableTraitement(m.getNomMed(), idOrdonance, m.getD().getDure(),m.getD().getDose());
	}
}
