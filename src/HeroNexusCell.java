public class HeroNexusCell implements Placeable {
    private char marker;
    private boolean accessible, hostile;

    public HeroNexusCell() {
        this.marker = 'H';
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
        return true;
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
