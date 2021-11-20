/**
 * class Cell
 * 
 *  Variables:
 * 
 *          Placeable staticObject:  HostileEmptyCell / NonAccessibleCell /....
 * 
 *          Movable movalbeObject:  Player object
 * 
 *          PlaceableFactory placeableFactory: 
*                                                  Pleacable getPlaceable(String placeableType):
 *                                          
 *                                                              return a cell/Pleacable object speficed by "placeableType"
 * 
 *  Constructor:
 * 
 *          Cell():
 * 
 *          Cell(Placeable newObject):
 * 
 *  Methods:
 *  
 *          boolean isOccupied():
 * 
 *          boolean isAccessible():
 * 
 *          boolean isAvailable():
 * 
 *          boolean isMarket():
 * 
 *          boolean isHostile() :
 * 
 *          void removeMovable():
 *                  
 *                                      set this.movalbeObject   = null
 * 
 *          char getMarker():
 *                      
 *                                      return the player's marker
 * 
 *          Placeable getStaticObject():
 * 
 *                                      return a HostileEmptyCell object / NonAccessibleCell object /  ....
 * 
 *          void setStaticObject(Placeable newObject):
 * 
 *                                      set this.staticObject to "newObject"
 *          
 *          Movable getMovableObject():
 * 
 * 
 *          void setMovableObject(Movable movealbeObject):
 * 
 *                                      set this.movableObject to "movealbeObject"
 *     
 */
public class Cell {
    private Placeable staticObject;
    private Movable heroObject, monsterObject;
    private PlaceableFactory placeableFactory;
    private boolean isExplored;

    // empty initializer
    public Cell() {
        this.placeableFactory = new PlaceableFactory();
        this.staticObject = placeableFactory.getPlaceable("Plain");
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

    public boolean hasHero()  {
        return !(this.heroObject == null);
    }
    public boolean hasMonster() {
        return !(this.monsterObject == null);
    }


    public boolean isAccessible() {
        return this.staticObject.isAccessible();
    }

    public boolean isAvailable() {
        return (this.isAccessible() && !this.isOccupied());
    }

    public boolean isNexus() {
        return this.staticObject.isNexus();
    }

    public boolean isHostile() {
        return this.staticObject.isHostile();
    }

    public boolean isPlain() {
        return this.staticObject.isPlain();
    }

    public boolean isBush() {
        return this.staticObject.isBush();
    }

    public boolean isCave() {
        return this.staticObject.isCave();
    }

    public boolean isKoulou() {
        return this.staticObject.isKoulou();
    }

    public void removeHero() {
        this.heroObject = null;
    }

    public void removeMonster() {
        this.monsterObject = null;
    }

    public char getHeroMarker() {
        if (this.heroObject != null) {
            return this.heroObject.getMarker();
        }
        return ' ';
    }

    public char getMonsterMarker() {
        if (this.monsterObject != null) {
            return this.monsterObject.getMarker();
        }
        return ' ';
    }



    public char getMarker() {
        if (this.heroObject != null) {
            return this.heroObject.getMarker();
        }
        if (this.monsterObject != null) {
            return this.monsterObject.getMarker();
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
        if ((this.staticObject.isNexus()) && (!this.staticObject.isHostile())) {
            this.setExplored(true);
        }
    }

    public void setStaticObject(String placeableType) {
        this.staticObject = this.placeableFactory.getPlaceable(placeableType);
        if ((this.staticObject.isNexus()) && (!this.staticObject.isHostile())) {
            this.setExplored(true);
        }
    }

    public Movable getMovableObject() {
        return heroObject;
    }

    public void setHeroObject(Movable heroObject) {
        this.heroObject = heroObject;
    }

    public Movable getMonsterObject() {
        return monsterObject;
    }

    public void setMonsterObject(Movable monsterObject) {
        this.monsterObject = monsterObject;
    }

}
