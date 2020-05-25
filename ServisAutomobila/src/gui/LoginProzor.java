package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import korisnici.Korisnici;
import servis.Servis;

public class LoginProzor extends JFrame {
	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
	
	private Servis servis;
	
	public LoginProzor(Servis servis) {
		this.servis = servis;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	private void initGUI() {
			
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		
		this.lblPoruka = new JLabel("Dobrodosli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisnicko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Sifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		
		
		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		// Klik na Login dugme
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				// Ukoliko nesto nije uneseno, obavestimo korisnika
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");
				}else {

					Korisnici korisnik = servis.login(korisnickoIme, sifra);
					if(korisnik != null ) {
						//JOptionPane.showMessageDialog(null, servis.getMusterija());

						if(korisnik.getUloga().equals("Musterija")) {
								LoginProzor.this.setVisible(false);
								LoginProzor.this.dispose();
								GlavniProzorMusterija glavni = new GlavniProzorMusterija(servis, korisnik);
								glavni.setVisible(true);
								
						}else if(korisnik.getUloga().equals("Administrator")) {
							LoginProzor.this.setVisible(false);
							LoginProzor.this.dispose();
							GlavniProzorAdministrator glavniD = new GlavniProzorAdministrator(servis, korisnik);
							glavniD.setVisible(true);
							
						}else if(korisnik.getUloga().equals("Serviser")) {
							LoginProzor.this.setVisible(false);
							LoginProzor.this.dispose();
							GlavniProzorServiser glavniD = new GlavniProzorServiser(servis, korisnik);
							glavniD.setVisible(true);
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Pogresni login podaci!");
					}
				}
			}
		});
		// Cancel dugme samo sakriva trenutni prozor
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
			}
		});
		
	}
}
