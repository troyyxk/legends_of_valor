import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Battle {
    private ArrayList<HeroObject> heroObjects;
    private ArrayList<MonsterModel> allMonsterModels;
    private HashMap<Integer, ArrayList<MonsterModel>> levelMonsterMap;

    private ArrayList<int[]> hpManaBeforeBattle;

    private ArrayList<MonsterObject> monsterObjects;

    public Battle(ArrayList<HeroObject> heroObjects, ArrayList<MonsterModel> allMonsterModels) {
        this.heroObjects = heroObjects;
        this.allMonsterModels = allMonsterModels;
        this.levelMonsterMap = getLevelMonsterMap();

        this.monsterObjects = getMonsterTeam();

        this.hpManaBeforeBattle = new ArrayList<int[]>();
        for (HeroObject heroObject : this.heroObjects) {
            int[] curHpMana = new int[2];
            curHpMana[0] = heroObject.getHP();
            curHpMana[1] = heroObject.getMana();
            this.hpManaBeforeBattle.add(curHpMana);
        }

    }

    //region constructor
    // add the monster that will face the heroes, each of them are of same level as the hero
    private ArrayList<MonsterObject> getMonsterTeam() {
        Random randomGenerator = new Random();
        ArrayList<MonsterObject> monsterTeam = new ArrayList<MonsterObject>();
        ArrayList<MonsterModel> levelMonterModels;

        int curLevel;
        MonsterModel curMonsterModel;
        for (HeroObject heroObject : this.heroObjects) {
            curLevel = heroObject.getLevel();
            levelMonterModels = this.levelMonsterMap.get(curLevel);
            curMonsterModel = levelMonterModels.get(randomGenerator.nextInt(levelMonterModels.size()));
            monsterTeam.add(new MonsterObject(curMonsterModel));
        }

        return monsterTeam;
    }

    // get a map level to monsters with the level
    private HashMap<Integer, ArrayList<MonsterModel>> getLevelMonsterMap() {
        HashMap<Integer, ArrayList<MonsterModel>> curLevelMonsterMap = new HashMap<Integer, ArrayList<MonsterModel>>();

        int curLevel;
        for (MonsterModel monsterModel : this.allMonsterModels) {
            curLevel = monsterModel.getLevel();
            if (!curLevelMonsterMap.containsKey(curLevel)) {
                curLevelMonsterMap.put(curLevel, new ArrayList<MonsterModel>());
            }
            curLevelMonsterMap.get(curLevel).add(monsterModel);
        }

        return curLevelMonsterMap;
    }
    //endregion

    //region check game exit (win or faint) condition
    private boolean heroWin() {
        boolean isWin = true;

        for (MonsterObject monsterObject : this.monsterObjects) {
            if (!monsterObject.isFainted()) {
                isWin = false;
            }
        }

        return isWin;
    }

    private boolean monsterWin() {
        boolean isWin = true;

        for (HeroObject heroObject : this.heroObjects) {
            if (!heroObject.isFainted()) {
                isWin = false;
            }
        }

        return isWin;
    }
    //endregion

    // return target monster for hero at hero index, precondition: not fainted monster exits
    private MonsterObject getTargetMonster(int heroIndex) {
        if (!this.monsterObjects.get(heroIndex).isFainted()) {
            return this.monsterObjects.get(heroIndex);
        }

        for (MonsterObject monsterObject : this.monsterObjects) {
            if (!monsterObject.isFainted()) {
                return monsterObject;
            }
        }
        return null;
    }

    // return target hero for monster at monster index, precondition: not fainted hero exits
    private HeroObject getTargetHero(int monsterIndex) {
        if (!this.heroObjects.get(monsterIndex).isFainted()) {
            return this.heroObjects.get(monsterIndex);
        }

        for (HeroObject heroObject : this.heroObjects) {
            if (!heroObject.isFainted()) {
                return heroObject;
            }
        }
        return null;
    }

    public void start() {
        boolean heroWin = startBattle();
        if (heroWin) {
            System.out.println("Hero Win!");
            int i = 0;
            for (HeroObject heroObject : this.heroObjects) {
                if (!heroObject.isFainted()) {
                    System.out.println("Hero " + i + ", " + heroObject.getName() + ", receives " + heroObject.getLevel() * 100 + " gold, " + 2 + " experience.");
                    heroObject.addMoney(heroObject.getLevel() * 100);
                    heroObject.addExp(2);
                }
                i++;
            }
        }
        else {
            System.out.println("Monsters Win!");
        }

        int i = 0;
        for (HeroObject heroObject : this.heroObjects) {
            if (heroObject.isFainted()) {
                int[] curHpMana = hpManaBeforeBattle.get(i);
                heroObject.setHp(curHpMana[0] / 2);
                heroObject.setMana(curHpMana[1] / 2);
            }
            i++;
        }
    }

    // battle logic, return true if player win, return false if monster win
    public boolean startBattle() {
        System.out.println("-------------- Start Battle --------------");

        // start battling
        while (true) {
            // show information
            System.out.println("Hero: ");
            for (HeroObject heroObject1 : this.heroObjects) {
                heroObject1.printStatus();
            }
            System.out.println("Monsters: ");
            for (MonsterObject monsterObject : this.monsterObjects) {
                monsterObject.printStatus();
            }

            // exit conditions
            if (heroWin()) return true;
            if (monsterWin()) return false;

            // print current condition

            // hero's turn
            MonsterObject targetMonster;
            int i = 0;
            for (HeroObject heroObject : this.heroObjects) {
                // exit conditions
                if (heroWin()) return true;
                if (monsterWin()) return false;

                // get the target monster
                targetMonster = getTargetMonster(i);

                if (!heroObject.isFainted()) {
                    while (true) {
                        System.out.println("It's hero: " + heroObject.getName() + "'s turn!");
                        System.out.println("Target monster is: " + targetMonster.getName());

                        System.out.println("Options: ");
                        System.out.println("    [I]: Show Info");
                        System.out.println("    [T]: Use Potion");
                        System.out.println("    [S]: Use Spell");
                        System.out.println("    [A]: Attack");
                        System.out.println("    [Q]: Quit Turn");

                        ArrayList<Character> options = new ArrayList<Character>();
                        options.add('I');
                        options.add('T');
                        options.add('S');
                        options.add('A');
                        options.add('Q');

                        char userInput = Utils.takeOptionInput(options);

                        // show info
                        if (userInput == 'I') {
                            System.out.println("Hero: ");
                            for (HeroObject heroObject1 : this.heroObjects) {
                                heroObject1.printStatus();
                            }
                            System.out.println("Monsters: ");
                            for (MonsterObject monsterObject : this.monsterObjects) {
                                monsterObject.printStatus();
                            }
                            break;
                        }
                        // use potion
                        if (userInput == 'T') {
                            if (heroObject.getStockPotion().size() == 0) {
                                System.out.println("No stocked potion.");
                                continue;
                            }
                            heroObject.getHeroObjectView().printPotion(heroObject);
                            System.out.println("Which potion do you want to use?");
                            int playerPotionInput = Utils.takeIntInput(0, heroObject.getStockPotion().size() - 1);
                            heroObject.takePotion(playerPotionInput);
                            System.out.println("Potion successfully used!");
                            break;
                        }
                        // use spell
                        if (userInput == 'S') {
                            if (heroObject.getStockSpell().size() == 0) {
                                System.out.println("No stocked spell.");
                                continue;
                            }
                            heroObject.getHeroObjectView().printSpell(heroObject);
                            System.out.println("Which spell do you want to use?");
                            int playerSpellInput = Utils.takeIntInput(0, heroObject.getStockSpell().size() - 1);
                            if (heroObject.getMana() < heroObject.getStockSpell().get(playerSpellInput).getManaCost()) {
                                System.out.println("Not enough mana");
                            }
                            else if (Utils.getDodged(targetMonster.getDodgePossiblity())) {
                                System.out.println("Hero dodges successfully!");
                            }
                            else {
                                System.out.println("Spell successfully used!");
                                if (heroObject.getStockSpell().get(playerSpellInput).isFireSpell()) {
                                    targetMonster.setDefense((int) (targetMonster.getDefense() * 0.9));

                                }
                                if (heroObject.getStockSpell().get(playerSpellInput).isIceSpell()) {
                                    targetMonster.setDamage((int) (targetMonster.getDamage() * 0.9));
                                }
                                if (heroObject.getStockSpell().get(playerSpellInput).isLightningSpell()) {
                                    targetMonster.setDodgeChance((int) (targetMonster.getDodgeChance() * 0.9));
                                }
                                int damage = heroObject.useSpell(playerSpellInput);
                                targetMonster.takeHit(damage);
                                System.out.println("Monster take damage: " + damage + ", remaining HP: " + targetMonster.getHP());
                                if (targetMonster.isFainted()) {
                                    System.out.println("Monster faints!");
                                }
                                break;
                            }
                        }
                        // attack
                        if (userInput == 'A') {
                            // monster dodge
                            if (Utils.getDodged(targetMonster.getDodgePossiblity())) {
                                System.out.println("Hero dodges successfully!");
                            }
                            // monster take the hit
                            else {
                                int damage = heroObject.getAttackDamage() - targetMonster.getDamageReduction();
                                targetMonster.takeHit(damage);
                                System.out.println("Monster take damage: " + damage + ", remaining HP: " + targetMonster.getHP());
                                if (targetMonster.isFainted()) {
                                    System.out.println("Monster faints!");
                                }
                            }
                            break;
                        }
                        // quit
                        if (userInput == 'Q') {
                            break;
                        }
                    }
                    heroObject.setHp((int) (heroObject.getHP() * 1.1));
                    heroObject.setMana((int) (heroObject.getMana() * 1.1));
                }
                i++;
            }

            // monster's turn
            HeroObject targetHero;
            int j = 0;
            for (MonsterObject monsterObject : this.monsterObjects) {
                // exit conditions
                if (heroWin()) return true;
                if (monsterWin()) return false;

                // get the target hero
                targetHero = getTargetHero(j);

                if (!monsterObject.isFainted()) {
                    System.out.println("--------------------");
                    System.out.println("It's monster: " + monsterObject.getName() + "'s turn!");
                    System.out.println("Target hero is: " + targetHero.getName());

                    // hero dodge
                    if (Utils.getDodged(targetHero.getDodgePossiblity())) {
                        System.out.println("Hero dodges successfully!");
                    }
                    // hero take the hit
                    else {
                        int damage = monsterObject.getDamage() - targetHero.getDamageReduction();
                        targetHero.takeHit(damage);
                        System.out.println("Hero take damage: " + damage + ", remaining HP: " + targetHero.getHp());
                        if (targetHero.isFainted()) {
                            System.out.println("Hero faints!");
                        }
                    }
                    System.out.println("--------------------");
                }

                j++;
            }
        }
    }

}
