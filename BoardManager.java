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

    //Takes the targeted board and marks(hits) given coordinates
    public static void hitBoard(String[][] board, String coords){
        int firstCoords = letterToNum(coords);
        int secondCoords = Integer.parseInt(coords.substring(1)) - 1; //Subtracts one to account for arrays starting at 0

        //DEBUGGING USE
        System.out.println(firstCoords + " " + secondCoords);

        board[firstCoords][secondCoords] = "X";
    }

    //Takes the given coordinates and places the pieces based on orientation
    public static void placePieces(String[][] board, int sameCoord, int startCoord, int endCoord, int length, boolean vertical){
    
        //Vertical otherwise horizontal
        if(vertical)
        {
            for(int i = startCoord; i <= endCoord; i++)
            {
                board[i][sameCoord] = "0";
            }
        }else{
            for(int i = startCoord; i <= endCoord; i++)
            {
                board[sameCoord][i] = "0";
            }
        }
        
    }
    //Helper method to check all conditions before a piece can be placed
    public static boolean placingCheck(String[][] board, String coords1, String coords2, int length){

        //Coordinates for the 1st Set
        int letter1 = letterToNum(coords1);
        int number1 = Integer.parseInt(coords1.substring(1)) - 1;
        
        //Coordinates for the 2nd Set
        int letter2 = letterToNum(coords2);
        int number2 = Integer.parseInt(coords2.substring(1)) - 1;

        //Determines the bigger and smaller numbers for each group
        int bigLetter = biggerNumber(letter1, letter2);
        int smallLetter = smallerNumber(letter1, letter2);

        int bigNumber = biggerNumber(number1, number2);
        int smallNumber = smallerNumber(number1, number2);

        //First Checks that piece is not being placed diagonally
        if(letter1 == letter2){
            
            //Second Checks that coords given will match with length of piece
            int distance = Math.abs((number1 - number2)) + 1;
            if(distance != length){
                System.out.println("The coordinates given do no match the length of the piece.\n Please give new coordinates.");
                return false;
            }
            
            //Third Checks that coords do not overlap another piece
            for(int i = smallNumber; i <= bigNumber; i++){
                if(board[letter1][i] != "-"){
                    System.out.println("The coordinates given overlap another piece.\n Please give new coordinates.");
                    return false;
                }
            }

            //Once checks are passed, places the piece (Horizontal)
            placePieces(board, letter1, smallNumber, bigNumber, length, false);
            return true;

        }else if (number1 == number2){

            //Second Checks that coords given will match with length of piece
            int distance = Math.abs((letter1 - letter2)) + 1;
            if(distance != length){
                System.out.println("The coordinates given do not match the length of the piece.\n Please give new coordinates.");
                return false;
            }

            //Third Checks that coords do not overlap another piece
            for(int i = smallLetter; i <= bigLetter; i++){
                if(board[number1][i] != "-"){
                    System.out.println("The coordinates given overlap another piece.\n Please give new coordinates.");
                    return false;
                }
            }

            //Once checks are passed, places the piece (Vertical)
            placePieces(board, number1, smallLetter, bigLetter, length, true);
            return true;

        }else{
            System.out.println("The coordinate given do not place the piece in one row or column.\n Please give new coordinates.");
            return false;
        }
    }

    //Checks the letter given by the player and gives the corresponding number
    public static int letterToNum(String coords){
        switch(coords.charAt(0)){
            case 'A': case 'a':
                return 0;
            case 'B': case 'b':
                return 1;
            case 'C': case 'c':
                return 2;
            case 'D': case 'd':
                return 3;
            case 'E': case 'e':
                return 4;
            case 'F': case 'f':
                return 5;
            case 'G': case 'g':
                return 6;
            case 'H': case 'h':
                return 7;
            case 'I': case 'i':
                return 8;
            case 'J': case 'j':
                return 9;
            default:
                return -1;
        }
    }

    public static int biggerNumber(int num1, int num2){
        return (num1 > num2 && num1 != num2) ? num1 : num2;
    }
    public static int smallerNumber(int num1, int num2){
        return (num1 < num2 && num1 != num2) ? num1 : num2;
    }
}



