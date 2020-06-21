package guiPrikaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import guiIzmenaiDodavanje.ServisAutomobilaForma;
import servis.Servis;
import servisi.ServisAutomobila;
import servisi.ServisnaKnjizica;


public class PrikazServisaAdministrator extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable servisiTablela;
	
	private Servis servis;
	
	public PrikazServisaAdministrator(Servis servis) {
		this.servis = servis;
		setTitle("Servisi");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension resolution = toolkit.getScreenSize();
		int screenWidth = resolution.width;
		int screenHeight = resolution.height;
		setSize(screenWidth / 2, screenHeight / 4);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.png"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.png"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.png"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Identifikacioni kod","Vlasnik","Marka","Model","Godina proizvodnje","Zapremina motora","Snaga motora","Vrsta goriva","Serviser","Termin","Opis","Status","Deo","Servisna knjizica"};
		Object[][] podaci = new Object[this.servis.getServise().size()][zaglavlje.length];
		
		for(int i=0; i<this.servis.getServise().size(); i++) {
			ServisAutomobila servisAutomobila = this.servis.getServise().get(i);
			ServisnaKnjizica servisnaKnjizica = servis.pronadjiKnjizicu(servisAutomobila);
			podaci[i][0] = servisAutomobila.getIdentifikacioniKod();
			podaci[i][1] = servisAutomobila.getVlasnik();
			podaci[i][2] = servisAutomobila.getMarka();
			podaci[i][3] = servisAutomobila.getModel();
			podaci[i][4] = servisAutomobila.getGodinaProizvodnje();
			podaci[i][5] = servisAutomobila.getZapreminaMotora();
			podaci[i][6] = servisAutomobila.getSnagaMotora();
			podaci[i][7] = servisAutomobila.getVrstaGoriva();
			podaci[i][8] = servisAutomobila.getServiser();
			podaci[i][9] = servisAutomobila.getTermin();
			podaci[i][10] = servisAutomobila.getOpis();
			podaci[i][11] = servisAutomobila.getDelovi();
			podaci[i][12] = servisAutomobila.getStatus();
			podaci[i][13] = servisnaKnjizica == null ? "--" : servisnaKnjizica.getIdKnjizica();
			
			
		}
		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		servisiTablela = new JTable(tableModel);
		servisiTablela.setRowSelectionAllowed(true);
		servisiTablela.setColumnSelectionAllowed(true);
		servisiTablela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisiTablela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(servisiTablela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisAutomobilaForma sF = new ServisAutomobilaForma(servis, null);
				sF.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTablela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)servisiTablela.getModel();
					String id = model.getValueAt(red, 0).toString();
					ServisAutomobila s = servis.nadjiServis(id);
					if(s != null) {
						ServisAutomobilaForma sF = new ServisAutomobilaForma(servis, s);
						sF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci servis.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTablela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)servisiTablela.getModel();
					String id = model.getValueAt(red, 0).toString();
					ServisAutomobila s = servis.nadjiServis(id);
					if(s != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete servis", s.getIdentifikacioniKod() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getServise().remove(s);
							model.removeRow(red);
							servis.snimiServisAutomobila();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani servis!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
}
