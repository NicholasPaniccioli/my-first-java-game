class Piece{
    String[] locations = {};
    String name;

    public Piece(){
        name = "UNDEFINED";
    }

    public Piece(String name){
        this.name = name;
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