package vehicles;

public abstract class VehicleD extends Vehicle {
    protected Vehicle decoratedVehicle;

	public VehicleD(String name, int passengers, int max_speed, Vehicle decoratedVehicle) {
		 super(name, passengers, max_speed);
	        this.decoratedVehicle = decoratedVehicle;
	}

	@Override
	    public void copyValues(Vehicle other) {
	        decoratedVehicle.copyValues(other);
	    }

	    @Override
	    public void Distance(float distance) {
	        decoratedVehicle.Distance(distance);
	    }

	    @Override
	    public String get_Type() {
	        return decoratedVehicle.get_Type();
	    }

	    @Override
	    public String toString() {
	        return decoratedVehicle.toString();
	    }

	    @Override
	    public String get_name() {
	        return decoratedVehicle.get_name();
	    }

	    @Override
	    public int get_passengers() {
	        return decoratedVehicle.get_passengers();
	    }

	    @Override
	    public int get_maxspeed() {
	        return decoratedVehicle.get_maxspeed();
	    }

	    @Override
	    public Boolean equals(Vehicle x) {
	        return decoratedVehicle.equals(x);
	    }

	    @Override
	    public void reset_km() {
	        decoratedVehicle.reset_km();
	    }

	    @Override
	    public void setPhoto(String photo) {
	        decoratedVehicle.setPhoto(photo);
	    }

	    @Override
	    public String getPhoto() {
	        return decoratedVehicle.getPhoto();
	    }
	    public float get_distance()
	    {
	    	return decoratedVehicle.get_distance();
	    }
}

