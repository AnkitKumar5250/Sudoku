package lib;

/** A utility class to verify the competence of the 'MatrixHelper' class */
public final class MatrixHelperTest {
  /** The matrix that will be operated on during the tests. */
  private static final int[][] testMatrix = {{1,2,3},{4,5,6},{7,8,9}};

  /** Runs tests on all three elementary row operations. */
  public static void run() {
    try {
      addTest();
      swapTest();
      scaleTest();
    } catch (Exception exception) {
      System.out.println("MatrixHelper tests failed!");
      exception.printStackTrace();
    }
    
  }

  /** 
   * Tests the MatrixHelper's 'addRow' method. Throws an exception if failed. 
   * @throws Exception Failed test.
   */
  private static void addTest() throws Exception {
    int[] expectedSum = {testMatrix[0][0] + testMatrix[1][0], testMatrix[0][1] + testMatrix[1][1], testMatrix[0][2] + testMatrix[1][2]};

    MatrixHelper.addRow(testMatrix, 0, 1);
    int[] sum = testMatrix[1].clone();

    for (int index = 0; index < 3; index++)
      if (expectedSum[index] != sum[index]) throw new Exception("Add-test failed!");
  }

  /** 
   * Tests the MatrixHelper's 'swapRows' method. Throws an exception if failed. 
   * @throws Exception Failed test.
   */
  private static void swapTest() throws Exception {
    int[] row1 = testMatrix[0].clone();
    int[] row2 = testMatrix[1].clone();

    MatrixHelper.swapRows(testMatrix, 0, 1);

    int[] swappedRow1 = testMatrix[0].clone();
    int[] swappedRow2 = testMatrix[1].clone();

    for (int index = 0; index < 3; index++)
      if (row1[index] != swappedRow2[index] || row2[index] != swappedRow1[index]) throw new Exception("Swap-test failed!");
  }

  /** 
   * Tests the MatrixHelper's 'scaleRow' method. Throws an exception if failed. 
   * @throws Exception Failed test.
   */
  private static void scaleTest() throws Exception {
    int scaler = (int)(Math.random() * 20);
    int[] expectedOutput = {testMatrix[0][0] * scaler, testMatrix[0][1] * scaler, testMatrix[0][2] * scaler};

    MatrixHelper.scaleRow(testMatrix, 0, scaler);
    int[] row = testMatrix[0].clone();

    for (int index = 0; index < 3; index++)
      if (row[index] != expectedOutput[index]) throw new Exception("Scale-test failed!");
  }
}
