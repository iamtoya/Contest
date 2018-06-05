package contestx;

public class Debate {
	private Team teamPro;
	private Team teamCon;
	private Zeitzone zeit;
	private String raum;
	
	public void Debate(Team pro, Team con) {
		this.teamCon = con;
		this.teamPro = pro;
		zeit = new Zeitzone(0,0,1,0);
		raum = "";
	}
}
