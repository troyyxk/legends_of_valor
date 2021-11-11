public class HostileEmptyCell implements Placeable {
    char marker;
    boolean accessible, hostile;

    public HostileEmptyCell() {
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

    public boolean isMarket() {
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
