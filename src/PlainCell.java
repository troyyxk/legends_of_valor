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
 *  class PlainCell :
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
 *          PlainCell():
 * 
 *                      empty constructor
 * 
 *  Methods:
 * 
 *          
 */
public class PlainCell implements Placeable {
    private char marker;
    private boolean accessible, hostile;

    public PlainCell() {
        this.marker = ' ';
        this.accessible = true;
        this.hostile = true;
    }

    public boolean isAccessible() {
        return this.accessible;
    }

    public boolean isHostile() {
        return this.hostile;
    }

    public boolean isNexus() {
        return false;
    }

    @Override
    public boolean isPlain() {
        return true;
    }

    @Override
    public boolean isEnermyNexus() {
        return false;
    }

    @Override
    public boolean isBush() {
        return false;
    }

    @Override
    public boolean isCave() {
        return false;
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
