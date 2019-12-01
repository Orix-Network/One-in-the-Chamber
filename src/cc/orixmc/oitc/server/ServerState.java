package cc.orixmc.oitc.server;

public enum ServerState {

    WAITING("Waiting"),
    STARTING("Starting"),
    GAME("Game");

    private String toString;

    ServerState(String toString){
        this.toString = toString;
    }

    @Override
    public String toString(){
        return this.toString;
    }
}
