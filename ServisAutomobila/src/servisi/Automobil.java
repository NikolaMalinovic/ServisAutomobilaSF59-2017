package servisi;



public abstract class Automobil {

	public String vlasnik; //musterija
	public MarkaAutomobila marka;
	public ModelAutomobila model;
	public int godinaProizvodnje;
	public int zapreminaMotora;
	public int snagaMotora;
	public VrstaGoriva vrstaGoriva;
	//public ServisnaKnjizica servisnaKnjizica;
//	public String servisnaKnjizica;
	
	public Automobil() {
		
		this.vlasnik = "";
		this.marka = MarkaAutomobila.FIAT;
		this.model = ModelAutomobila.STILO;
		this.godinaProizvodnje = 0;
		this.zapreminaMotora = 0;
		this.snagaMotora = 0;
		this.vrstaGoriva = VrstaGoriva.BENZIN;
		
	}
	
	
	public Automobil(String vlasnik, MarkaAutomobila marka, ModelAutomobila model, int godinaProizvodnje,
			int zapreminaMotora, int snagaMotora, VrstaGoriva vrstaGoriva) {
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
	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}
	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
	public int getZapreminaMotora() {
		return zapreminaMotora;
	}
	public void setZapreminaMotora(int zapreminaMotora) {
		this.zapreminaMotora = zapreminaMotora;
	}
	public int getSnagaMotora() {
		return snagaMotora;
	}
	public void setSnagaMotora(int snagaMotora) {
		this.snagaMotora = snagaMotora;
	}
	public VrstaGoriva getVrstaGoriva() {
		return vrstaGoriva;
	}
	public void setVrstaGoriva(VrstaGoriva vrstaGoriva) {
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
