package contestx;
import java.util.ArrayList;


public class Speaker {
	private String name;
	private ArrayList<Integer> Punkte;
	private Team team;
	
	public void Speaker(String name, Team team) {
		this.name=name;
		this.team=team;
	}
	
	public Team getTeam() {
		return team;
	}

}
 