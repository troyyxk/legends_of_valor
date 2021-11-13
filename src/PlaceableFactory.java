public class PlaceableFactory {
    //use getPlaceable method to get object of type Placeable
    public Placeable getPlaceable(String placeableType) {
        if (placeableType == null) {
            System.out.println("In PlaceableFactory.java, placeableType should not be null!");
            System.exit(1);
        }
        if (placeableType.equalsIgnoreCase("HostileEmptyCell")) {
            return new HostileEmptyCell();
        } else if (placeableType.equalsIgnoreCase("NonAccessibleCell")) {
            return new NonAccessibleCell();
        } else if (placeableType.equalsIgnoreCase("HeroNexus")) {
            return new HeroNexus();
        } else if (placeableType.equalsIgnoreCase("MonsterNexus")) {
            return new MonsterNexus();
        } else if (placeableType.equalsIgnoreCase("MarketCell")) {
            return new MarketCell();
        }
        System.out.println("In PlaceableFactory.java, placeableType has no matches cell type!");
        System.exit(1);
        return null;
    }
}
