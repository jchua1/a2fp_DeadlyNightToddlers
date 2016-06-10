public class Card {
    
    public int manaCost, attack, health, clas;
    public boolean isDead = false; 
    public boolean isUsed = false;
    public String name = "";
    public String effect; 
    public String description = "";
    
    public String toString() {
	return name;
    }

    public void lowerHealth(int dmg) {
	health -= dmg;
	if (health < 0) {
	    health = 0;
	    isDead = true;
	}
    }

    public void direct(Card target) {
	target.lowerHealth(attack);
	lowerHealth(target.attack);
    }
}
