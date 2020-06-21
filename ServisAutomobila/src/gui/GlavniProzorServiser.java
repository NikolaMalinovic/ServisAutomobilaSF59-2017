package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import guiPrikaz.PrikazServisaServiser;
import korisnici.Korisnici;
import servis.Servis;

public class GlavniProzorServiser extends JFrame {

	private Servis servis;
	private Korisnici prijavljeniKorisnik;
	
	private JMenuBar mainMenu;
	private JMenu meniMenu;
	private JMenuItem servisiItem;
	private JMenuItem odjavaItem;
	
	public GlavniProzorServiser(Servis servis, Korisnici prijavljenKorisnik) {
		this.servis = servis;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Servis automobila - " + prijavljenKorisnik.getIme()+" "+prijavljenKorisnik.getPrezime()+" "+prijavljenKorisnik.getUloga());
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension resolution = toolkit.getScreenSize();
		int screenWidth = resolution.width;
		int screenHeight = resolution.height;
		setSize(screenWidth / 4, screenHeight / 4);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		
		this.mainMenu = new JMenuBar();
		this.meniMenu = new JMenu("Menu");
		
		this.servisiItem = new JMenuItem("Servisi");
		this.odjavaItem = new JMenuItem("Odjava");
		setJMenuBar(this.mainMenu);
		
		this.meniMenu.add(servisiItem);
		this.meniMenu.add(odjavaItem);
		
		this.mainMenu.add(meniMenu);
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
		
		servisiItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrikazServisaServiser pSs = new PrikazServisaServiser(servis, null);
				pSs.setVisible(true);
			}
		});
		odjavaItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GlavniProzorServiser.this.setVisible(false);
				GlavniProzorServiser.this.dispose();
				LoginProzor login = new LoginProzor(servis);
				login.setVisible(true);
			}
		});
		
	}
}
