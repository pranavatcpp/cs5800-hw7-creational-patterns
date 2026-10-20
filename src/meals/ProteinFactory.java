package meals;

import java.util.Objects;
import java.util.Random;

/** Singleton factory for the protein category. */
public final class ProteinFactory extends FoodFactory {
    private static final ProteinFactory INSTANCE = new ProteinFactory();
    private ProteinFactory() { }
    public static ProteinFactory getInstance() { return INSTANCE; }

    @Override public Food createFood(DietPlan diet, Random random) {
        Objects.requireNonNull(diet, "Diet plan is required");
        String[] options;
        switch (diet) {
            case NO_RESTRICTION:
                options = new String[] {"Fish", "Chicken", "Beef", "Tofu"};
                break;
            case PALEO:
                options = new String[] {"Fish", "Chicken", "Beef"};
                break;
            case VEGAN:
                options = new String[] {"Tofu"};
                break;
            case NUT_ALLERGY:
                options = new String[] {"Fish", "Chicken", "Beef", "Tofu"};
                break;
            default: throw new IllegalArgumentException("Unsupported diet: " + diet);
        }
        return choose(Category.PROTEIN, options, random);
    }
}
