
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nextGame;

        Game.startGame();

        View.print("Do you want to start game once again? yes/no");
        nextGame = in.next();

        while (nextGame.equals("yes")) {
            Game.startGame();
            View.print("Do you want to start game once again? yes/no");
            nextGame = in.next();
        }
    }
}
