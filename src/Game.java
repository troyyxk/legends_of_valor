
/**
 * class game
 */
public class Game {
    private Board board;
    private int boardHeight, boardWidth;

    public Game() {
        // initialize boardHeight and boardWidth, should set by child class
        this.boardHeight = 0;
        this.boardWidth = 0;
        this.board = null;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getBoardHeight() {
        return boardHeight;
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

}
