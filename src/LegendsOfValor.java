import java.util.ArrayList;
import java.util.Random;

public class LegendsOfValor extends RPGGame {

    private boolean continueGaming;
    private Battle battle;
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

    private void spawnMonster() {
        Random randomGenerator = new Random();
        ArrayList<MonsterModel> levelMonterModels;

        int curLevel;
        MonsterModel curMonsterModel;
        ArrayList<int[]> monsterSpawnPos = new ArrayList<int[]>();
        monsterSpawnPos.add(new int[]{0, 0});
        monsterSpawnPos.add(new int[]{0, 3});
        monsterSpawnPos.add(new int[]{0, 6});

        // assume 3 heroes
        for (int i = 0; i < 3; i++) {
            int[] curPos = monsterSpawnPos.get(i);
            if (this.getBoard().posHasNoMonster(curPos)) {
                Player curPlayer = this.getCurTeam().getPlayers().get(i);
                HeroObject heroObject = curPlayer.getHeroAtIndex(0);
                curLevel = heroObject.getLevel();
                levelMonterModels = this.getLevelMonsterMap().get(curLevel);
                curMonsterModel = levelMonterModels.get(randomGenerator.nextInt(levelMonterModels.size()));

                MonsterObject curMonsterObject = new MonsterObject(monsterId, curMonsterModel);
                monsterId++;

                this.getBoard().addMonsterObjectToPos(curMonsterObject, curPos);
                this.addMonsterObject(curMonsterObject);
            }
        }
    }

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

    public boolean playerHasMonsterInRange(Player player) {
        for (MonsterObject monsterObject : this.getMonsterObjects()) {
            if (inRange(monsterObject.getPos(), player.getPos())) {
                return true;
            }
        }
        return false;
    }

    public boolean mosnterHasPlayerInRange(MonsterObject monsterObject) {
        for (Player player : this.getCurTeam().getPlayers()) {
            if (inRange(monsterObject.getPos(), player.getPos())) {
                return true;
            }
        }
        return false;
    }

    public void moveMosnterAhead(MonsterObject monsterObject) {
        int[] oldPos = monsterObject.getPos();
        int[] newPos = new int[]{oldPos[0] + 1, oldPos[1]};
        if (this.getBoard().getCell(newPos).hasMonster()) {
            return;
        }
        monsterObject.setPos(newPos);
        this.getBoard().moveMonster(monsterObject, oldPos, newPos);
    }


    public int play() {
        boolean firstTime = true;
        int round_number = 0;
        while (continueGaming) {
            // if at the monster spurning round, spurn monster
            if (round_number % monster_spawn_round == 0) {
                spawnMonster();
            }
            // revise heroes
            this.getCurTeam().reviseHeroes();
            for (Player player : this.getCurTeam().getPlayers()) {
                this.getBoard().setHero(player);
            }

            // hero's turn
            int j = 0;
            for (Player player : this.getCurTeam().getPlayers()) {
                int i = player.getId();
                this.setCurPlayerIndex(j);
                System.out.println("It's Hero" + player.getId() + "'s turn!");
                this.drawBoard();
                if (this.getCurTeam().getReadyToRevisePlayers().size() > 0) {
                    System.out.print("<<< Hero will revise next round: ");
                    for (Player readyToRevisePlayer: this.getCurTeam().getReadyToRevisePlayers()) {
                        System.out.print(readyToRevisePlayer.getId());
                        System.out.print(" >>>");
                    }
                    System.out.println("");
                }

                // other options
                takeNormalOptions();
                j++;
            }

            // monster's turn
            // either attack or move forward and check win
            for (MonsterObject monsterObject : this.getMonsterObjects()) {
                // if able to attack
                if (mosnterHasPlayerInRange(monsterObject)) {
                    for (Player player : this.getCurTeam().getPlayers()) {
                        if (inRange(player.getPos(), monsterObject.getPos())) {
                            HeroObject hero = player.getFirstHeroObject();
//                            int damage = monsterObject.getDamage() - hero.getDamageReduction();
//                            System.out.println("Hero"+hero.getHeroIndex());
//                            hero.takeHit(damage);
//                            System.out.println("Hero " + player.getId() + " take damage: " + damage + ", remaining HP: " + hero.getHp());
//                            System.out.println("Hero"+hero.getHeroIndex());
                            new Offense(player, monsterObject, getBoard().getCell(player.getPos())).monsterAttacks();
                            if(hero.isFainted()){
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


    public void takeNormalOptions() {
        int[] curPlayerPos = this.getCurPlayer().getPos();
        int[] newPos = new int[2];

        while (true) {
            System.out.println("Options: ");
            // TODO, if monster inRange() and the in range monster at same line, cannot use W, move foward

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
            if (playerHasMonsterInRange(this.getCurPlayer())) {
                System.out.println("------MOnster-In--Range---------");
                System.out.println("    [Y]: Attack");
                System.out.println("    [X]: Use Spell");
                System.out.println("    [G]: Show Monster Info");
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
            if (playerHasMonsterInRange(this.getCurPlayer())) {
                options.add('Y');
                options.add('X');
                options.add('G');
            }
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
                if (curPlayerPos[0] == this.getBoardWidth() || !this.getBoard().getCell(newPos).isAvailable()) {
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
                System.out.println("Teleport successful!");
                this.getBoard().moveHero(this.getCurPlayer(), curPlayerPos, newPos);
            }
            if (userInput == 'B') { //back to nexus
                newPos[0] = this.getBoardHeight()-1;
                newPos[1] = curPlayerPos[1];
                System.out.println("You are back to your Nexus!");
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
                new Offense(this.getCurPlayer(), monsterTarget, getBoard().getCell(this.getCurPlayer().getPos())).heroAttacks();
                if(monsterTarget.isFainted()){
                    System.out.println("Monster fainted!");
                    //removed if died
                    removeMonster(monsterTarget);
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
                if (this.getCurPlayer().getFirstHeroObject().getStockSpell().size() == 0) {
                    System.out.println("No stocked spell.");
                    continue;
                }
                hero.getHeroObjectView().printSpell(hero);
                System.out.println("Which spell do you want to use?");
                int playerSpellInput = Utils.takeIntInput(0, hero.getStockSpell().size() - 1);
                if (hero.getMana() < hero.getStockSpell().get(playerSpellInput).getManaCost()) {
                    System.out.println("Not enough mana");
                }
                else if (Utils.getDodged(monsterTarget.getDodgePossiblity())) {
                    System.out.println("Monster dodges successfully!");
                }
                else { //spell not dodged
                    System.out.println("Spell successfully used!");
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
                    }
                    break;
                }
            }
            if(userInput =='G'){
                for(MonsterObject mon: this.getMonsterObjects()) {
                    if (inRange(this.getCurPlayer().getPos(), mon.getPos())){
                        mon.printStatus();
                    }
                }
                continue;
            }
            return;
        }

    }

    public void removeMonster(MonsterObject monsterObject) {
        this.getBoard().getCell(monsterObject.getPos()).removeMonster();
        this.getMonsterObjects().remove(monsterObject);
    }

}
