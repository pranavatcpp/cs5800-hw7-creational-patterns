package pizza;

import java.util.Arrays;
import java.util.List;
import static pizza.Chain.*;
import static pizza.Size.*;

public final class PizzaDriver {
    public static List<Pizza> initialPizzas() {
        return Arrays.asList(
            new Pizza.Builder(PIZZA_HUT, SMALL)
                .pepperoni().sausage().mushrooms().build(),
            new Pizza.Builder(PIZZA_HUT, MEDIUM)
                .bacon().onions().extraCheese().peppers().chicken().olives().build(),
            new Pizza.Builder(PIZZA_HUT, LARGE)
                .pepperoni().sausage().mushrooms().bacon().onions()
                .extraCheese().peppers().chicken().olives().build()
        );
    }

    public static List<Pizza> expandedPizzas() {
        return Arrays.asList(
            new Pizza.Builder(PIZZA_HUT, LARGE)
                .pepperoni().mushrooms().extraCheese().build(),
            new Pizza.Builder(PIZZA_HUT, SMALL)
                .ham().pesto().build(),
            new Pizza.Builder(LITTLE_CAESARS, MEDIUM)
                .sausage().bacon().onions().peppers().chicken()
                .olives().spinach().tomatoAndBasil().build(),
            new Pizza.Builder(LITTLE_CAESARS, SMALL)
                .pepperoni().mushrooms().extraCheese().beef().spicyPork()
                .hamAndPineapple().build(),
            new Pizza.Builder(DOMINOS, SMALL).hamAndPineapple().build(),
            new Pizza.Builder(DOMINOS, LARGE)
                .spinach().tomatoAndBasil().pesto().build()
        );
    }

    public static void main(String[] args) {
        System.out.println("Part 1: Three Pizza Hut pizzas");
        initialPizzas().forEach(Pizza::eat);
        System.out.println("\nPart 2: Three pizza chains");
        expandedPizzas().forEach(Pizza::eat);
    }
}
