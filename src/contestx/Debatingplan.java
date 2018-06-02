package contestx;

import java.util.ArrayList;
import java.util.Collections;

public class Debatingplan {
	
	private Gui gui;
	private ArrayList<String> teams_junior;
	private int dPTjunior;
	private ArrayList<String> teams_senior;
	private int dPTsenior;
	private int[][] usedCompsJunior;
	private int[][] usedCompsSenior;
	
	public Debatingplan(Gui gui) {
		this.gui = gui; //referenzieren (für Datenfluss später)
		teams_junior = this.gui.getJuniorSchools(); //Datenfluss
		Collections.shuffle(teams_junior); //Reihenfolge randomisieren
		dPTjunior = (int) teams_junior.size()/2; //?
		teams_senior = this.gui.getSeniorSchools(); //Datenfluss
		Collections.shuffle(teams_senior); //Reihenfolge randomisieren
		dPTsenior = (int) teams_senior.size()/2; //?
		usedCompsJunior = new int[1][dPTjunior*3];
		usedCompsSenior = new int[1][dPTsenior*3];
	}
	
	public void berechne() {
		for(int i = 1; i <= dPTjunior; i++) { //Timezone 1
			usedCompsJunior[0][i-1] = (2*i)-1; //"[i-1]", da i in for-schleife um 1 größer; "(2*i)-1", da 
			usedCompsJunior[1][i-1] = 2*i;
		}
		Collections.shuffle(teams_junior);
	}
}
