public abstract class HeroModel {

    private String name;
    private int mana, strength, agility, dexterity, startingMoney, startingExperience;

    public HeroModel(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExperience) {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.startingMoney = startingMoney;
        this.startingExperience = startingExperience;
    }

    public abstract boolean isPaladin();
    public abstract boolean isSorcerer();
    public abstract boolean isWarrior();

    // getter
    public String getName() {
        return name;
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

}
