public class IceSpell extends Spell {

    private String type;

    public IceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = "Ice";
    }

    public boolean isFireSpell() {
        return false;
    }

    public boolean isIceSpell() {
        return true;
    }

    public boolean isLightningSpell() {
        return false;
    }

    // getter
    public String getType() {
        return this.type;
    }

}
