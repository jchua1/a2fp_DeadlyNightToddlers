public class Card {
    
    protected int manaCost, attack, health, clas;
    protected boolean isDead, isUsed;
    protected String name, effect;
    
    public String toString() {
	return name;
    }

    public int getCost() {
	return manaCost;
    }

    public int getClas() {
	return clas;
    }

    public String showDescription() {
    	return "";
    }
}
