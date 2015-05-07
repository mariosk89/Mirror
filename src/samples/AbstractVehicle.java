package samples;

public abstract class AbstractVehicle 
{
	protected final int numberOfWheels;
	protected final int maxSpeed;
	protected final String manufacturer;
	protected final String model;
		
	public AbstractVehicle(String manufacturer, String model, int numberOfWheels, int maxSpeed)
	{
		this.numberOfWheels = numberOfWheels;
		this.maxSpeed = maxSpeed;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	public abstract void move(int x, int y);
	
	public String getManufacturer()
	{
		return this.manufacturer;		
	}
	
	public String getModel()	
	{
		return this.model;
	}
	
	public int getNumberOfWheels()
	{
		return this.numberOfWheels;
	}
	
	public int getMaxSpeed()
	{
		return this.maxSpeed;
	}
	
}
