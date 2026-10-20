import meals.*;
import pizza.*;
import java.util.*;
import java.io.*;

public final class AssignmentTests {
    private static int checks;
    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError(message);
    }
    private static void rejects(Runnable action, Class<? extends RuntimeException> type) {
        try { action.run(); } catch (RuntimeException ex) {
            check(type.isInstance(ex), "Unexpected exception " + ex);
            return;
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
    // Exercise each possible random index, without a probabilistic coverage test.
    private static final class IndexRandom extends Random {
        private static final long serialVersionUID = 1L;
        private final int index;
        private final int expectedBound;
        IndexRandom(int index, int expectedBound) {
            this.index = index;
            this.expectedBound = expectedBound;
        }
        @Override public int nextInt(int bound) {
            check(bound == expectedBound, "Incorrect number of allowed choices");
            return index;
        }
    }
    public static void main(String[] args) {
        List<Pizza> pizzas = new ArrayList<>(PizzaDriver.initialPizzas());
        pizzas.addAll(PizzaDriver.expandedPizzas());
        int[] counts = {3, 6, 9, 3, 2, 8, 6, 1, 3};
        Size[] sizes = {Size.SMALL, Size.MEDIUM, Size.LARGE, Size.LARGE,
            Size.SMALL, Size.MEDIUM, Size.SMALL, Size.SMALL, Size.LARGE};
        Chain[] chains = {Chain.PIZZA_HUT, Chain.PIZZA_HUT, Chain.PIZZA_HUT,
            Chain.PIZZA_HUT, Chain.PIZZA_HUT, Chain.LITTLE_CAESARS,
            Chain.LITTLE_CAESARS, Chain.DOMINOS, Chain.DOMINOS};
        check(pizzas.size() == 9, "Nine pizzas required");
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza p = pizzas.get(i);
            check(p.getToppings().size() == counts[i], "Topping count " + i);
            check(p.getSize() == sizes[i] && p.getChain() == chains[i], "Pizza details " + i);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            PrintStream original = System.out;
            try { System.setOut(new PrintStream(output)); p.eat(); }
            finally { System.setOut(original); }
            String text = output.toString();
            check(text.contains(p.getChain().toString()) && text.contains(p.getSize().toString()), "eat details");
            for (Topping t : p.getToppings()) check(text.contains(t.toString()), "eat omitted topping");
        }
        rejects(() -> new Pizza.Builder(Chain.PIZZA_HUT, null), NullPointerException.class);
        rejects(() -> new Pizza.Builder(null, Size.SMALL), NullPointerException.class);
        Pizza.Builder builder = new Pizza.Builder(Chain.DOMINOS, Size.LARGE);
        Pizza plain = builder.build();
        for (Topping topping : Topping.values()) builder.addTopping(topping);
        Pizza all = builder.build();
        check(all.getToppings().size() == 16 && plain.getToppings().isEmpty(), "Builder copied state");
        builder.pepperoni();
        check(builder.build().getToppings().size() == 16, "No duplicate toppings");
        rejects(() -> all.getToppings().clear(), UnsupportedOperationException.class);

        MacronutrientFactory factory = MacronutrientFactory.getInstance();
        check(factory == MacronutrientFactory.getInstance(), "Abstract factory singleton");
        check(factory.createCarbFactory() == CarbFactory.getInstance(), "Carb singleton");
        check(factory.createProteinFactory() == ProteinFactory.getInstance(), "Protein singleton");
        check(factory.createFatFactory() == FatFactory.getInstance(), "Fat singleton");
        FoodFactory[] factories = {factory.createCarbFactory(), factory.createProteinFactory(), factory.createFatFactory()};
        Category[] categories = {Category.CARBS, Category.PROTEIN, Category.FATS};
        // Independent specification of the complete allowed set for each diet/category.
        String[][] expected = {
            {"Cheese,Bread,Lentils,Pistachio", "Fish,Chicken,Beef,Tofu", "Avocado,Sour cream,Tuna,Peanuts"},
            {"Pistachio", "Fish,Chicken,Beef", "Avocado,Tuna,Peanuts"},
            {"Bread,Lentils,Pistachio", "Tofu", "Avocado,Peanuts"},
            {"Cheese,Bread,Lentils", "Fish,Chicken,Beef,Tofu", "Avocado,Sour cream,Tuna"}
        };
        for (DietPlan diet : DietPlan.values()) {
            for (int c = 0; c < factories.length; c++) {
                Set<String> allowed = new HashSet<>(Arrays.asList(expected[diet.ordinal()][c].split(",")));
                Set<String> produced = new HashSet<>();
                for (int i = 0; i < allowed.size(); i++) {
                    Food food = factories[c].createFood(diet, new IndexRandom(i, allowed.size()));
                    check(food.getCategory() == categories[c], "Category mismatch");
                    produced.add(food.getName());
                }
                check(produced.equals(allowed), "Diet choices: " + diet + " / " + categories[c]);
            }
            MealPlanner planner = new MealPlanner(factory);
            for (int i = 0; i < 500; i++) {
                Meal meal = planner.createMeal(new Customer("Test", diet));
                Food[] foods = {meal.getCarbs(), meal.getProtein(), meal.getFats()};
                for (int c = 0; c < 3; c++) {
                    check(Arrays.asList(expected[diet.ordinal()][c].split(",")).contains(foods[c].getName()), "Random meal violated diet");
                }
            }
        }
        check(MealDriver.customers().length == 6, "Six customers required");
        Set<DietPlan> demonstrated = EnumSet.noneOf(DietPlan.class);
        for (Customer c : MealDriver.customers()) demonstrated.add(c.getDietPlan());
        check(demonstrated.size() == 4, "All four diets demonstrated");
        rejects(() -> new Customer(" ", DietPlan.VEGAN), IllegalArgumentException.class);
        rejects(() -> new Customer("Test", null), NullPointerException.class);
        rejects(() -> CarbFactory.getInstance().createFood(null), NullPointerException.class);
        rejects(() -> new Meal(new Food("Tofu", Category.PROTEIN),
            new Food("Beef", Category.PROTEIN), new Food("Tuna", Category.FATS)), IllegalArgumentException.class);
        System.out.println("PASS: " + checks + " checks; all pizza requirements, complete diet choice sets,");
        System.out.println("2,000 random meals, singleton identities, and input validation verified.");
    }
}
