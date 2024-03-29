class BoardManager {
    public static String[] letters = {"A","B","C","D","E","F","G","H","I","J"};
    public static String border = "_|_________________________________";
    public static String numberAxis = "    1  2  3  4  5  6  7  8  9  10";

    //Creates a 10x10 grid to be used for the game
    public static String[][] createBoard(String[][] board){

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
            System.out.print("\n\t"+ letters[x] + "| ");
            for(int y = 0; y < board[0].length; y++ )
            {
                System.out.print(" " + board[x][y] + " "); 
            }
        }
        System.out.println("\n\t"+border+"\n\t"+numberAxis);
    }

    //Prints out two boards side to side for better viewing
    public static void printBoards(String[][] board, String[][] board2){

        System.out.println("\tPLAYER BOARD: \t\t\t\t\t CPU BOARD: " );
        for(int x = 0; x < board.length; x++){
            System.out.print("\n\t"+ letters[x] + "| ");
            for(int y = 0; y < board[0].length; y++ )
            {
                System.out.print(" " + board[x][y] + " "); 
            }
            System.out.print("\t");
            System.out.print("\t"+ letters[x] + "| ");
            for(int y = 0; y < board2[0].length; y++ )
            {
                System.out.print(" " + board2[x][y] + " "); 
            }
        }
        System.out.println("\n\t"+border+"\t\t"+border+"\n\t"+numberAxis+"\t\t"+numberAxis);
    }

    //Takes the targeted board and marks(hits) given coordinates
    public static void hitBoard(String[][] board, String coords, boolean player){
        int firstCoords = letterToNum(coords);
        int secondCoords = Integer.parseInt(coords.substring(1)) - 1; //Subtracts one to account for arrays starting at 0

        //Checks which player is hitting who
        //Loops through each gamepiece and their respective location
        //To match coordinates and either hit a piece or an empty spot
        if(player == true){
            for(Piece i : PieceManager.cpuPieces){
                for(String x : i.locations)
                {
                    if(coords.equals(x) && board[firstCoords][secondCoords] != "X"){
                        i.takesHit();
                        board[firstCoords][secondCoords] = "X";
                        return;
                    }else if(board[firstCoords][secondCoords].equals("X")){
                        System.out.println("\n You already shot here, pick another spot.");
                        return;
                    }
                }
            }
        }else if (player != true){
            for(Piece i : PieceManager.playerPieces){
                for(String x : i.locations)
                {
                    if(coords.equals(x) && board[firstCoords][secondCoords] != "X"){
                        i.takesHit();
                        board[firstCoords][secondCoords] = "X";
                        return;
                    }else if(board[firstCoords][secondCoords].equals("X")){
                        System.out.println("\n You already shot here, pick another spot.");
                        return;
                    }
                }
            }
        }

        System.out.println("\n You missed!");
        board[firstCoords][secondCoords] = "X";
    }

    //Helper method to check all conditions before a piece can be placed
    public static boolean placingCheck(String[][] board, String coords1, String coords2, int length, String name, boolean player){

        //System.out.println("C1:" + coords1 + "C2:" + coords2);
        
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

        //Used fgor placing debugging
        //System.out.println("L1:"+ letter1 + " L2:" + letter2 + " N1:" + number1 + " N2:" + number2);

        //First Checks that piece is not being placed diagonally
        if(letter1 == letter2){
            
            //Second Checks that coords given will match with length of piece
            //Adds 1 to be inclusive of both start/end points
            int distance = Math.abs((number1 - number2)) + 1;
            if(distance != length){
                System.out.println(" The coordinates given do no match the length of the piece.\n Please give new coordinates.");
                return false;
            }
            
            //Third Checks that coords do not overlap another piece
            for(int i = smallNumber; i <= bigNumber; i++){
                if(board[letter1][i] != "-"){
                    System.out.println(" The coordinates given overlap another piece.\n Please give new coordinates.");
                    return false;
                }
            }

            //Once checks are passed, places the piece (Horizontal)
            PieceManager.placePieces(board, letter1, smallNumber, bigNumber, length, false, name, player);
            return true;

        }else if (number1 == number2){

            //Second Checks that coords given will match with length of piece
            //Adds 1 to be inclusive of both start/end points
            int distance = Math.abs((letter1 - letter2)) + 1;
            if(distance != length){
                System.out.println(" The coordinates given do not match the length of the piece.\n Please give new coordinates.");
                return false;
            }

            //Third Checks that coords do not overlap another piece
            for(int i = smallLetter; i <= bigLetter; i++){
                if(board[i][number1] != "-"){
                    System.out.println(" The coordinates given overlap another piece.\n Please give new coordinates.");
                    return false;
                }
            }

            //Once checks are passed, places the piece (Vertical)
            PieceManager.placePieces(board, number1, smallLetter, bigLetter, length, true, name, player);
            return true;

        }else{
            System.out.println(" The coordinate given do not place the piece in one row or column.\n Please give new coordinates.");
            return false;
        }
    }

    //Method to check for valid coordinates
    public static boolean coordinateCheck(String coords){
        if(coords.length() <= 3 && coords.length() > 0 && letterToNum(coords) != -1){
            try{
                int value = Integer.parseInt(coords.substring(1));
                if(value <= 10 && value > 0){
                    return true;
                }
            }
            catch(NumberFormatException e){
                System.out.println(" Input given was not the correct format ");
                return false;
            }
        }
        return false;
    }

    //Checks if coordinate has already been hit
    public static boolean coordinateHit(String[][] board, String coords){
        int firstCoords = letterToNum(coords);
        int secondCoords = Integer.parseInt(coords.substring(1)) - 1;

        if(board[firstCoords][secondCoords] != "X"){
            return false;
        }else if(board[firstCoords][secondCoords].equals("X")){
            return true;
        }
        
        return false;
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

    //Takes a number and returns a coressponding letter
    public static String numToLetter(int num){
        switch(num){
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
            case 8:
                return "i";
            case 9:
                return "j";
            default:
                return "x";
        }
    }

    public static int biggerNumber(int num1, int num2){
        return (num1 > num2 && num1 != num2) ? num1 : num2;
    }
    public static int smallerNumber(int num1, int num2){
        return (num1 < num2 && num1 != num2) ? num1 : num2;
    }
}



