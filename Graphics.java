public class Graphics {

    public static Object[][] display = new Object[11][75];

    public static String[] convertString( String str ) {
	String[] retStr = new String[str.length()];
	for (int c = 0; c < str.length(); c ++) {
	    retStr[c] = str.substring(c,c+1);
	}
	    
	return retStr;
    }

    public static Object[][] eraseArray( Object[][] array ){
	return (new Object[array.length][array[0].length]);
    }
      
    public static void addTo(Object[][] loc, String[] array, int pos ) {
	for (int i = 0; i < array.length; i++) {
	    loc[pos][i] = array[i];
	}
    }

    public static void refresh() {
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	AddCardDisp();
    }

    public static void AddCardDisp() {
	addTo( display, convertString( "Opponent: Mr. Kappa" ), 0 );
	addTo( display, convertString( "---------------------------------------------------------------------------" ), 5 );
	addTo( display, convertString( "Player: Dat Boi" ), 6 );

	String s = "Hand: "; String temp = s;

	for( int i = 0; i < Engine.opponentHand.size(); i++ ) {
	    temp += Engine.opponentHand.get(i).toString() + " ";
	}
	temp += "Cards Left: " + Engine.opponentDeck.size() + " Mana: " + Engine.opponentMana;

	addTo( display, convertString( temp ), 1 );

	temp = s;

	for( int i = 0; i < Engine.playerHand.size(); i++ ) {
	    temp += Engine.playerHand.get(i).toString() + " ";
	}
	temp += "Cards Left: " + Engine.playerDeck.size() + " Mana: " + Engine.playerMana;

	addTo( display, convertString( temp ), 10 );

	s = "Weapon: "; temp = s;
	temp += Engine.opponentWeapon.toString() + " Hero: " + Engine.opponentHero.toString() + " Health: " + 
	    Engine.opponentHero.health + " | " + Engine.opponentHero.armor + " Armor  Power: " + Engine.opponentHero.power;

	addTo( display, convertString( temp ), 2 );

	temp = s;
	temp += Engine.playerWeapon.toString() + " Hero: " + Engine.playerHero.toString() + " Health: " + 
	    Engine.playerHero.health + " | " + Engine.playerHero.armor + " Armor  Power: " + Engine.playerHero.power;

	addTo( display, convertString( temp ), 9 );

	s = "Minions: "; temp = s;
	for( int i = 0; i < Engine.opponentMinions.size(); i++ ) {
	    if( temp.length() > display[0].length ) {
		addTo( display, convertString( temp.substring(0, temp.length() - Engine.opponentMinions.get(i).toString().length() - 1) ), 3 );
		temp = "";
	    }
	    temp += Engine.opponentMinions.get(i).toString() + " ";
	}
	if( temp.substring(0, 9).equals( s ) ) addTo( display, convertString( temp ), 3 );
	else addTo( display, convertString( temp ), 4 );

	temp = s;
	for( int i = 0; i < Engine.playerMinions.size(); i++ ) {
	    if( temp.length() > display[0].length ) {
		addTo( display, convertString( temp.substring(0, temp.length() - Engine.playerMinions.get(i).toString().length() - 1) ), 7 );
		temp = "";
	    }
	    temp += Engine.playerMinions.get(i).toString() + " ";
	}
	if( temp.substring(0, 9).equals( s ) ) addTo( display, convertString( temp ), 7 );
	else addTo( display, convertString( temp ), 8 );
    }

}
