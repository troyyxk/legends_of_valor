public class MonsterNexus implements Placeable {
    char marker;
    boolean accessible, hostile;

    public MonsterNexus() {
        this.marker = 'H';
        this.accessible = false;
        this.hostile = false;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public boolean isHostile() {
        return hostile;
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
