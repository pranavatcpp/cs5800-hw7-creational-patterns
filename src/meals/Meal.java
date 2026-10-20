package meals;

import java.util.Objects;

public final class Meal {
    private final Food carbs;
    private final Food protein;
    private final Food fats;
    public Meal(Food carbs, Food protein, Food fats) {
        this.carbs = requireCategory(carbs, Category.CARBS);
        this.protein = requireCategory(protein, Category.PROTEIN);
        this.fats = requireCategory(fats, Category.FATS);
    }
    private static Food requireCategory(Food food, Category expected) {
        Objects.requireNonNull(food, "Meal item is required");
        if (food.getCategory() != expected) throw new IllegalArgumentException("Expected " + expected);
        return food;
    }
    public Food getCarbs() { return carbs; }
    public Food getProtein() { return protein; }
    public Food getFats() { return fats; }
    @Override public String toString() {
        return "Carbs: " + carbs + " | Protein: " + protein + " | Fats: " + fats;
    }
}
