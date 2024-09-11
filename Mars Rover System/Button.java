/** This is a public class representing a button. 
 * The class has button features such as holding a button, 
 * showing how long its holding for and changing holds
 * 
 * @author Serena Iyoha
 * @version 1.0(11/11/21)
 * 
*/

public class Button{


	private int hold; // integer that represents the amount of time that the button is being held for

	/** creates a button that is being held for 0 units of time*/
	public Button(){
		hold = 0;
	}

	/**This method shows the amount of time that the button is being held for
	 * @return An integer that represent the amount of time the button is being held for */
	public int showHold(){
		return hold;
	}


	/**This method changes the amount of time the button is being held for.
	 * @param holdTime- the amount of time the button is being held for
	 * @throws IllegalArgumentException -when the holdTime is negative */
	public void changeHold(int holdTime){
		hold = holdTime; 
	}



}