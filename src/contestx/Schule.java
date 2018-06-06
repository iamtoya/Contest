package contestx;

public class Schule {
	public String name;
	public Boolean hasJuniorTeam;
	public Boolean hasSeniorTeam;
	public Team juniorTeam;
	public Team seniorTeam;
	
	public void Schule(String name, boolean junior, boolean senior, Team juniorTeam, Team seniorTeam) {
		this.name=name;
		this.hasJuniorTeam=junior;
		this.hasSeniorTeam=senior;
		this.juniorTeam=juniorTeam;
		this.seniorTeam=seniorTeam;
	}
	
	
	
	
}

