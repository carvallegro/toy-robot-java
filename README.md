# Toy Robot Coding Exercise

[![Build Status](https://travis-ci.com/carvallegro/toy-robot-java.svg?branch=master)](https://travis-ci.com/carvallegro/toy-robot-java)
[![codecov](https://codecov.io/gh/carvallegro/toy-robot-java/branch/master/graph/badge.svg)](https://codecov.io/gh/carvallegro/toy-robot-java)
[![CodeFactor](https://www.codefactor.io/repository/github/carvallegro/toy-robot-java/badge)](https://www.codefactor.io/repository/github/carvallegro/toy-robot-java)

> See [Problem Statement](./PROBLEM.md)

## Run

- Build and install
```
./gradlew build
./gradlew clean install
``` 
- Run tests
```
./gradlew test
```
- Run the app 
```
./gradlew execute <path to your file>
```
## Environment

- Java 11
- Gradle 4.8
- JUnit 5

## Coding choices

> All code is Technical Debt

While reviewing my code, I've spotted a few things that I could change and/or improve but I'm happy with the code as it is and if I were to go down that rabbit hole I'd always find something to change and never deliver.

The following points are detailing what I could have changed or done differently and what was motivating me when I wrote it. 

### Grid class

This class contains the majority of the business logic for the exercise. Each command is represented by a method and the class is locked down in a way that allows feature enrichment but avoids developers tampering with the inner logic during the game. 

Each concept has its own class so that the code is easier to read and `Grid` can focus on handling business logic. 

### Robot class

The Robot class is very simplistic as it will not be accessible outside of the `Grid` class and won't even be mutated by anything else (see following points).

### Coordinates class

Easier to manipulate coordinates if X is always with Y. It has multiple advantages:

- Easier comparison.
- The use of `#clone` allows to share coordinates without risk of modification outside of `Grid` (even though there are no setters in Coordinates at the moment). It was a bit of a show off.

### Direction enum

Having an enum for the directions sets hard values that cannot be cheated upon. It also allows to encapsulate logic such as:
- What is the direction when we turn right or left (always going to be the case for the cardinal points).
- What would be the potential coordinates when moving in one direction?

I could have put the `#moveTowards` direction in the `Grid` class, that is also a valid choice. But I find it easier to read if we let a `Direction` give new coordinates, regardless or their validity, and then let `Grid` decide what should be done with it.

A conscious choice was to use the `Coordinates` class as a difference to calculate new coordinates. While the lambda concept does not really fit semantically with `Coordinates`, it was easier to keep this encapsulation concept. Another choice would have been to use `deltaX` and `deltaY` as members of `Direction`.  

### Text Interface

The `TextInterface` class is fairly simple as it's only a wrapper around the Grid. It allows to abstract the commands from the input and the actual grid's operation. The only logic in here is around input validation and not the exercise rule.

### Exceptions

I wondered if I should create specific Exceptions for this exercise (`RobotNotSetException` and such) and while this is a valid choice I chose not too as the exceptions uses in the code are valid and sufficient for this exercise.

If the codebase grows and gains in complexity, the question would need to be raised again.

All of the exceptions are shown in the console but in the error output, which is technically legal. The exercise does talk about this aspect of the code so I assumed that the results were shown on the standard ouptut. If an external test suite fails because of this, please comment the **line 97** in the [`TextInterface`](./src/main/java/com/carvallegro/toyrobot/cli/TextInterface.java) class  