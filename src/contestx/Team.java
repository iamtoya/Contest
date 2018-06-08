package contestx;
import java.util.ArrayList;

//neu Andy

public class Team {
	private Schule schule;
	private ArrayList<Speaker> speaker;
	private Boolean isJunior;
	private ArrayList<Teambewertung> bewertungen;
	

	public Team(Schule schule, boolean isJunior) {
		this.schule=schule;
		this.isJunior=isJunior;
		speaker = new ArrayList<Speaker>();
		for(int i = 0; i < 9; i++) { //9 = max Anzahl Speaker pro Team
			speaker.add(new Speaker("", this)); //verhindert durch setSpeakerAt() ausgelöste IndexOutOfBoundsException
		}
	}

	
	public Team(Schule schule, ArrayList<Speaker> speaker, Boolean isJunior) {
		this.schule=schule;
		this.speaker=speaker;
		this.isJunior=isJunior;
	}
	
	
	
	public Schule getSchule() {
		return schule;
	}
	
	public ArrayList<Speaker> getAllSpeaker() {
		return speaker;
	}
	
	public Boolean getIsJunior() {
		return isJunior;
	}
	
	public ArrayList<Teambewertung> getTeambewertungen() {
		return bewertungen;
	}
	
	public void setPoints(Speaker[] speakers, int[] punkte, Zeitzone zeitzone) {
		for(int i = 0; i < speakers.length; i++) { //i = 1.,2.,3.,Reply-Speaker
			int j = 0;
			while(!speaker.get(j).equals(speakers[i])) {
				j++;
			}
			if(i != 3) speaker.get(j).setPunkteIn(zeitzone.getNumber()-1, punkte[i]); //-1, da speaker nullbasiert, zeitzonenNummer nicht
			else speaker.get(j).setPunkteIn(zeitzone.getNumber()-1+3, punkte[i]);
		}
	}
	
	public void setSchule(Schule schule) {
		this.schule = schule;
	}
	
	public void setSpeakerAt(int index, Speaker speaker) {
		this.speaker.set(index, speaker);
	}
	
	public void setIsJunior(Boolean isJunior) {
		this.isJunior = isJunior;
	}
	
	public void setTeambewertungAt(int index, Teambewertung teambewertung) {
		if(index<3) {
			this.bewertungen.set(index, teambewertung);
		}
	}
	

	
}
