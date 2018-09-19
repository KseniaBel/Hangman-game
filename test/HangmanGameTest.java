import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class HangmanGameTest {

    @Test
    public void play() throws IOException {
        HangmanGame gameSession = new HangmanGame(new TextFileSource(new File("test/TestFile.txt")),
                new FileInputStream("test/UserInput.txt"), new FileOutputStream("test/actualResult.txt"));
        gameSession.play();
        List<String> actualLines = Files.readAllLines(Paths.get("test/actualResult.txt"));
        List<String> expectedLines = Files.readAllLines(Paths.get("test/ExpectedResult.txt"));
        Assert.assertArrayEquals(expectedLines.toArray(), actualLines.toArray());
    }

    @Test
    public void displayRandomWordFromCategory() {
        HangmanGame gameSession = new HangmanGame(new TextFileSource(new File("test/TestFile.txt")), System.in, System.out);
        StringBuilder b = gameSession.displayMaskedWord("Dfdggd", 8);
        assertEquals("_ _ _ _ _ _ ", b.toString());
        b = gameSession.displayMaskedWord("D D", 8);
        assertEquals("_   _ ", b.toString());
    }
}