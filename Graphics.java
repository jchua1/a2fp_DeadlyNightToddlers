public class Graphics {
   
    public static Object[][] inventory = new Object[9][31];
    public static String[][] stats = new String[5][31]; 
    public static String[][] monStats = new String[6][31];
    public static Object[][] graphics = new Object[15][31]; // Should not include monStats
    public static Object[][] equipped = new String[17][25];
    public static String[][] equipping = new String[17][25];
    public static Equipment[] avalEquips = new Equipment[5];
    public static String[][] settings = new String[17][25];
    public static String[][] helpScreen = (new Help()).helpScreen;
    public static String[][] attackinfo = (new Help()).attackinfo;
    public static Object[][] display;

    public static String[] convertString( String str ) { //to make array rows
	String[] retStr = new String[str.length()];
	for (int c = 0; c < str.length(); c ++) {
	    retStr[c] = str.substring(c,c+1);
	}
	    
	return retStr;
    }



    private static Object[][] eraseArray( Object[][] array ){
	return (new Object[array.length][array[0].length]);
    }

    private static String[][] eraseArray( String[][] array ){
	return (new String[array.length][array[0].length]);
    }

       

    private static void addTo(Object[][] loc, String[] array, int pos ) {
	for (int i = 0; i < array.length; i++) {
	    loc[pos][i] = array[i];
	}
    }

    public static void updateEquipped (Character character) {
	equipped = eraseArray(equipped);
	addTo(equipped, convertString("Equipped Items:"),0);
	addTo(equipped, convertString("Armor: "),2);
	addTo(equipped, convertString("Weapon: "),5);
	for (int i = 0; i < character.equipped.size(); i++) {
	    Equipment temp = character.equipped.get(i);
	    if (temp instanceof Armor) {
		addTo(equipped, convertString(temp.name), 3);
	    }
	    if (temp instanceof Weapon) {
		addTo(equipped, convertString(temp.name),6);
	    }
	}
	if (equipped[3][0] == null) {
	    addTo(equipped, convertString("None"),3);
	}
	if (equipped[6][0] == null) {
	    addTo(equipped, convertString("None"),6);
	}

	addTo(equipped, convertString("Back"),15);
    }

    public static void updateEquipping (Character character) {
	equipping = eraseArray(equipping);
	avalEquips = new Equipment [avalEquips.length];
	addTo(equipping, convertString("Available Equipment:"),0);
	addTo(equipping, convertString("Armors: "),2);
	addTo(equipping, convertString("Weapons: "),7);
	for (int i = 0; i < character.armors.size(); i++) {
	    Armor a = character.armors.get(i);
	    if (a instanceof Light) {
		avalEquips[0] = a;
		addTo(equipping, convertString(a.name), 3);
	    }

	    if (a instanceof Medium) {
		avalEquips[1] = a;
		addTo(equipping, convertString(a.name), 4);
	    }

	    if (a instanceof Heavy) {
		avalEquips[2] = a;
		addTo(equipping, convertString(a.name), 5);
	    }
	}

	for (int i = 0; i < character.sticks.size(); i++) {
	    Weapon a = character.sticks.get(i);
	    if (a instanceof Twig) {
		avalEquips[3] = a;
		addTo(equipping, convertString(a.name), 8);
	    }

	    if (a instanceof Sword) {
		avalEquips[4] = a;
		addTo(equipping, convertString(a.name), 9);
	    }
	}


	addTo(equipping, convertString("Back"),15);

    }

    public static void updateInventory ( Character character) {
	inventory = eraseArray(inventory);
	String[] armorT = convertString("Armors: ");
	addTo(inventory,armorT,0);

	addTo(inventory,convertString(character.armors.size() + "x"),1);


	addTo(inventory,convertString("Weapons: "),2);
	addTo(inventory,convertString(character.sticks.size() + "x"),3);

	addTo(inventory,convertString("Adrenaline: "),4);
	addTo(inventory,convertString(character.adren.size() + "x"),5);
	addTo(inventory,convertString( "Health Potions: "),6);
	addTo(inventory,convertString(character.healthdrinks.size() + "x"),7);

	}

    public static void updateStats ( Character character ) {
	stats = eraseArray(stats);
	String hp = "Hp = " + character.getHp();
	String speed = "Speed = "  + character.getSpeed();
	String damage = "Damage = " + character.getDamage();
	String luck = "Luck = " + character.getLuck();
	String money = "Money = " + character.getMoney();
	addTo(stats,convertString(hp),0);
	addTo(stats,convertString(speed),1);
	addTo(stats,convertString(damage),2);
	addTo(stats,convertString(luck),3);
	addTo(stats,convertString(money),4);
    }

    public static void updateMonStats ( Monster monster ) {
	monStats = eraseArray(monStats);
	String border = "";
	for (int i = 0; i < monStats[0].length; i++) {
	    border+="-";
	}
	String mon = "Monster: ";
	String name = "Name = " + monster.getName();
	String hp = "Hp = " + monster.getHp();
	String speed = "Speed = " + monster.getSpeed();
	String damage = "Damage = " + monster.getDamage();
	addTo(monStats,convertString(border),0);
	addTo(monStats,convertString(mon),1);
	addTo(monStats,convertString(name),2);
	addTo(monStats,convertString(hp),3);
	addTo(monStats,convertString(speed),4);
	addTo(monStats,convertString(damage),5);
    }

    public static void updateGraphics () {
	graphics = eraseArray(graphics);
	for( int i = 0; i < inventory.length; i++ ) {
	    for (int c = 0; c <inventory[i].length; c++) {
		graphics[i][c] = inventory[i][c];
	    }
	}
	int ctr = -1;
	for( int i = 0; i < stats.length; i++ ) {
	    for (int c = 0; c < stats[i].length; c++) {
		graphics[i + inventory.length][c] = stats[i][c];
	    }
	}

    }

    public static Object[][] displayMazeGraphics(Object[][] maze) {
	int size;
	if (graphics.length > maze.length) {
	    size = graphics.length;
	}
	else {
	    size = maze.length;
	}
	display = new Object[size][maze[0].length+graphics[0].length+2];

	for (int c = 0; c < maze.length; c ++) {
	    for (int i = 0; i < maze[c].length; i++) {
		display[c][i] = maze[c][i];
	    }
	    display[c][maze[0].length+1] = "|";
	}
	
	for (int c = 0; c < graphics.length; c ++) {
	    for (int i = 0; i < graphics[c].length; i++) {

		display[c][maze[0].length + i + 2] = graphics[c][i];
	    }
	}
	//remove nulls
	
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	return display;
    }

    public static Object[][] displayBattleGraphics( String[][] battleMap ){
	int size;
	if ((graphics.length + monStats.length) > battleMap.length) {
	    size = (graphics.length + monStats.length);
	}
	else {
	    size = battleMap.length;
	}
	display = new Object[size][battleMap[0].length + graphics[0].length + 2];

	for (int c = 0; c < battleMap.length; c ++) {
	    for (int i = 0; i < battleMap[c].length; i++) {
		display[c][i] = battleMap[c][i];
	    }
	}

	for (int c = 0; c < graphics.length; c ++) {
	    for (int i = 0; i < graphics[c].length; i++) {

		display[c][battleMap[0].length + i + 2] = graphics[c][i];
	    }
	    display[c][battleMap[0].length+1] = "|";
	}


	for (int c = 0; c < monStats.length; c++ ) {
	    for (int i = 0; i < monStats[c].length; i++) {
		display[c + graphics.length ][battleMap[0].length + i + 2] = monStats[c][i]; 
	    }
	    display[c+ graphics.length ][battleMap[0].length+1] = "|";
	}

	//remove nulls
	
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	return display;


    }

    public static Object[][] displayShopGraphics(Object[][] shop) {
	int size;
	if (graphics.length > shop.length) {
	    size = (graphics.length);
	}
	else {
	    size = shop.length;
	}
	display = new Object[size][shop[0].length+graphics[0].length + 2];
	

	for (int c = 0; c < shop.length; c ++) {
	    for (int i = 0; i < shop[c].length; i++) {
		display[c][i] = shop[c][i];
	    }
	}

	for (int c = 0; c < graphics.length; c ++) {
	    for (int i = 0; i < graphics[c].length; i++) {

		display[c][shop[0].length + i + 2] = graphics[c][i];
	    }
	    display[c][shop[0].length+1] = "|";
	}
	
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	return display;

    }

    public static Object[][] displayEquippedGraphics() {
	int size;
	if (graphics.length > equipped.length) {
	    size = graphics.length;
	}
	else {
	    size = equipped.length;
	}
	display = new Object[size][equipped[0].length+graphics[0].length+2];

	for (int c = 0; c < equipped.length; c ++) {
	    for (int i = 0; i < equipped[c].length; i++) {
		display[c][i] = equipped[c][i];
	    }
	}
	
	for (int c = 0; c < graphics.length; c ++) {
	    for (int i = 0; i < graphics[c].length; i++) {

		display[c][equipped[0].length + i + 2] = graphics[c][i];
	    }
	    display[c][equipped[0].length+1] = "|";
	}
	//remove nulls
	
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	return display;
    }


    public static Object[][] displaySettingsGraphics() {
	int size;
	if (graphics.length > settings.length) {
	    size = graphics.length;
	}
	else {
	    size = settings.length;
	}
	display = new Object[size][settings[0].length+graphics[0].length+2];

	addTo(settings,convertString("Settings:"),0);
	addTo(settings,convertString("1: Equip Items"),2);
	addTo(settings,convertString("2: Unequip Items"),4);
	addTo(settings,convertString("3: Check Equipped"),6);
	addTo(settings, convertString("Back"),15);

	for (int c = 0; c < settings.length; c ++) {
	    for (int i = 0; i < settings[c].length; i++) {
		display[c][i] = settings[c][i];
	    }
	}
	
	for (int c = 0; c < graphics.length; c ++) {
	    for (int i = 0; i < graphics[c].length; i++) {

		display[c][settings[0].length + i + 2] = graphics[c][i];
	    }
	    display[c][settings[0].length+1] = "|";
	}
	//remove nulls
	
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	return display;
    }

  public static Object[][] displayEquippingGraphics() {
	int size;
	if (graphics.length > equipping.length) {
	    size = graphics.length;
	}
	else {
	    size = equipping.length;
	}
	display = new Object[size][equipping[0].length+graphics[0].length+2];

	for (int c = 0; c < equipping.length; c ++) {
	    for (int i = 0; i < equipping[c].length; i++) {
		display[c][i] = equipping[c][i];
	    }
	}
	
	for (int c = 0; c < graphics.length; c ++) {
	    for (int i = 0; i < graphics[c].length; i++) {
		display[c][equipping[0].length + i + 2] = graphics[c][i];
	    }
	    display[c][equipping[0].length+1] = "|";
	}


	//remove nulls
	
	for (int c = 0; c < display.length; c++) {
	    for (int i = 0; i < display[c].length; i++) {
		if (display[c][i] == null) {
		    display[c][i] = " ";
		}
	    }
	}
	return display;
    }

    public static Object[][] displayHelpGraphics(String s) {
    String[][] array;

	if( s.toUpperCase().equals("A") ) {
		array = attackinfo;
	}

	else {
		array = helpScreen;
	}

	for (int c = 0; c < array.length; c++) {
	    for (int i = 0; i < array[c].length; i++) {
		if (array[c][i] == null) {
		    array[c][i] = " ";
		}
	    }
	}
	return array;
    }

}
