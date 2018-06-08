
package contestx;

//neu Andy
public class Zeitzone {
	private int startHours;
	private int startMins;
	private int endHours;
	private int endMins;
	private int timezoneNumber; //um die wievielte Zeitzone handelt es sich
	
	public Zeitzone(int h, int m, int hh, int mm, int number)
	{
		startHours=h;
		startMins=m;
		endHours=hh;
		endMins=mm;
		timezoneNumber = number;
	}
	
	public void setStartHours(int h)
	{
		startHours=h;
	}
	
	public void setStartMins(int m)
	{
		startMins=m;
	}
	
	public void setEndHours(int h)
	{
		endHours=h;
	}
	
	public void setEndMins(int m)
	{
		endMins=m;
	}
	
	
	public int getStartHours()
	{
		return startHours;
	}
	
	public int getStartMins()
	{
		return startMins;
	}
	
	public int getEndHours()
	{
		return endHours;
	}
	
	public int getEndMins()
	{
		return endMins;
	}
	
	public int getNumber() {
		return timezoneNumber;
	}
}
