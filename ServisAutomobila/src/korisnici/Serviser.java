package korisnici;

public class Serviser extends Korisnici {

	private double plata;
	private SpecijalizacijaServisera specijalizacija;
	
	
	
	
	public Serviser() {
		this.plata = 0;
		this.specijalizacija = SpecijalizacijaServisera.AUTOELEKTRICAR;
	}




	public Serviser(String uloga, String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTelefona, String korIme,
			String lozinka, double plata, SpecijalizacijaServisera specijalizacija) {
		super(uloga, ime, prezime, jmbg, pol, adresa, brojTelefona, korIme, lozinka);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}




	public double getPlata() {
		return plata;
	}




	public void setPlata(double plata) {
		this.plata = plata;
	}


	

	




	public SpecijalizacijaServisera getSpecijalizacija() {
		return specijalizacija;
	}




	public void setSpecijalizacija(SpecijalizacijaServisera specijalizacija) {
		this.specijalizacija = specijalizacija;
	}




	@Override
	public String toString() {
		return "Serviser [plata=" + plata + ", specijalizacija=" + specijalizacija + ", uloga=" + ime +",ime=" + ime + ", prezime="
				+ prezime + ", jmbg=" + jmbg + ", pol=" + pol + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona
				+ ", korIme=" + korIme + ", lozinka=" + lozinka + "]";
	}
	
	
}
