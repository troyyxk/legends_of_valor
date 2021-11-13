public class Cell {
    private Placeable staticObject;
    private Movable heroObject, monsterObject;
    private PlaceableFactory placeableFactory;
    private boolean isExplored;

    // empty initializer
    public Cell() {
        this.placeableFactory = new PlaceableFactory();
        this.staticObject = placeableFactory.getPlaceable("HostileEmptyCell");
        this.heroObject = null;
        this.monsterObject = null;
        this.isExplored = false;
    }

    // initialize with an object
    public Cell(Placeable newObject) {
        this.staticObject = newObject;
    }

    public boolean isOccupied() {
        return !(this.heroObject == null);
    }

    public boolean isAccessible() {
        return this.staticObject.isAccessible();
    }

    public boolean isAvailable() {
        return (this.isAccessible() && !this.isOccupied());
    }

    public boolean isMarket() {
        return this.staticObject.isNexus();
    }

    public boolean isHostile() {
        return this.staticObject.isHostile();
    }

    public void removeMovable() {
        this.heroObject = null;
    }

    public char getMarker() {
        if (this.isOccupied()) {
            return this.heroObject.getMarker();
        }
        return this.staticObject.getMarker();
    }

    public void getVisited() {
        setExplored(true);
    }

    // getters and setters
    public Placeable getStaticObject() {
        return staticObject;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    public void setStaticObject(Placeable newObject) {
        this.staticObject = newObject;
    }

    public void setStaticObject(String placeableType) {
        this.staticObject = this.placeableFactory.getPlaceable(placeableType);
    }

    public Movable getMovableObject() {
        return heroObject;
    }

    public void setMovableObject(Movable movealbeObject) {
        this.heroObject = movealbeObject;
    }
}
