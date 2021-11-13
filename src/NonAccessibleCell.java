public class NonAccessibleCell implements Placeable {
    char marker;
    boolean accessible, hostile;

    public NonAccessibleCell() {
        this.marker = 'X';
        this.accessible = false;
        this.hostile = true;
    }

    public boolean isAccessible(){
        return accessible;
    }

    public boolean isHostile() {
        System.out.println("In NonAccessibleCell.java, should not access isHostile()");
        System.exit(1);
        return hostile;
    }

    public boolean isNexus() {
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
