package vehicles;

import vehicles.Enums.EnergyScore;
import vehicles.Enums.PowerSource;
import vehicles.Enums.UseFor;

public class Spy_glider extends air_vehicles implements not_motorized{
	

	private PowerSource power_source;
	private EnergyScore energy_score;

	public Spy_glider(PowerSource power_source) {
		super("privileged", 1, 50,UseFor.MILITARY);
		this.power_source=power_source;
		this.energy_score=EnergyScore.C;
	}
	public void copyValues(Spy_glider other) {
		super.copyValues(other);
	    this.power_source=other.power_source;
	    this.energy_score=other.energy_score;
	  }

	public String get_Type()
	{
		return "Spy glider";
	}
	public PowerSource power_source() {
		return this.power_source;
	}

	
	public EnergyScore energy_score() {
		return this.energy_score;
	}
	
	public String toString()
	{

		return "Spy glider: "+super.toString() + " the power source is: "+this.power_source+" and energy score is: "+this.energy_score+"\n";
	}
	
	public Boolean equals(Spy_glider x)
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
