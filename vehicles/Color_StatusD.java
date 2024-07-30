package vehicles;

public class Color_StatusD extends VehicleD implements DecoratorInter{
	private String status;
	private String color;
	
	public Color_StatusD(Vehicle decoratedVehicle, String color) {
        super(decoratedVehicle.get_name(), decoratedVehicle.get_passengers(), decoratedVehicle.get_maxspeed(), decoratedVehicle);
        this.color = color;
        this.status = "In Stock";
    }
	
	public String toString()
	{
		return super.toString()+" color: "+this.color + " Status: " + status;
	}

	@Override
	public void SetStatus(String Status) {
		this.status=Status;
	}

	@Override
	public void SetColor(String color) {
		this.color=color;
	}

	@Override
	public String GetStatus() {		
		return status;
	}

	@Override
	public String GetColor() {		
		return color;
	}
	public Vehicle get_vehicle()
	{
		return decoratedVehicle;
	}

}
