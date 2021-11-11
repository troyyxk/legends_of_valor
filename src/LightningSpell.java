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
