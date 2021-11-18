import java.util.ArrayList;

public class HeroObject {
    private HeroModel heroModel;
    private String name;
    private int mana, strength, agility, dexterity, startingMoney, startingExperience;
    private int money, exp, level;
    private int levelCap;
    private int defence;

    private int hp;

    private int heroIndex;

    private HeroObjectView heroObjectView;

    // equipped
    private Weapon curEquippedWeapon;
    private Armory curEquippedArmory;

    // stock
    private ArrayList<Weapon> stockWeapons;
    private ArrayList<Armory> stockArmory;
    private ArrayList<Potion> stockPotion;
    private ArrayList<Spell> stockSpell;

    public HeroObject(int heroIndex, HeroModel heroModel, int levelCap) {
        this.heroIndex = heroIndex;

        this.level = 1;
        this.setHeroModel(heroModel);

        this.name = heroModel.getName();
        this.mana = heroModel.getMana();
        this.strength = heroModel.getStrength();
        this.agility = heroModel.getAgility();
        this.dexterity = heroModel.getDexterity();
        this.money = heroModel.getStartingMoney();
        this.exp = heroModel.getStartingExperience();
        this.defence = 0;

        this.stockWeapons = new ArrayList<Weapon>();
        this.stockArmory = new ArrayList<Armory>();
        this.stockPotion = new ArrayList<Potion>();
        this.stockSpell = new ArrayList<Spell>();

        this.levelCap = levelCap;

        this.hp = this.getMaxHP();

        this.heroObjectView = new HeroObjectView();
    }

    public void takeDownWeapon() {
        this.stockWeapons.add(this.curEquippedWeapon);
        this.curEquippedWeapon = null;
    }

    public void takeDownArmory() {
        this.stockArmory.add(this.curEquippedArmory);
        this.curEquippedArmory = null;
    }

    public void putOnWeapon(int weaponIdx) {
        if (this.curEquippedWeapon != null) {
            takeDownWeapon();
        }
        this.curEquippedWeapon = this.stockWeapons.get(weaponIdx);
        this.stockWeapons.remove(weaponIdx);
    }

    public void putOnArmory(int armoryIdx) {
        if (this.curEquippedArmory != null) {
            takeDownWeapon();
        }
        this.curEquippedArmory = this.stockArmory.get(armoryIdx);
        this.stockArmory.remove(armoryIdx);
    }

    public void takePotion(int potionIdx) {
        this.getStockPotion().get(potionIdx).effectOn(this);
        this.getStockPotion().remove(potionIdx);
    }


    public void addMoney(int amount) {
        this.money += amount;
    }

    public void printStatus() {
        this.heroObjectView.printStatus(this);
    }

    public void getReadyForBatter() {
        this.hp = this.getMaxHP();
    }

