package contestx;
import java.util.ArrayList;

public class Judge {
	private String name;
	private Schule schule;
	private boolean erfahren;
	private ArrayList<Boolean> kannZuZeit;
	
	public void Judge(String name, Schule schule, boolean erfahren) {
		this.name=name;
		this.schule=schule;
		this.erfahren=erfahren;
	}
	
}
