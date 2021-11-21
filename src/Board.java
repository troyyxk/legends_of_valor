import java.lang.reflect.Array;
import java.util.*;
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
 *              void addHero( int [] pos, Movable hero):
 * 
 *                                  put player /"hero" at  this.cells[pos[0]][pos[1]]        
 * 
 *              boolean posHasNoMonster(int[] pos):
 * 
 *                                  return true if there is an monster at this.cells[pos[0]][pos[1]]              
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
 *              Cell getCell(int[] pos) :
 *                  
 *                                  return Cell at this.cells[pos[0]][pos[1]]        
 * 
 *              void moveHero(Movable movable, int[] oldPos, int[] newPos) :
 * 
 *                                  move player/Movable from "oldPos" to "newPos"
 * 
 *              void moveMonster(Movable movable, int[] oldPos, int[] newPos):
 * 
 *                                  move monster from "oldPos" to "newPos"
 * 
 *              ArrayList<char[]> getBlankBoard():
 * 
 *              
 *              void drawBoard():
 *  
 *                                  print the entire game board on terminal
 * 
 *              int getWidth():
 * 
 *                                  return this.width
 * 
 *              int getHeight():
 * 
 *                                  return this.height
 * 
 */
// this is class for keep width and height of board and provide an empty board
public class Board {
    private int width, height;
    private Cell[][] cells;
    private PlaceableFactory placeableFactory;
    private final int specialTypeCount = 7;


    public Board(int width, int height) {
        if (width < 0 || height < 0){
            throw new Error("In board, width or height cannot be negative!");
        }
        this.width = width;
        this.height = height;
        this.placeableFactory = new PlaceableFactory();
        this.cells = new Cell[height][width];
        getBlankCells();
        // getRandomMap();
    }

    public void addHero(int[] pos, Movable hero) {
        this.cells[pos[0]][pos[1]].setHeroObject(hero);
    }

    public boolean posHasNoMonster(int[] pos) {
        return this.cells[pos[0]][pos[1]].getMonsterObject() == null;
    }

    public void addMonsterObjectToPos(Movable monsterObject, int[] pos) {
        this.cells[pos[0]][pos[1]].setMonsterObject(monsterObject);
        monsterObject.setPos(pos);
    }

