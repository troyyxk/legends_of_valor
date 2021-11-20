import java.util.ArrayList;

/**
 * class MonsterGallary:
 * 
 *  Variables:
 * 
 *      ArrayList<MonsterModel> allMonsterModels:
 * 
 *      ArrayList<Dragon> allDragons:
 * 
 *      ArrayList<Exoskeleton> allExoskeletons:
 * 
 *      ArrayList<Spirit> allSpirits:
 * 
 *  Constructor:
 * 
 *      MonsterGallary() :
 *                      
 *                          loaded monsters from local txt files
 *  Methods:
 * 
 *      void getAllDragons(String filePath):
 * 
 *      void getAllExoskeletons(String filePath):
 * 
 *      void getAllSpirits(String filePath):
 * 
 *      // getter and setter
 * 
 *      ...
 */
public class MonsterGallary {
    private ArrayList<MonsterModel> allMonsterModels;
    private ArrayList<Dragon> allDragons;
    private ArrayList<Exoskeleton> allExoskeletons;
    private ArrayList<Spirit> allSpirits;

    public MonsterGallary() {
        this.allDragons = new ArrayList<Dragon>();
        this.allExoskeletons = new ArrayList<Exoskeleton>();
        this.allSpirits = new ArrayList<Spirit>();

        getAllDragons("./Legends_Monsters_and_Heroes/Dragons.txt");
        getAllExoskeletons("./Legends_Monsters_and_Heroes/Exoskeletons.txt");
        getAllSpirits("./Legends_Monsters_and_Heroes/Spirits.txt");

        this.allMonsterModels = new ArrayList<MonsterModel>();
        this.allMonsterModels.addAll(this.allDragons);
        this.allMonsterModels.addAll(this.allExoskeletons);
        this.allMonsterModels.addAll(this.allSpirits);
    }

    public void getAllDragons(String filePath) {
        ArrayList<ArrayList<String>> allDragonAttributes = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curDragon : allDragonAttributes) {
            assert curDragon.size() == 5;
            this.allDragons.add(new Dragon(curDragon.get(0),
                            Integer.parseInt(curDragon.get(1)),
                            Integer.parseInt(curDragon.get(2)),
                            Integer.parseInt(curDragon.get(3)),
                            Integer.parseInt(curDragon.get(4))
                    )
            );
        }
    }

    public void getAllExoskeletons(String filePath) {
        ArrayList<ArrayList<String>> allExoskeletonAttributes = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curExoskeleton : allExoskeletonAttributes) {
            assert curExoskeleton.size() == 5;
            this.allExoskeletons.add(new Exoskeleton(curExoskeleton.get(0),
                            Integer.parseInt(curExoskeleton.get(1)),
                            Integer.parseInt(curExoskeleton.get(2)),
                            Integer.parseInt(curExoskeleton.get(3)),
                            Integer.parseInt(curExoskeleton.get(4))
                    )
            );
        }
    }

    public void getAllSpirits(String filePath) {
        ArrayList<ArrayList<String>> allSpiritAttributes = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curSpirit : allSpiritAttributes) {
            assert curSpirit.size() == 5;
            this.allSpirits.add(new Spirit(curSpirit.get(0),
                            Integer.parseInt(curSpirit.get(1)),
                            Integer.parseInt(curSpirit.get(2)),
                            Integer.parseInt(curSpirit.get(3)),
                            Integer.parseInt(curSpirit.get(4))
                    )
            );
        }
    }

    // getter and setter
    public ArrayList<MonsterModel> getAllMonsterModels() {
        return allMonsterModels;
    }

    public void setAllMonsterModels(ArrayList<MonsterModel> allMonsterModels) {
        this.allMonsterModels = allMonsterModels;
    }

    public ArrayList<Dragon> getAllDragons() {
        return allDragons;
    }

    public void setAllDragons(ArrayList<Dragon> allDragons) {
        this.allDragons = allDragons;
    }

    public ArrayList<Exoskeleton> getAllExoskeletons() {
        return allExoskeletons;
    }

    public void setAllExoskeletons(ArrayList<Exoskeleton> allExoskeletons) {
        this.allExoskeletons = allExoskeletons;
    }

    public ArrayList<Spirit> getAllSpirits() {
        return allSpirits;
    }

    public void setAllSpirits(ArrayList<Spirit> allSpirits) {
        this.allSpirits = allSpirits;
    }

}
