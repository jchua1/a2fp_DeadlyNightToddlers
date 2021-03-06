import java.util.*;
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

    //public static Card playerWeapon = new Card();
    //public static Card opponentWeapon = new Card();

    public static ArrayList<Card> opponentMinions = new ArrayList<Card>();
    public static ArrayList<Card> playerMinions = new ArrayList<Card>();

    public static int playerFatigue = 1;
    public static int opponentFatigue = 1;

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
		Card c = getCardC(choice,playerHand);
		if ((c.manaCost <= pTurnMana) && (c != null)) {
		    pTurnMana -= c.manaCost;
		    clearConsole();
		    System.out.println("You played " + c + "!");
		    System.out.println(c.getStats());
		    playerMinions.add(c);
		    playerHand.remove(getCard(choice,playerHand));
		    System.out.println();
		}
		else {
		    clearConsole();
		    System.out.println("You don't have enough mana to play " + c + "!");
		    System.out.println();
		}
	    }
	    else {
		System.out.println("You don't have that card in your hand!");
	    }
	}
	else if (input.toUpperCase().equals("ATTACK")) {
	    System.out.println("Attack with minion at which position? (1-7)");
	    int ch = in.nextInt();
	    in.nextLine();
	    if (ch <= playerMinions.size() && ch >= 1) {
		Card caster = playerMinions.get(ch-1);
		System.out.println(caster.hasAttacked);
		if (!caster.hasAttacked) {
		    if (caster.time <= 0) {
			System.out.println("Attack minion at which position? (0-7: Entering 0 attacks the enemy hero)");
			ch = in.nextInt();
			if (ch <= opponentMinions.size() && ch >= 0) {
			    caster.hasAttacked = true;
			    if (ch == 0) {
				opponentHero.lowerHealth(caster.attack);
				clearConsole();
				System.out.println("You attacked the enemy hero with " + caster + "!");
				System.out.println();
			    }
			    else {
				Card dest = opponentMinions.get(ch-1);
				caster.direct(dest);
				clearConsole();
				System.out.println("You attacked " + dest + " with " + caster + "!");
				System.out.println();
			    }
			}
			else {
			    clearConsole();
			    System.out.println("There is no minion at that position!");
			    System.out.println();
			}
		    }
		    else {
			clearConsole();
			System.out.println("Give that minion a turn to get ready!");
			System.out.println();
		    }
		}
		else {
		    clearConsole();
		    System.out.println("That minion has already attacked this turn!");
		    System.out.println();
		}
	    }
	    else {
		clearConsole();
		System.out.println("There is no minion at that position!");
		System.out.println();
	    }
	}
	else if(input.toUpperCase().equals("INFO")) {
	    System.out.println("Choose where to select a card from. (Hand, Minions, Opponent)");
	    choice = in.nextLine();
	    int ch;
	    if (choice.toUpperCase().equals("HAND")) {
	    try {
		System.out.println("Get the information of a card at which position? (1-10)");
		ch = in.nextInt();
		in.nextLine();
		if (ch <= playerHand.size() && ch >= 1) {
		    Card c = playerHand.get(ch-1);
		    clearConsole();
		    System.out.println("Info for " + c + ": " + c.getStats());
		    System.out.println();
		}
		else {
		    clearConsole();
		    System.out.println("There is no minion at that position!");
		    System.out.println();
		}
	    } catch(Exception e) { clearConsole();
		System.out.println("There is no minion at that position!");
		System.out.println(); }
	    }
	    else if (choice.toUpperCase().equals("MINIONS")) {
		System.out.println("Get the information of a card at which position? (1-7)");
		try {
		ch = in.nextInt();
		in.nextLine();
		if (ch <= playerMinions.size() && ch >= 1) {
		    Card c = playerMinions.get(ch-1);
		    clearConsole();
		    System.out.println("Info for " + c + ": " + c.getStats());
		    System.out.println();
		}
		else {
		    clearConsole();
		    System.out.println("There is no minion at that position!");
		    System.out.println();
		}
		} catch(Exception e) { clearConsole();
		    System.out.println("There is no minion at that position!");
		    System.out.println(); }
	    }
	    else if (choice.toUpperCase().equals("OPPONENT")) {
		System.out.println("Get the information of a card at which position? (1-7)");
		try {
		ch = in.nextInt();
		in.nextLine();
		if (ch <= opponentMinions.size() && ch >= 1) {
		    Card c = opponentMinions.get(ch-1);
		    clearConsole();
		    System.out.println("Info for " + c + ": " + c.getStats());
		    System.out.println();
		}
		else {
		    clearConsole();
		    System.out.println("There is no minion at that position!");
		    System.out.println();
		}
		} catch(Exception e) { 
		    clearConsole();
		    System.out.println("There is no minion at that position!");
		    System.out.println(); }
	    }
	    else {
		clearConsole();
		System.out.println("Invalid input!");
		System.out.println();
	    }
	}
	else if (input.toUpperCase().equals("END")) {
	    clearConsole();
	    System.out.println ("Turn end!");
	    System.out.println();
	    for (int i = 0; i < playerMinions.size(); i++)
		playerMinions.get(i).hasAttacked = false;
	}
	else if (input.toUpperCase().equals("POWER")) {
	    if (pTurnMana < 2) { 
		clearConsole();
		System.out.println("You don't have enough mana!"); 
		System.out.println();
	    }
	    else {
		playerHero.power();
		}
	}
	else if (input.toUpperCase().equals("CONCEDE")) {
	    playerHero.health = 0;
	}
	else if (input.toUpperCase().equals("HELP") || in.equals("?")) {
	    clearConsole();
	    helpG();
	    System.out.println();
	}
	else {
	    clearConsole();
	    System.out.println("Confused? Enter 'help' for help.");
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

    public static void mulligan() {
	if (Math.random() < 0.5) {
	    draw(playerHand,playerDeck,2);
	    draw(opponentHand,opponentDeck,3);
	    opponentMana = oTurnMana = 2;
	}
	else {
	    draw(playerHand,playerDeck,3);
	    draw(opponentHand,opponentDeck,2);
	    playerMana = pTurnMana = 2;
	}
    }
		    
    public static void checkMinions(ArrayList<Card> minions) {
	for (int i = 0; i < minions.size(); i++)
	    minions.get(i).time--;
    }

    public static void useDefault() {
	try {
	    FileReader reader = new FileReader("Cards/Default.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    while ((line = br.readLine()) != null) {
		for (int i = 0; i < 2; i++)
		    playerDeck.push(getCardC(line,playerCollection.cards));
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
	    FileReader reader = new FileReader("Cards/Default2.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    while ((line = br.readLine()) != null) {
		for (int i = 0; i < 2; i++)
		    opponentDeck.push(getCardC(line,playerCollection.cards));
	    }
	    reader.close();
	    opponentHero = new Hero(1);
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
		clearConsole();
		System.out.println("Invalid choice! Confused? Enter 'help' for help.");
		System.out.println();
	    }
	}
	Engine.playerCollection.makeDeck(choice);
    }

    public static Card getCard( String str, ArrayList<Card> a ) {
	for ( Card s : a ) {
	    if ( s.toString().toUpperCase().equals(str.toUpperCase()) )
		return s;
	}
	return null;
    }
	
    public static Card getCardC( String str, ArrayList<Card> a ) {
	for( Card s : a ) {
	    if( s.toString().toUpperCase().equals(str.toUpperCase()) ) {
		if (s.type.equals("Minion"))
		    return new Minion(s.name,s.manaCost,s.attack,s.health,s.clas,"Minion");
		else if (s.type.equals("Spell"))
		    return new Spell(s.name,s.manaCost,s.clas,"Spell");
		else
		    return new Weapon(s.name,s.manaCost,s.attack,s.health,s.clas,"Weapon");
	    }
	}
	return null;
    }

    public static void shuffle(Stack<Card> deck) {
	ArrayList<Card> temp = new ArrayList<Card>();
	while (!deck.empty())
	    temp.add(deck.pop());
	Collections.shuffle(temp);
	for (int i = 0; i < temp.size(); i++)
	    deck.push(temp.get(i));
    }

    public static void draw(ArrayList<Card> hand, Stack<Card> deck, int x) {
	for (int i = 0; i < x; i++) {
	    if (!deck.empty()) {
		if (hand.size() < 10)
		    hand.add(deck.pop());
		else
		    deck.pop();
	    }
	    else {
		if (hand == playerHand) {
		    playerHero.lowerHealth(playerFatigue);
		    System.out.println("You took " + playerFatigue + " damage from fatigue!");
		    playerFatigue++;
		}
		else {
		    opponentHero.lowerHealth(opponentFatigue);
		    System.out.println("Opponent took " + opponentFatigue + " damage from fatigue!");
		    opponentFatigue++;
		}
	    }
	}
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
	s += "DONE: Finish editing the deck.\n"; 
	s += "SIZE: Returns the size of deck you're currently editing.\n"; 
	s += "REMOVE: Remove a card from your deck.\n";
	System.out.println(s);
    }
    
    //commands to be used while in game 
    public static void helpG(){
	String s = ""; 
	s += "USE: Use a card. \n"; 
	s += "ATTACK: Attack the opponent with one of your minions.\n"; 
	s += "INFO: Gives you information about a card.\n";
	s += "END: End your turn!\n"; 
	s += "POWER: Use your hero power!\n"; 
	s += "CONCEDE: Forfeit the match.\n";
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

    	ArrayList<Card> minionNotused = copyArraylist( opponentMinions );

	    while( playerMinions.size() > 0 && calcTotalDmg( minionNotused ) >=  minionLeastHealth( playerMinions ).health ) {
		Card c = minionLeastHealth( playerMinions );

		ArrayList<Card> usedMinions = optimalMinions( c.health, minionNotused );
		takeDmg( c, usedMinions );

		minionNotused = notUsed();

		playerMinions.remove(c);
		
		int n = 0;
		while( n < opponentMinions.size() ) {
			if( opponentMinions.get(n).health <= 0 )
				opponentMinions.remove(n);
			else
				n++;
		}

		System.out.println( opponentHero.name + "'s minions attacked " + c.name + " with " + dmg + " damage!" );
	    }

	    checkMinions( opponentMinions );

	    if( (opponentMana > 2) || ((opponentMana <= 2) && (Math.random() < 0.5)) ) {
		Card c = minionMana( opponentMana, opponentHand );
		opponentMinions.add(c);
		opponentHand.remove(c);
		opponentMana-=c.manaCost;
		System.out.println( opponentHero.name + " played " + c.name + "." );
	    }

	    if( playerMinions.size() == 0 ) {
	    	dmg = calcTotalDmg( minionNotused ) + opponentHero.attack;
	    	if( dmg != 0 ) {
	    		playerHero.lowerHealth( dmg );
	    		System.out.println( opponentHero.name + " and " + opponentHero.name + "'s minions attacked " + playerHero.name + " with " + dmg + " damage!" );
	    	}
	    }

    	}
    }

    public static void takeDmg( Card c, ArrayList<Card> usedCards ) {
    	for( int i = 0; i < opponentMinions.size(); i++ ) {
    		int n = 0;
    		while( n < usedCards.size() ) {
    			if( usedCards.get(n).name.equals( opponentMinions.get(i).name ) ) {
    				opponentMinions.get(i).health -= c.attack;
    				opponentMinions.get(i).time = 1;
    				usedCards.remove(n);
    			}
    			else {
    				n++;
    			}
    		}
    	}
    }

    public static ArrayList<Card> copyArraylist( ArrayList<Card> a ) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for( Card c : a ) { copy.add(c); }
    	return copy;
    }

    public static ArrayList<Card> notUsed() {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for( Card c : opponentMinions ) {
    		if( c.time <= 0 ) {
    			copy.add(c);
    		}
    	}
    	return copy;
    }
/*
    public static ArrayList<Card> deleteDups( ArrayList<Card> orig, ArrayList<Card> dup ) {
    	for(int i = 0; i < orig.size(); i++) {
    		int n = 0;
    		while( n < dup.size() ) {
    			if( orig.get(i).name.equals( dup.get(n).name ) ){
    				dup.remove(n);
    				break;
    			}
    			else {
    				n++;
    			}
    		}
    	}
    	return dup;
    }
*/
    public static ArrayList<Card> optimalMinions( int health, ArrayList<Card> a ) {
    	for( int i = 0; i < a.size(); i++ ) {
    		ArrayList<Card> copy = copyArraylist( a );
    		copy.remove(i);
    		if( possibleKill( health, copy ) ) return optimalMinions( health, copy );
    	}

    	return a;
    }

    public static boolean possibleKill( int health, ArrayList<Card> a ) {
    	return calcTotalDmg( a ) >= health;
    }

    public static void reset() {
    	System.exit(0);
    }
}
