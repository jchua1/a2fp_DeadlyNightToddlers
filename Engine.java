import java.util.Scanner; 
public class Engine {
    
    public static Object[][] userMap; // One map for all
    public static Character character; // One character for all
    public static BattleMap battleMap;
    public static Shop shop = new Shop();
    public static Monster monster; // only one monster to exist at a time
    public static int stage = 1;


    public static String defaultc = "\u001B[0m";
    public static String blackc = "\u001B[30m";
    public static String redc = "\u001B[31m";
    public static String greenc = "\u001B[32m";
    public static String yellowc = "\u001B[33m";
    public static String bluec = "\u001B[34m";
    public static String purplec = "\u001B[35m";
    public static String cyanc = "\u001B[36m";
    public static String whitec = "\u001B[37m";

    public static void printArray(Object[][] array) {
	for (Object[] f : array) {
	    for (Object s : f) {
		System.out.print(s);
	    }
	    System.out.println();
	}
    }

    public static void printArrayM(Object[][] array) {
	for (int f = 0; f < array.length; f++) {
	    for (int s = 0; s < array[0].length; s++) {
	        if ((array[f][s].toString()).equals("*") && f < userMap.length && s < userMap[0].length) {
		    System.out.print(cyanc + array[f][s] + defaultc);
		}
		else if (((array[f][s].toString()).equals("-") || (array[f][s].toString()).equals("|")) && f < userMap.length && s < userMap[0].length) {
		    System.out.print(redc + array[f][s] + defaultc);
		}
		else if (((array[f][s].toString()).equals("+") || (array[f][s].toString()).equals("A") || (array[f][s].toString()).equals("&"))&& f < userMap.length && s < userMap[0].length) {
		    System.out.print(greenc + array[f][s] + defaultc);
		}
	        else if ((array[f][s].toString()).equals("e") && f < userMap.length && s < userMap[0].length) {
		    System.out.print(purplec + array[f][s] + defaultc);
		}
		else {
		    System.out.print(array[f][s]);
		}
	    }
	    System.out.println();
	}
    }

    public static void help() {
	Scanner in = new Scanner(System.in);
	String input = "";
	while (!input.toUpperCase().equals("BACK")) {
	    clearConsole();
	    if (input.toUpperCase().equals("ATTACKS")) {
	    	printArray(Graphics.displayHelpGraphics("A"));
	    }
	    else {
	    	printArray(Graphics.displayHelpGraphics("H"));
	    }

	    input = in.nextLine();
	}
    }

    public static void fillMap() {
	int mapSize;
	if (stage > 15) {
	    mapSize = 15;
	}
	else {
	    mapSize = 10 + stage;
	}
	MazeGen john = new MazeGen( mapSize, mapSize + 1);

	userMap = MazeGen.generate( john.maze );
	 
	userMap[1][1] = character;
	character.setCLocation(1);
	character.setRLocation(1);
    }
    
    public final static void clearConsole(){
	System.out.print("\033[H\033[2J");  
	System.out.flush();  
    }
    
    public final static void pressEnter() {
	System.out.println("Enter something to continue");
	Scanner con1 = new Scanner(System.in);
	String contin1 = con1.nextLine();
	if (contin1.toUpperCase().equals("HELP") || contin1.equals("?")) {
	    help();
	}
    }

    private static void updateMazeGraphics(){
	newLvl();
	clearConsole();
	Graphics.updateInventory(character);
	Graphics.updateStats(character);
	Graphics.updateGraphics();
    }

    public static void moveUp(int r, int c) {
	if (!(userMap[r-1][c]  instanceof Wall)) { //if not blocked by border
	    autoPickup( r-1, c );
	    userMap[r][c] = character.tileUnder;

	    if ( ((Floor) userMap[r-1][c]).hasMon() ) {
		character.inBattle = true;
		monster = ((Floor) userMap[r-1][c]).monster;
	    }

	   	userMap[r - 1][c] = character.appearance;
	    character.setRLocation(r - 1);
	}
	updateMazeGraphics();
    }

