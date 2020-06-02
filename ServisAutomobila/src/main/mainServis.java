package main;

import gui.LoginProzor;
import servis.Servis;

public class mainServis {

	public static void main(String[] args) {
		
		Servis servis = new Servis();
		servis.ucitajKorisnike();
		servis.ucitajAutomobile();
		servis.ucitajDelove();
		servis.ucitajServise();
		
		LoginProzor login = new LoginProzor(servis);
		login.setVisible(true);

	}

}
