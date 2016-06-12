import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.io.*;

public class Engine {
    
    public static Collection playerCollection = new Collection();

    public static Stack<Card> playerDeck = new Stack<Card>();
    public static Stack<Card> opponentDeck = new Stack<Card>();

    public static ArrayList<Card> playerHand = new ArrayList<Card>();
    public static ArrayList<Card> opponentHand = new ArrayList<Card>();

    public static Hero playerHero = new Hero(0); 
    public static int playerMana = 1;
    public static Hero opponentHero = new Hero(1); 
    public static int opponentMana = 1;

    public static Card playerWeapon = new Card();
    public static Card opponentWeapon = new Card();

    public static ArrayList<Card> opponentMinions = new ArrayList<Card>();
    public static ArrayList<Card> playerMinions = new ArrayList<Card>();

    public static String defaultc = "\u001B[0m";
    public static String blackc = "\u001B[30m";
    public static String redc = "\u001B[31m"; //warrior
    public static String greenc = "\u001B[32m"; //druid
    public static String yellowc = "\u001B[33m"; //paladin
    public static String bluec = "\u001B[34m"; //mage
    public static String purplec = "\u001B[35m"; //warlock
    public static String cyanc = "\u001B[36m"; 
    public static String whitec = "\u001B[37m"; //priest

    public static String blueDarkc = "\u001B[1;34m";
    public static String redDarkc = "\u001B[1;31m"; 
    public static String greenDarkc = "\u001B[1;32m"; //hunter
    public static String yellowDarkc = "\u001B[1;33m";
    public static String magentaDarkc = "\u001B[1;35m"; //rogue
    public static String cyanDarkc = "\u001B[1;36m"; //shaman

    public static void printArray(Object[][] array) {
	for (Object[] f : array) {
	    for (Object s : f) {
		System.out.print(s);
	    }
	    System.out.println();
	}
    }

    public static void printArrayM(Object[][] array) {
	for (int f = 0; f < array.length; f++) {
	    for (int s = 0; s < array[0].length; s++) {
	        if ((array[f][s].toString()).equals("-")) {
		    System.out.print(yellowDarkc + array[f][s] + defaultc);
		}
		else if((array[f][s].toString()).equals(":")) {
		    System.out.print(blueDarkc + array[f][s] + defaultc);
		}
		else {
		    System.out.print(redc + array[f][s] + defaultc);
		}
	    }
	    System.out.println();
	}
    }

    public static void main( String args[] ) {
    	Graphics.refresh();
    	printArrayM( Graphics.display );
    }


