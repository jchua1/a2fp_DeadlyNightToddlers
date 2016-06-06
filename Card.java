public class Card {
    
    protected int manaCost;
    protected boolean isDead, isUsed;
    protected String name, effect;
    
    public String toString() {
	return name;
    }

    public int getCost() {
	return manaCost;
    }
}
