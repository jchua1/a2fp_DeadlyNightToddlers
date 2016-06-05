public class Spell extends Card {
  
    private String name, effect;
  
    public Spell(String n, int mana, String eff) {
	name = n;
	manaCost = mana;
	effect = eff;
    }

}

