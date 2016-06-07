import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Collection {

    private ArrayList<Card> cards;
    
    public Collection() {
	cards = new ArrayList<Card>();
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
    }

    public ArrayList<Card> merge(ArrayList<Card> a, ArrayList<Card> b) {
	ArrayList<Card> ret = new ArrayList<Card>();
	int i = 0;
	int ain = 0;
	int bin = 0;
	while (ain < a.size() && bin < b.size()) {
	    if (a.get(ain).getClas() < b.get(bin).getClas()) {
		ret.add(a.get(ain));
		ain++;
	    }
	    else if (a.get(ain).getClas() > b.get(bin).getClas()) {
		ret.add(b.get(bin));
		bin++;
	    }
	    else {
		if (a.get(ain).getCost() < b.get(bin).getCost()) {
		    ret.add(a.get(ain));
		    ain++;
		}
		else if (a.get(ain).getCost() > b.get(bin).getCost()) {
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
	ArrayList<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    if (mana == 7) {
		if (cards.get(i).getCost() >= 7)
		    ret.add(cards.get(i));
	    }
	    else {
		if (cards.get(i).getCost() == mana)
		    ret.add(cards.get(i));
	    }
	}
	return ret;
    }

    public ArrayList<Card> filterClass(int clas) {
	ArrayList<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < cards.size(); i++) {
	    if (cards.get(i).getClas() == clas)
		ret.add(cards.get(i));
	}
	return ret;
    }

    public static void main(String[] args) {
        Collection x = new Collection();
	x.addMinions();
	x.addSpells();
	x.addWeapons();
	x.organize();
	System.out.println(x.cards);
	System.out.println(x.filterCost(7));
	System.out.println(x.filterClass(0));
    }
}
