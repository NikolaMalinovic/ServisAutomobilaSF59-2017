package guiIzmenaiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Serviser;
import net.miginfocom.swing.MigLayout;
import servis.Servis;
import servisi.Automobil;
import servisi.MarkaAutomobila;
import servisi.ModelAutomobila;
import servisi.ServisAutomobila;
import servisi.ServisnaKnjizica;
import servisi.VrstaGoriva;

public class ServisAutomobilaForma extends JFrame {
	
	private JLabel lblId = new JLabel("Identifikacioni kod");
	private JTextField txtId = new JTextField(10);
	private JLabel lblVlasnik = new JLabel("Vlasnik");
	private JTextField txtVlasnik = new JTextField(20);
	private JLabel lblMarka = new JLabel("Marka automobila");
	private JComboBox<MarkaAutomobila> cbMarka = new JComboBox<MarkaAutomobila>(MarkaAutomobila.values());
	private JLabel lblModel = new JLabel("Model automobila");
	private JComboBox<ModelAutomobila> cbModel = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
	private JLabel lblGodinaProizvodnje = new JLabel("Godina proizvodnje");
	private JTextField txtGodinaProizvodnje = new JTextField(4);
	private JLabel lblZapreminaMotora = new JLabel("Zapremina motora");
	private JTextField txtZapreminaMotora = new JTextField(8);
	private JLabel lblSnagaMotora = new JLabel("Snaga motora");
	private JTextField txtSnagaMotora = new JTextField(10);
	private JLabel lblVrstaGoriva = new JLabel("Vrsta goriva");
	private JComboBox<VrstaGoriva> cbVrstaGoriva = new JComboBox<VrstaGoriva>(VrstaGoriva.values());
	
	private JLabel lblServiser = new JLabel("Serviser");
	private JComboBox<String> cbServiser = new JComboBox<String>();
	private JLabel lblIdK = new JLabel("Servisna knjizica");
	private JComboBox<String> cbKnjizice = new JComboBox<String>();
	
//	private JLabel lblTermin = new JLabel("Termin");
//	private JTextField txtTermin= new JTextField(20);
	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblDelovi = new JLabel("Delovi");
	private JTextField txtDelovi = new JTextField(20);
	private JLabel lblStatus = new JLabel("Status");
	private JTextField txtStatus = new JTextField(20);
	
	
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Servis servis;
	private ServisAutomobila servisAutomobila;
	
