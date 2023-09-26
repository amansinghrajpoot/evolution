# Evolution (The zero-player game)

## How to run 

```bash
mvn exec:java -Dexec.mainClass="com.me.projects.EvolutionAppKt"
```

## Overview

This is a zero-player game based on a genetic algorithm inspired by a YouTuber. In this simulation, you'll encounter simple single-cell organisms represented as green dots (".") on the screen. These cells have a unique property called "genes," which influences their movement behavior. While their movement is inherently random, the genes predispose them to prefer certain directions over others.

## Cell Properties

Each cell is defined by a set of genes, represented as an array of probabilities. For example:
```kotlin
[0.1, 0.0, 0.3, 0.0, 0.1, 0.2, 0.0, 0.0, 0.1]
```
These genes determine the likelihood of the cell moving in different directions.

## Gameplay

- Cells are initially spawned with random genes at random coordinates on the screen.
- In each generation, cells are allowed to move a certain number of steps.
- At the end of each generation, cells located on the left side of the window are eliminated.
- Surviving cells have the opportunity to reproduce, resulting in an exchange of genes. Occasionally, mutations may occur during reproduction.
- The next generation is spawned with improved genes based on the success of the previous generation.
- Only the fittest cells survive to continue the cycle.

## Configuration

The simulation can be customized through various configuration parameters found in the `ApplicationConstants`. You can adjust parameters such as `MAX_POPULATION`, `REFRESH_RATE`, `MAX_GENERATIONS`, and others to experiment and have fun with the simulation.

Please note that while the algorithms used in this simulation may not be optimized or efficient, it provides an entertaining way to observe how genetic algorithms can influence the evolution of organisms in a zero-player game. Enjoy exploring the world of evolving cells!
