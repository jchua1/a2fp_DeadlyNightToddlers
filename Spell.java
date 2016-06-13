public class Spell extends Card {
  
    public Spell(String n, int mana, int cl, String t) {
	name = n;
	manaCost = mana;
	clas = cl;
	attack = -1;
	health = -1;
	type = t;
    }
}

