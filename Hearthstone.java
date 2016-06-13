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
	Engine.oppDefault();
		
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
	    System.out.println("Type 'help' for a manual if you need help.");
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
		if (Engine.playerDeck.empty()) {
		    int yn = -1;
		    while (yn != 0) {
			System.out.println("You don't have a deck!");
			System.out.println("Would you like to use a default deck? (YES/NO)");
			choice = in.nextLine();
			if (choice.toUpperCase().equals("YES")) {
			    yn = 0;
			    Engine.useDefault();
			    Engine.clearConsole();
			    System.out.println("You are now using the default deck!");
			    System.out.println();
			}
			else if (choice.toUpperCase().equals("NO")) {
			    Engine.clearConsole();
			    yn = 0;
			}
			else {
			    Engine.clearConsole();
			    System.out.println("Invalid input!");
			    System.out.println();
			}
		    }
		}
		else {
		    play();
		}

	    }
	    else if (choice.toUpperCase().equals("MY COLLECTION")) {
		Engine.clearConsole();
		collection();
	    }
	    else if (choice.toUpperCase().equals("SHOW")) {
		Engine.clearConsole();
		if (Engine.playerDeck.empty())
		    System.out.println("You don't have a deck!");
		else
		    System.out.println(Engine.playerDeck);
		System.out.println();
	    }
	    else if (choice.toUpperCase().equals("USE DEFAULT")) {
		Engine.clearConsole();
		while (!Engine.playerDeck.empty()) { 
		    Engine.playerDeck.pop(); 
		}
		Engine.useDefault();
		System.out.println("You are now using the default deck!");
		System.out.println();
	    }
	    else {
		Engine.clearConsole();
		System.out.println("Invalid command!");
		System.out.println();
	    }	    
	}
    }

    public void play() {
	Scanner in = new Scanner( System.in );
	String choice = "";
	Engine.shuffle(Engine.playerDeck);
	Engine.draw(Engine.playerHand,Engine.playerDeck,3);
	Engine.draw(Engine.opponentHand,Engine.opponentDeck,4);
	while (Engine.opponentHero.health > 0 && Engine.playerHero.health > 0) {
		if( Engine.playerHand.size() < 10 )
	    	Engine.draw(Engine.playerHand,Engine.playerDeck,1);
	    if( Engine.opponentHand.size() < 10 )
	    	Engine.draw(Engine.opponentHand,Engine.opponentDeck,1);

	    while (!choice.toUpperCase().equals("END")) {
		Graphics.showBoard();
		System.out.println ("What will you do next?");
		choice = in.nextLine();
		Engine.move(choice);
		if (Engine.playerHero.health == 0) { 
		    choice = "end"; 
		}
	    }

	    Engine.checkMinions(Engine.playerMinions);

	    if( Engine.playerMana < 10 ) {
	    	Engine.playerMana += 1;
		Engine.pTurnMana = Engine.playerMana;
	    }

	    Engine.aiMove();
	    if( Engine.opponentMana < 10 ) {
	    	Engine.opponentMana += 1;
		Engine.oTurnMana = Engine.opponentMana;
	    }

	    choice = "";
	    winCond();
	}
    }
    
    public void collection() {
	Engine.clearConsole();
	System.out.println("Accessing your collection...");
	System.out.println();
	Engine.playerCollection.unfilter();
	Scanner in = new Scanner(System.in);
	String command = "";
	boolean inCollection = true;
	int p = 0;
	while (inCollection) {
	    Engine.playerCollection.showCards(p);
	    System.out.println();
	    System.out.println("What would you like to do?");
	    command = in.nextLine();
	    if (command.toUpperCase().equals("HELP") ||
		command.equals("?")){ 
		Engine.clearConsole();
		Engine.helpC();
	    }
	    else if (command.toUpperCase().equals("NEXT")) {
		Engine.clearConsole();
		if ((p+1)*6 < Engine.playerCollection.display.size()) {
		    p++;
		}
		else {
		    System.out.println("You are on the last page!");
		    System.out.println();
		}
	    }
	    else if (command.toUpperCase().equals("PREVIOUS")) {
		Engine.clearConsole();
		if (p-1 >= 0) {
		    p--;
		}
		else {
		    System.out.println("You are on the first page!");
		    System.out.println();
		}
	    }
	    else if (command.toUpperCase().equals("EXIT")) {
		Engine.clearConsole();
		System.out.println("Leaving your collection...");
		System.out.println();
		inCollection = false;
	    }
	    else if (command.toUpperCase().equals("FILTER")) {
		int mana = -1;
		Engine.clearConsole();
		while (mana < 0 || mana > 7) { 
		    System.out.println("Show cards of what mana cost?");
		    System.out.println("0 1 2 3 4 5 6 7+");
		    System.out.println("Note: Enter 7 to show cards of 7+ mana");
		    mana = in.nextInt();
		    if (mana < 0 || mana > 7) {
			Engine.clearConsole();
			System.out.println("Invalid input!");
			System.out.println();
		    }
		    in.nextLine();
		}
		Engine.playerCollection.filterCost(mana);
		p = 0;
		Engine.clearConsole();
	    }
	    else if (command.toUpperCase().equals("UNFILTER")) {
		Engine.playerCollection.unfilter();
		p = 0;
		Engine.clearConsole();
		System.out.println("Filter removed.");
		System.out.println();
	    }
	    else if (command.toUpperCase().equals("CREATE DECK")) {
		if (Engine.playerDeck.empty())
		    Engine.chooseHero();
		else {
		    int yn = -1;
		    Engine.clearConsole();
		    while (yn != 0) {
			System.out.println("You already have a deck.");
			System.out.println("Do you want to delete it and create a new one? (YES/NO)");
			command = in.nextLine();
			if (command.toUpperCase().equals("YES")) {
			    yn = 0;
			    Engine.playerDeck = new Stack<Card>();
			    Engine.chooseHero();
			}
			else if (command.toUpperCase().equals("NO"))
			    yn = 0;
			else {
			    Engine.clearConsole();
			    System.out.println("Invalid input!");
			    System.out.println();
			    Engine.clearConsole();
			}
		    }
		}
	    }
	    else  {
		Engine.clearConsole();
		System.out.println("Invalid command!");
	    }
	}
    }
	    
    public static void winCond () { 
	if (Engine.playerHero.health <= 0) {
	    System.out.println ("You have lost the match!"); 
	    Engine.reset();
	}
	else if (Engine.opponentHero.health <= 0) {
	    System.out.println ("You have won the match!");
	    Engine.reset();
	    }
	     
    }

    public static void main( String[] args ) {
	Hearthstone m = new Hearthstone();	
    }
}
