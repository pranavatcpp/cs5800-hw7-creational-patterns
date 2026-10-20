package meals;

import java.util.Objects;

public final class Customer {
    private final String name;
    private final DietPlan dietPlan;
    public Customer(String name, DietPlan dietPlan) {
        this.name = Objects.requireNonNull(name, "Customer name is required").trim();
        if (this.name.isEmpty()) throw new IllegalArgumentException("Customer name is empty");
        this.dietPlan = Objects.requireNonNull(dietPlan, "Diet plan is required");
    }
    public String getName() { return name; }
    public DietPlan getDietPlan() { return dietPlan; }
}
