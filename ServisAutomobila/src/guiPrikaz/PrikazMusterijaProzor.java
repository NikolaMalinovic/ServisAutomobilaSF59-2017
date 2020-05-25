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


import guiIzmenaiDodavanje.MusterijeForma;

import korisnici.Musterija;

import servis.Servis;

public class PrikazMusterijaProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable musterijeTabela;
	
	private Servis servis;
	
	public PrikazMusterijaProzor(Servis servis) {
		this.servis = servis;
		setTitle("Musterije");
		
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
		
		
		String[] zaglavlje = new String[] {"Uloga","Ime","Prezime","Jmbg","Pol","Adresa","Broj telefona","Nagradni poeni","Korisnicko ime","Lozinka"};
		int brojKorisnika = servis.getMusterija().size();
		Object[][] podaci = new Object[brojKorisnika][zaglavlje.length];
		
		
		for(int i=0; i<this.servis.getMusterija().size(); i++) {
			Musterija musterije = this.servis.getMusterija().get(i);
			podaci[i][0] = "Musterija";
			podaci[i][1] = musterije.getIme();
			podaci[i][2] = musterije.getPrezime();
			podaci[i][3] = musterije.getJmbg();
			podaci[i][4] = musterije.getPol();
			podaci[i][5] = musterije.getAdresa();
			podaci[i][6] = musterije.getBrojTelefona();
			podaci[i][7] = musterije.getBrojNagradnihBodova();
			podaci[i][8] = musterije.getKorIme();
			podaci[i][9] = musterije.getLozinka();
			
		}

		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		musterijeTabela = new JTable(tableModel);
		musterijeTabela.setRowSelectionAllowed(true);
		musterijeTabela.setColumnSelectionAllowed(false);
		musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterijeTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(musterijeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijeForma mF = new MusterijeForma(servis, null);
				mF.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli,","Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel)musterijeTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 8).toString();
					Musterija m = servis.nadjiMusteriju(korisnickoIme);
					if(m != null) {
							MusterijeForma mF = new MusterijeForma(servis, m);
							mF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog korisnika!","Greska",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli,","Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)musterijeTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 8).toString();
					Musterija m = servis.nadjiMusteriju(korisnickoIme);
					if(m != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete korisnika?", m.getKorIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getKorisnici().remove(m);
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
