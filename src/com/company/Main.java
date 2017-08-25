package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Minesweeper.\nPlease enter a height, length, and number of mines: ");

        int height = scanner.nextInt();
        int length = scanner.nextInt();
        int numberOfMines = scanner.nextInt();

        GameBoard gameBoard = new GameBoard(height, length, numberOfMines);
        while (true) {
            printBoard(gameBoard);

            if (gameBoard.won()) {
                System.out.println("You Win!");
                break;
            }

            System.out.print("Enter and X and Y to reveal: ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (gameBoard.reveal(x, y)) {
                System.out.println("You Died!");
                break;
            }
        }
    }

    private static void printBoard(GameBoard gameBoard) {
        System.out.println(gameBoard.toString());
    }
}
