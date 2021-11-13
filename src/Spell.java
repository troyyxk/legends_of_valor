/**
 * Predefined Interface Purchasable:
 *  
 *                  abstract boolean isPurchasable(HeroObject heroObject)
 * 
 * Class Spell:
 * 
 *  Variables:
 *      
 *              String Name:
 * 
 *              int cost:
 * 
 *              int requiredLevel:
 * 
 *              int damage:
 * 
 *              int manaCost:
 * 
 *  Constructor:
 * 
 *              Spell(String name, int cost, int requiredLevel, int damage, int manaCost) :
 * 
 *  Methods:
 * 
 *              abstract boolean isFireSpell():
 * 
 *              abstract boolean isIceSpell():
 * 
 *              abstract boolean isLightningSpell():
 *                          
 *              abstract String getType():
 * 
 *              boolean isPurchasable( HeroObject heroObject):
 * 
 *                                              return true if "heroObject" 's money and level are qualified for a Purchase 
 * 
 *              String getName():
 * 
 *              void setName(String name) :
 * 
 *              int getCost():
 * 
 *              void setCost(int cost):
 * 
 *              int getRequiredLevel():
 * 
 *              void setRequiredLevel(int requiredLevel):
 * 
 *              int getDamage():
 * 
 *              void setDamage(int damage) :
 * 
 *              int getManaCost():
 * 
 *              void setManaCost(int manaCost):
 *              
 */
public abstract class Spell implements Purchasable {
    private String Name;
    private int cost;
    private int requiredLevel;
    private int damage;
    private int manaCost;

    public Spell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        Name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public abstract boolean isFireSpell();

    public abstract boolean isIceSpell();

    public abstract boolean isLightningSpell();

    public abstract String getType();

    @Override
    public boolean isPurchasable(HeroObject heroObject) {
        return heroObject.getLevel() >= this.getRequiredLevel() && heroObject.getMoney() >= cost;
    }

    // getter and setter

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
