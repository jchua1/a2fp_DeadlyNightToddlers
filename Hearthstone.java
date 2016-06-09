import java.util.*;

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
		Engine.helpH();
	    } 
	    else if (choice.toUpperCase().equals("EXIT")) { 
		System.out.println("Thanks for playing!"); 
		exitSession = true; 
	    } 
	    else if (choice.toUpperCase().equals("PLAY")) { 
		play(); 
	    }
	    else if (choice.toUpperCase().equals("MY COLLECTION")) {
		collection();
	    }
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
	    while (choice.equals("end") == false) {
		choice = in.nextLine();
		Engine.move(choice);
	    }
	    choice = "";
	}
    }
    
    public void collection() {
	System.out.println("Accessing your collection...");
	Engine.playerCollection.addMinions();
	Engine.playerCollection.addSpells();
	Engine.playerCollection.addWeapons();
	Engine.playerCollection.organize();
	Scanner in = new Scanner(System.in);
	String command = "";
	boolean inCollection = true;
	int p = 0;
	while (inCollection) {
	    Engine.playerCollection.showCards(p);
	    System.out.println("What will you do next?");
	    command = in.nextLine();
	    if (command.toUpperCase().equals("HELP")){ 
		Engine.helpC();
	    }
	    else if (command.toUpperCase().equals("NEXT") 
		&& (p+1)*8 < Engine.playerCollection.display.size())
		p++;
	    else if (command.toUpperCase().equals("PREVIOUS")
		     && p-1 >= 0)
		p--;
	    else if (command.toUpperCase().equals("NEXT") 
		     && (p+1)*8 > Engine.playerCollection.display.size())
		System.out.println("You are on the last page!");
	    else if (command.toUpperCase().equals("PREVIOUS")
		     && p-1 < 0)
		System.out.println("You are on the first page!");
	    else if (command.toUpperCase().equals("EXIT")) {
		System.out.println("Leaving your collection...");
		inCollection = false;
	    }
	    else 
		System.out.println("Not a valid command!");
	}
    }
	    
    
    public static void main( String[] args ) {
	Hearthstone m = new Hearthstone();	
    }
}
