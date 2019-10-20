/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/CS-SSW-555-Group-Project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:18:50
 */

import objects.Family;
import objects.Individual;
import org.junit.Test;
import tools.readGedcomFile;
import tools.UserStories;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Unit test for user stories.
 */
public class userStoriesTest {
//    String testFilePath = "G:\\Courses Info\\SSW 555 Agile Dev\\Sprint\\CS-SSW-555-Group-Project\\src\\main\\resources\\TestGEDCOM.ged";//relative path is not morking! using your path can run!
//    String testUS17 = "G:\\Courses Info\\SSW 555 Agile Dev\\Sprint\\CS-SSW-555-Group-Project\\src\\main\\resources\\UserStory17.ged";
    //    String testFilePath = "src\\test\\resources\\us01\\us01DatesAfterToday.ged";//relative path is not morking! using your path can run!
    private boolean win_System = true;

    private boolean errorContain(Set<String> errorSet, String errorInfo) {
        for (String s : errorSet) {
            if (s.contains(errorInfo))
                return true;
        }
        return false;
    }

    /**
     * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE *
     * ***************************************************
     * <p>
     * IF YOU ARE UNDER WINDOWS SYSTEM, THEN ASSIGN --> win_System -> true
     * or rather assign:  win_System -> false
     * Check it out in main function
     * <p>
     * ***************************************************
     * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE *
     */
//    private static boolean win_System = true;
    @Test
    public void TestUS01T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us01\\us01DatesAfterToday.ged";
        } else {
            trueTestFile = "resources/us01/us01DatesAfterToday.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams, indis);
        String expected = "US01";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS01F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us01\\us01DatesBeforeToday.ged";
        } else {
            falseTestFile = "resources/us01/us01DatesBeforeToday.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams, indis);
        String expected = "US01";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS02T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us02\\us02BirthAfterMarriage.ged";
        } else {
            trueTestFile = "resources/us02/us02BirthAfterMarriage.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US02(curFam, _indis);
        }
        String expected = "US02";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS02F() throws Exception {
        String falseTestFile;
        if (win_System){
            falseTestFile = "src\\test\\resources\\us02\\us02BirthBeforeMarriage.ged";
        } else {
            falseTestFile = "resources/us02/us02BirthBeforeMarriage.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US02(curFam, _indis);
        }
        String expected = "US02";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS03T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us03\\us03BirthAfterDeath.ged";
        } else {
            trueTestFile = "src/test/resources/us03/us03BirthAfterDeath.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US03(curIndis);
        }
        String expected = "US03";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS03F() throws Exception {
        String falseTestFile;
        if (win_System){
            falseTestFile = "src\\test\\resources\\us03\\us03BirthBeforeDeath.ged";
        } else {
            falseTestFile = "resources/us03/us03BirthBeforeDeath.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US03(curIndis);
        }
        String expected = "US03";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS04T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us04\\us04MarriageAfterDivorce.ged";
        } else {
            trueTestFile = "resources/us04/us04MarriageAfterDivorce.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams, indis);
        String expected = "US04";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS04F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us04\\us04MarriageBeforeDivorce.ged";
        } else {
            falseTestFile = "resources/us04/us04MarriageBeforeDivorce.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.IterateFam(Fams, indis);
        String expected = "US04";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }


    @Test
    public void TestUS05T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us05\\us05MarriageAfterDeath.ged";
        } else {
            trueTestFile = "resources/us01/us05MarriageAfterDeath.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US05(curFam, indis);
        }
        String expected = "US05";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS05F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us05\\us05MarriageBeforeDeath.ged";
        } else {
            falseTestFile = "resources/us05/us05MarriageBeforeDeath.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US05(curFam, indis);
        }
        String expected = "US05";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS06T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us06\\us06DivorceAfterDeath.ged";
        } else {
            trueTestFile = "resources/us06/us06DivorceAfterDeath.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US06(curFam, indis);
        }
        String expected = "US06";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS06F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us06\\us06DivorceBeforeDeath.ged";
        } else {
            falseTestFile = "resources/us06/us06DivorceBeforeDeath.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US06(curFam, indis);
        }
        String expected = "US06";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }


    @Test
    public void TestUS07T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us07\\us07GreaterThan150years.ged";
        } else {
            trueTestFile = "resources/us07/us07GreaterThan150years.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Individual>> entries1 = indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US07(curIndis);
        }
        String expected = "US07";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS07F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us07\\us07LessThan150years.ged";
        } else {
            falseTestFile = "resources/us07/us07LessThan150years.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Individual>> entries1 = indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US07(curIndis);
        }
        String expected = "US07";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS08T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us08\\us08BirthBeforeParentsMarriage.ged";
        } else {
            trueTestFile = "resources/us08/us08BirthBeforeParentsMarriage.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US08(curFam, indis);
        }
        String expected = "US08";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS08F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us08\\us08BirthAfterParentsMarriage.ged";
        } else {
            falseTestFile = "resources/us08/us08BirthAfterParentsMarriage.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US08(curFam, indis);
        }
        String expected = "US08";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS09T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us09\\us09BirthAfterParentsDeath.ged";
        } else {
            trueTestFile = "resources/us09/us09BirthAfterParentsDeath.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US09(curFam, indis);
        }
        String expected = "US09";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS09F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us09\\us09BirthBeforeParentsDeath.ged";
        } else {
            falseTestFile = "resources/us09/us09BirthBeforeParentsDeath.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US09(curFam, indis);
        }
        String expected = "US09";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS10T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us10\\us10MarriageBefore14years.ged";
        } else {
            trueTestFile = "resources/us10/us10MarriageBefore14years.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US10(Fams, indis);
        String expected = "US10";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS10F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us10\\us10MarriageAfter14years.ged";
        } else {
            falseTestFile = "resources/us10/us10MarriageAfter14years.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US10(Fams, indis);
        String expected = "US10";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS11T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us11\\us11Bigamy.ged";
        } else {
            trueTestFile = "resources/us11/us11Bigamy.ged";//relative path is not working on travis ci!!!
        }
