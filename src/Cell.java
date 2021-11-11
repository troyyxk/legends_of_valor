public class Cell {
    private Placeable staticObject;
    private Movable movalbeObject;
    private PlaceableFactory placeableFactory;

    // empty initializer
    public Cell() {
        this.placeableFactory = new PlaceableFactory();
        this.staticObject = placeableFactory.getPlaceable("HostileEmptyCell");
        this.movalbeObject = null;
    }

    // initialize with an object
    public Cell(Placeable newObject) {
        this.staticObject = newObject;
    }

    public boolean isOccupied() {
        return !(this.movalbeObject == null);
    }

    public boolean isAccessible() {
        return this.staticObject.isAccessible();
    }

    public boolean isAvailable() {
        return (this.isAccessible() && !this.isOccupied());
    }

    public boolean isMarket() {
        return this.staticObject.isMarket();
    }

    public boolean isHostile() {
        return this.staticObject.isHostile();
    }

    public void removeMovable() {
        this.movalbeObject = null;
    }

    public char getMarker() {
        if (this.isOccupied()) {
            return this.movalbeObject.getMarker();
        }
        return this.staticObject.getMarker();
    }

    // getters and setters
    public Placeable getStaticObject() {
        return staticObject;
    }

    public void setStaticObject(Placeable newObject) {
        this.staticObject = newObject;
    }

    public void setStaticObject(String placeableType) {
        this.staticObject = this.placeableFactory.getPlaceable(placeableType);
    }

    public Movable getMovableObject() {
        return movalbeObject;
    }

    public void setMovableObject(Movable movealbeObject) {
        this.movalbeObject = movealbeObject;
    }
}
