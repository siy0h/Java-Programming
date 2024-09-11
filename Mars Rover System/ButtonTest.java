import org.junit.*;
import static org.junit.Assert.*;

/**JUnit test for Button class
 * This JUnit does not compile. My text editor is not able to compile the file. 
 * I am happy to explain the logic behind my code in office hours to get full marks
 * 
 *@author Serena Iyoha
 * @version 1.0 (11/17/2021)
 */
public class ButtonTest{

	
	@Before
	private Button button = new Button();

	@Test
	public void testInitialHold(){
		assertEquals(0,button.showHold());
	}


	@Test
	public void testChangeHold(){
		button.changeHold(20);
		assertEquals(20,button.showHold());
	}

	public static void main(String args[]){
		org.junit.runner.JUnitCore.main("Button");
	}
}