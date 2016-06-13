import java.util.*;

public class Graphics {

    public static String display = "";

    public static String getMinions(ArrayList<Card> minions) {
	String ret = "";
	for (int i = 0; i < minions.size(); i++) {
	    if (i < minions.size()-1)
		ret += minions.get(i) + ", ";
	    else
		ret += minions.get(i);
	}
	return ret;
    }

    public static void showBoard() {
	display = Engine.redc + "Opponent Hero: " + Engine.opponentHero.name + "\n"
	    + "Cards Left: " + Engine.opponentDeck.size() + " | " 
	    + "Mana: " + Engine.oTurnMana + "\n"
	    + "Weapon: " + Engine.opponentWeapon + " | " 
	    + "Health: " + Engine.opponentHero.health + " | " 
	    + "Armor : " + Engine.opponentHero.armor + "\n"
	    + "Opponent Minions:\n"
	    + Engine.blueDarkc + getMinions(Engine.opponentMinions) + "\n"
	    + Engine.yellowDarkc + "---------------------------------------------------------------------------\n"
	    + Engine.redc + "Your Minions:\n"
	    + Engine.blueDarkc + getMinions(Engine.playerMinions) + "\n"
	    + Engine.redc + "Weapon: " + Engine.playerWeapon + " | "
	    + "Health: " + Engine.playerHero.health + " | "
	    + "Armor: " + Engine.playerHero.armor + "\n"
	    + "Your Hand:\n"
	    + Engine.blueDarkc + getMinions(Engine.playerHand) + "\n"
	    + Engine.redc + "Cards Left: " + Engine.playerDeck.size() + " | "
	    + "Mana: " + Engine.pTurnMana + "\n"
	    + "Your Hero: " + Engine.playerHero.name + Engine.defaultc;
	System.out.println(display);
    }
    
}
