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
	Engine.clearConsole();
    	System.out.print(welcome());
	newGame();
    }
    
    public void newGame() {
	Scanner entrance = new Scanner(System.in);
	boolean entered = false;
	boolean exitSession = false;
	String enter;
	Engine.playerCollection.addMinions();
	Engine.playerCollection.addSpells();
	Engine.playerCollection.addWeapons();
	Engine.playerCollection.organize();
		
	while (!entered) {
	    System.out.println("Press enter to begin.");
	    enter = entrance.nextLine();
	    entered = true;
	    Engine.clearConsole();
	}
	
	System.out.println( "Welcome to Hearthstone!" );	
	System.out.println();
    		
	while (!exitSession) {
	    System.out.println("What would you like to do?");
	    System.out.println("Type '?' or 'help' for a manual if you need help.");
	    System.out.println("Type 'exit' if you would like to stop playing."); 
	    System.out.println();

	    Scanner in = new Scanner( System.in );
	
	    String choice = in.nextLine();
		
	    if(choice.toUpperCase().equals("HELP") || choice.equals("?")) {
		Engine.clearConsole();
		Engine.helpH();
	    } 
	    else if (choice.toUpperCase().equals("EXIT")) { 
		System.out.println("Thanks for playing!"); 
		exitSession = true; 
	    } 
	    else if (choice.toUpperCase().equals("PLAY")) { 
		Engine.clearConsole();
		play(); 
	    }
	    else if (choice.toUpperCase().equals("MY COLLECTION")) {
		Engine.clearConsole();
		collection();
	    }
	    else {
		Engine.clearConsole();
		System.out.println("Invalid command!");
		System.out.println();
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
		if (Engine.playerHero.health == 0) { 
		    choice = "end"; 
		}
	    }
	    choice = "";
	    Engine.clearConsole(); 
	    winCond();
	}
    }
    
    public void collection() {
	Engine.clearConsole();
	System.out.println("Accessing your collection...");
	Scanner in = new Scanner(System.in);
	String command = "";
	boolean inCollection = true;
	int p = 0;
	while (inCollection) {
	    Engine.playerCollection.showCards(p);
	    System.out.println();
	    System.out.println("What would you like to do?");
	    command = in.nextLine();
	    if (command.toUpperCase().equals("HELP")){ 
		Engine.clearConsole();
		Engine.helpC();
	    }
	    else if (command.toUpperCase().equals("NEXT") 
		     && (p+1)*8 < Engine.playerCollection.display.size()) {
		Engine.clearConsole();
		p++;
	    }
	    else if (command.toUpperCase().equals("PREVIOUS")
		     && p-1 >= 0) {
		Engine.clearConsole();
		p--;
	    }
	    else if (command.toUpperCase().equals("NEXT") 
		     && (p+1)*8 > Engine.playerCollection.display.size()) {
		Engine.clearConsole();
		System.out.println("You are on the last page!");
		System.out.println();
	    }
	    else if (command.toUpperCase().equals("PREVIOUS")
		     && p-1 < 0) {
		Engine.clearConsole();
		System.out.println("You are on the first page!");
		System.out.println();
	    }
	    else if (command.toUpperCase().equals("EXIT")) {
		Engine.clearConsole();
		System.out.println("Leaving your collection...");
		System.out.println();
		inCollection = false;
	    }
	    else if (command.toUpperCase().equals("FILTER")) {
		int mana = -1;
		while (mana < 0 || mana > 7) { 
		    Engine.clearConsole();
		    System.out.println("Show cards of what mana cost?");
		    System.out.println("0 1 2 3 4 5 6 7+");
		    System.out.println("Note: Enter 7 to show cards of 7+ mana");
		    mana = in.nextInt();
		    if (mana < 0 || mana > 7) {
			System.out.println("Invalid input!");
			System.out.println();
		    }
		    in.nextLine();
		}
		Engine.playerCollection.filterCost(mana);
	    }
	    else if (command.toUpperCase().equals("UNFILTER")) {
		Engine.playerCollection.unfilter();
		Engine.clearConsole();
		System.out.println("Filter removed.");
		System.out.println();
	    }
	    else if (command.toUpperCase().equals("CREATE DECK")) {
		int choice = -2;
		if (choice == -2)
		    Engine.clearConsole();
		choice = -1;
		while (choice < 0 || choice > 8) {
		    System.out.println("Choose your hero.");
		    System.out.println("Warrior | Shaman | Rogue");
		    System.out.println("Paladin | Hunter | Druid");
		    System.out.println("Warlock |  Mage  | Priest");
		    String heroChoice = in.nextLine();
		    if (heroChoice.toUpperCase().equals("DRUID"))
			choice = 0;
		    else if (heroChoice.toUpperCase().equals("HUNTER"))
			choice = 1;
		    else if (heroChoice.toUpperCase().equals("MAGE"))
			choice = 2;
		    else if (heroChoice.toUpperCase().equals("PALADIN"))
			choice = 3;
		    else if (heroChoice.toUpperCase().equals("PRIEST"))
			choice = 4;
		    else if (heroChoice.toUpperCase().equals("ROGUE"))
			choice = 5;
		    else if (heroChoice.toUpperCase().equals("SHAMAN"))
			choice = 6;
		    else if (heroChoice.toUpperCase().equals("WARLOCK"))
			choice = 7;
		    else if (heroChoice.toUpperCase().equals("WARRIOR"))
			choice = 8;
		    else {
			Engine.clearConsole();
			System.out.println("Invalid choice!");
			System.out.println();
		    }
		}
		Engine.playerCollection.makeDeck(choice);
	    }
	    else  {
		Engine.clearConsole();
		System.out.println("Invalid command!");
	    }
	}
    }
	    
    public static void winCond () { 
	if (Engine.playerHero.health == 0) { 
	    System.out.println ("You have lost the match!"); 
	}
	else { 
	    System.out.println ("You have won the match!"); 
	}
    }

    public static void main( String[] args ) {
	Hearthstone m = new Hearthstone();	
    }
}
