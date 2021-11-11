public class Armory implements Purchasable{
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
