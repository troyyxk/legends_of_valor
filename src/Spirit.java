public class Spirit extends MonsterModel {
    public Spirit(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
    }

    public boolean isDragon() {
        return false;
    }

    public boolean isExoskeleton() {
        return false;
    }

    public boolean isSpirit() {
        return true;
    }

}
