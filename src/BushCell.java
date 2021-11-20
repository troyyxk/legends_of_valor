public class BushCell implements Placeable {
    private char marker;
    private boolean accessible, hostile;

    public BushCell() {
        this.marker = 'B';
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
        return true;
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
