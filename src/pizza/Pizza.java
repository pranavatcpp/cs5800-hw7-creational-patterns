package pizza;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/** Immutable product: only the builder can construct a pizza. */
public final class Pizza {
    private final Chain chain;
    private final Size size;
    private final Set<Topping> toppings;

    private Pizza(Builder builder) {
        chain = builder.chain;
        size = builder.size;
        toppings = Collections.unmodifiableSet(EnumSet.copyOf(builder.toppings));
    }

    public Chain getChain() { return chain; }
    public Size getSize() { return size; }
    public Set<Topping> getToppings() { return toppings; }

    public void eat() {
        String selected = toppings.stream().map(Topping::toString)
                .collect(Collectors.joining(", "));
        System.out.println(chain + " | " + size + " | " + toppings.size()
                + (toppings.size() == 1 ? " topping: " : " toppings: ")
                + (selected.isEmpty() ? "None" : selected));
    }

    public static final class Builder {
        private final Chain chain;
        private final Size size;
        private final EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        // Required fields cannot be omitted or changed after starting the builder.
        public Builder(Chain chain, Size size) {
            this.chain = Objects.requireNonNull(chain, "Pizza chain is required");
            this.size = Objects.requireNonNull(size, "Pizza size is required");
        }
        public Builder addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping, "Topping is required"));
            return this;
        }
        public Builder pepperoni() { return addTopping(Topping.PEPPERONI); }
        public Builder sausage() { return addTopping(Topping.SAUSAGE); }
        public Builder mushrooms() { return addTopping(Topping.MUSHROOMS); }
        public Builder bacon() { return addTopping(Topping.BACON); }
        public Builder onions() { return addTopping(Topping.ONIONS); }
        public Builder extraCheese() { return addTopping(Topping.EXTRA_CHEESE); }
        public Builder peppers() { return addTopping(Topping.PEPPERS); }
        public Builder chicken() { return addTopping(Topping.CHICKEN); }
        public Builder olives() { return addTopping(Topping.OLIVES); }
        public Builder spinach() { return addTopping(Topping.SPINACH); }
        public Builder tomatoAndBasil() { return addTopping(Topping.TOMATO_AND_BASIL); }
        public Builder beef() { return addTopping(Topping.BEEF); }
        public Builder ham() { return addTopping(Topping.HAM); }
        public Builder pesto() { return addTopping(Topping.PESTO); }
        public Builder spicyPork() { return addTopping(Topping.SPICY_PORK); }
        public Builder hamAndPineapple() { return addTopping(Topping.HAM_AND_PINEAPPLE); }
        public Pizza build() { return new Pizza(this); }
    }
}
