package vehicles;




import vehicles.Enums.LicenseType;
import vehicles.Enums.RoadType;

public class Jeep extends land_vehicles implements Motorized , commercial_use {
	private int Average_fuel;
	private int Engine_life;
	public Jeep(String name,int Average_fuel, int max_speed,int Engine_life) {
		
		super(name,5,max_speed, 4,RoadType.DIRT);
		this.name=String.valueOf(name);
		this.Average_fuel=Average_fuel;
		this.Engine_life=Engine_life;
	}


	public int Average_fuel() {
		return this.Average_fuel;
	}

 
	public int Engine_life() {
		return this.Engine_life;

	}
	public String get_Type()
	{
		return "Jeep";
	}

	public LicenseType license_type() {
		return LicenseType.MINI;
	}
	public String toString()
	{

		return "Jeep: "+super.toString() + " average fuel is "+ this.Average_fuel+" engine life is "+this.Engine_life+" years and license type is "+this.license_type()+"\n";
	}
	
	public Boolean equals(Jeep x)
	{
		if(!super.equals(x))
			return false;
		if(x.Average_fuel==this.Average_fuel&&x.Engine_life==this.Engine_life&&this.license_type()==x.license_type())
		{
			return true;
		}
		return false;
	}

}
