package servis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import korisnici.Administrator;
import korisnici.Korisnici;
import korisnici.Musterija;
import korisnici.Pol;
import korisnici.Serviser;
import korisnici.SpecijalizacijaServisera;
import servisi.Automobil;
import servisi.MarkaAutomobila;
import servisi.ModelAutomobila;
import servisi.ServisAutomobila;
import servisi.ServisnaKnjizica;
import servisi.ServisniDeo;
import servisi.VrstaGoriva;

public class Servis {
	
	private ArrayList<Musterija> musterija;
	private ArrayList<Serviser> serviser;
	private ArrayList<Administrator> administrator;
	private ArrayList<Korisnici> korisnik;
	private ArrayList<Automobil> automobil;
	private ArrayList<ServisniDeo> deo;
	private ArrayList<ServisnaKnjizica> servisnaKnjizica;
	private ArrayList<ServisAutomobila> servisAutomobila;
	
	public Servis() {
		
		this.musterija = new ArrayList<Musterija>();
		this.serviser = new ArrayList<Serviser>();
		this.administrator = new ArrayList<Administrator>();
		this.korisnik = new ArrayList<Korisnici>();
		this.automobil = new ArrayList<Automobil>();
		this.deo = new ArrayList<ServisniDeo>();
		this.servisnaKnjizica = new ArrayList<ServisnaKnjizica>();
	}
	
//-------------------------------------KORISNICI---------------------------------------
	public ArrayList<Korisnici> getKorisnici() {
		return korisnik;
	}
	
	public void dodajKorisnika(Korisnici korisnik) {
		this.korisnik.add(korisnik);
	}
	
	public void obrisiKorisnika(Korisnici korisnik) {
		this.korisnik.remove(korisnik);
	}
	
	public Korisnici nadjiKorisnika(String korIme) {
		
		for(Korisnici kor: korisnik) {
			if( kor.getKorIme().equalsIgnoreCase(korIme)) {
				return kor;
			}
		}
		return null;
	}
	
	public Korisnici login(String korisnickoIme, String sifra) {
		for(Korisnici korisnik: korisnik) {
			if(korisnik.getKorIme().equals(korisnickoIme) && korisnik.getLozinka().equals(sifra)){
				return korisnik;	
			}
				
		}
		return null;
	}
	
//-------------------------------------MUSTERIJA------------------------------------------
	public ArrayList<Musterija> getMusterija() {
		return musterija;
	}
	
	public void dodajMusteriju(Musterija musterija) {
		this.musterija.add(musterija);
	}
	
	public void obrisiMusteriju(Musterija musterija) {
		this.musterija.remove(musterija);
	}
	
	public Musterija nadjiMusteriju(String korIme) {
		for(Musterija musterija: musterija) {
			if( musterija.getKorIme().equalsIgnoreCase(korIme)) {
				return musterija;
			}
		}
		return null;
	}
//-------------------------------------SERVISER-------------------------------------------
	public ArrayList<Serviser> getServiser() {
		return serviser;
	}
	
	public void dodajServisera(Serviser serviser) {
		this.serviser.add(serviser);
	}
	
	public void obrisiServisera(Serviser serviser) {
		this.serviser.remove(serviser);
	}

	public Serviser nadjiServisera(String korIme) {
		for(Serviser serviser: serviser) {
			if(serviser.getKorIme().equalsIgnoreCase(korIme)) {
				return serviser;
			}
		}
		return null;
	}
//-------------------------------------ADMINISTRATOR---------------------------------------
	public ArrayList<Administrator> getAdministrator() {
		return administrator;
	}
	
	public void dodajAdministratora(Administrator administrator) {
		this.administrator.add(administrator);
	}
	
	public void obrisiAdministratora(Administrator administrator) {
		this.administrator.remove(administrator);
	}
	
