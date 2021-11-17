import java.util.ArrayList;
import java.util.HashMap;

public class RPGGame {

    private Board board;
    private int boardHeight, boardWidth, teamCount, curTeamIdx, curPlayerIndex;
    private ArrayList<Team> teams;
    private Player curPlayer;
    private MarketController marketController;

    private MonsterGallary monsterGallary;
    private ArrayList<MonsterModel> allMonsterModels;
    private HashMap<Integer, ArrayList<MonsterModel>> levelMonsterMap;
    private ArrayList<MonsterObject> monsterObjects;

    public RPGGame() {
        // initialize boardHeight and boardWidth, should set by child class
        this.boardHeight = 0;
        this.boardWidth = 0;
        this.board = null;
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
                curPos = this.board.getRandomInitialPosition();
                curPlayer.setPos(curPos);
                this.board.addHero(curPos, curPlayer);
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
        this.board = new Board(this.boardWidth, this.boardHeight);
    }

    public void drawBoard() {
        this.board.drawBoard();
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
        return boardHeight;
    }

    public Board getBoard() {
        return board;
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
        this.boardHeight = boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public Team getCurTeam() {
        return this.teams.get(this.curTeamIdx);
    }

    public int getCurPlayerIdx() {
        return this.getCurTeam().getCurPlayerIndex();
    }


    public void moveTONextPlayer() {
        this.teams.get(this.curTeamIdx).moveToNextPlayer();
        if (this.teams.get(this.curTeamIdx).getCurPlayerIndex() == 0) {
            this.curTeamIdx = (this.curTeamIdx + 1) % this.teams.size();
        }
        this.curPlayer = this.teams.get(this.curTeamIdx).getCurPlayer();

    }

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
