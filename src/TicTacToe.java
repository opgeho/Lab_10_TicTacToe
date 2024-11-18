import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "X";
        boolean won = false;
        boolean tie = false;
        boolean gDone = false;
        boolean done = false;
        boolean mDone = false;

        int moveCount = 0;
        int moveCol = 0;
        int moveRow = 0;

        clearBoard();
        SafeInput.prettyHeader("Tic Tac Toe");
        do{
            clearBoard();
            player = "X";
            moveCount = 0;
            done = false;
            do{

                gDone = false;
                System.out.println("\n Player: " + player + " move: " + moveCount);
                do{
                    mDone = false;
                    moveRow = SafeInput.getRangedInt(in,"enter row", 1, 3);
                    moveRow--;
                    moveCol = SafeInput.getRangedInt(in,"enter column", 1, 3);
                    moveCol--;
                    mDone = isValidMove(moveRow, moveCol);
                    if (!mDone){
                        System.out.println("that space is already taken. enter another move.");
                        display();
                    }
                }while (!mDone);
                board[moveRow][moveCol] = player;
                moveCount++;
                display();
                if (moveCount >= 3 && isWin(player) == true){
                    gDone = true;
                    System.out.println("\n Player " + player + " wins!");
                }
                else if (moveCount >= 6 && isTie() == true){
                    gDone = true;
                    System.out.println("\n It's a Tie!");
                    gDone = true;
                }
                if (player == "X"){
                    player = "O";
                }
                else {
                    player = "X";
                }

            }while (!gDone);
            if (SafeInput.getYNConfirm(in,"Would you like to play again") == false){
                done = true;
            }
            else{
                done = false;
            }
        }while (!done);



    }
    private static void clearBoard(){
        for (int row = 0; row < ROW; row++){
            for (int col = 0; col < COL; col++){
                board[row][col] = " ";
            }
        }
    }
    private static void display(){
        for (int row = 0; row < ROW; row++){
            for (int col = 0; col < COL; col++){
                System.out.print(" " + board[row][col]);
                if (col < 2){
                    System.out.print(" |");
                }
            }
            if (row < 2) {
                System.out.println("\n-----------");
            }
        }
    }
    private static boolean isValidMove(int row, int col){
        boolean retVal = false;
        if (board[row][col].equalsIgnoreCase(" ")){
            retVal = true;
        }
        else {
            retVal = false;
        }
        return retVal;
    }
    private static boolean isWin (String player){
        boolean retVal = false;
        retVal = isColWin(player);
        if (!retVal){
            retVal = isRowWin(player);
        }
        if (!retVal){
            retVal = isDiagonalWin(player);
        }
        return retVal;
    }
    private static boolean isColWin (String player){
        boolean retVal = false;
        int winCond = 0;
        for (int col = 0; col < COL; col++){
            winCond = 0;
            for (int row = 0; row < ROW; row++){
                if (board[row][col].equalsIgnoreCase(player)){
                    winCond++;
                }
            }
            if (winCond == 3){
                retVal = true;
                return retVal;
            }
        }
        return retVal;
    }

    private static boolean isRowWin (String player){
        boolean retVal = false;
        int winCond = 0;
        for (int row = 0; row < ROW; row++){
            winCond = 0;
            for (int col = 0; col < COL; col++){
                if (board[row][col].equalsIgnoreCase(player)){
                    winCond++;
                }
            }
            if (winCond == 3){
                retVal = true;
                return retVal;
            }
        }
        return retVal;
    }

    private static boolean isDiagonalWin(String player){
        boolean retVal = false;
        if (board[1][1].equalsIgnoreCase(player)){
            if (board[0][0].equalsIgnoreCase(player) && board[2][2].equalsIgnoreCase(player)){
                retVal = true;
            }
            else if (board[2][0].equalsIgnoreCase(player) && board[0][2].equalsIgnoreCase(player)){
                retVal = true;
            }
        }
        return retVal;
    }
    private static boolean isTie(){
        boolean retVal = false;
        int x = 0;
        int o = 0;
        int ties = 0; // counts how many paths are tied
        //Check column ties
        for (int col = 0; col < COL; col++){
            x = 0; // resets count for each column
            o = 0;
            for (int row = 0; row < ROW; row++){
                if (board[row][col].equalsIgnoreCase("X")){
                    x++;
                } else if (board[row][col].equalsIgnoreCase("O")) {
                    o++;
                }
            }
            if (x != 0 && o != 0){ // if there is one or more of each then there is a tie
                ties++;
            }
        }
        // check row ties
        for (int row = 0; row < ROW; row++){
            x = 0;
            o = 0;
            for (int col = 0; col < COL; col++){
                if (board[row][col].equalsIgnoreCase("X")){
                    x++;
                } else if (board[row][col].equalsIgnoreCase("O")) {
                    o++;
                }
            }
            if (x != 0 && o != 0){
                ties++;
            }
        }
        // check diagonal ties
        x = 0;
        o = 0;
        for (int row = 0; row < ROW; row++) { // top to bottom
            if (board[row][row].equalsIgnoreCase("X")){
                x++;
            } else if (board[row][row].equalsIgnoreCase("O")) {
                o++;
            }
        }
        if (x != 0 && o != 0){
            ties++;
        }
        x = 0;
        o = 0;
        for (int col = 0; col < COL; col ++){ // bottom to top
            if (board[2 - col][col].equalsIgnoreCase("X")){
                x++;
            } else if (board[2 - col][col].equalsIgnoreCase("O")) {
                o++;
            }
        }
        if (x != 0 && o != 0){
            ties++;
        }
        if (ties == 8){
            retVal = true;
        }
        return retVal;
    }
}