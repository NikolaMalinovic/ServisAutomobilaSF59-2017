package guiIzmenaiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Pol;
import korisnici.Serviser;
import net.miginfocom.swing.MigLayout;
import servis.Servis;
import servisi.MarkaAutomobila;
import servisi.ModelAutomobila;
import servisi.ServisniDeo;

public class DeloviForma extends JFrame {

	private JLabel lblMarka = new JLabel("Marka automobila");
	private JComboBox<MarkaAutomobila> cbMarka = new JComboBox<MarkaAutomobila>(MarkaAutomobila.values());
	private JLabel lblModel = new JLabel("Model automobila");
	private JComboBox<ModelAutomobila> cbModel = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
	private JLabel lblOpis = new JLabel("Opis dela");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblCena = new JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Servis servis;
	private ServisniDeo servisniDeo;
	
	public DeloviForma(Servis servis,ServisniDeo servisniDeo) {
		this.servis = servis;
		this.servisniDeo = servisniDeo;
		if(this.servisniDeo == null) {
			setTitle("Kreiranje novog dela");
		}else {
			setTitle("Izmena podataka -" + this.servisniDeo.getDeo());
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
		
		if(this.servisniDeo != null) {
			txtOpis.setText(this.servisniDeo.getDeo());
			txtCena.setText(String.valueOf(this.servisniDeo.getCena()));
		}
		
		add(lblMarka); add(cbMarka);
		add(lblModel); add(cbModel);
		add(lblOpis); add(txtOpis);
		add(lblCena); add(txtCena);
		add(new JLabel()); add(btnOk,"split 2"); add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		
		if(txtOpis.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti opis dela", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		if(txtCena.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti cenu dela", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		try {
			Long.parseLong(txtCena.getText().trim());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, 
						"Cena mora biti ceo broj", "Greska", JOptionPane.WARNING_MESSAGE);
				ok = false;
			}
		return ok;
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacija() == true) {
					String markaInt = cbMarka.getSelectedItem().toString().trim();
					MarkaAutomobila marka = MarkaAutomobila.valueOf(markaInt);
					String modelInt = cbMarka.getSelectedItem().toString().trim();
					ModelAutomobila model = ModelAutomobila.valueOf(modelInt);
					String opis = txtOpis.getText().trim();
					int cena = Integer.parseInt(txtCena.getText().trim());
					
					if (servisniDeo == null) {
						
						servisniDeo = new ServisniDeo(marka,model,opis,cena);
						servis.getDelovi().add(servisniDeo);
					}else {
						servisniDeo.setMarka(marka);
						servisniDeo.setModel(model);
						servisniDeo.setDeo(opis);
						servisniDeo.setCena(cena);
					}
					servis.snimiDeo();
					DeloviForma.this.dispose();
					DeloviForma.this.setVisible(false);
				}
				
				
				
			}
		});
	}
}
