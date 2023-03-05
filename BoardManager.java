import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class BoardManager {
    public static String[] letters = {"A","B","C","D","E","F","G","H","I","J"};
    public static String border = "_|_________________________________";
    public static String numberAxis = "    1  2  3  4  5  6  7  8  9  10";

    public static Piece[] gamePieces = new Piece[0];
    public static List<Piece> pieceList = new ArrayList<Piece>(Arrays.asList(gamePieces));

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
    public static void hitBoard(String[][] board, String coords){
        int firstCoords = letterToNum(coords);
        int secondCoords = Integer.parseInt(coords.substring(1)) - 1; //Subtracts one to account for arrays starting at 0

        //Loops through each gamepiece and their respective location
        //To match coordinates and either hit a piece or an empty spot
        for(Piece i : gamePieces){
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

        System.out.println("\n You missed!");
        board[firstCoords][secondCoords] = "X";
    }

    //Takes the given coordinates and places the pieces based on orientation
    public static void placePieces(String[][] board, int sameCoord, int startCoord, int endCoord, int length, boolean vertical, String name){
    
        //Creates a new piece to store the points it covers
        Piece newPiece = new Piece(name,length);
        List<String> locationList = new ArrayList<String>(Arrays.asList(newPiece.locations));

        //Vertical otherwise horizontal
        if(vertical)
        {
            for(int i = startCoord; i <= endCoord; i++)
            {
                board[i][sameCoord] = "O";
                String converted = numToLetter(i); //Converts the first coord back to letter
                String full = converted.concat(Integer.toString(sameCoord+1)); //Adds 1 to account for starting at 0
                locationList.add(full);
                newPiece.locations = locationList.toArray(newPiece.locations);
            }
        }else{
            for(int i = startCoord; i <= endCoord; i++)
            {
                board[sameCoord][i] = "O";
                String converted = numToLetter(sameCoord); //Converts the first coord back to letter
                String full = converted.concat(Integer.toString(i+1)); //Adds 1 to account for starting at 0
                locationList.add(full);
                newPiece.locations = locationList.toArray(newPiece.locations);
            }
        }

        pieceList.add(newPiece);
    }
    //Helper method to check all conditions before a piece can be placed
    public static boolean placingCheck(String[][] board, String coords1, String coords2, int length, String name){

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
            placePieces(board, letter1, smallNumber, bigNumber, length, false, name);
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
                if(board[number1][i] != "-"){
                    System.out.println(" The coordinates given overlap another piece.\n Please give new coordinates.");
                    return false;
                }
            }

            //Once checks are passed, places the piece (Vertical)
            placePieces(board, number1, smallLetter, bigLetter, length, true, name);
            return true;

        }else{
            System.out.println(" The coordinate given do not place the piece in one row or column.\n Please give new coordinates.");
            return false;
        }
    }

    public static void placingQuestions(String[][] playerBoard, String[][] cpuBoard){
        String startPoint;
        String endPoint;

        int[] pieceLengths = {5,4,3,3,2};
        String[] pieceNames = {"Man-o-War","Barge","Dauntless","Pirate","Dingy"};

        //To get help get input from the console/player
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input = "";
                        
        for(int i = 0; i < 5; i++){
           boolean placed = false;

            while(!placed){
                System.out.println("Pick a Starting Point for the " + pieceNames[i] + " piece that's " + pieceLengths[i] + " spaces in length: ");
                try{
                    input = buffer.readLine().trim().toLowerCase();
                }catch(IOException e){
                    System.out.println("\nPlease put in a proper action\n");
                }
                                
                 //Checks that the input is a coordinate and then stores it
                if(coordinateCheck(input)){
                    startPoint = input;
                    System.out.println("Pick an Ending Point for the piece: ");
                    try{
                        input = buffer.readLine().trim().toLowerCase();
                    }catch(IOException e){
                        System.out.println("\nPlease put in a proper action\n");
                    }
    
                    //Checks that the second input is a coordinate and stores it
                    if(coordinateCheck(input)){
                        endPoint = input;
    
                        //With coordinates collected begins to check they are valid for placement
                        //If they pass, places the game piece in the given location
                        placed = placingCheck(playerBoard, startPoint, endPoint, pieceLengths[i], pieceNames[i]);
    
                    }else{
                        System.out.println("\nPlease put in an acceptable response\n");
                    }
    
                }else{
                    System.out.println("\nPlease put in an acceptable response\n");
                }
            }
            //Feedback after placing piece
            System.out.println("Piece was placed!\n");
            printBoards(playerBoard,cpuBoard);
        }
        gamePieces = pieceList.toArray(gamePieces);
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



