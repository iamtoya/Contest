package contestx;
import java.util.ArrayList;
//neu Andy

public class Judge {
	private String name;
	private Schule schule;
	private boolean erfahren;
	private ArrayList<Boolean> kannZuZeit;
	
	public Judge(String name, boolean erfahren) {
		this.name = name;
		this.erfahren = erfahren;
	}
	
	public Judge(String name, Schule schule, boolean erfahren) {
		this.name=name;
		this.schule=schule;
		this.erfahren=erfahren;
	}
	
	
	
	
	public String getName() {
		return name;
	}
	
	public Schule getSchule() {
		return schule;
	}
	
	public Boolean getErfahren() {
		return erfahren;
	}
	
	public Boolean getKannZuZZ1() {
		return kannZuZeit.get(1);
	}
	
	public Boolean getKannZuZZ2() {
		return kannZuZeit.get(2);
	}
	
	public Boolean getKannZuZZ3() {
		return kannZuZeit.get(3);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSchule(Schule schule) {
		this.schule = schule;
	}
	
	public void setErfahren(Boolean erfahren) {
		this.erfahren = erfahren;
	}
	
	public void setKannZuZZ1(Boolean kann) {
		this.kannZuZeit.set(1, kann);
	}
	
	public void setKannZuZZ2(Boolean kann) {
		this.kannZuZeit.set(2, kann);
	}
	
	public void setKannZuZZ3(Boolean kann) {
		this.kannZuZeit.set(3, kann);
	}
	
	
}
