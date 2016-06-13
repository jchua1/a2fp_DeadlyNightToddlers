public class Minion extends Card {

    public Minion(String n, int mana, int atk, int hp, int cl, String t) {
	name = n;
	manaCost = mana;
	attack = atk;
	health = hp;
	clas = cl;
	type = t;
	time = 1;
    }

    public String getStats() {
	return "\nMana: " + manaCost + "\n"
	    + "Attack: " + attack + "\n"
	    + "Health: " + health;
    }
}
