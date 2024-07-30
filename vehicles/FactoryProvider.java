package vehicles;

public class FactoryProvider {
	public static VehicleFactory getFactory(int type)
	{
		VehicleFactory factory=null;
		switch (type) {
		case 0:
			factory= new LandVehicleFactory();
			break;
		case 1:
			factory=new AirVehicleFactory();
			break;
		case 2:
			factory=new SeaVehicleFactory();
			break;
		}	
		return factory;
	}
}
