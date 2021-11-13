/**
 * Predefined Interface Purchasable:
 * 
 *              abstract boolean isPurchasable(HeroObject heroObject)
 * 
 * Class Weapon :
 * 
 *  Variables:
 * 
 *              String name :
 *              
 *              int cost:
 * 
 *              int level:
 * 
 *              int damage:
 * 
 *              int requireHands:
 * 
 *  Constructor:
 *              
 *              Weapon(tring name, int cost, int level, int damage, int requiredHands):
 * 
 *  Methods:
 * 
 *              boolean isPurchasable(HeroObject heroObject):
 * 
 *                              return true if "heroObject" 's money and level are qualified for a Purchase
 *        
 *              String getName():
 * 
 *              void setName(String name):
 * 
 *              int getCost():
 * 
 *              void setCost(int cost):
 * 
 *              int getLevel()
 * 
 *              void setLevel(int level) :
 * 
 *              int getDamage():
 * 
 *              void setDamage(int damage): 
 * 
 *              int getRequiredHands():
 * 
 *              void setRequiredHands(int requiredHands):
 */

public class Weapon implements Purchasable {
    private String name;
    private int cost;
    private int level;
    private int damage;
    private int requiredHands;

    public Weapon(String name, int cost, int level, int damage, int requiredHands) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.requiredHands = requiredHands;
    }

    @Override
    public boolean isPurchasable(HeroObject heroObject) {
        return heroObject.getLevel() >= this.getLevel() && heroObject.getMoney() >= cost;
    }

    // getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public int getRequiredHands() {
        return requiredHands;
    }

    public void setRequiredHands(int requiredHands) {
        this.requiredHands = requiredHands;
    }
}
