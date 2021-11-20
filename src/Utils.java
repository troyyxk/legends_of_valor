import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * Class of utility methods. All methods are static
 * 
 *  Methods:
 * 
 *          static String takeInput():
 * 
 *                                          ask user to type in something
 * 
 *          static char takeOptionInput(ArrayList<Character> options) :
 * 
 *                                          limit the range of valid input
 * 
 *          static String takeName():
 * 
 *                                  ask user to type in a name
 * 
 *          
 *          static char takeMarker():
 * 
 *                                  ask user to type a marker which is used to present its character and to be display on map
 * 
 *          static int takeIntInput(int lowerBound, int upperBound):
 * 
 * 
 *          static boolean takeYesFromInput() :
 * 
 *                                      ask user to answer yes or no 
 * 
 *          static boolean isNum(String str):
 * 
 *                                      if the "str" can be convert to a number
 *  
 *          static boolean isValidCoord(int[] coord):
 *      
 *                                      check the input x y is within bound
 * 
 *          static int[] takeCoordFromInput():
 * 
 *                                      ask for x y 
 * 
 *          static ArrayList<ArrayList<String>> takeAttributeFromFile(String filePath) :
 * 
 *          
 *          static boolean getRandomBollean(double p) :
 * 
 *                                  p is probability return boolean is true
 * 
 *                              
 *          static boolean getDodged(double p) :
 * 
 * 
 *          //printing methods
 * 
 *          ....
 */

public class Utils {

    /**Utils
     * Take String input from cmd
     * @return input
     */
    public static String takeInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    // take option input from a list of options
    public static char takeOptionInput(ArrayList<Character> options) {
        String input;
        char charInput;
        while (true) {
            input = takeInput();
            if (input.length() != 1) {
                System.out.println("Please enter a character!");
                continue;
            }
            charInput = Character.toUpperCase(input.charAt(0));
            if (!options.contains(charInput)) {
                System.out.println("Invalid option, please select and enter from provided options!");
                continue;
            }
            return charInput;

        }
    }


    public static String takeName() {
        String input = null;
        System.out.println("Please enter Player name:");
        while (true) {
            input = Utils.takeInput();
            if (input == "") {
                System.out.println("Name cannot be empty!");
                continue;
            }
            break;

        }
        return input;
    }

    public static char takeMarker() {
        String input = null;
        System.out.println("Please enter Player marker:");
        while (true) {
            input = Utils.takeInput();
            if (input.length() != 1) {
                System.out.println("Marker has to be exact 1 character!");
                continue;
            }
            break;

        }
        return input.charAt(0);
    }

    // take an integer input which should be >= upperBound and <= lowerBound
    public static int takeIntInput(int lowerBound, int upperBound) {
        while (true) {
            String input = Utils.takeInput();
            // check if the input is integer
            if (!input.matches("\\d+$")) {
                System.out.println("Invalid input, request an integer!");
                continue;
            }

            // check if the input is within the boundary
            int intInput = Integer.parseInt(input);
            if (intInput < lowerBound) {
                System.out.println("Invalid input, the input should be >= " + lowerBound);
                continue;
            }
            if (intInput > upperBound) {
                System.out.println("Invalid input, the input should be <= " + upperBound);
                continue;
            }

            return intInput;
        }
    }

    /**
     * Read yes or no from input
     * @return boolean, true for yes, false for no
     */
    public static boolean takeYesFromInput() {
        while (true) {
            String input = Utils.takeInput();
            if (input.equalsIgnoreCase("Yes")) {
                return true;
            }
            else if (input.equalsIgnoreCase("No")) {
                return false;
            }
            else {
                System.out.println("Invalid input! Try again! ");
            }
        }
    }
    //check if is number
    public static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    //check within range
    public static boolean isValidCoord(int[] coord){
        return (coord.length == 2 && coord[0] >= 0 && coord[0] < 8 && coord[1] >= 0 && coord[1] < 8);
    }
    /**
     * Take Coordinate from input
     * assume user treats first row/col as 1 and not 0, shifts later
     * @return int[] as coordinate
     */

