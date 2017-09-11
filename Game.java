
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Game {

    public Game() {
        // current data
        // uzyte litery, slowa
    }

    public String turnToHidden(String text) {
        String hiddenWord = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                hiddenWord += " ";
            }
            else {
                hiddenWord += "_";
            }
        }
        return hiddenWord;
    }

    public String unhideLetter(String word, String hiddenWord, String letter) {
        StringBuilder unhiddenWord = new StringBuilder(hiddenWord);
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        int index = word.toUpperCase().indexOf(Character.toUpperCase(letter.charAt(0)));
        while (index >= 0) {
            indexList.add(index);
            index = word.indexOf(letter, index + 1);
        }
        System.out.println(indexList);
        for (Integer i: indexList) {
            unhiddenWord.setCharAt(i, word.charAt(i));
        }
        return unhiddenWord.toString();
    }

    public static void startGame() {
        Integer playerLifepoints = 10;
        Scanner in;

        in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = in.next();

        Game newGame = new Game();
        Capital capital = new Capital("countries_and_capitals.txt");
        Player newPlayer = new Player(playerName, playerLifepoints);

        String capitalName = capital.getCapitalName();
        String capitalHidden = newGame.turnToHidden(capitalName);

        System.out.format("Your capital to guess is: %s.\n", capitalHidden);
        System.out.format("Lifepoints left: %s.\n", newPlayer.getPlayerLife());

        while (!capitalHidden.equals(capitalName)) {
            in = new Scanner(System.in);
            System.out.print("Enter a letter: ");
            String guessedLetter = in.next();
            capitalHidden = newGame.unhideLetter(capitalName, capitalHidden, guessedLetter);
            System.out.format("Your capital to guess is: %s.\n", capitalHidden);
            System.out.format("Lifepoints left: %s.\n", newPlayer.getPlayerLife());
        }
    }
}
