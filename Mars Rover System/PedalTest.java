import org.junit.*;

/**This JUnit does not compile. 
 * My text editor is not able to compile the file. 
 * I am happy to explain the logic behind my code in office hours to get full marks*/
import static org.junit.Assert.*;

public class PedalTest{


	@Before
	private Pedal pedal = new Pedal();

	public void testSetUp(){
		assertEquals(0,pedal.sensor.showHold());
	}

	@Test
	public void testPress(){
		pedal.press(9);
		assertEquals(9,pedal.sensor.showHold());
	}

	@Test
	public void testHold(){
		pedal.hold(10);
		assertEquals(10,pedal.sensor.showHold());
	}


	@Test
	public void testButton(){
		Button dummie = pedal.sensor();
		assertSame(dummie, pedal.sensor());
	}


	public static void main(String args[]){
		testSetUp();
		testButton();
		testHold();
		testPress();
	}
}
