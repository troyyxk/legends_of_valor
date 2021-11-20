import java.util.ArrayList;
import java.util.Random;

/**
 * Predefined class RPGGame
 * 
 *  Variables:
 * 
 *      Board board:
 * 
 *      int boardHeight:
 * 
 *      int boardWidth:
 * 
 *      int teamCount:
 * 
 *      int curTeamIdx:
 * 
 *      int curPlayerIndex:
 * 
 *      ArrayList<Team> teams:
 * 
 *      Player curPlayer:
 * 
 *      MarketController marketController: Hero actions in market 
 * 
 *      MonsterGallary monsterGallary: a database for three kinds of monster, loaded all monsters from local file 
 * 
 *      ArrayList<MonsterModel> allMonsterModel: a collection of all  individual monsters , extracted from "monsterGallary"
 * 
 *      HashMap<Integer, ArrayList<MonsterModel>> levelMonsterMap: a map of the format < level , [Monster 1, Monster 2, Monster 3 ...]>
 * 
 *      ArrayList<MonsterObject> monsterObjects: the monsters actually be put on the map
 * 
 *  Constructors:
 * 
 *      RPGGame():
 * 
 *  Methods:
 * 
 *      void initializePlayerPositions() :
 * 
 *      void getBoardHeightWidth():
 * 
 *      void setBoard():
 * 
 *      void drawBoard() :
 * 
 *      void addTeam() :
 * 
 *      void addMonsterObject(MonsterObject monsterObject):
 * 
 *      // getter and setter
 * 
 *      int getBoardHeight()
 * 
 *      ......
 * 
 *  class LegendsOfValor:
 * 
 *  Variables:
 * 
 *              boolean continueGaming:
 * 
 *              Battle battle:
 * 
 *              final int monster_spawn_round = 8:
 * 
 *              Integer monsterId: keep track of the id that will be given to the next spawn monster
 * 
 *  Constructors:
 *              
 *              LegendsOfValor():
 * 
 *                                  empty constructor
 * 
 *              initializePlayerPositions():
 * 
 *                                  to initialize a game put three heroes onto the game map 
 * 
 *              void spawnMonster():
 *          
 *                                  spawn three monsters at nexus
 * 
 *              boolean inRange(int[] aPos, int[] bPos):
 * 
 *                                  if aPos and bPos are in attack range of each other
 * 
 *              boolean playerHasMonsterInRange(Player player):
 * 
 *              boolean mosnterHasPlayerInRange(MonsterObject monsterObject):
 * 
 *              void moveMosnterAhead(MonsterObject monsterObject):
 * 
 *                                          Monster will only move forward on the lane it is born 
 * 
 *              int play():
 * 
 *                                       high level game while loop
 * 
 *              void takeNormalOptions():
 * 
 *                                      prompt the user to take user control command on terminal 
 */
public class LegendsOfValor extends RPGGame {

    private boolean continueGaming, reachedDest;
    private final int monster_spawn_round = 8;
    private Integer monsterId;

    public LegendsOfValor() {
        super();
        continueGaming = true;

        this.setBoardHeight(8);
        this.setBoardWidth(8);
        this.setBoard();

        this.getBoard().getLoVStandardBoard();
        this.getBoard().drawBoard();

        this.monsterId = 0;

        initializePlayerPositions();
    }

    //initalize the position of three heroes
    private void initializePlayerPositions() {
        // first hero
        this.getCurTeam().getPlayerAtIndex(0).setPos(new int[]{7, 0});
        this.getBoard().addHero(new int[]{7, 0}, this.getCurTeam().getPlayerAtIndex(0));

        // second hero
        this.getCurTeam().getPlayerAtIndex(1).setPos(new int[]{7, 3});
        this.getBoard().addHero(new int[]{7, 3}, this.getCurTeam().getPlayerAtIndex(1));

        // third hero
        this.getCurTeam().getPlayerAtIndex(2).setPos(new int[]{7, 6});
        this.getBoard().addHero(new int[]{7, 6}, this.getCurTeam().getPlayerAtIndex(2));
    }

