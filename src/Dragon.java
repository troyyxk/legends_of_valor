public class Dragon extends MonsterModel {
    public Dragon(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
    }

    public boolean isDragon() {
        return true;
    }

    public boolean isExoskeleton() {
        return false;
    }

    public boolean isSpirit() {
        return false;
    }

}
