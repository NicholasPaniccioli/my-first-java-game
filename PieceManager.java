import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class PieceManager {


    public static Piece[] playerPieces = new Piece[0];
    public static List<Piece> pieceList = new ArrayList<Piece>(Arrays.asList(playerPieces));

    public static Piece[] cpuPieces = new Piece[0];
    public static List<Piece> cpuPieceList = new ArrayList<Piece>(Arrays.asList(cpuPieces));


    //Method will autofill/randomly place pieces for the CPU Board
    public void placeCPUPieces(String[][] board){
        //Pick a Random spot on the board [0-9][0-9]
        //Checks the spot isnt already in use 
        //Loops while it takes the length of each piece
        //And checks if it'll fit (chosen randomly) Either going Up, Down, Left, Or Right
        
        //If it fits places the piece and goes to the next piece
        //If it doesn't, goes to the next direction
        //If nothing works, will pick a different random spot, and Repeat

    }
}
