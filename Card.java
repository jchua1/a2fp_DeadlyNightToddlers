public class Card {
    
    protected int manaCost, attack, health;
    protected boolean isDead, isUsed;
    protected String name = "";
    protected String effect, clas;
    
    public String toString() {
	return name;
    }

    public int getCost() {
	return manaCost;
    }

    public String showDescription() {
    	return "";
    }
}
