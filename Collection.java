import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Collection {

    public ArrayList<Card> cards;
    public ArrayList<Card> display;
    
    public Collection() {
	cards = new ArrayList<Card>();
	display = cards;
    }

    public void addMinions() {
	try {
	    FileReader reader = new FileReader("Cards/Minions.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line, name;
	    int mana, attack, health, clas;
	    while ((line = br.readLine()) != null) {
		if (line.equals("{")) {
		    name = br.readLine();
		    mana = Integer.parseInt(br.readLine());
		    attack = Integer.parseInt(br.readLine());
		    health = Integer.parseInt(br.readLine());
		    clas = Integer.parseInt(br.readLine());	      
		    Card x = new Minion(name,mana,attack,health,clas,"Minion");
		    cards.add(x);
		}
	    }
	    reader.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void addSpells() {
	try {
	    FileReader reader = new FileReader("Cards/Spells.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    String name;
	    int mana;
	    int clas;
	    while ((line = br.readLine()) != null) {
		if (line.equals("{")) {
		    name = br.readLine();
		    mana = Integer.parseInt(br.readLine());
		    clas = Integer.parseInt(br.readLine());
		    Card x = new Spell(name,mana,clas,"Spell");
		    cards.add(x);
		}
	    }
	    reader.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void addWeapons() {
	try {
	    FileReader reader = new FileReader("Cards/Weapons.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    String name;
	    int mana;
	    int attack;
	    int health;
	    int clas;
	    while ((line = br.readLine()) != null) {
		if (line.equals("{")) {
		    name = br.readLine();
		    mana = Integer.parseInt(br.readLine());
		    attack = Integer.parseInt(br.readLine());
		    health = Integer.parseInt(br.readLine());
		    clas = Integer.parseInt(br.readLine());
		    Card x = new Weapon(name,mana,attack,health,clas,"Weapon");
		    cards.add(x);
		}
	    }
	    reader.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void organize() {
	cards = sort(cards);
	display = cards;
    }

    public ArrayList<Card> merge(ArrayList<Card> a, ArrayList<Card> b) {
	ArrayList<Card> ret = new ArrayList<Card>();
	int i = 0;
	int ain = 0;
	int bin = 0;
	while (ain < a.size() && bin < b.size()) {
	    if (a.get(ain).clas < b.get(bin).clas) {
		ret.add(a.get(ain));
		ain++;
	    }
	    else if (a.get(ain).clas > b.get(bin).clas) {
		ret.add(b.get(bin));
		bin++;
	    }
	    else {
		if (a.get(ain).manaCost < b.get(bin).manaCost) {
		    ret.add(a.get(ain));
		    ain++;
		}
		else if (a.get(ain).manaCost > b.get(bin).manaCost) {
		    ret.add(b.get(bin));
		    bin++;
		}
		else {
		    if (a.get(ain).toString().compareTo(b.get(bin).toString()) < 0) {
			ret.add(a.get(ain));
			ain++;
		    }
		    else {
			ret.add(b.get(bin));
			bin++;
		    }
		}
	    }
	    i++;
	}
	while (ain < a.size()) {
	    ret.add(a.get(ain));
	    ain++;
	    i++;
	}
	while (bin < b.size()) {
	    ret.add(b.get(bin));
	    bin++;
	    i++;
	}
	return ret;
    }

    public ArrayList<Card> sort(ArrayList<Card> arr) {
	if (arr.size() == 1)
	    return arr;
	else {
	    int half = arr.size()/2;
	    ArrayList<Card> a = new ArrayList<Card>();
	    ArrayList<Card> b = new ArrayList<Card>();
	    for (int i = 0; i < half; i++)
		a.add(arr.get(i));
	    for (int i = half; i < arr.size(); i++)
		b.add(arr.get(half+(i-half)));
	    return merge(sort(a),sort(b));
	}
    }
    
    public void filterCost(int mana) {
	ArrayList<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    if (mana == 7) {
		if (cards.get(i).manaCost >= 7)
		    ret.add(cards.get(i));
	    }
	    else {
		if (cards.get(i).manaCost == mana)
		    ret.add(cards.get(i));
	    }
	}
	display = ret;
    }

    public void filterClass(int clas) {
	ArrayList<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    if (cards.get(i).clas == clas || cards.get(i).clas == 9)
		ret.add(cards.get(i));
	}
	display = ret;
    }

    public void showCards(int p) {
	String ret = "";
	String c;
	int clas = display.get(p*6).clas;
	for (int i = p*6; i < display.size() && i < p*6+6; i++) {
	    if (display.get(i).clas == clas) {
		if (clas == 0)
		    ret += Engine.greenc + "Druid Cards:\n";	
		else if (clas == 1)
		    ret += Engine.redDarkc + "Hunter Cards:\n";
		else if (clas == 2) 
		    ret += Engine.blueDarkc + "Mage Cards:\n";
		else if (clas == 3) 
		    ret += Engine.yellowc + "Paladin Cards:\n";
		else if (clas == 4) 		    
		    ret += Engine.whitec + "Priest Cards:\n";
		else if (clas == 5)	    
		    ret += Engine.magentaDarkc + "Rogue Cards:\n";
		else if (clas == 6) 		    
		    ret += Engine.cyanDarkc + "Shaman Cards:\n";
		else if (clas == 7) 		    
		    ret += Engine.purplec + "Warlock Cards:\n";
		else if (clas == 8) 		    
		    ret += Engine.redc + "Warrior Cards:\n";
		else 
		    ret += Engine.defaultc + "General Cards:\n";
		clas = -1;
	    }
	    ret += display.get(i) + "\n";
	    if (i < display.size()-1
		&& display.get(i).clas != display.get(i+1).clas) {
		clas = display.get(i+1).clas;
		ret += "\n";
	    }
	}
	ret += Engine.defaultc + "\nPage: " + (p+1);
	System.out.println(ret);
    }

    public boolean checkPage(int p, String choice) {
	for (int i = p*6; i < display.size() && i < p*6+6; i++) {
	    if (display.get(i).name.toUpperCase().equals(choice.toUpperCase()))
		return true;
	}
	return false;
    }

    public Card getCard(int p, String choice) {
	for (int i = p*6; i < display.size() && i < p*6+6; i++) {
	    if (display.get(i).name.toUpperCase().equals(choice.toUpperCase()))
		return display.get(i);
	}
	return null;
    }

    public void removeCard(ArrayList<Card> deck, String choice) {
	for (int i = 0; i < deck.size(); i++) {
	    if (deck.get(i).name.toUpperCase().equals(choice.toUpperCase()))
		deck.remove(i);
	}
    }

    public int checkDeck(ArrayList<Card> deck, String choice) {
	int count = 0;
	for (int i = 0; i < deck.size(); i++) {
	    if (deck.get(i).name.toUpperCase().equals(choice.toUpperCase()))
		count++;
	}
	return count;
    }

    public void makeDeck(int heroChoice) {
	Engine.clearConsole();
	Scanner in = new Scanner(System.in);
	int p = 0;
	boolean makingDeck = true;
	String command = "";
	String choice = "";
	filterClass(heroChoice);
	ArrayList<Card> deck = new ArrayList<Card>();
	while (makingDeck) {
	    showCards(p);
	    System.out.println("What would you like to do?");
	    command = in.nextLine();
	    if (command.toUpperCase().equals("NEXT")) {
		Engine.clearConsole();
		if ((p+1)*6 < display.size())
		    p++;
		else {
		    System.out.println("You are on the last page!");
		    System.out.println();
		}
	    }
	    else if (command.toUpperCase().equals("PREVIOUS")) {
		Engine.clearConsole();
		if (p-1 >= 0)
		    p--;
		else {
		    System.out.println("You are on the first page!");
		    System.out.println();
		}
	    }
	    else if (command.toUpperCase().equals("ADD")) {
		if (deck.size() < 30) {
		    System.out.println("What card do you want to add?");
		    choice = in.nextLine();
		    Engine.clearConsole();
		    if (checkPage(p,choice)) {
			if (checkDeck(deck,choice) < 2) {
			    deck.add(getCard(p,choice));
			    System.out.println("Card added!");
			    System.out.println();
			}
			else {
			    System.out.println("You already have 2 copies of that card in your deck!");
			    System.out.println();
			}
		    }
		    else {
			System.out.println("Card does not exist or is not on this page!");
			System.out.println();
		    }
		}
		else {
		    Engine.clearConsole();
		    System.out.println("Deck limit reached!");
		    System.out.println();
		}
	    }
	    else if (command.toUpperCase().equals("DONE")
		     && deck.size() == 30) {
		if (deck.size() == 30) {
		    for (int i = 0; i < deck.size(); i++)
			Engine.playerDeck.push(deck.get(i));
		    Engine.clearConsole();
		    System.out.println("Deck created!");
		    System.out.println();
		    makingDeck = false;
		}
		else {
		    int yn = -1;
		    Engine.clearConsole();
		    while (yn != 0) {
			System.out.println("You do not yet have a complete deck of 30 cards. Would you like to exit? (YES/NO)");
			System.out.println("Note: This deck will not be saved.");
			choice = in.nextLine();
			if (choice.toUpperCase().equals("YES")) {
			    yn = 0;
			    makingDeck = false;
			}
			else if (choice.toUpperCase().equals("NO"))
			    yn = 0;
			else {
			    Engine.clearConsole();
			    System.out.println("Invalid input!");
			    System.out.println();
			}
			Engine.clearConsole();
		    }
		}
	    }
	    else if (command.toUpperCase().equals("SIZE")) {
		Engine.clearConsole();
		System.out.println("Size: " + deck.size() + " cards");
		System.out.println();
	    }
	    else if (command.toUpperCase().equals("REMOVE")) {
		if (deck.size() > 0) {
		    System.out.println("What card do you want to remove?");
		    choice = in.nextLine();
		    Engine.clearConsole();
		    if (checkDeck(deck,choice) > 0) {
			removeCard(deck,choice);
			System.out.println("Card removed!");
			System.out.println();
		    }
		    else {
			System.out.println("You don't have that card in your deck!");
			System.out.println();
		    }
		}
		
		else {
		    Engine.clearConsole();
		    System.out.println("Your deck is empty!");
		    System.out.println();
		}
	    }
	    else if (command.toUpperCase().equals("SHOW")) {
		Engine.clearConsole();
		System.out.println(deck);
		System.out.println();
	    }
	    else if (command.toUpperCase().equals("HELP") || 
		     command.equals("?")) { 
		Engine.clearConsole(); 
		Engine.helpCC(); 
	    }

	    else {
		Engine.clearConsole();
		System.out.println("Invalid command!");
		System.out.println();
	    }
	}
	display = cards;
    }

    public void unfilter() {
	display = cards;
    }
}
