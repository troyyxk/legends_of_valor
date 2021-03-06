import java.util.ArrayList;

/**
 * Predefined interface Movable:
 * 
 *  Methods:
 * 
 *          abstract char getMarker():
 *              
 *                          return the Marker which represents this Movable object
 * 
 *          abstract int[] getPos():
 * 
 *                          int [] x, y 
 * 
 *          abstract void setPos(int[] pos):
 * 
 * class Player:
 * 
 *  Variables:
 * 
 *              String name:
 * 
 *              char marker:
 * 
 *              ArrayList<HeroObject> heroObjects:
 * 
 *              int heroLevelCap, heroCount:
 * 
 *              HeroGallaryController heroGallaryController :     allow user to select a hero from the all hero collection   
 * 
 *              int posX, posY:
 * 
 *              int id   
 * 
 *  Constrcutors:
 * 
 *              Player(int id) :
 * 
 *              Player(String name):
 * 
 *              Player(String name, int heroLevelCap):
 * 
 *              void printInfo() :
 * 
 *              HeroObject getFirstHeroObject():
 * 
 *              void addHeroObjects() :
 * 
 *              ....
 * 
 *              //getter and setter
 * 
 *              ...
 * 
 *              
 */
public class Player implements Movable{

    private String name;
    private char marker;
    private ArrayList<HeroObject> heroObjects;
    private int heroLevelCap, heroCount;
    private HeroGallaryController heroGallaryController;
    private int posX, posY;
    private int id;

    public Player(int id) {
        // this.name = Utils.takeName();
        this.name = "";
        this.id = id;
        this.marker = (char) (id + '0');
        this.heroLevelCap = 10;
        this.heroObjects = new ArrayList<HeroObject>();
        this.heroCount = 1;
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


    // get first hero object
    public HeroObject getFirstHeroObject() {
        return this.heroObjects.get(0);
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

    public char getMarker(){
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

    public int getId() {
        return id;
    }

}
