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

import guiIzmenaiDodavanje.AutomobiliForma;
import servis.Servis;
import servisi.Automobil;
import servisi.MarkaAutomobila;
import servisi.ModelAutomobila;

public class PrikazAutomobilaProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable automobiliTabela;
	
	private Servis servis;
	
	public PrikazAutomobilaProzor(Servis servis) {
		this.servis = servis;
		setTitle("Automobili");
		
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
		
		String[] zaglavlje = new String[] {"Vlasnik","Marka","Model","Godina proizvodnje","Zapremina motora","Snaga motora","Vrsta goriva"};
		Object[][] podaci = new Object[this.servis.getAutomobil().size()][zaglavlje.length];
		
		for(int i=0; i<this.servis.getAutomobil().size(); i++) {
			Automobil automobil = this.servis.getAutomobil().get(i);
			podaci[i][0] = automobil.getVlasnik();
			podaci[i][1] = automobil.getMarka();
			podaci[i][2] = automobil.getModel();
			podaci[i][3] = automobil.getGodinaProizvodnje();
			podaci[i][4] = automobil.getZapreminaMotora();
			podaci[i][5] = automobil.getSnagaMotora();
			podaci[i][6] = automobil.getVrstaGoriva();
		}
		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		automobiliTabela = new JTable(tableModel);
		automobiliTabela = new JTable(tableModel);
		automobiliTabela.setRowSelectionAllowed(true);
		automobiliTabela.setColumnSelectionAllowed(true);
		automobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		automobiliTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(automobiliTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	AutomobiliForma aF = new AutomobiliForma(servis, null);
			//	aF.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiliTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)automobiliTabela.getModel();
					String naziv = model.getValueAt(red, 0).toString();
					Automobil automobil = servis.nadjiAutomobil(naziv);
					if(automobil != null) {
					//	AutomobiliForma aF = new AutomobiliForma(servis, automobil);
					//	aF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiliTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)automobiliTabela.getModel();
					String naziv = model.getValueAt(red, 0).toString();
					Automobil automobil = servis.nadjiAutomobil(naziv);
					if(naziv != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete automobil", automobil.getModel() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getAutomobil().remove(naziv);
							model.removeRow(red);
						//	servis.snimiAutomobile();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		
		
	}
}
