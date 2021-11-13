import java.util.ArrayList;

public class Player implements Movable {

    private String name;
    private char marker;
    private ArrayList<HeroObject> heroObjects;
    private int heroLevelCap, heroCount;
    private HeroGallaryController heroGallaryController;
    private int posX, posY;

    public Player() {
        this.name = Utils.takeName();
        this.marker = Utils.takeMarker();
        this.heroLevelCap = 10;
        this.heroObjects = new ArrayList<HeroObject>();
        System.out.println("Enter number of hero you want to play: 1-3 inclusive:");
        this.heroCount = Utils.takeIntInput(1, 3);
        this.heroGallaryController = new HeroGallaryController();
        addHeroObjects();
    }

    public Player(String name) {
        this.name = name;
        this.heroLevelCap = 10;
        this.heroObjects = new ArrayList<HeroObject>();
    }

    public Player(String name, int heroLevelCap) {
        this.name = name;
        this.heroLevelCap = heroLevelCap;
        this.heroObjects = new ArrayList<HeroObject>();
    }

    public void printInfo() {
        System.out.println(this.name + "'s info:");
        for (HeroObject heroObject : this.heroObjects) {
            heroObject.printStatus();
        }
    }

    public void setPos(int[] pos) {
        this.posY = pos[0];
        this.posX = pos[1];
    }

    private void addHeroObjects() {
        HeroModel curHeroModel;
        for (int i = 0; i < this.heroCount; i++) {
            curHeroModel = this.heroGallaryController.selectHeroModel();
            System.out.println(curHeroModel.getName() + " acquired successfully!");
            addHeroObject(i, curHeroModel);
        }
    }

    public void addHeroObject(int heroIndex, HeroModel heroModel) {
        this.heroObjects.add(new HeroObject(heroIndex, heroModel, this.heroLevelCap));
    }

    public void addHeroObject(HeroObject heroObject) {
        this.heroObjects.add(heroObject);
    }

    public int getNumOfHero() {
        return this.heroObjects.size();
    }

    public HeroObject getHeroAtIndex(int index) {
        if (index < 0 || index >= this.getNumOfHero()) {
            System.out.println("In player.java, index out of range for getHeroAtIndex()");
            System.exit(1);
        }
        return this.getHeroObjects().get(index);
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public char getMarker() {
        return this.marker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<HeroObject> getHeroObjects() {
        return heroObjects;
    }

    public int[] getPos() {
        int[] pos = new int[2];
        pos[0] = this.posY;
        pos[1] = this.posX;
        return pos;
    }

}
