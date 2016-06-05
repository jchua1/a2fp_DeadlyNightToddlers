public class Weapon extends Card {

    private String name, effect;
    private int health, attack;

    public Weapon(String n, int mana, int atk, int hp, String eff) {
	name = n;
	manaCost = mana;
	attack = atk;
	health = hp;
	effect = eff;
    }
}
