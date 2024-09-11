/** This is a public class representing a pedal. 
 * The class has a sensor that is a button. This sensor senses the pedal movements
 * It can be held for a certain amount of time or pressed a certain number of times. 
 * 
 * @author Serena Iyoha
 * @version 1.0(11/11/21)
 * 
*/
public class Pedal{

	private Button sensor; //A button that represents the sensor that controls the pedal

		/** creates a pedal that has a sensor button*/
		public Pedal(){
			sensor = new Button();

		}


/** Method that presses the sensor a certain number of time
 * @param pressFrequency integer value representing the number of times a button is press*/
		public void press(int pressFrequency){
			sensor.changeHold(pressFrequency);
		}

/** Method that holds the sensor for a certain amount of time
 * @param holdTime integer value representing the amount of time a button is press*/
		public void hold(int holdTime){
			sensor.changeHold(holdTime);
		}

/**Method that allows access to the sensor and its properties
 * @return The sensor object*/
		public Button sensor(){
			return sensor;
		}


	}