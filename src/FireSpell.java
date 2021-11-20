
/**
/**
 * Predefined Interface Purchasable:
 *  
 *                  abstract boolean isPurchasable(HeroObject heroObject)
 * 
 * Predefined Class Spell:
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
 * Class FireSpell:
 * 
 *  Variable:
 * 
 *              String type
 *  Constructor:
 * 
 *              FireSpell(String name, int cost, int requiredLevel, int damage, int manaCost):
 * 
 *  Methods:  
 *             boolean isLightningSpell()
 * 
 *             String getType() 
 */
public class FireSpell extends Spell {

    private String type;

    public FireSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = "Fire";
    }

    public boolean isFireSpell() {
        return true;
    }

    public boolean isIceSpell() {
        return false;
    }

    public boolean isLightningSpell() {
        return false;
    }

    // getter
    public String getType() {
        return this.type;
    }

}
