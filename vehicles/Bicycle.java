package vehicles;

import vehicles.Enums.RoadType;

public abstract class Bicycle  extends land_vehicles {

	public Bicycle(String name, int passengers, int max_speed, int wheels_num, RoadType road_type) {
		super(name, passengers, max_speed, 2 , road_type);
	}
	public String toString()
	{

		return "Bicycle: "+super.toString();
	}
	
		public Boolean equals(Regular_bicycle x)
		{
			if(!super.equals(x))
				return false;
			return true;
		}

}
