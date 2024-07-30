package vehicles;

import vehicles.Enums.RoadType;

public abstract class land_vehicles extends Vehicle {
	protected int wheels_num;
	protected RoadType road_type;

	public land_vehicles( String name,  int passengers, int max_speed,int wheels_num,RoadType road_type) {
		super(name, passengers, max_speed);
		this.wheels_num=wheels_num;
		this.road_type=road_type;
	}
	public void copyValues(land_vehicles other) {
		super.copyValues(other);
	    this.wheels_num=other.wheels_num;
	    this.road_type=other.road_type;
	  }
	public String toString()
	{
		
		return  super.toString() + " number of wheels: "+this.wheels_num+", drive at "+ this.road_type + " road ,";
	}
	public String get_Type()
	{
		return "land_vehicles";
	}
	public Boolean equals(land_vehicles x)
	{
		if(!super.equals(x))
			return false;
		if (x.wheels_num==this.wheels_num)
		{
			if(x.road_type==this.road_type)
			{
				return true;
			}
		}
		return false;
	} 

}
