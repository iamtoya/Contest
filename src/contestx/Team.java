package contestx;
import java.util.ArrayList;


public class Team {
	private Schule schule;
	private ArrayList<Speaker> speaker;
	private Boolean isJunior;
	private ArrayList<Teambewertung> bewertungen;
	
	public Team(Schule schule, Boolean isJunior) {
		this.schule=schule;
		this.isJunior=isJunior;
	}
	
	public Team(Schule schule, ArrayList<Speaker> speaker, Boolean isJunior) {
		this.schule=schule;
		this.speaker=speaker;
		this.isJunior=isJunior;
	}


	
}
