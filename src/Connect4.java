/**
 * Created by Richa on 10/11/16.
 */
import java.util.*;
import java.util.Arrays;
public class Connect4 {

    public static char RED;
    public static char YELLOW;
    public static char NONE;
    private static char[][] board;

    public Connect4() {
        this.RED = 'X';
        this.YELLOW = 'O';
        this.NONE = ' ';
        this.board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char[][] getBoard() {
        return Arrays.copyOf(board, board.length);
    }

    public int putPiece(int column, char color) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == NONE) {
                board[i][column] = color;
                return i;
            }
        }
        return 0;
    }

    public char checkAlignment(int row, int column) {
        int count;
        char turn = board[row][column];
        //vertical win
        if (row + 3 <= 5) {
            if (board[row][column] == board[row + 1][column]) {
                if (board[row][column] == board[row + 2][column]) {
                    if (board[row + 3][column] == board[row + 3][column]) {
                        return board[row][column];
                    }
                }
            }
        }
        //horizontal win
        if (column + 1 <= 6 && board[row][column + 1] == board[row][column]) {
            if (column + 2 <= 6 && board[row][column + 2] == board[row][column]) {
                if (column + 3 <= 6 && board[row][column + 3] == board[row][column]) {
                    return board[row][column];
                } else if (column - 1 >= 0 && board[row][column - 1] == board[row][column]) {
                    return board[row][column];
                }
            } else if (column - 1 >= 0 && board[row][column - 1] == board[row][column]) {
                if (column - 2 >= 0 && board[row][column - 2] == board[row][column]) {
                    return board[row][column];
                }
            }
        } else if (column - 1 <= 6 && board[row][column - 1] == board[row][column]) {
            if (column - 2 <= 6 && board[row][column - 2] == board[row][column]) {
                if (column - 3 <= 6 && board[row][column - 3] == board[row][column]) {
                    return board[row][column];

                }
            }
        }
        //diagonal win
        for (int a = 0; a < 3 && a >= 0; a++) {
            for (int b = 3; b < 7 && b >= 0; b++) {
                if (board[row][column] == board[a][b]) {
                    if (b - 1 >= 0 && a + 1 < board.length) {
                        if (board[row][column] == board[a + 1][b - 1]) {
                            if (b - 2 >= 0 && a + 2 < board.length) {
                                if (board[row][column] == board[a + 2][b - 2]) {
                                    if (b - 3 >= 0 && a + 3 < board.length) {
                                        if (board[row][column] == board[a + 3][b - 3]) {
                                            return board[row][column];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int c = 0; c < 3 && c >= 0; c++) {
            for (int d = 0; d < 4 && d >= 0; d++) {
                if (board[row][column] == board[c][d]) {
                    if (c + 1 < board.length && d + 1 < board[0].length) {
                        if (board[row][column] == board[c + 1][d + 1]) {
                            if (c + 2 < board.length && d + 2 < board[0].length) {
                                if (board[row][column] == board[c + 2][d + 2]) {
                                    if (c + 3 < board.length && d + 3 < board[0].length) {
                                        if (board[row][column] == board[c + 3][d + 3]) {
                                            return board[row][column];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public void printScreen() {
        char letter = 'A';

//        System.out.println("    0   1   2   3   4   5   6 ");
        System.out.println("  ----------------------------");
        for (int i = 0; i < 6; i++) {
            {
                System.out.print(letter);
                for (int j = 0; j < 7; j++) {
                    System.out.print(" | " + board[i][j]);
                }
                System.out.println(" | ");
                System.out.println("  -----------------------------");
                letter++;
            }
        }
    }

    public void play() {
        printScreen();
        boolean turn = true;
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            if (turn) {
                //while(true) {
                System.out.println("RED turn");

                int column = myScanner.nextInt();
                if (column > 7 || column < 0) {
                    System.out.println("Out of bounds");
                } else {
                    int row = putPiece(column, RED);
                    printScreen();
                    char c = checkAlignment(row, column);
                    if (c == RED) {
                        System.out.println("RED WINS!");
                        return;
                    } else
                        turn = false;
                }
                turn = false;
            } else {
                System.out.println("YELLOW turn");
                int column = myScanner.nextInt();
                if (column > 7 || column < 0) {
                    System.out.println("Out of bounds");
                } else {
                    int row = putPiece(column, YELLOW);
                    printScreen();
                    char c = checkAlignment(row, column);
                    if (c == YELLOW) {
                        System.out.println("YELLOW WINS!");
                        return;
                    } else {
                        turn = true;
                    }
                }
            }
        }
    }

}

