public class Card {
    
    protected int manaCost, attack, health, clas;
    protected boolean isDead, isUsed;
    protected String name = "";
    protected String effect, description;
    
    public String toString() {
	return name;
    }

    public int getCost() {
	return manaCost;
    }

    public int getClas() {
	return clas;
    }

    public int getAttack() {
	return attack;
    }

    public void lowerHealth(int dmg) {
	health -= dmg;
	if (health < 0) 
	    health = 0;
	isDead = true;
    }

    public String showDescription() {
    	return description;
    }

    public void direct(Card target) {
	target.lowerHealth(attack);
	lowerHealth(target.getAttack());
    }
}
