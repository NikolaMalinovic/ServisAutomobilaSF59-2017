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

import guiIzmenaiDodavanje.AdministratoriForma;

import korisnici.Administrator;

import servis.Servis;

public class PrikazAdministratoraProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable administratoriTabela;
	
	private Servis servis;
	
	public PrikazAdministratoraProzor(Servis servis) {
		this.servis = servis;
		setTitle("Administratori");
		
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
		ImageIcon deleteicon = new ImageIcon(getClass().getResource("/slike/remove.png"));
		btnDelete.setIcon(deleteicon);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		
		String[] zaglavlje = new String[] {"Uloga","Ime","Prezime","Jmbg","Pol","Adresa","Broj telefona","Korisnicko ime","Lozinka","Plata"};
		int brojKorisnika = servis.getAdministrator().size();
		Object[][] podaci = new Object[brojKorisnika][zaglavlje.length];
		
		
		for(int i=0; i<this.servis.getAdministrator().size(); i++) {
			Administrator administratori = this.servis.getAdministrator().get(i);
			podaci[i][0] = "Administratori";
			podaci[i][1] = administratori.getIme();
			podaci[i][2] = administratori.getPrezime();
			podaci[i][3] = administratori.getJmbg();
			podaci[i][4] = administratori.getPol();
			podaci[i][5] = administratori.getAdresa();
			podaci[i][6] = administratori.getBrojTelefona();
			podaci[i][7] = administratori.getKorIme();
			podaci[i][8] = administratori.getLozinka();
			podaci[i][9] = administratori.getPlata();
			
		}

		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		administratoriTabela = new JTable(tableModel);
		administratoriTabela.setRowSelectionAllowed(true);
		administratoriTabela.setColumnSelectionAllowed(false);
		administratoriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		administratoriTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(administratoriTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratoriForma aF = new AdministratoriForma(servis, null);
				aF.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = administratoriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli,","Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel)administratoriTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 7).toString();
					Administrator a = servis.nadjiAdministratora(korisnickoIme);
					if(a != null) {
						AdministratoriForma aF = new AdministratoriForma(servis, a);
						aF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog korisnika!","Greska",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = administratoriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli,","Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)administratoriTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 7).toString();
					Administrator a = servis.nadjiAdministratora(korisnickoIme);
					if(a != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete korisnika?", a.getKorIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getKorisnici().remove(a);
							model.removeRow(red);
							servis.snimiKorisnika();
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog korisnika!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
}
