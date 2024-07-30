package vehicles;

import vehicles.Enums.UseFor;

public abstract class air_vehicles extends Vehicle{
	protected UseFor use_for;

	air_vehicles(String name, int passengers, int max_speed,UseFor use_for) {
		super(name, passengers, max_speed);
		this.use_for=use_for;
	}
	public void copyValues(air_vehicles other) {
		super.copyValues(other);
	    this.use_for=other.use_for;
	  }
	public String get_Type()
	{
		return "air_vehicles";
	}
	public String toString()
	{
		
		return  super.toString() + " use for : "+this.use_for+",";
	}
	public Boolean equals(air_vehicles x)
	{
		if(!super.equals(x))
			return false;
		if (x.use_for==this.use_for)
		{
			return true;
		}
		return false;
	} 

}
