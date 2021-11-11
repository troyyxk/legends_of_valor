public class HeroGalleryView {
    public void show(HeroGalleryModel heroGalleryModel) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Heros on Gallary:");

        int index = 0;

        // paladin
        System.out.println("[0] Paladin: ");
        printHeroHeader();
        index = 0;
        for (Paladin curPaladin : heroGalleryModel.getPaladin()) {
            printHero(index, curPaladin);
            index++;
        }

        // sorcerer
        System.out.println("[1] Sorcerer: ");
        printHeroHeader();
        index = 0;
        for (Sorcerer curSorcerer : heroGalleryModel.getSorcerers()) {
            printHero(index, curSorcerer);
            index++;
        }

        // warrior
        System.out.println("[2] Warrior: ");
        printHeroHeader();
        index = 0;
        for (Warrior curWarrior : heroGalleryModel.getWarriors()) {
            printHero(index, curWarrior);
            index++;
        }

    }

    private void printHeroHeader()  {
        System.out.print("        ");
        System.out.println("Name/mana/strength/agility/dexterity/starting money/starting experience");
    }

    private void printHero(int index, HeroModel heroModel) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(heroModel.getName());
        System.out.print(" ");
        System.out.print(heroModel.getMana());
        System.out.print(" ");
        System.out.print(heroModel.getStrength());
        System.out.print(" ");
        System.out.print(heroModel.getAgility());
        System.out.print(" ");
        System.out.print(heroModel.getDexterity());
        System.out.print(" ");
        System.out.print(heroModel.getStartingMoney());
        System.out.print(" ");
        System.out.print(heroModel.getStartingExperience());
        System.out.println("");
    }

}
