import java.io.*;
class Game {
    public static void main(String[] args) {

        boolean quitProgram = false;
        boolean quitGame = false;

        //To get help get input from the console/player
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input = "";
        
        String intro = "Welcome to the Game!\n";
        String question = "Would you like to PLAY or QUIT: ";
        String markQuestion = "Pick a Spot to Mark or QUIT:";
        
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

                //Loops so the player can continue to mark until they quit
                while(!quitGame){

                    System.out.print("\n" + markQuestion);
                    try{
                        input = buffer.readLine().trim();
                    }catch(IOException e){
                        System.out.println("\nPlease put in a proper action\n" + question);
                    }

                    //Checks if the player wants to quit or mark
                    if(input.equals("quit")){
                        quitGame = true;
                    }else if(input.length() <= 3){
                        BoardManager.markBoard(playerBoard, input);
                        BoardManager.printBoard(playerBoard);
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
