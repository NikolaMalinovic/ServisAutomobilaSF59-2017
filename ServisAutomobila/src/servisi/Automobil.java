package servisi;



public abstract class Automobil {

	public String vlasnik;
	public MarkaAutomobila marka;
	public ModelAutomobila model;
	public double godinaProizvodnje;
	public double zapreminaMotora;
	public double snagaMotora;
	public String vrstaGoriva;
	public ServisnaKnjizica servisnaKnjizica;
	
	
	public Automobil() {
		
		this.vlasnik = "";
		this.marka = MarkaAutomobila.FIAT;
		this.model = ModelAutomobila.STILO;
		this.godinaProizvodnje = 0;
		this.zapreminaMotora = 0;
		this.snagaMotora = 0;
		this.vrstaGoriva = "";
	}
	
	
	public Automobil(String vlasnik, MarkaAutomobila marka, ModelAutomobila model, double godinaProizvodnje,
			double zapreminaMotora, double snagaMotora, String vrstaGoriva) {
		super();
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.vrstaGoriva = vrstaGoriva;
	}
	public String getVlasnik() {
		return vlasnik;
	}
	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
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
	public double getGodinaProizvodnje() {
		return godinaProizvodnje;
	}
	public void setGodinaProizvodnje(double godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
	public double getZapreminaMotora() {
		return zapreminaMotora;
	}
	public void setZapreminaMotora(double zapreminaMotora) {
		this.zapreminaMotora = zapreminaMotora;
	}
	public double getSnagaMotora() {
		return snagaMotora;
	}
	public void setSnagaMotora(double snagaMotora) {
		this.snagaMotora = snagaMotora;
	}
	public String getVrstaGoriva() {
		return vrstaGoriva;
	}
	public void setVrstaGoriva(String vrstaGoriva) {
		this.vrstaGoriva = vrstaGoriva;
	}
	
	
	@Override
	public String toString() {
		return "Automobil [vlasnik=" + vlasnik + ", marka=" + marka + ", model=" + model + ", godinaProizvodnje="
				+ godinaProizvodnje + ", zapreminaMotora=" + zapreminaMotora + ", snagaMotora=" + snagaMotora
				+ ", vrstaGoriva=" + vrstaGoriva + "]";
	}
	
	
	
	//promeniti u protected
	
	
}
