import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MarketModel {

    private ArrayList<Weapon> weapons;
    private ArrayList<Potion> potions;
    private ArrayList<Armory> armories;
    private ArrayList<FireSpell> fireSpells;
    private ArrayList<IceSpell> iceSpells;
    private ArrayList<LightningSpell> lightningSpells;

    public MarketModel() {
        this.weapons = new ArrayList<Weapon>();
        this.potions = new ArrayList<Potion>();
        this.armories = new ArrayList<Armory>();
        this.fireSpells = new ArrayList<FireSpell>();
        this.iceSpells = new ArrayList<IceSpell>();
        this.lightningSpells = new ArrayList<LightningSpell>();

        this.collectWeapons("Legends_Monsters_and_Heroes/Weaponry.txt");
        this.collectPotions("Legends_Monsters_and_Heroes/Potions.txt");
        this.collectArmory("Legends_Monsters_and_Heroes/Armory.txt");
        this.collectFireSpells("Legends_Monsters_and_Heroes/FireSpells.txt");
        this.collectIceSpells("Legends_Monsters_and_Heroes/IceSpells.txt");
        this.collectLightningSpells("Legends_Monsters_and_Heroes/LightningSpells.txt");
    }

    // collection data from file
    private void collectWeapons(String filePath) {
        ArrayList<ArrayList<String>> allWeapons = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curWeapon : allWeapons) {
            assert curWeapon.size() == 5;
            this.weapons.add(new Weapon(curWeapon.get(0),
                    Integer.parseInt(curWeapon.get(1)),
                    Integer.parseInt(curWeapon.get(2)),
                    Integer.parseInt(curWeapon.get(3)),
                    Integer.parseInt(curWeapon.get(4))));
        }
    }

    // collection data from file
    private void collectPotions(String filePath) {
        ArrayList<ArrayList<String>> allPotions = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curPotion : allPotions) {
            assert curPotion.size() >= 5;
            this.potions.add(new Potion(curPotion.get(0),
                    Integer.parseInt(curPotion.get(1)),
                    Integer.parseInt(curPotion.get(2)),
                    Integer.parseInt(curPotion.get(3)),
                    new ArrayList<String>(curPotion.subList(4, curPotion.size()))));
        }
    }

    // collection data from file
    private void collectArmory(String filePath) {
        ArrayList<ArrayList<String>> allArmory = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curArmory : allArmory) {
            assert curArmory.size() == 4;
            this.armories.add(new Armory(curArmory.get(0),
                    Integer.parseInt(curArmory.get(1)),
                    Integer.parseInt(curArmory.get(2)),
                    Integer.parseInt(curArmory.get(3))));
        }
    }

    // collection data from file
    private void collectFireSpells(String filePath) {
        ArrayList<ArrayList<String>> allFireSpells = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curFireSpell : allFireSpells) {
            assert curFireSpell.size() == 5;
            this.fireSpells.add(new FireSpell(curFireSpell.get(0),
                    Integer.parseInt(curFireSpell.get(1)),
                    Integer.parseInt(curFireSpell.get(2)),
                    Integer.parseInt(curFireSpell.get(3)),
                    Integer.parseInt(curFireSpell.get(4))));
        }
    }

    // collection data from file
    private void collectIceSpells(String filePath) {
        ArrayList<ArrayList<String>> allIceSpells = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curIceSpell : allIceSpells) {
            assert curIceSpell.size() == 5;
            this.iceSpells.add(new IceSpell(curIceSpell.get(0),
                    Integer.parseInt(curIceSpell.get(1)),
                    Integer.parseInt(curIceSpell.get(2)),
                    Integer.parseInt(curIceSpell.get(3)),
                    Integer.parseInt(curIceSpell.get(4))));
        }
    }

    // collection data from file
    private void collectLightningSpells(String filePath) {
        ArrayList<ArrayList<String>> allLightningSpells = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curLightningSpell : allLightningSpells) {
            assert curLightningSpell.size() == 5;
            this.lightningSpells.add(new LightningSpell(curLightningSpell.get(0),
                    Integer.parseInt(curLightningSpell.get(1)),
                    Integer.parseInt(curLightningSpell.get(2)),
                    Integer.parseInt(curLightningSpell.get(3)),
                    Integer.parseInt(curLightningSpell.get(4))));
        }
    }

    // getters

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<Armory> getArmories() {
        return armories;
    }

    public ArrayList<FireSpell> getFireSpells() {
        return fireSpells;
    }

    public ArrayList<IceSpell> getIceSpells() {
        return iceSpells;
    }

    public ArrayList<LightningSpell> getLightningSpells() {
        return lightningSpells;
    }

}
