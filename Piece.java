class Piece{
    String[] locations = {};
    String name;
    int health;

    public Piece(){
        name = "UNDEFINED";
    }

    public Piece(String name, int health){
        this.name = name;
        this.health = health;
    }

    //Used to detect if the piece was hit and if it was the last point
    public void takesHit(){

        if(health > 1){
            System.out.println("\tYou hit the " + name + "!");
            health--;
        }else if (health == 1){
            System.out.println("\tYou sunk the " + name + "!");
            health = 0;
        }
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