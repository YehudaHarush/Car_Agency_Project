package vehicles;

import vehicles.Enums.LicenseType;

public class Cruise_ship extends sea_vehicles implements Motorized , commercial_use {
	
	private LicenseType licenseType;
	private int Average_fuel;
	private int Engine_life;

	public Cruise_ship(String name, int passengers, int max_speed,  String flag,int Average_fuel,int Engine_life) {
		super(name, passengers, max_speed, true, flag);
		this.licenseType=LicenseType.UNLIMIT;
		this.Average_fuel=Average_fuel;
		this.Engine_life=Engine_life;
	}

	public LicenseType license_type() {
		return this.licenseType;
	}

	public int Average_fuel() {
		return this.Average_fuel;
	}

	public int Engine_life() {
		return this.Engine_life;
	}
	public String toString()
	{

		return "Crusie ship: "+super.toString() + " average fuel is "+ this.Average_fuel+" engine life is "+this.Engine_life+" years and license type is "+this.license_type()+"\n";
	}
	
	public Boolean equals(Cruise_ship x)
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
