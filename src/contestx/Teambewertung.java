package contestx;
import java.util.ArrayList;

//neu Andy
public class Teambewertung {
	private int gesamtpunkte;
	private Boolean pro;
	private Boolean win;
	private Speaker[] speakerZuDiesemZeitpunkt;
	private Zeitzone zeitzone;
	
	public Teambewertung() {
		gesamtpunkte = 0;
		pro = true;
		win = false;
		zeitzone = new Zeitzone(0, 0, 0, 0, 0);
		speakerZuDiesemZeitpunkt = new Speaker[4];
	}
	
	public int getGesamtpunkte() {
		return gesamtpunkte;
	}
	
	public Boolean getIsPro() {
		return pro;
	}
	
	public Boolean getHaveWon() {
		return win;
	}
	
	public Speaker getSpeakerAt(int index) {
		if(index<4) {
			return speakerZuDiesemZeitpunkt[index];
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
	
	public void setHaveWon(Boolean haveWon) {
		this.win = haveWon;
	}
	
	public void setSpeakerAt(int index, Speaker speaker) {
		if(index < 4) {
			speakerZuDiesemZeitpunkt[index] = speaker;
		}
	}
	
	public void setZeitzone(Zeitzone zeitzone ) {
		this.zeitzone = zeitzone;
	}
	
}
