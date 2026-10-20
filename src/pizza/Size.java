package pizza;

public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String label;
    Size(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
