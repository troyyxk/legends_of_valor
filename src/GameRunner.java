import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// this is the class for the errants before and after running the game
// it say hello to player, keep track of win records and say goodbye to player
public class GameRunner {
    public void run(){
        System.out.println("Welcome to Legends: Monsters and Heroes!");
        Scanner in = new Scanner(System.in);

        boolean continuePlay = true;

        ArrayList<Integer> winHistory = new ArrayList<Integer>();
        String inputLine;

        while (continuePlay) {
            continuePlay = false;

            LegendsMonstersHeroes LMHgame = new LegendsMonstersHeroes();
            int winner = LMHgame.play();
            assert winner >= -1;
            if (winner != -1) {
                winHistory.add(winner);
            }

            // check if the player wnat to play more games
            System.out.println("Want to play another round? (y/Y)");
            inputLine = in.nextLine();
            if (inputLine.equals("y") || inputLine.equals("Y")) {
                continuePlay = true;
            }
        }

        // if all games played result in draw, it will return the following message
        if (winHistory.size() == 0){
            System.out.println("No winning games, all ties.");
        }

        // check winner
        ArrayList<Integer> winTeams = new ArrayList<Integer>();
        for (Integer w : winHistory) {
            if (!winTeams.contains(w)){
                winTeams.add(w);
            }
        }

        // print statics for the games.
        int numOfWinGames;
        for (Integer t : winTeams) {
            numOfWinGames = Collections.frequency(winHistory, t);
            System.out.println("Team " + t + " has win " + numOfWinGames + " games.");
        }
        System.out.println("RPG game terminating, have a good day!");
    }
}