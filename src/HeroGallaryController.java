import java.util.ArrayList;

/**
 * Predefined class HeroGalleryModel:
 * 
 *  Variables:
 *          ArrayList<Paladin> paladins:
 * 
 *          ArrayList<Sorcerer> sorcerers:
 * 
 *          ArrayList<Warrior> warriors:
 * 
 *          ArrayList<ArrayList<HeroModel>> heroCollections
 * 
 *  Methods:
 * 
 *          HeroGalleryModel():
 * 
 *                              read in data from local txt files to initilize "paladins", "sorcerers","warriors"
 * 
 *          void collectWarriors(String filePath):
 *      
 *                              read in data from "filePath" and store data into "this.warriors"
 * 
 *          void collectPaladins(String filePath):
 * 
 *          void collectPaladins(String filePath):
 * 
 *          ArrayList<Paladin> getPaladin():
 * 
 *                                      return "this.paladins"
 * 
 *          ArrayList<Sorcerer> getSorcerers():
 * 
 *                                      return "this.sorcerers"
 * 
 *          ArrayList<Warrior> getWarriors():
 * 
 *                                      return "this.warriors"
 * class HeroGallaryController:
 * 
 *  Variables:
 *          
 *              HeroGalleryView heroGalleryView:    a class with methods to print out heroes of HeroGalleryModel object 
 * 
 *              HeroGalleryModel heroGalleryModel:  collection of hero candidates 
 * 
 *  Constructors:
 *              
 *              HeroGallaryController():
 *          
 *                                      empty constructor
 * 
 *  Methods:
 * 
 *              HeroModel selectHeroModel():
 * 
 *                                      return a HeroModel selected by player from the "this.heroGalleryModel"
 *              
 *                          
 */
public class HeroGallaryController {
    private HeroGalleryView heroGalleryView;
    private HeroGalleryModel heroGalleryModel;

    public HeroGallaryController() {
        this.heroGalleryModel = new HeroGalleryModel();
        this.heroGalleryView = new HeroGalleryView();
    }

    public HeroModel selectHeroModel() {
        this.heroGalleryView.show(this.heroGalleryModel);
        System.out.println("What hero type would you pick for this hero?");
        System.out.println("[0] for Paladin");
        System.out.println("[1] for Sorcerer");
        System.out.println("[2] for Warrior");
        int playerInput = Utils.takeIntInput(0, 2);
        if (playerInput == 0) {
            ArrayList<Paladin> curHeroModels;
            curHeroModels = heroGalleryModel.getPaladin();
            System.out.println("Which hero would you pick in Paladin? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curHeroModels.size() - 1);
            return curHeroModels.get(playerInput1);
        }
        else if (playerInput == 1) {
            ArrayList<Sorcerer> curHeroModels;
            curHeroModels = heroGalleryModel.getSorcerers();
            System.out.println("Which hero would you pick in Sorcerer? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curHeroModels.size() - 1);
            return curHeroModels.get(playerInput1);
        }
        else {
            ArrayList<Warrior> curHeroModels;
            curHeroModels = heroGalleryModel.getWarriors();
            System.out.println("Which hero would you pick in Warrior? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curHeroModels.size() - 1);
            return curHeroModels.get(playerInput1);
        }

    }
}
