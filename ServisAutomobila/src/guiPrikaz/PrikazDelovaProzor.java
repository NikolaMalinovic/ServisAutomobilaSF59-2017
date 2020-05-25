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
import servis.Servis;
import servisi.Automobil;
import servisi.ServisniDeo;

public class PrikazDelovaProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable deloviTabela;
	
	private Servis servis;
	
	public PrikazDelovaProzor(Servis servis) {
		this.servis = servis;
		setTitle("Delovi");
		
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
		
		String[] zaglavlje = new String[] {"Marka","Model","Deo","Cena"};
		Object[][] podaci = new Object[this.servis.getDelovi().size()][zaglavlje.length];
		
		for(int i=0; i<this.servis.getDelovi().size(); i++) {
			ServisniDeo servisniDeo = this.servis.getDelovi().get(i);
			podaci[i][0] = servisniDeo.getMarka();
			podaci[i][1] = servisniDeo.getModel();
			podaci[i][2] = servisniDeo.getDeo();
			podaci[i][3] = servisniDeo.getCena();
			
		}
		
		tableModel = new DefaultTableModel(podaci,zaglavlje);
		deloviTabela = new JTable(tableModel);
		deloviTabela.setRowSelectionAllowed(true);
		deloviTabela.setColumnSelectionAllowed(true);
		deloviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deloviTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(deloviTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeloviForma dF = new DeloviForma(servis, null);
				dF.setVisible(true);
				
			}
		});
		
btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)deloviTabela.getModel();
					String naziv = model.getValueAt(red, 2).toString();
					ServisniDeo s = servis.nadjiDeo(naziv);
					if(s != null) {
						DeloviForma dF = new DeloviForma(servis, s);
						dF.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)deloviTabela.getModel();
					String naziv = model.getValueAt(red, 2).toString();
					ServisniDeo s = servis.nadjiDeo(naziv);
					if(s != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete automobil", s.getModel() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.getAutomobil().remove(s);
							model.removeRow(red);
							servis.snimiDeo();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
}
