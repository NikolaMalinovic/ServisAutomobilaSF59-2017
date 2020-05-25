package korisnici;

public abstract class Korisnici {

	public String uloga;
	public String  ime;
	public String prezime;
	public String jmbg;
	public Pol pol;
	public String adresa;
	public String brojTelefona;
	public String korIme;
	public String lozinka;
	
	public Korisnici() {
		this.uloga = "";
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.pol = Pol.MUSKI;
		this.adresa = "";
		this.brojTelefona = "";
		this.korIme = "";
		this.lozinka = "";
		
	}
	
	public Korisnici(String uloga, String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTelefona,
			String korIme, String lozinka) {
		super();
		this.uloga = uloga;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korIme = korIme;
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getKorIme() {
		return korIme;
	}

	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return "Korisnici [uloga=" + uloga + ",ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol + ", adresa="
				+ adresa + ", brojTelefona=" + brojTelefona + ", korIme=" + korIme + ", lozinka=" + lozinka + "]";
	}

	
	
	
	
}
