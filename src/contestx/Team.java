package contestx;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

//neu Andy

public class Team implements Serializable {
	private static final long serialVersionUID = -4720434346042715094L;
	private Schule schule;
	private ArrayList<Speaker> speaker;
	private ArrayList<Speaker[]> speakerInZeitzone;
	private boolean isJunior;
	private ArrayList<Integer> punkte; //Gesamtpunkte pro Zeitzone
	private int hoechstPunkte;
	private ArrayList<Boolean> wins;
	

	public Team(Schule schule, boolean isJunior) {
		this.schule=schule;
		this.isJunior=isJunior;
		speaker = new ArrayList<Speaker>();
		//speaker.add(new Speaker()); //dummy
		punkte = new ArrayList<Integer>();
		for(int i = 0; i < 3; i++) {
			punkte.add(0);
		}
		this.hoechstPunkte=0;
		wins = new ArrayList<Boolean>();
		for(int i = 0; i < 3; i++) { //3 = max Anzahl Siege (Spiele)
			wins.add(false); //wird mit false gefüllt und später bei Siegen mit true belegt
		}
		speakerInZeitzone = new ArrayList<Speaker[]>();
		for(int i = 0; i < 3; i++) {
			Speaker[] s = new Speaker[4];
			speakerInZeitzone.add(s);
		}
	}
	
	public Team()
	{
		this.schule=new Schule("");
		speaker.add(new Speaker());
		this.isJunior=true;
		this.hoechstPunkte=0;
		punkte = new ArrayList<Integer>();
		wins = new ArrayList<Boolean>();
	}

	
	public Team(Schule schule, ArrayList<Speaker> speaker, Boolean isJunior) {
		this.schule=schule;
		this.speaker=speaker;
		this.isJunior=isJunior;
		this.hoechstPunkte=0;
		wins = new ArrayList<Boolean>();
		for(int i = 0; i < 3; i++) { //3 = max Anzahl Siege (Spiele)
			wins.add(false); //wird mit false gefüllt und später bei Siegen mit true belegt
		}
		punkte = new ArrayList<Integer>();
		speakerInZeitzone = new ArrayList<Speaker[]>();
		for(int i = 0; i < 3; i++) {
			Speaker[] s = new Speaker[4];
			speakerInZeitzone.add(s);
		}
	}
	
	public void teampunkte(int timeperiod)
	{
		int z=0;
		for(int i=0; i<speaker.size();i++)
		{
			z=z+this.getSpeaker(i).getPunkteIn(timeperiod);
			z=z+this.getSpeaker(i).getPunkteIn(timeperiod+3);
		}
		this.punkte.set(timeperiod, z);
		
	}
	//
	public int getHoechstPunkte()
	{
		for(int i = 0; i < punkte.size(); i++) {
			if(hoechstPunkte < punkte.get(i)) hoechstPunkte = punkte.get(i);
		}
		return hoechstPunkte;
	}
	
	public Speaker getBestenSpeaker()
	{
		Speaker x = new Speaker();
		for(int i=0; i<speaker.size();i++)
		{
			speaker.get(i).hoechstPunkteErmitteln();
			if(speaker.get(i).getHoechstePunkte()>x.getHoechstePunkte())
			{
				x=speaker.get(i);
			}
		}
		return x;
	}
	
	public void setHoechstPunkte()
	{
		hoechstPunkte=punkte.get(1);
		if(punkte.get(2)>hoechstPunkte)
		{
			hoechstPunkte=punkte.get(2);
		}
		else if(punkte.get(3)>hoechstPunkte)
		{
			hoechstPunkte=punkte.get(3);
		}
	}
	
	public Schule getSchule() {
		return schule;
	}
	
	public int getPunkteAt(int index)
	{
		return punkte.get(index);
	}
	
	public boolean getWinAt(int index)
	{
		return wins.get(index);
	}
	
	public int getWinAmount() //Berechnet die Zahl der Siege
	{
		int x=0;
		for(int i=0; i<3; i++)
		{
			if(getWinAt(i))
			{
				x=x+1;
			}
		}
		return x;
	}
	