    public void takeHit(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    public void purchaseWeapon(Weapon weapon) {
        this.stockWeapons.add(weapon);
        this.money -= weapon.getCost();
    }

    public void purchaseArmory(Armory armory) {
        this.stockArmory.add(armory);
        this.money -= armory.getCost();
    }

    public void purchasePotion(Potion potion) {
        this.stockPotion.add(potion);
        this.money -= potion.getCost();
    }

    public void purchaseSpell(Spell spell) {
        this.stockSpell.add(spell);
        this.money -= spell.getCost();
    }

    // pre-condition: can only be used during battle
    public boolean isFainted() {
        return this.hp <= 0;
    }

    public void addExp(int newExp) {
        this.exp += newExp;
        if (ableTOLevelUp()) {
            levelUp();
        }
    }

    // pre-condition: able to level up
    public void levelUp() {
        if (!ableTOLevelUp()) {
            System.out.println("In Player.java, level up without been able to level up");
            System.exit(1);
        }
        this.exp -= this.level * 10;
        if (this.level <= levelCap) {
            this.level += 1;
            this.setMana((int) (this.getMana() * 1.1));
            this.setAgility((int) (this.getAgility() * 1.1));
            this.setDexterity((int) (this.getDexterity() * 1.1));
            this.setStrength((int) (this.getStrength() * 1.1));
            this.setDefence((int) (this.getDefence() * 1.1));
            if (this.heroModel.isPaladin()) {
                this.setStrength((int) (this.getStrength() * 1.1));
                this.setAgility((int) (this.getAgility() * 1.1));
            }
            if (this.heroModel.isPaladin()) {
                this.setAgility((int) (this.getAgility() * 1.1));
                this.setDexterity((int) (this.getDexterity() * 1.1));
            }
            if (this.heroModel.isPaladin()) {
                this.setDexterity((int) (this.getDexterity() * 1.1));
                this.setStrength((int) (this.getStrength() * 1.1));
            }
        } else {
            System.out.println("Level cap reached, cannot further Levelup " + this.name + "!");
        }
        this.setHp(getMaxHP());
    }

    // getter and setter
    public int getHeroIndex() {
        return heroIndex;
    }

    public double getDodgePossiblity() {
        double dodgePossibility = this.agility * 0.002;
        return dodgePossibility;
    }

    public int getDamageReduction() {
        int damageReduction = 0;
        if (this.curEquippedArmory != null) {
            damageReduction = (int) (this.curEquippedArmory.getDamageReduction() * 0.5);
        }
        return damageReduction;
    }

    public HeroModel getHeroModel() {
        return heroModel;
    }

    public int getMaxHP() {
        return this.getLevel() * 100;
    }

    public void setHeroModel(HeroModel heroModel) {
        this.name = heroModel.getName();
        this.heroModel = heroModel;
        this.mana = heroModel.getMana();
        this.strength = heroModel.getStrength();
        this.agility = heroModel.getAgility();
        this.dexterity = heroModel.getDexterity();
        this.money = heroModel.getStartingMoney();
        this.exp = heroModel.getStartingExperience();
    }

    public int getAttackDamage() {
        int damage = strength;
        if (this.curEquippedWeapon != null) {
            damage += this.curEquippedWeapon.getDamage();
        }
        damage *= 0.05;
        return damage;
    }

    public boolean ableTOLevelUp() {
        return this.exp >= this.level * 10;
    }

    public int getHP() {
        return this.hp;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return this.name;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStartingMoney() {
        return startingMoney;
    }

    public int getStartingExperience() {
        return startingExperience;
    }

    public int getMoney() {
        return money;
    }

    public int getExp() {
        return exp;
    }

    public int getLevelCap() {
        return levelCap;
    }

    public Weapon getCurEquippedWeapon() {
        return curEquippedWeapon;
    }

    public Armory getCurEquippedArmory() {
        return curEquippedArmory;
    }

    public ArrayList<Weapon> getStockWeapons() {
        return stockWeapons;
    }

    public ArrayList<Armory> getStockArmory() {
        return stockArmory;
    }

    public ArrayList<Potion> getStockPotion() {
        return stockPotion;
    }

    public ArrayList<Spell> getStockSpell() {
        return stockSpell;
    }

    public HeroObjectView getHeroObjectView() {
        return heroObjectView;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setStartingMoney(int startingMoney) {
        this.startingMoney = startingMoney;
    }

    public void setStartingExperience(int startingExperience) {
        this.startingExperience = startingExperience;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLevelCap(int levelCap) {
        this.levelCap = levelCap;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setHeroIndex(int heroIndex) {
        this.heroIndex = heroIndex;
    }

    public void setHeroObjectView(HeroObjectView heroObjectView) {
        this.heroObjectView = heroObjectView;
    }

    public void setCurEquippedWeapon(Weapon curEquippedWeapon) {
        this.curEquippedWeapon = curEquippedWeapon;
    }

    public void setCurEquippedArmory(Armory curEquippedArmory) {
        this.curEquippedArmory = curEquippedArmory;
    }

    public void setStockWeapons(ArrayList<Weapon> stockWeapons) {
        this.stockWeapons = stockWeapons;
    }

    public void setStockArmory(ArrayList<Armory> stockArmory) {
        this.stockArmory = stockArmory;
    }

    public void setStockPotion(ArrayList<Potion> stockPotion) {
        this.stockPotion = stockPotion;
    }

    public void setStockSpell(ArrayList<Spell> stockSpell) {
        this.stockSpell = stockSpell;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int useSpell(int playerSpellInput) {
        Spell curSpell = this.stockSpell.get(playerSpellInput);
        this.stockSpell.remove(playerSpellInput);
        this.mana -= curSpell.getManaCost();
        return curSpell.getDamage() + (dexterity/10000) * curSpell.getDamage();
    }

}
