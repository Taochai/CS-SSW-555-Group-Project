import objects.Family;
import objects.Individual;
import org.junit.Test;
import tools.*;

import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for user stories.
 */
public class userStoriesTest
{
    final static boolean cloud_test = true;//if you are testing locally change it to false, for Travis change it to true.

    private boolean errorContain(Set<String> errorSet, String errorInfo){
        for (String s : errorSet) {
            if(s.contains(errorInfo))
                return true;
        }
        return false;
    }

    private String WhereTest(){
        String Localtest = "";
        String Travistest = "src/test/";
        if(cloud_test){
            return Travistest;
        }
        else{
            return Localtest;
        }
    }

    @Test
    public void TestUS01T() throws Exception {
        String trueTestFile = WhereTest() + "resources/us01/us01DatesAfterToday.ged";//relative path is not working on travis ci!!!
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
        String falseTestFile =  WhereTest() +"resources/us01/us01DatesBeforeToday.ged";//relative path is not working on travis ci!!!
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
        String trueTestFile =  WhereTest() +"resources/us02/us02BirthAfterMarriage.ged";
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
        String falseTestFile =  WhereTest() +"resources/us02/us02BirthBeforeMarriage.ged";//relative path is not working on travis ci!!!
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
        String trueTestFile =  WhereTest() +"resources/us03/us03BirthAfterDeath.ged";
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
        String falseTestFile =  WhereTest() +"resources/us03/us03BirthBeforeDeath.ged";//relative path is not working on travis ci!!!
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
        String trueTestFile =  WhereTest() +"resources/us04/us04MarriageAfterDivorce.ged";
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
        String falseTestFile =  WhereTest() +"resources/us04/us04MarriageBeforeDivorce.ged";//relative path is not working on travis ci!!!
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
        String trueTestFile =  WhereTest() +"resources/us05/us05MarriageAfterDeath.ged";
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
        String falseTestFile =  WhereTest() +"resources/us05/us05MarriageBeforeDeath.ged";//relative path is not working on travis ci!!!
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
        String trueTestFile =  WhereTest() +"resources/us06/us06DivorceAfterDeath.ged";
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
        String falseTestFile =  WhereTest() +"resources/us06/us06DivorceBeforeDeath.ged";//relative path is not working on travis ci!!!
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
        String trueTestFile =  WhereTest() +"resources/us07/us07GreaterThan150years.ged";
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
        String falseTestFile =  WhereTest() +"resources/us07/us07LessThan150years.ged";//relative path is not working on travis ci!!!
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

    @Test
    public void TestUS08T() throws Exception {
//        System.out.println(url.getPath());
        String trueTestFile =  WhereTest() +"resources/us08/us08BirthBeforeParentsMarriage.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us08 test = new us08();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US08(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US08") );
    }

    @Test
    public void TestUS08F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us08/us08BirthAfterParentsMarriage.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us08 test = new us08();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US08(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US08") );
    }

    @Test
    public void TestUS09T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us09/us09BirthAfterParentsDeath.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us09 test = new us09();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US09(curFam, _indis);
        }

        assertTrue(errorContain(test.getError(),"US09") );
    }

    @Test
    public void TestUS09F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us09/us09BirthBeforeParentsDeath.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us09 test = new us09();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US09(curFam, _indis);
        }

        assertFalse(errorContain(test.getError(),"US09") );
    }

    @Test
    public void TestUS10T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us10/us10MarriageBefore14years.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us10 test = new us10();
        test.US10(_Fams, _indis);
        assertTrue(errorContain(test.getError(),"US10") );
    }

    @Test
    public void TestUS10F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us10/us10MarriageAfter14years.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us10 test = new us10();
        test.US10(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US10") );
    }

    @Test
    public void TestUS11T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us11/us11Bigamy.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us11 test = new us11();
        test.US11(_Fams, _indis);
        assertTrue(errorContain(test.getError(),"US11") );
    }

    @Test
    public void TestUS11F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us11/us11NoBigamy.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us11 test = new us11();
        test.US11(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US11") );
    }

    @Test
    public void TestUS12T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us12/us12ParentsTooOld.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us12 test = new us12();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US12(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US12") );
    }

    @Test
    public void TestUS12F() throws Exception {
        String falseTestFile = WhereTest() + "resources/us12/us12ParentsNotOld.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us12 test = new us12();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US12(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US12") );
    }

    @Test
    public void TestUS13T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us13/us13SiblingBirthNotSeperate.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us13 test = new us13();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US13(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US13") );
    }

    @Test
    public void TestUS13F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us13/us13SiblingBirthSeperate.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us13 test = new us13();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US13(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US13") );
    }

    @Test
    public void TestUS14T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us14/us14FiveMoreSiblingSameDay.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us14 test = new us14();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US14(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US14") );
    }

    @Test
    public void TestUS14F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us14/us14FiveLessSiblingSameDay.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us14 test = new us14();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US14(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US14") );
    }

    @Test
    public void TestUS15T() throws Exception {
//        System.out.println(url.getPath());
        String trueTestFile =  WhereTest() +"resources/us15/us15MoreThan15Sibling.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
//        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us15 test = new us15();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US15(curFam);
        }
        assertTrue(errorContain(test.getError(),"US15") );
    }

    @Test
    public void TestUS15F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us15/us15LessThan15Sibling.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
