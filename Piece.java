class Piece{
    String[] locations = {};
    String name;

    public Piece(){
        name = "UNDEFINED";
    }

    public Piece(String name){
        this.name = name;
    }

    //Used to detect if the piece was hit and if it was the last point
    public void takesHit(){

        System.out.println("You hit the " + name);
        //Do Coords match any in pieces location?
        //If so send message that a piece was hit
        //If so and was the last spot, special message "You destroyed the NAME"
        //If not nothing returns and miss message will be sent
    }

    //Returns the name of the piece and what points on the board it covers
    public String toString(){
        String message = "The location points for the " + name + " piece are: ";
        for(int i = 0; i < locations.length; i++){
            message += locations[i];
            if(i+1 != locations.length){
                message +=", ";
            }
        }
        return message;
    }
}