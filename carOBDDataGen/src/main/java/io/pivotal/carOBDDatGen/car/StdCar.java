package io.pivotal.carOBDDatGen.car;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import com.google.gson.Gson;

public class StdCar {
	
	private int amountOfFuel;
	private int tankCapacity;
	private int currentMileage;
    /* We use an int to track gears that a car is in
     * 0 = Park
     * 1 - 5 Different Gears 
     * 10 = Reverse
     */
	private int currentGear;
	private int currentSpeed;
	private int currentRpm;
	/*
	 * 0 = no eBrake applied
	 * 1 = eBrake is on
	 */
	private int eBrake;
	private float engTemp;
	private float insideTemp;
	private float outsideTemp;
	private float frontLeftPsi;
	private float frontRightPsi;
	private float rearLeftPsi;
	private float rearRightPsi;
	private String make;
	private String model;
	private String vin;
	private int year;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public StdCar () {
		Random rnd = new Random();
		String mModel = getRandomMakeModel();
		this.make = mModel.substring(0, mModel.indexOf(":"));
		this.model = mModel.substring(mModel.indexOf(":")+1);
		this.year = getRandomYear();
		this.vin = randomString(17);
		this.tankCapacity = 16;
		this.amountOfFuel = rnd.nextInt(1+tankCapacity);
		this.setCurrentMileage(275+rnd.nextInt(5000));
		this.setCurrentSpeed(7+rnd.nextInt(35));
		this.setEngTemp(rnd.nextFloat() * (220 - 195) + 195);
		this.setFrontLeftPsi(rnd.nextFloat() * (40 - 35) + 35);
		this.setFrontRightPsi(rnd.nextFloat() * (40 - 35) + 35);
		this.setRearLeftPsi(rnd.nextFloat() * (38 - 35) + 35);
		this.setRearRightPsi(rnd.nextFloat() * (38 - 35) + 35);
	}

	public int getAmountOfFuel() {
		return amountOfFuel;
	}

	public void setAmountOfFuel(int amountOfFuel) {
		this.amountOfFuel = amountOfFuel;
	}

	public int getTankCapacity() {
		return tankCapacity;
	}

