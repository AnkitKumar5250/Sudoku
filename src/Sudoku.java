/** A utility class to generate Sudoku boards. */
public final class Sudoku {
  /** The size of the sudoku board (tiles on the board and elements per tile). */
  private static final int SIZE = 9;

  /** The size of each individual tile (rows/columns within a single tile) */
  private static final int WIDTH = 3;

  private static final int[][] boardBuffer = new int[SIZE][SIZE];

  /** Inaccessible constructor. */
  private Sudoku() {}

  public static void generate(int seed) {}

  /** Clears cache and board buffer. */
  public static void clear() {
    for (int row = 0; row < SIZE; row++)
      for (int column = 0; column < SIZE; column++)
        boardBuffer[row][column] = 0;
  }

  /**
   * A new board is generated on each call of the 'generate' method. This board is
   * then stored in a buffer and accessed with this method.
   * 
   * @return a 2D array with game rows as array rows and game columns as array columns
   */
  public static int[][] board() {
    int[][] output = new int[SIZE][SIZE];
    System.arraycopy(boardBuffer, 0, output, 0, SIZE);

    return output;
  }

  /**
   * A new board is generated on each call of the 'generate' method. This board is
   * then stored in a buffer and printed to the console with this method.
   */
  public static void printBoard() {
    // UTILITY RUNNABLE TO HELP GENERATE SEPARATOR.
    Runnable makeSeparator = () -> System.out.print(" " + "-".repeat(SIZE * 2 + WIDTH * 2 - 1) + "\n");
    makeSeparator.run();

    // BOARD ROWS
    for (int row = 0; row < SIZE; row++) {
      // TILES IN ROW
      for (int tile = 0; tile < WIDTH; tile++) {
        System.out.print("| ");

        // NUMBERS IN TILE
        for (int number = 0; number < WIDTH; number++)
          System.out.print(boardBuffer[row][number + tile * WIDTH] + " ");
      }

      // NEW LINE (ADD SEPARATOR FOR NEW COLUMN TILES)
      System.out.print("|\n");
      if ((row + 1) % WIDTH == 0) makeSeparator.run();
    }
  }
}