//        System.out.println(url.getPath());
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US11(Fams, indis);
        String expected = "US11";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }


    @Test
    public void TestUS11F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us11\\us11NoBigamy.ged";
        } else {
            falseTestFile = "resources/us11/us11NoBigamy.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US11(Fams, indis);
        String expected = "US11";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS12T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us12\\us12ParentsTooOld.ged";
        } else {
            trueTestFile = "resources/us12/us12ParentsTooOld.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US12(curFam, indis);
        }
        String expected = "US12";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS12F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us12\\us12ParentsNotOld.ged";
        } else {
            falseTestFile = "resources/us12/us12ParentsNotOld.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US12(curFam, indis);
        }
        String expected = "US12";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS13T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us13\\us13SiblingBirthNotSeperate.ged";
        } else {
            trueTestFile = "resources/us13/us13SiblingBirthNotSeperate.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US13(curFam, indis);
        }
        String expected = "US13";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS13F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us13\\us13SiblingBirthSeperate.ged";
        } else {
            falseTestFile = "resources/us13/us13SiblingBirthSeperate.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US13(curFam, indis);
        }
        String expected = "US13";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS14T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us14\\us14FiveMoreSiblingSameDay.ged";
        } else {
            trueTestFile = "resources/us14/us14FiveMoreSiblingSameDay.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US14(curFam, indis);
        }
        String expected = "US14";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS14F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us14\\us14FiveLessSiblingSameDay.ged";
        } else {
            falseTestFile = "resources/us14/us14FiveLessSiblingSameDay.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US14(curFam, indis);
        }
        String expected = "US14";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS15T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us15\\us15MoreThan15Sibling.ged";
        } else {
            trueTestFile = "resources/us15/us15MoreThan15Sibling.ged";
        }
//        System.out.println(url.getPath());
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US15(curFam);
        }
        String expected = "US15";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS15F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us15\\us15LessThan15Sibling.ged";
        } else {
            falseTestFile = "resources/us15/us15LessThan15Sibling.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US15(curFam);
        }
        String expected = "US15";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS16T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us16\\us16FamilyNameDiff.ged";
        } else {
            trueTestFile = "resources/us16/us16FamilyNameDiff.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US16(curFam, _indis);
        }
        String expected = "US16";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS16F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us16\\us16FamilyNameSame.ged";
        } else {
            falseTestFile = "resources/us16/us16FamilyNameSame.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US16(curFam, _indis);
        }
        String expected = "US16";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS17T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us17\\us17MarryToChildren.ged";
        } else {
            trueTestFile = "resources/us17/us17MarryToChildren.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US17(curFam, indis);
        }
        String expected = "US17";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS17F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us17\\us17NoMarryToChildren.ged";
        } else {
            falseTestFile = "resources/us17/us17NoMarryToChildren.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US17(curFam, indis);
        }
        String expected = "US17";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS18T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us18\\us18SiblingMarryEachother.ged";
        } else {
            trueTestFile = "resources/us18/us18SiblingMarryEachother.ged";//relative path is not working on travis ci!!!
        }
//        System.out.println(url.getPath());
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US18(curFam, _indis);
        }
        String expected = "US18";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS18F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us18\\us18SiblingNotMarryEachother.ged";
        } else {
            falseTestFile = "resources/us18/us18SiblingNotMarryEachother.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map _indis = read.printIndi();
        Map _Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US18(curFam, _indis);
        }
        String expected = "US18";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS19T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us19\\us19CousinsMarry.ged";
        } else {
            trueTestFile = "resources/us19/us19CousinsMarry.ged";
        }
//        System.out.println(url.getPath());
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Individual>> entries1 = indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US19(curIndis,Fams, indis);
        }
        String expected = "US19";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS19F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us19\\us19NoCousinsMarry.ged";
        } else {
            falseTestFile = "resources/us19/us19NoCousinsMarry.ged";//relative path is not working on travis ci!!!
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Individual>> entries1 = indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US19(curIndis,Fams, indis);
        }
        String expected = "US19";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS20T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us20\\us20AuntsUnclesMarryNiecesNephews.ged";
        } else {
            trueTestFile = "resources/us20/us20AuntsUnclesMarryNiecesNephews.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US20(curFam,Fams, indis);
        }
        String expected = "US20";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS20F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\us20\\us20AuntsUnclesNotMarryNiecesNephews.ged";
        } else {
            falseTestFile = "resources/us20/us20AuntsUnclesNotMarryNiecesNephews.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(falseTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        Iterator<Map.Entry<String, Family>> entries1 = Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US20(curFam,Fams, indis);
        }
        String expected = "US20";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

}
