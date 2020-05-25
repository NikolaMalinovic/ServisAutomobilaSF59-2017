package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import guiPrikaz.PrikazAdministratoraProzor;
import guiPrikaz.PrikazAutomobilaProzor;
import guiPrikaz.PrikazDelovaProzor;
import guiPrikaz.PrikazMusterijaProzor;
import guiPrikaz.PrikazServisaAdministrator;
import guiPrikaz.PrikazServiseraProzor;
import korisnici.Korisnici;
import servis.Servis;

public class GlavniProzorAdministrator extends JFrame {

	private Servis servis;
	private Korisnici prijavljeniKorisnik;
	
	private JMenuBar mainMenu;
	private JMenu korisniciMenu;
	private JMenuItem musterijeItem;
	private JMenuItem serviseriItem;
	private JMenuItem administratoriItem;
	private JMenu deloviMenu;
	private JMenuItem deloviItem;
	private JMenu automobiliMenu;
	private JMenuItem automobiliItem;
	private JMenu servisiMenu;
	private JMenuItem servisneKnjiziceItem;
	private JMenuItem servisiItem;
	private JMenuItem odjavaItem;
	
	
	public GlavniProzorAdministrator(Servis servis, Korisnici prijavljeniKorisnik) {
		this.servis = servis;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Servis automobila -" + prijavljeniKorisnik.getIme()+ " "+prijavljeniKorisnik.getPrezime()+ " "+prijavljeniKorisnik.getUloga());
		
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
		this.korisniciMenu = new JMenu("Korisnici");
		this.musterijeItem = new JMenuItem("Prikazi musterije");
		this.serviseriItem = new JMenuItem("Prikazi servisere");
		this.administratoriItem = new JMenuItem("Prikazi administratore");
		this.automobiliMenu = new JMenu("Automobili");
		this.automobiliItem = new JMenuItem("Prikazi automobile");
		this.deloviMenu = new JMenu("Delovi");
		this.deloviItem = new JMenuItem("Prikazi delove");
		this.servisiMenu = new JMenu("Servisi");
		this.servisneKnjiziceItem = new JMenuItem("Servisne knjizice");
		this.servisiItem = new JMenuItem("Prikazi servise");
		this.odjavaItem = new JMenuItem("Odjava");
		setJMenuBar(this.mainMenu);
		
		this.korisniciMenu.add(musterijeItem);
		this.korisniciMenu.add(serviseriItem);
		this.korisniciMenu.add(administratoriItem);
		this.automobiliMenu.add(automobiliItem);
		this.deloviMenu.add(deloviItem);
		this.servisiMenu.add(servisiItem);
		this.servisiMenu.add(servisneKnjiziceItem);
		
		this.mainMenu.add(servisiMenu);
		this.mainMenu.add(korisniciMenu);
		this.mainMenu.add(automobiliMenu);
		this.mainMenu.add(deloviMenu);
		this.mainMenu.add(odjavaItem);
		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
		
		musterijeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazMusterijaProzor pM = new PrikazMusterijaProzor(servis);
				pM.setVisible(true);
				
			}
		});
		
		serviseriItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						PrikazServiseraProzor pS = new PrikazServiseraProzor(servis);
						pS.setVisible(true);
						
					}
				});
		
		administratoriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAdministratoraProzor pA = new PrikazAdministratoraProzor(servis);
				pA.setVisible(true);
				
			}
		});
		
		automobiliItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAutomobilaProzor pA = new PrikazAutomobilaProzor(servis);
				pA.setVisible(true);
				
			}
		});
		
		deloviItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazDelovaProzor pd= new PrikazDelovaProzor(servis);
				pd.setVisible(true);
				
			}
		});
		
		servisneKnjiziceItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	PrikazServisnihKnjizicaProzor pSK = new PrikazServisnihKnjizicaProzor(servis);
			//	pSK.setVisible(true);
				
			}
		});
		
		servisiItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazServisaAdministrator  pSa = new PrikazServisaAdministrator(servis);
				pSa.setVisible(true);
				
			}
		});
		odjavaItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GlavniProzorAdministrator.this.setVisible(false);
				GlavniProzorAdministrator.this.dispose();
				LoginProzor login = new LoginProzor(servis);
				login.setVisible(true);
				
			}
		});
		
	}
}
