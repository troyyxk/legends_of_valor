public interface Placeable {
    public abstract boolean isAccessible();
    public abstract boolean isHostile();
    public abstract boolean isNexus();
    public abstract boolean isPlain();
    public abstract boolean isBush();
    public abstract boolean isCave();
    public abstract boolean isKoulou();

    public abstract char getMarker();
    public abstract void setMarker(char newMarker);
}
