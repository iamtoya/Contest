package contestx;
import java.util.ArrayList;

//neu Andy

public class Speaker {
	private String name;
	private ArrayList<Integer> punkte; //Aufgebaut wie folgt: index: 		0			 ;			  1			 ;			2			 ;			3		 ;			4		 ;			5
														//Bedeutung: Zeitzone1, 1. Speech;  Zeitzone 2, 1. Speech;  Zeitzone 3, 1. Speech;  Zeitzone 1, Reply;  Zeitzone 2, Reply;  Zeitzone 3, Reply
	private Team team;
	
	public Speaker(String name, Team team) {
		this.name=name;
		this.team=team;
		punkte = new ArrayList<Integer>();
	}
	
		
	public String getName() {
		return name;
	}
	
	public int getPunkteIn(int index) {
		if(index<3) {
			return this.getPunkteIn(index);
		}
		else {
			return(-1);
		}
	}
	
	public Team getTeam() {
		return team;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPunkteIn(int index, int punkte) {
		if(index < 3) {
			this.punkte.set(index,punkte);
		}
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
		
}
 