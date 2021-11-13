/**
 * Predefined Interface Purchasable:
 *  
 *                  abstract boolean isPurchasable(HeroObject heroObject)
 * 
 * 
 * Armory class 
 * 
 *      Variables:
 * 
 *                  String name : 
 *          
 *                  int cost:
 * 
 *                  int requiredLevel:
 * 
 *                  int damageReduction:
 * 
 *      Constructors:
 *              
 *                  Armory(String name, int cost, int requiredLevel, int damageReduction) :
 *          
 *      Methods:
 *          
 *                  boolean isPurchasable( HeroObject heroObject):
 * 
 *                                              return true if "heroObject" 's money and level are qualified for a Purchase
 * 
 *                  String getName():
 * 
 *                  void setName(String name):
 * 
 *                  int getCost():
 *                              
 *                  setCost(int cost) :
 * 
 *                  int getRequiredLevel():
 * 
 *                  void setRequiredLevel(int requiredLevel):
 * 
 *                  int getDamageReduction() :
 * 
 *                  void setDamageReduction(int damageReduction):
 */
public class Armory implements Purchasable {
    String name;
    int cost;
    int requiredLevel;
    int damageReduction;

    public Armory(String name, int cost, int requiredLevel, int damageReduction) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damageReduction = damageReduction;
    }

    @Override
    public boolean isPurchasable(HeroObject heroObject) {
        return heroObject.getLevel() >= this.getRequiredLevel() && heroObject.getMoney() >= cost;
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

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }
}
