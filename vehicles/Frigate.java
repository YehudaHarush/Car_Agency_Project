package vehicles;
public class Frigate extends sea_vehicles implements Motorized {
	private int Average_fuel;
	private int Engine_life;
	public Frigate(String name, int passengers, int max_speed, Boolean wind_direction) {
		super(name, passengers, max_speed, wind_direction,"israel");
		this.Average_fuel=500;
		this.Engine_life=4;	
		this.flag=String.valueOf("israel");
	}
	public void copyValues(Frigate other) {
		super.copyValues(other);
	    this.Average_fuel=other.Average_fuel;
	    this.Engine_life=other.Engine_life;
	  }
	public int Average_fuel() {
		return this.Average_fuel;
	}
	
	public int Engine_life() {
		return this.Engine_life;
	}

	public String get_Type()
	{
		return "Frigate";
	}
	public String toString()
	{
		return "Frigate: "+super.toString()+"average fuel is "+this.Average_fuel+" and engine life is "+ this.Engine_life+" years\n";
	}
	
	public Boolean equals(Frigate x)
	{
		if(!super.equals(x))
			return false;
		if(x.Average_fuel==this.Average_fuel&&x.Engine_life==this.Engine_life)
		{
			return true;
		}
		return false;
	}
}
