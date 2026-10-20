package meals;

public enum Category {
    CARBS("Carbs"),
    PROTEIN("Protein"),
    FATS("Fats");

    private final String label;
    Category(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
