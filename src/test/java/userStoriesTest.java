import org.junit.Test;
import tools.readGedcomFile;
import tools.userStories;

import java.net.URL;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for user stories.
 */
public class userStoriesTest
{
//    static final String testFilePath = "src\\main\\resources\\testOne.ged";
//    G:\Courses Info\SSW 555 Agile Dev\Sprint\src\main\resources\TestGEDCOM.ged G:\Courses Info\SSW 555 Agile Dev\Sprint\src\test\testResources\US01\testOne.ged
            static String testfile = "US01/testOne.ged";
    private static final String testFilePath = userStoriesTest.class.getResource(testfile).getFile();

    URL url = Thread.currentThread().getContextClassLoader().getResource("US01/testOne.ged");

    @Test
    public void TestUS08T() throws Exception {
        System.out.println(url.getPath());
        readGedcomFile read = new readGedcomFile();
        read.readFile(url.getPath());
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertTrue(test.getError().contains("Error US08") );
    }

    @Test
    public void TestUS08F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertFalse(test.getError().contains("Error US08") );
    }

    @Test
    public void TestUS03T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        test.IterateInds(read.printFam(),read.printIndi());
        assertTrue(test.getError().contains("Error US03") );
    }

    @Test
    public void TestUS03F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        test.IterateInds(read.printFam(),read.printIndi());
        assertFalse(test.getError().contains("Error US03") );
    }

    @Test
    public void TestUS02T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertTrue(test.getError().contains("Error US02") );
    }

    @Test
    public void TestUS02F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertFalse(test.getError().contains("Error US02") );
    }

    @Test
    public void TestUS01T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams,indis);
        assertTrue(test.getError().contains("Error US01") );
    }

    @Test
    public void TestUS01F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams,indis);
        assertFalse(test.getError().contains("Error US01") );
    }

    @Test
    public void TestUS04F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertFalse(test.getError().contains("Error US04") );
    }

    @Test
    public void TestUS04T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertTrue(test.getError().contains("Error US04") );
    }

    @Test
    public void TestUS05T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertTrue(test.getError().contains("Error US05") );
    }

    @Test
    public void TestUS05F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertFalse(test.getError().contains("Error US05") );
    }

    @Test
    public void TestUS06F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertFalse(test.getError().contains("Error US06") );
    }

    @Test
    public void TestUS06T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertTrue(test.getError().contains("Error US06") );
    }

    @Test
    public void TestUS07T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateInds(Fams,indis);
        assertTrue(test.getError().contains("Error US07") );
    }

    @Test
    public void TestUS07F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateInds(Fams,indis);
        assertFalse(test.getError().contains("Error US07") );
    }

    @Test
    public void TestUS09T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertTrue(test.getError().contains("Error US09") );
    }

    @Test
    public void TestUS09F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams,indis);
        assertFalse(test.getError().contains("Error US09") );
    }

    @Test
    public void TestUS10T() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US10(Fams,indis);
        assertTrue(test.getError().contains("Error US10") );
    }

    @Test
    public void TestUS10F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(testFilePath);
        userStories test = new userStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US10(Fams,indis);
        assertFalse(test.getError().contains("Error US10") );
    }



}
