package meals;

public enum DietPlan {
    NO_RESTRICTION("No Restriction"),
    PALEO("Paleo"),
    VEGAN("Vegan"),
    NUT_ALLERGY("Nut Allergy");

    private final String label;
    DietPlan(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
