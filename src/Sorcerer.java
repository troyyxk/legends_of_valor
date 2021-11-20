
/**
 * Predefined abstract class HeroModel :
 * 
 *  Variables:
 *          
 *          String name:
 * 
 *          int mana:
 * 
 *          int strength:
 * 
 *          int agility:
 * 
 *          int dodgeChance:
 * 
 *          int dexterity:
 * 
 *          int startingMoney:
 * 
 *          int startingExperience:
 * 
 * 
 *  Constrcutor:
 * 
 *          HeroModel(String name, int mana, int strength, int agility, int dexterity, int startingMoney,
            int startingExperience) :

                                    empty constructor
 * 
 *  Methods:
 * 
 *          abstract boolean isPaladin():
 * 
 *          abstract boolean isSorcerer():
 * 
 *          abstract boolean isWarrior():
 * 
 *          String getName():
 * 
 *          int getMana():
 * 
 *          int getStrength() :
 * 
 *          int getAgility() :
 *  
 *          int getDexterity():
 * 
 *          int getStartingMoney() :
 * 
 *          int getStartingExperience():
 * 
 * class Sorcerer:
 * 
 *  Constructor:
 *                  Sorcerer(String name, int mana, int strength, int agility, int dexterity, int startingMoney,
            int startingExperience):

 *  Methods:
 * 
 *              boolean isPaladin():
 * 
 *              boolean isSorcerer():
 * 
 *              boolean isWarrior(
 *              
 */
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