    //randomly generates three monsters
    private void spawnMonster() {
        Random randomGenerator = new Random();
        ArrayList<MonsterModel> levelMonterModels;

        int curLevel;
        Player curPlayer = null;
        if (this.getCurTeam().getPlayers().size() > 0) {
            curPlayer = this.getCurTeam().getPlayers().get(0);
        }
        if (this.getCurTeam().getPlayers().size() > 0) {
            curPlayer = this.getCurTeam().getPlayers().get(0);
        }
        if (this.getCurTeam().getPlayers().size() > 0) {
            curPlayer = this.getCurTeam().getPlayers().get(0);
        }
        HeroObject heroObject = curPlayer.getHeroAtIndex(0);
        curLevel = heroObject.getLevel();
        
        MonsterModel curMonsterModel;
        ArrayList<int[]> monsterSpawnPos = new ArrayList<int[]>();
        //place monster in their respective nexus
        monsterSpawnPos.add(new int[]{0, 0});
        monsterSpawnPos.add(new int[]{0, 3});
        monsterSpawnPos.add(new int[]{0, 6});

        // assume 3 heroes
        for (int i = 0; i < 3; i++) {
            int[] curPos = monsterSpawnPos.get(i);
            if (this.getBoard().posHasNoMonster(curPos)) {

                levelMonterModels = this.getLevelMonsterMap().get(curLevel);
                curMonsterModel = levelMonterModels.get(randomGenerator.nextInt(levelMonterModels.size()));

                MonsterObject curMonsterObject = new MonsterObject(monsterId, curMonsterModel);
                monsterId++;

                this.getBoard().addMonsterObjectToPos(curMonsterObject, curPos);
                this.addMonsterObject(curMonsterObject);
            }
        }
    }

    //see if one position is adjacent or diagonal to another position
    public boolean inRange(int[] aPos, int[] bPos) {
        int aY = aPos[0];
        int aX = aPos[1];
        int bY = bPos[0];
        int bX = bPos[1];

        if ((aY - 1 == bY || aY + 1 == bY) && (aX == bX)) {
            return true;
        }
        if ((aX - 1 == bX || aX + 1 == bX) && (aY == bY)) {
            return true;
        }
        if ((aX - 1 == bX || aX + 1 == bX) && (aY - 1 == bY || aY + 1 == bY)) {
            return true;
        }
        if ((aY == bY) && (aX == bX)) {
            return true;
        }

        return false;
    }

    //check if a hero is next to a monster
    public boolean playerHasMonsterInRange(Player player) {
        for (MonsterObject monsterObject : this.getMonsterObjects()) {
            //loop through the monster list and compare it against the given hero
            if (inRange(monsterObject.getPos(), player.getPos())) {
                return true;
            }
        }
        return false;
    }

    //check if a monster is next to a hero
    public boolean mosnterHasPlayerInRange(MonsterObject monsterObject) {
        //loop through the hero list and compare it against the given monster
        for (Player player : this.getCurTeam().getPlayers()) {
            if (inRange(monsterObject.getPos(), player.getPos())) {
                return true;
            }
        }
        return false;
    }

    //monster goes down each round unless there's another monster blocks it, in which case it stays
    public void moveMosnterAhead(MonsterObject monsterObject) {
        int[] oldPos = monsterObject.getPos();
        int[] newPos = new int[]{oldPos[0] + 1, oldPos[1]};
        if (this.getBoard().getCell(newPos).hasMonster()) {
            return;
        }
        monsterObject.setPos(newPos);
        this.getBoard().moveMonster(monsterObject, oldPos, newPos);
    }

