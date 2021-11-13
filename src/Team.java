import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    private ArrayList<Player> players;
    private int curPlayerIndex, numOfPlayers, teamIdx;

    public Team(int teamIdx) {
        // this.numOfPlayers = getNumOfPlayers();
        this.numOfPlayers = 1;
        this.teamIdx = teamIdx;

        this.players = new ArrayList<Player>();
        addPlayer();

        this.curPlayerIndex = 0;
    }

    private void addPlayer() {
        for (int i = 0; i < this.numOfPlayers; i++) {
            System.out.println("Team: " + teamIdx + " Player: " + i);
            this.players.add(new Player());
        }
    }

    private int getNumOfPlayers() {
        System.out.println("Enter number of players for team  (between 1 and 9):");
        Scanner in = new Scanner(System.in);
        String teamSize;
        for (;;) {
            teamSize = in.nextLine();
            if (teamSize.matches("[1-9]")) {
                return Integer.parseInt(teamSize);
            }
            System.out.println("Invalid Input. Try again:");
        }
    }

    public void moveToNextPlayer() {
        this.curPlayerIndex = (this.curPlayerIndex + 1) % this.numOfPlayers;
    }

    // getter and setter

    public int getCurPlayerIndex() {
        return this.curPlayerIndex;
    }

    public Player getCurPlayer() {
        return this.players.get(this.curPlayerIndex);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
