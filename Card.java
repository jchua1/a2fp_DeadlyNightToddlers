public class Card {
    
    public int manaCost, attack, health, clas;
    public boolean isDead, isUsed;
    public String name = "";
    public String effect; 
	public String description = "";
    
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
