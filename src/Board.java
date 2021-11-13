import java.lang.reflect.Array;
import java.util.*;

/**
 * class Board :
 * 
 *  Variables:
 *          
 *              int width:
 * 
 *              int height:
 * 
 *              Cell[][] cells:
 * 
 *              PlaceableFactory placeableFactory:  a class with only one method
 *          
 *                                                  Pleacable getPlaceable(String placeableType):
 *                                          
 *                                                              return a cell/Pleacable object speficed by "placeableType"
 *  Constructor:
 * 
 *              Board(int width , int height):
 * 
 *  Methods:
 *              void addMoveable( int [] pos, Movable movable):
 * 
 *                                  put player /"movable" at  this.cells[pos[0]][pos[1]]               
 * 
 *              int[] getRandomInitialPosition():
 * 
 *                                  return random x, y 
 * 
 *              void getRandomMap() :
 * 
 *                                  set this.cells to different types of cells
 *              
 *              boolean containPosition(HashMap<Integer, ArrayList<Integer>> builtCells, int x, int y) :
 * 
 *  
 *              void getBlankCells():
 *      
 *                                  set this.cells to the default type of cell
 * 
 *              Cell[][] getCells() :
 * 
 *                                  return this.cells
 * 
 *              void moveMovable(Movable movable, int[] oldPos, int[] newPos) :
 * 
 *                                  move player/Movable from "oldPos" to "newPos"
 * 
 *              ArrayList<char[]> getBlankBoard():
 * 
 *              
 *              void drawBoard():
 * 
 *              int getWidth():
 * 
 *                                  return this.width
 * 
 *              int getHeight():
 * 
 *                                  return this.height
 */

// this is class for keep width and height of board and provide an empty board
public class Board {
    private int width, height;
    private Cell[][] cells;
    private PlaceableFactory placeableFactory;

    public Board(int width, int height) {
        if (width < 0 || height < 0) {
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
        for (int i = 0; i < this.width; i++) {
            pseudoHGrid.append("+--");
        }
        pseudoHGrid.append("+");
        char[] hGrid = pseudoHGrid.toString().toCharArray();

        // horizontal content
        StringBuilder pseudoHContent = new StringBuilder();
        for (int i = 0; i < this.width; i++) {
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

    public static final String SANE = "\u001B[0m";

    public static final String HIGH_INTENSITY = "\u001B[1m";
    public static final String LOW_INTENSITY = "\u001B[2m";

    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLINK = "\u001B[5m";
    public static final String RAPID_BLINK = "\u001B[6m";
    public static final String REVERSE_VIDEO = "\u001B[7m";
    public static final String INVISIBLE_TEXT = "\u001B[8m";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public void drawBoard() {

        char curSymbol;

        for (int i = 0; i < this.getHeight(); i++) {
            System.out.println();
            for (int j = 0; j < this.width; j++) {
                curCell = this.cells[i][j];

                //isAccessible
                if (curCell.isAccessible()) {
                    System.out.print(ANSI_BLACK_BACKGROUND);

                }
                //isMarket
                else if (curCell.isMarket()) {
                    System.out.print(ANSI_YELLOW_BACKGROUND);
                }

                //isPlain
                else if (curCell.isPlain()) {
                    System.out.print(ANSI_WHITE_BACKGROUND);
                }
                //isBush
                else if (curCell.isBush()) {
                    System.out.print(ANSI_GREEN_BACKGROUND);
                }
                //isCave
                else if (curCell.isCave()) {
                    System.out.print(ANSI_PURPLE_BACKGROUND);
                }
                //isKoulou
                else if (curCell.isKoulou()) {
                    System.out.print(ANSI_CYAN_BACKGROUND);
                }

                //H
                if (curCell.hasHero()) {
                    System.out.print("H");

                } else {
                    System.out.print(" ");
                }

                //M
                if (curCell.hasMonster()) {
                    System.out.print("M");

                } else {
                    System.out.print(" ");
                }
                //reset color
                System.out.print(ANSI_RESET);
            }
        }

        for (char[] line : curBoard) {
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
