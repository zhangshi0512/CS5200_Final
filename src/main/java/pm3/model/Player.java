package pm3.model;

public class Player {
    protected int playerID;
    String name;
    String emailAddress;

    public Player(){}
    public Player(int playerID){
        this.playerID = playerID;
    }

    public Player(String name,String emailAddress){
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public Player(int playerID, String name,String emailAddress){
        this.playerID = playerID;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
