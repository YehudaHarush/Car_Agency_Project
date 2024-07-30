package vehicles;


import vehicles.Enums.UseFor;

public class HybridPlane extends Amphibious implements AirVehicleInter 
{
	
	protected UseFor use_for;

	public HybridPlane(String name, int passengers, int max_speed, String flag, int wheels_num, int Average_fuel,
			int Engine_life) {
		super(name, passengers, max_speed, flag, wheels_num, Average_fuel, Engine_life);
		this.use_for=UseFor.MILITARY;
		
	}



	@Override
	public Boolean equals(HybridPlane x) {
		if(!super.equals(x))
			return false;
		if (x.use_for==this.use_for)
		{
			return true;
		}
		return false;
	}
	public String toString() {
        String parentString = super.toString();
        return "Hybrid plane: " + parentString.substring(parentString.indexOf(":") + 1) + ", use for: " + use_for;
    }
}

	