    public static void moveDown(int r, int c) {
	if (!(userMap[r+1][c]  instanceof Wall)) { //if not blocked by border
	    autoPickup( r+1, c );
	    userMap[r][c] = character.tileUnder;

	    if ( ((Floor) userMap[r+1][c]).hasMon() ) {
		character.inBattle = true;
		monster = ((Floor) userMap[r+1][c]).monster;
	    }

	    userMap[r + 1][c] = character.appearance;
	    character.setRLocation(r + 1);
	}
		updateMazeGraphics();
    }

    public static void moveLeft(int r, int c) {
	if (!(userMap[r][c-1]  instanceof Wall)) { //if not blocked by border
	    autoPickup( r, c-1 );
	    userMap[r][c] = character.tileUnder;

	    if ( ((Floor) userMap[r][c - 1]).hasMon() ) {
		character.inBattle = true;
		monster = ((Floor) userMap[r][c - 1]).monster;
	    }

	    userMap[r][c - 1] = character.appearance;
	    character.setCLocation(c - 1);
	}
	updateMazeGraphics();
    }
    
    public static void moveRight(int r, int c) {
	if (!(userMap[r][c+1] instanceof Wall)) { //if not blocked by border
	    autoPickup( r, c+1 );
	    userMap[r][c] = character.tileUnder;

	    if ( ((Floor) userMap[r][c + 1]).hasMon() ) {
		character.inBattle = true;
		monster = ((Floor) userMap[r][c + 1]).monster;
	    }

	    userMap[r][c + 1] = character.appearance;
	    character.setCLocation(c + 1);
	}
    	updateMazeGraphics();
    }

    public static boolean isEnd() {
	int r = character.getRLocation();
	int c = character.getCLocation();
	
	return (r == userMap.length - 2) && (c == userMap[r].length - 2);
    }

    public static void newLvl() { 
	if(isEnd()) { 
	    fillMap();
	    character.isShopping = true;
	    stage++;
	    while (character.isShopping) {
		shop();
	    }
	}
    }

    public static void autoPickup( int r, int c ) {
    	if( (userMap[r][c] instanceof Floor) && !((Floor) userMap[r][c]).item.name.equals( "None" ) ) {
	    character.pickup( ((Floor) userMap[r][c]).item );
    	}
    }

