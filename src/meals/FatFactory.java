package meals;

import java.util.Objects;
import java.util.Random;

/** Singleton factory for the fats category. */
public final class FatFactory extends FoodFactory {
    private static final FatFactory INSTANCE = new FatFactory();
    private FatFactory() { }
    public static FatFactory getInstance() { return INSTANCE; }

    @Override public Food createFood(DietPlan diet, Random random) {
        Objects.requireNonNull(diet, "Diet plan is required");
        String[] options;
        switch (diet) {
            case NO_RESTRICTION:
                options = new String[] {"Avocado", "Sour cream", "Tuna", "Peanuts"};
                break;
            case PALEO:
                options = new String[] {"Avocado", "Tuna", "Peanuts"};
                break;
            case VEGAN:
                options = new String[] {"Avocado", "Peanuts"};
                break;
            case NUT_ALLERGY:
                options = new String[] {"Avocado", "Sour cream", "Tuna"};
                break;
            default: throw new IllegalArgumentException("Unsupported diet: " + diet);
        }
        return choose(Category.FATS, options, random);
    }
}
