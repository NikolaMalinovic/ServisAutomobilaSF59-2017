package guiIzmenaiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Musterija;
import net.miginfocom.swing.MigLayout;
import servis.Servis;
import servisi.Automobil;
import servisi.MarkaAutomobila;
import servisi.ModelAutomobila;
import servisi.ServisnaKnjizica;
import servisi.ServisniDeo;
import servisi.VrstaGoriva;

public class AutomobiliForma extends JFrame {
	
	private JLabel lblId = new JLabel("Identifikacioni kod");
	private JTextField txtId = new JTextField(10);
//	private JLabel lblVlasnik = new JLabel("Vlasnik");
//	private JTextField txtVlasnik = new JTextField(20);
	private JLabel lblVlasnik = new JLabel("Vlasnik");
	private JComboBox<String> cbVlasnik = new JComboBox<String>();
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
//	private JLabel lblIdKnjizice = new JLabel("Identifikacioni kod knjizice");
//	private JTextField txtIdKnjizice = new JTextField(10);
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Servis servis;
	private Automobil automobil;
	
	public AutomobiliForma(Servis servis,Automobil automobil) {
		this.servis = servis;
		this.automobil = automobil;
		if(this.automobil == null) {
			setTitle("Kreiranje novog automobila");
		}else {
			setTitle("Izmena podataka -" + this.automobil.getIdentifikacioniKod());
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
		
		cbVlasnik.addItem("/");
		for(Musterija musterija : servis.getMusterija()) {
			cbVlasnik.addItem(musterija.getKorIme());
		}
		
		if(this.automobil != null) {
			txtId.setText(this.automobil.getIdentifikacioniKod());
			txtId.setEnabled(false);
		//	txtVlasnik.setText(this.automobil.getVlasnik());
			txtGodinaProizvodnje.setText(String.valueOf(this.automobil.getGodinaProizvodnje()));
			txtZapreminaMotora.setText(String.valueOf(this.automobil.getZapreminaMotora()));
			txtSnagaMotora.setText(String.valueOf(this.automobil.getSnagaMotora()));
	//		txtIdKnjizice.setText(this.automobil.getIdentifikacioniKod());
	//		txtIdKnjizice.setEnabled(false);
			
		}
		
		add(lblId); add(txtId);
		add(lblVlasnik); add(cbVlasnik);
		add(lblMarka); add(cbMarka);
		add(lblModel); add(cbModel);
		add(lblGodinaProizvodnje); add(txtGodinaProizvodnje);
		add(lblZapreminaMotora); add(txtZapreminaMotora);
		add(lblSnagaMotora);	add(txtSnagaMotora);
		add(lblVrstaGoriva);	add(cbVrstaGoriva);
	//	add(lblIdKnjizice);		add(txtIdKnjizice);
		add(new JLabel()); add(btnOk,"split 2"); add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		
		
		if(txtGodinaProizvodnje.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti godinu proizvodnje", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		try {
			Integer.parseInt(txtGodinaProizvodnje.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Cena mora biti ceo broj", "Greska", JOptionPane.WARNING_MESSAGE);
				ok = false;
			}
		
	
		if(txtZapreminaMotora.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti zapreminu motora", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		try {
			Integer.parseInt(txtZapreminaMotora.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Zapremina motora mora biti ceo broj", "Greska", JOptionPane.WARNING_MESSAGE);
				ok = false;
			}
		


		if(txtSnagaMotora.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti snagu motora", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		try {
			Integer.parseInt(txtSnagaMotora.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Snaga motora mora biti ceo broj", "Greska", JOptionPane.WARNING_MESSAGE);
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
					String vlasnik = cbVlasnik.getSelectedItem().toString();
					String markaInt = cbMarka.getSelectedItem().toString().trim();
					MarkaAutomobila marka = MarkaAutomobila.valueOf(markaInt);
					String modelInt = cbModel.getSelectedItem().toString().trim();
					ModelAutomobila model = ModelAutomobila.valueOf(modelInt);
					int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
					int zapreminaMotora = Integer.parseInt(txtZapreminaMotora.getText().trim());
					int snagaMotora = Integer.parseInt(txtSnagaMotora.getText().trim());
					String vrstaGorivaInt = cbVrstaGoriva.getSelectedItem().toString().trim();
					VrstaGoriva vrstaGoriva = VrstaGoriva.valueOf(vrstaGorivaInt);
					
					
					if (automobil == null) {
						
						automobil = new Automobil(id,vlasnik,marka,model,godinaProizvodnje,zapreminaMotora,snagaMotora,vrstaGoriva,new ArrayList<ServisnaKnjizica>()) {};
						servis.getAutomobil().add(automobil);
					}else {
						automobil.setVlasnik(vlasnik);
						automobil.setMarka(marka);
						automobil.setModel(model);
						automobil.setGodinaProizvodnje(godinaProizvodnje);
						automobil.setZapreminaMotora(zapreminaMotora);
						automobil.setSnagaMotora(snagaMotora);
						automobil.setVrstaGoriva(vrstaGoriva);
					}
					servis.snimiAutomobil();
					AutomobiliForma.this.dispose();
					AutomobiliForma.this.setVisible(false);
				}
				
			
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AutomobiliForma.this.dispose();
				AutomobiliForma.this.setVisible(false);
			}
		});	
		
	}
}

	

