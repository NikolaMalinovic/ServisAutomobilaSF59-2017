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

import guiIzmenaiDodavanje.DeloviForma;
import guiIzmenaiDodavanje.ServisneKnjiziceForma;
import servis.Servis;
import servisi.Automobil;
import servisi.ServisnaKnjizica;
import servisi.ServisniDeo;

public class PrikazServisnihKnjizicaProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable knjiziceTabela;
	
	private Servis servis;
	
	public PrikazServisnihKnjizicaProzor(Servis servis) {
		this.servis = servis;
		setTitle("Servisne knjizice");
		
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
		
		String[] zaglavlje = new String[] {"Identifikacioni kod knjizice","Identifikacioni kod automobila"};
		Object[][] podaci = new Object[this.servis.getKnjizice().size()][zaglavlje.length];
		
		for(int i=0; i<this.servis.getKnjizice().size(); i++) {
			ServisnaKnjizica servisnaKnjizica = this.servis.getKnjizice().get(i);
			podaci[i][0] = servisnaKnjizica.getIdKnjizica();
			
			
		}
		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		knjiziceTabela = new JTable(tableModel);
		knjiziceTabela = new JTable(tableModel);
		knjiziceTabela.setRowSelectionAllowed(true);
		knjiziceTabela.setColumnSelectionAllowed(true);
		knjiziceTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjiziceTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(knjiziceTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisneKnjiziceForma sF = new ServisneKnjiziceForma(servis, null);
				sF.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)knjiziceTabela.getModel();
					String id = model.getValueAt(red, 0).toString();
					ServisnaKnjizica servisnaKnjizica = servis.nadjiKnjizicu(id);
					if(servisnaKnjizica != null) {
						ServisneKnjiziceForma sF = new ServisneKnjiziceForma(servis, servisnaKnjizica);
						sF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)knjiziceTabela.getModel();
					String id = model.getValueAt(red, 0).toString();
					ServisnaKnjizica servisnaKnjizica = servis.nadjiKnjizicu(id);
					if(id != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete automobil", servisnaKnjizica.getIdKnjizica() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getAutomobil().remove(id);
							model.removeRow(red);
							servis.snimiServisnuKnjizicu();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		
		
	}
}
