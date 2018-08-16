package contestx;

import java.io.Serializable;

//neu Andy
public class Schule implements Serializable {
	private static final long serialVersionUID = -9093385497921031366L;
	private String name;
	private Boolean hasJuniorTeam;
	private Boolean hasSeniorTeam;
	private Team juniorTeam;
	private Team seniorTeam;
	
	public Schule(String name) {
		this.name = name;
	}
	
	public Schule(String name, boolean junior, boolean senior) {
		this.name = name;
		this.hasJuniorTeam = junior;
		this.hasSeniorTeam = senior;
		if(junior) juniorTeam = new Team(this, true);
		if(senior) seniorTeam = new Team(this, false);
	}
	
	public Schule(String name, boolean junior, boolean senior, Team juniorTeam, Team seniorTeam) {
		this.name=name;
		this.hasJuniorTeam=junior;
		this.hasSeniorTeam=senior;
		this.juniorTeam=juniorTeam;
		this.seniorTeam=seniorTeam;
	}
	
	public Schule(boolean keineSchule) {
		if(keineSchule) {
			name="Keine Schule";
			hasJuniorTeam=false;
			hasSeniorTeam=false;
			juniorTeam = new Team(this, true);
			seniorTeam = new Team(this, false);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Boolean getHasJuniorTeam() {
		return hasJuniorTeam;
	}
	
	public Boolean getHasSeniorTeam() {
		return hasSeniorTeam;
	}
	
	public Team getJuniorTeam() {
		return juniorTeam;
	}
	
	public Team getSeniorTeam() {
		return seniorTeam;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setHasJuniorTeam(Boolean hasJuniorTeam) {
		this.hasJuniorTeam = hasJuniorTeam;
	}
	
	public void setHasSeniorTeam(Boolean hasSeniorTeam) {
		this.hasSeniorTeam = hasSeniorTeam;
	}
	
	public void setJuniorTeam(Team juniorTeam) {
		this.juniorTeam = juniorTeam;
	}
	
	public void setSeniorTeam(Team seniorTeam) {
		this.seniorTeam = seniorTeam;
	}
	
}

