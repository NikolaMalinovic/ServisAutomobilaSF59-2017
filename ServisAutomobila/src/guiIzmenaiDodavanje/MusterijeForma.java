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


import korisnici.Korisnici;
import korisnici.Musterija;
import korisnici.Pol;
import korisnici.SpecijalizacijaServisera;
import net.miginfocom.swing.MigLayout;

import servis.Servis;

public class MusterijeForma extends JFrame {

	
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
	private JLabel lblBrojNagradnihPoena = new JLabel("Broj nagradnih poena");
	private JTextField txtBrojNagradnihPoena = new JTextField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Servis servis;
	private Musterija musterija;
	
	public MusterijeForma(Servis servis,Musterija musterija) {
		this.servis = servis;
		this.musterija = musterija;
		if(this.musterija == null) {
			setTitle("Kreiranje novog korisnika");
		}else {
			setTitle("Izmena podataka -" + this.musterija.getIme());
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
		
		
		
		if(this.musterija != null) {
			txtIme.setText(this.musterija.getIme());
			txtPrezime.setText(this.musterija.getPrezime());
			txtJmbg.setText(this.musterija.getJmbg());
			txtAdresa.setText(this.musterija.getAdresa());
			txtBrojTelefona.setText(this.musterija.getBrojTelefona());
			txtKorisnickoIme.setText(this.musterija.getKorIme());
			txtSifra.setText(this.musterija.getLozinka());
			txtBrojNagradnihPoena.setText(this.musterija.getBrojNagradnihBodova());
		}
		
		add(lblIme); add(txtIme);
		add(lblPrezime); add(txtPrezime);
		add(lblPol); add(cbPol);
		add(lblJmbg); add(txtJmbg);
		add(lblAdresa); add(txtAdresa);
		add(lblBrojTelefona); add(txtBrojTelefona);
		add(lblSifra); add(txtSifra);
		add(lblKorisnickoIme); add(txtKorisnickoIme);
		add(lblSifra); add(txtSifra);
		add(lblBrojNagradnihPoena); add(txtBrojNagradnihPoena);
		
		add(new JLabel()); add(btnOk,"split 2"); add(btnCancel);
		
		//Mozda raspored promeniti da kor ime i sifra budu an vrhu!
	};
	private boolean validacija() {
		boolean ok = true;
		
		if(txtIme.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti ime korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
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
		if(txtBrojNagradnihPoena.getText().trim().equals("")) {
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
			Long.parseLong(txtBrojNagradnihPoena.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Broj nagradnih poena mora biti broj", "Greska", JOptionPane.WARNING_MESSAGE);
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
					String uloga = "Musterija";
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJmbg.getText().trim();
					String polInt = cbPol.getSelectedItem().toString().trim();
					Pol pol = Pol.valueOf(polInt);
					String adresa = txtAdresa.getText().trim();
					String brojtelefona = txtBrojTelefona.getText().trim();
					String brojNagradnihPoena = txtBrojNagradnihPoena.getText().trim();
					String korime = txtKorisnickoIme.getText().trim();
					String lozinka = txtSifra.getText().trim();
					
					if (musterija == null) {
						//Dodavanje:
						musterija = new Musterija(uloga, ime, prezime,jmbg,pol, adresa,brojtelefona, korime,lozinka,brojNagradnihPoena);
						servis.getMusterija().add(musterija);
					}else {
						//Izmena
						musterija.setIme(ime);
						musterija.setPrezime(prezime);
						musterija.setJmbg(jmbg);
						musterija.setPol(pol);
						musterija.setAdresa(adresa);
						musterija.setKorIme(korime);
						musterija.setLozinka(lozinka);
						musterija.setBrojTelefona(brojtelefona);
						musterija.setBrojNagradnihBodova(brojNagradnihPoena);
					}
					servis.snimiKorisnika();
					MusterijeForma.this.dispose();
					MusterijeForma.this.setVisible(false);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MusterijeForma.this.dispose();
				MusterijeForma.this.setVisible(false);
			}
		});	
	};
	
	
}

