package contestx;
import java.util.ArrayList;

//neu Andy

public class Speaker {
	private String name;
	private ArrayList<Integer> punkte;
	private Team team;
	
	public Speaker(String name, Team team) {
		this.name=name;
		this.team=team;
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
 