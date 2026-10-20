package meals;

/** The single concrete Abstract Factory; it reuses each category singleton. */
public final class MacronutrientFactory implements AbstractMacronutrientFactory {
    private static final MacronutrientFactory INSTANCE = new MacronutrientFactory();
    private MacronutrientFactory() { }
    public static MacronutrientFactory getInstance() { return INSTANCE; }

    @Override public FoodFactory createCarbFactory() { return CarbFactory.getInstance(); }
    @Override public FoodFactory createProteinFactory() { return ProteinFactory.getInstance(); }
    @Override public FoodFactory createFatFactory() { return FatFactory.getInstance(); }
}
