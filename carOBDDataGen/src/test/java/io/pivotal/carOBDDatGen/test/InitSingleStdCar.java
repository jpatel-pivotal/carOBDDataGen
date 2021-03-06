package io.pivotal.carOBDDatGen.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.pivotal.carOBDDatGen.car.StdCar;


public class InitSingleStdCar {
	static final Logger logger = LogManager.getLogger(InitSingleStdCar.class);

	@Before
	public void setup() throws Exception {
		//logger.debug("Starting up test");
	}
	
	@After
	public void tearDown() throws Exception {
		//logger.debug("Completed Single Car tests");
	}
	
	@Test
	public void canInitCar() throws Exception {
		try {
			StdCar car = new StdCar();
			Assert.assertNotNull(car.getMake());
			Assert.assertNotNull(car.getModel());
			Assert.assertNotNull(car.getYear());
			Assert.assertNotNull(car.getVin());
			Assert.assertNotNull(car.getTankCapacity());
			logger.debug((car.toString()));
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}			
		
	}
	
	@Test
	public void canParkCar() throws Exception {
		try{
			StdCar car = new StdCar();
			car.park();
			logger.debug((car.toString()));
			Assert.assertEquals(1,car.getEbrake());
			Assert.assertEquals(0,car.getCurrentGear());
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}
	}
	
