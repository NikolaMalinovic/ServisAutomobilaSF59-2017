package servisi;

public class ServisniDeo {

	public String identifikacioniKodServisa;
	public MarkaAutomobila marka;
	public ModelAutomobila model;
	public String deo;
	public double cena;
	
	public ServisniDeo() {
		super();
		this.identifikacioniKodServisa = "";
		this.marka = MarkaAutomobila.FIAT;
		this.model = ModelAutomobila.STILO;
		this.deo = "";
		this.cena = 0;
	}

	
	
	
	public ServisniDeo(String identifikacioniKodServisa,MarkaAutomobila marka, ModelAutomobila model, String deo, double cena) {
		super();
		this.identifikacioniKodServisa = identifikacioniKodServisa;
		this.marka = marka;
		this.model = model;
		this.deo = deo;
		this.cena = cena;
	}




	public String getIdentifikacioniKodServisa() {
		return identifikacioniKodServisa;
	}




	public void setIdentifikacioniKodServisa(String identifikacioniKodServisa) {
		this.identifikacioniKodServisa = identifikacioniKodServisa;
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
		return "ServisniDeo [identifikacioniKodSerivsa=" + identifikacioniKodServisa + ",marka=" + marka + ", model=" + model + ", deo=" + deo + ", cena=" + cena + "]";
	}
	
	
	
	
	
}
