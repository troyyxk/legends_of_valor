import java.util.ArrayList;

public class LegendsOfValor extends RPGGame {

    private boolean continueGaming;
    private Battle battle;
    private double battleChance;
    private MonsterGallary monsterGallary;
    private final int monster_spawn_round = 8;

    public LegendsOfValor() {
        super();
        continueGaming = true;

        battleChance = 0.2;

        this.setBoardHeight(8);
        this.setBoardWidth(8);
        this.setBoard();

        this.getBoard().getLoVStandardBoard();
        this.getBoard().drawBoard();

        this.monsterGallary = new MonsterGallary();
    }

    public int play() {
        boolean firstTime = true;
        int round_number = 0;
        while (continueGaming) {
            System.out.println("It's Team " + this.getCurTeamIdx() + " Player " + this.getCurPlayerIdx() + ", "
                    + this.getCurPlayer().getName() + "'s turn!");
            this.drawBoard();

            int[] curPos = this.getCurPlayer().getPos();
            // check if meet monster, escape first time
            if (!firstTime) {
                if (this.getBoard().getCell(curPos).isHostile() && Utils.getRandomBollean(battleChance)) {
                    battle = new Battle(this.getCurPlayer().getHeroObjects(), this.monsterGallary.getAllMonsterModels());
                    battle.start();
                }
            }
            firstTime = false;
            // check if is market

            // other options
            takeNormalOptions();


            this.moveTONextPlayer();
        }
        return -1;
    }


    public void takeNormalOptions() {
        int[] curPlayerPos = this.getCurPlayer().getPos();
        int[] newPos = new int[2];

        while (true) {
            System.out.println("Options: ");
            System.out.println("    [W]: Move Up");
            System.out.println("    [A]: Move Left");
            System.out.println("    [S]: Move Down");
            System.out.println("    [D]: Move Left");
            System.out.println("    [Q]: Quite Game");
            System.out.println("    [I]: Show Info");
            System.out.println("    [M]: Show Map");
            System.out.println("    [E]: Equip Weapon");
            System.out.println("    [R]: Equip Armory");
            System.out.println("    [T]: Use Potion");
            System.out.println("    [V]: Teleport");
            System.out.println("    [B]: Back to Nexus");
            if (this.getBoard().getCell(curPlayerPos).isNexus()) {
                System.out.println("-------In--Nexus---------");
                System.out.println("    [P]: Purchase");
            }

            ArrayList<Character> options = new ArrayList<Character>();
            options.add('W');
            options.add('A');
            options.add('S');
            options.add('D');
            options.add('Q');
            options.add('I');
            options.add('M');
            options.add('E');
            options.add('R');
            options.add('T');
            options.add('V');
            options.add('B');
            if (this.getBoard().getCell(curPlayerPos).isNexus()) {
                options.add('P');
            }

            char userInput = Utils.takeOptionInput(options);
            if (userInput == 'W') {
                newPos[0] = curPlayerPos[0] - 1;
                newPos[1] = curPlayerPos[1];
                if (curPlayerPos[0] == 0 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveMovable(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'A') { //move left
                newPos[0] = curPlayerPos[0];
                newPos[1] = curPlayerPos[1] - 1;
                if (curPlayerPos[1] == 0 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveMovable(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'S') { //move down
                newPos[0] = curPlayerPos[0] + 1;
                newPos[1] = curPlayerPos[1];
                if (curPlayerPos[0] == this.getBoardHeight() - 1 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveMovable(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'D') { //move right
                newPos[0] = curPlayerPos[0];
                newPos[1] = curPlayerPos[1] + 1;
                if (curPlayerPos[0] == this.getBoardWidth() || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveMovable(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'Q') { //quit
                System.out.println("Quit gaming!");
                continueGaming = false;
            }
            if (userInput == 'I') { //info
                System.out.println("Show info!");
                this.getCurPlayer().printInfo();
                continue;
            }
            if (userInput == 'M') { //map
                System.out.println("Show map!");
                this.drawBoard();
                continue;
            }
            if (userInput == 'E') { //equip weapon
                int i = 0;
                for (HeroObject heroObject : this.getCurPlayer().getHeroObjects()) {
                    if (heroObject.getStockWeapons().size() == 0) {
                        System.out.println("No stocked weapon.");
                        continue;
                    }
                    heroObject.getHeroObjectView().printWeapon(heroObject);
                    System.out.println("Hero number " + i + ", " + heroObject.getName() + ", Which weapon do you want to equip?");
                    int playerWeaponInput = Utils.takeIntInput(0, heroObject.getStockWeapons().size() - 1);
                    heroObject.putOnWeapon(playerWeaponInput);
                    System.out.println("Weapon successfully equipped!");
                    i++;
                }
                continue;
            }
            if (userInput == 'R') { //equip armor
                for (HeroObject heroObject : this.getCurPlayer().getHeroObjects()) {
                    if (heroObject.getStockArmory().size() == 0) {
                        System.out.println("No stocked armory.");
                        continue;
                    }
                    heroObject.getHeroObjectView().printArmory(heroObject);
                    System.out.println("Which armory do you want to equip?");
                    int playerArmoryInput = Utils.takeIntInput(0, heroObject.getStockArmory().size() - 1);
                    heroObject.putOnArmory(playerArmoryInput);
                    System.out.println("Armory successfully equipped!");
                }
                continue;
            }
            if (userInput == 'T') { //drink potion
                for (HeroObject heroObject : this.getCurPlayer().getHeroObjects()) {
                    if (heroObject.getStockPotion().size() == 0) {
                        System.out.println("No stocked potion.");
                        continue;
                    }
                    heroObject.getHeroObjectView().printPotion(heroObject);
                    System.out.println("Which potion do you want to use?");
                    int playerPotionInput = Utils.takeIntInput(0, heroObject.getStockPotion().size() - 1);
                    heroObject.takePotion(playerPotionInput);
                    System.out.println("Potion successfully used!");
                }
                continue;
            }
            if (userInput == 'P') { //go to market
                System.out.println("Start Shopping!");
                for (HeroObject heroObject : this.getCurPlayer().getHeroObjects()) {
                    this.getMarketController().startShopping(heroObject);
                }
                continue;
            }
            if (userInput == 'V') { //teleport
                System.out.println("Where do you want to teleport to? (X,Y)");
                newPos = Utils.takeCoordFromInput(); //gets a coord input that is within board range but not checked for other validities
                Cell tp = this.getBoard().getCell(newPos);
                if (!tp.isExplored() || !tp.isAccessible() || tp.isOccupied() || Math.abs(newPos[1]-curPlayerPos[1]) < 2) { //not a valid tp destination
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().moveMovable(this.getCurPlayer(), curPlayerPos, newPos);
                continue;
            }
            if (userInput == 'B') { //back to nexus
                newPos[0] = this.getBoardHeight()-1;
                newPos[1] = curPlayerPos[1];
                this.getBoard().moveMovable(this.getCurPlayer(), curPlayerPos, newPos);
            }
            return;
        }

    }

}
