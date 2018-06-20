package contestx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;

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
		debatesJ = new ArrayList<Debate>(); //die nachher auszugebende Liste der Debates
		debatesS = new ArrayList<Debate>();
		teamJuniorNames = new ArrayList<String>(); 
		teamSeniorNames = new ArrayList<String>();
		schulen = new ArrayList<Schule>();
		speaker = new ArrayList<Speaker>();
		judges = new ArrayList<Judge>();
		
		judges.add(new Judge("1", true));
		judges.add(new Judge("2", true));
		judges.add(new Judge("3", true));
		judges.add(new Judge("4", true));
		judges.add(new Judge("5", true));
		judges.add(new Judge("6", true));
		judges.add(new Judge("7", true));
		judges.add(new Judge("8", true));
		judges.add(new Judge("9", true));
		judges.add(new Judge("10", true));
		judges.add(new Judge("11", true));
		judges.add(new Judge("12", true));
		judges.add(new Judge("13", true));
		judges.add(new Judge("14", true));
		judges.add(new Judge("15", true));
		judges.add(new Judge("16", true));
		judges.add(new Judge("17", true));
		judges.add(new Judge("18", true));
		judges.add(new Judge("19", true));

		
	}
	
	public ArrayList<Debate> berechne(boolean junior) {
		reset(); //Komponenten erneuern/zurücksetzen
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
				usedComps[i-1][0] = teamNames.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 größer; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
				usedComps[i-1][1] = teamNames.get(2*i-1);
				
			}
			boolean run = true;
			while(run) { //Timezone 2
				Collections.shuffle(teamNames);
				for(int i = 1; i <= dPT; i++) {
					usedComps[dPT+i-1][0] = teamNames.get((2*i)-2); //"[i-1]", da for-schleife einsbasiert
					usedComps[dPT+i-1][1] = teamNames.get((2*i)-1); //usedCompsJunior wird ausgefüllt
				}
				if(!entryDuplicated(usedComps, 0, dPT*2)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgefüllten Bereich gibt, ist Timezone 2 fertig
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
				if(!entryDuplicated(usedComps, 0, dPT*3)) { //wenn es keine Duplikate (kein Team spielt 2mal gegen dasselbe) in usedCompsJunior im bisher ausgefüllten Bereich gibt, ist Timezone 2 fertig
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
	
	public void judgesZuordnen()
	{
		Collections.shuffle(judges);
		ArrayList<Judge> erfahren = new ArrayList<Judge>();
		ArrayList<Judge> kannAktuell = new ArrayList<Judge>();
		//Zeitzone 1
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ1()) {
				kannAktuell.add(judges.get(i));
			}
		}
		kannAktuell = erfahrenFuellen(erfahren, kannAktuell);
		zuordnen(1, erfahren, kannAktuell);
		
		erfahren.clear();
		kannAktuell.clear();
		//Zeitzone 2
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ2()) {
				kannAktuell.add(judges.get(i));
			}
		}
		kannAktuell = erfahrenFuellen(erfahren, kannAktuell);
		zuordnen(2, erfahren, kannAktuell);
		
		erfahren.clear();
		kannAktuell.clear();
		//Zeitzone 3
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ3()) {
				kannAktuell.add(judges.get(i));
			}
		}
		kannAktuell = erfahrenFuellen(erfahren, kannAktuell);
		zuordnen(3, erfahren, kannAktuell);
	}
	
	private ArrayList<Judge> erfahrenFuellen(ArrayList<Judge> erfahren, ArrayList<Judge> kannAktuell) {
		Collections.shuffle(judges);
		int x = 0;
		dPTsenior = 0; //erstmal wenn nur junior wichtig
		while(erfahren.size() < (dPTjunior + dPTsenior)) {
			if(kannAktuell.get(x).getErfahren()) {
				erfahren.add(kannAktuell.get(x));
				kannAktuell.remove(x);
				x--;
			}
			x++;
		}
		return kannAktuell;
	}
	private void zuordnen(int zeitzone, ArrayList<Judge> erfahren, ArrayList<Judge> kannAktuell) {
		for(int i = 0; i < erfahren.size(); i++) {
			JButton b;
			switch(zeitzone) {
			case 1: b = (JButton) this.gui.getDebates().get(i).getComponent(3);
			break;
			case 2: b = (JButton) this.gui.getDebates().get(i+erfahren.size()).getComponent(3);
			break;
			case 3: b = (JButton) this.gui.getDebates().get(2*erfahren.size()+i).getComponent(3);
			break;
			default: b = (JButton) this.gui.getDebates().get(i).getComponent(3);
			}
			Judge j1 = new Judge();
			Judge j2 = new Judge();
			JButton x = (JButton) this.gui.getDebates().get(i).getComponent(2);
			JButton y = (JButton) this.gui.getDebates().get(i).getComponent(1);
			String x1 = x.getText();
			x1.replaceAll("<html>Con<br/>", "");
			x1.replaceAll("</html>", "");
			String y1 = y.getText();
			y1.replaceAll("<html>Pro<br/>", "");
			y1.replaceAll("</html>", "");
			for(int j = 0; j < kannAktuell.size(); j++) {
				if(j1.getName().equals("")) {
					j1 = kannAktuell.get(j);
					kannAktuell.remove(j);
				}
				if(j2.getName().equals("")) {
					j2 = kannAktuell.get(j);
					kannAktuell.remove(j);
				}
			}
			
			if(!j1.getSchule().getName().equals(x1) && !j1.getSchule().getName().equals(y1) && !j2.getSchule().getName().equals(x1) && !j2.getSchule().getName().equals(y1))
				{
					b.setText(erfahren.get(i).getName() + ", " + j1.getName() + ", " + j2.getName());
				}
			
		}
		
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
	
	public boolean arrayContains(String[][] set, String[] temp) {
		for(int i = 0; i < set.length; i++) {
			if((set[i][0].equals(temp[0]) && set[i][1].equals(temp[1])) || (set[i][0].equals(temp[1]) && set[i][1].equals(temp[0]))) { //wenn die Zuordnung (TeamX vs. TeamY) bereits vorhanden ist
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
		
		if((teamsPro.size() == teamNames.size()) && (teamsCon.size() == teamNames.size())) return true; //wenn alle Teams enthalten sind, muss die Länge mit der der vorhandenen Teams übereinstimmen
		else return false;
	}
	public Team replaceStringJunior(String s) { //findet das zum Teamnamen zugehörige Team
		int i = 0;
		while(!teams_junior.get(i).getSchule().getName().equals(s)) {
			i++;
		}
		return teams_junior.get(i);
	}
	
	public Team replaceStringSenior(String s) { //findet das zum Teamnamen zugehörige Team
		int i = 0;
		while(!teams_senior.get(i).getSchule().getName().equals(s)) {
			i++;
		}
		return teams_senior.get(i);
	}
	public void reset() {
		//Zurücksetzen der Listen
		debatesJ.clear();
		debatesS.clear();
		teamJuniorNames.clear();
		teamSeniorNames.clear();
		//erneuern der vom GUI abgerufenen Daten
		teams_junior = this.gui.getJuniorTeams(); //Datenfluss: zur Liste der im GUI gespeicherten Teams wird hier referenziert
		for(int i = 0; i < teams_junior.size(); i++) {
			teamJuniorNames.add(teams_junior.get(i).getSchule().getName()); //Strings werden hier verwendet, da sie besser in der berechne()-Methode anwendbar sind, als Teams
		Collections.shuffle(teamJuniorNames); //Reihenfolge randomisieren
		dPTjunior = (int) teamJuniorNames.size()/2; //pro Zeitzone können nur die Hälfte (abgerundet) alles Teams in verschiedenen Debates sein, da zwei jener eins jenes besuchen
		}
		teams_senior = this.gui.getSeniorTeams();
		for(int i1 = 0; i1 < teams_senior.size(); i1++) {
			teamSeniorNames.add(teams_senior.get(i1).getSchule().getName());
		}
		Collections.shuffle(teamSeniorNames); //Reihenfolge randomisieren
		dPTsenior = (int) teamSeniorNames.size()/2;
		usedCompsJunior = new String[dPTjunior*3][2];
		usedCompsSenior = new String[dPTsenior*3][2];
	}
	
	public ArrayList<Schule> getSchulen() {
		return schulen;
	}
	
	public Judge getJudgeAt(int index)
	{
		return judges.get(index);
	}
	
	public ArrayList<Judge> getJudges() {
		return judges;
	}
	
	public void addJudge(Judge j) {
		judges.add(j);
	}
	
	public ArrayList<Speaker> getSpeaker() {
		return speaker;
	}
}
//Githubg ist beste

