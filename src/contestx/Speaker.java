package contestx;
import java.util.ArrayList;

//neu Andy

public class Speaker {
	private String name;
	private ArrayList<Integer> punkte; //Aufgebaut wie folgt: index: 		0			 ;			  1			 ;			2			 ;			3		 ;			4		 ;			5
	private int hoechstPunkte;													//Bedeutung: Zeitzone1, 1. Speech;  Zeitzone 2, 1. Speech;  Zeitzone 3, 1. Speech;  Zeitzone 1, Reply;  Zeitzone 2, Reply;  Zeitzone 3, Reply
	private Team team;
	
	
	
	public Speaker(String name, Team team) {
		hoechstPunkte = 0;
		this.name=name;
		this.team=team;
		punkte = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++) { //verhindert durch setPunkteIn() ausgelöste IndexOutOfBoundsException
			punkte.add(0);
		}
	}
	
	public Speaker(String name) {
		this.name = name;
		hoechstPunkte = 0;
		punkte = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++) { //verhindert durch setPunkteIn() ausgelöste IndexOutOfBoundsException
			punkte.add(0);
		}
		
	}
	private void hoechstPunkteErmitteln(Speaker speaker)
	{
		for(int i=0; i<5; i++)
		{
			if(getPunkteIn(i)>hoechstPunkte)
			{
				hoechstPunkte=getPunkteIn(i);
			}
		}
	}
		
	public String getName() {
		return name;
	}
	
	public int getPunkteIn(int index) {
		return punkte.get(index);
	}
	
	public Team getTeam() {
		return team;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPunkteIn(int index, int punkte) {
		this.punkte.set(index,punkte);
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
		
	public int getHoechstePunkte()
	{
		return hoechstPunkte;
	}
}
 