//        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us15 test = new us15();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US15(curFam);
        }
        assertFalse(errorContain(test.getError(),"US15") );
    }

    @Test
    public void TestUS16T() throws Exception {
//        System.out.println(url.getPath());
        String trueTestFile =  WhereTest() +"resources/us16/us16FamilyNameDiff.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us16 test = new us16();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US16(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US16") );
    }

    @Test
    public void TestUS16F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us16/us16FamilyNameSame.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us16 test = new us16();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US16(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US16") );
    }

    @Test
    public void TestUS17T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us17/us17MarryToChildren.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us17 test = new us17();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US17(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US17") );
    }

    @Test
    public void TestUS17F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us17/us17NoMarryToChildren.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us17 test = new us17();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US17(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US17") );
    }

    @Test
    public void TestUS18T() throws Exception {
//        System.out.println(url.getPath());
        String trueTestFile =  WhereTest() +"resources/us18/us18SiblingMarryEachother.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us18 test = new us18();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US18(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US18") );
    }

    @Test
    public void TestUS18F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us18/us18SiblingNotMarryEachother.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us18 test = new us18();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US18(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US18") );
    }

    @Test
    public void TestUS19T() throws Exception {
//        System.out.println(url.getPath());
        String trueTestFile =  WhereTest() +"resources/us19/us19CousinsMarry.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us19 test = new us19();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US19(curIndis,_Fams, _indis);
        }
        assertTrue(errorContain(test.getError(),"US19") );
    }

    @Test
    public void TestUS19F() throws Exception {
//        System.out.println(url.getPath());
        String falseTestFile =  WhereTest() +"resources/us19/us19NoCousinsMarry.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us19 test = new us19();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US19(curIndis,_Fams, _indis);
        }
        assertFalse(errorContain(test.getError(),"US19") );
    }

    @Test
public void TestUS20T() throws Exception {
//        System.out.println(url.getPath());
    String trueTestFile =  WhereTest() +"resources/us20/us20AuntsUnclesMarryNiecesNephews.ged";
    readGedcomFile read = new readGedcomFile();
    read.readFile(trueTestFile);
    Map _indis = read.getIndi();
    Map _Fams = read.getFam();

    us20 test = new us20();
    Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
    while (entries1.hasNext()) {
        Map.Entry<String, Individual> entry = entries1.next();
        Individual curIndis = entry.getValue();
        test.US20(curIndis,_Fams, _indis);
    }
    assertTrue(errorContain(test.getError(),"US20") );
}

