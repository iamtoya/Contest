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
