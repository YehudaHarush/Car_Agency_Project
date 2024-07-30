package vehicles;

public interface VehicleFactory {
	Vehicle create(int type,String... args);
}
