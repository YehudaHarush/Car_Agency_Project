package vehicles;


public abstract class Vehicle {
	protected String name;
	protected float km;
	protected int passengers;
	protected int max_speed;
	protected String photo;
	public Vehicle(String name,int passengers,int max_speed)
	{
		this.name=String.valueOf(name);
		this.passengers=passengers;
		this.max_speed=max_speed;
		this.km=0;
	}
	public void copyValues(Vehicle other) {
	   this.name=String.valueOf(other.name);
	   this.km=other.km;
	   this.passengers=other.passengers;
	   this.max_speed=other.max_speed;
	  } 
	public void Distance(float distance)
	{
		this.km=this.km+distance;
	}
	public String get_Type()
	{
		return "Vehicle";
	}
	public String toString()
	{
		return "Model:"+this.name+",traveled: "+ this.km+" Km , Max speed of "+this.max_speed+" Mph, can carry max of " + this.passengers+ " passengers ,";
	}
	public String get_name()
	{
		return this.name;
	}
	public int get_passengers()
	{
		return this.passengers;
	}
	public int get_maxspeed()
	{
		return this.max_speed;
	}
	public Boolean equals(Vehicle x)
	{
		if (x.name.equals(this.name))
		{
			if(x.passengers==this.passengers)
			{
				if(x.max_speed==this.max_speed)
				{
					return true;
				}
			}
		}
		return false;
	}
	public void reset_km()
	{
		this.km=0;
	}
	
	public void setPhoto(String photo)
	{
		this.photo=String.valueOf(photo);
	}
	public String getPhoto()
	{
		return this.photo;
	}
	public float get_distance()
	{
		return this.km;
	}
}
