import java.io.*;
class Game {
    public static void main(String[] args) {

        boolean quitProgram = false;
        boolean quitGame = false;
        boolean goBack = false;

        int[] pieceLengths = {5,4,3,3,2};

        //To get help get input from the console/player
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input = "";
        
        String intro = "\n Welcome to the Game!\n";
        String question = " Would you like to PLAY the game or QUIT the program: ";
        String markQuestion = "\n Would you like to PLACE a piece, HIT a space, or QUIT the game: ";
        String hitQuestion = " Pick a Spot to Hit or go BACK: ";
        
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
                
                System.out.println("\n New board created:");
                String[][] playerBoard = BoardManager.createBoard();
                BoardManager.printBoard(playerBoard);

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
                        //If they are placing needs to refresh board to make new space.
                        System.out.println("\n Reseting board!");
                        playerBoard = BoardManager.createBoard();
                        System.out.println(" Board was reset!");
                        String startPoint;
                        String endPoint;
                        
                        for(int i = 0; i < 5; i++){
                            boolean placed = false;

                            while(!placed){
                                System.out.print("\n Pick a Starting Point for a piece that's " + pieceLengths[i] + " spaces in length: ");
                                try{
                                    input = buffer.readLine().trim().toLowerCase();
                                }catch(IOException e){
                                    System.out.println("\n Please put in a proper action\n");
                                }
                                
                                //Checks that the input is a coordinate and then stores it
                                if(BoardManager.coordinateCheck(input)){
                                    startPoint = input;
                                    System.out.print("\n Pick an Ending Point for the piece: ");
                                    try{
                                        input = buffer.readLine().trim().toLowerCase();
                                    }catch(IOException e){
                                        System.out.println("\n Please put in a proper action\n");
                                    }
    
                                    //Checks that the second input is a coordinate and stores it
                                    if(BoardManager.coordinateCheck(input)){
                                        endPoint = input;
    
                                        //With coordinates collected begins to check they are valid for placement
                                        //If they pass, places the game piece in the given location
                                       placed = BoardManager.placingCheck(playerBoard, startPoint, endPoint, pieceLengths[i]);
    
                                    }else{
                                        System.out.println("\n Please put in an acceptable response\n");
                                    }
    
                                }else{
                                    System.out.println("\n Please put in an acceptable response\n");
                                }
                            }
                            //Feedback after placing piece
                            System.out.println("\n The piece was placed!\n");
                            BoardManager.printBoard(playerBoard);
                        }
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
                                BoardManager.hitBoard(playerBoard, input);
                                BoardManager.printBoard(playerBoard);
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
