import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    private ArrayList<Player> players, faintedPlayers, readyToRevisePlayers;
    private int curPlayerIndex, numOfPlayers, teamIdx;

    public Team(int teamIdx) {
        // this.numOfPlayers = getNumOfPlayers();
        this.numOfPlayers = 3;
        this.teamIdx = teamIdx;

        this.players = new ArrayList<Player>();
        this.faintedPlayers = new ArrayList<Player>();
        this.readyToRevisePlayers = new ArrayList<Player>();

        addPlayer();

        this.curPlayerIndex = 0;
    }

    private void addPlayer() {
        for (int i = 0; i < this.numOfPlayers; i++) {
            System.out.println("Hero " + i + " selection!");
            this.players.add(new Player(i));
        }
    }

    private int getNumOfPlayers() {
        System.out.println("Enter number of players for team  (between 1 and 9):");
        Scanner in = new Scanner(System.in);
        String teamSize;
        for (;;) {
            teamSize = in.nextLine();
            if (teamSize.matches("[1-9]")){
                return Integer.parseInt(teamSize);
            }
            System.out.println("Invalid Input. Try again:");
        }
    }

    public void moveToNextPlayer(){
        this.curPlayerIndex = (this.curPlayerIndex + 1) % this.players.size();
    }

    public void faintHero(Player player) {
        if (! this.players.contains(player)) {
            return;
        }
        this.players.remove(player);
        this.faintedPlayers.add(player);
    }

    public void reviseHeroes() {
        for (Player player : this.readyToRevisePlayers) {
            this.players.add(player);
        }
        this.readyToRevisePlayers.clear();
        for (Player player : this.faintedPlayers) {
            this.readyToRevisePlayers.add(player);
        }
        this.faintedPlayers.clear();
    }

    // getter and setter

    public int getCurPlayerIndex(){
        return this.curPlayerIndex;
    }

    public Player getCurPlayer() {
        return this.players.get(this.curPlayerIndex);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return this.getPlayers().size();
    }

    public Player getPlayerAtIndex(int index) {
        return this.players.get(index);
    }

    public ArrayList<Player> getFaintedPlayers() {
        return faintedPlayers;
    }

    public ArrayList<Player> getReadyToRevisePlayers() {
        return readyToRevisePlayers;
    }

}
