import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

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
    public static String redc = "\u001B[31m";
    public static String greenc = "\u001B[32m";
    public static String yellowc = "\u001B[33m";
    public static String bluec = "\u001B[34m";
    public static String purplec = "\u001B[35m";
    public static String cyanc = "\u001B[36m";
    public static String whitec = "\u001B[37m";

    public static String blueDarkc = "\u001B[1;34m";
    public static String redDarkc = "\u001B[1;31m";
    public static String greenDarkc = "\u001B[1;32m";
    public static String yellowDarkc = "\u001B[1;33m";
    public static String magnetaDarkc = "\u001B[1;35m";
    public static String cyanDarkc = "\u001B[1;36m";

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

	    if( c != null ) playerMinions.add(c);
	    else System.out.println("\nConfused? Enter ? or help for help.");
	}
	else if (in.toUpperCase().indexOf("DIRECT") != -1) {
	    String strlong = in.substring(7);
	    String name1 = strlong.substring(0, strlong.indexOf(" "));
	    String name2 = strlong.substring(strlong.indexOf(" ")+1);
	    Card caster = getCard(name1, playerMinions); Card dest = getCard(name2, opponentMinions);
			
	    if((caster == null) && (name1.equals(playerHero.toString())) ) caster = playerHero;
	    if((dest == null) && (name2.equals(opponentHero.toString())) ) dest = opponentHero;
	    //if( (caster != null) && (dest != null) )  
	    //else System.out.println("\nConfused? Enter ? or help for help.");
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
    public static void helpH(){ 
	String s = ""; 
	s += "Current commands:\n";
	s += "PLAY: Start a match!\n";
	s += "EXIT: Quit Game!\n"; 
	s += "MY COLLECTION: Check out your collection of cards!"; 
	System.out.println (s);
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
	s += "PEEP <any card> \n"; 
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
    
    public static void aiMove() {
    	int dmg = calcTotalDmg( opponentMinions ) + opponentHero.attack;
    	if( dmg >= playerHero.health ) {
	    System.out.println( opponentHero.name + " and his minions attacked " + playerHero.name + " for " + dmg + ".");
    		playerHero.health = 0;
    	}
    	else {
    		//if( player )
     		while( dmg >=  minionLeastHealth( playerMinions ) ) {
     			Card c = minionLeastHealth( playerMinions )
    			c.lowerHealth(dmg);
    			playerMinions.remove(c);
    		}
    	}
    }
}
