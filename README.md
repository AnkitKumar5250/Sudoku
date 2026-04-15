# Sudoku Generator
*(Created by Ankit)*

![Presentation Video](video.mp4)

## Design Summary

This project generates new Sudoku puzzles by randomly shuffling an existing solution 6 different ways!

1. Swapping digits
2. Transposing the board
3. Shuffling rows of boxes
4. Shuffling columns of boxes
5. Shuffling rows within boxes
6. Shuffling columns within boxes

All six of these operations preserve the validity of the original board!

## Running the Code
Simply run ```Main.java``` in any old IDE! Or if you are feeling fancy ```javac Main.java``` and ```java Main```

There are only two files of importance here:
1. ```Main.java``` (runs the generator and displays the result)
2. ```SudokuGenerator.java``` (contains the generator code itself)
