package samples;

public class Car extends AbstractVehicle
{
	private final int numberOfSeats;
	public boolean isCar = true;

	public Car(String manufacturer, String model, int maxSpeed, int numberOfSeats) 
	{
		super(manufacturer, model, 4, maxSpeed);
		this.numberOfSeats = numberOfSeats;
	}

	@Override
	public void move() 
	{
		System.out.println("Car moving !!!");		
	}
		
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	private class GearBox
	{
		private final int numberOfGears;
		private final boolean auto;
		
		public GearBox(int numberOfGears, boolean auto)
		{
			this.numberOfGears = numberOfGears;
			this.auto = auto;
		}
		
		public int getNumberOfGears()
		{
			return this.numberOfGears;
		}
		
		public boolean isAuto()
		{
			return this.auto;			
		}
		
		private void shiftGear()
		{
			System.out.println("Car - Shifting gear");
		}
	}
}
