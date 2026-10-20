package meals;

/** Abstract Factory: supplies the family of category factories. */
public interface AbstractMacronutrientFactory {
    FoodFactory createCarbFactory();
    FoodFactory createProteinFactory();
    FoodFactory createFatFactory();
}
