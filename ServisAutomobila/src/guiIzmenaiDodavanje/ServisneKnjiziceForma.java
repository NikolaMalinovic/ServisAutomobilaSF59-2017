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

import net.miginfocom.swing.MigLayout;
import servis.Servis;
import servisi.Automobil;
import servisi.MarkaAutomobila;
import servisi.ModelAutomobila;
import servisi.ServisAutomobila;
import servisi.ServisnaKnjizica;


public class ServisneKnjiziceForma extends JFrame {

	private JLabel lblIdKnjizica = new JLabel("Identifikacioni kod knjizice");
	private JTextField txtIdKnjizica = new JTextField(10);
	private JLabel lblAutomobil = new JLabel("Identifikacioni kod automobila");
	private JComboBox<String> cbAutomobil = new JComboBox<String>();
	
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Servis servis;
	private ServisnaKnjizica servisnaKnjizica;
	
	public ServisneKnjiziceForma(Servis servis,ServisnaKnjizica servisnaKnjizica) {
		this.servis = servis;
		this.servisnaKnjizica = servisnaKnjizica;
		if(this.servisnaKnjizica == null) {
			setTitle("Kreiranje novog dela");
		}else {
			setTitle("Izmena podataka -" + this.servisnaKnjizica.getIdKnjizica());
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
		
		cbAutomobil.addItem("/");
		
		for(Automobil automobili : servis.getAutomobil()) {
			cbAutomobil.addItem(automobili.getIdentifikacioniKod());
		}
		
		if(this.servisnaKnjizica != null) {
			txtIdKnjizica.setText(this.servisnaKnjizica.getIdKnjizica());
			
		}
		
		add(lblIdKnjizica); add(txtIdKnjizica);
		add(lblAutomobil); add(cbAutomobil);
		
		add(new JLabel()); add(btnOk,"split 2"); add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		
		if(txtIdKnjizica.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Morate uneti  id", "Greska", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		
		return ok;
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacija() == true) {
					String id = txtIdKnjizica.getText().toString().trim();
					String automobil = cbAutomobil.getSelectedItem().toString().trim();
					if (servisnaKnjizica == null) {
						
						servisnaKnjizica = new ServisnaKnjizica(id,automobil, new ArrayList<ServisAutomobila>());
						servis.getKnjizice().add(servisnaKnjizica);
					}else {
						servisnaKnjizica.setIdKnjizica(id);
						servisnaKnjizica.setAutomobili(automobil);
						
					}
					servis.snimiServisnuKnjizicu();
					ServisneKnjiziceForma.this.dispose();
					ServisneKnjiziceForma.this.setVisible(false);
				}
				
			
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServisneKnjiziceForma.this.dispose();
				ServisneKnjiziceForma.this.setVisible(false);
			}
		});	
		
	}
}