    public static void move() {

	Scanner input = new Scanner(System.in);
	updateMazeGraphics();
	printArrayM(Graphics.displayMazeGraphics(userMap));
	if (!(character.inBattle) && !(character.isShopping)) {	
	    String in = input.nextLine();
	    if (in.toUpperCase().equals("W")) { //FPS keys :D
		moveUp(character.getRLocation(), (character.getCLocation()));
	    }
	    else if (in.toUpperCase().equals("A")) {
		moveLeft(character.getRLocation(), (character.getCLocation()));
	    }
	    else if (in.toUpperCase().equals("S")) {
		moveDown(character.getRLocation(), (character.getCLocation()));
	    }
	    else if (in.toUpperCase().equals("D")) {
		moveRight(character.getRLocation(), (character.getCLocation()));
	    }
	    else if (in.toUpperCase().equals("DRINK")) {
		chooseDrink();
	    }
	    else if (in.toUpperCase().equals("SETTINGS")) {
		character.settingsMode = true;
		while (character.settingsMode) {
		    settings();
		}
	    }
	    else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
		help();
	    }
	    else {
	        System.out.println("\nConfused? Enter ? or help for help.");
		pressEnter();
	    }
	    
	}
    }
    
    public static void updateBattleGraphics() {
	battleMap = new BattleMap(character,monster);
	clearConsole();
	Graphics.updateInventory(character);
	Graphics.updateStats(character);
	Graphics.updateMonStats(monster);
	Graphics.updateGraphics();
	printArray(Graphics.displayBattleGraphics( battleMap.map ));
    }

    public static void battle() {
	while ( monster.hp > 0 && character.hp > 0 ) {
	    boolean moveChosen = false;
	    updateBattleGraphics();
	    character.printAttacks();
	    Scanner input = new Scanner(System.in);
	    String in = input.nextLine();
	    String attack = character.name + " did nothing.";
	    if (in.equals("1")){
		attack = character.name + " used " + character.attack1( monster, battleMap ) + ".";
		moveChosen = true;
	    }
	    else if (in.equals("2")){
		attack = character.name + " used " + character.attack2( monster, battleMap ) + ".";
		moveChosen = true;
	    }
	    else if (in.equals("3")){
		attack = character.name + " used " + character.attack3( monster, battleMap ) + ".";
		moveChosen = true;
	    }
	    else if (in.equals("4")){
		attack = character.name + " used " + character.attack4( monster, battleMap ) + ".";
		moveChosen = true;
	    }
	    else if (in.equals("5") || in.toUpperCase().equals("RUN")) { // Run Away
		if (Math.random() * character.getLuck() > 40 ) {
		    character.inBattle = false;
		    attack = character.name + " ran away sucessfully.";
		}
		else {
		    attack = character.name + " failed to run away.";
		}
		moveChosen = true;
	    }
	    else if (in.toUpperCase().equals("DRINK")) { // Use Items
		moveChosen = chooseDrink();
		if (moveChosen) {
		    attack = character.name + " used an item.";
		}
	    }
	    else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
		help();
	    }
	    else {
		attack += "\nConfused? Enter ? or help for help.";
	    }
	    updateBattleGraphics();
	    System.out.println(attack);

	    pressEnter();
	    if (!character.inBattle){ //ran away
		break;
	    }
	    
	    if (monster.hp > 0 && moveChosen) {
		int h = monster.attack(character, battleMap);
		updateBattleGraphics();
		System.out.println(monster.name + " did " + h + " hit-points.");
		pressEnter();
	    }
	    
	}
	
	character.inBattle = false;
    }

    public static void shop() {
	clearConsole();
	Graphics.updateInventory(character);
	Graphics.updateStats(character);
	Graphics.updateGraphics();
	printArray(Graphics.displayShopGraphics(shop.shop));
	Scanner input = new Scanner(System.in);
	String in = input.nextLine();
	if (in.toUpperCase().equals("EXIT")) {
	    character.isShopping = false;
	}
	else if (in.toUpperCase().equals("B1")) {
	    character.purchase(new HpPotion());
	}
	else if (in.toUpperCase().equals("B2")) {
	    character.purchase(new Adrenaline());
	}

	else if (in.toUpperCase().equals("B3")) {
	    character.purchase(new Light());
	}

	else if (in.toUpperCase().equals("B4")) {
	    character.purchase(new Medium());
	}

	else if (in.toUpperCase().equals("B5")) {
	    character.purchase(new Heavy());
	}

	else if (in.toUpperCase().equals("B6")) {
	    character.purchase(new Twig());
	}

	else if (in.toUpperCase().equals("B7")) {
	    character.purchase(new Sword());
	}
	else if (in.toUpperCase().equals("S1")) {
	    character.sell(new HpPotion());
	}
	else if (in.toUpperCase().equals("S2")) {
	    character.sell(new Adrenaline());
	}

	else if (in.toUpperCase().equals("S3")) {
	    character.sell(new Light());
	}

	else if (in.toUpperCase().equals("S4")) {
	    character.sell(new Medium());
	}

	else if (in.toUpperCase().equals("S5")) {
	    character.sell(new Heavy());
	}

	else if (in.toUpperCase().equals("S6")) {
	    character.sell(new Twig());
	}

	else if (in.toUpperCase().equals("S7")) {
	    character.sell(new Sword());
	}
	else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
	    help();
	}
	else {
	    System.out.println( "\nConfused? Enter ? or help for help.");
	    pressEnter();
	}
	
    }


    public static boolean chooseDrink() {
	boolean retBo = false;
	System.out.println("Choose a drink: ");
	System.out.println("1. HpPotion \t 2. Adrenaline");
	Scanner in = new Scanner(System.in);
	String input = in.nextLine();
	if (input.equals("1")) {
	    if (character.healthdrinks.size() == 0) {
		System.out.println(character.name + " does not have Hp Potions.");
		pressEnter();
	    }
	    else {
		System.out.println( character.name + " now has " + character.drink(character.healthdrinks.get(0)) + " health." );
		pressEnter();
		retBo = true;
	    }
	}
	else if (input.equals("2")) {
	    if (character.adren.size() == 0) {
		System.out.println(character.name + " does not have Adrenaline Potions.");
		pressEnter();
	    }
	    else {
		System.out.println( character.name + " now has " + character.drink(character.adren.get(0)) + " speed." );
		pressEnter();
		retBo = true;
	    }
	}
	return retBo;
    }

    public static boolean chooseEquipment() {
	boolean exitMode = false;
	while(!exitMode) {
	    clearConsole();
	    Graphics.updateInventory(character);
	    Graphics.updateStats(character);
	    Graphics.updateGraphics();
	    Graphics.updateEquipping(character);
	    printArray(Graphics.displayEquippingGraphics());
	    Scanner input = new Scanner(System.in);
	    String in = input.nextLine();
	    if (in.toUpperCase().equals("BACK") || in.toUpperCase().equals("EXIT")) {
		exitMode = true;
	    }
	    else if (Graphics.avalEquips[0] != null && in.toUpperCase().equals(Graphics.avalEquips[0].name.toUpperCase())) {
		character.equip( Graphics.avalEquips[0]);
	    }
	    else if (Graphics.avalEquips[1] != null && in.toUpperCase().equals(Graphics.avalEquips[1].name.toUpperCase())) {
		character.equip( Graphics.avalEquips[1]);
	    }
	    else if (Graphics.avalEquips[2] != null && in.toUpperCase().equals(Graphics.avalEquips[2].name.toUpperCase())) {
		character.equip( Graphics.avalEquips[2]);
	    }
	    else if (Graphics.avalEquips[3] != null && in.toUpperCase().equals(Graphics.avalEquips[3].name.toUpperCase())) {
		character.equip( Graphics.avalEquips[3]);
	    }
	    else if (Graphics.avalEquips[4] != null && in.toUpperCase().equals(Graphics.avalEquips[4].name.toUpperCase())) {
		character.equip( Graphics.avalEquips[4]);
	    }
	    else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
		help();
	    }
	    else if (!exitMode){
		System.out.println("\nConfused? Enter ? or help for help.");
		pressEnter();
	    }
	}
	return exitMode;
    }

 public static boolean removeEquipment() {
	boolean exitMode = false;
	while(!exitMode) {
	    clearConsole();
	    Graphics.updateInventory(character);
	    Graphics.updateStats(character);
	    Graphics.updateGraphics();
	    Graphics.updateEquipping(character);
	    Graphics.updateEquipped(character);
	    printArray(Graphics.displayEquippedGraphics());
	    Scanner input = new Scanner(System.in);
	    String in = input.nextLine();
	    if (in.toUpperCase().equals("BACK") || in.toUpperCase().equals("EXIT")) {
		exitMode = true;
	    }
	    else if (character.equipped.size() > 0 && in.toUpperCase().equals(character.equipped.get(0).name.toUpperCase())) {
		character.unequip(character.equipped.get(0));
	    }
	    else if (character.equipped.size() > 1 && in.toUpperCase().equals(character.equipped.get(1).name.toUpperCase())) {
		character.unequip(character.equipped.get(1));
	    }
	    else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
		help();
	    }
	    else if (!exitMode){
		System.out.println("\nConfused? Enter ? or help for help.");
		pressEnter();
	    }
	}
	return exitMode;
	
    }


    public static void settings() {
	boolean help = false; // to prevent extra help popup
	clearConsole();
	printArray(Graphics.displaySettingsGraphics());
	Scanner input = new Scanner(System.in);
	String in = input.nextLine();
	if (in.toUpperCase().equals("EXIT") || in.toUpperCase().equals("BACK")) {
	    character.settingsMode = false;
	}
	if (in.equals("1")) {
	    help = chooseEquipment();
	}
	if (in.equals("2")) {
	    help = removeEquipment();
	}
	if (in.equals("3")) {
	    help = checkEquipment();
	}
	else if (in.toUpperCase().equals("HELP") || in.equals("?")) {
	    help();
	}
	else if (character.settingsMode && !help){
	    System.out.println("\nConfused? Enter ? or help for help.");
	    pressEnter();
	}
    }

    public static boolean checkEquipment() {
	String in = "";
	while (!((in.toUpperCase().equals("EXIT") ^ in.toUpperCase().equals("BACK")))) {
	    Scanner esc = new Scanner(System.in);
	    clearConsole();
	    Graphics.updateStats(character);
	    Graphics.updateEquipped(character);
	    printArray(Graphics.displayEquippedGraphics());
	    in = esc.nextLine();
	}
	return true;
    }
    
}