	public void setTankCapacity(int tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

	public int getCurrentMileage() {
		return currentMileage;
	}

	public void setCurrentMileage(int currentMileage) {
		this.currentMileage = currentMileage;
	}

	public int getCurrentGear() {
		return currentGear;
	}

	public void setCurrentGear(int currentGear) {
		this.currentGear = currentGear;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public int getCurrentRpm() {
		return currentRpm;
	}

	public void setCurrentRpm(int currentRpm) {
		this.currentRpm = currentRpm;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getEbrake() {
		return eBrake;
	}

	public void setEbrake(int eBrake) {
		this.eBrake = eBrake;
	}

	public float getEngTemp() {
		return engTemp;
	}

	public void setEngTemp(float engTemp) {
		this.engTemp = engTemp;
	}

	public float getInsideTemp() {
		return insideTemp;
	}

	public void setInsideTemp(float insideTemp) {
		this.insideTemp = insideTemp;
	}

	public float getOutsideTemp() {
		return outsideTemp;
	}

	public void setOutsideTemp(float outsideTemp) {
		this.outsideTemp = outsideTemp;
	}

	public float getFrontLeftPsi() {
		return frontLeftPsi;
	}

	public void setFrontLeftPsi(float frontLeftPsi) {
		this.frontLeftPsi = frontLeftPsi;
	}

	public float getFrontRightPsi() {
		return frontRightPsi;
	}

	public void setFrontRightPsi(float frontRightPsi) {
		this.frontRightPsi = frontRightPsi;
	}

	public float getRearLeftPsi() {
		return rearLeftPsi;
	}

	public void setRearLeftPsi(float rearLeftPsi) {
		this.rearLeftPsi = rearLeftPsi;
	}

	public float getRearRightPsi() {
		return rearRightPsi;
	}

	public void setRearRightPsi(float rearRightPsi) {
		this.rearRightPsi = rearRightPsi;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	public void incrementFuelTank() {
		//This is a full tank refuel action or additional 4 gallons
		if (this.amountOfFuel >= 12 )
			this.setAmountOfFuel(this.getTankCapacity());
		else if (this.amountOfFuel <= 8)
			this.setAmountOfFuel(this.amountOfFuel+=4);
		else if (this.amountOfFuel > 8 && this.amountOfFuel < 12)
			this.setAmountOfFuel(this.amountOfFuel+=4);
		else if (this.amountOfFuel == 16)
			this.setAmountOfFuel(this.getTankCapacity());
	}

	public void decrementFuelTank() {
		if (this.amountOfFuel > 0)
			this.amountOfFuel--;
	}
	
	public void incrementCurrentMileage() {
		if (this.currentMileage < 999999 )
			this.currentMileage++;
	}
	
	public void decrementCurrentMileage() {
		//if ( this.currentMileage > 25 )
			//this.currentMileage--;
	}
	

	public void incrementCurrentGear() {
		if ( this.currentGear == 0)
			this.currentGear++;
		else if ( this.currentGear < 5 )
			this.currentGear++;
	}
	
	public void decrementCurrentGear() {
		if ( this.currentGear > 0 && this.currentGear <= 5)
			this.currentGear--;	
	}
	
	public void putInReverse() {
		this.currentGear = 10;
		this.currentSpeed = 2;
		this.currentRpm = 1;
	}
	
	public void getOutOfReverse() {
		this.currentGear = 1;
		
	}

	public void park() {
		this.currentGear = 0;
		this.eBrake = 1;
		this.engTemp = 0;
	}
	
	public void accelerate() {
		if ( this.currentSpeed < 135  && this.currentRpm < 8 ) {
			this.currentSpeed++;
		    this.currentRpm++;
		    this.currentMileage++;
		    this.engTemp++;
		} else if ( this.currentSpeed == 135 || this.currentRpm == 8 || this.engTemp == 220)
			this.currentMileage++;
	}
	
	public void decelerate(){
		if (this.currentSpeed > 0 && this.currentRpm > 1 && this.engTemp > 195) {
			this.currentSpeed--;
			this.currentRpm--;
			this.currentMileage++;
			this.engTemp--;
		} else if ( this.currentSpeed > 0 && this.currentRpm == 1 || this.engTemp == 195) {
			this.currentSpeed--;
			this.currentMileage++;
		}
	}
	
	public String randomString( int len ) 
	{
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}
	
	public int getRandomYear(){
		Random random = new Random();
		Calendar c = new GregorianCalendar(2010+random.nextInt(7),1,1);
		return c.get(Calendar.YEAR);
		 
	}
	
	public String getRandomMakeModel() {
		Random random = new Random();
		String [] makeModel = {"Honda:Accord","Honda:Civic","Honda:CRV",
				"Mercedes:C250","Mercedes:E400","Mercedes:S500","BMW:328xi",
				"BMW:528i","Volvo:XC90","Volvo:XC60","GMC:Yukon",
				"Toyota:Highlander","Toyota:Camry",
				"Dodge:Durango", "Ford:Mustang"};
		return makeModel[random.nextInt(makeModel.length)];
	}
	
	public int takeRandomAction() {
		Random rnd = new Random();
		int randAction = rnd.nextInt(10);
		switch (randAction) {
		case 0:
			//Park
			this.park();
			break;
		case 1:
			//Accelerate
			this.accelerate();
			break;
		case 2:
			//Decelerate 
			this.decelerate();
			break;
		case 3:
			//Change Gears Up
			this.incrementCurrentGear();
			break;
		case 4:
			//Change Gears Down
			this.decrementCurrentGear();
			break;
		case 5:
			//Put in Reverse
			this.putInReverse();
			break;
		case 6:
			//Decrement Fuel
			this.decrementFuelTank();
			break;			
		case 7:
			//Increment Fuel
			this.incrementFuelTank();
			break;			
		case 8:
			//Cruising along highway while maintaining speed and rpm
			if (this.getCurrentSpeed() >= 42) {
				this.incrementCurrentMileage();	
			}
			break;
		case 9:
			//Cruising along inner city while maintaining speed and rpm
			if (this.getCurrentSpeed() <= 42) {
				this.incrementCurrentMileage();	
			}
			break;	
		}
		return randAction;
	}
	
	public String toString() {
		Gson gson = new Gson();
		String result = gson.toJson(this);
		return result;
	}
	
}
