package meals;

public final class MealDriver {
    public static Customer[] customers() {
        return new Customer[] {
            new Customer("Alex", DietPlan.NO_RESTRICTION),
            new Customer("Blair", DietPlan.PALEO),
            new Customer("Casey", DietPlan.VEGAN),
            new Customer("Devon", DietPlan.NUT_ALLERGY),
            new Customer("Emery", DietPlan.NO_RESTRICTION),
            new Customer("Finley", DietPlan.VEGAN)
        };
    }
    public static void main(String[] args) {
        MealPlanner planner = new MealPlanner(MacronutrientFactory.getInstance());
        for (Customer customer : customers()) {
            Meal meal = planner.createMeal(customer);
            System.out.println(customer.getName() + " (" + customer.getDietPlan() + ") | " + meal);
        }
    }
}
