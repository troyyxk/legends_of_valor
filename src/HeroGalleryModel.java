import java.util.ArrayList;

public class HeroGalleryModel {
    private ArrayList<Paladin> paladins;
    private ArrayList<Sorcerer> sorcerers;
    private ArrayList<Warrior> warriors;

    private ArrayList<ArrayList<HeroModel>> heroCollections;

    public HeroGalleryModel() {
        this.paladins = new ArrayList<Paladin>();
        this.sorcerers = new ArrayList<Sorcerer>();
        this.warriors = new ArrayList<Warrior>();

        this.collectPaladins("Legends_Monsters_and_Heroes/Paladins.txt");
        this.collectSorcerers("Legends_Monsters_and_Heroes/Sorcerers.txt");
        this.collectWarriors("Legends_Monsters_and_Heroes/Warriors.txt");
    }

    private void collectWarriors(String filePath) {
        ArrayList<ArrayList<String>> allWarriors = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curWarrior : allWarriors) {
            assert curWarrior.size() == 7;
            this.warriors.add(new Warrior(curWarrior.get(0),
                            Integer.parseInt(curWarrior.get(1)),
                            Integer.parseInt(curWarrior.get(2)),
                            Integer.parseInt(curWarrior.get(3)),
                            Integer.parseInt(curWarrior.get(4)),
                            Integer.parseInt(curWarrior.get(5)),
                            Integer.parseInt(curWarrior.get(6))
                    )
            );
        }
    }

    private void collectPaladins(String filePath) {
        ArrayList<ArrayList<String>> allPaladins = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curPaladin : allPaladins) {
            assert curPaladin.size() == 7;
            this.paladins.add(new Paladin(curPaladin.get(0),
                    Integer.parseInt(curPaladin.get(1)),
                    Integer.parseInt(curPaladin.get(2)),
                    Integer.parseInt(curPaladin.get(3)),
                    Integer.parseInt(curPaladin.get(4)),
                    Integer.parseInt(curPaladin.get(5)),
                    Integer.parseInt(curPaladin.get(6))
                    )
            );
        }
    }

    private void collectSorcerers(String filePath) {
        ArrayList<ArrayList<String>> allSorcerers = Utils.takeAttributeFromFile(filePath);
        for (ArrayList<String> curSorcerer : allSorcerers) {
            assert curSorcerer.size() == 7;
            this.sorcerers.add(new Sorcerer(curSorcerer.get(0),
                            Integer.parseInt(curSorcerer.get(1)),
                            Integer.parseInt(curSorcerer.get(2)),
                            Integer.parseInt(curSorcerer.get(3)),
                            Integer.parseInt(curSorcerer.get(4)),
                            Integer.parseInt(curSorcerer.get(5)),
                            Integer.parseInt(curSorcerer.get(6))
                    )
            );
        }
    }

    // getter
    public ArrayList<Paladin> getPaladin() {
        return this.paladins;
    }

    public ArrayList<Sorcerer> getSorcerers() {
        return sorcerers;
    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }
}
