package meals;

import java.util.Objects;

/** A food product created by a category factory. */
public final class Food {
    private final String name;
    private final Category category;
    public Food(String name, Category category) {
        this.name = Objects.requireNonNull(name);
        this.category = Objects.requireNonNull(category);
    }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    @Override public String toString() { return name; }
}
