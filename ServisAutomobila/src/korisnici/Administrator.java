package korisnici;

public class Administrator extends Korisnici {

	private String plata;

	public Administrator() {
		this.plata = "";
	}
	
	
	
	
	public Administrator(String uloga, String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTelefona,
			String korIme, String lozinka, String plata) {
		super(uloga, ime, prezime, jmbg, pol, adresa, brojTelefona, korIme, lozinka);
		this.plata = plata;
	}




	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	@Override
	public String toString() {
		return "Administrator [plata=" + plata + ",uloga=" + uloga + " ,ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol="
				+ pol + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona + ", korIme=" + korIme + ", lozinka="
				+ lozinka + "]";
	}

	
	
	
	
	
}
