package meals;

import java.util.Objects;
import java.util.Random;

/** Singleton factory for the carbs category. */
public final class CarbFactory extends FoodFactory {
    private static final CarbFactory INSTANCE = new CarbFactory();
    private CarbFactory() { }
    public static CarbFactory getInstance() { return INSTANCE; }

    @Override public Food createFood(DietPlan diet, Random random) {
        Objects.requireNonNull(diet, "Diet plan is required");
        String[] options;
        switch (diet) {
            case NO_RESTRICTION:
                options = new String[] {"Cheese", "Bread", "Lentils", "Pistachio"};
                break;
            case PALEO:
                options = new String[] {"Pistachio"};
                break;
            case VEGAN:
                options = new String[] {"Bread", "Lentils", "Pistachio"};
                break;
            case NUT_ALLERGY:
                options = new String[] {"Cheese", "Bread", "Lentils"};
                break;
            default: throw new IllegalArgumentException("Unsupported diet: " + diet);
        }
        return choose(Category.CARBS, options, random);
    }
}
