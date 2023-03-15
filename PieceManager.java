import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class PieceManager {

    static int[] lengths = {5,4,3,3,2};
    static String[] names = {"Man-o-War","Barge","Dauntless","Pirate","Dingy"};

    public static Piece[] playerPieces = new Piece[0];
    public static List<Piece> pieceList = new ArrayList<Piece>(Arrays.asList(playerPieces));

    public static Piece[] cpuPieces = new Piece[0];
    public static List<Piece> cpuPieceList = new ArrayList<Piece>(Arrays.asList(cpuPieces));


    //Method will autofill/randomly place pieces for the CPU Board
    static public void placeCPUPieces(String[][] board){
         
        Random rand = new Random();
        int randY;
        int randX;
        boolean placed = false;

        //Loops while it takes the length of each piece
        //And checks if it'll fit (chosen randomly) Either going Up, Down, Left, Or Right
        for(int n = 0; n < 5; n++){

            //Pick a Random spot on the board [0-9][0-9] Letter/Num
            //Checks the spot isnt already in use 
            do{
                randY = rand.nextInt(10);
                randX = rand.nextInt(10);
                System.out.println(randY +""+ randX);
            }while(board[randY][randX].equals("O"));   

            placed = false;


            //Check spaces around and that the Piece hasnt been placed yet
            //If it fits places the piece and goes to the next piece
            //If it doesn't, goes to the next direction
            while(!placed){
                //Check Up
                for(int u = 0; u < lengths[n]; u++){
                    System.out.print("Im in up need length of " + lengths[n]); 
                    System.out.println("Checking u:" + u + "At " + Integer.toString(randY-u) + ""+ randX);
                    if(board[randY-u][randX].equals("O") || placed == true || randY-lengths[n] < 0){
                        System.out.println("Im leaving UP CHECK");
                        break; //If there is a dispute for space, leaves the loop
                    }
                    

                    //Checks it made it through entire length of piece
                    //If no dispute, places the piece
                    if(u == lengths[n])
                    {
                        System.out.print("True in U");
                        placed = true;
                        String firstCoords = formatCoords(randY,randX);
                        String secondCoords = formatCoords(randY-lengths[n],randX);
                        BoardManager.placingCheck(board,firstCoords, secondCoords, lengths[n], names[n]);
                    }
                }

                //Check Down
                for(int d = 0; d < lengths[n]; d++){
                    if(board[randY+d][randX].equals("O") || placed == true || randY+lengths[n] > 9){
                        break; //If there is a dispute for space, leaves the loop
                    }

                    if(d == lengths[n])
                    {
                        System.out.print("True in d");
                        placed = true;
                        String firstCoords = formatCoords(randY,randX);
                        String secondCoords = formatCoords(randY+lengths[n],randX);
                        BoardManager.placingCheck(board,firstCoords, secondCoords, lengths[n], names[n]);
                    }
                }

                //Check Right
                for(int r = 0; r < lengths[n]; r++){
                    if(board[randY][randX+r].equals("O") || placed == true || randX+lengths[n] > 9){
                        break; //If there is a dispute for space, leaves the loop
                    }

                    if(r == lengths[n])
                    {
                        System.out.print("True in r");
                        placed = true;
                        String firstCoords = formatCoords(randY,randX);
                        String secondCoords = formatCoords(randY,randX+lengths[n]);
                        BoardManager.placingCheck(board,firstCoords, secondCoords, lengths[n], names[n]);
                    }
                }       

                //Check Left
                for(int l = 0; l < lengths[n]; l++){
                    if(board[randY][randX-l].equals("O") || placed == true || randX-lengths[n] < 0){
                        break; //If there is a dispute for space, leaves the loop
                    }

                    if(l == lengths[n])
                    {
                        System.out.print("True in l");

                        placed = true;
                        String firstCoords = formatCoords(randY,randX);
                        String secondCoords = formatCoords(randY,randX-lengths[n]);
                        BoardManager.placingCheck(board,firstCoords, secondCoords, lengths[n], names[n]);
                    }
                }        

                //If nothing works, will pick a different random spot, and Repeat
                if(!placed){
                    do{
                        randY = rand.nextInt(10);
                        randX = rand.nextInt(10);
                        System.out.println(randY +""+ randX +"again");
                    }while(board[randY][randX].equals("O"));
                }

            }

            System.out.println("Placed piece in CPU Board");

        }

    }

    //Formats two numbers into coords
    static public String formatCoords(int axisY, int axisX){
        String letterY = BoardManager.numToLetter(axisY);
        String formatted = letterY.concat(Integer.toString(axisX));
        return formatted;
    }
}
