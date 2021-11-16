public class CaveCell implements Placeable {
    char marker;
    boolean accessible, hostile;

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
