package vehicles;
import vehicles.Enums.RoadType;
import vehicles.Enums.UseFor;

public class Amphibious extends sea_vehicles implements Land_vehicle_inter , Motorized 
{
	private int wheels_num;
	private RoadType road_type;
	private int Average_fuel;
	private int Engine_life;

	public Amphibious(String name, int passengers, int max_speed, String flag, int wheels_num,int Average_fuel,int Engine_life) {
		super(name, passengers, max_speed, true, flag);
		this.wheels_num=wheels_num;
		this.road_type=RoadType.PAVED;
		this.Average_fuel=Average_fuel;
		this.Engine_life=Engine_life;
	}

	public void copyValues(Amphibious other) {
		super.copyValues(other);
	    this.wheels_num=other.wheels_num;
	    this.road_type=other.road_type;
	    this.Average_fuel=other.Average_fuel;
	    this.Engine_life=other.Engine_life;
	}

	
	public Boolean equals(Amphibious x) {
		if(!super.equals(x))
			return false;
		if (x.wheels_num==this.wheels_num&&x.Average_fuel==this.Average_fuel&&x.Engine_life==this.Engine_life&&x.road_type==this.road_type)
		{
			return true;
		}
		return false;
	}

	public int Average_fuel() {
		return this.Average_fuel;
	}

	public int Engine_life() {
		return this.Engine_life;
	}
	
	public String toString()
	{

		return "Amphibious: "+super.toString() + " average fuel is "+ this.Average_fuel+" engine life is "+this.Engine_life+" years , road type is "+this.road_type
				+ " and wheels num is "+this.wheels_num+"\n";
	}
}
