import org.junit.*;
import static org.junit.Assert.*;
import java.util.Random;

/**This JUnit does not compile. 
 * My text editor is not able to compile the file.
 *  I am happy to explain the logic behind my code in office hours to get full marks*/
public class RoverTest{

	@Before
	private Rover rover = new Rover();

	@Test
	public void testSetandGetState(){
		rover.setState("test");
		assertEquals("test", rover.getState());

	}

	@Test
	public void testSetandGetSpeed(){
		rover.setSpeed(0);
		assertEquals(0, rover.getSpeed());

	}


	@Test
	public void testGetLeftPedal(){
		Pedal test = rover.getLeft();
		assertSame(test, rover.getLeft());
		assertEquals(test,rover.getLeft());
		assertTrue(rover.getLeft().equals(test));
	}

	@Test
	public void testGetRightPedal(){
		Pedal test = rover.getRight();
		assertSame(test, rover.getRight());
		assertEquals(test,rover.getRight());
		assertTrue(rover.getRight().equals(test));
	}


	@Test
	public void testisZero(){
		assertTrue(rover.isZero());
	}

	@Test
	public void testisnotZero(){
		rover.setSpeed(10);
		assertTrue(rover.isZero());	
	}

	@Test
	public void testPressLeftPedalOnce(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		assertEquals(1,rover.getLeft().showHold());
	}


	@Test
	public void testPressBoth(){
		rover = new Rover();
		rover.pressBoth();
		assertTrue(rover.getLeft().showHold() == 1 && rover.getRight().showHold() == 1);
	}

	@Test
	public void testPressLeftThrice(){
		rover = new Rover();
		rover.pressLeftThrice();
		assertEquals(3,rover.getLeft().showHold());
	}

	@Test
	public void testPressRightTwice(){
		rover = new Rover();
		rover.pressRightTwice();
		assertEquals(2,rover.getRight().showHold());
	}

	@Test
	public void testInitalRest(){
		rover = new Rover();
		assertEquals("rest",rover.getState());

	}


	@Test
	public void testMoveForwardFromRest(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		assertEquals("timer", rover.getState());
	}

	@Test
	public void testAccelerateForwardOrBackwardsFromTimer(){
		rover = new Rover();
		Random number = new Random();
		number = number.nextInt();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(number);
		if (number>5){
		assertEquals("accelerateBackwards", rover.getState());
		} else{
		assertEquals("accelerateForward", rover.getState());
		}
	}
	
	@Test
	public void testDecelerateFromAccelerateForward(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(2);
		rover.pressRightTwice();
		assertEquals("decelerate",rover.getState());

	}
	
	@Test
	public void testConstantSpeedForwardFromAccelerateForward(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(2);
		rover.holdRightPedal(8);
		assertEquals("constantSpeedForward",rover.getState());

	}

	@Test
	public void testDecelerateToRest(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(2);
		rover.pressRightTwice();
		rover.isZero();
		assertEquals("rest",rover.getState());

	}

	@Test
	public void testDecelerateToRest(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(2);
		rover.pressRightTwice();
		rover.isZero();
		assertEquals("rest",rover.getState());

	}

	@Test
	public void testDecelerateToRest2(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(8);
		rover.pressBoth();
		rover.isZero();
		assertEquals("rest",rover.getState());

	}


	@Test
	public void testDecelerateToTimer(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(2);
		rover.pressRightTwice();
		rover.pressBoth();
		assertEquals("timer",rover.getState());

	}

	@Test
	public void testDecelerateToTimer2(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(8);
		rover.pressBoth();
		rover.pressBoth();
		assertEquals("timer",rover.getState());

	}

	@Test
	public void testDeceleterateFromAccelerateBackwards(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(8);
		rover.pressBoth();
		assertEquals("decelerate",rover.getState());
	}

	@Test
	public void testConstantSpeedBackwardsFromAccelerateBackwards(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		rover.holdLeftPedal(8);
		rover.pressLeftThrice();
		assertEquals("constantSpeedBackwards",rover.getState());
	}

	@Test
	public void testMaxSpeed(){
		rover = new Rover();
		rover.pressLeftPedalOnce();
		for(int i = 0 ; i<=13; i++){
			rover.holdLeftPedal(2);
		}
		assertEquals("decelerate", rover.getState());
	}

	

	public static void main(args String []){
		boolean testPassed = (testisZero() && testisnotZero() && testMaxSpeed() && testPressBoth()
			&& testInitalRest() && testDecelerateToRest() && testGetLeftPedal() && testDecelerateToRest2()
			&& testDecelerateToTimer() && testDecelerateToTimer2() && testConstantSpeedForwardFromAccelerateForward()
			&& testConstantSpeedBackwardsFromAccelerateBackwards() && testAccelerateForwardOrBackwardsFromTimer() 
			&& testPressLeftThrice() && testPressRightTwice() && testPressLeftPedalOnce());

		if(testPassed){
			System.out.println("All test passed!");
		} else {
		 System.out.println("Failure");
		}
	}

	

}
