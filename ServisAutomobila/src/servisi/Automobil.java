package servisi;

import java.util.ArrayList;

public class Automobil {

	protected String identifikacioniKod;
	protected String vlasnik; //musterija
	protected MarkaAutomobila marka;
	protected ModelAutomobila model;
	protected int godinaProizvodnje;
	protected int zapreminaMotora;
	protected int snagaMotora;
	protected VrstaGoriva vrstaGoriva;
	protected ArrayList<ServisnaKnjizica> servisnaKnjizica;
	//public ServisnaKnjizica servisnaKnjizica;
//	public String servisnaKnjizica;
	
	public Automobil() {
		
		this.identifikacioniKod = "";
		this.vlasnik = "";
		this.marka = MarkaAutomobila.FIAT;
		this.model = ModelAutomobila.STILO;
		this.godinaProizvodnje = 0;
		this.zapreminaMotora = 0;
		this.snagaMotora = 0;
		this.vrstaGoriva = VrstaGoriva.BENZIN;
		this.servisnaKnjizica = new ArrayList<ServisnaKnjizica>();
		
	}
	
	
	public Automobil(String identifikacioniKod,String vlasnik, MarkaAutomobila marka, ModelAutomobila model, int godinaProizvodnje,
			int zapreminaMotora, int snagaMotora, VrstaGoriva vrstaGoriva, ArrayList<ServisnaKnjizica> servisnaKnjizica) {
		super();
		this.identifikacioniKod = identifikacioniKod;
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.vrstaGoriva = vrstaGoriva;
		
	}
	
	
	
	
	public ArrayList<ServisnaKnjizica> getServisnaKnjizica() {
		return servisnaKnjizica;
	}


	public void setServisnaKnjizica(ArrayList<ServisnaKnjizica> servisnaKnjizica) {
		this.servisnaKnjizica = servisnaKnjizica;
	}


	public String getIdentifikacioniKod() {
		return identifikacioniKod;
	}


	public void setIdentifikacioniKod(String identifikacioniKod) {
		this.identifikacioniKod = identifikacioniKod;
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
		return "Automobil [identifikacioniKod=" + identifikacioniKod + ", vlasnik=" + vlasnik + ", marka=" + marka + ", model=" + model + ", godinaProizvodnje="
				+ godinaProizvodnje + ", zapreminaMotora=" + zapreminaMotora + ", snagaMotora=" + snagaMotora
				+ ", vrstaGoriva=" + vrstaGoriva + ",servisnaKnjizica=" + servisnaKnjizica + "]";
	}
	
	
	
	//promeniti u protected
	
	
}
