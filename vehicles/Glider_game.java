package vehicles;

import vehicles.Enums.EnergyScore;
import vehicles.Enums.PowerSource;
import vehicles.Enums.UseFor;
public class Glider_game extends air_vehicles implements not_motorized{
	private PowerSource power_source;
	private EnergyScore energy_score;
	public Glider_game() {
		super("Toy", 0, 10,UseFor.CIVIL);
		this.energy_score=EnergyScore.A;
		this.power_source=PowerSource.MANUAL;
	}
	public void copyValues(Glider_game other) {
		super.copyValues(other);
	  	  }

	public PowerSource power_source() {
		return this.power_source;
	}

	public EnergyScore energy_score() {
		return this.energy_score;
	}
	public String get_Type()
	{
		return "Glider game";
	}
	public String toString()
	{

		return "Glider game: "+super.toString() + " the power source is: "+this.power_source+" and energy score is: "+this.energy_score+"\n";
	}
	
	public Boolean equals(Glider_game x)
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
