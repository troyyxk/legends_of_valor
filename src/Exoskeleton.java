public class Exoskeleton extends MonsterModel {
    public Exoskeleton(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
    }

    public boolean isDragon() {
        return false;
    }

    public boolean isExoskeleton() {
        return true;
    }

    public boolean isSpirit() {
        return false;
    }

}
