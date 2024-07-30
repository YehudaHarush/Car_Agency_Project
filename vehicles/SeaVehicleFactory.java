package vehicles;


public class SeaVehicleFactory implements VehicleFactory{

	@Override
	public Vehicle create(int type, String... args) {
		Vehicle vehicle=null;
		switch (type) {
		case 0: //Frigate
			boolean wind = Boolean.parseBoolean(args[3]);
			vehicle=new Frigate(args[0], Integer.parseInt(args[1]),Integer.parseInt(args[2]),wind);
			vehicle=new Color_StatusD(vehicle, args[4]);
			break;
		case 1://cruise ship
			vehicle=new Cruise_ship(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]));
			vehicle=new Color_StatusD(vehicle, args[6]);
			break;
		case 2://hybrid plane
			vehicle = new HybridPlane(args[0],Integer.parseInt(args[1]),  Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]),Integer.parseInt(args[6]));
			vehicle=new Color_StatusD(vehicle, args[7]);
			break;
		}
		return vehicle;
	}
	}


