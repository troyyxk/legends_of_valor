

/**
 *  class HeroObjectView
 * 
 *                      print hero's information
 * 
 *  Methods:
 * 
 *          void printStatus(HeroObject heroObject):
 * 
 *          void printWeapon(HeroObject heroObject):
 * 
 *          void printArmory(HeroObject heroObject):
 * 
 *          void printSpell(HeroObject heroObject):
 * 
 *          void printStock(HeroObject heroObject):
 */
public class HeroObjectView {
    public void printStatus(HeroObject heroObject) {
        System.out.println("[" + heroObject.getHeroIndex() + "] -----Hero-Info-----");
        System.out.println("Name : " + heroObject.getName());
        System.out.println("HP : " + heroObject.getHP());
        System.out.println("Level : " + heroObject.getLevel());
        System.out.println("Exp: " + heroObject.getExp());
        System.out.println("Strength : " + heroObject.getStrength());
        System.out.println("Agility : " + heroObject.getAgility());
        System.out.println("Dexterity : " + heroObject.getDexterity());
        System.out.println("Money: " + heroObject.getMoney());
        System.out.println("Mana: " + heroObject.getMana());

        System.out.print("Equipped Weapon: ");
        if (heroObject.getCurEquippedWeapon() == null) {
            System.out.println(" ");
        }
        else {
            System.out.println(heroObject.getCurEquippedWeapon().getName());
        }

        System.out.print("Equipped Armory: ");
        if (heroObject.getCurEquippedArmory() == null) {
            System.out.println(" ");
        }
        else {
            System.out.println(heroObject.getCurEquippedArmory().getName());
        }

        System.out.println("----------------------");

        printStock(heroObject);
    }

    public void printWeapon(HeroObject heroObject) {
        System.out.println("[0] Weapon: ");
        Utils.printWeaponHeader();
        int index = 0;
        for (Weapon curWeapon : heroObject.getStockWeapons()) {
            Utils.printWeapon(index, curWeapon);
            index++;
        }
    }

    public void printPotion(HeroObject heroObject) {
        System.out.println("[1] Potion: ");
        Utils.printPotionHeader();
        int index = 0;
        for (Potion curPotion : heroObject.getStockPotion()) {
            Utils.printPotion(index, curPotion);
            index++;
        }
    }

    public void printArmory(HeroObject heroObject) {
        System.out.println("[2] Armory: ");
        Utils.printArmoryHeader();
        int index = 0;
        for (Armory curArmory : heroObject.getStockArmory()) {
            Utils.printArmory(index, curArmory);
            index++;
        }
    }

    public void printSpell(HeroObject heroObject) {
        System.out.println("[3] Spell: ");
        Utils.printSpellHeader();
        int index = 0;
        for (Spell curFireSpell : heroObject.getStockSpell()) {
            Utils.printSpell(index, curFireSpell);
            index++;
        }
    }

    public void printStock(HeroObject heroObject) {
        System.out.println("----------------------");
        int index;
        // weapon
        printWeapon(heroObject);

        // potion
        printPotion(heroObject);

        // Armory
        printArmory(heroObject);

        // Spell
        printSpell(heroObject);

        System.out.println("----------------------");
    }
}