    //game starts
    public int play() {
        boolean firstTime = true;
        reachedDest = false;
        int round_number = 0;
        while (continueGaming) {
            System.out.println("--- Round " + round_number + " ---");
            // if at the monster spurning round, spurn monster
            if (round_number % monster_spawn_round == 0) {
                spawnMonster();
            }

            // revive heroes
            this.getCurTeam().reviseHeroes();
            for (Player player : this.getCurTeam().getPlayers()) {
                this.getBoard().setHero(player);
            }
            // hero's turn, takes in character as input and displays map
            int j = 0;
            for (Player player : this.getCurTeam().getPlayers()) {
                int i = player.getId();
                this.setCurPlayerIndex(j);
                System.out.println("It's Hero" + player.getId() + "'s turn!");
                this.drawBoard();
                if (this.getCurTeam().getReadyToRevisePlayers().size() > 0) {
                    System.out.print("<<< Hero will revive next round: ");
                    for (Player readyToRevisePlayer: this.getCurTeam().getReadyToRevisePlayers()) {
                        System.out.print(readyToRevisePlayer.getId());
                        System.out.print(" ");
                    }
                    System.out.println(" >>>");
                }

                // takes in options
                takeNormalOptions();
                j++;
                if(reachedDest) return 0; //return 0 if last W reaches monster nexus
            }

            // monster's turn
            // either attack or move forward and check win
            for (MonsterObject monsterObject : this.getMonsterObjects()) {
                // if able to attack
                if (mosnterHasPlayerInRange(monsterObject)) {
                    //loop through monsters to see if any of them is near a hero. attack if yes
                    for (Player player : this.getCurTeam().getPlayers()) {
                        if (inRange(player.getPos(), monsterObject.getPos())) { //if able to attack
                            HeroObject hero = player.getFirstHeroObject(); //get hero victim identity
                            new Offense(player, monsterObject, getBoard().getCell(player.getPos())).monsterAttacks(); //attack hero
                            if(hero.isFainted()){ // if faint, move hero back to nexus
                                this.getBoard().moveHero(player, player.getPos(), new int[]{getBoardHeight()-1, player.getId()*3});
                                this.getBoard().removeHero(new int[]{getBoardHeight()-1, player.getId()*3});

                                System.out.println("You fainted! You are back to your own starting nexus with full health within 1 round.");
                                hero.setHp(hero.getMaxHP());
                                this.getCurTeam().faintHero(player);
                            }

                            break;
                        }
                    }
                }
                // move the monster
                else {
                    moveMosnterAhead(monsterObject);
                    // if win
                    if (monsterObject.getPos()[0] >= this.getBoardHeight() - 1) {
                        this.drawBoard();
                        System.out.println("You lose!");
                        return -1;
                    }
                }

            }

            round_number++;
        }
        return -1;
    }