    public static void move(String in) {

	//updateMazeGraphics();	
	if (in.toUpperCase().indexOf("PLACE") != -1) {
	    String str = in.substring(6);
	    Card c = getCard(str, playerHand);

	    if( c != null ) {
	    	playerMinions.add(c);
	    	playerHand.remove(c);
	    }
	    
	    else System.out.println("\nConfused? Enter ? or help for help.");
	}
	else if (in.toUpperCase().indexOf("DIRECT") != -1) {
	    String strlong = in.substring(7);
	    String name1 = strlong.substring(0, strlong.indexOf(" "));
	    String name2 = strlong.substring(strlong.indexOf(" ")+1);
	    Card caster = getCard(name1, playerMinions); Card dest = getCard(name2, opponentMinions);
			
	    if((caster == null) && (name1.equals(playerHero.toString())) ) caster = playerHero;
	    if((dest == null) && (name2.equals(opponentHero.toString())) ) dest = opponentHero;
	    
	    if( (caster != null) && (dest != null) )  dest.lowerHealth( caster.attack );
	    else System.out.println("\nConfused? Enter ? or help for help.");
	}
	else if( in.toUpperCase().indexOf("PEEP") != -1) {
	    String str = in.substring(5);
	    Card c = getCard(str, playerMinions);
	    if( c == null ) {
		c = getCard(str, opponentMinions);
		if( c == null ) {
		    c = getCard(str, playerHand);
		    if( (c == null) && (str.equals(playerHero.toString()) ) ) {
			c = playerHero;
			if( (c == null) && (str.equals(playerWeapon.toString())) ) {
			    c = playerWeapon;
			}
		    }
		}
	    }
	    if( c != null ) System.out.println( c.description );
	    else System.out.println("\nConfused? Enter ? or help for help.");
	}
	else if (in.toUpperCase().equals("END")) {
	    System.out.println ("Turn end!");
	}
	else if (in.toUpperCase().equals("POWER")) {
	    

	}
	else if (in.toUpperCase().equals("CONCEDE")) {
	    playerHero.health = 0;
	}
	else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
	    helpG();
	}
	else {
	    System.out.println("\nConfused? Enter ? or help for help.");
	}
	    
    }

    public static void useDefault() {
	try {
	    FileReader reader = new FileReader("Cards/Default.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    while ((line = br.readLine()) != null) {
		for (int i = 0; i < 2; i++)
		    playerDeck.push(getCard(line,playerCollection.cards));
	    }
	    reader.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void chooseHero() {
	Scanner in = new Scanner(System.in);
	clearConsole();
	int choice = -1;
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
		else if (heroChoice.toUpperCase().equals("help") || heroChoice.equals("?")) {
			helpD();
		}
	    else {
		Engine.clearConsole();
		System.out.println("Invalid choice! Confused? Enter ? or help for help.");
		System.out.println();
	    }
	}
	Engine.playerCollection.makeDeck(choice);
    }
	
    public static Card getCard( String str, ArrayList<Card> a ) {
	for( Card s : a ) {
	    if( s.toString().equals(str) ) 
		return s;
	}
	return null;
    }

    public final static void clearConsole(){
	System.out.print("\033[H\033[2J");  
	System.out.flush();  
    }

    //commands that can be used while on the home screen
    public static void helpD() {
	String s = ""; 
	s += "Current commands:\n";
	s += "Type the name of the class of the hero you want to play as."; 
	System.out.println(s);
    }

    public static void helpH(){ 
	String s = ""; 
	s += "Current commands:\n";
	s += "PLAY: Start a match!\n";
	s += "EXIT: Quit Game!\n"; 
	s += "MY COLLECTION: Check out your collection of cards!"; 
	System.out.println(s);
    }
    
    //commands that can be used while browsing the collection
    public static void helpC(){ 
	String s = "";
	s += "Current commands:\n"; 
	s += "NEXT: Check your next page of available cards.\n"; 
	s += "PREVIOUS: Check your previous page of available cards.\n"; 
	s += "EXIT: Exit your collection.\n"; 
	System.out.println (s);
    }

    //commands to be used while in game 
    public static void helpG(){
	String s = ""; 
	s += "PLACE <your card> \n"; 
	s += "DIRECT <your card> <some other card> \n"; 
	s += "PEEP <any card>: show the stats and description of any card! \n"; 
	s += "END: End your turn!\n"; 
	s += "POWER: Use your hero power!\n"; 
	s += "CONCEDE: Forfeit the match.\n"; 
	s += "SETTINGS: \n"; 
	System.out.println (s);
    }
    
    public static int calcTotalDmg( ArrayList<Card> a ) {
    	int t = 0;
    	for( Card c : a ) {
	    t += c.attack;
    	}
    	return t;
    }
    
    public static Card minionLeastHealth( ArrayList<Card> a ) {
   	Card min = a.get(0);
   	for( Card c : a ) {
	    if( min.health > c.health ) min = c;
   	}
   	return min;
    }
   
    public static Card minionMana( int m, ArrayList<Card> a ) {
   	Card minion = a.get(0);
   	if( minion.manaCost == m ) return minion;
   	
      	for( Card c : a ) {
	    if( (minion.manaCost < c.manaCost) && (c.manaCost <= m) ) minion = c;
   	}
   	return minion;
    }
    
    public static void aiMove() {
    	int dmg = calcTotalDmg( opponentMinions ) + opponentHero.attack;
    	if( dmg >= playerHero.health ) {
	    System.out.println( opponentHero.name + " and his minions attacked " + playerHero.name + " for " + dmg + ".");
	    playerHero.health = 0;
    	}

    	else {
	    if( (opponentMana > 2) || ((opponentMana <= 2) && (Math.random() < 0.5)) ) {
		Card c = minionMana( opponentMana, opponentMinions );
		opponentMinions.add(c);
		opponentHand.remove(c);
		opponentMana-=c.manaCost;
		System.out.println( opponentHero.name + " used " + c.name + " for " + c.manaCost + "." );
	    }
    		
	    while( dmg >=  minionLeastHealth( playerMinions ).health ) {
		Card c = minionLeastHealth( playerMinions );
		c.lowerHealth(dmg);
		playerMinions.remove(c);
		System.out.println( opponentHero.name + " defeated " + c.name + " with " + dmg + "!" );
	    }
    	}
    }
}
