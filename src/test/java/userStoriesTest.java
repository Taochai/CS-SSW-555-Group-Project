import objects.Family;
import objects.Individual;
import org.junit.Test;
import tools.*;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for user stories.
 */
public class userStoriesTest
{

//            static String testfile = "US01/testOne.ged";
//    private static final String falseTestFile = userStoriesTest.class.getResource(testfile).getFile();
//
//    URL url = Thread.currentThread().getContextClassLoader().getResource("US01/testOne.ged");

    private boolean errorContain(Set<String> errorSet, String errorInfo){
        for (String s : errorSet) {
            if(s.contains(errorInfo))
                return true;
        }
        return false;
    }

    @Test
    public void TestUS01T() throws Exception {
        String trueTestFile = "resources/us01/us01DatesAfterToday.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        us01 test = new us01();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams,indis);
        assertTrue(errorContain(test.getError(),  "US01"));
    }

    @Test
    public void TestUS01F() throws Exception {
        String falseTestFile = "resources/us01/us01DatesBeforeToday.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        us01 test = new us01();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams,indis);
        assertFalse(errorContain(test.getError(),  "US01"));
    }

    @Test
    public void TestUS02T() throws Exception {
        String trueTestFile = "resources/us02/us02BirthAfterMarriage.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        us02 test = new us02();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US02(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),  "US02"));
    }

    @Test
    public void TestUS02F() throws Exception {
        String falseTestFile = "resources/us02/us02BirthBeforeMarriage.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        us02 test = new us02();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US02(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US02") );
    }

    @Test
    public void TestUS03T() throws Exception {
        String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
//        Map _Fams = read.printFam();
        us03 test = new us03();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US03(curIndis);
        }
        assertTrue(errorContain(test.getError(),"US03") );
    }

    @Test
    public void TestUS03F() throws Exception {
        String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
//        Map _Fams = read.printFam();
        us03 test = new us03();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US03(curIndis);
        }
        assertFalse(errorContain(test.getError(),"US03") );
    }

    @Test
    public void TestUS04T() throws Exception {
        String trueTestFile = "resources/us04/us04MarriageAfterDivorce.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
//        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us04 test = new us04();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US04(curFam);
        }
        assertTrue(errorContain(test.getError(),"US04") );
    }

    @Test
    public void TestUS04F() throws Exception {
        String falseTestFile = "resources/us04/us04MarriageBeforeDivorce.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
//        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us04 test = new us04();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US04(curFam);
        }
        assertFalse(errorContain(test.getError(),"US04") );
    }

    @Test
    public void TestUS05T() throws Exception {
        String trueTestFile = "resources/us05/us05MarriageAfterDeath.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us05 test = new us05();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US05(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US05") );
    }

    @Test
    public void TestUS05F() throws Exception {
        readGedcomFile read = new readGedcomFile();
        String falseTestFile = "resources/us05/us05MarriageBeforeDeath.ged";//relative path is not morking! using your path can run!
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us05 test = new us05();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US05(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US05") );
    }

    @Test
    public void TestUS06T() throws Exception {
        String trueTestFile = "resources/us06/us06DivorceAfterDeath.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us06 test = new us06();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US06(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US06") );
    }

    @Test
    public void TestUS06F() throws Exception {
        String falseTestFile = "resources/us06/us06DivorceBeforeDeath.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us06 test = new us06();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US06(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US06") );
    }

    @Test
    public void TestUS07T() throws Exception {
        String trueTestFile = "resources/us07/us07GreaterThan150years.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
//        Map _Fams = read.getFam();

        us07 test = new us07();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US07(curIndis);
        }
        assertTrue(errorContain(test.getError(),"US07") );
    }

    @Test
    public void TestUS07F() throws Exception {
        String falseTestFile = "resources/us07/us07LessThan150years.ged";//relative path is not morking! using your path can run!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
//        Map _Fams = read.getFam();

        us07 test = new us07();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US07(curIndis);
        }
        assertFalse(errorContain(test.getError(),"US07") );
    }

//    @Test
//    public void TestUS08T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US08") );
//    }
//
//    @Test
//    public void TestUS08F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US08") );
//    }
//
//    @Test
//    public void TestUS09T() throws Exception {
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US09") );
//    }
//
//    @Test
//    public void TestUS09F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US09") );
//    }
//
//    @Test
//    public void TestUS10T() throws Exception {
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.US10(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US10") );
//    }
//
//    @Test
//    public void TestUS10F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.US10(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US10") );
//    }
//
//    @Test
//    public void TestUS11T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US11") );
//    }
//
//    @Test
//    public void TestUS11F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US11") );
//    }
//
//    @Test
//    public void TestUS12T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US12") );
//    }
//
//    @Test
//    public void TestUS12F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US12") );
//    }
//
//    @Test
//    public void TestUS13T() throws Exception {
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.US10(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error: US13") );
//    }
//
//    @Test
//    public void TestUS13F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.US10(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error: US13") );
//    }
//
//    @Test
//    public void TestUS14T() throws Exception {
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.US10(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error: US14") );
//    }
//
//    @Test
//    public void TestUS14F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.US10(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error: US14") );
//    }
//
//    @Test
//    public void TestUS15T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US15") );
//    }
//
//    @Test
//    public void TestUS15F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US15") );
//    }
//
//    @Test
//    public void TestUS16T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"ERROR: FAMILY: US16: F2 male members don`t have same last name.") );
//    }
//
//    @Test
//    public void TestUS16F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"ERROR: FAMILY: US16: F2 male members don`t have same last name.") );
//    }
//
//    @Test
//    public void TestUS17T() throws Exception {
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US17") );
//    }
//
//    @Test
//    public void TestUS17F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(testUS17);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US17") );
//    }
//
//    @Test
//    public void TestUS18T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"Error US08") );
//    }
//
//    @Test
//    public void TestUS18F() throws Exception {
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"Error US08") );
//    }
//
//    @Test
//    public void TestUS19T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"ERROR: FAMILY: US19") );
//    }
//
//    @Test
//    public void TestUS19F() throws Exception {
////        System.out.println(url.getPath());
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"ERROR: FAMILY: US19") );
//    }
//
//    @Test
//    public void TestUS20T() throws Exception {
////        System.out.println(url.getPath());
// String trueTestFile = "resources/us03/us03BirthAfterDeath.ged";
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(trueTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertTrue(errorContain(test.getError(),"ERROR: FAMILY: US20: F1 : niece: I1 married uncle: I1") );
//    }
//
//    @Test
//    public void TestUS20F() throws Exception {
////        System.out.println(url.getPath());
// String falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";//relative path is not morking! using your path can run!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        userStories test = new userStories();
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        test.IterateFam(Fams,indis);
//        assertFalse(errorContain(test.getError(),"ERROR: FAMILY: US20: F1 : niece: I1 married uncle: I1") );
//    }

}
