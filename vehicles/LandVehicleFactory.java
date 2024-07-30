package vehicles;

import vehicles.Enums.RoadType;

public class LandVehicleFactory implements VehicleFactory {

	@Override
	public Vehicle create(int type, String... args) {
		Vehicle vehicle=null;
		
		switch (type) {
		case 0: //Jeep
			vehicle=new Jeep(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			vehicle=new Color_StatusD(vehicle, args[4]);
			break;
		case 1://Regular_bicycle
			RoadType roadType = RoadType.valueOf(args[3].toUpperCase());
			vehicle=new Regular_bicycle(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), roadType);
			vehicle=new Color_StatusD(vehicle, args[4]);
			break;
		case 2://amphibious
			vehicle = new Amphibious(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
			vehicle=new Color_StatusD(vehicle, args[7]);
			break;
		case 3://Electric_bicycle
			RoadType roadType1 = RoadType.valueOf(args[3].toUpperCase());
			vehicle= new ElectricBicycle(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), roadType1, Integer.parseInt(args[4]));
			vehicle=new Color_StatusD(vehicle, args[5]);
		}
		return vehicle;
	}

}
