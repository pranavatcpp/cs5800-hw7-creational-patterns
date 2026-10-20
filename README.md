# HW7 Creational Design Patterns

This Java project implements both parts of the assignment: pizzas built with optional toppings, and randomized meals that respect each customer's diet. It uses only the Java standard library.

## Compile and run

With JDK 9 or newer, open a terminal in this folder:

```sh
sh run.sh
sh test.sh
```

The scripts compile Java 8 compatible bytecode. Newer JDKs may warn that Java 8 is an obsolete compilation target; these warnings do not prevent compilation. Tested with JDK 24.

To compile manually (including with JDK 8):

```sh
mkdir -p build
javac -d build src/pizza/*.java src/meals/*.java
java -cp build pizza.PizzaDriver
java -cp build meals.MealDriver
```

In an IDE, open this folder as a Java project, mark `src` as the source directory, and run `pizza.PizzaDriver` and `meals.MealDriver`.

## Pizza Builder

`Pizza.Builder` requires a chain and size in its constructor. A missing or null size cannot produce a pizza. Optional toppings use fluent methods such as `pepperoni()` and `extraCheese()`. Each method returns the builder, and `build()` creates the finished pizza.

```java
Pizza pizza = new Pizza.Builder(Chain.PIZZA_HUT, Size.LARGE)
        .pepperoni()
        .mushrooms()
        .extraCheese()
        .build();
pizza.eat();
```

All 16 toppings are supported for every chain and size. `Pizza` stores an immutable copy of the selected toppings, so reusing a builder cannot alter an existing pizza. Repeated selection of a topping does not duplicate it.

The driver first creates Small, Medium, and Large Pizza Hut pizzas with 3, 6, and 9 toppings. It then creates the six additional pizzas:

| Chain | Size | Topping count |
| --- | --- | --- |
| Pizza Hut | Large | 3 |
| Pizza Hut | Small | 2 |
| Little Caesars | Medium | 8 |
| Little Caesars | Small | 6 |
| Dominos | Small | 1 |
| Dominos | Large | 3 |

Every pizza is passed to `eat()`, which prints its chain, size, and selected toppings.

## Meal factories

`AbstractMacronutrientFactory` is the Abstract Factory interface. Its three methods supply a family of factories: one for carbs, one for protein, and one for fats. `MacronutrientFactory` implements that interface, returning the existing category factory instances.

`FoodFactory` defines the factory method `createFood()`. `CarbFactory`, `ProteinFactory`, and `FatFactory` implement it by selecting randomly from the permitted foods and constructing a new `Food` product. Each product records its name and category.

All four concrete factories use Singleton: a private constructor, a private static final instance, and a public `getInstance()` method. Class initialization safely creates one instance per factory. Customer diet choices are passed into methods rather than saved in the shared factory.

`MealPlanner` depends on the abstract factory interface. For each customer, it requests one product from each category factory and assembles a `Meal`. `Meal` verifies the three categories. `MealDriver` creates six customers covering all four diet plans.

## Allowed foods

The implementation preserves the assignment's food categories, including cheese as carbs and tuna as fats. Choices are uniformly randomized within each allowed category.

| Diet | Carbs | Protein | Fats |
| --- | --- | --- | --- |
| No Restriction | Cheese, Bread, Lentils, Pistachio | Fish, Chicken, Beef, Tofu | Avocado, Sour cream, Tuna, Peanuts |
| Paleo | Pistachio | Fish, Chicken, Beef | Avocado, Tuna, Peanuts |
| Vegan | Bread, Lentils, Pistachio | Tofu | Avocado, Peanuts |
| Nut Allergy | Cheese, Bread, Lentils | Fish, Chicken, Beef, Tofu | Avocado, Sour cream, Tuna |

The assignment says Vegan has “No Meat,” but its parenthetical examples omit beef. This implementation excludes beef as meat. Paleo follows the assignment's explicit restrictions, so peanuts remain available.

## Verification

`tests/AssignmentTests.java` checks all nine pizzas, every printed topping, required sizes, all 16 available toppings, immutable built pizzas, and singleton identity. It supplies controlled random indices to verify each complete set of allowed foods, then generates 2,000 meals to verify restrictions through the normal randomized path. It also checks six customers cover all four diets and rejects invalid inputs.

`output/sample-output.txt` contains an actual run of both drivers. Meals can differ on later runs because selection is random. `output/test-results.txt` records the test result.

## Files

- `src/pizza/`: pizza classes and driver.
- `src/meals/`: meal classes, factories, and driver.
- `tests/`: standalone verification without external test dependencies.
- `run.sh` and `test.sh`: build and run commands.
- `output/`: captured program and test output.

The assignment document does not specify a submission format or require UML diagrams. The ZIP contains the Java sources, scripts, this explanation, and captured output. Check your course submission page for any additional upload requirements.