//    @Test
//    public void TestUS20F() throws Exception {
////        System.out.println(url.getPath());
//        String falseTestFile = "src/test/resources/us20/us20AuntsUnclesNotMarryNiecesNephews.ged";//relative path is not working on travis ci!!!
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(falseTestFile);
//        Map _indis = read.getIndi();
//        Map _Fams = read.getFam();
//
//        us20 test = new us20();
//        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
//        while (entries1.hasNext()) {
//            Map.Entry<String, Individual> entry = entries1.next();
//            Individual curIndis = entry.getValue();
//            test.US20(curIndis,_Fams, _indis);
//        }
//        assertFalse(errorContain(test.getError(),"US20") );
//    }

    @Test
    public void TestUS21T() throws Exception {
//        System.out.println(url.getPath());
        String trueTestFile =  WhereTest() +"resources/us21/us21Uncorrectgenderforrole.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us21 test = new us21();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US21(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"US21") );
    }

    @Test
    public void TestUS21F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us21/us21Correctgenderforrole.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us21 test = new us21();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US21(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"US21") );
    }

    @Test
    public void TestUS22T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us22/Test22.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();
        us22 test = new us22();
        test.US22(_Fams,_indis);

        assertTrue(errorContain(test.getError(),"US22") );
    }

    @Test
    public void TestUS22F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us22/Test22True.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();
        us22 test = new us22();
        test.US22(_Fams,_indis);

        assertFalse(errorContain(test.getError(),"US22") );
    }

    @Test
    public void TestUS23T() throws Exception {
        String trueTestFile = WhereTest() + "resources/us23/Test23.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        us23 test = new us23();
        test.US23(_indis);

        assertTrue(errorContain(test.getError(),"US23") );
    }

    @Test
    public void TestUS23F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us23/Test23True.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();

        us23 test = new us23();
        test.US23(_indis);

        assertFalse(errorContain(test.getError(),"US23") );
    }

    @Test
    public void TestUS24T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us24/us24sameNameDateMarriage.ged";//relative path is not working on travis ci!!! need to change the idea configer
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us24 test = new us24();
        test.US24(_Fams,_indis);

        assertTrue(errorContain(test.getError(),"US24") );
    }

    @Test
    public void TestUS24F() throws Exception {
        String falseTestFile =  WhereTest() +"resources/us24/us24normal.ged";//relative path is not working on travis ci!!!need to change the idea configer
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us24 test = new us24();
        test.US24(_Fams,_indis);

        assertFalse(errorContain(test.getError(),"US24") );
    }

    @Test
    public void TestUS25T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us25/NotUnique.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us25 test = new us25();
        test.US25(_Fams, _indis);
        assertTrue(errorContain(test.getError(),"US25") );
    }

    @Test
    public void TestUS25F() throws Exception {
        String falseTestFile = WhereTest() + "resources/us25/UniqueName.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us25 test = new us25();
        test.US25(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US25") );
    }

    @Test
    public void TestUS26T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us26/us26notCorresponding.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us26 test = new us26();
        test.US26(_Fams, _indis);

        assertTrue(errorContain(test.getError(),"US26") );
    }

    @Test
    public void TestUS26F() throws Exception {
        String falseTestFile =  WhereTest() + "resources/us26/us26normal.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us26 test = new us26();
        test.US26(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US26") );
    }

    @Test
    public void TestUS27T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us27/us27Can'tCalculateAge.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
//        Map _Fams = read.printFam();
        us27 test = new us27();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US27(curIndis);
        }
        assertTrue(errorContain(test.getError(),"US27") );
    }

    @Test
    public void TestUS27F() throws Exception {
        String falseTestFile = WhereTest() +"resources/us27/us27CalculateAge.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
//        Map _Fams = read.printFam();
        us27 test = new us27();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US27(curIndis);
        }
        assertFalse(errorContain(test.getError(),"US27") );
    }

    @Test
    public void TestUS28T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us28/OrderSiblings.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us28 test = new us28();
        test.US28(_Fams, _indis);
        assertTrue(errorContain(test.getError(),"US28") );
    }

    @Test
    public void TestUS28F() throws Exception {
        String falseTestFile = WhereTest() + "resources/us28/CannotOrder.ged";//relative path is not working on travis ci!!!
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us28 test = new us28();
        test.US28(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US28") );
    }

    @Test
    public void TestUS29T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us29/ControlGroup.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us29 test = new us29();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US29(curIndis);
        }
        assertTrue(errorContain(test.getError(),"I40") );
    }



    @Test
    public void TestUS30T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us30/ControlGroup.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us30 test = new us30();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US30(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"I13") );
    }

    @Test
    public void TestUS31T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us31/US31.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us31 test = new us31();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US31(curIndis);
        }
        assertTrue(errorContain(test.getError(),"I3") );
    }

    @Test
    public void TestUS31F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us31/US31.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us31 test = new us31();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US31(curIndis);
        }
        assertFalse(errorContain(test.getError(),"I4") );
    }

    @Test
    public void TestUS32T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us32/US32.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us32 test = new us32();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US32(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"F1") );
    }

    @Test
    public void TestUS32F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us32/US32.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us32 test = new us32();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US32(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"F0") );
    }

    @Test
    public void TestUS33T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us33/US33.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us33 test = new us33();
        test.US33(_Fams,_indis);


        assertTrue(errorContain(test.getError(),"US33") );
    }

    @Test
    public void TestUS33F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us33/US33.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us33 test = new us33();
        test.US33(_Fams,_indis);
        assertTrue(errorContain(test.getError(),"US33") );
    }

    @Test
    public void TestUS34T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us34/us34hubansTwoTimesOldWhenMarry.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us34 test = new us34();
        test.US34(_Fams, _indis);
        assertTrue(errorContain(test.getError(),"US34") );
    }

    @Test
    public void TestUS34F() throws Exception {
        String falseTestFile = WhereTest() + "resources/us34/us34MarryAgeSimalar.ged";//relative path is not working on travis ci!!! (added the whereTEst! Work Now)
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us34 test = new us34();
        test.US34(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US34") );
    }

    @Test
    public void TestUS35T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us35/us35bornWithin30Day.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us35 test = new us35();
        test.US35(_Fams, _indis);

        assertTrue(errorContain(test.getError(),"US35") );
    }

    @Test
    public void TestUS35F() throws Exception {
        String falseTestFile =  WhereTest() + "resources/us35/us35noOneBornLast30Day.ged";//relative path is not working on travis ci!!!(added the whereTEst! Work Now)
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us35 test = new us35();
        test.US35(_Fams, _indis);
        assertFalse(errorContain(test.getError(),"US35") );
    }

    @Test
    public void TestUS36T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us36/DiedLast30Days.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us36 test = new us36();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US36(curIndis);
        }
        assertTrue(errorContain(test.getError(),"I12") );
    }

    @Test
    public void TestUS36F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us36/DiedLast30Days.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us36 test = new us36();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US36(curIndis);
        }
        assertFalse(errorContain(test.getError(),"I5") );
    }

    @Test
    public void TestUS37T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us37/ListSurvive.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us37 test = new us37();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US37(curFam, _indis);
        }
        assertTrue(errorContain(test.getError(),"Child I15 is survivor") );
    }

    @Test
    public void TestUS37F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us37/ListSurvive.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us37 test = new us37();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US37(curFam, _indis);
        }
        assertFalse(errorContain(test.getError(),"Wife I10 is survivor") );
    }

    @Test
    public void TestUS38T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us38/ControlGroup.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();

        us38 test = new us38();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US38(curIndis);
        }
        assertTrue(errorContain(test.getError(),"I20") );
    }

    @Test
    public void TestUS38F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us38/ControlGroupF.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();

        us38 test = new us38();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US38(curIndis);
        }
        assertFalse(errorContain(test.getError(),"I20") );
    }

    @Test
    public void TestUS39T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us39/ControlGroup.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us39 test = new us39();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US39(curFam);
        }
        assertTrue(errorContain(test.getError(),"F8") );
    }

    @Test
    public void TestUS39F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us39/ControlGroupF.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();

        us39 test = new us39();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US39(curFam);
        }
        assertFalse(errorContain(test.getError(),"F8") );
    }

    @Test
    public void TestUS42T() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us42/US42.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Set<String> errDateString = read.getErrDate();
        us42 test = new us42();
        test.US42(errDateString);

        assertTrue(errorContain(test.getError(),"US42") );
    }

    @Test
    public void TestUS42F() throws Exception {
        String trueTestFile =  WhereTest() +"resources/us42/US42F.ged";
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        Set<String> errDateString = read.getErrDate();
        us42 test = new us42();
        test.US42(errDateString);

        assertFalse(errorContain(test.getError(),"US42") );
    }
}
