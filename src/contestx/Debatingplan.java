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
		this.gui = gui; //referenzieren (f�r Datenfluss sp�ter)
		teams_junior = this.gui.getJuniorTeams(); //Datenfluss: zur Liste der im GUI gespeicherten Teams wird hier referenziert
		teamJuniorNames = new ArrayList<String>(); 
		for(int i = 0; i < teams_junior.size(); i++) {
			teamJuniorNames.add(teams_junior.get(i).getSchule().getName());
			teamJuniorNames.add(teams_junior.get(i).getSchule().getName()); //Strings werden hier verwendet, da sie besser in der berechne()-Methode anwendbar sind, als Teams
		Collections.shuffle(teamJuniorNames); //Reihenfolge randomisieren
		dPTjunior = (int) teamJuniorNames.size()/2; //pro Zeitzone k�nnen nur die H�lfte (abgerundet) alles Teams in verschiedenen Debates sein, da zwei jener eins jenes besuchen
		}
		teams_senior = this.gui.getSeniorTeams();
		teamSeniorNames = new ArrayList<String>();
		for(int i1 = 0; i1 < teams_senior.size(); i1++) {
			teamSeniorNames.add(teams_senior.get(i1).getSchule().getName());
		}
		Collections.shuffle(teamSeniorNames); //Reihenfolge randomisieren
		dPTsenior = (int) teamSeniorNames.size()/2; //?
		usedCompsJunior = new String[dPTjunior*3][2];
		usedCompsSenior = new String[dPTjunior*3][2];
		
		schulen = this.gui.getSchools();
		debatesJ = new ArrayList<Debate>(); //die nachher auszugebende Liste der Debates
		debatesS = new ArrayList<Debate>();
	}
	
	/*public String[][] berechne() {
		//Juniors
		while(!teamOnEachSide(usedCompsJunior, true)) {
			for(int i = 1; i <= dPTjunior; i++) { //Timezone 1; 1<=i<=dPTjunior, da i multipliziert wird.
				usedCompsJunior[i-1][0] = teamJuniorNames.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 gr��er; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
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
				if(!entryDuplicated(usedCompsJunior, 0, dPTjunior*2)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgef�llten Bereich gibt, ist Timezone 2 fertig
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
				if(!entryDuplicated(usedCompsJunior, 0, dPTjunior*3)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgef�llten Bereich gibt, ist Timezone 2 fertig
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
	}*/
	public ArrayList<Debate> berechne(boolean junior) {
		String[][] usedComps;
		int dPT;
		ArrayList<String> teamNames;
		if(junior) {
			usedComps = usedCompsJunior;
			dPT = dPTjunior;
			teamNames = teamJuniorNames;
		}
		else {
			usedComps = usedCompsSenior;
			dPT = dPTsenior;
			teamNames = teamSeniorNames;
		}
		while(!teamOnEachSide(usedComps, teamNames)) {
			for(int i = 1; i <= dPT; i++) { //Timezone 1
				usedComps[i-1][0] = teamNames.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 gr��er; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
				usedComps[i-1][1] = teamNames.get(2*i-1);
				
			}
			boolean run = true;
			while(run) { //Timezone 2
				Collections.shuffle(teamNames);
				for(int i = 1; i <= dPT; i++) {
					usedComps[dPT+i-1][0] = teamNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
					usedComps[dPT+i-1][1] = teamNames.get((2*i)-1); //usedCompsJunior wird ausgef�llt
				}
				if(!entryDuplicated(usedComps, 0, dPT*2)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgef�llten Bereich gibt, ist Timezone 2 fertig
					run = false;
				}
			}
			run = true;
			while(run) { //Timezone 3
				Collections.shuffle(teamNames); //teamnamenliste wird neu gemischt
				for(int i = 1; i <= dPT; i++) {
					usedComps[(2*dPT)+i-1][0] = teamNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
					usedComps[(2*dPT)+i-1][1] = teamNames.get((2*i)-1);
					
				}
				if(!entryDuplicated(usedComps, 0, dPT*3)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgef�llten Bereich gibt, ist Timezone 2 fertig
					run = false;
				}
			}
		}
		for(int i = 0; i < usedComps.length; i++) {
			System.out.println(usedComps[i][0]);
			System.out.println(usedComps[i][1] + "\n");
		}
		
		System.out.println(teamOnEachSide(usedComps, teamNames));
		for(int i = 0; i < dPT*3; i++) {
			if(junior) debatesJ.add(new Debate(replaceStringJunior(usedComps[i][0]), replaceStringJunior(usedComps[i][1]))); //Debates werden aufgrund der Teamnamen gebildet
			else debatesS.add(new Debate(replaceStringJunior(usedComps[i][0]), replaceStringJunior(usedComps[i][1]))); //Debates werden aufgrund der Teamnamen gebildet
		}
		if(junior) return debatesJ;
		else return debatesS;
	}
	/*public ArrayList<Debate> berechneSenior() {
		while(!teamOnEachSide(usedCompsSenior, false)) { //2
			for(int i = 1; i <= dPTsenior; i++) { //Timezone 1 /1
				usedCompsSenior[i-1][0] = teamSeniorNames.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 gr��er; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
				usedCompsSenior[i-1][1] = teamSeniorNames.get(2*i-1); //4
				
			}
			boolean run = true;
			while(run) { //Timezone 2
				Collections.shuffle(teamSeniorNames); //1
				for(int i = 1; i <= dPTsenior; i++) { //1
					usedCompsSenior[dPTsenior+i-1][0] = teamSeniorNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert /3
					usedCompsSenior[dPTsenior+i-1][1] = teamSeniorNames.get((2*i)-1); //usedCompsJunior wird ausgef�llt /3
				}
				if(!entryDuplicated(usedCompsSenior, 0, dPTsenior*2)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgef�llten Bereich gibt, ist Timezone 2 fertig
					run = false;
				}
			}
			run = true;
			while(run) { //Timezone 3
				Collections.shuffle(teamSeniorNames); //teamnamenliste wird neu gemischt /1
				for(int i = 1; i <= dPTsenior; i++) { //1
					usedCompsSenior[(2*dPTsenior)+i-1][0] = teamSeniorNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert /3
					usedCompsSenior[(2*dPTsenior)+i-1][1] = teamSeniorNames.get((2*i)-1); //3
					
				}
				if(!entryDuplicated(usedCompsSenior, 0, dPTsenior*3)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgef�llten Bereich gibt, ist Timezone 2 fertig
					run = false; //2
				}
			}
		}
		for(int i = 0; i < usedCompsSenior.length; i++) { //1
			System.out.println(usedCompsSenior[i][0]); //1
			System.out.println(usedCompsSenior[i][1] + "\n"); //1
		}
		
		System.out.println(teamOnEachSide(usedCompsSenior, false)); //2
		for(int i = 0; i < dPTsenior*3; i++) { //1
			debatesS.add(new Debate(replaceStringSenior(usedCompsSenior[i][0]), replaceStringSenior(usedCompsSenior[i][1]))); //Debates werden aufgrund der Teamnamen gebildet /5
		}
		return debatesS; //1
	}*/
	
	public boolean entryDuplicated(String[][] array, int start, int end) { //sucht nach Duplikaten im String[][]
		String[][] set = new String[array.length][array[0].length];
		for(int i = 0; i < set.length; i++) { //Array wird mit "" gef�llt (verhindert NullPointerException)
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
	
	public boolean arrayContains(String[][] set, String[] temp) { //nur f�r String[2] temp!
		for(int i = 0; i < set.length; i++) {
			if((set[i][0].equals(temp[0]) && set[i][1].equals(temp[1])) || (set[i][0].equals(temp[1]) && set[i][1].equals(temp[0]))) {
				return true;
			}
		}
		return false;
	}
	public boolean teamOnEachSide(String[][] usedComps, ArrayList<String> teamNames) {
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
		
		if((teamsPro.size() == teamNames.size()) && (teamsCon.size() == teamNames.size())) return true;
		else return false;
	}
	public Team replaceStringJunior(String s) { //findet das zum Teamnamen zugeh�rige Team
		int i = 0;
		while(!teams_junior.get(i).getSchule().getName().equals(s)) {
			i++;
		}
		return teams_junior.get(i);
	}
	
	public Team replaceStringSenior(String s) { //findet das zum Teamnamen zugeh�rige Team
		int i = 0;
		while(!teams_senior.get(i).getSchule().getName().equals(s)) {
			i++;
		}
		return teams_senior.get(i);
	}
}
