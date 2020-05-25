package servisi;

public class ServisniDeo {

	private MarkaAutomobila marka;
	private ModelAutomobila model;
	private String deo;
	private double cena;
	
	public ServisniDeo() {
		super();
		this.marka = MarkaAutomobila.FIAT;
		this.model = ModelAutomobila.STILO;
		this.deo = "";
		this.cena = 0;
	}

	
	
	
	public ServisniDeo(MarkaAutomobila marka, ModelAutomobila model, String deo, double cena) {
		super();
		this.marka = marka;
		this.model = model;
		this.deo = deo;
		this.cena = cena;
	}




	public MarkaAutomobila getMarka() {
		return marka;
	}

	public void setMarka(MarkaAutomobila marka) {
		this.marka = marka;
	}

	public ModelAutomobila getModel() {
		return model;
	}

	public void setModel(ModelAutomobila model) {
		this.model = model;
	}

	public String getDeo() {
		return deo;
	}

	public void setDeo(String deo) {
		this.deo = deo;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}


	@Override
	public String toString() {
		return "ServisniDeo [marka=" + marka + ", model=" + model + ", deo=" + deo + ", cena=" + cena + "]";
	}
	
	
	
	
	
}
