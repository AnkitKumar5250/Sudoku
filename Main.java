public class Main {
  public static void main(String[] args) {
    SudokuGenerator generator = new SudokuGenerator();
    
    int iterations = 1000;
    long totalTime = 0;
    for (int index = 0; index < iterations; index++) {
      long startTime = System.nanoTime();
      generator.generate();
      long endTIme = System.nanoTime();

      totalTime += (endTIme - startTime);
    }

    generator.printBoard();
    double averageTime = totalTime * 1.0 / iterations;
    double millis = averageTime / 1000000;

    System.out.print("AVERAGE TIME: " + millis);
  }
}