package contestx;
import java.util.ArrayList;

//neu Andy
public class Teambewertung {
	private int gesamtpunkte;
	private Boolean pro;
	private Boolean win;
	private ArrayList<Speaker> speakerZuDiesemZeitpunkt;
	
	
	
	public int getGesamtpunkte() {
		return gesamtpunkte;
	}
	
	public Boolean getIsPro() {
		return pro;
	}
	
	public Boolean getHaveWon() {
		return win;
	}
	
	public ArrayList<Speaker> getAllSpeakers() {
		return speakerZuDiesemZeitpunkt;
	}
	
	public Speaker getSpeakerAt(int index) {
		if(index<4) {
			return this.speakerZuDiesemZeitpunkt.get(index);
		}
		else {
			return null;
		}
		
	}
	
	public void setGesamtpunkte(int punkte) {
		this.gesamtpunkte = punkte;
	}
	
	public void setIsPro(Boolean isPro) {
		this.pro = isPro;
	}
	
	public void setHaveWOn(Boolean haveWon) {
		this.win = haveWon;
	}
	
	public void setSpeakerAt(int index, Speaker speaker) {
		if(index < 4) {
			this.speakerZuDiesemZeitpunkt.set(index, speaker);
		}
	}
	
	
	
	
}
