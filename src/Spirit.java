
/**
 * Predefined abstract class MonsterModel :
 * 
 *  Variables:
 *          
 *          String name:
 * 
 *          int level:
 * 
 *          int damage:
 * 
 *          int defense:
 * 
 *          int dodgeChance:
 * 
 *  Constrcutor:
 * 
 *          MonsterModel(String name, int level, int damage, int defense, int dodgeChance):
 * 
 *  Methods:
 * 
 *          abstract boolean isDragon():
 * 
 *          abstract boolean isExoskeleton():
 * 
 *          abstract boolean isSpirit():
 * 
 *          String getName():
 * 
 *          void setName(String name) :
 * 
 *          int getLevel():
 * 
 *          void setLevel(int level):
 * 
 *          int getDamage():
 * 
 *          void setDamage(int damage):
 * 
 *          int getDefense():
 * 
 *          void setDefense(int defense) :
 * 
 *          int getDodgeChance() :
 * 
 *          void setDodgeChance(int dodgeChance):
 * 
 * Class Spirit:
 * 
 *  Constructor:
 * 
 *              Spirit(String name, int level, int damage, int defense, int dodgeChance) 
 * 
 *  Variables:
 * 
 *              boolean isDragon():
 * 
 *              boolean isExoskeleton():
 * 
 *              boolean isSpirit():
 */
public class Spirit extends MonsterModel {
    public Spirit(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
    }

    public boolean isDragon() {
        return false;
    }

    public boolean isExoskeleton() {
        return false;
    }

    public boolean isSpirit() {
        return true;
    }

}
