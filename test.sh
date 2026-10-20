#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build
javac --release 8 -Xlint:all -d build src/pizza/*.java src/meals/*.java tests/AssignmentTests.java
java -cp build AssignmentTests
