
/**
 * Predefined interface Purchasable:
 * 
 *       abstract boolean isPurchasable(HeroObject heroObject)
 * 
 * Predefined abstract class Spell:
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
 *          Spell(String name, int cost, int requiredLevel, int damage, int manaCost):
 * 
 *  Methods:
 *          
 *          abstract boolean isFireSpell():
 * 
 *          abstract boolean isIceSpell():
 * 
 *          abstract boolean isLightningSpell():
 * 
 *          abstract String getType():
 * 
 *          boolean isPurchasable(HeroObject heroObject):
 * 
 *    // getter and setter
 * 
 *          ....      
 * 
 * class LightningSpell :
 * 
 *  Variables:
 * 
 *          String type:
 * 
 *  Constructor:
 *      
 *          LightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost):

 * 
 *  Methods:
 * 
 *          String getType():
 * 
 *          boolean isFireSpell():
 * 
 *          boolean isIceSpell() :
 * 
 *          boolean isLightningSpell()
 * */
public class LightningSpell extends Spell{

    private String type;

    public LightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = "Lightning";
    }

    public boolean isFireSpell() {
        return false;
    }

    public boolean isIceSpell() {
        return false;
    }

    public boolean isLightningSpell() {
        return true;
    }

    // getter
    public String getType() {
        return this.type;
    }

}
