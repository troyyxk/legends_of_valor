public interface Placeable {
    public abstract boolean isAccessible();

    public abstract boolean isHostile();

    public abstract boolean isMarket();

    public abstract char getMarker();

    public abstract void setMarker(char newMarker);
}
