import java.util.*;

public class Hero extends Card {
    public int armor, power;

    public Hero(int n) {
	if (n == 0) {
	    name = "Druid";
	    description = "+1 Attack this turn. +1 Armor.";
	}
	else if (n == 1) {
	    name = "Hunter";
	    description = "Deal 2 damage to the enemy hero.";
	}
	else if (n == 2) {
	    name = "Mage";
	    description = "Deal 1 damage.";
	}
	else if (n == 3) {
	    name = "Paladin";
	    description = "Summon a 1/1 Silver Hand Recruit.";
	}
	else if (n == 4) {
	    name = "Priest";
	    description = "Restore 2 health.";
	}
	else if (n == 5) {
	    name = "Rogue";
	    description = "Equip a 1/2 Dagger.";
	}
	else if (n == 6) {
	    name = "Shaman";
	    description = "Summon a random Totem.";
	}
	else if (n == 7) {
	    name = "Warlock";
	    description = "Draw a card and take 2 damage.";
	}
	else if (n == 8) {
	    name = "Warrior";
	    description = "Gain 2 armor.";
	}
	power = n;
	health = 30;
    }	

    public void power () { 
	Engine.pTurnMana -= 2;
	if (name.equals("Druid")) {
	    Engine.playerHero.armor += 1; 
	    Engine.playerHero.attack += 1;
	}
	else if (name.equals("Hunter")) {
	    Engine.opponentHero.health -= 2; 
	}
	else if (name.equals("Mage")) { 
	    System.out.println("Attack minion at which position? (0-7 : Entering 0 attacks the enemy hero)");
	    Scanner in = new Scanner( System.in );	
	    int choice = in.nextInt();
	    in.nextLine();
	    if (choice == 0) { 
		Engine.opponentHero.lowerHealth(1); 
	    }
	    else { 
		Card dest = Engine.opponentMinions.get(choice-1);
		dest.lowerHealth(1); 
	    }
	}
	else if (name.equals("Priest")) { 
	    Engine.playerHero.health += 2; 
	}
	else if (name.equals("Warrior")) { 
	    Engine.playerHero.armor += 2; 
	}
	Engine.clearConsole();
	System.out.println ("Hero power has been used!");
    }
	    
    public String toString() {
	return name;
    }
}
