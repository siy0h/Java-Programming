import java.util.Scanner;
/** This is a public class representing a Rover. 
 * The rover has a left and right pedal and starts at rest state (represented by a String) with a speed of zero (represented by an Integer). 
 * The rover has certain motor commands to move. If you start from rest and press the left pedal once, it will accelerate forward. If you hold
 * the left pedal for more than 5 seconds, it will accelerate backwards. There are many other commands as well. 
 * 
 * Some additional personalized commands include reaccelerating from deceleration by pressing both pedals and decelerating to rest.
 *  
 * @author Serena Iyoha
 * @version 1.0(11/11/21)
 * 
*/
	public class Rover{

	private String state; // A string that tells us what state the rover is in
	private Pedal left; // A pedal that corresponds to the left pedal
	private Pedal right; // A pedal that corresponds to the right pedal
	private long speed; // A long that tells us the speed that the rover is going 


	/** Creates a rover at a rest state with a speed of zero and a left and right pedal*/
	public Rover(){

		left = new Pedal();
		right = new Pedal();
		state = new String("rest");
		speed = 0;
	}


	/** Changes the rover's state to whatever is in the parameter
	 * @param state A string that respresent that state that the rover will change to*/

	public void setState(String state){
		this.state = state;
	}

	/** Method displays the rover's state
	 * @return  A string that represent rover's state*/

	public String getState(){
		return state;
	}

	/** Method displays the rover's speed
	 * @return  A long that represent rover's speed*/
	public long getSpeed(){
		return speed;
	}

	/** Method displays the rover's left pedal
	 * @return  A pedal that represent rover's left pedal*/
	public Pedal getLeftPedal(){
		return left;
	}

	/** Method displays the rover's right pedal
	 * @return  A string that represent rover's right pedal*/
	public Pedal getRight(){
		return right;
	}




//transitions

	/** Method checks if the speed is zero*/
	public void isZero(){
		if(speed == 0){
			rest();
		}

	}

	/** Method presses the left pedal once*/
	public void pressLeftPedalOnce(){
	
		left.press(1);
		if (state.equals("rest")){
			move();

		} else if(state.equals("timer") || state.equals("accelerateBackwards")){
			accelerateForward();
		}


	}

	/** Method presses both pedals once*/
	public void pressBoth(){
		left.press(1);
		right.press(1);
		if(state.equals("accelerateBackwards")){
		decelerate();
	} else if(state.equals("decelerate")){
		move();
	}
	}


	/** Method holds the left pedal for n amount of time.
	 * @param n An integer that represents the amount of time that the left pedal is being held for*/
	
	public void holdLeftPedal(int n){
		left.press(n);
		if(n>5){
			accelerateBackwards();
		} else {
			//assumption that anything less than 5 seconds is a basic press
			accelerateForward();
		}

	}


	/** Method holds the right pedal for n amount of time.
	 * @param n An integer that represents the amount of time that the right pedal is being held for*/
	public void holdRightPedal(int n){
		
		right.hold(n);
		if(n>5 && state.equals("accelerateForward")){
			constantSpeedForward();
		}
	}


	/** Method presses left pedal three times*/
	public void pressLeftThrice(){
		
		left.press(3);
		constantSpeedBackwards();
	}

	/** Method presses right pedal twice*/
	public void pressRightTwice(){
	
		right.press(2);
		decelerate();
	}


	/** Method checks if the rover has reached the maximum speed of 12km/hr
	 * @return a boolean that returns true if the rover has hit the maximum speed of 12 km/hr and false if otherwise*/
	public boolean maxSpeed(){
		if(speed == 12){
			return true;
		}
		return false;
	}


//states

	/**Method sets the rover's state to rest*/
	public void rest(){
		System.out.println("\n Start state: " + state);

			System.out.println( "The rover is going to rest because you have decelerated to zero");
			setState("rest");
			System.out.println("Current State:" + state );

	}


	/**Method sets the rover's state to move and calls the timer function*/
	public void move(){

		System.out.println("\n Start state: " + state);
	

		if(state.equals("rest")){
			System.out.println( "The rover the rover is ready to move because you pressed the left pedal once");
		} else if (state.equals("decelerate")){
			System.out.println( "The rover the rover is ready to move because you pressed both pedals at once");
		}
			setState("move");
			System.out.println("Current State: " + state);
			speed ++;
			timer();
		
	}


	/**Method sets the rover's state to timer and increases rover's speed. This method is a waiting state; it waits for the next move*/
	public void timer(){

		System.out.println("\n Start state: " + state);


			setState("timer");

			System.out.println( "The rover waiting for your next to move");

			System.out.println("Current State: " + state);
		
		speed ++;
	}

	/**Method sets the rover's state to accelerate forward and increases rover's speed if it is less than the maximum speed*/
	public void accelerateForward(){
		System.out.println("\n Start state: " + state);
		
		setState("accelerateForward");
		speed++;
		System.out.println( "You are going accelerate forward because you pressed the left pedal once");
		System.out.println("Current State: " + state);
		
		if (maxSpeed()){
			System.out.println(" You have reached the max speed, you will now decelerate");
			decelerate();
		}
	}

	/**Method sets the rover's state to constant speed forward*/
	public void constantSpeedForward(){

		System.out.println("\n Start state: " + state);

			setState("constantSpeedForward");
			System.out.println( "You are going at a constant speed forward because you pressed the right pedal for more than 5 seconds");
			System.out.println("Current State " + state);

	}

	/**Method sets the rover's state to accelerate backwards and increases rover's speed if it is less than the maximum speed*/
	public void accelerateBackwards(){

		System.out.println("\n Start state: " + state);
			
			setState("accelerateBackwards");
			System.out.println( "You are going accelerate backwards because you pressed the left pedal for more than 5 seconds");

			System.out.println("Current State " + state);
		
		 if (maxSpeed()){
		 	System.out.println("You have reached the max speed, you will now decelerate");
			decelerate();
		}
		speed++;

	}

	/**Method sets the rover's state to constant speed backwards*/
	public void constantSpeedBackwards(){

		System.out.println("\n Start state: " + state);

			System.out.println( "You are going at a constant speed backwards because you pressed the left pedal 3 times");


				setState("constantSpeedBackwards");
				System.out.println("Current State: " + state);
	}

	/**Method sets the rover's state to decelerate and slows down the rover*/
	public void decelerate(){

		System.out.println("\n Start state: " +  state);

				if(state.equals("accelerateForward")){
					System.out.println("You are going to decelerate because you pressed the right pedal twice");
				} else if(state.equals("accelerateBackwards")){
					System.out.println("You are going to decelerate because you pressed both pedals");
				} else if(maxSpeed()){
					System.out.println("You are going to decelerate because you have hit the max speed threshold");
				}
				setState("decelerate");
				speed = 0;
				System.out.println("Current State: " + state);
		
	}


	/**Main method tests the functions and asks the user what they want to do. 
	 * @param args array that takes in the user's response to questions and uses it to control the rover
	 * Assumption : the user response will always be exactly what the computer asks for*/
	public static void main(String[] args){

		Rover r2d2 = new Rover();
		Scanner responseScanner = new Scanner(System.in);
		System.out.println("Do you want to move forward? ");

		String response = responseScanner.nextLine();

		if(response.equals("yes")){

			r2d2.pressLeftPedalOnce();
			System.out.println("How long are pressing the left pedal");

			response = responseScanner.nextLine();


			r2d2.holdLeftPedal(Integer.valueOf(response)); //either moves forward or backward


			if(r2d2.getState().equals("accelerateForward")){
				System.out.println("Type option number. \n 1) right pedal twice \n 2)right pedal for more than 5 seconds");
				response = responseScanner.nextLine();

				if(response.equals("1")){
					r2d2.pressRightTwice();
					System.out.println("Type optio n number. \n 1) press both pedals \n 2) continue decelerating");
					//assumption that you will continue decelerating to rest unless you press both pedals
					response = responseScanner.nextLine();
					
					if(response.equals("1")){
					r2d2.pressBoth();
					} else if (response.equals("2")){
					r2d2.isZero();
					}
				} else if (response.equals("2")){
					r2d2.holdRightPedal(6);
					
				}

				} else if(r2d2.getState().equals("accelerateBackwards")){ 
					System.out.println("Type option number. \n 1) press left pedal 3 times \n 2) press both pedals");
					response = responseScanner.nextLine();
					if(response.equals("1")){
					r2d2.pressLeftThrice();
				} else if (response.equals("2")){
					r2d2.pressBoth();
					
					
					System.out.println("Type option number. \n 1) press both pedals \n 2) continue decelerating");
					//assumption that you will continue decelerating to rest unless you press both pedals
					response = responseScanner.nextLine();
					
					if(response.equals("1")){
					r2d2.pressBoth();
					} else if (response.equals("2")){
					r2d2.isZero();
					}
				}
				}

				}

			}

	}










