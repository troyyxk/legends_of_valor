public class Paladin extends HeroModel {
    public Paladin(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExperience) {
        super(name, mana, strength, agility, dexterity, startingMoney, startingExperience);
    }

    public boolean isPaladin() {
        return true;
    }

    public boolean isSorcerer() {
        return false;
    }

    public boolean isWarrior() {
        return false;
    }

}
