
/**
 * abstract class MonsterModel:
 * 
 *  Variables:
 * 
 *          String name:
 * 
 *          int level :
 * 
 *          int damage:
 * 
 *          int defense:
 * 
 *          int dodgeChance:
 * 
 *  Constructor:
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
 *          // getter and setter
 * 
 *          String getName():
 * 
 *          ...
 */
public abstract class MonsterModel {
    private String name;
    private int level, damage, defense, dodgeChance;

    public MonsterModel(String name, int level, int damage, int defense, int dodgeChance) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    public abstract boolean isDragon();
    public abstract boolean isExoskeleton();
    public abstract boolean isSpirit();

    // getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

}
