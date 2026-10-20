package meals;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/** Factory method abstraction shared by the three product categories. */
public abstract class FoodFactory {
    public final Food createFood(DietPlan diet) {
        return createFood(diet, ThreadLocalRandom.current());
    }

    // A Random overload permits deterministic verification of every choice.
    public abstract Food createFood(DietPlan diet, Random random);

    protected final Food choose(Category category, String[] options, Random random) {
        Objects.requireNonNull(random, "Random generator is required");
        return new Food(options[random.nextInt(options.length)], category);
    }
}
