import java.util.ArrayList;
import java.util.HashMap;

/**
 * class RPGGame
 * 
 *  Variables:
 * 
 *      Board board:
 * 
 *      int boardHeight:
 * 
 *      int boardWidth:
 * 
 *      int teamCount:
 * 
 *      int curTeamIdx:
 * 
 *      int curPlayerIndex:
 * 
 *      ArrayList<Team> teams:
 * 
 *      Player curPlayer:
 * 
 *      MarketController marketController: Hero actions in market 
 * 
 *      MonsterGallary monsterGallary: a database for three kinds of monster, loaded all monsters from local file 
 * 
 *      ArrayList<MonsterModel> allMonsterModel: a collection of all  individual monsters , extracted from "monsterGallary"
 * 
 *      HashMap<Integer, ArrayList<MonsterModel>> levelMonsterMap: a map of the format < level , [Monster 1, Monster 2, Monster 3 ...]>
 * 
 *      ArrayList<MonsterObject> monsterObjects: the monsters actually be put on the map
 * 
 *  Constructors:
 * 
 *      RPGGame():
 * 
 *  Methods:
 * 
 *      void initializePlayerPositions() :
 * 
 *      void getBoardHeightWidth():
 * 
 *      void setBoard():
 * 
 *      void drawBoard() :
 * 
 *      void addTeam() :
 * 
 *      void addMonsterObject(MonsterObject monsterObject):
 * 
 *      // getter and setter
 * 
 *      int getBoardHeight()
 * 
 *      ......
 */
public class RPGGame extends Game {

    private int teamCount, curTeamIdx, curPlayerIndex;
    private ArrayList<Team> teams;
    private Player curPlayer;
    private MarketController marketController;

    private MonsterGallary monsterGallary;
    private ArrayList<MonsterModel> allMonsterModels;
    private HashMap<Integer, ArrayList<MonsterModel>> levelMonsterMap;
    private ArrayList<MonsterObject> monsterObjects;

    public RPGGame() {
        // this.getBoardHeightWidth();
        // this.setBoard();
        this.teams = new ArrayList<Team>();
        this.teamCount = 1;
        this.addTeam();
        // initializePlayerPositions();
        this.curTeamIdx = 0;

        this.curPlayerIndex = 0;

        this.curPlayer = this.teams.get(0).getPlayers().get(curPlayerIndex);
        this.marketController = new MarketController();

        this.monsterGallary = new MonsterGallary();
        this.allMonsterModels = this.monsterGallary.getAllMonsterModels();
        this.levelMonsterMap = initializeLevelMonsterMap();

        this.monsterObjects = new ArrayList<MonsterObject>();
    }

    private void initializePlayerPositions() {
        int[] curPos;
        for (Team curTeam : this.teams) {
            for (Player curPlayer : curTeam.getPlayers()) {
                curPos = this.getBoard().getRandomInitialPosition();
                curPlayer.setPos(curPos);
                this.getBoard().addHero(curPos, curPlayer);
            }
        }
    }

    // get height and width of the board, limit set to 100
    private void getBoardHeightWidth() {
        System.out.println("Enter height of the board, limit: 8 to 100:");
        this.setBoardHeight(Utils.takeIntInput(8, 100));
        System.out.println("Enter width of the board, limit: 8 to 100:");
        this.setBoardWidth(Utils.takeIntInput(8, 100));

    }

    // board is scalable to set to different width and length
    public void setBoard(){
        this.setBoard(new Board(this.getBoardWidth(), this.getBoardHeight()));
    }

    public void drawBoard() {
        this.getBoard().drawBoard();
    }

    private void addTeam() {
        for (int i = 0; i < this.teamCount; i++) {
            this.teams.add(new Team(i));
        }
    }

    public void addMonsterObject(MonsterObject monsterObject) {
        this.monsterObjects.add(monsterObject);
    }

    // getter and setter
    public int getBoardHeight() {
        return super.getBoardWidth();
    }

    public int getTeamCount() {
        return teamCount;
    }

    public int getCurTeamIdx() {
        return curTeamIdx;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Player getCurPlayer() {
        return teams.get(curTeamIdx).getPlayerAtIndex(curPlayerIndex);
    }

    public void setBoardHeight(int boardHeight) {
        super.setBoardHeight(boardHeight);
    }

    public int getBoardWidth() {
        return super.getBoardHeight();
    }

    public void setBoardWidth(int boardWidth) {
        super.setBoardWidth(boardWidth);
    }

    public Team getCurTeam() {
        return this.teams.get(this.curTeamIdx);
    }

    public int getCurPlayerIdx() {
        return this.getCurTeam().getCurPlayerIndex();
    }


//    public void moveTONextPlayer() {
//        this.teams.get(this.curTeamIdx).moveToNextPlayer();
//        if (this.teams.get(this.curTeamIdx).getCurPlayerIndex() == 0) {
//            this.curTeamIdx = (this.curTeamIdx + 1) % this.teams.size();
//        }
//        this.curPlayer = this.teams.get(this.curTeamIdx).getCurPlayer();
//
//    }

    public MarketController getMarketController() {
        return marketController;
    }

    public int getCurPlayerIndex() {
        return curPlayerIndex;
    }

    public void setCurPlayerIndex(int curPlayerIndex) {
        this.curPlayerIndex = curPlayerIndex;
    }

    private HashMap<Integer, ArrayList<MonsterModel>> initializeLevelMonsterMap() {
        HashMap<Integer, ArrayList<MonsterModel>> curLevelMonsterMap = new HashMap<Integer, ArrayList<MonsterModel>>();

        int curLevel;
        for (MonsterModel monsterModel : this.allMonsterModels) {
            curLevel = monsterModel.getLevel();
            if (!curLevelMonsterMap.containsKey(curLevel)) {
                curLevelMonsterMap.put(curLevel, new ArrayList<MonsterModel>());
            }
            curLevelMonsterMap.get(curLevel).add(monsterModel);
        }

        return curLevelMonsterMap;
    }

    public ArrayList<MonsterModel> getAllMonsterModels() {
        return allMonsterModels;
    }

    public void setAllMonsterModels(ArrayList<MonsterModel> allMonsterModels) {
        this.allMonsterModels = allMonsterModels;
    }

    public HashMap<Integer, ArrayList<MonsterModel>> getLevelMonsterMap() {
        return levelMonsterMap;
    }

    public void setLevelMonsterMap(HashMap<Integer, ArrayList<MonsterModel>> levelMonsterMap) {
        this.levelMonsterMap = levelMonsterMap;
    }

    public ArrayList<MonsterObject> getMonsterObjects() {
        return monsterObjects;
    }

    public void setMonsterObjects(ArrayList<MonsterObject> monsterObjects) {
        this.monsterObjects = monsterObjects;
    }
}
