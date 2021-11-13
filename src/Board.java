import java.lang.reflect.Array;
import java.util.*;

// this is class for keep width and height of board and provide an empty board
public class Board {
    private int width, height;
    private Cell[][] cells;
    private PlaceableFactory placeableFactory;

    public Board(int width, int height) {
        if (width < 0 || height < 0){
            throw new Error("In board, width or height cannot be negative!");
        }
        this.width = width;
        this.height = height;
        this.placeableFactory = new PlaceableFactory();
        this.cells = new Cell[height][width];
        getBlankCells();
        getRandomMap();
    }

    public void addMoveable(int[] pos, Movable movable) {
        this.cells[pos[0]][pos[1]].setMovableObject(movable);
    }

    public int[] getRandomInitialPosition() {
        int[] position = new int[2];
        Random random = new Random();

        while (true) {
            int x = random.nextInt(this.width);
            int y = random.nextInt(this.height);
            Cell curCell = this.cells[y][x];
            if (curCell.isAvailable()) {
                position[0] = y;
                position[1] = x;
                break;
            }
        }

        return position;
    }

    private void getRandomMap() {
        Random random = new Random();
        double nonAccessiblePercent = 0.2;
        double marketPerecent = 0.3;
        HashMap<Integer, ArrayList<Integer>> nonAccessibleCells, marketCells;
        nonAccessibleCells = new HashMap<Integer, ArrayList<Integer>>();
        marketCells = new HashMap<Integer, ArrayList<Integer>>();

        int x, y;
        for (int i = 0; i < nonAccessiblePercent * (width * height); i++) {
            x = random.nextInt(this.width);
            y = random.nextInt(this.height);
            if (!containPosition(nonAccessibleCells, x, y)) {
                this.cells[y][x].setStaticObject(placeableFactory.getPlaceable("NonAccessibleCell"));
            }

            if (!nonAccessibleCells.containsKey(y)) {
                nonAccessibleCells.put(y, new ArrayList<Integer>());
            }
            nonAccessibleCells.get(y).add(x);
        }

        for (int i = 0; i < marketPerecent * (width * height); i++) {
            x = random.nextInt(this.width);
            y = random.nextInt(this.height);
            if (!containPosition(nonAccessibleCells, x, y) && !containPosition(marketCells, x, y)) {
                this.cells[y][x].setStaticObject(placeableFactory.getPlaceable("MarketCell"));
            }
            if (!marketCells.containsKey(y)) {
                marketCells.put(y, new ArrayList<Integer>());
            }
            marketCells.get(y).add(x);
        }
    }

    private boolean containPosition(HashMap<Integer, ArrayList<Integer>> builtCells, int x, int y) {
        if (builtCells.containsKey(y) && builtCells.get(y).contains(x)) {
            return true;
        }
        return false;
    }

    private void getBlankCells() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Cell getCell(int[] pos) {
        return this.cells[pos[0]][pos[1]];
    }

    public void moveMovable(Movable movable, int[] oldPos, int[] newPos) {
        this.cells[oldPos[0]][oldPos[1]].removeMovable();
        this.cells[newPos[0]][newPos[1]].setMovableObject(movable);
        movable.setPos(newPos);
    }

    public ArrayList<char[]> getBlankBoard() {
        ArrayList<char[]> blankBoard = new ArrayList<char[]>();

        // horizontal grid
        StringBuilder pseudoHGrid = new StringBuilder();
        for (int i = 0; i < this.width; i++){
            pseudoHGrid.append("+--");
        }
        pseudoHGrid.append("+");
        char[] hGrid = pseudoHGrid.toString().toCharArray();

        // horizontal content
        StringBuilder pseudoHContent = new StringBuilder();
        for (int i = 0; i < this.width; i++){
            pseudoHContent.append("|  ");
        }
        pseudoHContent.append("|");
        char[] hContent = pseudoHContent.toString().toCharArray();

        // build the board
        for (int i = 0; i < this.height; i++) {
            blankBoard.add(hGrid);
            blankBoard.add(Arrays.copyOf(hContent, hContent.length));
        }
        blankBoard.add(hGrid);

        return blankBoard;
    }

    public void drawBoard() {
        System.out.println("M for market");
        System.out.println("X for inaccessible");
        ArrayList<char[]> curBoard = this.getBlankBoard();
        char curSymbol;

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.width; j++) {
                curSymbol = this.cells[i][j].getMarker();
                Array.setChar(curBoard.get(2*i + 1), 3*j + 1, curSymbol);
            }
        }

        for (char[] line : curBoard){
            System.out.println(new String(line));
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}