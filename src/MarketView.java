
/**
 * class MarketView:
 * 
 *      Variables:
 * 
 *      Methods:
 * 
 *              void show(MarketModel marketModel):
 * 
 *                      print out items available at the market
 * 
 *              void printLightningSpellHeader():
 * 
 *              void printLightningSpell(int index, LightningSpell curLightningSpell):
 * 
 *              ...
 *              
 */

public class MarketView {
    public void show(MarketModel marketModel) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Products on Shelf:");

        int index = 0;

        // weapon
        System.out.println("[0] Weapon: ");
        printWeaponHeader();
        index = 0;
        for (Weapon curWeapon : marketModel.getWeapons()) {
            printWeapon(index, curWeapon);
            index++;
        }

        // potion
        System.out.println("[1] Potion: ");
        printPotionHeader();
        index = 0;
        for (Potion curPotion : marketModel.getPotions()) {
            printPotion(index, curPotion);
            index++;
        }

        // Armory
        System.out.println("[2] Armory: ");
        printArmoryHeader();
        index = 0;
        for (Armory curArmory : marketModel.getArmories()) {
            printArmory(index, curArmory);
            index++;
        }

        // Spell
        System.out.println("*** Spell ***");

        // Fire Spell
        System.out.println("[3] Fire Spell: ");
        printFireSpellHeader();
        index = 0;
        for (FireSpell curFireSpell : marketModel.getFireSpells()) {
            printFireSpell(index, curFireSpell);
            index++;
        }

        // Ice Spell
        System.out.println("[4] Ice Spell: ");
        printIceSpellHeader();
        index = 0;
        for (IceSpell curIceSpell : marketModel.getIceSpells()) {
            printIceSpell(index, curIceSpell);
            index++;
        }

        // Lightning Spell
        System.out.println("[5] Lightning Spell: ");
        printLightningSpellHeader();
        index = 0;
        for (LightningSpell curLightningSpell : marketModel.getLightningSpells()) {
            printLightningSpell(index, curLightningSpell);
            index++;
        }

        System.out.println("--------------------------------------------------------");
    }

    private void printLightningSpellHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/damage/mana cost");
    }

    private void printLightningSpell(int index, LightningSpell curLightningSpell) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curLightningSpell.getName());
        System.out.print(" ");
        System.out.print(curLightningSpell.getCost());
        System.out.print(" ");
        System.out.print(curLightningSpell.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curLightningSpell.getDamage());
        System.out.print(" ");
        System.out.print(curLightningSpell.getManaCost());
        System.out.println("");
    }

    private void printIceSpellHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/damage/mana cost");
    }

    private void printIceSpell(int index, IceSpell curIceSpell) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curIceSpell.getName());
        System.out.print(" ");
        System.out.print(curIceSpell.getCost());
        System.out.print(" ");
        System.out.print(curIceSpell.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curIceSpell.getDamage());
        System.out.print(" ");
        System.out.print(curIceSpell.getManaCost());
        System.out.println("");
    }

    private void printFireSpellHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/damage/mana cost");
    }

    private void printFireSpell(int index, FireSpell curFireSpell) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curFireSpell.getName());
        System.out.print(" ");
        System.out.print(curFireSpell.getCost());
        System.out.print(" ");
        System.out.print(curFireSpell.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curFireSpell.getDamage());
        System.out.print(" ");
        System.out.print(curFireSpell.getManaCost());
        System.out.println("");
    }

    private void printArmoryHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/damage reduction");
    }

    private void printArmory(int index, Armory curArmory) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curArmory.getName());
        System.out.print(" ");
        System.out.print(curArmory.getCost());
        System.out.print(" ");
        System.out.print(curArmory.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curArmory.getDamageReduction());
        System.out.println("");
    }

    private void printPotionHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/attribute increase/attribute affected");
    }

    private void printPotion(int index, Potion curPotion) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curPotion.getName());
        System.out.print(" ");
        System.out.print(curPotion.getCost());
        System.out.print(" ");
        System.out.print(curPotion.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curPotion.getAttributeIncrease());
        System.out.print(" ");
        for (int i = 0; i < curPotion.getAttributeAffected().size(); i++) {
            System.out.print(curPotion.getAttributeAffected().get(i));
            if (i < curPotion.getAttributeAffected().size() - 1) {
                System.out.print("/");
            }
        }
        System.out.println("");
    }

    private void printWeaponHeader()  {
        System.out.print("        ");
        System.out.println("Name/cost/level/damage/required hands");
    }

    private void printWeapon(int index, Weapon weapon) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(weapon.getName());
        System.out.print(" ");
        System.out.print(weapon.getCost());
        System.out.print(" ");
        System.out.print(weapon.getLevel());
        System.out.print(" ");
        System.out.print(weapon.getDamage());
        System.out.print(" ");
        System.out.print(weapon.getRequiredHands());
        System.out.println("");
    }

}
