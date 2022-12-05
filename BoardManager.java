class BoardManager {
    public static String[][] board = new String[10][10]; //[Letters][Numbers]
    public static String[] letters = {"a","b","c","d","e","f","g","h","i"};

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
    public static void printBoard(String[][] board){

        for(int x = 0; x < board.length; x++){

            System.out.print("\n");

            for(int y = 0; y < board[0].length; y++ )
            {
                System.out.print(board[x][y]); 
            }
        }

        System.out.print("\n");
    }
}



