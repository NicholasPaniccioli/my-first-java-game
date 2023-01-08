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
        
        String intro = "Welcome to the Game!\n";
        String question = "Would you like to PLAY the game or QUIT the program: ";
        String markQuestion = "Would you like to PLACE a piece, HIT a space, or QUIT the game: ";
        String hitQuestion = "Pick a Spot to Hit or go BACK: ";
        
        //Beginning of the Program asks for user input
        System.out.println(intro + question);
        //Loops until the player quits the program
        while(!quitProgram){

            //Gets the input
            try{
                input = buffer.readLine().trim().toLowerCase();
            }catch(IOException e){
                System.out.println("\nPlease put in a proper action\n" + question);
            }
            
            //Checks input for play or quit, and gives appropriate action
            if(input.equals("play")){
                
                System.out.println("\nNew board created:");
                String[][] playerBoard = BoardManager.createBoard();
                BoardManager.printBoard(playerBoard);

                //Loops so the player can continue to play until they quit
                while(!quitGame){

                    //Asks about marking spaces ie PLACE, HIT, BACK
                    System.out.println(markQuestion);
                    try{
                        input = buffer.readLine().trim().toLowerCase();
                    }catch(IOException e){
                        System.out.println("\nPlease put in a proper action\n" + markQuestion);
                    }

                    if(input.equals("place")){
                        String startPoint;
                        String endPoint;
                        
                        for(int i = 0; i < 5; i++){

                            System.out.println("Pick a Starting Point for a piece that's " + pieceLengths[i] + " spaces in length: ");
                            try{
                                input = buffer.readLine().trim().toLowerCase();
                            }catch(IOException e){
                                System.out.println("\nPlease put in a proper action\n");
                            }
                            
                            //Checks that the input is a coordinate and then stores it
                            if(input.length() <= 3 && input.length() > 0 && BoardManager.letterToNum(input) != -1){
                                startPoint = input;
                                System.out.println("Pick an Ending Point for the piece: ");
                                try{
                                    input = buffer.readLine().trim().toLowerCase();
                                }catch(IOException e){
                                    System.out.println("\nPlease put in a proper action\n");
                                }

                                //Checks that the second input is a coordinate and stores it
                                if(input.length() <= 3 && input.length() > 0 && BoardManager.letterToNum(input) != -1){
                                    endPoint = input;

                                    //With coordinates collected begins to check they are valid for placement
                                   boolean placeable = BoardManager.placingCheck(playerBoard, startPoint, endPoint, pieceLengths[i]);

                                   if(placeable){
                                        //TO BE DONE NEXT
                                        //Code to place the piece
                                   }else{
                                        //TO BE DONE NEXT
                                        //Otherwise restarts to allow player to put in new inputs
                                   }

                                }else{
                                    System.out.println("\nPlease put in an acceptable response\n");
                                }

                            }else{
                                System.out.println("\nPlease put in an acceptable response\n");
                            }
                        }
                    }else if(input.equals("hit")){

                        //Loops until the player decides to stop hitting
                        while(!goBack){
                            System.out.print("\n" + hitQuestion);
                            try{
                                input = buffer.readLine().trim().toLowerCase();
                            }catch(IOException e){
                                System.out.println("\nPlease put in a proper action\n" + hitQuestion);
                            }
        
                            //Checks if the player wants to go back or hit a space
                            //Also checks that input is an acceptable format
                            if(input.equals("back")){
                                goBack = true;
                            }else if(input.length() <= 3 && input.length() > 0 && BoardManager.letterToNum(input) != -1){
                                BoardManager.markBoard(playerBoard, input, false);
                                BoardManager.printBoard(playerBoard);
                            }else{
                                System.out.println("\nPlease put in an acceptable response\n");
                            }
                        }
                        goBack = false;

                    }else if(input.equals("quit")){
                        quitGame = true;
                    }else{
                        System.out.println("\nPlease give a valid action.");
                    }
                }
                System.out.println("\nYou played the Game! Thanks for playing!\n" + question);
                quitGame = false;

            }else if(input.equals("quit")){
                quitProgram = true;
            }else{
                System.out.println("\nPlease give a valid action.\n" + question);
            }
        }
        System.out.println("\nYou quit the program");
    }
}