    public void addMonster(int[] pos, Movable monster) {
        this.cells[pos[0]][pos[1]].setHeroObject(monster);
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



    // for legends and monsters
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

    public void moveHero(Movable movable, int[] oldPos, int[] newPos) {
        this.cells[oldPos[0]][oldPos[1]].removeHero();
        this.cells[newPos[0]][newPos[1]].setHeroObject(movable);
        movable.setPos(newPos);
    }

    public void removeHero(int[] pos) {
        this.cells[pos[0]][pos[1]].removeHero();
    }

    public void setHero(Player player) {
        int[] pos = player.getPos();
        this.cells[pos[0]][pos[1]].setHeroObject(player);
    }

    public void moveMonster(Movable movable, int[] oldPos, int[] newPos) {
        this.cells[oldPos[0]][oldPos[1]].removeMonster();
        this.cells[newPos[0]][newPos[1]].setMonsterObject(movable);
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

            /**
             *      layer 1 :    N - N - N
             *      layer 2 :    N H | M N
             *      layer 3 :    N - N - N
             */
            String layer_1and3_Str = "";
            String layer_2_Str = "";
            for (int j = 0; j < this.width; j++) {
                Cell curCell = this.cells[i][j];

                //isAccessible
                if (!curCell.isAccessible()) {

                    layer_1and3_Str = layer_1and3_Str + ANSI_WHITE + ANSI_BLACK_BACKGROUND + " I - I - I " + ANSI_RESET;
                    layer_2_Str = layer_2_Str + ANSI_WHITE + ANSI_BLACK_BACKGROUND;

                    //H

                    layer_2_Str = layer_2_Str + " I   ";//" N H | M N"

                    //M
                    layer_2_Str = layer_2_Str + "    I " + ANSI_RESET;//" N H | M N"

                }
                //isMarket
                else if (curCell.isNexus()) {

                    layer_1and3_Str = layer_1and3_Str + LOW_INTENSITY + ANSI_BLACK + ANSI_YELLOW_BACKGROUND
                            + " N - N - N " + ANSI_RESET;
                    layer_2_Str = layer_2_Str + ANSI_BLACK + LOW_INTENSITY + ANSI_YELLOW_BACKGROUND;

                    //H
                    if (curCell.hasHero()) {

                        layer_2_Str = layer_2_Str + " N"  + " H"+curCell.getHeroMarker()+" ";//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + " N" + "    ";//" N H | M N"
                    }

                    //M
                    if (curCell.hasMonster()) {

                        layer_2_Str = layer_2_Str  + "M" +curCell.getMonsterMarker() + ANSI_BLACK + " N " + ANSI_RESET;//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + "  " + ANSI_BLACK + " N " + ANSI_RESET;//" N H | M N"
                    }
                }

                //isPlain
                else if (curCell.isPlain()) {

                    layer_1and3_Str = layer_1and3_Str + LOW_INTENSITY + ANSI_BLACK + ANSI_WHITE_BACKGROUND
                            + " P - P - P " + ANSI_RESET;
                    layer_2_Str = layer_2_Str + LOW_INTENSITY + ANSI_BLACK + ANSI_WHITE_BACKGROUND;

                    //H
                    if (curCell.hasHero()) {

                        layer_2_Str = layer_2_Str + " P"  + " H"+curCell.getHeroMarker() +" ";//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + " P" + "    ";//" N H | M N"
                    }

                    //M
                    if (curCell.hasMonster()) {

                        layer_2_Str = layer_2_Str + "M" +curCell.getMonsterMarker() + ANSI_BLACK + " P " + ANSI_RESET;//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + "  " + ANSI_BLACK + " P " + ANSI_RESET;//" N H | M N"
                    }
                }
                //isBush
                else if (curCell.isBush()) {

                    layer_1and3_Str = layer_1and3_Str + LOW_INTENSITY + ANSI_BLACK + ANSI_GREEN_BACKGROUND
                            + " B - B - B " + ANSI_RESET;
                    layer_2_Str = layer_2_Str + ANSI_BLACK + LOW_INTENSITY + ANSI_GREEN_BACKGROUND;

                    //H
                    if (curCell.hasHero()) {

                        layer_2_Str = layer_2_Str + " B"  + " H"+curCell.getHeroMarker()+" ";//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + " B" + "    ";//" N H | M N"
                    }

                    //M
                    if (curCell.hasMonster()) {

                        layer_2_Str = layer_2_Str  + "M"  +curCell.getMonsterMarker()+ ANSI_BLACK + " B " + ANSI_RESET;//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + "  " + ANSI_BLACK + " B " + ANSI_RESET;//" N H | M N"
                    }
                }
                //isCave
                else if (curCell.isCave()) {

                    layer_1and3_Str = layer_1and3_Str + LOW_INTENSITY + ANSI_BLACK + ANSI_CYAN_BACKGROUND
                            + " C - C - C " + ANSI_RESET;
                    layer_2_Str = layer_2_Str + ANSI_BLACK + LOW_INTENSITY + ANSI_CYAN_BACKGROUND;

                    //H
                    if (curCell.hasHero()) {

                        layer_2_Str = layer_2_Str + " C"  + " H"+curCell.getHeroMarker()+" ";//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + " C" + "    ";//" N H | M N"
                    }

                    //M
                    if (curCell.hasMonster()) {

                        layer_2_Str = layer_2_Str + "M" +curCell.getMonsterMarker() + ANSI_BLACK + " C " + ANSI_RESET;//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + "  " + ANSI_BLACK + " C " + ANSI_RESET;//" N H | M N"
                    }
                }
                //isKoulou
                else if (curCell.isKoulou()) {

                    layer_1and3_Str = layer_1and3_Str + LOW_INTENSITY + ANSI_BLACK + ANSI_PURPLE_BACKGROUND
                            + " K - K - K " + ANSI_RESET;
                    layer_2_Str = layer_2_Str + ANSI_BLACK + LOW_INTENSITY + ANSI_PURPLE_BACKGROUND;

                    //H
                    if (curCell.hasHero()) {

                        layer_2_Str = layer_2_Str + " K"  + " H"+curCell.getHeroMarker()+" ";//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + " K" + "    ";//" N H | M N"
                    }

                    //M
                    if (curCell.hasMonster()) {

                        layer_2_Str = layer_2_Str + "M" +curCell.getMonsterMarker()+ ANSI_BLACK + " K " + ANSI_RESET;//" N H | M N"

                    } else {
                        layer_2_Str = layer_2_Str + "  " + ANSI_BLACK + " K " + ANSI_RESET;//" N H | M N"
                    }
                }

            }
            //print
            System.out.println(layer_1and3_Str); // layer 1
            System.out.println(layer_2_Str); //layer 2
            System.out.println(layer_1and3_Str);//  layer 3

        }

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void getLoVStandardBoard() {
        // add enermy nexus
        for (int i = 0; i< this.width; i++) {
            this.cells[0][i].setStaticObject(placeableFactory.getPlaceable("EnermyNexus"));
        }

        // add own nexus
        for (int i = 0; i< this.width; i++) {
            this.cells[this.height - 1][i].setStaticObject(placeableFactory.getPlaceable("HeroNexus"));
        }

        // add non accessible
        for (int i = 0; i< this.height; i++) {
            this.cells[i][2].setStaticObject(placeableFactory.getPlaceable("NonAccessible"));
        }
        for (int i = 0; i< this.height; i++) {
            this.cells[i][5].setStaticObject(placeableFactory.getPlaceable("NonAccessible"));
        }

        Random random = new Random();

        // bush
        for (int i = 0; i < specialTypeCount; i++) {
            int y = random.nextInt(6) + 1;
            int x = random.nextInt(6);
            if (x >= 4) {
                x += 2;
            }
            else if (x >= 2) {
                x += 1;
            }
            this.cells[y][x].setStaticObject(placeableFactory.getPlaceable("Bush"));
        }

        // cave
        for (int i = 0; i < specialTypeCount; i++) {
            int y = random.nextInt(6) + 1;
            int x = random.nextInt(6);
            if (x >= 4) {
                x += 2;
            }
            else if (x >= 2) {
                x += 1;
            }
            this.cells[y][x].setStaticObject(placeableFactory.getPlaceable("Cave"));
        }

        // koulou
        for (int i = 0; i < specialTypeCount; i++) {
            int y = random.nextInt(6) + 1;
            int x = random.nextInt(6);
            if (x >= 4) {
                x += 2;
            }
            else if (x >= 2) {
                x += 1;
            }
            this.cells[y][x].setStaticObject(placeableFactory.getPlaceable("Koulou"));
        }



//        // add bush
//        this.cells[3][1].setStaticObject(placeableFactory.getPlaceable("Bush"));
//        this.cells[3][3].setStaticObject(placeableFactory.getPlaceable("Bush"));
//        this.cells[4][3].setStaticObject(placeableFactory.getPlaceable("Bush"));
//        this.cells[1][6].setStaticObject(placeableFactory.getPlaceable("Bush"));
//        this.cells[1][7].setStaticObject(placeableFactory.getPlaceable("Bush"));
//        this.cells[4][7].setStaticObject(placeableFactory.getPlaceable("Bush"));
//
//        // add cave
//        this.cells[3][0].setStaticObject(placeableFactory.getPlaceable("Cave"));
//        this.cells[1][3].setStaticObject(placeableFactory.getPlaceable("Cave"));
//
//        // add koulou
//        this.cells[5][0].setStaticObject(placeableFactory.getPlaceable("Koulou"));
//        this.cells[5][1].setStaticObject(placeableFactory.getPlaceable("Koulou"));
//        this.cells[5][3].setStaticObject(placeableFactory.getPlaceable("Koulou"));
//        this.cells[3][4].setStaticObject(placeableFactory.getPlaceable("Koulou"));
//        this.cells[3][6].setStaticObject(placeableFactory.getPlaceable("Koulou"));

    }
}