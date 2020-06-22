package guiIzmenaiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import korisnici.Administrator;
import korisnici.Korisnici;
import korisnici.Musterija;
import korisnici.Pol;
import korisnici.Serviser;
import korisnici.SpecijalizacijaServisera;
import net.miginfocom.swing.MigLayout;

import servis.Servis;

public class ServiseriForma extends JFrame {

	
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJmbg = new JLabel("JMBG");
	private JTextField txtJmbg = new JTextField(13);
	
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrojTelefona = new JLabel("Broj telefona");
	private JTextField txtBrojTelefona = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblSifra = new JLabel("Sifra");
	private JTextField txtSifra = new JTextField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JComboBox<SpecijalizacijaServisera> cbSpecijalizacija = new JComboBox<SpecijalizacijaServisera>(SpecijalizacijaServisera.values());
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Servis servis;
	private Serviser serviser;
	
	public ServiseriForma(Servis servis,Serviser serviser) {
		this.servis = servis;
		this.serviser = serviser;
		if(this.serviser == null) {
			setTitle("Kreiranje novog korisnika");
		}else {
			setTitle("Izmena podataka -" + this.serviser.getIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		
		
		if(this.serviser != null) {
			txtIme.setText(this.serviser.getIme());
			txtPrezime.setText(this.serviser.getPrezime());
			txtJmbg.setText(this.serviser.getJmbg());
			txtAdresa.setText(this.serviser.getAdresa());
			txtBrojTelefona.setText(this.serviser.getBrojTelefona());
			txtKorisnickoIme.setText(this.serviser.getKorIme());
			txtSifra.setText(this.serviser.getLozinka());
			txtPlata.setText(String.valueOf(this.serviser.getPlata()));
			
		}
		
		add(lblIme); add(txtIme);
		add(lblPrezime); add(txtPrezime);
		add(lblPol); add(cbPol);
		add(lblJmbg); add(txtJmbg);
		add(lblAdresa); add(txtAdresa);
		add(lblBrojTelefona); add(txtBrojTelefona);
		add(lblPlata); add(txtPlata);
		add(lblSpecijalizacija); add(cbSpecijalizacija);
		add(lblKorisnickoIme); add(txtKorisnickoIme);
		add(lblSifra); add(txtSifra);
		add(new JLabel()); add(btnOk,"split 2"); add(btnCancel);
		
		//Mozda raspored promeniti da kor ime i sifra budu an vrhu!
	};
	private boolean validacija() {
		boolean ok = true;
		
		if(txtIme.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti ime korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}else if(serviser == null) {
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Serviser pronadjeni = servis.nadjiServisera(korisnickoIme);
			if(pronadjeni != null) {
				JOptionPane.showMessageDialog(null, 
						"Serviser sa tim korisnickim imenom vec postoji!", "Greska", JOptionPane.WARNING_MESSAGE);
				ok = false;
			}
		}
		
		if(txtPrezime.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti prezime korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		if(txtSifra.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti lozinku korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		if(txtJmbg.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti jmbg korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		if(txtAdresa.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti adresu korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		if(txtBrojTelefona.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti broj telefona korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		if(txtPlata.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti broj nagradnih poena ", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		
		if(txtKorisnickoIme.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti korisnicko ime korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		try {
			Long.parseLong(txtBrojTelefona.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Broj telefona mora biti broj", "Greska", JOptionPane.WARNING_MESSAGE);
				ok = false;
			}
		
		try {
			Long.parseLong(txtJmbg.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Jmbg mora biti broj", "Greska", JOptionPane.WARNING_MESSAGE);
				ok = false;
			}
		return ok;
	
		}
		
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacija() == true) {
					String uloga = "Serviser";
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJmbg.getText().trim();
					String polInt = cbPol.getSelectedItem().toString().trim();
					Pol pol = Pol.valueOf(polInt);
					String adresa = txtAdresa.getText().trim();
					String korime = txtKorisnickoIme.getText().trim();
					String lozinka = txtSifra.getText().trim();
					String brojtelefona = txtBrojTelefona.getText().trim();
					double plata = Double.parseDouble(txtPlata.getText().trim());
					String specijalizacijaInt = cbSpecijalizacija.getSelectedItem().toString().trim();
					SpecijalizacijaServisera specijalizacijaServisera = SpecijalizacijaServisera.valueOf(specijalizacijaInt);
					
					if (serviser == null) {
						//Dodavanje:
						serviser = new Serviser(uloga, ime, prezime,jmbg,pol, adresa,brojtelefona, korime,lozinka,plata,specijalizacijaServisera);
						servis.getServiser().add(serviser);
					}else {
						//Izmena
						serviser.setIme(ime);
						serviser.setPrezime(prezime);
						serviser.setJmbg(jmbg);
						serviser.setPol(pol);
						serviser.setAdresa(adresa);
						serviser.setKorIme(korime);
						serviser.setLozinka(lozinka);
						serviser.setBrojTelefona(brojtelefona);
						serviser.setPlata(plata);
						serviser.setSpecijalizacija(specijalizacijaServisera);
						
					}
					servis.snimiKorisnika();
					ServiseriForma.this.dispose();
					ServiseriForma.this.setVisible(false);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiseriForma.this.dispose();
				ServiseriForma.this.setVisible(false);
			}
		});	
	};
	
	
}

