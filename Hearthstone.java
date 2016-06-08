import java.util.Scanner; 

public class Hearthstone {

    public String welcome() {
	String s = "";
	s += "  _    _                 _   _         _                   " + "\n";
	s += " | |  | |               | | | |       | |                  " + "\n";
	s += " | |__| | ___  __ _ _ __| |_| |__  ___| |_ ___  _ __   ___ " + "\n";
	s += " |  __  |/ _ \\/ _' | '__| __| '_ \\/ __| __/ _ \\| '_ \\ / _ \\" + "\n";
	s += " | |  | |  __/ (_| | |  | |_| | | \\__ \\ || (_) | | | |  __/" + "\n";
	s += " |_|  |_|\\___|\\__,_|_|   \\__|_| |_|___/\\__\\___/|_| |_|\\___|" + "\n";
	return s;
    }

    public Hearthstone() {
    	System.out.print(welcome());
	newGame();
    }
    
    public void newGame() {
	Scanner entrance = new Scanner(System.in);
	boolean entered = false;
	boolean inCollection = false;
	boolean exitSession = false;
	String enter;
		
	while (!entered) {
	    System.out.println("Press enter to begin.");
	    enter = entrance.nextLine();
	    entered = true;
	}
	
	System.out.println( "Welcome to Hearthstone!" );	
	System.out.println();
    		
	while (!exitSession) {
	    System.out.println();
	    System.out.println("Hello.");
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
		play(); 
	    }/*
	    else if (choice.toUpperCase().equals("COLLECTION")) {
		collection();
		}*/
	    else { 
		System.out.println("That doesn't seem like a valid command.");
	    }	    
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
	Scanner in = new Scanner( System.in );
	
	String choice = "";
		
	while (Engine.opponentHero.health > 0 && Engine.playerHero.health > 0) {
	    Graphics.refresh();
	    Engine.printArrayM( Graphics.display );
	    System.out.println ("What will you do next?");
	    choice = in.nextLine(); 	
	    Engine.move(choice);
	}
    }
    /*
    public void collection() {
	Scanner in = new Scanner(System.in);
	String command = "";
	inCollection = true;
	while (inCollection) {
	    
	}
	}*/
	    
    
    public static void main( String[] args ) {
	Hearthstone m = new Hearthstone();	
    }
}
