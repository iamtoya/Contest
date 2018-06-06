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
	
	
	
	
	
	
	
	
	public Team getTeamPro() {
		return teamPro;
	}
	
	public Team getTeamCon() {
		return teamCon;
	}
	
	public Zeitzone getZeit() {
		return zeit;
	}
	
	public String getRaum() {
		return raum;
	}
	
	public void setTeamPro(Team teamPro) {
		this.teamPro = teamPro;
	}
	
	public void setTeamCon(Team teamCon) {
		this.teamPro = teamCon;
	}
	
	public void setZeit(Zeitzone zeit) {
		this.zeit = zeit;
	}
	
	public void setRaum(String raum) {
		this.raum = raum;
	}
	
	
}
