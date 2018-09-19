import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @inheritDoc
 */
public class TextFileSource implements DataSource {

    private HashMap<String, Category> categoriesMap = new HashMap<>();
    private File file;

    public TextFileSource(File inputFile){
        file = inputFile;
    }

    @Override
    public Collection<Category> listCategories() throws FileNotFoundException {
        int count = 0;
        int prevCatLine = 0;
        String catName = null;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            count++;
            String str = scanner.nextLine();
            if (str.startsWith("_")) {
                if(catName == null){
                    prevCatLine = count;
                } else {
                    Category category = new Category(catName, prevCatLine, count);
                    categoriesMap.put(catName.toLowerCase(), category);
                    prevCatLine = count;
                }
                catName = str.substring(1);
            }
        }
        if(catName != null){
            Category category = new Category(catName, prevCatLine, count);
            categoriesMap.put(catName.toLowerCase(), category);
        }
        return categoriesMap.values();
    }

    @Override
    public String getRandomWordByCategory(String category) throws FileNotFoundException, NoCategoryFoundException {
         category = category.toLowerCase();
         Category matchedCategory= categoriesMap.get(category);
         if(matchedCategory == null){
             throw new NoCategoryFoundException();
         }
         int randomLine = matchedCategory.getRandomWordLineNumber();

         Scanner scanner = new Scanner(file);

         String word = "";
         for(int i = 1; scanner.hasNextLine(); i++) {
            word = scanner.nextLine();
            if (i == randomLine) {
                break;
            }
         }
         return word;
    }
}
