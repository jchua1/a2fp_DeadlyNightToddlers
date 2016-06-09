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

    public String toString() {
	return name;
    }
}
