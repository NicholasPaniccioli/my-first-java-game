import java.io.*;
class Game {
    public static void main(String[] args) {

        boolean quitGame = false;

        //To get help get input from the console/player
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input = "";
        
        String intro = "Welcome to the Game!\n";
        String question = "Would you like to PLAY or QUIT: ";
        String markQuestion = "Pick a Spot to Mark:";
        System.out.println(intro + question);

        //Loops until the player quits the game
        while(!quitGame){

            //Gets the input
            try{
                input = buffer.readLine().trim().toLowerCase();
            }catch(IOException e){
                System.out.println("\nPlease put in a proper action\n" + question);
            }
            
            //Checks input for appropriate action
            if(input.equals("play")){
    
                System.out.println("\nNew board created:");
                String[][] playerBoard = BoardManager.createBoard();
                BoardManager.printBoard(playerBoard);

                //With board created asks where player wants to mark
                System.out.print("\n" + markQuestion);
                try{
                    input = buffer.readLine().trim();
                    BoardManager.markBoard(playerBoard, input);
                    BoardManager.printBoard(playerBoard);
                }catch(IOException e){
                    System.out.println("\nPlease put in a proper action\n" + question);
                }

                System.out.println("\nYou played the Game!\n" + question);

            }else if(input.equals("quit")){
                quitGame = true;
            }else{
                System.out.println("\nPlease give a valid action.\n" + markQuestion);
            }
            
        }
        
        System.out.println("\nYou quit the game! Thanks for playing!");
    }
}
