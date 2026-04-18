public class Main {
  public static void main(String[] args) {
    SudokuGenerator generator = new SudokuGenerator();
    
    int iterations = 1000;
    long totalNonPrintTime = 0;
    long totalPrintTime = 0;

    for (int index = 0; index < iterations; index++) {
      long startTime = System.nanoTime();
      generator.generate();
      long nonPrintEndTime = System.nanoTime();
      generator.printBoard();
      long printEndTime = System.nanoTime();
      
      totalNonPrintTime += (nonPrintEndTime - startTime);
      totalPrintTime += (printEndTime - startTime);
    }
    
    double averageNonPrintTime = totalNonPrintTime * 1.0 / iterations;
    double averagePrintTime = totalPrintTime * 1.0 / iterations;

    double nonPrintTime = averageNonPrintTime / 1000000;
    double printTime = averagePrintTime / 1000000;

    System.out.print("\nAVERAGE TIME (NO PRINT): " + nonPrintTime);
    System.out.print("\nAVERAGE TIME (PRINT): " + printTime);
  }
}