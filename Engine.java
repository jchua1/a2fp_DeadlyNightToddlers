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

    public static Hero playerHero;
    public static int pTurnMana = 1;
    public static int playerMana = 1;
    public static Hero opponentHero; 
    public static int oTurnMana = 1;
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
    public static String magentaDarkc = "\u001B[1;35m";
    public static String cyanDarkc = "\u001B[1;36m";


    public static boolean check(ArrayList<Card> a, String choice) {
	for (int i = 0; i < a.size(); i++)
	    if (a.get(i).name.toUpperCase().equals(choice.toUpperCase()))
		return true;
	return false;
    }


    public static void move(String input) {
	Scanner in = new Scanner(System.in);
	String choice = "";
	if (input.toUpperCase().equals("USE")) {
	    System.out.println("What card will you use?");
	    choice = in.nextLine();
	    if (check(playerHand,choice)) {
		Card c = getCard(choice,playerHand);
		if (c.manaCost <= pTurnMana) {
		    pTurnMana -= c.manaCost;
		    Engine.clearConsole();
		    System.out.println("You played " + c + "!");
		    playerMinions.add(c);
		    playerHand.remove(c);
		    System.out.println();
		}
		else {
		    Engine.clearConsole();
		    System.out.println("You don't have enough mana to play " + c + "!");
		    System.out.println();
		}
	    }
	    else {
		System.out.println("You don't have that card in your hand!");
	    }
	}
	else if (input.toUpperCase().equals("ATTACK")) {
	    System.out.println("What will you attack with?");
	    choice = in.nextLine();
	    if (check(playerMinions,choice)) {
		Card caster = getCard(choice,playerMinions);
		if (caster.time <= 0) {
		    System.out.println("What will you attack?");
		    choice = in.nextLine();
		    if (check(opponentMinions,choice)) {
			Card dest = getCard(choice,opponentMinions);
			caster.direct(dest);
			Engine.clearConsole();
			System.out.println("You attacked " + dest + " with " + caster + "!");
			System.out.println();
		    }
		    else {
			Engine.clearConsole();
			System.out.println("Your opponent does not have that card on the field!");
			System.out.println();
		    }
		}
		else {
		    Engine.clearConsole();
		    System.out.println("Give that minion a turn to get ready!");
		    System.out.println();
		}
	    }
	    else {
		System.out.println("You don't have that card on the field!");
	    }
	}
	else if(input.toUpperCase().equals("INFO")) {
	    System.out.println("Get the information of what card?");
	    choice = in.nextLine();
	    Engine.clearConsole();
	    if (check(playerMinions,choice)) {
		Card c = getCard(choice,playerMinions);
		System.out.println("Info for " + c + ": " + c.description);
		System.out.println();
	    }
	    else if (check(playerHand,choice)) {
		Card c = getCard(choice,playerHand);
		System.out.println("Info for " + c + ": " + c.description);
		System.out.println();
	    }
	    else if (check(opponentMinions,choice)) {
		Card c = getCard(choice,opponentMinions);
		System.out.println("Info for " + c + ": " + c.description);
		System.out.println();
	    }
	    else {
		System.out.println("That card is not in your hand or on the field!");
		System.out.println();
	    }
	}
	else if (input.toUpperCase().equals("END")) {
	    Engine.clearConsole();
	    System.out.println ("Turn end!");
	    System.out.println();
	}
	else if (input.toUpperCase().equals("POWER")) {
	    

	}
	else if (input.toUpperCase().equals("CONCEDE")) {
	    playerHero.health = 0;
	}
	else if (input.toUpperCase().equals("HELP") || in.equals("?")) {
	    helpG();
	}
	else {
	    Engine.clearConsole();
	    System.out.println("Confused? Enter ? or help for help.");
	    System.out.println();
	}
	checkBoard();
    }

    public static void checkBoard() {
	for (int i = 0; i < playerMinions.size(); i++) {
	    if (playerMinions.get(i).isDead)
		playerMinions.remove(i);
	}
	for (int i = 0; i < opponentMinions.size(); i++) {
	    if (opponentMinions.get(i).isDead)
		opponentMinions.remove(i);
	}
    }

    public static void checkMinions(ArrayList<Card> minions) {
	for (int i = 0; i < minions.size(); i++)
	    minions.get(i).time--;
    }

    public static void useDefault() {
	try {
	    FileReader reader = new FileReader("Cards/Test.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    while ((line = br.readLine()) != null) {
		for (int i = 0; i < 2; i++)
		    playerDeck.push(getCard(line,playerCollection.cards));
	    }
	    reader.close();
	    playerHero = new Hero(2);
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void oppDefault() {
	try {
	    FileReader reader = new FileReader("Cards/Test.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    while ((line = br.readLine()) != null) {
		for (int i = 0; i < 2; i++)
		    opponentDeck.push(getCard(line,playerCollection.cards));
	    }
	    reader.close();
	    opponentHero = new Hero(0);
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
		else if (heroChoice.toUpperCase().equals("HELP") || heroChoice.equals("?")) {
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
	    if( s.toString().toUpperCase().equals(str.toUpperCase()) ) 
		return s;
	}
	return null;
    }

    public void shuffle(Stack<Card> deck) {
     
    }

    public static void draw(ArrayList<Card> hand, Stack<Card> deck, int x) {
	for (int i = 0; i < x; i++)
	    hand.add(deck.pop());
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
	s += "MY COLLECTION: Check out your collection of cards!\n"; 
	s += "SHOW: Look at your current deck!\n"; 
	s += "USE DEFAULT: Use the default deck!\n";
	System.out.println(s);
    }
    
    //commands that can be used while browsing the collection
    public static void helpC(){ 
	String s = "";
	s += "Current commands:\n"; 
	s += "NEXT: Check your next page of available cards.\n"; 
	s += "PREVIOUS: Check your previous page of available cards.\n"; 
	s += "EXIT: Exit your collection.\n"; 
	s += "FILTER: Filters cards by mana cost.\n";
	s += "UNFILER: Returns to default display.\n";
	s += "CREATE DECK: Creates a new deck, can only have one saved deck at a time.\n";
	System.out.println (s);
    }

 
    //commands used while making a deck
    public static void helpCC () { 
	String s = ""; 
	s += "Current commands:\n"; 
	s += "NEXT: Check your next page of available cards.\n"; 
	s += "PREVIOUS: Check your previous page of available cards.\n";
	s += "ADD: Add card on the page into deck.\n"; 
	s += "DONE: Finish editing the deck (must have 30 cards).\n"; 
	s += "SIZE: Returns the size of deck you're currently editing.\n"; 
	s += "REMOVE: Remove a card from your deck.\n";
	System.out.println(s);
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
