public class FireSpell extends Spell{

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
