import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the logic of the Hangman game.
 */
public class HangmanGame {
    private DataSource source;
    private PrintStream stream;
    private InputStream input;
    private int score = 0;

    /**
     * Class constructor with the specifying the data source, input and output.
     * @param source data source that used as dictionary for Hangman game
     * @param input instance of {@link InputStream}
     * @param output instance of {@link OutputStream}
     */
    public HangmanGame(DataSource source, InputStream input, OutputStream output){
        this.source = source;
        stream = new PrintStream(output);
        this.input = input;
    }

    /**
     * Represents the game logic. Starts with displaying all the categories of the words.
     * The user chooses one of them.
     * A random word from the chosen category appears with the letters represented by "_" symbols.
     * The user should guess the word by inputting one letter after another.
     * There are 10 attempts. If the user has not guessed the word after 10 attempts the game finishes.
     * If the user has guessed the word, his score increases, and he can choose another category.
     * @throws FileNotFoundException if an input or output exception occurred
     */
    public void play() throws FileNotFoundException {
        while(true) {
            int attempts = 10;
            boolean isWordGuessed = false;

            stream.println("Please choose a category:");
            source.listCategories().forEach(cat -> stream.println(cat));

            String category;
            Scanner scanner = new Scanner(input);
            try {
                category = scanner.nextLine();
            } catch(NoSuchElementException e){
                break; //in case input does not come from console input
            }

            String randomWord;
            try {
                randomWord = source.getRandomWordByCategory(category);
            } catch (NoCategoryFoundException e) {
                continue;
            }

            StringBuilder maskedWord = displayMaskedWord(randomWord, attempts);

            while(attempts > 0 && !isWordGuessed){
                stream.println("Please enter a letter:");

                char letter = scanner.nextLine().toLowerCase().charAt(0);

                if(randomWord.toLowerCase().indexOf(letter) == -1){
                    stream.println("The word/phrase doesn't have this letter.");
                    attempts--;
                }

                stream.printf("Attempts left: %d\n", attempts);
                stream.print("Current word/phrase: ");
                for(int y = 0; y < randomWord.length(); y++) {
                    if (randomWord.toLowerCase().charAt(y) == letter) {
                        maskedWord.setCharAt(y*2, randomWord.charAt(y));
                    }
                }
                stream.println(maskedWord);

                if(maskedWord.indexOf("_") == -1){
                    stream.printf("Congratulations you have revealed the word/phrase:\n%s\nCurrent score: %d\n",
                            maskedWord,++score);
                    isWordGuessed = true;
                }
            }
            if(!isWordGuessed) return;
        }
    }

    /**
     * Displays the number of attempts left and the specified word with the letters represented by "_" symbols.
     * @param word the word to be displayed
     * @param attempts the number of attempts left
     * @return the word with the letters represented by "_" symbols
     */
    protected StringBuilder displayMaskedWord(String word, int attempts){
        StringBuilder maskedWord = new StringBuilder();
        stream.printf("Attempts left: %d\n", attempts);
        stream.print("Current word/phrase: ");

        for(int y = 0; y < word.length(); y++){
            if(word.charAt(y) != ' '){
                maskedWord.append("_ ");
            } else {
                maskedWord.append("  ");
            }
        }
        stream.println(maskedWord);
        return maskedWord;
    }
}