	@Test
	public void canChangeGear() throws Exception {
		try {
			StdCar car = new StdCar();
			Assert.assertEquals(0,  car.getCurrentGear());
			Assert.assertEquals(0,  car.getCurrentRpm());
			car.incrementCurrentGear();
			Assert.assertEquals(1,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(2,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(3,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(4,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(5,  car.getCurrentGear());
			//Checking that it stays at 5
			car.incrementCurrentGear();
			Assert.assertEquals(5,  car.getCurrentGear());
			//Checking that gears decrement fine
			car.decrementCurrentGear();
			Assert.assertEquals(4,  car.getCurrentGear());
			car.decrementCurrentGear();
			Assert.assertEquals(3,  car.getCurrentGear());
			car.decrementCurrentGear();
			Assert.assertEquals(2,  car.getCurrentGear());
			car.decrementCurrentGear();
			Assert.assertEquals(1,  car.getCurrentGear());
			car.decrementCurrentGear();
			Assert.assertEquals(0,  car.getCurrentGear());
			//Checking that it stays at 0
			car.decrementCurrentGear();
			Assert.assertEquals(0,  car.getCurrentGear());
			//Check case for reverse
			car.putInReverse();
			Assert.assertEquals(10,  car.getCurrentGear());
			car.getOutOfReverse();
			Assert.assertEquals(1,  car.getCurrentGear());
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}
	}	
	
	@Test
	public void canChangeSpeed() throws Exception {
		//changing rpms is tested here so no need for a seperate test
		try {
			StdCar car = new StdCar();
			Assert.assertEquals(0,  car.getCurrentMileage());
			Assert.assertEquals(0, car.getCurrentSpeed());
			//Assumption is when we accelerate we change speed , rpm and mileage
			car.accelerate();
			Assert.assertEquals(1, car.getCurrentMileage());
			Assert.assertEquals(1,  car.getCurrentSpeed());
			Assert.assertEquals(1, car.getCurrentRpm());
			car.setCurrentSpeed(35);
			car.setCurrentRpm(4);
			car.accelerate();
			car.accelerate();
			// testing Decelerate test 
			//Making Sure Mileage still goes up
			Assert.assertEquals(3,  car.getCurrentMileage());
			car.decelerate();
			Assert.assertNotEquals(37,  car.getCurrentSpeed());
			Assert.assertEquals(36,  car.getCurrentSpeed());
			Assert.assertEquals(5, car.getCurrentRpm());
			Assert.assertEquals(4,  car.getCurrentMileage());
			// testing to check RPM does not go below 1 when decelerating
			for (int i=0; i < 10; i++ ) {
				logger.debug(car.getCurrentRpm());
				car.decelerate();
				Assert.assertTrue("Rpm cannot be 0 or less " + car.getCurrentRpm(), car.getCurrentRpm() >=1);
			}
			
		}catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
			}
		}

	@Test
	public void canGetMaxSpeed() throws Exception {
		try {
			StdCar car = new StdCar();
			car.setCurrentRpm(7);
			car.setCurrentSpeed(134);
			car.setCurrentMileage(88000);
			car.accelerate();
			Assert.assertEquals(135,  car.getCurrentSpeed());
			Assert.assertEquals(88001,  car.getCurrentMileage());
			Assert.assertEquals(8,  car.getCurrentRpm());
			//test that mileage increases but speed and rpm do not increase as they are at max
			car.accelerate();
			Assert.assertEquals(135,  car.getCurrentSpeed());
			Assert.assertEquals(88002,  car.getCurrentMileage());
			Assert.assertEquals(8,  car.getCurrentRpm());
			car.accelerate();
			Assert.assertEquals(135,  car.getCurrentSpeed());
			Assert.assertEquals(88003,  car.getCurrentMileage());
			Assert.assertEquals(8,  car.getCurrentRpm());

		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
			}
		}
	
	
	@Test
	public void canNotDecrementMileage() throws Exception {
		//ChengeSpeed and max Speed already test incrementing Mileage
		//This case tests that Mileage can not be decremented
		try {
			StdCar car = new StdCar();
			car.setCurrentMileage(10000);
			car.decrementCurrentMileage();
			Assert.assertEquals(10000,  car.getCurrentMileage());
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
			}
		} 
	
	@Test
	public void canChangeFuel() throws Exception {
		try {
			StdCar car = new StdCar();
			//testing > 8 but less than 12
			car.setAmountOfFuel(10);
			car.incrementFuelTank();
			Assert.assertEquals(14,  car.getAmountOfFuel());
			car.decrementFuelTank();
			Assert.assertEquals(13,  car.getAmountOfFuel());
			car.decrementFuelTank();
			Assert.assertEquals(12,  car.getAmountOfFuel());
			//testing == 12 refills a full tank
			car.incrementFuelTank();
			Assert.assertEquals(16,  car.getAmountOfFuel());
			//testing == 8 adds + 4
			car.setAmountOfFuel(8);
			car.incrementFuelTank();
			Assert.assertEquals(12,  car.getAmountOfFuel());
			car.incrementFuelTank();
			Assert.assertEquals(16,  car.getAmountOfFuel());
			//Should stay at 16
			car.incrementFuelTank();
			Assert.assertEquals(16,  car.getAmountOfFuel());

		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
			}
		}
	
	@Test
	public void checkYearInRange() throws Exception {
		try {
			StdCar car = new StdCar();
			Assert.assertTrue(2010 <= car.getYear() && car.getYear() <= 2016);
			logger.debug(car.toString());
			StdCar car2 = new StdCar();
			Assert.assertTrue(2010 <= car2.getYear() && car2.getYear() <= 2016);
			logger.debug(car2.toString());
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}
	}
	
	@Test
	public void canGetRandomMakeModel() throws Exception {
		try {
			StdCar car = new StdCar();
			logger.debug(car.toString());
			StdCar car2 = new StdCar();
			logger.debug(car2.toString());
			if (car2.getMake().equals(car.getMake())){
				if (car2.getModel().equals(car.getModel())) {
					Assert.assertNotEquals(car2.getVin(), car.getVin());
				} else {
				Assert.assertNotEquals(car.getModel() , car2.getModel());
				}
			} else if (car2.getModel().equals(car.getModel())) {
				if (car2.getMake().equals(car.getMake())) {
					Assert.assertNotEquals(car2.getVin(), car.getVin());
				} else {
					Assert.assertNotEquals(car.getMake(), car2.getMake());	
				}
							
			} else {
				Assert.assertNotEquals(car.getMake(), car2.getMake());
				Assert.assertNotEquals(car.getModel() , car2.getModel());
			}
			
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
			
		}
	}
}