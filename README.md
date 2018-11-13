# Toy Robot Coding Exercise
> See [Problem Statement](./PROBLEM.md)

## Run

## Environment

- Java 11
- Gradle 4.8
- JUnit 5

## Coding choices

### Grid class

This class contains the majority of the business logic for the exercise. Each command is represented by a method and the class is locked down in a way that allows feature enrichment but avoids developers to tamper with the inner logic during the game. 

Each concept has its own class so that the code is easier to read and `Grid` can focus on handling business logic. 

### Robot class

The Robot class is very simplistic as it will not be accessible outside of the `Grid` class and won't even be mutated by anything else (see following points).

### Coordinates class

Easier to manipulate coordinates if X is always with Y. It has multiple advantages:

- Easier comparison.
- The use of `#clone` allows to share coordinates without risk of modification outside of `Grid`

### Direction enum

Having an enum for the directions sets hard values that cannot be cheated upon. It also allows to encapsulate logic such as:
- What is the direction when we turn right or left (always going to be the case for the cardinal points).
- What would be the potential coordinates when moving in one direction?

I could have put the `#moveTowards` direction in the `Grid` class, that is also a valid choice. But I find it easier to read if we let a `Direction` give new coordinates, regardless or their validity, and then let `Grid` decide what should be done with it.