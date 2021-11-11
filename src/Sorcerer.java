public class Sorcerer extends HeroModel {
    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExperience) {
        super(name, mana, strength, agility, dexterity, startingMoney, startingExperience);
    }

    public boolean isPaladin() {
        return false;
    }

    public boolean isSorcerer() {
        return true;
    }

    public boolean isWarrior() {
        return false;
    }


}
