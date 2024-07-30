package vehicles;

import vehicles.Enums.PowerSource;

public class AirVehicleFactory implements VehicleFactory{

	@Override
	public Vehicle create(int type, String... args) {
		
		Vehicle vehicle=null;
		switch (type) {
		case 0: //spy glider
			 PowerSource p = PowerSource.valueOf(args[0].toUpperCase());
			vehicle=new Spy_glider(p);
			vehicle=new Color_StatusD(vehicle, args[1]);
			break;
		case 1://glider game
			vehicle=new Glider_game();
			vehicle=new Color_StatusD(vehicle, args[0]);
			break;
		}
		return vehicle;
	}
}
