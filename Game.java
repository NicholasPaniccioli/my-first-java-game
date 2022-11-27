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
                
                System.out.println("\nYou played the Game!\n" + question);
                
            }else if(input.equals("quit")){

                quitGame = true;
                
            }else{
                System.out.println("\nPlease give a valid action.\n" + question);
            }
            
        }

        System.out.println("\nYou quit the game! Thanks for playing!");
    }
}
