package vehicles;

import vehicles.Enums.RoadType;

public class ElectricBicycle extends Bicycle implements Motorized {
	
	private int Average_fuel;
	private int Engine_life;
	public ElectricBicycle(String name, int passengers, int max_speed, RoadType road_type, int engine_life) {
		super(name, passengers, max_speed, 2, road_type);
		this.Average_fuel=20;
		this.Engine_life=engine_life;
	}

	@Override
	public int Average_fuel() {
		return this.Average_fuel;
	}

	@Override
	public int Engine_life() {
		return this.Engine_life;
	}
	public String toString()
	{

		return "Electric "+super.toString() + " the Average fuel is: "+this.Average_fuel+" and engine life is: "+this.Engine_life+"\n";
	}
	
		public Boolean equals(ElectricBicycle x)
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
