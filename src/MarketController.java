import java.util.ArrayList;

public class MarketController {

    private MarketView marketView;
    private MarketModel marketModel;

    public MarketController() {
        this.marketView = new MarketView();
        this.marketModel = new MarketModel();
    }

    public void startPurchase(HeroObject heroObject) {
        this.printWelcomeMessage(heroObject);
        this.marketView.show(this.marketModel);
    }

    public void printProducts() {
        this.marketView.show(this.marketModel);
    }

    public void printWelcomeMessage(HeroObject heroObject) {
        System.out.println("--- Welcome to the shop, " + heroObject.getName() + "! ---");
    }

    public void startShopping(HeroObject heroObject) {
        printWelcomeMessage(heroObject);
        printProducts();
        while (true) {
            System.out.println("Options: ");
            System.out.println("    [I]: Show Info");
            System.out.println("    [B]: Buy Item");
            System.out.println("    [S]: Sell Item");
            System.out.println("    [Q]: Quit Shopping");

            ArrayList<Character> options = new ArrayList<Character>();
            options.add('I');
            options.add('B');
            options.add('S');
            options.add('Q');

            char userInput = Utils.takeOptionInput(options);
            // info
            if (userInput == 'I') {
                System.out.println("Show info!");
                heroObject.getHeroObjectView().printStatus(heroObject);
            }
            // buy
            if (userInput == 'B') {
                printProducts();
                purchase(heroObject);
            }
            // sell
            if (userInput == 'S') {
                sell(heroObject);
            }
            // quit
            if (userInput == 'Q') {
                System.out.println("Stop shopping!");
                return;
            }

        }
    }

    public void sell(HeroObject heroObject) {
        heroObject.getHeroObjectView().printStock(heroObject);
        System.out.println("What do you want to sell?");
        System.out.println("[0] for Weapon");
        System.out.println("[1] for Armory");
        System.out.println("[2] for Portion");
        System.out.println("[3] for Spell");
        int playerInput = Utils.takeIntInput(0, 3);
        if (playerInput == 0) {
            if (heroObject.getStockWeapons().size() == 0) {
                System.out.println("Nothing to sell!");
                return;
            }
            System.out.println("Which weapon do you want to sell? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, heroObject.getStockWeapons().size() - 1);
            Weapon curWeapon = heroObject.getStockWeapons().get(playerInput1);
            heroObject.addMoney(curWeapon.getCost() / 2);
            heroObject.getStockWeapons().remove(playerInput1);
        }
        if (playerInput == 1) {
            if (heroObject.getStockArmory().size() == 0) {
                System.out.println("Nothing to sell!");
                return;
            }
            System.out.println("Which weapon do you want to sell? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, heroObject.getStockArmory().size() - 1);
            Armory curArmory = heroObject.getStockArmory().get(playerInput1);
            heroObject.addMoney(curArmory.getCost() / 2);
            heroObject.getStockArmory().remove(playerInput1);
        }
        if (playerInput == 2) {
            if (heroObject.getStockPotion().size() == 0) {
                System.out.println("Nothing to sell!");
                return;
            }
            System.out.println("Which weapon do you want to sell? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, heroObject.getStockPotion().size() - 1);
            Potion curPotion = heroObject.getStockPotion().get(playerInput1);
            heroObject.addMoney(curPotion.getCost() / 2);
            heroObject.getStockPotion().remove(playerInput1);
        }
        if (playerInput == 3) {
            if (heroObject.getStockSpell().size() == 0) {
                System.out.println("Nothing to sell!");
                return;
            }
            System.out.println("Which weapon do you want to sell? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, heroObject.getStockSpell().size() - 1);
            Spell curSpell = heroObject.getStockSpell().get(playerInput1);
            heroObject.addMoney(curSpell.getCost() / 2);
            heroObject.getStockSpell().remove(playerInput1);
        }

    }

    public void purchase(HeroObject heroObject) {
        printProducts();
        System.out.println("What type of product you want to buy?");
        System.out.println("[0] for Weapon");
        System.out.println("[1] for Armory");
        System.out.println("[2] for Portion");
        System.out.println("[3] for Fire Spell");
        System.out.println("[4] for Ice Spell");
        System.out.println("[5] for Lightning Spell");
        int playerInput = Utils.takeIntInput(0, 5);
        if (playerInput == 0) {
            ArrayList<Weapon> curProducts;
            curProducts = marketModel.getWeapons();
            System.out.println("Which weapon do you want to buy? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curProducts.size() - 1);
            Weapon curWeapon = curProducts.get(playerInput1);
            if (curWeapon.isPurchasable(heroObject)) {
                System.out.println("Successfully purchased item!");
                heroObject.purchaseWeapon(curWeapon);
            } else {
                System.out.println("Unable to purchase due level or money");
            }
        } else if (playerInput == 1) {
            ArrayList<Armory> curProducts;
            curProducts = marketModel.getArmories();
            System.out.println("Which armory do you want to buy? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curProducts.size() - 1);
            Armory curArmory = curProducts.get(playerInput1);
            if (curArmory.isPurchasable(heroObject)) {
                System.out.println("Successfully purchased item!");
                heroObject.purchaseArmory(curArmory);
            } else {
                System.out.println("Unable to purchase due level or money");
            }
        } else if (playerInput == 2) {
            ArrayList<Potion> curProducts;
            curProducts = marketModel.getPotions();
            System.out.println("Which potion do you want to buy? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curProducts.size() - 1);
            Potion curPotion = curProducts.get(playerInput1);
            if (curPotion.isPurchasable(heroObject)) {
                System.out.println("Successfully purchased item!");
                heroObject.purchasePotion(curPotion);
            } else {
                System.out.println("Unable to purchase due level or money");
            }
        } else if (playerInput == 3) {
            ArrayList<FireSpell> curProducts;
            curProducts = marketModel.getFireSpells();
            System.out.println("Which fire spell do you want to buy? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curProducts.size() - 1);
            Spell curSpell = curProducts.get(playerInput1);
            if (curSpell.isPurchasable(heroObject)) {
                System.out.println("Successfully purchased item!");
                heroObject.purchaseSpell(curSpell);
            } else {
                System.out.println("Unable to purchase due level or money");
            }
        } else if (playerInput == 4) {
            ArrayList<IceSpell> curProducts;
            curProducts = marketModel.getIceSpells();
            System.out.println("Which ice spell do you want to buy? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curProducts.size() - 1);
            Spell curSpell = curProducts.get(playerInput1);
            if (curSpell.isPurchasable(heroObject)) {
                System.out.println("Successfully purchased item!");
                heroObject.purchaseSpell(curSpell);
            } else {
                System.out.println("Unable to purchase due level or money");
            }
        } else if (playerInput == 5) {
            ArrayList<LightningSpell> curProducts;
            curProducts = marketModel.getLightningSpells();
            System.out.println("Which lightning spell do you want to buy? Enter the index");
            int playerInput1 = Utils.takeIntInput(0, curProducts.size() - 1);
            Spell curSpell = curProducts.get(playerInput1);
            if (curSpell.isPurchasable(heroObject)) {
                System.out.println("Successfully purchased item!");
                heroObject.purchaseSpell(curSpell);
            } else {
                System.out.println("Unable to purchase due level or money");
            }
        }
    }
}
