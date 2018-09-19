import java.util.Random;

/**
 * Represents a category of words for Hangman game.
 */
public class Category {
    private String name;
    private int startLine;
    private int endLine;
    private Random r;

    /**
     * Class constructor.
     * @param name the category's name
     * @param startLine the line number from which this category starts
     * @param endLine the line number where this category ends
     */
    public Category(String name, int startLine, int endLine){
        this.name = name;
        this.startLine = startLine;
        this.endLine = endLine;
        r = new Random();
    }

    /**
     * Gets a random line number for this category between the starting and ending lines.
     * @return the random number between the starting and ending lines
     */
    int getRandomWordLineNumber(){
        int random = r.nextInt(endLine - (startLine + 1)) + (startLine + 1);
        return random;
    }

    @Override
    public String toString(){
        return name;
    }
}
