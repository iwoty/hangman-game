
import java.util.ArrayList;

public class View {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void print(String message, String string) {
        System.out.format("%s: %s\n", message, string);
    }

    public static void print(String message, Integer value) {
        System.out.format("%s: %d\n", message, value);
    }

    public static void printAnswer(String firstString, String secondString) {
        System.out.format("Word to guess is %s, capital of %s.\n", firstString, secondString);
    }

    public static void printWrongLetters(ArrayList<String> list) {
        System.out.println("Not-in-word letters are " + list.toString());
    }

    public static void printGameInfo(Integer guessNumber, long guessTime) {
        System.out.format("You guessed after %d tries. It took you %d seconds.\n", guessNumber, guessTime);
    }

    public static void printDashes() {
        System.out.println("-----------------------------------------------");
    }
}
