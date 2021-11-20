/**
 * interface Movable:
 * 
 *  Methods:
 * 
 *          abstract char getMarker():
 *              
 *                          return the Marker which represents this Movable object
 * 
 *          abstract int[] getPos():
 * 
 *                          int [] x, y 
 * 
 *          abstract void setPos(int[] pos):
 * 
 *          
 */
public interface Movable {
    public abstract char getMarker();
    public abstract int[] getPos();
    public abstract void setPos(int[] pos);
}
