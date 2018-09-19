import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Represents a data source that used as dictionary for Hangman game
 */
public interface DataSource {

    /**
     * Gets all the categories of the words from this data source represented as a collection of Strings.
     * @return a collection of categories of the words. Represented as a collection of Strings.
     * @throws FileNotFoundException if an input or output exception occurred
     */
    Collection<Category> listCategories() throws FileNotFoundException;

    /**
     * Gets a random word for the specified category from this data source.
     * @param category the category's name
     * @return a random word for the specified category
     * @throws FileNotFoundException if an input or output exception occurred
     * @throws NoCategoryFoundException if no category found exception occurred
     */
    String getRandomWordByCategory(String category) throws FileNotFoundException, NoCategoryFoundException;

}
