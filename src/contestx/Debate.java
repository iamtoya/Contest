package contestx;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


//neu Andy
public class Debate implements Serializable {
	private static final long serialVersionUID = -7586576858743587740L;
	private Team teamPro;
	private Team teamCon;
	private Zeitzone zeit;
	private String raum;
	private JFrame subFrame;
	
	
	public Debate(Team pro, Team con) {
		this.teamCon = con;
		this.teamPro = pro;
		//zeit = new Zeitzone(0,0,1,0);
		raum = "";
		subFrame = new JFrame();
	}
	
	
	public void teamWin(int timeperiod)
	{
		if(teamPro.getPunkteAt(timeperiod)>teamCon.getPunkteAt(timeperiod))
		{
			teamPro.setWinner(timeperiod);
		}
		else if(teamPro.getPunkteAt(timeperiod)<teamCon.getPunkteAt(timeperiod))
		{
			teamCon.setWinner(timeperiod);
		}
		else if(teamPro.getPunkteAt(timeperiod)==teamCon.getPunkteAt(timeperiod))
		{
			JOptionPane.showMessageDialog(subFrame, "Team pro and team con have the same amount of points", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//
	
	
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
