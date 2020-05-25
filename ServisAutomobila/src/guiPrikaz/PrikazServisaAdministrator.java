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


import servis.Servis;
import servisi.ServisAutomobila;


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
		
		String[] zaglavlje = new String[] {"Vlasnik","Marka","Model","Godina proizvodnje","Zapremina motora","Snaga motora","Vrsta goriva","Serviser","Termin","Opis","Delovi","Status"};
		Object[][] podaci = new Object[this.servis.getServise().size()][zaglavlje.length];
		
		for(int i=0; i<this.servis.getServise().size(); i++) {
			ServisAutomobila servisAutomobila = this.servis.getServise().get(i);
			podaci[i][0] = servisAutomobila.getVlasnik();
			podaci[i][1] = servisAutomobila.getMarka();
			podaci[i][2] = servisAutomobila.getModel();
			podaci[i][3] = servisAutomobila.getGodinaProizvodnje();
			podaci[i][4] = servisAutomobila.getZapreminaMotora();
			podaci[i][5] = servisAutomobila.getSnagaMotora();
			podaci[i][6] = servisAutomobila.getVrstaGoriva();
			podaci[i][7] = servisAutomobila.getServiser();
			podaci[i][8] = servisAutomobila.getTermin();
			podaci[i][9] = servisAutomobila.getOpis();
			podaci[i][10] = servisAutomobila.getDelovi();
			podaci[i][11] = servisAutomobila.isStatus();
			
			
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
			//	ServisiForma sF = new ServisiForma(servis, null);
			//	sF.setVisible(true);
				
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
					String opis = model.getValueAt(red, 9).toString();
					ServisAutomobila s = servis.nadjiServis(opis);
					if(s != null) {
					//	ServisiForma sF = new ServisiForma(servis, s);
					//	sF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	/*	btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTablela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)servisiTablela.getModel();
					String opis = model.getValueAt(red, 9).toString();
					ServisniDeo s = servis.nadjiDeo(opis);
					if(s != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete automobil", s.getModel() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getAutomobil().remove(s);
							model.removeRow(red);
							servis.snimiServis();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});*/
	}
}
