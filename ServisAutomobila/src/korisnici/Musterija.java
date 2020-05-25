package korisnici;

public class Musterija extends Korisnici{

	private String brojNagradnihBodova;

	
	
	
	
	
	
	public Musterija() {
		this.brojNagradnihBodova = "";
	}





	public Musterija(String uloga,String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTelefona,
			String korIme, String lozinka, String brojNagradnihBodova) {
		super(uloga, ime, prezime, jmbg, pol, adresa, brojTelefona, korIme, lozinka);
		this.brojNagradnihBodova = brojNagradnihBodova;
	}





	public String getBrojNagradnihBodova() {
		return brojNagradnihBodova;
	}





	public void setBrojNagradnihBodova(String brojNagradnihBodova) {
		this.brojNagradnihBodova = brojNagradnihBodova;
	}





	@Override
	public String toString() {
		return "Musterija [brojNagradnihBodova=" + brojNagradnihBodova + ",uloga=" + uloga + ", ime=" + ime + ", prezime=" + prezime
				+ ", jmbg=" + jmbg + ", pol=" + pol + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona
				+ ", korIme=" + korIme + ", lozinka=" + lozinka + "]";
	}
	
	
}
