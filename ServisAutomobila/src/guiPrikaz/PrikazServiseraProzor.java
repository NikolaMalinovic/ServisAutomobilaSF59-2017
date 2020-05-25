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


import guiIzmenaiDodavanje.ServiseriForma;

import korisnici.Serviser;
import servis.Servis;

public class PrikazServiseraProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable serviseriTabela;
	
	private Servis servis;
	
	public PrikazServiseraProzor(Servis servis) {
		this.servis = servis;
		setTitle("Serviseri");
		
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
		
		
		String[] zaglavlje = new String[] {"Uloga","Ime","Prezime","Jmbg","Pol","Adresa","Broj telefona","Korisnicko ime","Lozinka","Plata","Specijalizacija"};
		int brojKorisnika = servis.getServiser().size();
		Object[][] podaci = new Object[brojKorisnika][zaglavlje.length];
		
		
		for(int i=0; i<this.servis.getServiser().size(); i++) {
			Serviser serviser = this.servis.getServiser().get(i);
			podaci[i][0] = "Serviser";
			podaci[i][1] = serviser.getIme();
			podaci[i][2] = serviser.getPrezime();
			podaci[i][3] = serviser.getJmbg();
			podaci[i][4] = serviser.getPol();
			podaci[i][5] = serviser.getAdresa();
			podaci[i][6] = serviser.getBrojTelefona();
			podaci[i][7] = serviser.getKorIme();
			podaci[i][8] = serviser.getLozinka();
			podaci[i][9] = serviser.getPlata();
			podaci[i][10] = serviser.getSpecijalizacija();
			
		}

		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		serviseriTabela = new JTable(tableModel);
		serviseriTabela.setRowSelectionAllowed(true);
		serviseriTabela.setColumnSelectionAllowed(false);
		serviseriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviseriTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(serviseriTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiseriForma sF = new ServiseriForma(servis, null);
				sF.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli,","Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel)serviseriTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 7).toString();
					Serviser s = servis.nadjiServisera(korisnickoIme);
					if(s != null) {
						ServiseriForma sF = new ServiseriForma(servis, s);
						sF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog korisnika!","Greska",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli,","Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)serviseriTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 7).toString();
					Serviser s = servis.nadjiServisera(korisnickoIme);
					if(s != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete korisnika?", s.getKorIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getKorisnici().remove(s);
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
