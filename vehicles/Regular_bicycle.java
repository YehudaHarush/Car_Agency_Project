package vehicles;

import vehicles.Enums.EnergyScore;
import vehicles.Enums.PowerSource;
import vehicles.Enums.RoadType;

public class Regular_bicycle extends Bicycle implements not_motorized {
	
	private PowerSource power_source;
	private EnergyScore energy_score;

	public Regular_bicycle(String name, int passengers, int max_speed,RoadType road_type) {
		super(name, passengers, max_speed, 2, road_type);
		this.energy_score=EnergyScore.A;
		this.power_source=PowerSource.MANUAL;
		
	}


	public PowerSource power_source() {
		return this.power_source;
	}

	
	public EnergyScore energy_score() {
		return this.energy_score;
	}
	
	public String toString()
	{

		return "Regular "+super.toString() + " the power source is: "+this.power_source+" and energy score is: "+this.energy_score+"\n";
	}
	
		public Boolean equals(Regular_bicycle x)
		{
			if(!super.equals(x))
				return false;
			if(x.power_source==this.power_source&&x.energy_score==this.energy_score)
			{
				return true;
			}
			return false;
		}


}
