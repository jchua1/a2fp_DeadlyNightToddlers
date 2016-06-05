import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Collection {

    private ArrayList<Minion> minions;
    private ArrayList<Spell> spells;
    private ArrayList<Weapon> weapons;
    
    public Collection() {
	minions = new ArrayList<Minion>();
	spells = new ArrayList<Spell>();
	weapons = new ArrayList<Weapon>();
    }

    public void addMinions() {
	try {
	    FileReader reader = new FileReader("Cards/Minions.txt");
	    BufferedReader br = new BufferedReader(reader);
	    String line;
	    String name;
	    int mana;
	    int attack;
	    int health;
	    String effect;
	    while ((line = br.readLine()) != null) {
		if (line.equals("{")) {
		    name = br.readLine();
		    mana = Integer.parseInt(br.readLine());
		    attack = Integer.parseInt(br.readLine());
		    health = Integer.parseInt(br.readLine());
		    effect = br.readLine();
		    minions.add(new Minion(name,mana,attack,health,effect));
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
	    String effect;
	    while ((line = br.readLine()) != null) {
		if (line.equals("{")) {
		    name = br.readLine();
		    mana = Integer.parseInt(br.readLine());
		    effect = br.readLine();
		    spells.add(new Spell(name,mana,effect));
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
	    String effect;
	    while ((line = br.readLine()) != null) {
		if (line.equals("{")) {
		    name = br.readLine();
		    mana = Integer.parseInt(br.readLine());
		    attack = Integer.parseInt(br.readLine());
		    health = Integer.parseInt(br.readLine());
		    effect = br.readLine();
		    weapons.add(new Weapon(name,mana,attack,health,effect));
		}
	    }
	    reader.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }

    
    public static void main(String[] args) {
        Collection x = new Collection();
	x.addMinions();
	x.addSpells();
	x.addWeapons();
	System.out.println(x.minions);
	System.out.println(x.spells);
	System.out.println(x.weapons);
	System.out.println(x.minions.size() + x.spells.size() + x.weapons.size());
    }
}
