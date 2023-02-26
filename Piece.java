class Piece{
    String[] locations = {};
    String name;

    public Piece(){
        name = "UNDEFINED";
    }

    public Piece(String name){
        this.name = name;
    }

    public String toString(){
        String message = "The location points for the " + name + " piece are ";
        for(int i = 0; i < locations.length; i++){
            message +=", " + locations[i];
        }
        return message;
    }
}