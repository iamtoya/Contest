package contestx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Debatingplan {
	private ArrayList<Debate> debatesJ;
	private ArrayList<Debate> debatesS;
	private Zeitzone zeitzone1;
	private Zeitzone zeitzone2;
	private Zeitzone zeitzone3;
	private ArrayList<Judge> judges;
	private ArrayList<Schule> schulen;
	private ArrayList<Speaker> speaker;
	private ArrayList<String> motions;	  
	//  obiges nach Modellierung
	private Gui gui;
	private ArrayList<Team> teams_junior;
	private ArrayList<String> teamJuniorNames;
	private int dPTjunior;
	private ArrayList<Team> teams_senior;
	private ArrayList<String> teamSeniorNames;
	private int dPTsenior;
	private String[][] usedCompsJunior; //hier werden die verwendeten Kompositionen gespeichert
	private String[][] usedCompsSenior;
	
	public Debatingplan(Gui gui) {
		this.gui = gui; //referenzieren (für Datenfluss später)
		teams_junior = this.gui.getJuniorTeams();
		teamJuniorNames = new ArrayList<String>(); 
		for(int i = 0; i < teams_junior.size(); i++) {
			teamJuniorNames.add(teams_junior.get(i).getSchoolName());
		}
		Collections.shuffle(teamJuniorNames); //Reihenfolge randomisieren
		dPTjunior = (int) teamJuniorNames.size()/2; //?
		
		teams_senior = this.gui.getSeniorTeams();
		teamSeniorNames = new ArrayList<String>();
		for(int i = 0; i < teams_senior.size(); i++) {
			teamSeniorNames.add(teams_senior.get(i).getSchoolName());
		}
		Collections.shuffle(teamSeniorNames); //Reihenfolge randomisieren
		dPTsenior = (int) teamSeniorNames.size()/2; //?
		usedCompsJunior = new String[dPTjunior*3][2];
		usedCompsSenior = new String[dPTjunior*3][2];
		
		schulen = this.gui.getSchools();
		
	}
	
	public String[][] berechne() {
		//Juniors
		while(!teamOnEachSide(usedCompsJunior, true)) {
			for(int i = 1; i <= dPTjunior; i++) { //Timezone 1; 1<=i<=dPTjunior, da i multipliziert wird.
				usedCompsJunior[i-1][0] = teamJuniorNames.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 größer; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
				usedCompsJunior[i-1][1] = teamJuniorNames.get(2*i-1);
				debatesJ.add(new Debate(replaceStringJunior(usedCompsJunior[i-1][0]), replaceStringJunior(usedCompsJunior[i-1][1])));
			}
			//System.out.println("\n");
			
			boolean run = true;
			while(run) { //Timezone 2
				Collections.shuffle(teamJuniorNames);
				for(int i = 1; i <= dPTjunior; i++) {
					usedCompsJunior[dPTjunior+i-1][0] = teamJuniorNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
					//System.out.println(usedCompsJunior[dPTjunior+i-1][0]);
					usedCompsJunior[dPTjunior+i-1][1] = teamJuniorNames.get((2*i)-1);
					//System.out.println(usedCompsJunior[dPTjunior+i-1][1]+"\n");
				}
				//System.out.println("\n");
				if(!entryDuplicated(usedCompsJunior, 0, dPTjunior*2)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgefüllten Bereich gibt, ist Timezone 2 fertig
					run = false;
				}
			}
			//System.out.println("Timezone 3");
			run = true;
			while(run) { //Timezone 3
				Collections.shuffle(teamJuniorNames);
				for(int i = 1; i <= dPTjunior; i++) {
					usedCompsJunior[(2*dPTjunior)+i-1][0] = teamJuniorNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
					//System.out.println(usedCompsJunior[(2*dPTjunior)+i-1][0]);
					usedCompsJunior[(2*dPTjunior)+i-1][1] = teamJuniorNames.get((2*i)-1);
					//System.out.println(usedCompsJunior[(2*dPTjunior)+i-1][1]+"\n");
				}
				if(!entryDuplicated(usedCompsJunior, 0, dPTjunior*3)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgefüllten Bereich gibt, ist Timezone 2 fertig
					run = false;
				}
			}
		}
		for(int i = 0; i < usedCompsJunior.length; i++) {
			System.out.println(usedCompsJunior[i][0]);
			System.out.println(usedCompsJunior[i][1] + "\n");
		}
		System.out.println("\n\n\n");
		
		
		System.out.println(teamOnEachSide(usedCompsJunior, true));
		return usedCompsJunior;
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
	public boolean teamOnEachSide(String[][] usedComps, boolean juniorTeams) {
		Set<String> teamsPro = new HashSet<String>();
		Set<String> teamsCon = new HashSet<String>();
		for(int i = 0; i < usedComps.length; i++) {
			if(!teamsPro.contains(usedComps[i][0])) {
				teamsPro.add(usedComps[i][0]);
			}
			if(!teamsCon.contains(usedComps[i][1])) {
				teamsCon.add(usedComps[i][1]);
			}
		}
		if(juniorTeams) {
			if((teamsPro.size() == teamJuniorNames.size()) && (teamsCon.size() == teamJuniorNames.size())) return true;
			else return false;
		}
		else {
			if((teamsPro.size() == teamSeniorNames.size()) && (teamsCon.size() == teamSeniorNames.size())) return true;
			else return false;
		}
	}
	public Team replaceStringJunior(String s) { //findet das zum Teamnamen zugehörige Team
		int i = 0;
		while(teams_junior.get(i).getSchoolName().equals(s)) {
			i++;
		}
		return teams_junior.get(i);
	}
}
