
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Game {

    public Game() {
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
        ArrayList<Integer> indexList = new ArrayList<>();
        int index = word.toUpperCase().indexOf(Character.toUpperCase(letter.charAt(0)));

        while (index >= 0) {
            indexList.add(index);
            index = word.indexOf(letter, index + 1);
        }
        for (Integer i: indexList) {
            unhiddenWord.setCharAt(i, word.charAt(i));
        }
        return unhiddenWord.toString();
    }

    public boolean isEqual(String firstWord, String secondWord) {
        if (firstWord.toUpperCase().equals(secondWord.toUpperCase())) {
            return true;
        }
        else {
            return false;
        }
    }

    public void winScreen(String capitalName, String countryName, Integer guessNumber, long startTime) {
        long stopTime = System.currentTimeMillis();
        long elapsedTime = (stopTime - startTime)/1000;
        View.print("YOU WON! CONGRATS!");
        View.printAnswer(capitalName, countryName);
        View.printGameInfo(guessNumber, elapsedTime);
    }

    public static void startGame() {
        Integer playerLifepoints = 10;
        Scanner in;
        String playerName;
        String previousCapitalHidden;
        boolean isWin = false;
        ArrayList<String> wrongLetters = new ArrayList<>();
        Integer guessNumber = 0;

        in = new Scanner(System.in);
        View.print("Enter your name: ");
        playerName = in.next();
        View.printDashes();

        Game game = new Game();
        Capital capital = new Capital("countries_and_capitals.txt");

        String capitalName = capital.getCapitalName();

        View.print(capitalName);

        String countryName = capital.getCountryName();
        String capitalHidden = game.turnToHidden(capitalName);
        Player player = new Player(playerName, playerLifepoints);

        long startTime = System.currentTimeMillis();
        while (!capitalHidden.equals(capitalName) && !player.getPlayerLife().equals(0) && !isWin) {
            View.print("Your capital to guess is", capitalHidden);
            View.print("Lifepoints left", player.getPlayerLife());
            if (player.getPlayerLife().equals(1)) {
                View.printAnswer(capitalHidden, countryName);
            }
            View.printWrongLetters(wrongLetters);
            View.print("Enter letter or word: ");
            String playerGuess = in.next();
            guessNumber ++;

            if (playerGuess.length() == 1) {
                previousCapitalHidden = capitalHidden;
                capitalHidden = game.unhideLetter(capitalName, capitalHidden, playerGuess);
                if (capitalHidden.equals(previousCapitalHidden)) {
                    View.print("Wrong guess of letter! You are losing one lifepoint! ¯\\_(ツ)_/¯");
                    player.subtractPoints(1);
                    if (!wrongLetters.contains(playerGuess)) {
                        wrongLetters.add(playerGuess);
                    }
                }
                else {
                    if (game.isEqual(capitalHidden, capitalName)) {
                        isWin = true;
                        game.winScreen(capitalName, countryName, guessNumber, startTime);
                    } else {
                        View.print("Good guess! Keep it up! ( ͡° ͜ʖ ͡°)");
                    }
                }

            } else {
                if (game.isEqual(playerGuess, capitalName)) {
                    isWin = true;
                    game.winScreen(capitalName, countryName, guessNumber, startTime);
                } else {
                    View.print("Wrong guess of word! You are losing one lifepoint! ¯\\_(ツ)_/¯");
                    player.subtractPoints(2);
                }
            }
        }
    }
}
