import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class PieceManager {

    int[] lengths = {5,4,3,3,2};
    String[] names = {"Man-o-War","Barge","Dauntless","Pirate","Dingy"};

    public static Piece[] playerPieces = new Piece[0];
    public static List<Piece> pieceList = new ArrayList<Piece>(Arrays.asList(playerPieces));

    public static Piece[] cpuPieces = new Piece[0];
    public static List<Piece> cpuPieceList = new ArrayList<Piece>(Arrays.asList(cpuPieces));


    //Method will autofill/randomly place pieces for the CPU Board
    public void placeCPUPieces(String[][] board){
         
        Random rand = new Random();
        int randX;
        int randY;
        boolean placed = false;

        //Loops while it takes the length of each piece
        //And checks if it'll fit (chosen randomly) Either going Up, Down, Left, Or Right
        for(int n = 0; n < 5; n++){

            //Pick a Random spot on the board [0-9][0-9]
            //Checks the spot isnt already in use 
            do{
                randX = rand.nextInt(10);
                randY = rand.nextInt(10);
            }while(board[randX][randY] != "O");   

            placed = false;

            //Check spaces around and that the Piece hasnt been placed yet
            //If it fits places the piece and goes to the next piece
            //If it doesn't, goes to the next direction
            while(placed){
                //Check Up
                for(int u = 0; u < lengths[n]; u++){
                    if(board[randX][randY-u].equals("O") || placed != true){
                        break; //If there is a dispute for space, leaves the loop
                    }
    
                    //If no dispute, places the piece
                    placed = true;
                }

                //Check Down
                for(int d = 0; d < lengths[n]; d++){
                    if(board[randX][randY+d].equals("O") || placed != true){
                        break; //If there is a dispute for space, leaves the loop
                    }

                    placed = true;
                }

                //Check Right
                for(int r = 0; r < lengths[n]; r++){
                    if(board[randX+r][randY].equals("O") || placed != true){
                        break; //If there is a dispute for space, leaves the loop
                    }

                    placed = true;
                }       

                //Check Left
                for(int l = 0; l < lengths[n]; l++){
                    if(board[randX-l][randY].equals("O") || placed != true){
                        break; //If there is a dispute for space, leaves the loop
                    }

                    placed = true;
                }        

                //If nothing works, will pick a different random spot, and Repeat
                if(!placed){
                    do{
                        randX = rand.nextInt(10);
                        randY = rand.nextInt(10);
                    }while(board[randX][randY] != "O");
                }

            }

        }

    }
}