	public void setWin(int index, boolean win)
	{
		wins.set(index, win);
	}
	
	public void setWinner(int index)
	{
		wins.set(index, true);
	}
	
	public ArrayList<Speaker> getAllSpeaker() {
		return speaker;
	}
	
	public void setAllSpeaker(ArrayList<Speaker> speaker_list) {
		speaker = speaker_list;
	}
	
	public Speaker getSpeaker(int index) {
		return speaker.get(index);
	}
	
	public Speaker[] getSpeakersAtTime(int index) {
		return speakerInZeitzone.get(index);
	}
	
	public void setSpeakersAtTime(int index, Speaker[] speaker) {
		speakerInZeitzone.set(index, speaker);
	}
	public boolean getIsJunior() {
		return isJunior;
	}
	
	public ArrayList<Integer> getPunkte() {
		return punkte;
	}
	
	public void setPoints(Speaker[] speakers, int[] punkte, Zeitzone zeitzone) {
		int teampunkteGesamt = 0;					// Variable die Punkte eines Teamsspeichert
		//Teambewertung tb = new Teambewertung();		// neues Teambewertungsobjekt
		setSpeakersAtTime(zeitzone.getNumber() - 1, speakers);
		for(int i = 0; i < speakers.length; i++) { //i = 1.,2.,3.,Reply-Speaker
			int j = 0;
			while(!speaker.get(j).equals(speakers[i]) && j < speaker.size()) { //richtiger Speaker in der speaker-liste wird gefunden
				j++;
			}
			if(i != 3) speaker.get(j).setPunkteIn(zeitzone.getNumber()-1, punkte[i]); //-1, da speaker nullbasiert, zeitzonenNummer nicht
			else speaker.get(j).setPunkteIn(zeitzone.getNumber()-1+3, punkte[i]); //bei korrektem Reply-Index hinzufügen
		}
		
		for(int k = 0; k < punkte.length; k++) {				// geht Punktearray durch
			teampunkteGesamt = punkte[k] + teampunkteGesamt;	// addiert die Punkte der Spieler um Teampunktzahl zu erhalten
		}
		this.punkte.set(zeitzone.getNumber() - 1, teampunkteGesamt);
	}
	
	public void setPoints(Speaker[] speakers, int[] punkte, Zeitzone zeitzone, boolean win) { //Zeitzonen: 1; 2; 3;
		setPoints(speakers, punkte, zeitzone);
		setWin(zeitzone.getNumber() - 1, win);
	}
	
	
	public void setSchule(Schule schule) {
		this.schule = schule;
	}
	
	public void setSpeakerAt(int index, Speaker speaker) {
		this.speaker.set(index, speaker);
	}
	
	public void addSpeaker(Speaker speaker) {
		this.speaker.add(speaker);
	}
	
	public void setIsJunior(Boolean isJunior) {
		this.isJunior = isJunior;
	}
	
	public int[] getSpeakerPunkteAt(int zeitzone) {
		int index = zeitzone - 1;
		int[] punkte = new int[4];
		Speaker[] speakers = getSpeakersAtTime(index);
		for(int i = 0; i < 4; i++) {
			if(i == 3) { //reply speaker has another index
				index += 3;
			}
			if(speakers[i] != null) punkte[i] = speakers[i].getPunkteIn(index);
			else punkte[i] = 0;
		}
		return punkte;
	}
	
	public Color getColor(int zeitzone) {
		Speaker[] s = getSpeakersAtTime(zeitzone - 1);
		int[] p = getSpeakerPunkteAt(zeitzone);
		for(int i = 0; i < s.length; i++) {
			if(s[i] == null) { //speaker noch nicht korrekt gesetzt
				return new Color(255, 243, 153); //gelb
			}
			if(p[i] == 0) { //WARNUNG: VERGEBENE PUNKTE DÜRFEN NICHT 0 SEIN!!
				if(getIsJunior()) return new Color(153, 214, 255); //blau
				else return new Color(255, 153, 153); //rot
			}
		}
		return new Color(153, 255, 161); //grün
	}
}
