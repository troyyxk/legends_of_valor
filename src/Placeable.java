/**
 * Interface Placeable:
 * 
 *  Methods:
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
 */
public interface Placeable {
    public abstract boolean isAccessible();
    public abstract boolean isHostile();
    public abstract boolean isNexus();
    public abstract boolean isEnermyNexus();
    public abstract boolean isPlain();
    public abstract boolean isBush();
    public abstract boolean isCave();
    public abstract boolean isKoulou();

    public abstract char getMarker();
    public abstract void setMarker(char newMarker);
}