    //takes in an option from user
    public void takeNormalOptions() {
        int[] curPlayerPos = this.getCurPlayer().getPos();//set current coord
        int[] newPos = new int[2];//get ready for new coord

        while (true) { //display options
            System.out.println("Options: ");
            System.out.println("    [W]: Move Up");
            System.out.println("    [A]: Move Left");
            System.out.println("    [S]: Move Down");
            System.out.println("    [D]: Move Left");
            System.out.println("    [Q]: Quite Game");
            System.out.println("    [I]: Show Info");
            System.out.println("    [L]: Monster Information");
            System.out.println("    [M]: Show Map");
            System.out.println("    [E]: Equip Weapon");
            System.out.println("    [R]: Equip Armory");
            System.out.println("    [T]: Use Potion");
            System.out.println("    [V]: Teleport");
            System.out.println("    [B]: Back to Nexus");
            if (this.getBoard().getCell(curPlayerPos).isNexus()) {//only display below options if in nexus
                System.out.println("-------In--Nexus---------");
                System.out.println("    [P]: Purchase");
            }
            if (playerHasMonsterInRange(this.getCurPlayer())) {//only display below options if near monsters
                System.out.println("------Monster-In--Range---------");
                System.out.println("    [Y]: Attack");
                System.out.println("    [X]: Use Spell");
                System.out.println("    [G]: Show Nearby Monster Info");
            }

            //add valid options to a list to prevent invalid input
            ArrayList<Character> options = new ArrayList<>();
            options.add('W');
            options.add('A');
            options.add('S');
            options.add('D');
            options.add('Q');
            options.add('I');
            options.add('L');
            options.add('M');
            options.add('E');
            options.add('R');
            options.add('T');
            options.add('V');
            options.add('B');
            if (playerHasMonsterInRange(this.getCurPlayer())) { //only add below options as valid if near monster
                options.add('Y');
                options.add('X');
                options.add('G');
            }
            if (this.getBoard().getCell(curPlayerPos).isNexus()) { //only add below options as valid if in nexus
                options.add('P');
            }

            char userInput = Utils.takeOptionInput(options); //util class takes in input
            if (userInput == 'W') { //move up
                //check if entering an unoccupied monster nexus, win game if it does
                if(curPlayerPos[0] == 1 && !this.getBoard().getCell(new int[]{0, curPlayerPos[1]}).hasMonster()){
                    System.out.println("You won!");
                    reachedDest = true;
                    return;
                }
                newPos[0] = curPlayerPos[0] - 1;
                newPos[1] = curPlayerPos[1];
                //check if going beyond range
                if (curPlayerPos[0] == 0 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                // cannot move foward if near a monster
                boolean hasMonsterNearBy = false;
                for(MonsterObject mon: this.getMonsterObjects()){
                    if(inRange(this.getCurPlayer().getPos(), mon.getPos())){
                        System.out.println("You cannot move forward near a monster!");
                        hasMonsterNearBy = true;
                        continue;
                    }
                }

                if (!hasMonsterNearBy) {
                    this.getBoard().getCell(newPos).setExplored(true);
                    this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
                }
                else {
                    continue;
                }
            }
            if (userInput == 'A') { //move left
                newPos[0] = curPlayerPos[0];
                newPos[1] = curPlayerPos[1] - 1;
                if (curPlayerPos[1] == 0 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'S') { //move down
                newPos[0] = curPlayerPos[0] + 1;
                newPos[1] = curPlayerPos[1];
                if (curPlayerPos[0] == this.getBoardHeight() - 1 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'D') { //move right
                newPos[0] = curPlayerPos[0];
                newPos[1] = curPlayerPos[1] + 1;
                if (curPlayerPos[1] == this.getBoardWidth() - 1 || !this.getBoard().getCell(newPos).isAvailable()) {
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                this.getBoard().getCell(newPos).setExplored(true);
                this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
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
            if (userInput == 'L') { // monster info
                System.out.println("Show Monster info!");
                System.out.println("Monsters: ");
                for (MonsterObject monsterObject : this.getMonsterObjects()) {
                    monsterObject.printStatus();
                }
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
                System.out.println("Where do you want to teleport to? Input format: Y,X");
                newPos = Utils.takeCoordFromInput(); //gets a coord input that is within board range but not checked for other validities
                Cell tp = this.getBoard().getCell(newPos);
                if (!tp.isExplored() || !tp.isAccessible() || tp.isOccupied() || Math.abs(newPos[1]-curPlayerPos[1]) < 2) { //not a valid tp destination
                    System.out.println("Invalid access, enter a new option!");
                    continue;
                }
                System.out.println("Teleport successful!");
                this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'B') { //back to nexus
                System.out.println("You are back to available Nexus!");
                newPos[0] = this.getBoardHeight()-1;
                newPos[1] = curPlayerPos[1];
                while (!((this.getBoard().getCell(newPos).isAccessible()) && (!this.getBoard().getCell(newPos).hasHero()))) {
                    newPos[1] = newPos[1] + 1;
                    newPos[1] = newPos[1] % this.getBoardWidth();

                }
                this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
                this.getCurPlayer().getFirstHeroObject().setHp(this.getCurPlayer().getFirstHeroObject().getMaxHP());
            }
            if (userInput == 'Y') { //hero attack
                MonsterObject monsterTarget = this.getMonsterObjects().get(0); // the target monster involved in the fight
                for(MonsterObject mon: this.getMonsterObjects()) {
                    if (inRange(this.getCurPlayer().getPos(), mon.getPos())){
                        monsterTarget = mon; //know which monster to attack
                        break;
                    }
                }
                //hero attacks monster
                new Offense(this.getCurPlayer(), monsterTarget, getBoard().getCell(this.getCurPlayer().getPos())).heroAttacks();
                if(monsterTarget.isFainted()){
                    System.out.println("Monster fainted!");
                    //removed if died
                    removeMonster(monsterTarget);
                    heroGetsReward(this.getCurPlayer());
                }
            }
            if (userInput == 'X'){ //use spell
                //get hero and monster identity in spell casting
                HeroObject hero = this.getCurPlayer().getFirstHeroObject();
                MonsterObject monsterTarget = this.getMonsterObjects().get(0); // the target monster involved in the fight
                for(MonsterObject mon: this.getMonsterObjects()) {
                    if (inRange(this.getCurPlayer().getPos(), mon.getPos())){
                        monsterTarget = mon; //know which monster to attack
                        break;
                    }
                }
                if (this.getCurPlayer().getFirstHeroObject().getStockSpell().size() == 0) { //no spell to use
                    System.out.println("No stocked spell.");
                    continue;
                }
                hero.getHeroObjectView().printSpell(hero);
                System.out.println("Which spell do you want to use?");
                int playerSpellInput = Utils.takeIntInput(0, hero.getStockSpell().size() - 1);
                if (hero.getMana() < hero.getStockSpell().get(playerSpellInput).getManaCost()) {
                    System.out.println("Not enough mana");
                }
                //check if dodged the spell
                else if (Utils.getDodged(monsterTarget.getDodgePossiblity())) {
                    System.out.println("Monster dodges successfully!");
                }
                else { //spell not dodged
                    System.out.println("Spell successfully used!");
                    //special effects for three different types of spells
                    if (hero.getStockSpell().get(playerSpellInput).isFireSpell()) {
                        monsterTarget.setDefense((int) (monsterTarget.getDefense() * 0.9));
                    }
                    if (hero.getStockSpell().get(playerSpellInput).isIceSpell()) {
                        monsterTarget.setDamage((int) (monsterTarget.getDamage() * 0.9));
                    }
                    if (hero.getStockSpell().get(playerSpellInput).isLightningSpell()) {
                        monsterTarget.setDodgeChance((int) (monsterTarget.getDodgeChance() * 0.9));
                    }
                    int damage = hero.useSpell(playerSpellInput);
                    monsterTarget.takeHit(damage);
                    System.out.println("Monster take damage: " + damage + ", remaining HP: " + monsterTarget.getHP());
                    if (monsterTarget.isFainted()) {
                        System.out.println("Monster faints!");
                        //removed if died
                        removeMonster(monsterTarget);
                        heroGetsReward(this.getCurPlayer());
                    }
                    break;
                }
            }
            if(userInput =='G'){
                for(MonsterObject mon: this.getMonsterObjects()) {//loop thru monsters and diaplay their info
                    if (inRange(this.getCurPlayer().getPos(), mon.getPos())){
                        mon.printStatus();
                    }
                }
                continue;
            }
            return;
        }

    }

    //hero gets reward (money and exp) after killing a monster
    public void heroGetsReward(Player p){
        System.out.println("Hero " + p.getId() + " receives " + p.getFirstHeroObject().getLevel() * 100 + " gold, " + 2 + " experience.");
        p.getFirstHeroObject().addMoney(p.getFirstHeroObject().getLevel() * 100);
        p.getFirstHeroObject().addExp(2);
    }

    //remove monster from board and monster list
    public void removeMonster(MonsterObject monsterObject) {
        this.getBoard().getCell(monsterObject.getPos()).removeMonster();
        this.getMonsterObjects().remove(monsterObject);
    }

}