	public Administrator nadjiAdministratora(String korIme) {
		for(Administrator administrator: administrator) {
			if(administrator.getKorIme().equalsIgnoreCase(korIme)) {
				return administrator;
			}
		}
		return null;
	}
//-------------------------------------UCITAVANJE KORISNIKA-----------------------------------------
	public void ucitajKorisnike() {
		try {
			File korisniciFile = new File("src/fajlovi/korisnici.txt");
			BufferedReader reader = new BufferedReader(new FileReader(korisniciFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String uloga = split[0];
				String ime = split[1];
				String prezime = split[2];
				String jmbg = split[3];
				int polInt = Integer.parseInt(split[4]);
				Pol pol = Pol.fromInt(polInt);
				String adresa = split[5];
				String brojTelefona = split[6];
				String korIme = split[7];
				String lozinka = split[8];
				Korisnici kor = new Korisnici(uloga, ime, prezime,jmbg,pol, adresa,brojTelefona, korIme,lozinka) {};
				korisnik.add(kor);
				if (uloga.contentEquals("Administrator")) {
					String plata = split[9];
					Administrator administratori = new Administrator(uloga, ime, prezime,jmbg,pol, adresa,brojTelefona, korIme,lozinka,plata);
					administrator.add(administratori);
					
				} else if (uloga.contentEquals("Musterija")) {
					String brojNagradnihPoena =split[9];
					Musterija musterije = new Musterija(uloga, ime, prezime,jmbg,pol, adresa,brojTelefona, korIme,lozinka,brojNagradnihPoena);
					musterija.add(musterije);
				} else if (uloga.contentEquals("Serviser")) {
					double plata = Double.parseDouble(split[9]);
					int specijalizacijaInt = Integer.parseInt(split[10]);
					SpecijalizacijaServisera specijalizacijaServisera = SpecijalizacijaServisera.fromInt(specijalizacijaInt);
					Serviser serviseri = new Serviser(uloga, ime, prezime,jmbg,pol, adresa,brojTelefona, korIme,lozinka,plata,specijalizacijaServisera);
					serviser.add(serviseri);
				}
				
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//-------------------------------------SNIMI KORISNIKA-----------------------------------------
	public void snimiKorisnika() {
		try {
			File korisniciFile = new File("src/fajlovi/korisnici.txt");
			String content = "";
			for(Musterija musterije: musterija) {
				content += "Musterija|" +
							musterije.getIme() + "|" +
							musterije.getPrezime() + "|" +
							musterije.getJmbg() + "|" +
							Pol.toInt(musterije.getPol()) + "|" +
							musterije.getAdresa() + "|" +
							musterije.getBrojTelefona() + "|" +
							musterije.getKorIme() + "|" +
							musterije.getLozinka() + "|" +
							musterije.getBrojNagradnihBodova() + "\n";
			}
			for (Serviser serviseri: serviser) {
				content += "Serviser|" +
						serviseri.getIme() + "|" +
						serviseri.getPrezime() + "|" +
						serviseri.getJmbg() + "|" +
						Pol.toInt(serviseri.getPol()) + "|" +
						serviseri.getAdresa() + "|" +
						serviseri.getBrojTelefona() + "|" +
						serviseri.getKorIme() + "|" +
						serviseri.getLozinka() + "|" +
						serviseri.getPlata() + "|" +
						SpecijalizacijaServisera.toInt(serviseri.getSpecijalizacija()) + "\n";
			}
			for(Administrator administratori: administrator) {
				content += "Administrator|" +
						administratori.getIme() + "|" +
						administratori.getPrezime() + "|" +
						administratori.getJmbg() + "|" +
						Pol.toInt(administratori.getPol()) + "|" +
						administratori.getAdresa() + "|" +
						administratori.getBrojTelefona() + "|" +
						administratori.getKorIme() + "|" +
						administratori.getLozinka() + "|" +
						administratori.getPlata() + "\n";
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(korisniciFile));
			bw.write(content);
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
//-------------------------------------UCITAVANJE AUTOMOBILA-----------------------------------------
	public ArrayList<Automobil> getAutomobil() {
		return automobil;
	}
	
	public void dodajAutomobil(Automobil automobil) {
		this.automobil.add(automobil);
	}
	
	public void obrisiAutomobil(Automobil automobil) {
		this.automobil.remove(automobil);
	}
	
	public Automobil nadjiAutomobil(String naziv) {
		for(Automobil automobili: automobil) {
			if(automobili.getVlasnik().equals(naziv)) {
				return automobili;
			}
		}
		return null;
	}
	
	public void ucitajAutomobile() {
		try {
			File automobiliFile = new File("src/fajlovi/automobili.txt");
			BufferedReader br = new BufferedReader(new FileReader(automobiliFile));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String vlasnik = split[0];
				int markaInt = Integer.parseInt(split[1]);
				MarkaAutomobila markaAutomobila = MarkaAutomobila.fromInt(markaInt);
				int modelInt = Integer.parseInt(split[2]);
				ModelAutomobila modelAutomobila = ModelAutomobila.fromInt(modelInt);
				int godinaProizvodnje = Integer.parseInt(split[3]);
				int zapreminaMotora = Integer.parseInt(split[4]);
				int snagaMotora = Integer.parseInt(split[5]);
				int gorivoInt = Integer.parseInt(split[6]);
				VrstaGoriva vrstaGoriva = VrstaGoriva.fromInt(gorivoInt);
				
			//	ArrayList<ServisnaKnjizica> servisnaKnjizica = new ArrayList<ServisnaKnjizica>();
				Automobil automobili = new Automobil(vlasnik,markaAutomobila,modelAutomobila,godinaProizvodnje,zapreminaMotora,snagaMotora,vrstaGoriva) {};
				automobil.add(automobili);
				
				
				
			}
			br.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
//-------------------------------------SNIMI AUTOMOBIL-----------------------------------------	
		public void snimiAutomobil() {
			try {
				File automobiliFile = new File("src/fajlovi/automobili.txt");
				String content = "";
				for(Automobil auto: automobil) {
					content += 
							auto.getVlasnik() + "|" +
							MarkaAutomobila.toInt(auto.getMarka()) + "|" +
							ModelAutomobila.toInt(auto.getModel()) + "|" +
							auto.getGodinaProizvodnje() + "|" +
							auto.getZapreminaMotora() + "|" +
							auto.getSnagaMotora() + "|" +
							VrstaGoriva.toInt(auto.getVrstaGoriva()) +  "\n";
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(automobiliFile));
				bw.write(content);
				bw.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
//-------------------------------------UCITAVANJE DELOVA-----------------------------------------
	public ArrayList<ServisniDeo> getDelovi() {
		return deo;
	}
	
	public void dodajDeo(ServisniDeo deo) {
		this.deo.add(deo);
	}
	
	public void obrisiDeo(ServisniDeo deo) {
		this.deo.remove(deo);
	}
	
	public ServisniDeo nadjiDeo(String naziv) {
		for(ServisniDeo servisniDeo: deo) {
			if(servisniDeo.getDeo().equalsIgnoreCase(naziv)) {
				return servisniDeo;
			}
		}
		return null;
	}
	
	public void ucitajDelove() {
		try {
			File deloviFile = new File("src/fajlovi/delovi.txt");
			BufferedReader br = new BufferedReader(new FileReader(deloviFile));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				int markaInt = Integer.parseInt(split[0]);
				MarkaAutomobila markaAutomobila = MarkaAutomobila.fromInt(markaInt);
				int modelInt = Integer.parseInt(split[1]);
				ModelAutomobila modelAutomobila = ModelAutomobila.fromInt(modelInt);
				String nazivDela = split[2];
				double cena = Double.parseDouble(split[3]);
				ServisniDeo servisniDeo = new ServisniDeo(markaAutomobila,modelAutomobila,nazivDela,cena);
				deo.add(servisniDeo);
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
//-------------------------------------SNIMI DEO-----------------------------------------	
	public void snimiDeo() {
		try {
			File deloviFile = new File("src/fajlovi/delovi.txt");
			String content = "";
			for(ServisniDeo servisniDelovi: deo) {
				content += 
						MarkaAutomobila.toInt(servisniDelovi.getMarka()) + "|" +
						ModelAutomobila.toInt(servisniDelovi.getModel()) + "|" +
						servisniDelovi.getDeo() + "|" +
						servisniDelovi.getCena() +  "\n";
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(deloviFile));
			bw.write(content);
			bw.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
//-------------------------------------UCITAVANJE SERVISA-----------------------------------------	
	public ArrayList<ServisAutomobila> getServise() {
		return servisAutomobila;
	}
	
	public void dodajServis(ServisAutomobila servis) {
		this.servisAutomobila.add(servis);
	}
	
	public void obrisiServis(ServisAutomobila servis) {
		this.servisAutomobila.remove(servis);
	}
	
	public ServisAutomobila nadjiServis(String naziv) {
		for(ServisAutomobila servis: servisAutomobila) {
			if(servis.getOpis().equalsIgnoreCase(naziv)) {
				return servis;
			}
		}
		return null;
	}
	
	public void ucitajServise() {
		try {
			File servisiFile = new File("src/fajlovi/servisAutomobila.txt");
			BufferedReader br = new BufferedReader(new FileReader(servisiFile));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				
				String vlasnik = split[0];
				int markaInt = Integer.parseInt(split[1]);
				MarkaAutomobila markaAutomobila = MarkaAutomobila.fromInt(markaInt);
				int modelInt = Integer.parseInt(split[2]);
				ModelAutomobila modelAutomobila = ModelAutomobila.fromInt(modelInt);
				double godinaProizvodnje = Double.parseDouble(split[3]);
				double zapreminaMotora = Double.parseDouble(split[4]);
				double snagaMotora = Double.parseDouble(split[5]);
				String vrstaGoriva = split[6];
				String serviser = split[7];
				int termin = Integer.parseInt(split[8]);
				String opis = split[9];
				ArrayList<ServisniDeo> deo = new ArrayList<ServisniDeo>();
				boolean status = Boolean.parseBoolean(split[11]);
				
				ServisAutomobila servis = new ServisAutomobila(vlasnik,markaAutomobila,modelAutomobila,godinaProizvodnje,zapreminaMotora,snagaMotora,vrstaGoriva,serviser,termin,opis,deo,status);
				servisAutomobila.add(servis);
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//protected ServisnaKnjizica servisnaKnjizica;
//-------------------------------------UCITAVANJE SERVISNIH KNJIZICA-----------------------------------------
	public ArrayList<ServisnaKnjizica> getKnjizice() {
		return servisnaKnjizica;
	}
	
	public void dodajKnjizicu(ServisnaKnjizica knjizica) {
		this.servisnaKnjizica.add(knjizica);
	}
	
	public void obrisiKnjizicu(ServisnaKnjizica knjizica) {
		this.servisnaKnjizica.remove(knjizica);
	}
	
	public ServisnaKnjizica nadjiKnjizicu(String naziv) {
		for(ServisnaKnjizica knjizica: servisnaKnjizica) {
			if(knjizica.getVlasnik().equalsIgnoreCase(naziv)) {
				return knjizica;
			}
		}
		return null;
	}
	
	public void ucitajKnjizice() {
		try {
			File knjiziceFile = new File("src/fajlovi/servisnaKnjizica.txt");
			BufferedReader br = new BufferedReader(new FileReader(knjiziceFile));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				
				String vlasnik = split[0];
				int markaInt = Integer.parseInt(split[1]);
				MarkaAutomobila markaAutomobila = MarkaAutomobila.fromInt(markaInt);
				int modelInt = Integer.parseInt(split[2]);
				ModelAutomobila modelAutomobila = ModelAutomobila.fromInt(modelInt);
				double godinaProizvodnje = Double.parseDouble(split[3]);
				double zapreminaMotora = Double.parseDouble(split[4]);
				double snagaMotora = Double.parseDouble(split[5]);
				String vrstaGoriva = split[6];
			//	ArrayList<ServisnaKnjizica> servisi = new ArrayList<ServisnaKnjizica>();
			//	String servisi = split[7];
			//	String[] servisiSplit = servisi.split(";");
				
			//	ArrayList<ServisnaKnjizica> knjizice = new ArrayList<ServisnaKnjizica>();
				/*for (String k : servisiSplit) {
					ServisnaKnjizica s = nadjiKnjizicu(k);
					if(k != null) {
						knjizice.add(k);
					}
			
				}*/
				
			//	ServisnaKnjizica knjizica = new ServisnaKnjizica(vlasnik,markaAutomobila,modelAutomobila,godinaProizvodnje,zapreminaMotora,snagaMotora,vrstaGoriva);
			//	servisnaKnjizica.add(knjizica);
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
