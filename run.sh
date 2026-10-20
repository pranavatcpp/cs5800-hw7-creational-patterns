#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build
javac --release 8 -Xlint:all -d build src/pizza/*.java src/meals/*.java
java -cp build pizza.PizzaDriver
printf '\nMacronutrient meals\n'
java -cp build meals.MealDriver
