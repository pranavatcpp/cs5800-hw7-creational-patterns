package meals;

import java.util.Objects;

/** Client depends on the abstract factory contract. */
public final class MealPlanner {
    private final AbstractMacronutrientFactory factory;
    public MealPlanner(AbstractMacronutrientFactory factory) {
        this.factory = Objects.requireNonNull(factory);
    }
    public Meal createMeal(Customer customer) {
        DietPlan diet = Objects.requireNonNull(customer).getDietPlan();
        return new Meal(factory.createCarbFactory().createFood(diet),
                factory.createProteinFactory().createFood(diet),
                factory.createFatFactory().createFood(diet));
    }
}
