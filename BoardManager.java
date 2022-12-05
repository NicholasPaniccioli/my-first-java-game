class BoardManager {
    public static String[][] board = new String[10][10]; //[Letters][Numbers]
    public static String[] letters = {"A","B","C","D","E","F","G","H","I","J"};

    //Creates a 10x10 grid to be used for the game
    public static String[][] createBoard(){

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++ ){
                board[i][j] = "-";
            }
        }

        return board;
    }

    //Goes through the given board to print out each position
    //Also prints out the Axis for the board
    public static void printBoard(String[][] board){

        for(int x = 0; x < board.length; x++){
            System.out.print("\n"+ letters[x] + "| ");
            for(int y = 0; y < board[0].length; y++ )
            {
                System.out.print(" " + board[x][y] + " "); 
            }
        }
        System.out.println("\n_|_________________________________\n    1  2  3  4  5  6  7  8  9  10");
    }
}



