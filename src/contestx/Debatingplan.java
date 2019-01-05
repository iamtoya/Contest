package contestx;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Debatingplan implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Debate> debatesJ;
	private ArrayList<Debate> debatesS;
	private ArrayList<Judge> judges;
	private ArrayList<Schule> schulen;
	private ArrayList<Speaker> speaker; //Zugriff durch sortSpeaker()
	private ArrayList<Speaker> speakerSortiert; //nach Punkten
	private ArrayList<Speaker> ersterS;
	private ArrayList<Speaker> zweiterS;
	private ArrayList<Speaker> dritterS;
	private ArrayList<Team> teamsSSortiert; //nach Siegen
	private Team teamS1;
	private Team teamS2;
	private Team teamS3;
	private ArrayList<Team> teamsJSortiert; //nach Siegen
	private Team teamJ1;
	private Team teamJ2;
	private Team teamJ3;
	private ArrayList<String> motions;	  
	public Zeitzone zeitzone1;
	public Zeitzone zeitzone2;
	public Zeitzone zeitzone3;
	//  obiges nach Modellierung
	private Gui gui;
	private ArrayList<Team> teams_junior; //Zugriff durch sortTeams()
	private ArrayList<String> teamJuniorNames;
	private int dPTjunior;
	private ArrayList<Team> teams_senior; //Zugriff durch sortTeams()
	private ArrayList<String> teamSeniorNames;
	private int dPTsenior;
	private String[][] usedCompsJunior; //hier werden die verwendeten Kompositionen gespeichert
	private String[][] usedCompsSenior;
	private ArrayList<Judge> unbenutzt;
	private ArrayList<Debate> debatesAll;
	
	private Judge[] judgesz1;
	private Judge[] judgesz2;
	private Judge[] judgesz3;
	private ArrayList<Judge> erfahren;
	private ArrayList<Judge> kannAktuell;
	
	public Debatingplan(Gui gui) {
		this.gui = gui; //referenzieren (für Datenfluss später)
		debatesJ = new ArrayList<Debate>(); //die nachher auszugebende Liste der Debates
		debatesS = new ArrayList<Debate>();
		teams_junior = new ArrayList<Team>();
		teams_senior = new ArrayList<Team>();
		teamJuniorNames = new ArrayList<String>(); 
		teamSeniorNames = new ArrayList<String>();
		schulen = new ArrayList<Schule>();
		speaker = new ArrayList<Speaker>();
		judges = new ArrayList<Judge>();
		
		unbenutzt = new ArrayList<Judge>();
		
		debatesAll = new ArrayList<Debate>();
		
		speakerSortiert = new ArrayList<Speaker>();
		ersterS = new ArrayList<Speaker>();
		zweiterS = new ArrayList<Speaker>();
		dritterS = new ArrayList<Speaker>();
		
		teamsSSortiert = new ArrayList<Team>();
		teamsJSortiert = new ArrayList<Team>();
		
		motions = new ArrayList<String>();
		for(int i = 0; i < 3; i++) { //fill with 3 entries (for the 3 motions)
			motions.add("Motion " + (i + 1));
		}
		zeitzone1 = new Zeitzone(1);
		zeitzone2 = new Zeitzone(2);
		zeitzone3 = new Zeitzone(3);
		
		//Dummy-Schulen
		schulen.add(new Schule("1", true, true));
		schulen.add(new Schule("2", true, true));
		schulen.add(new Schule("3", true, true));
		schulen.add(new Schule("4", true, true));
		schulen.add(new Schule("5", true, true));
		schulen.add(new Schule("6", true, true));
		schulen.add(new Schule("7", true, true));
		schulen.add(new Schule("8", true, true));
		schulen.add(new Schule(true));
		
		//Dummy-Judges
		/*judges.add(new Judge("1", schulen.get(1), true));
		judges.add(new Judge("2", schulen.get(1), true));
		judges.add(new Judge("3", schulen.get(1), false));
		judges.add(new Judge("4", schulen.get(1), false));
		judges.add(new Judge("5", schulen.get(1), false));
		judges.add(new Judge("6", schulen.get(0), true));
		judges.add(new Judge("7", schulen.get(0), true));
		judges.add(new Judge("8", schulen.get(0), false));
		judges.add(new Judge("9", schulen.get(0), false));
		judges.add(new Judge("10", schulen.get(0), false));
		judges.add(new Judge("11", schulen.get(0), false));
		judges.add(new Judge("12", schulen.get(0), false));
		judges.add(new Judge("13", schulen.get(0), false));
		judges.add(new Judge("14", schulen.get(0), false));
		judges.add(new Judge("15", schulen.get(0), false));
		judges.add(new Judge("16", schulen.get(0), false));
		judges.add(new Judge("17", schulen.get(0), false));
		judges.add(new Judge("18", schulen.get(0), false));
		judges.add(new Judge("19", schulen.get(0), false));*/
		for(int i = 0; i < 24; i++) {
			judges.add(new Judge("Frau Kaltenbacher " + i, schulen.get(i % schulen.size()), (i % 2 == 0)));
		}

		
	}
	
	public void _speakerDummys()
	{
		for(int i=0;i<schulen.size();i++)
		{
			for(int j=0;j<schulen.get(i).getJuniorTeam().getAllSpeaker().size();j++)
			{
				speaker.add(schulen.get(i).getJuniorTeam().getAllSpeaker().get(j));
			}
		}
	}
	
	public ArrayList<Debate> berechne(boolean junior, boolean avoid_reset) {
		if(!avoid_reset) reset(); //Komponenten erneuern/zurücksetzen
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
		while(!teamOnEachSide(usedComps, teamNames)) { //jedes Team muss mindestens einmal Pro und Con sein, damit es nicht in 2 Zeitzonen aussetzen muss
			for(int i = 1; i <= dPT; i++) { //Timezone 1
				//Teams, die gegeneinander spielen sollen werden in usedComps gespeichert
				usedComps[i-1][0] = teamNames.get((2*i)-2); //"[i-1]", da i in for-schleife um 1 größer; "(2*i)-1" = Index der zugewiesenen Schule, immer: s1+s2;s3+s4...
				usedComps[i-1][1] = teamNames.get(2*i-1);
				
			}
			boolean run = true;
			while(run) { //Timezone 2: es wird zusätzlich überprüft, dass kein Team zweimal gegen dasselbe spielt
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
		//an diesem Punkt ist usedComps korrekt gefüllt
		for(int i = 0; i < usedComps.length; i++) {
			System.out.println(usedComps[i][0]);
			System.out.println(usedComps[i][1] + "\n");
		}
		
		System.out.println(teamOnEachSide(usedComps, teamNames));
		for(int i = 0; i < dPT*3; i++) {
			if(junior) {
				debatesJ.add(new Debate(replaceStringJunior(usedComps[i][0]), replaceStringJunior(usedComps[i][1]))); //Debates werden auf Basis der Teamnamen gebildet
			}
			else {
				debatesS.add(new Debate(replaceStringSenior(usedComps[i][0]), replaceStringSenior(usedComps[i][1]))); //Debates werden auf Basis der Teamnamen gebildet
			}
		}
		if(junior) return debatesJ;
		else return debatesS;
	}
	
	public void judgesZuordnen()
	{
		dPTjunior = debatesJ.size()/3;
		dPTsenior = debatesS.size()/3;
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < dPTjunior; j++) {
				debatesAll.add(debatesJ.get((i*dPTjunior) + j));
			}
			for (int j = 0; j < dPTsenior; j++) {
				debatesAll.add(debatesS.get((i*dPTsenior) + j));
			}
		}
		//Judge-Button-Texte resetten
		for(int i = 0; i < gui.getDebates().size(); i++) {
			JButton b = (JButton) gui.getDebates().get(i).getComponent(3);
			b.setText("");
		}
		//Judges in den Debates zurücksetzen
		for(int i = 0; i < debatesAll.size(); i++) {
			debatesAll.get(i).removeAllJudges();
		}
		
		Collections.shuffle(judges);
		ArrayList<Judge> erfahren = new ArrayList<Judge>();
		ArrayList<Judge> kannAktuell = new ArrayList<Judge>();
		//Zeitzone 1
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ1() && !judges.get(i).getErfahren()) { //alle unerfahrenen werden zu kannAktuell hinzugefügt
				kannAktuell.add(judges.get(i));
			}
			if(judges.get(i).getKannZuZZ1() && judges.get(i).getErfahren()) { //alle erfahrenen werden zu erfahren hinzugefügt
				erfahren.add(judges.get(i));
			}
		}
		//erfahren = erfahrenFuellen(erfahren, kannAktuell);
		//kannAktuell = kannAktuellKuerzen(erfahren, kannAktuell);
		if(!zuordnen(1, erfahren, 0, true)) JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
		else {
			for(int i = 0; i < unbenutzt.size(); i++) {
				kannAktuell.add(unbenutzt.get(i));
			}
			if(!zuordnen(1, kannAktuell, 0, false)) JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		
		unbenutzt.clear();
		erfahren.clear();
		kannAktuell.clear();
		//Zeitzone 2
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ2() && !judges.get(i).getErfahren()) {
				kannAktuell.add(judges.get(i));
			}
			if(judges.get(i).getKannZuZZ2() && judges.get(i).getErfahren()) {
				erfahren.add(judges.get(i));
			}
		}
		//erfahren = erfahrenFuellen(erfahren, kannAktuell);
		//kannAktuell = kannAktuellKuerzen(erfahren, kannAktuell);
		if(!zuordnen(2, erfahren, 0, true)) JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
		else {
			for(int i = 0; i < unbenutzt.size(); i++) {
				kannAktuell.add(unbenutzt.get(i));
			}
			if(!zuordnen(2, kannAktuell, 0, false)) JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		
		unbenutzt.clear();
		erfahren.clear();
		kannAktuell.clear();
		//Zeitzone 3
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ3() && !judges.get(i).getErfahren()) {
				kannAktuell.add(judges.get(i));
			}
			if(judges.get(i).getKannZuZZ3() && judges.get(i).getErfahren()) {
				erfahren.add(judges.get(i));
			}
		}
		//erfahren = erfahrenFuellen(erfahren, kannAktuell);
		//kannAktuell = kannAktuellKuerzen(erfahren, kannAktuell);
		if(!zuordnen(3, erfahren, 0, true)) JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
		else {
			for(int i = 0; i < unbenutzt.size(); i++) {
				kannAktuell.add(unbenutzt.get(i));
			}
			if(!zuordnen(3, kannAktuell, 0, false)) JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		
		unbenutzt.clear();
	}
	
	public boolean judgesZuordnen2() {
		debatesAll.clear();
		for(int i = 0; i < 3; i++) { //3 Zeitzonen werden durchlaufen
			for (int j = 0; j < dPTjunior; j++) {
				debatesAll.add(debatesJ.get((i*dPTjunior) + j));
			}
			for (int j = 0; j < dPTsenior; j++) {
				debatesAll.add(debatesS.get((i*dPTsenior) + j));
			}
		}
		//Judges in den Debates zurücksetzen
		for(int i = 0; i < debatesAll.size(); i++) {
			debatesAll.get(i).removeAllJudges();
		}
		
		
		
		dPTjunior = debatesJ.size()/3;
		dPTsenior = debatesS.size()/3;
		
		judgesz1 = new Judge[(dPTjunior + dPTsenior)*3];
		judgesz2 = new Judge[(dPTjunior + dPTsenior)*3];
		judgesz3 = new Judge[(dPTjunior + dPTsenior)*3];
		
		kannAktuell = new ArrayList<Judge>();
		erfahren = new ArrayList<Judge>();
		
		Collections.shuffle(judges);
		
		//Zeitzone 1
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ1() && !judges.get(i).getErfahren()) { //alle unerfahrenen werden zu kannAktuell hinzugefügt
				kannAktuell.add(judges.get(i));
			}
			
			if(judges.get(i).getKannZuZZ1() && judges.get(i).getErfahren()) { //alle erfahrenen werden zu erfahren hinzugefügt
				erfahren.add(judges.get(i));
			}
		}
		
		//if(!zuordnen2(1, 0, 0, true)) {
		if(!zuordnen3(0, 1)) {
			JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		erfahren.clear();
		kannAktuell.clear();
		/*else {
			for(int i = 0; i < unbenutzt.size(); i++) {
				kannAktuell1.add(unbenutzt.get(i));
			}
			unbenutzt.clear();
			if(!zuordnen2(1, kannAktuell1, 0, 1)) {
				JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else {
				for(int i = 0; i < unbenutzt.size(); i++) {
					kannAktuell2.add(unbenutzt.get(i));
				}
				if(!zuordnen2(1, kannAktuell2, 0, 2)) {
					JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		unbenutzt.clear();
		erfahren.clear();
		kannAktuell1.clear();
		kannAktuell2.clear();*/
		//Zeitzone 2
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ2() && !judges.get(i).getErfahren()) { //alle unerfahrenen werden zu kannAktuell hinzugefügt
				kannAktuell.add(judges.get(i));
			}
			
			if(judges.get(i).getKannZuZZ2() && judges.get(i).getErfahren()) { //alle erfahrenen werden zu erfahren hinzugefügt
				erfahren.add(judges.get(i));
			}
		}

		//if(!zuordnen2(2, 0, 0, true)) {
		if(!zuordnen3(0, 2)) {
			JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		erfahren.clear();
		kannAktuell.clear();
		/*else {
			for(int i = 0; i < unbenutzt.size(); i++) {
				kannAktuell1.add(unbenutzt.get(i));
			}
			unbenutzt.clear();
			if(!zuordnen2(2, kannAktuell1, 0, 1)) {
				JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else {
				for(int i = 0; i < unbenutzt.size(); i++) {
					kannAktuell2.add(unbenutzt.get(i));
				}
				if(!zuordnen2(2, kannAktuell2, 0, 2)) {
					JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		unbenutzt.clear();
		erfahren.clear();
		kannAktuell1.clear();
		kannAktuell2.clear();*/
		//Zeitzone 3
		for(int i = 0; i < judges.size(); i++)
		{
			if(judges.get(i).getKannZuZZ3() && !judges.get(i).getErfahren()) { //alle unerfahrenen werden zu kannAktuell hinzugefügt
				kannAktuell.add(judges.get(i));
			}
			
			if(judges.get(i).getKannZuZZ3() && judges.get(i).getErfahren()) { //alle erfahrenen werden zu erfahren hinzugefügt
				erfahren.add(judges.get(i));
			}
		}
		
		//if(!zuordnen2(3, 0, 0, true)) {
		if(!zuordnen3(0, 3)) {
			JOptionPane.showMessageDialog(new JFrame(), "Judges couldn't be assigned correctly\nNo matching assignment possible.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		erfahren.clear();
		kannAktuell.clear();
		
		//Judges in die Debates eintragen
		
		for(int i = 0; i < dPTjunior + dPTsenior; i++) {
			for(int j = 0; j < 3; j++) {
				debatesAll.get(i).addJudge(getCalculatedJudges(1)[i][j]);
				debatesAll.get(i + dPTjunior + dPTsenior).addJudge(getCalculatedJudges(2)[i][j]);
				debatesAll.get(i + 2*(dPTjunior + dPTsenior)).addJudge(getCalculatedJudges(3)[i][j]);
			}
		}
		return true;
	}
	
	private boolean zuordnen(int zeitzone, ArrayList<Judge> kannAktuell, int index, boolean erfahren) { //index steht für das index-te Debate der Zz.
		boolean letzterDurchlauf = false; //beschreibt, ob sich diese Ausführung im 2. Durchlauf der unerfahrenen Judges befindet (im letzten Durchlauf)
		ArrayList<Judge> bereitsVersucht = new ArrayList<Judge>();
		if(erfahren) {
			if(!(index < dPTjunior + dPTsenior)) { //wenn Limit überschritten, erster Erfolg
				for(int i = 0; i < kannAktuell.size(); i++) { //unbenutzte erfahrene Judges speichern
					unbenutzt.add(kannAktuell.get(i));
				}
				return true;
			}
		}
		else {
			if(!(index < 2*(dPTjunior + dPTsenior))) { //wenn Limit überschritten, erster Erfolg
				return true;
			}
			if(index >= (dPTjunior + dPTsenior)) { //wenn bereits beim 2. Durchlauf
				letzterDurchlauf = true;
				index = index - dPTjunior - dPTsenior; //index verringern, damit er gleich behandelt werden kann
			}
		}
		
		JPanel b;
		Debate d;
		switch(zeitzone) {
			case 1: b = this.gui.getDebates().get(index);
				d = debatesAll.get(index);
				break;
			case 2: b = this.gui.getDebates().get(index+dPTjunior+dPTsenior);
				d = debatesAll.get(dPTjunior + dPTsenior + index);
				break;
			case 3: b = this.gui.getDebates().get(2*(dPTjunior+dPTsenior)+index);
				d = debatesAll.get(2*(dPTjunior + dPTsenior) + index);
				break;
			default: b = this.gui.getDebates().get(index);
				d = debatesAll.get(index);
				break;
		}
		Judge erfahrenerJudge;
			
		JButton judge_button = (JButton) b.getComponent(3);
		JButton x = (JButton) b.getComponent(2);
		JButton y = (JButton) b.getComponent(1);
		String x1 = x.getText();
		x1 = x1.replaceAll("<html>Con<br/>", "");
		x1 = x1.replaceAll("</html>", "");
		String y1 = y.getText();
		y1 = y1.replaceAll("<html>Pro<br/>", "");
		y1 = y1.replaceAll("</html>", "");
			
		erfahrenerJudge = new Judge();
		int i = 0;
		while(i < kannAktuell.size() && (kannAktuell.get(i).getSchule().getName().equals(x1) 
				|| kannAktuell.get(i).getSchule().getName().equals(y1))) { //i erhöhen bis möglicher Judge gefunden, der nicht zu Schule Pro und Schule Con gehört
			i++;
		}
		if(!(i == kannAktuell.size())) { //wenn möglicher Judge gefunden
			erfahrenerJudge = kannAktuell.get(i);
			String s = "";
			if(letzterDurchlauf) {
				if(containsDoubleComma(judge_button.getText())) {
					s = judge_button.getText().substring(0, judge_button.getText().lastIndexOf(","));
					judge_button.setText(s);
					d.removeJudge(2);
				}
			}
			else {
				if(judge_button.getText().contains(",")) {
					judge_button.setText(judge_button.getText().substring(0, judge_button.getText().lastIndexOf(","))); //kürzt den String um das zu ersetzende Ende
					d.removeJudge(1);
				}
			}
			if(judge_button.getText() != "") {
				judge_button.setText(judge_button.getText() + ", " + erfahrenerJudge.getName());
			}
			else judge_button.setText(erfahrenerJudge.getName());
			d.addJudge(erfahrenerJudge);
			
			kannAktuell.remove(i); //Liste wird gekürzt, damit der benutzte Judge nicht mehr verwendet werden kann
			bereitsVersucht.add(erfahrenerJudge); //damit ein Judge nicht mehrmals für dieselbe Position versucht wird
			Collections.shuffle(kannAktuell); //more randomness
			if(letzterDurchlauf) index = index + dPTjunior + dPTsenior; //index wieder erhöhen
			while(!zuordnen(zeitzone, kannAktuell, index+1, erfahren)) { //alle darunterliegenden Äste durchprobieren
					
				Judge temp = erfahrenerJudge; //zusätzliche Variable für den verlustfreien Austausch des gewählten Judges
					
				int j = 0;
				while(j < kannAktuell.size() && (kannAktuell.get(j).getSchule().getName().equals(x1) //neuen verfügbaren Judge finden
						|| kannAktuell.get(j).getSchule().getName().equals(y1)
						|| bereitsVersucht.contains(kannAktuell.get(j)))) {
					j++;
				}
				if(!(j == kannAktuell.size())) { //wenn gefunden
					erfahrenerJudge = kannAktuell.get(j);
					if(letzterDurchlauf) {
						if(containsDoubleComma(judge_button.getText())) { //Button-Text kürzen wenn zu lang
							s = judge_button.getText().substring(0, judge_button.getText().lastIndexOf(","));
							judge_button.setText(s);
							d.removeJudge(2);
						}
					}
					else { //Button-Text kürzen wenn zu lang
						if(judge_button.getText().contains(",")) {
							judge_button.setText(judge_button.getText().substring(0, judge_button.getText().lastIndexOf(","))); //kürzt den String um das zu ersetzende Ende
							d.removeJudge(1);
						}
					}
					//Judge auf Button schreiben
					if(judge_button.getText() != "") judge_button.setText(judge_button.getText() + ", " + erfahrenerJudge.getName());
					else judge_button.setText(erfahrenerJudge.getName());
					d.addJudge(erfahrenerJudge);
					kannAktuell.remove(j);
					bereitsVersucht.add(erfahrenerJudge);
					kannAktuell.add(temp);
				}
				else { //wenn keiner mehr verfügbar
					kannAktuell.add(temp);
					return false; //kein Judge kann zugeordnet werden
				}
			} //hier konnten alle Judges zugeordnet werden
			return true;
		} 
		else { //wenn kein Judge verfügbar ist
			return false; //kein Judge kann zugeordnet werden
		}

	} 
	
	public boolean zuordnen2(int zeitzone, int index, int debateNr, boolean erfahren) {
		if(index > 3*(dPTjunior + dPTsenior) - 1) {
			return true;
		}
		
		ArrayList<Judge> verfuegbar; //Liste der tatsächlich für dieses Debate verfügbaren Judges
		if(index >= dPTjunior + dPTsenior) {
			erfahren = false;
			for(int i = 0; i < this.erfahren.size(); i++) {
				kannAktuell.add(this.erfahren.get(i));
			}
		}
		else if(index < dPTjunior + dPTsenior) {
			erfahren = true;
			for(int i = 0; i < kannAktuell.size(); i++) {
				if(kannAktuell.get(i).getErfahren()) {
					this.erfahren.add(kannAktuell.get(i));
					kannAktuell.remove(i);
				}
			}
		}
		if(erfahren) verfuegbar = this.erfahren;
		else verfuegbar = kannAktuell;
		
		JPanel b;
		Judge[] chosenJudges = new Judge[(dPTjunior + dPTsenior)*3];
		ArrayList<Judge> bereitsVersucht = new ArrayList<Judge>();
		switch(zeitzone) {
			case 1: {
				b = this.gui.getDebates().get(debateNr);
				chosenJudges = judgesz1;
				break;
			}
			case 2: {
				b = this.gui.getDebates().get(debateNr+dPTjunior+dPTsenior);
				chosenJudges = judgesz2;
				break;
			}
			case 3: {
				b = this.gui.getDebates().get(2*(dPTjunior+dPTsenior)+debateNr);
				chosenJudges = judgesz3;
				break;
			}
			default: {
				b = this.gui.getDebates().get(debateNr);
				break;
			}
		}
		Judge erfahrenerJudge;
		JButton x = (JButton) b.getComponent(2);
		JButton y = (JButton) b.getComponent(1);
		String x1 = x.getText();
		x1 = x1.replaceAll("<html>Con<br/>", "");
		x1 = x1.replaceAll("</html>", "");
		String y1 = y.getText();
		y1 = y1.replaceAll("<html>Pro<br/>", "");
		y1 = y1.replaceAll("</html>", "");
		if(debateNr < dPTjunior + dPTsenior - 1) {
			debateNr++;
		}
		else {
			debateNr = 0;
		}
		
		int i = 0;
		while(i < verfuegbar.size() && (verfuegbar.get(i).getSchule().getName().equals(x1) 
				|| verfuegbar.get(i).getSchule().getName().equals(y1))) { //i erhöhen bis möglicher Judge gefunden, der nicht zu Schule Pro und Schule Con gehört
			i++;
		}
		
		if(i != verfuegbar.size()) { //wenn Judge gefunden:
			erfahrenerJudge = verfuegbar.get(i);
			verfuegbar.remove(i);
			bereitsVersucht.add(erfahrenerJudge);
			chosenJudges[index] = erfahrenerJudge;
			Collections.shuffle(verfuegbar);
			while(!zuordnen2(zeitzone, index+1, debateNr, erfahren)) {
				Judge temp = erfahrenerJudge;
				
				int j = 0;
				while(j < verfuegbar.size() && (verfuegbar.get(j).getSchule().getName().equals(x1) //neuen verfügbaren Judge finden
						|| verfuegbar.get(j).getSchule().getName().equals(y1)
						|| bereitsVersucht.contains(verfuegbar.get(j)))) {
					j++;
				}
				if(j != verfuegbar.size()) {
					erfahrenerJudge = verfuegbar.get(j);
					verfuegbar.remove(j);
					bereitsVersucht.add(erfahrenerJudge);
					chosenJudges[index] = erfahrenerJudge;
					verfuegbar.add(temp);
				}
				else { //kein Ast gibt true zurück
					verfuegbar.add(temp);
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean zuordnen3(int index, int zeitzone) { 
		Judge[] calculated_judges; //zu füllende Liste wird abhängig von der Zeitzone ausgewählt
		if(zeitzone == 1) calculated_judges = judgesz1;
		else if(zeitzone == 2) calculated_judges = judgesz2;
		else calculated_judges = judgesz3;
		
		if(!Arrays.asList(calculated_judges).contains(null) && !gui.hasDuplicate(this.getCalculatedJudges(zeitzone))) return true; //Abbruchbedingung
		
		ArrayList<Judge> available = new ArrayList<Judge>(); //beinhaltet alle für dieses Debate verfügbaren Judges
		//Schulen werden aus Debate mittels >index< ausgelesen (notwendig als Bedingung für >available<)
		String schule1 = debatesAll.get((zeitzone - 1) * (dPTjunior + dPTsenior) + index % (dPTjunior + dPTsenior)).getTeamPro().getSchule().getName();
		String schule2 = debatesAll.get((zeitzone - 1) * (dPTjunior + dPTsenior) + index % (dPTjunior + dPTsenior)).getTeamCon().getSchule().getName();
		for(int i = 0; i < judges.size(); i++) { //>available< wird gefüllt...
			if(judges.get(i).getKannZuZeit(zeitzone) && //is available at this timezone
					judges.get(i).getSchule().getName() != schule1 && //is not from Pro's school
					judges.get(i).getSchule().getName() != schule2 && //is not from Con's school
					!Arrays.asList(calculated_judges).contains(judges.get(i)) && //has not been already selected
					!(index < (dPTjunior + dPTsenior) && !judges.get(i).getErfahren()) //there is at least 1 experienced judge
					)
			{
				available.add(judges.get(i));
			}
		}
		if(available.isEmpty()) return false; //kein Judge verfügbar -> korrekte Zuordnung an diesem Punkt nicht möglich
		int judge_to_test_index = 0; //iteriert über >available<
		while(judge_to_test_index < available.size()) {
			calculated_judges[index] = available.get(judge_to_test_index);
			if(zuordnen3(index + 1, zeitzone)) return true;
			else judge_to_test_index++; 
			System.out.println("" + judge_to_test_index);
		}
		return false;
	}
	public boolean containsDoubleComma(String t) {
		if(t.contains(",")) {
			String s = t.substring(0, t.lastIndexOf(","));
			if(s.contains(",")) {
				return true;
			}
			else return false;
		}
		else return false;
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
	public boolean teamOnEachSide(String[][] usedComps, ArrayList<String> teamNames) { //prüft, ob alle teilnehmenden Teams mindestens einmal Pro und mindestens einmal Con sind
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
		teams_junior.clear();
		teams_senior.clear();
		teamJuniorNames.clear();
		teamSeniorNames.clear();
		//Erneuern der vom GUI abgerufenen Daten
		for(int i = 0; i < schulen.size(); i++) {
			if(schulen.get(i).getHasJuniorTeam()) {
				teams_junior.add(schulen.get(i).getJuniorTeam());
			}
			if(schulen.get(i).getHasSeniorTeam()) {
				teams_senior.add(schulen.get(i).getSeniorTeam());
			}
		}
		//teams_junior = this.gui.getJuniorTeams(); //Datenfluss: zur Liste der im GUI gespeicherten Teams wird hier referenziert
		for(int i = 0; i < teams_junior.size(); i++) {
			teamJuniorNames.add(teams_junior.get(i).getSchule().getName()); //Strings werden hier verwendet, da sie besser in der berechne()-Methode anwendbar sind, als Teams
		Collections.shuffle(teamJuniorNames); //Reihenfolge randomisieren
		dPTjunior = (int) teamJuniorNames.size()/2; //pro Zeitzone können nur die Hälfte (abgerundet) alles Teams in verschiedenen Debates sein, da zwei jener eins jenes besuchen
		}
		//teams_senior = this.gui.getSeniorTeams();
		for(int i1 = 0; i1 < teams_senior.size(); i1++) {
			teamSeniorNames.add(teams_senior.get(i1).getSchule().getName());
		}
		Collections.shuffle(teamSeniorNames); //Reihenfolge randomisieren
		dPTsenior = (int) teamSeniorNames.size()/2;
		usedCompsJunior = new String[dPTjunior*3][2];
		usedCompsSenior = new String[dPTsenior*3][2];
	}
	
	public void bestSpeaker()
	{
		sortSpeaker();
		int i=0;
		while(speakerSortiert.get(i).getHoechstePunkte()==speakerSortiert.get(0).getHoechstePunkte())
		{
			i++;
		}
		int j=i+1;
		while(speakerSortiert.get(j).getHoechstePunkte()==speakerSortiert.get(i+1).getHoechstePunkte())
		{
			j++;
		}
		int k=j+1;
		while(speakerSortiert.get(k).getHoechstePunkte()==speakerSortiert.get(j+1).getHoechstePunkte())
		{
			k++;
		}
		for(int a=0; a<i; a++)
		{
			ersterS.add(speakerSortiert.get(a));
		}
		for(int s=i+1; s<j; s++)
		{
			zweiterS.add(speakerSortiert.get(s));
		}
		for(int d=j+1; d<k; d++)
		{
			dritterS.add(speakerSortiert.get(d));
		}
	}
	
	public void bestSpeaker2() {
		ersterS.clear();
		zweiterS.clear();
		dritterS.clear();
		sortSpeaker2();
		int i = 0;
		System.out.println("Erste:");
		while(i < speaker.size() && speaker.get(0).getHoechstePunkte() == speaker.get(i).getHoechstePunkte()) {
			ersterS.add(speaker.get(i));
			System.out.println(speaker.get(i).getName());
			i++;
		}
		int j = i;
		System.out.println("Zweite:");
		while(j < speaker.size() && speaker.get(i).getHoechstePunkte() == speaker.get(j).getHoechstePunkte()) {
			zweiterS.add(speaker.get(j));
			System.out.println(speaker.get(j).getName());
			j++;
		}
		int k = j;
		System.out.println("Dritte:");
		while(k < speaker.size() && speaker.get(j).getHoechstePunkte() == speaker.get(k).getHoechstePunkte()) {
			dritterS.add(speaker.get(k));
			System.out.println(speaker.get(k).getName());
			k++;
		}
	}
	
	private void sortTeams(boolean junior)
	{
		ArrayList<Team> teams1 = new ArrayList<Team>();
		ArrayList<Team> temp = new ArrayList<Team>();
		if(junior)
		{
			teamsJSortiert.clear();
			teamsJSortiert.add(new Team());
			for(int y=0;y<teams_junior.size();y++)		
			{
				teams1.add(teams_junior.get(y));
			}
			for(int i=0; i<teams1.size();i++)
			{
				teams1.get(i).setHoechstPunkte();
			}
			while(teamsJSortiert.size()<teams_junior.size())
			{
				temp.add(new Team());
				for(int j=0; j<teams1.size();j++)
				{
					if(teams1.get(j).getWinAmount()>temp.get(0).getWinAmount())
					{
						temp.clear();
						temp.add(teams1.get(j));
						teams1.remove(j);
					}
					else if(teams1.get(j).getWinAmount()==temp.get(0).getWinAmount())
					{
						temp.add(teams1.get(j));
						teams1.remove(j);
					}
					for(int h=0; h<temp.size();h++)
					{
						teamsJSortiert.add(temp.get(h));
					}
					temp.clear();
				}
				if(teamsSSortiert.get(0).getSchule().getName().equals("")) teamsSSortiert.remove(0);
			}
		}
		else
		{
			teamsSSortiert.clear();
			teamsSSortiert.add(new Team());
			for(int y=0;y<teams_senior.size();y++)		
			{
				teams1.add(teams_senior.get(y));
			}
			for(int i=0; i<teams1.size();i++)
			{
				teams1.get(i).setHoechstPunkte();
			}
			while(teamsSSortiert.size()<teams_senior.size())
			{
				temp.add(new Team());
				for(int j=0; j<teams1.size();j++)
				{
					if(teams1.get(j).getWinAmount()>temp.get(0).getWinAmount())
					{
						temp.clear();
						temp.add(teams1.get(j));
						teams1.remove(j);
					}
					else if(teams1.get(j).getWinAmount()==temp.get(0).getWinAmount())
					{
						temp.add(teams1.get(j));
						teams1.remove(j);
					}
				}
				for(int h=0; h<temp.size();h++)
				{
					teamsSSortiert.add(temp.get(h));
				}
				temp.clear();
			}
			if(teamsSSortiert.get(0).getSchule().getName().equals("")) teamsSSortiert.remove(0);
		}
	}
	
	public void sortTeams2(boolean junior) {
		if(junior) {
			Collections.sort(teams_junior, new TeamComparator());
		}
		else {
			Collections.sort(teams_senior, new TeamComparator());
		}
	}
	
	public void bestTeams(boolean junior)
	{
		sortTeams(junior);
		ArrayList<Team> teams1 = new ArrayList<Team>();
		ArrayList<Team> temp = new ArrayList<Team>();
		ArrayList<Team> nachPunkten = new ArrayList<Team>();
		nachPunkten.clear();
		nachPunkten.add(new Team());
		if(junior)
		{
			for(int y=0;y<teamsJSortiert.size();y++)		
			{
				teams1.add(teamsJSortiert.get(y));
			}
			int i=0;
			while(teamsJSortiert.get(i).getWinAmount()==teamsJSortiert.get(0).getWinAmount())
			{
				i++;
			}
			if(i>=3)
			{
				while(nachPunkten.size()<teamsJSortiert.size())
				{
					for(int x=0;x<i;x++)
					{
						if(teams1.get(x).getHoechstPunkte()>nachPunkten.get(0).getHoechstPunkte())
						{
							nachPunkten.clear();
							nachPunkten.add(teams1.get(x));
							teams1.remove(x);
						}
						else if(teams1.get(x).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
						{
							nachPunkten.add(teams1.get(x));
							teams1.remove(x);
						}
					}
					for(int h=0; h<temp.size();h++)
					{
						nachPunkten.add(temp.get(h));
					}
					temp.clear();
				}
				int o=0;
				while(nachPunkten.get(o).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
				{
					o++;
				}
				if(o!=0)
				{
					Team besterSpeaker=new Team();
					besterSpeaker=nachPunkten.get(0);
					for(int z=0;z<o;z++)
					{
						if(besterSpeaker.getBestenSpeaker().getHoechstePunkte()<nachPunkten.get(o).getBestenSpeaker().getHoechstePunkte())
						{
							besterSpeaker=nachPunkten.get(o);
						}
					}
					teamJ1=besterSpeaker;
				}
				else
				{
					while(nachPunkten.get(o).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
					{
						o++;
					}
					if(o!=0)
					{
						Team besterSpeaker=new Team();
						besterSpeaker=nachPunkten.get(0);
						for(int z=0;z<o;z++)
						{
							if(besterSpeaker.getBestenSpeaker().getHoechstePunkte()<nachPunkten.get(o).getBestenSpeaker().getHoechstePunkte())
							{
								besterSpeaker=nachPunkten.get(o);
							}
						}
						teamJ2=besterSpeaker;
					}
					else
					{
						while(nachPunkten.get(o).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
						{
							o++;
						}
						if(o!=0)
						{
							Team besterSpeaker=new Team();
							besterSpeaker=nachPunkten.get(0);
							for(int z=0;z<o;z++)
							{
								if(besterSpeaker.getBestenSpeaker().getHoechstePunkte()<nachPunkten.get(o).getBestenSpeaker().getHoechstePunkte())
								{
									besterSpeaker=nachPunkten.get(o);
								}
							}
						teamJ3=besterSpeaker;
						}
					}
				}
			}
			//ab hier weniger abgefangen
			else if(i==2)
			{
				if(teamsJSortiert.get(0).getHoechstPunkte()>teamsJSortiert.get(1).getHoechstPunkte())
				{
					teamJ1=teamsJSortiert.get(0);
					teamJ2=teamsJSortiert.get(1);
				}
				else
				{
					teamJ2=teamsJSortiert.get(0);
					teamJ1=teamsJSortiert.get(1);
				}
				//aus den Teams mit weniger Punkten
				while(nachPunkten.size()<teamsJSortiert.size()-2)
				{
					for(int x=0;x<i;x++)
					{
						if(teams1.get(x).getHoechstPunkte()>nachPunkten.get(0).getHoechstPunkte())
						{
							nachPunkten.clear();
							nachPunkten.add(teams1.get(x));
							teams1.remove(x);
						}
						else if(teams1.get(x).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
						{
							nachPunkten.add(teams1.get(x));
							teams1.remove(x);
						}
					}
					for(int h=0; h<temp.size();h++)
					{
						nachPunkten.add(temp.get(h));
					}
					temp.clear();
				}
				teamJ3=nachPunkten.get(0);
			}
			else if(i==1)
			{
				teamJ1=teamsJSortiert.get(0);
				while(nachPunkten.size()<teamsJSortiert.size()-1)
				{
					for(int x=0;x<i;x++)
					{
						if(teams1.get(x).getHoechstPunkte()>nachPunkten.get(0).getHoechstPunkte())
						{
							nachPunkten.clear();
							nachPunkten.add(teams1.get(x));
							teams1.remove(x);
						}
						else if(teams1.get(x).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
						{
							nachPunkten.add(teams1.get(x));
							teams1.remove(x);
						}
					}
					for(int h=0; h<temp.size();h++)
					{
						nachPunkten.add(temp.get(h));
					}
					temp.clear();
				}
				teamJ2=nachPunkten.get(0);
				teamJ3=nachPunkten.get(1);
			}
		}
		else
		{
			{
				for(int y=0;y<teamsSSortiert.size();y++)		
				{
					teams1.add(teamsSSortiert.get(y));
				}
				int i=0;
				while(teamsSSortiert.get(i).getWinAmount()==teamsSSortiert.get(0).getWinAmount())
				{
					i++;
				}
				if(i>=3)
				{
					while(nachPunkten.size()<teamsJSortiert.size())
					{
						for(int x=0;x<i;x++)
						{
							if(teams1.get(x).getHoechstPunkte()>nachPunkten.get(0).getHoechstPunkte())
							{
								nachPunkten.clear();
								nachPunkten.add(teams1.get(x));
								teams1.remove(x);
							}
							else if(teams1.get(x).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
							{
								nachPunkten.add(teams1.get(x));
								teams1.remove(x);
							}
						}
						for(int h=0; h<temp.size();h++)
						{
							nachPunkten.add(temp.get(h));
						}
						temp.clear();
					}
					int o=0;
					while(nachPunkten.get(o).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
					{
						o++;
					}
					if(o!=0)
					{
						Team besterSpeaker=new Team();
						besterSpeaker=nachPunkten.get(0);
						for(int z=0;z<o;z++)
						{
							if(besterSpeaker.getBestenSpeaker().getHoechstePunkte()<nachPunkten.get(o).getBestenSpeaker().getHoechstePunkte())
							{
								besterSpeaker=nachPunkten.get(o);
							}
						}
						teamS1=besterSpeaker;
					}
					else
					{
						while(nachPunkten.get(o).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
						{
							o++;
						}
						if(o!=0)
						{
							Team besterSpeaker=new Team();
							besterSpeaker=nachPunkten.get(0);
							for(int z=0;z<o;z++)
							{
								if(besterSpeaker.getBestenSpeaker().getHoechstePunkte()<nachPunkten.get(o).getBestenSpeaker().getHoechstePunkte())
								{
									besterSpeaker=nachPunkten.get(o);
								}
							}
							teamS2=besterSpeaker;
						}
						else
						{
							while(nachPunkten.get(o).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
							{
								o++;
							}
							if(o!=0)
							{
								Team besterSpeaker=new Team();
								besterSpeaker=nachPunkten.get(0);
								for(int z=0;z<o;z++)
								{
									if(besterSpeaker.getBestenSpeaker().getHoechstePunkte()<nachPunkten.get(o).getBestenSpeaker().getHoechstePunkte())
									{
										besterSpeaker=nachPunkten.get(o);
									}
								}
							teamS3=besterSpeaker;
							}
						}
					}
				}
				else if(i==2)
				{
					if(teamsSSortiert.get(0).getHoechstPunkte()>teamsSSortiert.get(1).getHoechstPunkte())
					{
						teamS1=teamsSSortiert.get(0);
						teamS2=teamsSSortiert.get(1);
					}
					else
					{
						teamS2=teamsSSortiert.get(0);
						teamS1=teamsSSortiert.get(1);
					}
					//aus den Teams mit weniger Punkten
					while(nachPunkten.size()<teamsSSortiert.size()-2)
					{
						for(int x=0;x<i;x++)
						{
							if(teams1.get(x).getHoechstPunkte()>nachPunkten.get(0).getHoechstPunkte())
							{
								nachPunkten.clear();
								nachPunkten.add(teams1.get(x));
								teams1.remove(x);
							}
							else if(teams1.get(x).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
							{
								nachPunkten.add(teams1.get(x));
								teams1.remove(x);
							}
						}
						for(int h=0; h<temp.size();h++)
						{
							nachPunkten.add(temp.get(h));
						}
						temp.clear();
					}
					teamS3=nachPunkten.get(0);
				}
				else if(i==1)
				{
					teamS1=teamsSSortiert.get(0);
					while(nachPunkten.size()<teamsSSortiert.size()-1)
					{
						for(int x=0;x<i;x++)
						{
							if(teams1.get(x).getHoechstPunkte()>nachPunkten.get(0).getHoechstPunkte())
							{
								nachPunkten.clear();
								nachPunkten.add(teams1.get(x));
								teams1.remove(x);
							}
							else if(teams1.get(x).getHoechstPunkte()==nachPunkten.get(0).getHoechstPunkte())
							{
								nachPunkten.add(teams1.get(x));
								teams1.remove(x);
							}
						}
						for(int h=0; h<temp.size();h++)
						{
							nachPunkten.add(temp.get(h));
						}
						temp.clear();
					}
					teamS2=nachPunkten.get(0);
					teamS3=nachPunkten.get(1);
			}
		}
		}
	}
	
	public void bestTeams2(boolean junior) {
		ArrayList<Team> t;
		if(junior) t = teams_junior;
		else t = teams_senior;
		System.out.println("Vorher");
		for(int i = 0; i < t.size(); i++) {
			System.out.println(t.get(i).getSchule().getName() + " mit Wins: " + t.get(i).getWinAmount() + " und Punkten: " + t.get(i).getHoechstPunkte());
		}
		sortTeams2(junior);
		System.out.println("Nachher");
		for(int i = 0; i < t.size(); i++) {
			System.out.println(t.get(i).getSchule().getName() + " mit Wins: " + t.get(i).getWinAmount() + " und Punkten: " + t.get(i).getHoechstPunkte());
		}
		try{
			if(junior) {
				teamJ1 = teams_junior.get(0);
				teamJ2 = teams_junior.get(1);
				teamJ3 = teams_junior.get(2);
			}
			else {
				teamS1 = teams_senior.get(0);
				teamS2 = teams_senior.get(1);
				teamS3 = teams_senior.get(2);
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
	}
	
	private void sortSpeaker()
	{
		speakerSortiert.clear();
		speakerSortiert.add(new Speaker());
		ArrayList<Speaker> speaker1 = new ArrayList<Speaker>();
		ArrayList<Speaker> temp = new ArrayList<Speaker>();
		for(int y=0;y<speaker.size();y++)
		{
			speaker1.add(speaker.get(y));
		}
		for(int i=0; i<speaker1.size();i++)
		{
			speaker1.get(i).hoechstPunkteErmitteln();
		}
		
		while(speakerSortiert.size()<speaker.size()+1)
		{
			temp.add(new Speaker());
			for(int j=0; j<speaker1.size();j++)
			{
				if(speaker1.get(j).getHoechstePunkte()>temp.get(0).getHoechstePunkte())
				{
					temp.clear();
					temp.add(speaker1.get(j));
					speaker1.remove(j);
				}
				else if(speaker1.get(j).getHoechstePunkte()==temp.get(0).getHoechstePunkte())
				{
					temp.add(speaker1.get(j));
					speaker1.remove(j);
				}
			}
			for(int h=0; h<temp.size();h++)
			{
				speakerSortiert.add(temp.get(h));
			}
			temp.clear();
			
		}
		if(speakerSortiert.get(0).getName().equals("")) speakerSortiert.remove(0);
	}
	
	public void sortSpeaker2() {
		System.out.println("Speakers:");
		for(int i = 0; i < speaker.size(); i++) { //works fine
			System.out.println(speaker.get(i).getHoechstePunkte() + ": " + speaker.get(i).getName());
		}
		Collections.sort(speaker, new PunkteComparator());
		System.out.println("Speakers highscores:");
		for(int i = 0; i < speaker.size(); i++) { //doesnt work
			System.out.println(speaker.get(i).getHoechstePunkte() + ": " + speaker.get(i).getName());
		}
	}
	
	public ArrayList<Speaker> getErsterSpeaker()
	{
		return ersterS;
	}
	
	public ArrayList<Speaker> getZweiterSpeaker()
	{
		return zweiterS;
	}
	
	public ArrayList<Speaker> getDritterSpeaker()
	{
		return dritterS;
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
	
	public ArrayList<Team> getJuniorTeams() {
		return teams_junior;
	}
	
	public ArrayList<Team> getSeniorTeams() {
		return teams_senior;
	}
	
	public ArrayList<Debate> getJuniorDebates() {
		return debatesJ;
	}
	
	public ArrayList<Debate> getSeniorDebates() {
		return debatesS;
	}
	public void setGui(Gui g) {
		this.gui = g;
	}
	
	//Methode um die Höhe der Panels abhängig von dem benötigten Platz für Schulnamen zu bestimmen
	public int getRecommendedPanelWidth(FontMetrics fm) {
		return getPanelWidth(getLongestSchoolName(fm), fm);
	}
	private String getLongestSchoolName(FontMetrics fm) {
		String rs = "";
		String loc = "";
		for(int i = 0; i < schulen.size(); i++) {
			loc = schulen.get(i).getName();
			Pattern pattern1 = Pattern.compile("\\s"); // "\\s" is the whitespace-regex
			Pattern pattern2 = Pattern.compile("-");
			Matcher matcher1 = pattern1.matcher(loc);
			Matcher matcher2 = pattern2.matcher(loc);
			if(!loc.equals("Keine Schule")) {
				List<String> s = new ArrayList<String>();
				if(matcher1.find()) {
					s = Arrays.asList(loc.split("\\s"));
					if(matcher2.find()) {
						s = getAllSplits("-", s);
					}
				}
				else if(matcher2.find()) {
					s = Arrays.asList(loc.split("-"));
					if(matcher1.find()) {
						s = getAllSplits("\\s", s);
					}
				}
				else {
					s.add(loc);
				}
				
				for(int j = 0; j < s.size(); j++) {
					if(fm.stringWidth(rs) < fm.stringWidth(s.get(j))) {
						rs = s.get(j);
					}
				}
			}
			else if(rs.length() < loc.length() && !loc.equals("Keine Schule")) {
				rs = loc;
			}
		}
		return rs;
	}
	public int getPanelWidth(String s, FontMetrics fm) {
		int rs = 0;
		List<String> strings = splitStringSpace(s);
		for(int i = 0; i < strings.size(); i++) {
			if(strings.size() <= 2) {
				if(rs < fm.stringWidth(strings.get(i))*2 + Math.toIntExact(Math.round(100 * gui.SCALE_CONSTANT))) {
					rs = fm.stringWidth(strings.get(i))*2 + Math.toIntExact(Math.round(100 * gui.SCALE_CONSTANT));
				}
			}
			else { //es gibt mehr als 1 Trennzeichen
				
			}
		}
		if(rs < 150 * gui.SCALE_CONSTANT) rs = Math.toIntExact(Math.round(150 * gui.SCALE_CONSTANT)); //Mindestgröße
		if(rs > 250 * gui.SCALE_CONSTANT) { //Maximalgröße
			//rs = -1; //Error-Code
			rs = Math.toIntExact(Math.round(250 * gui.SCALE_CONSTANT));
		}
		return rs;
	}
	
	public List<String> getAllSplits(String regex, List<String> s) {
		List<String> temp = new ArrayList<String>();
		for(int j = 0; j < s.size(); j++) {
			temp.addAll(Arrays.asList(s.get(j).split(regex)));
		}
		return temp;
	}
	
	public List<String> splitString(String s) {
		Pattern pattern1 = Pattern.compile("\\s");
		Matcher matcher1 = pattern1.matcher(s);
		Pattern pattern2 = Pattern.compile("-");
		Matcher matcher2 = pattern2.matcher(s);
		List<String> strings = new ArrayList<String>();
		if(matcher1.find()) {
			strings = Arrays.asList(s.split("\\s"));
			if(matcher2.find()) {
				strings = getAllSplits("-", strings);
			}
		}
		else if(matcher2.find()) {
			strings = Arrays.asList(s.split("-"));
			if(matcher1.find()) strings = getAllSplits("\\s", strings);
		}
		else strings.add(s);
		return strings;
	}
	
	public List<String> splitStringSpace(String s) {
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(s);
		List<String> strings = new ArrayList<String>();
		if(matcher.find()) {
			strings = Arrays.asList(s.split("\\s"));
		}
		else strings.add(s);
		return strings;
	}
	
	public String getRecommendedTwoLineString(List<String> strings, FontMetrics fm) {
		String rs = "";
		String s1 = "";
		String s2 = "";
		int minimalDifference = 999999999;
		int indexOfminDif = 0;
		for(int i = 0; i < strings.size() - 1; i++) {
			for(int j = 0; j <= i; j++) {
				s1 += strings.get(j);
			}
			for(int j = i + 1; j < strings.size(); j++) {
				s2 += strings.get(j);
			}
			int difference = Math.abs(fm.stringWidth(s1) - fm.stringWidth(s2));
			if(difference < minimalDifference) {
				minimalDifference = difference;
				indexOfminDif = i;
			}
			s1 = "";
			s2 = "";
		}
		for(int j = 0; j <= indexOfminDif; j++) {
			if(j != 0) s1 += "-" + strings.get(j);
			else s1 += strings.get(j);
		}
		for(int j = indexOfminDif + 1; j < strings.size(); j++) {
			if(j != indexOfminDif + 1) s2 += "-" + strings.get(j);
			else s2 += strings.get(j);
		}
		return s1 + " " + s2;
	}
	
	public Judge[][] getCalculatedJudges(int zeitzone) {
		switch(zeitzone) {
			case 1: {
				return reshape(judgesz1, dPTjunior + dPTsenior, 3);
			}
			case 2: {
				return reshape(judgesz2, dPTjunior + dPTsenior, 3);
			}
			case 3: {
				return reshape(judgesz3, dPTjunior + dPTsenior, 3);
			}
			default: return null;
		}
	}
	
	public Judge[][] reshape(Judge[] array, int rows, int columns) {
		Judge[][] rs = new Judge[rows][columns];
		if(array.length != rows*columns) { //passt nicht
			return null;
		}
		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				rs[j][i] = array[j + (i*rows)];
			}
		}
		return rs;
	}
	
	public void setMotion(String motion, int index) {
		motions.set(index, motion);
	}
	
	public String getMotion(int index) {
		return motions.get(index);
	}
	
	public Team[] getBestTeams(boolean junior) {
		Team[] teams = new Team[3];
		if(junior) {
			teams[0] = teamJ1;
			teams[1] = teamJ2;
			teams[2] = teamJ3;
		}
		else {
			teams[0] = teamS1;
			teams[1] = teamS2;
			teams[2] = teamS3; 
		}
		return teams;
	}
	
	public class PunkteComparator implements Comparator<Speaker> {
		@Override
		public int compare(Speaker speaker1, Speaker speaker2) {
			if(speaker1.getHoechstePunkte() > speaker2.getHoechstePunkte()) {
				return -1;
			}
			else if(speaker1.getHoechstePunkte() == speaker2.getHoechstePunkte()) {
				return 0;
			}
			else return 1;
		}
	}
	
	public class TeamComparator implements Comparator<Team> {
		@Override
		public int compare(Team team1, Team team2) {
			if(team1.getWinAmount() > team2.getWinAmount()) {
				return -1;
			}
			else if(team1.getWinAmount() < team2.getWinAmount()) {
				return 1;
			}
			else {
				if(team1.getHoechstPunkte() > team2.getHoechstPunkte()) {
					return -1;
				}
				else if(team1.getHoechstPunkte() < team2.getHoechstPunkte()) {
					return 1;
				}
				else return 0;
			}
		}
	}
}

