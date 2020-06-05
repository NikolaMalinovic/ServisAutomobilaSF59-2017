package servisi;

import java.util.ArrayList;


public class ServisAutomobila extends Automobil{

	public String serviser; //serviser
	public String termin;
	public String opis;
	public String delovi;
	public String status;
	
	public ServisAutomobila() {
		this.serviser = "";
		this.termin = "";
		this.opis = "";
		this.status = "";
		this.delovi ="";
		
	}


	public ServisAutomobila(String identifikacioniKod,String vlasnik, MarkaAutomobila marka, ModelAutomobila model, int godinaProizvodnje,
			int zapreminaMotora, int snagaMotora, VrstaGoriva vrstaGoriva,ArrayList<ServisnaKnjizica> servisnaKnjizica,String serviser,String termin,String opis,String status,String delovi) {
		super(identifikacioniKod,vlasnik, marka, model, godinaProizvodnje, zapreminaMotora, snagaMotora, vrstaGoriva, servisnaKnjizica);
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

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getDelovi() {
		return delovi;
	}

	public void setDelovi(String delovi) {
		this.delovi = delovi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}







	@Override
	public String toString() {
		return "ServisAutomobila [serviser=" + serviser + ", termin=" + termin + ", opis=" + opis + ", delovi=" 
				+ delovi + ", status=" + status + ",identifikacioniKod=" + identifikacioniKod + ",  vlasnik=" + vlasnik + ", marka=" + marka + ", model=" 
				+ model + ", godinaProizvodnje=" + godinaProizvodnje + ", zapreminaMotora=" + zapreminaMotora + ", snagaMotora=" 
				+ snagaMotora +", vrstaGoriva=" + vrstaGoriva + ",servisnaKnjizica=" + servisnaKnjizica + "]";
	}







	




	
	
	
	
	
}
