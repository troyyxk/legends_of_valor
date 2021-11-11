import java.util.ArrayList;

public class Potion implements Purchasable {
    String name;
    int cost;
    int requiredLevel;
    int attributeIncrease;
    ArrayList<String> attributeAffected;

    public Potion(String name, int cost, int requiredLevel, int attributeIncrease, ArrayList<String> attributeAffected) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.attributeIncrease = attributeIncrease;
        this.attributeAffected = attributeAffected;
    }

    @Override
    public boolean isPurchasable(HeroObject heroObject) {
        return heroObject.getLevel() >= this.getRequiredLevel() && heroObject.getMoney() >= cost;
    }

    public void effectOn(HeroObject heroObject) {
        for (String curAttribute : attributeAffected) {
            if (curAttribute.equals("Health")) {
                heroObject.setHp(heroObject.getHP() + this.attributeIncrease);
            }
            if (curAttribute.equals("Strength")) {
                heroObject.setStrength(heroObject.getStrength() + this.attributeIncrease);
            }
            if (curAttribute.equals("Mana")) {
                heroObject.setMana(heroObject.getMana() + this.attributeIncrease);
            }
            if (curAttribute.equals("Agility")) {
                heroObject.setAgility(heroObject.getAgility() + this.attributeIncrease);
            }
            if (curAttribute.equals("Dexterity")) {
                heroObject.setDexterity(heroObject.getDexterity() + this.attributeIncrease);
            }
            if (curAttribute.equals("Defense")) {
                heroObject.setDefence(heroObject.getDefence() + this.attributeIncrease);
            }
        }
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

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public void setAttributeIncrease(int attributeIncrease) {
        this.attributeIncrease = attributeIncrease;
    }

    public ArrayList<String> getAttributeAffected() {
        return attributeAffected;
    }

    public void setAttributeAffected(ArrayList<String> attributeAffected) {
        this.attributeAffected = attributeAffected;
    }


}
