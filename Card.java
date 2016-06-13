public class Card {
    
    public int manaCost, attack, health, clas, time;
    public boolean isDead = false; 
    public boolean isUsed = false;
    public String name, effect, description, type;
    
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
	if ((!(target instanceof Hero)) && (!(((Hero)target).armor > 0))) {
	    target.lowerHealth(attack);
	    lowerHealth(target.attack);
	}
	else {
	    int holder = ((Hero)target).armor; 
	    int holder2 = attack;
	    ((Hero) target).armor -= attack; 
	    holder2 -= holder;
	    if (((Hero)target).armor < 0) { 
		target.lowerHealth(holder2); 
		((Hero)target).armor = 0;
	    }
	}
    }

    public String getStats() {
	return "";
    }
}
