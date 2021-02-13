package Classes;

public class Details 
{
	private float dose ;
	private String dure;
	private Ordonance ordonance ;
	private Medicament medicament ;
	
	public Details(float dose, String dure) {
		super();
		this.dose = dose;
		this.dure = dure;
	}

	public float getDose() {
		return dose;
	}

	public void setDose(float dose) {
		this.dose = dose;
	}

	public String getDure() {
		return dure;
	}

	public void setDure(String dure) {
		this.dure = dure;
	}

	public Ordonance getOrdonance() {
		return ordonance;
	}

	public void setOrdonance(Ordonance ordonance) {
		this.ordonance = ordonance;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

}
