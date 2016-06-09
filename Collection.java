import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Collection {

    private ArrayList<Card> cards;
    public ArrayList<Card> display;
    public boolean filtered;
    
    public Collection() {
	cards = new ArrayList<Card>();
	display = cards;
	filtered = false;
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
		    Card x = new Minion(name,mana,attack,health,clas);
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
		    Card x = new Spell(name,mana,clas);
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
		    Card x = new Weapon(name,mana,attack,health,clas);
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
    
    public ArrayList<Card> filterCost(int mana) {
	filtered = true;
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
	return ret;
    }

    public ArrayList<Card> filterClass(int clas) {
	filtered = true;
	ArrayList<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    if (cards.get(i).clas == clas)
		ret.add(cards.get(i));
	}
	return ret;
    }

    public void showCards() {
	String ret = "";
	int clas = 0;
	for (int i = 0; i < display.size(); i++) {
	    if (display.get(i).clas == clas) {
		if (clas == 0)
		    ret += "Druid Cards:\n";
		else if (clas == 1)
		    ret += "\nHunter Cards:\n";
		else if (clas == 2)
		    ret += "\nMage Cards:\n";
		else if (clas == 3)
		    ret += "\nPaladin Cards:\n";
		else if (clas == 4)
		    ret += "\nPriest Cards:\n";
		else if (clas == 5)
		    ret += "\nRogue Cards:\n";
		else if (clas == 6)
		    ret += "\nShaman Cards:\n";
		else if (clas == 7)
		    ret += "\nWarlock Cards:\n";
		else if (clas == 8)
		    ret += "\nWarrior Cards:\n";
		else 
		    ret += "\nGeneral Cards:\n";
		clas++;
	    }
	    ret += display.get(i) + "\n";
	}
	System.out.println(ret);
    }

    public static void main(String[] args) {
        Collection x = new Collection();
	x.addMinions();
	x.addSpells();
	x.addWeapons();
	x.organize();
	System.out.println(x.cards);
	System.out.println(x.display);
	//System.out.println(x.filterCost(7));
	//System.out.println(x.filterClass(0));
    }
}
