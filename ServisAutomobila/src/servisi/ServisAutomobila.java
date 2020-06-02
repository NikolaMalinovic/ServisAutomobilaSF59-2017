package servisi;

import java.util.ArrayList;


public class ServisAutomobila extends Automobil{

	protected String serviser; //serviser
	protected int termin;
	protected String opis;
	protected ArrayList<ServisniDeo> delovi;
	protected boolean status;
	
	public ServisAutomobila() {
		this.serviser = "";
		this.termin = 0;
		this.opis = "";
		this.status = false;
		
	}

	
	
	
	


	public ServisAutomobila(String vlasnik, MarkaAutomobila marka, ModelAutomobila model, double godinaProizvodnje,
			double zapreminaMotora, double snagaMotora, String vrstaGoriva, String serviser, int termin, String opis,
			ArrayList<ServisniDeo> delovi, boolean status) {
		super(vlasnik, marka, model, godinaProizvodnje, zapreminaMotora, snagaMotora, vrstaGoriva);
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.delovi = delovi;
		this.status = status;
	}







	public String getServiser() {
		return serviser;
	}

	public void setServiser(String serviser) {
		this.serviser = serviser;
	}

	public int getTermin() {
		return termin;
	}

	public void setTermin(int termin) {
		this.termin = termin;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<ServisniDeo> getDelovi() {
		return delovi;
	}

	public void setDelovi(ArrayList<ServisniDeo> delovi) {
		this.delovi = delovi;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}







	@Override
	public String toString() {
		return "ServisAutomobila [serviser=" + serviser + ", termin=" + termin + ", opis=" + opis + ", delovi=" 
				+ delovi + ", status=" + status + ", vlasnik=" + vlasnik + ", marka=" + marka + ", model=" 
				+ model + ", godinaProizvodnje=" + godinaProizvodnje + ", zapreminaMotora=" + zapreminaMotora + ", snagaMotora=" 
				+ snagaMotora +", vrstaGoriva=" + vrstaGoriva + "]";
	}







	




	
	
	
	
	
}