	public ServisAutomobilaForma(Servis servis,ServisAutomobila servisAutomobila) {
		this.servis = servis;
		this.servisAutomobila = servisAutomobila;
		if(this.servisAutomobila == null) {
			setTitle("Kreiranje novog servisa");
		}else {
			setTitle("Izmena podataka -" + this.servisAutomobila.getMarka());
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
		
		cbServiser.addItem("/");
		for(Serviser serviser : servis.getServiser()) {
			cbServiser.addItem(serviser.getKorIme());
		}
		
		for(ServisnaKnjizica servisnaKnjizica : servis.sveKnjizice()) {
			cbKnjizice.addItem(servisnaKnjizica.getIdKnjizica());
		}
		
		
		if(this.servisAutomobila != null) {
			txtId.setText(this.servisAutomobila.getIdentifikacioniKod());
			txtId.setEnabled(false);
			txtVlasnik.setText(this.servisAutomobila.getVlasnik());
			txtGodinaProizvodnje.setText(String.valueOf(this.servisAutomobila.getGodinaProizvodnje()));
			txtZapreminaMotora.setText(String.valueOf(this.servisAutomobila.getZapreminaMotora()));
			txtSnagaMotora.setText(String.valueOf(this.servisAutomobila.getSnagaMotora()));
			
			ServisnaKnjizica servisnaKnjizica = servis.pronadjiKnjizicu(servisAutomobila);
			cbKnjizice.setSelectedItem(servisnaKnjizica.getIdKnjizica());
			
		//	txtTermin.setText(String.valueOf(this.servisAutomobila.getTermin()));
			txtOpis.setText(this.servisAutomobila.getOpis());
			txtDelovi.setText(this.servisAutomobila.getDelovi());
			txtStatus.setText(this.servisAutomobila.getStatus());
			
		}

		add(lblId); add(txtId);
		add(lblVlasnik); add(txtVlasnik);
		add(lblMarka); add(cbMarka);
		add(lblModel); add(cbModel);
		add(lblGodinaProizvodnje); add(txtGodinaProizvodnje);
		add(lblZapreminaMotora); add(txtZapreminaMotora);
		add(lblSnagaMotora);	add(txtSnagaMotora);
		add(lblVrstaGoriva);	add(cbVrstaGoriva);
		
		
		add(lblServiser); add(cbServiser);
	//	add(lblTermin); add(txtTermin);
		add(lblOpis); add(txtOpis);
		add(lblDelovi); add(txtDelovi);
		add(lblStatus); add(txtStatus);
		add(lblIdK); add(cbKnjizice);
		
		add(new JLabel()); add(btnOk,"split 2"); add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		
		if(txtOpis.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti opis", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		
		return ok;
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacija() == true) {
					
					String id = txtId.getText().trim();
					String vlasnik = txtVlasnik.getText().trim();
					String markaInt = cbMarka.getSelectedItem().toString().trim();
					MarkaAutomobila marka = MarkaAutomobila.valueOf(markaInt);
					String modelInt = cbModel.getSelectedItem().toString().trim();
					ModelAutomobila model = ModelAutomobila.valueOf(modelInt);
					int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
					int zapreminaMotora = Integer.parseInt(txtZapreminaMotora.getText().trim());
					int snagaMotora = Integer.parseInt(txtSnagaMotora.getText().trim());
					String vrstaGorivaInt = cbVrstaGoriva.getSelectedItem().toString().trim();
					VrstaGoriva vrstaGoriva = VrstaGoriva.valueOf(vrstaGorivaInt);
					ArrayList<ServisnaKnjizica> knjizica = new ArrayList<ServisnaKnjizica>();
					
					String serviser = cbServiser.getSelectedItem().toString().trim();
			
					String knjiziceID = cbKnjizice.getSelectedItem().toString();
					ServisnaKnjizica servisnaKnjizica = servis.nadjiKnjizicu(knjiziceID);
					
					String termin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
					String opis = txtOpis.getText().trim();
					String delovi = txtDelovi.getText().trim();
					String status = txtStatus.getText().trim();
	
					if (servisAutomobila == null) {
						
						servisAutomobila = new ServisAutomobila(id,vlasnik,marka,model,godinaProizvodnje,zapreminaMotora,snagaMotora,vrstaGoriva,knjizica,serviser,termin,opis,delovi,status) {};
					/*	if(servisnaKnjizica != null) {
							servisnaKnjizica.getServisAutomobila().add(servisAutomobila);
						}*/
						servis.getServise().add(servisAutomobila);
					}else {
						servisAutomobila.setIdentifikacioniKod(id);
						servisAutomobila.setVlasnik(vlasnik);
						servisAutomobila.setMarka(marka);
						servisAutomobila.setModel(model);
						servisAutomobila.setGodinaProizvodnje(godinaProizvodnje);
						servisAutomobila.setZapreminaMotora(zapreminaMotora);
						servisAutomobila.setSnagaMotora(snagaMotora);
						servisAutomobila.setVrstaGoriva(vrstaGoriva);
						servisAutomobila.setServiser(serviser);
						
						servisAutomobila.setOpis(opis);
						servisAutomobila.setDelovi(delovi);
						servisAutomobila.setStatus(status);
						
						
					/*	ServisnaKnjizica staraKnjizica = servis.pronadjiKnjizicu(servisAutomobila);
						if(staraKnjizica != null) {
							staraKnjizica.getServisAutomobila().remove(servisAutomobila);
							}
						if(servisnaKnjizica != null) {
							servisnaKnjizica.getServisAutomobila().add(servisAutomobila);
						}*/
						
						
						
						
						
					}
					servis.snimiServisAutomobila();
					ServisAutomobilaForma.this.dispose();
					ServisAutomobilaForma.this.setVisible(false);
				}
				
			
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServisAutomobilaForma.this.dispose();
				ServisAutomobilaForma.this.setVisible(false);
			}
		});	
		
	}
}

	


