import java.io.*;
class Game {
    public static void main(String[] args) {

        boolean quitProgram = false;
        boolean quitGame = false;
        boolean goBack = false;

        //To get help get input from the console/player
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input = "";
        
        String intro = "\n Welcome to the Game!\n";
        String question = " Would you like to PLAY the game or QUIT the program: ";
        String markQuestion = "\n Would you like to PLACE a piece, HIT a space, or QUIT the game: ";
        String hitQuestion = " Pick a Spot to Hit or go BACK: ";
        
        String[][] playerBoard = new String[10][10];
        String[][] cpuBoard = new String[10][10];

        //Beginning of the Program asks for user input
        System.out.print(intro + question);
        //Loops until the player quits the program
        while(!quitProgram){

            //Gets the input
            try{
                input = buffer.readLine().trim().toLowerCase();
            }catch(IOException e){
                System.out.println("\n Please put in a proper action\n" + question);
            }
            
            //Checks input for play or quit, and gives appropriate action
            if(input.equals("play")){
                
                System.out.println("\n New boards created:");
                playerBoard = BoardManager.createBoard(playerBoard);
                cpuBoard = BoardManager.createBoard(cpuBoard);
                BoardManager.printBoards(playerBoard,cpuBoard);

                //Loops so the player can continue to play until they quit
                while(!quitGame){

                    //Asks about marking spaces ie PLACE, HIT, BACK
                    System.out.print(markQuestion);
                    try{
                        input = buffer.readLine().trim().toLowerCase();
                    }catch(IOException e){
                        System.out.println("\n Please put in a proper action\n" + markQuestion);
                    }

                    if(input.equals("place")){
                        System.out.println("Resetting board & Game Pieces!");
                        playerBoard = BoardManager.createBoard(playerBoard);
                        cpuBoard = BoardManager.createBoard(cpuBoard);
                        PieceManager.placeCPUPieces(cpuBoard);
                        PieceManager.pieceList.clear();
                        System.out.println("Board & Pieces were reset!\n");

                        BoardManager.placingQuestions(playerBoard, cpuBoard);

                    }else if(input.equals("hit")){

                        //Loops until the player decides to stop hitting
                        while(!goBack){
                            System.out.print("\n" + hitQuestion);
                            try{
                                input = buffer.readLine().trim().toLowerCase();
                            }catch(IOException e){
                                System.out.println("\n Please put in a proper action\n" + hitQuestion);
                            }
        
                            //Checks if the player wants to go back or hit a space
                            //Also checks that input is an acceptable format
                            if(input.equals("back")){
                                goBack = true;
                            }else if(BoardManager.coordinateCheck(input)){
                                BoardManager.hitBoard(cpuBoard, input, true);
                                BoardManager.printBoards(playerBoard,cpuBoard);
                            }else{
                                System.out.println("\n Please put in an acceptable response\n");
                            }
                        }
                        goBack = false;

                    }else if(input.equals("quit")){
                        quitGame = true;
                    }else{
                        System.out.println("\n Please give a valid action.");
                    }
                }
                System.out.print("\n You played the Game! Thanks for playing!\n" + question);
                quitGame = false;

            }else if(input.equals("quit")){
                quitProgram = true;
            }else{
                System.out.println("\n Please give a valid action.\n" + question);
            }
        }
        System.out.println("\n You quit the program");
    }
}
