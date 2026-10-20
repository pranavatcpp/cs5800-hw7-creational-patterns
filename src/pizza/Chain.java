package pizza;

public enum Chain {
    PIZZA_HUT("Pizza Hut"),
    LITTLE_CAESARS("Little Caesars"),
    DOMINOS("Dominos");

    private final String label;
    Chain(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
