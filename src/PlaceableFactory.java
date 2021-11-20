/**
 * Factor design pattern
 */
public class PlaceableFactory {
    //use getPlaceable method to get object of type Placeable
    public Placeable getPlaceable(String placeableType){
        if(placeableType == null){
            System.out.println("In PlaceableFactory.java, placeableType should not be null!");
            System.exit(1);
        }
        if(placeableType.equalsIgnoreCase("Plain")){
            return new PlainCell();
        }
        else if(placeableType.equalsIgnoreCase("NonAccessible")){
            return new NonAccessibleCell();
        }
        else if(placeableType.equalsIgnoreCase("HeroNexus")) {
            return new HeroNexusCell();
        }
        else if(placeableType.equalsIgnoreCase("EnermyNexus")) {
            return new EnermyNexusCell();
        }
        else if(placeableType.equalsIgnoreCase("Bush")) {
            return new BushCell();
        }
        else if(placeableType.equalsIgnoreCase("Cave")) {
            return new CaveCell();
        }
        else if(placeableType.equalsIgnoreCase("Koulou")) {
            return new KoulouCell();
        }
        System.out.println("In PlaceableFactory.java, placeableType has no matches cell type!");
        System.exit(1);
        return null;
    }
}
