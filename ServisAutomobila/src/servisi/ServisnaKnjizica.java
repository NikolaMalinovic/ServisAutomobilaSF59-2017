package servisi;

import java.util.ArrayList;



public class ServisnaKnjizica   {
	
	public String idKnjizica;
	public String automobili;
	ArrayList<ServisAutomobila> servisAutomobila;

	public ServisnaKnjizica() {
		this.idKnjizica = "";
		this.automobili = "";
		this.servisAutomobila = new ArrayList<ServisAutomobila>();
	}
	
	public ServisnaKnjizica(String idKnjizica, String automobili, ArrayList<ServisAutomobila> servisAutomobila) {
		super();
		this.idKnjizica = idKnjizica;
		this.automobili = automobili;
		this.servisAutomobila = servisAutomobila;
	}
	
	
	

	


	



	



	public String getAutomobili() {
		return automobili;
	}

	public void setAutomobili(String automobili) {
		this.automobili = automobili;
	}

	public String getIdKnjizica() {
		return idKnjizica;
	}









	public void setIdKnjizica(String idKnjizica) {
		this.idKnjizica = idKnjizica;
	}









	public ArrayList<ServisAutomobila> getServisAutomobila() {
		return servisAutomobila;
	}









	public void setServisAutomobila(ArrayList<ServisAutomobila> servisAutomobila) {
		this.servisAutomobila = servisAutomobila;
	}

	@Override
	public String toString() {
		return "ServisnaKnjizica [idKnjizica=" + idKnjizica + ", automobili=" + automobili + ", servisAutomobila="
				+ servisAutomobila + "]";
	}



	





	
	
}
