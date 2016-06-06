import java.util.Scanner; 

public class Hearthstone {


    public String name = "Bob";

    public Hearthstone() {
	newGame();
    }
    
    public void newGame() {
	boolean chosenName = false;
	Scanner entrance = new Scanner(System.in);
	boolean entered = false;
	boolean exitSession = false;
	String enter; // for all user inputs
		
	while (!entered) {
	    System.out.println("Press enter to begin.");
	    enter = entrance.nextLine();
	    entered = true;
	    
	}
	
	    System.out.println( "Welcome to Hearthstone!" );	
	    System.out.println();
	    
	    while (!chosenName){
		
		System.out.println( "Enter an awesome name:" );
		
		enter = entrance.nextLine();
		
		System.out.println( "Your character's name is "+ enter + ".");
		
		if (confirm()) {
		    name = enter;
		    chosenName = true;
		}
		else if (!chosenName) {
		    System.out.println("Your default name will be Bob.");
		    if (confirm()) {
			chosenName = true;
		    }
		}
	    }
    		

	    //instead of having a game over, players come back here regardless of whether they win or lose their match (they keep playing until they voluntarily exit)
	    while (!exitSession) {
		System.out.println();
		System.out.println("Hello " + name + ".");
		System.out.println("What would you like to do?");
		System.out.println("Type '?' or 'help' for a manual if you need help");
		System.out.println("Type 'exit' if you would like to stop playing"); 
		System.out.println();

		Scanner in = new Scanner( System.in );
	
		String choice = in.nextLine();
		
		if(choice.toUpperCase().equals("HELP") || choice.equals("?")) {
		    //Engine.help();
		} 
		else if (choice.toUpperCase().equals("EXIT")) { 
		    System.out.println("Thanks for playing!"); 
		    exitSession = true; 
		} 
		else if (choice.toUpperCase().equals("PLAY")) { 
		    //m.play(); 
		}
		else { 
		    System.out.println("That doesn't seem like a valid command.");
	    }	    
    }

    public boolean confirm() { 
    	System.out.println( "Are you sure? (YES/NO)" );

    	Scanner in = new Scanner( System.in );
	    
	String s = in.nextLine();

    	if( s.toUpperCase().equals( "YES" ) ) {
	    return true;
    	}

    	return false;
    }

    public void tutorial() {
    
    }

    public void play() {

    }
    
    public static void main( String[] args ) {
	Hearthstone m = new Hearthstone();	
    }
}
