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
	addTo( display, convertString( "Opponent Hero: " + Engine.opponentHero.name ), 0 );
	addTo( display, convertString( "---------------------------------------------------------------------------" ), 5 );
	addTo( display, convertString( "Player Hero: " + Engine.playerHero.name ), 10 );

	String temp;
	temp = "Cards Left: " + Engine.opponentDeck.size() + " | Mana: " + Engine.opponentMana;
	addTo( display, convertString( temp ), 1 );

	temp = "";
	for( int i = 0; i < Engine.playerHand.size(); i++ ) {
	    temp += Engine.playerHand.get(i).toString() + " ";
	}
	temp += "Cards Left: " + Engine.playerDeck.size() + " | Mana: " + Engine.playerMana;
	addTo( display, convertString( temp ), 9 );
	
	temp = "Weapon: " + Engine.opponentWeapon.toString() 
	    + " Health: " + Engine.opponentHero.health + " | " 
	    + Engine.opponentHero.armor + " Armor";
	addTo( display, convertString( temp ), 2 );

	temp = "Weapon: " + Engine.playerWeapon.toString() 
	    + " Health: " + Engine.playerHero.health + " | " 
	    + Engine.playerHero.armor + " Armor";
	addTo( display, convertString( temp ), 8 );

	temp = "Minions: ";
	for( int i = 0; i < Engine.opponentMinions.size(); i++ ) {
	    if( temp.length() > display[0].length ) {
		addTo( display, convertString( temp.substring(0, temp.length() - Engine.opponentMinions.get(i).toString().length() - 1) ), 3 );
		temp = "";
	    }
	    temp += Engine.opponentMinions.get(i).toString() + " ";
	}
	if( temp.substring(0, 9).equals( "Minions: " ) ) 
	    addTo( display, convertString( temp ), 3 );
	else 
	    addTo( display, convertString( temp ), 4 );

	temp = "Minions: ";
	for( int i = 0; i < Engine.playerMinions.size(); i++ ) {
	    if( temp.length() > display[0].length ) {
		addTo( display, convertString( temp.substring(0, temp.length() - Engine.playerMinions.get(i).toString().length() - 1) ), 7 );
		temp = "";
	    }
	    temp += Engine.playerMinions.get(i).toString() + " ";
	}
	if( temp.substring(0, 9).equals( "Minions: " ) ) 
	    addTo( display, convertString( temp ), 6 );
	else 
	    addTo( display, convertString( temp ), 7 );
    }

}
