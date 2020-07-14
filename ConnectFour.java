import java.util.Scanner;

public class ConnectFour {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int height; //Height of board
        int length; //Length of board
        int col;    //Column of placed chip
        int row;    //Row of placed chip

        int chips = 0;        //Number of placed chips
        boolean game = true; //Game is still going or not

        //Get board specifications
        System.out.print("What would you like the height of the board to be? ");
        height = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        length = scnr.nextInt();
        System.out.println("\n");

        final int MAX = height*length; //maximum number of spaces in the board

        //Initialize the board
        char[][] board = new char[height][length];
        initializeBoard(board);

        //Print board
        printBoard(board);
        System.out.println();

        //Tell players their tokens
        System.out.println("Player 1: x\nPlayer 2: o\n");

        //Play the game
        while(game) {
            //Player 1 turn
            System.out.print("Player 1: Which column would you like to choose? ");
            col = scnr.nextInt();
            System.out.println("\n");
            row = insertChip(board, col, 'x');
            checkIfWinner(board, col, row, 'x');
            printBoard(board);
            System.out.println();
            ++chips;

            //Check for end game
            if (checkIfWinner(board, col, row, 'x')) {
                System.out.println("Player 1 won the game!");
                break;
            }
            else if(chips == (MAX)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }

            //Player 2 turn
            System.out.print("Player 2: Which column would you like to choose? ");
            col = scnr.nextInt();
            System.out.println("\n");
            row = insertChip(board, col, 'o');
            printBoard(board);
            System.out.println();
            ++chips;

            //Check for end game
            if (checkIfWinner(board, col, row, 'o')) {
                System.out.println("Player 2 won the game!");
                break;
            }
            else if(chips == (MAX)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }

    }

    //Print connect four board
    public static void printBoard(char[][] array) {
        for (int i = array.length - 1; i >= 0; --i) {
            for (int j = 0; j < array[i].length; ++j) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    //This will set each spot in the array to “-”.
    public static void initializeBoard(char[][] array) {
        for (int i = array.length - 1; i >= 0; --i) {
            for (int j = 0; j < array[i].length; ++j) {
                array[i][j] = '-';
            }
        }
    }

    //Places the token in the column that the user has chosen. Returns row placed
    public static int insertChip(char[][] array, int col, char chipType) {
        int row = 0;

        while (array[row][col] != '-') {
            ++row;
        }

        array[row][col] = chipType;

        return row;
    }

    //Checks for winner
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int counter = 0;

        for (int i = 0; i < array[row].length; ++i) {
            if (array[row][i] == chipType) {
                ++counter;

                if (counter == 4) {
                    return true;
                }
            }

            else {
                counter = 0;
            }
        }

        counter = 0;

        for (int j = 0; j < array.length; ++j) {
            if (array[j][col] == chipType) {
                ++counter;

                if (counter == 4) {
                    return true;
                }
            }

            else {
                counter = 0;
            }
        }
        return false;
    }
}

