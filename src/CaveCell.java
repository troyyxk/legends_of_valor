/**
 * Predefined Interface Placeable:
 * 
 *  Abstract Methods:
 *  
 *          boolean isAccessible():
 * 
 *          boolean isHostile():
 * 
 *          boolean isNexus():
 * 
 *          boolean isEnermyNexus():
 * 
 *          boolean isPlain():
 * 
 *          boolean isCave():
 * 
 *          boolean isBush():
 * 
 *          boolean isKoulou():
 * 
 *          char getMarker():  return the char representing the cell
 * 
 *          void setMarker(char newMarker): set the cell's marker to be "newMarker"
 * 
 *  class CaveCell :
 * 
 *  Variables:
 * 
 *          char marker:
 * 
 *          boolean accessible:
 * 
 *          boolean hostile:
 * 
 *  Constructors:
 * 
 *          CaveCell():
 * 
 *                      empty constructor
 * 
 *  Methods:
 * 
 *          
 */
public class CaveCell implements Placeable {
    private char marker;
    private boolean accessible, hostile;

    public CaveCell() {
        this.marker = 'C';
        this.accessible = true;
        this.hostile = false;
    }

    public boolean isAccessible(){
        return accessible;
    }

    public boolean isHostile() {
        return hostile;
    }

    @Override
    public boolean isNexus() {
        return false;
    }

    @Override
    public boolean isEnermyNexus() {
        return false;
    }

    @Override
    public boolean isPlain() {
        return false;
    }

    @Override
    public boolean isBush() {
        return false;
    }

    @Override
    public boolean isCave() {
        return true;
    }

    @Override
    public boolean isKoulou() {
        return false;
    }


    // getter and setter
    public char getMarker() {
        return this.marker;
    }

    public void setMarker(char newMarker) {
        this.marker = newMarker;
    }
}