    public static int[] takeCoordFromInput() {
        while (true) {
            String input = Utils.takeInput();
            if (input.length() == 3 && input.charAt(1) == ',') {
                if(isNum(input.split(",")[0]) && isNum(input.split(",")[1])){
                    int[] coord = new int[]{Integer.parseInt(input.split(",")[0]),Integer.parseInt(input.split(",")[1])};
                    if(isValidCoord(coord)) return coord;
                }
            }
            System.out.println("Invalid coordinate! Try again! ");
        }
    }

    // take attributes from file at the specific path
    public static ArrayList<ArrayList<String>> takeAttributeFromFile(String filePath) {
//        String absolutePath = System.getProperty("user.dir") + "/" + filePath;
//        System.out.println(absolutePath);
//
//        Path pathToFile = Paths.get(filePath);
//        System.out.println(pathToFile.toAbsolutePath());
        filePath = System.getProperty("user.dir") + "/src/" + filePath;
        ArrayList<ArrayList<String>> allAttributes = new ArrayList<ArrayList<String>>();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : lines) {
            ArrayList<String> attributes = new ArrayList<String>(Arrays.asList(s.split("/|\s|\t")));
            attributes.removeIf(a -> a.length() == 0);
            // System.out.println(attributes.size());
            allAttributes.add(attributes);
        }
        // remove the first line as it is the header
        allAttributes.remove(0);
        return allAttributes;
    }

    // return a random boolean value, p is the possibility of getting true
    public static boolean getRandomBollean(double p) {
        Random randomGenerator = new Random();
        return randomGenerator.nextDouble() < p;
    }

    public static boolean getDodged(double p) {
        return getRandomBollean(p);
    }

    public static void printArmoryHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/damage reduction");
    }

    public static void printArmory(int index, Armory curArmory) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curArmory.getName());
        System.out.print(" ");
        System.out.print(curArmory.getCost());
        System.out.print(" ");
        System.out.print(curArmory.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curArmory.getDamageReduction());
        System.out.println("");
    }

    public static void printPotionHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/attribute increase/attribute affected");
    }

    public static void printPotion(int index, Potion curPotion) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curPotion.getName());
        System.out.print(" ");
        System.out.print(curPotion.getCost());
        System.out.print(" ");
        System.out.print(curPotion.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curPotion.getAttributeIncrease());
        System.out.print(" ");
        for (int i = 0; i < curPotion.getAttributeAffected().size(); i++) {
            System.out.print(curPotion.getAttributeAffected().get(i));
            if (i < curPotion.getAttributeAffected().size() - 1) {
                System.out.print("/");
            }
        }
        System.out.println("");
    }

    public static void printSpellHeader() {
        System.out.print("        ");
        System.out.println("Name/cost/required level/damage/mana cost/type");
    }

    public static void printSpell(int index, Spell curSpell) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(curSpell.getName());
        System.out.print(" ");
        System.out.print(curSpell.getCost());
        System.out.print(" ");
        System.out.print(curSpell.getRequiredLevel());
        System.out.print(" ");
        System.out.print(curSpell.getDamage());
        System.out.print(" ");
        System.out.print(curSpell.getManaCost());
        System.out.print(" ");
        System.out.print(curSpell.getType());
        System.out.println("");
    }

    public static void printWeaponHeader()  {
        System.out.print("        ");
        System.out.println("Name/cost/level/damage/required hands");
    }

    public static void printWeapon(int index, Weapon weapon) {
        System.out.print("    ");
        System.out.print("[" + index + "]");
        System.out.print(" ");
        System.out.print(weapon.getName());
        System.out.print(" ");
        System.out.print(weapon.getCost());
        System.out.print(" ");
        System.out.print(weapon.getLevel());
        System.out.print(" ");
        System.out.print(weapon.getDamage());
        System.out.print(" ");
        System.out.print(weapon.getRequiredHands());
        System.out.println("");
    }

}
