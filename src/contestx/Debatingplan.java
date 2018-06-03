package contestx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Debatingplan {
	
	private Gui gui;
	private ArrayList<String> teams_junior;
	private int dPTjunior;
	private ArrayList<String> teams_senior;
	private int dPTsenior;
	private String[][] usedCompsJunior; //hier werden die verwendeten Kompositionen gespeichert
	private String[][] usedCompsSenior;
	
	public Debatingplan(Gui gui) {
		this.gui = gui; //referenzieren (für Datenfluss später)
		teams_junior = this.gui.getJuniorSchools(); //Datenfluss
		Collections.shuffle(teams_junior); //Reihenfolge randomisieren
		dPTjunior = (int) teams_junior.size()/2; //?
		teams_senior = this.gui.getSeniorSchools(); //Datenfluss
		Collections.shuffle(teams_senior); //Reihenfolge randomisieren
		dPTsenior = (int) teams_senior.size()/2; //?
		usedCompsJunior = new String[dPTjunior*3][2];
		usedCompsSenior = new String[dPTjunior*3][2];
	}
	
	public void berechne() {
		//Juniors
		for(int i = 1; i <= dPTjunior; i++) { //Timezone 1
			usedCompsJunior[i-1][0] = teams_junior.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 größer; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
			System.out.println(usedCompsJunior[i-1][0]);
			usedCompsJunior[i-1][1] = teams_junior.get(2*i-1);
			System.out.println(usedCompsJunior[i-1][1] + "\n");
		}
		System.out.println("\n");
		
		boolean run = true;
		while(run) { //Timezone 2
			Collections.shuffle(teams_junior);
			for(int i = 1; i <= dPTjunior; i++) {
				usedCompsJunior[dPTjunior+i-1][0] = teams_junior.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
				System.out.println(usedCompsJunior[dPTjunior+i-1][0]);
				usedCompsJunior[dPTjunior+i-1][1] = teams_junior.get((2*i)-1);
				System.out.println(usedCompsJunior[dPTjunior+i-1][1]+"\n");
			}
			System.out.println("\n");
			if(!entryDuplicated(usedCompsJunior, 0, dPTjunior*2)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgefüllten Bereich gibt, ist Timezone 2 fertig
				run = false;
			}
		}
		/*run = true;
		while(run) { //Timezone 3
			Collections.shuffle(teams_junior);
			for(int i = 1; i <= dPTjunior; i++) {
				usedCompsJunior[(2*dPTjunior)+i-1][0] = teams_junior.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
				System.out.println(usedCompsJunior[(2*dPTjunior)+i-1][0]);
				usedCompsJunior[(2*dPTjunior)+i-1][1] = teams_junior.get((2*i)-1);
				System.out.println(usedCompsJunior[(2*dPTjunior)+i-1][1]+"\n");
			}
			System.out.println("\n");
			if(!entryDuplicated(usedCompsJunior, 0, dPTjunior*3)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgefüllten Bereich gibt, ist Timezone 2 fertig
				run = false;
			}
		}*/
	}
	
	public boolean entryDuplicated(String[][] array, int start, int end) { //sucht nach Duplikaten im String[][]
		String[][] set = new String[array.length][array[0].length];
		for(int i = 0; i < set.length; i++) { //Array wird mit "" gefüllt (verhindert NullPointerException)
			set[i][0] = "";
			set[i][1] = "";
		}
		String[] temp = new String[2];
	    for ( int i = start; i < end; ++i ) {
	    	temp = array[i];
	        if (arrayContains(set, temp)) {
	            return true;
	        }
	        
	        else {
	            set[i] = array[i];
	        }
	    }
	    return false;
	}
	
	public boolean arrayContains(String[][] set, String[] temp) { //nur für String[2] temp!
		for(int i = 0; i < set.length; i++) {
			if((set[i][0].equals(temp[0]) && set[i][1].equals(temp[1])) || (set[i][0].equals(temp[1]) && set[i][1].equals(temp[0]))) {
				return true;
			}
		}
		return false;
	}
}
