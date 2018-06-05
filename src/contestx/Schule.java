package contestx;

public class Schule {
	private String name;
	private Boolean hasJuniorTeam;
	private Boolean hasSeniorTeam;
	private Team juniorTeam;
	private Team seniorTeam;
	
	public void Schule(String name, boolean junior, boolean senior, Team juniorTeam, Team seniorTeam) {
		this.name=name;
		this.hasJuniorTeam=junior;
		this.hasSeniorTeam=senior;
		this.juniorTeam=juniorTeam;
		this.seniorTeam=seniorTeam;
	}
}
