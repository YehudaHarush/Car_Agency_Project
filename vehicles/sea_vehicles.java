package vehicles;
public abstract class sea_vehicles extends Vehicle {
	protected Boolean wind_direction;
	protected String flag;

	public sea_vehicles(String name,int passengers, int max_speed,Boolean wind_direction,String flag) {
		super( name, passengers, max_speed);
		this.wind_direction=wind_direction;
		this.flag=String.valueOf(flag);
	}
	public void copyValues(sea_vehicles other) {
		super.copyValues(other);
	    this.wind_direction=other.wind_direction;
	    this.flag=other.flag;
	  }
	public String get_Type()
	{
		return "sea_vehicles";
	}
	public Boolean get_wind()
	{
		return this.wind_direction;
	}
	public String toString()
	{
		if(this.wind_direction)
		{
			return  super.toString() +" under "+this.flag +" flag , with the wind , ";
		}
		return super.toString() +" under "+this.flag +" flag,  against the direction of the wind , ";
	}
	public Boolean equals(sea_vehicles x)
	{
		if(!super.equals(x))
			return false;
		if (x.wind_direction==this.wind_direction)
		{
			if(x.flag.equals(this.flag))
			{
				return true;
			}
		}
		return false;
	} 
	public void change_flag(String new_flag)
	{
		this.flag=String.valueOf(new_flag);
	}
}
