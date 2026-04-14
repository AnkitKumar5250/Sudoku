package lib;

/** A utility class to aid with matrix operations. */
public final class MatrixHelper {

  /** Inaccessible constructor. */
  private MatrixHelper() {}

  /**
   * Adds the values of one row to the values of another row.
   * 
   * @param matrix the matrix to operate on
   * @param addendRowIndex the index of the row to not store the sum
   * @param sumRowIndex the index of the row to store the sum
   */
  public static void addRow(int[][] matrix, int addendRowIndex, int sumRowIndex) {
    if (addendRowIndex < 0 || sumRowIndex < 0 || addendRowIndex >= matrix.length || sumRowIndex >= matrix.length) throw new UnsupportedOperationException("Cannot add out-of-bounds rows!");

    int[] addendRow = matrix[addendRowIndex];
    int[] sumRow = matrix[sumRowIndex];

    if (addendRow == null || sumRow == null) throw new UnsupportedOperationException("Cannot add null rows!");
    if (addendRow.length != sumRow.length) throw new UnsupportedOperationException("Cannot add rows of different lengths!");

    for (int element = 0; element < addendRow.length; element++)
      sumRow[element] += addendRow[element];
  }

  /**
   * Swaps the values of two rows within a matrix.
   * 
   * @param matrix the matrix to operate on
   * @param row1Index the index of the first row
   * @param row2Index the index of the second row
   */
  public static void swapRows(int[][] matrix, int row1Index, int row2Index) {
    if (row1Index < 0 || row2Index < 0 || row1Index >= matrix.length || row2Index >= matrix.length) throw new UnsupportedOperationException("Cannot swap out-of-bounds rows!");

    int[] row1 = matrix[row1Index];
    int[] row2 = matrix[row2Index];

    if (row1 == null || row2 == null) throw new UnsupportedOperationException("Cannot swap null rows!");
    if (row1.length != row2.length) throw new UnsupportedOperationException("Cannot swap rows of different lengths!");

    for (int element = 0; element < row1.length; element++) {
      int row1Value = row1[element];
      row1[element] = row2[element];
      row2[element] = row1Value;
    }
  }

  /**
   * Scales the values of a row within a matrix.
   * 
   * @param matrix the matrix to operate on
   * @param rowIndex the index of the row to scale
   * @param scalar the scalar to apply to the row
   */
  public static void scaleRow(int[][] matrix, int rowIndex, int scalar) {
    int[] row = matrix[rowIndex];
    if (row == null) throw new UnsupportedOperationException("Cannot scale null rows!");

    for (int element = 0; element < row.length; element++)
      row[element] *= scalar;
  }
}
