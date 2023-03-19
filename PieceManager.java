import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class PieceManager {
    public static Piece[] playerPieces = new Piece[0];
    public static List<Piece> pieceList = new ArrayList<Piece>(Arrays.asList(playerPieces));
    public static Piece[] cpuPieces = new Piece[0];
    public static List<Piece> cpuPieceList = new ArrayList<Piece>(Arrays.asList(cpuPieces));
    
    static boolean placed;

        //Takes the given coordinates and places the pieces based on orientation
        public static void placePieces(String[][] board, int sameCoord, int startCoord, int endCoord, int length, boolean vertical, String name, Boolean player){
    
            //Creates a new piece to store the points it covers
            Piece newPiece = new Piece(name,length);
            List<String> locationList = new ArrayList<String>(Arrays.asList(newPiece.locations));
    
            //Vertical otherwise horizontal
            if(vertical)
            {
                for(int i = startCoord; i <= endCoord; i++)
                {
                    System.out.print("Point picked. ");
                    board[i][sameCoord] = "O";
                    String converted = BoardManager.numToLetter(i); //Converts the first coord back to letter
                    String full = converted.concat(Integer.toString(sameCoord+1)); //Adds 1 to account for starting at 0
                    locationList.add(full);
                    newPiece.locations = locationList.toArray(newPiece.locations);
                }
            }else{
                for(int i = startCoord; i <= endCoord; i++)
                {
                    System.out.print("Point picked. ");
                    board[sameCoord][i] = "O";
                    String converted = BoardManager.numToLetter(sameCoord); //Converts the first coord back to letter
                    String full = converted.concat(Integer.toString(i+1)); //Adds 1 to account for starting at 0
                    locationList.add(full);
                    newPiece.locations = locationList.toArray(newPiece.locations);
                }
            }
    
            //Checks if it was a player or cpu created piece and adds to appropriate list
            if(player){
                PieceManager.pieceList.add(newPiece);
            }else if(!player){
                PieceManager.cpuPieceList.add(newPiece);
            }
        }

    //Method will autofill/randomly place pieces for the CPU Board
    static public void placeCPUPieces(String[][] board){
         
        Random rand = new Random();
        int randY;
        int randX;
        placed = false;

        //Loops while it takes the length of each piece
        //And checks if it'll fit (chosen randomly) Either going Up, Down, Left, Or Right
        for(int n = 0; n < 5; n++){

            //Pick a Random spot on the board [0-9][0-9] Letter/Num
            //Adds 1 to account for how board displays and respective logic works
            //Checks the spot isnt already in use 
            do{
                randY = rand.nextInt(10);
                randX = rand.nextInt(10) + 1;
                System.out.println(randY +""+ randX);
            }while(board[randY][randX-1].equals("O"));   

            placed = false;

            //Creates a list of numbers representing methods to be 
            //shuffled and used in random order
            List<Integer> methodRep = new ArrayList<Integer>();
            for(int m =1;m<=4;m++){
                methodRep.add(m);
            }

            //Using the random point,checks a random cardinal direction
            //Checking through each one until it is placed
            //If it does not place, picks a new point and repeats
            while(!placed){

                Collections.shuffle(methodRep);
                for(Integer mRep : methodRep){
                    if(!placed){
                        switch(mRep){
                            case 1:
                                checkUp(board,randX,randY,n);
                            break;
                            case 2:
                                checkDown(board,randX,randY,n);
                            break;
                            case 3:
                                checkRight(board,randX,randY,n);
                            break;
                            case 4:
                                checkLeft(board,randX,randY,n);
                            break;
                        }
                    }else if(placed){
                        break;
                    }
                }   

                if(!placed){
                    do{
                        randY = rand.nextInt(10);
                        randX = rand.nextInt(10) + 1;
                        System.out.println(randY +""+ randX +"again");
                    }while(board[randY][randX-1].equals("O"));
                }
            }

            System.out.println("Placed piece in CPU Board");
        }
    }

    //Formats two numbers into coordinates
    static public String formatCoords(int axisY, int axisX){
        String letterY = BoardManager.numToLetter(axisY);
        String formatted = letterY.concat(Integer.toString(axisX));
        return formatted;
    }

    static public boolean checkUp(String[][] board, int randX, int randY, int n){
        for(int u = 0; u < Game.pieceLengths[n]; u++){
            System.out.println("Im in up, need length of " + Game.pieceLengths[n]); 
            System.out.println("Checking at " + formatCoords(randY-u,randX));
            if(board[randY-u][randX-1].equals("O") || randY-Game.pieceLengths[n] < 0){
                System.out.println("Im leaving UP CHECK");
                return false;
            } 

            //Checks it made it through entire length of piece
            //If no dispute, places the piece
            if(u == Game.pieceLengths[n]-1)
            {
                String firstCoords = formatCoords(randY,randX);
                String secondCoords = formatCoords(randY-u,randX);
                
                if(BoardManager.placingCheck(board,firstCoords, secondCoords, Game.pieceLengths[n], Game.pieceNames[n], false)){
                    System.out.print("True in u");
                    placed = true;
                    return placed;
                }
            }
        }

        return false;
    }

    static public boolean checkDown(String[][] board, int randX, int randY, int n)
    {
        for(int d = 0; d < Game.pieceLengths[n]; d++){
            System.out.println("Im in down, need length of " + Game.pieceLengths[n]);
            System.out.println("Checking at " + formatCoords(randY+d,randX));

            if(board[randY+d][randX-1].equals("O") || randY+Game.pieceLengths[n] > 9){
                System.out.println("Im leaving DOWN CHECK");
                return false;
            }

            if(d == Game.pieceLengths[n]-1)
            {
                String firstCoords = formatCoords(randY,randX);
                String secondCoords = formatCoords(randY+d,randX);
                
                if(BoardManager.placingCheck(board,firstCoords, secondCoords, Game.pieceLengths[n], Game.pieceNames[n], false)){
                    System.out.print("True in d");
                    placed = true;
                    return placed;
                }
            }
        }

        return false;
    }

    static public boolean checkRight(String[][] board, int randX, int randY, int n){
        for(int r = 0; r < Game.pieceLengths[n]; r++){
            System.out.println("Im in right, need length of " + Game.pieceLengths[n]);
            System.out.println("Checking at " + formatCoords(randY,randX+r-1));

            if(board[randY][randX+r-1].equals("O") || randX+Game.pieceLengths[n]-1 > 9){
                System.out.println("Im leaving RIGHT CHECK");
                return false;
            }

            if(r == Game.pieceLengths[n]-1)
            {
                String firstCoords = formatCoords(randY,randX);
                String secondCoords = formatCoords(randY,randX+r);
                
                if(BoardManager.placingCheck(board,firstCoords, secondCoords, Game.pieceLengths[n], Game.pieceNames[n], false)){
                    System.out.print("True in r");
                    placed = true;
                    return placed;
                }
            }
        }
        
        return false;
    }

    static public boolean checkLeft(String[][] board, int randX, int randY, int n){
        for(int l = 0; l < Game.pieceLengths[n]; l++){
            System.out.println("Im in left, need length of " + Game.pieceLengths[n]);
            System.out.println("Checking at " + formatCoords(randY,randX-l-1));

            if(board[randY][randX-l-1].equals("O") || randX-Game.pieceLengths[n]-1 < 0){
                System.out.println("Im leaving LEFT CHECK");
                return false;
            }

            if(l == Game.pieceLengths[n]-1)
            {
                String firstCoords = formatCoords(randY,randX);
                String secondCoords = formatCoords(randY,randX-l);
                
                if(BoardManager.placingCheck(board,firstCoords, secondCoords, Game.pieceLengths[n], Game.pieceNames[n], false)){
                    System.out.print("True in l");
                    placed = true;
                    return placed;
                }
            }
        }   
        
        return false;
    }
}
