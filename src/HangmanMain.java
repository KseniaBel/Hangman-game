import java.io.File;

/**
 * The main application class.
 */
public class HangmanMain {

    public static void main(String[] args) throws Exception{
        HangmanGame gameSession = new HangmanGame(new TextFileSource(new File("CategoriesAndWords.txt")), System.in, System.out);
        gameSession.play();
    }
}
