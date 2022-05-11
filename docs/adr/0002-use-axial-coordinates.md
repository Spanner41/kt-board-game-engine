# 1. Use Axial Coordinates

Date: 2022-05-07

## Status

Accepted

## Context

To represent the Catan game board, I need a quick and easy system for managing the location and relationships of tiles,
edges, and corners.  I have started building some game mechanics, but I'm afraid that if I don't get this settled first, there will be a lot of rework.
I read (skimmed) a [comprehensive article on hexagonal grids](https://www.redblobgames.com/grids/hexagons/), and it gave a lot of great
options that I won't bother elaborating here.

## Decision

I am going to learn and use an Axial coordinate system that seems the obvious choice. It is quick, efficient, and versatile.
The algorithms are simpler than other alternatives, but there will be some mental lift in becoming intuitive with the math.
In summary: There are 3 axes labeled q, r, s with the property that q + r + s = 0.  Because of that, the model can be stored in
a 2D array; discarding the 3rd dimension because it can always be calculated by s = -q - r.


## Consequences

There is a risk that this will be difficult for other people to understand, but I think it is the simplest of the options.
I will try to write comprehensive documentation as I go, so other developers have an easier time knowing what's going on.
I also want to be careful not to let this bring the rest of the code crashing down.  I will decouple this code and integrate
with it when it's been more thoroughly implemented.