public class Minion extends Card {

    private String name, effect;
    private int health, attack;

    public Minion(String n, int mana, int atk, int hp, String eff) {
	name = n;
	manaCost = mana;
	attack = atk;
	health = hp;
	effect = eff;
    }

}
