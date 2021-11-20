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
 *  class EnermyNexusCell :
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
 *          EnermyNexusCell():
 * 
 *                      empty constructor
 * 
 *  Methods:
 * 
 *          
 */
public class EnermyNexusCell implements Placeable {
    private char marker;
    private boolean accessible, hostile;

    public EnermyNexusCell() {
        this.marker = 'H';
        this.accessible = true;
        this.hostile = true;
    }

    public boolean isAccessible(){
        return accessible;
    }

    public boolean isHostile() {
        return hostile;
    }

    @Override
    public boolean isNexus() {
        return true;
    }

    @Override
    public boolean isEnermyNexus() {
        return true;
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
        return false;
    }

    @Override
    public boolean isKoulou() {
        return false;
    }

    public boolean isMarket() {
        return true;
    }

    // getter and setter
    public char getMarker() {
        return this.marker;
    }

    public void setMarker(char newMarker) {
        this.marker = newMarker;
    }
}
