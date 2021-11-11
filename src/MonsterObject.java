public class MonsterObject {
    private String name;
    private int level, damage, defense, dodgeChance;
    private MonsterModel monsterModel;
    private int hp;

    public MonsterObject(MonsterModel monsterModel) {
        this.monsterModel = monsterModel;
        this.name = monsterModel.getName();
        this.level = monsterModel.getLevel();
        this.damage = monsterModel.getDamage();
        this.defense = monsterModel.getDefense();
        this.dodgeChance = monsterModel.getDodgeChance();

        this.hp = this.getMaxHP();
    }

    public void getReadyForBatter() {
        this.hp = this.getMaxHP();
    }

    public void takeHit(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    // pre-condition: can only be used during battle
    public boolean isFainted() {
        return this.hp <= 0;
    }

    public void printStatus() {
        System.out.println("-----Monster-Info-----");
        System.out.println("Name : " + this.getName());
        System.out.println("HP : " + this.getHP());
        System.out.println("Level : " + this.getLevel());
        System.out.println("Damage : " + this.getDamage());
        System.out.println("Defense : " + this.getDefense());
        System.out.println("Dodge Chance : " + this.getDodgeChance());

        System.out.println("----------------------");
    }

    // getter and setter

    public int getHP() {
        return this.hp;
    }

    public int getMaxHP() {
        return this.getLevel() * 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public MonsterModel getMonsterModel() {
        return monsterModel;
    }

    public void setMonsterModel(MonsterModel monsterModel) {
        this.monsterModel = monsterModel;
    }

    public double getDodgePossiblity() {
        return dodgeChance * 0.01;
    }

    public int getDamageReduction() {
        return (int) (this.defense * 0.01);
    }
}
