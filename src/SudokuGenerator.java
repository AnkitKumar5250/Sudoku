import java.util.Random;

/** A utility class to generate Sudoku boards. */
public final class SudokuGenerator {
  /** The size of the sudoku board (number of rows and columns). */
  private static final int SIZE = 9;

  /** The size of each individual box (number of rows and columns in each box) */
  private static final int BOX_SIZE = 3;

  /** The randomizer used to generate the puzzle. */
  private final Random random = new Random();

  /** The base solution to edit. */
  private final int[][] boardBuffer = {
      { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
      { 4, 5, 6, 7, 8, 9, 1, 2, 3 },
      { 7, 8, 9, 1, 2, 3, 4, 5, 6 },
      { 2, 3, 4, 5, 6, 7, 8, 9, 1 },
      { 5, 6, 7, 8, 9, 1, 2, 3, 4 },
      { 8, 9, 1, 2, 3, 4, 5, 6, 7 },
      { 3, 4, 5, 6, 7, 8, 9, 1, 2 },
      { 6, 7, 8, 9, 1, 2, 3, 4, 5 },
      { 9, 1, 2, 3, 4, 5, 6, 7, 8 },
  };

  /** A utility class to generate Sudoku boards. */
  public SudokuGenerator() {
    generate();
  }

  /** Generates a brand new board and stores it in the buffer. */
  public void generate() {
    shuffleDigits();
    shuffleRowsWithinBoxRows();
    shuffleColumnsWithinBoxColumns();
    shuffleBoxRows();
    shuffleBoxColumns();
    if (random.nextBoolean())
      transpose();
  }

  /**
   * Determines if a board is a valid sudoku board.
   * 
   * @param board the board to inspect
   * @return whether or not the board adheres to the rules of sudoku
   */
  public static boolean isValid(int[][] board) {
    // CHECK ROWS
    for (int row = 0; row < SIZE; row++)
      if (!isValidGroup(board[row]))
        return false;

    // CHECK COLUMNS
    for (int column = 0; column < SIZE; column++) {
      int[] group = new int[SIZE];
      for (int row = 0; row < SIZE; row++)
        group[row] = board[row][column];
      if (!isValidGroup(group))
        return false;
    }

    // CHECK BOXES
    for (int boxRow = 0; boxRow < BOX_SIZE; boxRow++) {
      for (int boxColumn = 0; boxColumn < BOX_SIZE; boxColumn++) {
        int[] group = new int[SIZE];
        int index = 0;
        for (int row = 0; row < BOX_SIZE; row++)
          for (int column = 0; column < BOX_SIZE; column++, index++)
            group[index] = board[boxRow * BOX_SIZE + row][boxColumn * BOX_SIZE + column];
        if (!isValidGroup(group))
          return false;
      }
    }

    return true;
  }

  /**
   * Determines whether or not the array is a valid sudoku group.
   * 
   * @param group an array of 9 unique single-digit integers
   * @return whether or not each element of the array is unique
   */
  private static boolean isValidGroup(int[] group) {
    boolean[] seen = new boolean[SIZE];
    for (int value : group) {
      if (value < 1 || value > SIZE || seen[value - 1])
        return false;
      seen[value - 1] = true;
    }
    return true;
  }

  /**
   * Swaps two rows in the board buffer.
   * 
   * @param row1 the index of the first row
   * @param row2 the index of the second row
   */
  private void swapRows(int row1, int row2) {
    int[] rowBuffer = boardBuffer[row1];
    boardBuffer[row1] = boardBuffer[row2];
    boardBuffer[row2] = rowBuffer;
  }

  /**
   * Swaps two columns in the board buffer.
   * 
   * @param column1 the index of the first column
   * @param column2 the index of the second column
   */
  private void swapColumns(int column1, int column2) {
    for (int row = 0; row < SIZE; row++) {
      int buffer = boardBuffer[row][column1];
      boardBuffer[row][column1] = boardBuffer[row][column2];
      boardBuffer[row][column2] = buffer;
    }
  }

  /**
   * Swaps two rows of boxes.
   * 
   * @param boxRow1 the index of the first row of boxes
   * @param boxRow2 the index of the second row of boxes
   */
  private void swapBoxRows(int boxRow1, int boxRow2) {
    for (int boxRow = 0; boxRow < BOX_SIZE; boxRow++)
      swapRows(boxRow1 * BOX_SIZE + boxRow, boxRow2 * BOX_SIZE + boxRow);
  }

  /**
   * Swaps two columns of boxes.
   * 
   * @param boxColumn1 the index of the first column of boxes
   * @param boxColumn2 the index of the second column of boxes
   */
  private void swapBoxColumns(int boxColumn1, int boxColumn2) {
    for (int boxColumn = 0; boxColumn < BOX_SIZE; boxColumn++)
      swapColumns(boxColumn1 * BOX_SIZE + boxColumn, boxColumn2 * BOX_SIZE + boxColumn);
  }

  /** Swaps the digits within the board randomly. */
  private void shuffleDigits() {
    // MAP DIGITS TO RANDOM DIGITS
    int[] shuffledDigits = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    for (int index = 0; index < SIZE; index++) {
      int randomIndex = random.nextInt(SIZE);

      // SWAP RANDOM DIGITS
      int digitBuffer = shuffledDigits[index];
      shuffledDigits[index] = shuffledDigits[randomIndex];
      shuffledDigits[randomIndex] = digitBuffer;
    }

    // APPLY NEW DIGIT MAP
    for (int row = 0; row < SIZE; row++)
      for (int column = 0; column < SIZE; column++)
        boardBuffer[row][column] = shuffledDigits[boardBuffer[row][column] - 1];
  }

  /** Shuffles the rows within each horizontally aligned box. */
  private void shuffleRowsWithinBoxRows() {
    for (int boxRow = 0; boxRow < BOX_SIZE; boxRow++) {
      int origin = boxRow * BOX_SIZE;

      // SHUFFLE ROWS IN SINGLE BOX ROW
      for (int rowInBox = 0; rowInBox < BOX_SIZE; rowInBox++) {
        int randomRowInBox = random.nextInt(rowInBox + 1);
        swapRows(origin + rowInBox, origin + randomRowInBox);
      }
    }
  }

  /** Shuffles the columns within each vertically aligned box. */
  private void shuffleColumnsWithinBoxColumns() {
    for (int boxColumn = 0; boxColumn < BOX_SIZE; boxColumn++) {
      int origin = boxColumn * BOX_SIZE;

      // SHUFFLE COLUMNS IN SINGLE BOX COLUMN
      for (int columnInBox = 0; columnInBox < BOX_SIZE; columnInBox++) {
        int randomColumnInBox = random.nextInt(columnInBox + 1);
        swapColumns(origin + columnInBox, origin + randomColumnInBox);
      }
    }
  }

  /** Shuffles box rows. */
  private void shuffleBoxRows() {
    for (int boxRow = 0; boxRow < BOX_SIZE; boxRow++) {
      int randomBoxRow = random.nextInt(boxRow + 1);
      swapBoxRows(randomBoxRow, boxRow);
    }
  }

  /** Shuffles box columns. */
  private void shuffleBoxColumns() {
    for (int boxColumn = 0; boxColumn < BOX_SIZE; boxColumn++) {
      int randomBoxColumn = random.nextInt(boxColumn + 1);
      swapBoxColumns(randomBoxColumn, boxColumn);
    }
  }

  /** Transposes the board (swaps rows and columns). */
  private void transpose() {
    int[][] transposed = new int[SIZE][SIZE];
    for (int row = 0; row < SIZE; row++)
      for (int column = 0; column < SIZE; column++)
        transposed[column][row] = boardBuffer[row][column];
    copyInto(transposed, boardBuffer);
  }

  /**
   * A new board is generated on each call of the 'generate' method. This board is
   * then stored in a buffer and accessed with this method.
   * 
   * @return a 2D array with game rows as array rows and game columns as array
   *         columns
   */
  public int[][] board() {
    int[][] output = new int[SIZE][SIZE];
    copyInto(boardBuffer, output);
    return output;
  }

  /**
   * Copies a board into another board's buffer.
   * 
   * @param board the board to put into the buffer
   */
  private void copyInto(int[][] board, int[][] buffer) {
    for (int row = 0; row < SIZE; row++)
      System.arraycopy(board[row], 0, buffer[row], 0, SIZE);
  }

  /**
   * A new board is generated on each call of the 'generate' method. This board is
   * then stored in a buffer and printed to the console with this method.
   */
  public void printBoard() {
    // UTILITY RUNNABLE TO HELP GENERATE SEPARATOR.
    Runnable makeSeparator = () -> System.out.print(" " + "-".repeat(SIZE * 2 + BOX_SIZE * 2 - 1) + "\n");
    makeSeparator.run();

    // BOARD ROWS
    for (int row = 0; row < SIZE; row++) {
      // BOXES IN ROW
      for (int tile = 0; tile < BOX_SIZE; tile++) {
        System.out.print("| ");

        // NUMBERS IN TILE
        for (int number = 0; number < BOX_SIZE; number++)
          System.out.print(boardBuffer[row][number + tile * BOX_SIZE] + " ");
      }

      // NEW LINE (ADD SEPARATOR FOR NEW COLUMN BOXES)
      System.out.print("|\n");
      if ((row + 1) % BOX_SIZE == 0)
        makeSeparator.run();
    }
  }
}
