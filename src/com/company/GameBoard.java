package com.company;

import java.util.PrimitiveIterator;
import java.util.Random;

public class GameBoard {

  private int [][] mines;
  private boolean [][] revealed;

  public GameBoard(int height, int length, int numberOfMines) {
    mines = new int[height][length];
    revealed = new boolean[height][length];
    addMines(numberOfMines);
  }

  private void addMines(int numberOfMines) {
    Random random = new Random();
    PrimitiveIterator.OfInt randomHeights = random.ints(0, mines.length).iterator();
    PrimitiveIterator.OfInt randomLengths = random.ints(0, mines[0].length).iterator();
    while (numberOfMines > 0) {
      int y = randomHeights.next();
      int x = randomLengths.next();

      if (mines[y][x] == 9) {
        continue;
      }

      mines[y][x] = 9;
      increment(y, x);

      numberOfMines--;
    }
  }

  private void increment(int y, int x) {
    int [] deltaY = {0, 1, 0, -1};
    int [] deltaX = {1, 0, -1, 0};
    for (int i = 0; i < 4; i++) {
      int tempY = y+deltaY[i];
      int tempX = x+deltaX[i];
      if (tempY >= 0 && tempY < mines.length && tempX >= 0 && tempX < mines[0].length) {
        if (mines[tempY][tempX] != 9) {
          mines[tempY][tempX]++;
        }
      }
    }
  }

  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("  ");
    for (int i = 0; i < mines[0].length; i++) {
      out.append(i);
      out.append(" ");
    }
    out.append("\n");

    for (int i = 0; i < mines.length; i++) {
      out.append(i);
      out.append(" ");
      for (int j = 0; j < mines[i].length; j++) {
        if (revealed[i][j]) {
          out.append(mines[i][j]);
        }
        else {
          out.append('X');
        }
        out.append(" ");
      }
      out.append('\n');
    }

    return out.toString();
  }

  public boolean reveal(int x, int y) {
    if (x < 0 || x >= revealed[0].length || y < 0 || y >= revealed.length || revealed[y][x]) {
      return false;
    }

    revealed[y][x] = true;
    if (mines[y][x] == 9) {
      return true;
    }

    if (mines[y][x] == 0) {
      reveal(x+1, y);
      reveal(x-1, y);
      reveal(x, y+1);
      reveal(x, y-1);
    }

    return false;
  }

  public boolean won() {
    for (int i = 0; i < revealed.length; i++) {
      for (int j = 0; j < revealed[i].length; j++) {
        if (!revealed[i][j] && mines[i][j] != 9) {
          return false;
        }
      }
    }
    return true;
  }
}
