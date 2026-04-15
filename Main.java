public class Main {
  public static void main(String[] args) {
    SudokuGenerator generator = new SudokuGenerator();
    while (!SudokuGenerator.isValid(generator.board())) generator.generate();
    generator.printBoard();
  }
}