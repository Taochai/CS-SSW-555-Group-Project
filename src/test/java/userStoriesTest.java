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
import tools.readGedcomFile;
import tools.UserStories;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Test;


public class userStoriesTest {

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
    private boolean win_System = true;

    // function to check whether target error info in the generated errorInfo set(-By Jiaxian Xing) Great job! :)
    private boolean errorContain(Set<String> errorSet, String errorInfo) {
        for (String s : errorSet) {
            if (s.contains(errorInfo))
                return true;
        }
        return false;
    }

    @Test
    public void TestUS01T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us01.ged";
        } else {
            trueTestFile = "src/test/resources/us01.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us02.ged";
        } else {
            trueTestFile = "src/test/resources/us02.ged";
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
        if (win_System) {
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us03.ged";
        } else {
            trueTestFile = "src/test/resources/us03.ged";
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
        if (win_System) {
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us04.ged";
        } else {
            trueTestFile = "src/test/resources/us04.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us05.ged";
        } else {
            trueTestFile = "src/test/resources/us05.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us06.ged";
        } else {
            trueTestFile = "src/test/resources/us06.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us07.ged";
        } else {
            trueTestFile = "src/test/resources/us07.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us08.ged";
        } else {
            trueTestFile = "src/test/resources/us08.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us09.ged";
        } else {
            trueTestFile = "src/test/resources/us09.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us10.ged";
        } else {
            trueTestFile = "src/test/resources/us10.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us11.ged";
        } else {
            trueTestFile = "src/test/resources/us11.ged";
        }
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us12.ged";
        } else {
            trueTestFile = "src/test/resources/us12.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us13.ged";
        } else {
            trueTestFile = "src/test/resources/us13.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us14.ged";
        } else {
            trueTestFile = "src/test/resources/us14.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us15.ged";
        } else {
            trueTestFile = "src/test/resources/us15.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us16.ged";
        } else {
            trueTestFile = "src/test/resources/us16.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us17.ged";
        } else {
            trueTestFile = "src/test/resources/us17.ged";
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
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us18.ged";
        } else {
            trueTestFile = "src/test/resources/us18.ged";
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
            test.US18(curFam, _indis);
        }
        String expected = "US18";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS18F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            trueTestFile = "src\\test\\resources\\us19.ged";
        } else {
            trueTestFile = "src/test/resources/us19.ged";
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
            test.US19(curFam, Fams, indis);
        }
        String expected = "US19";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS19F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            test.US19(curFam, Fams, indis);
        }
        String expected = "US19";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS20T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us20.ged";
        } else {
            trueTestFile = "src/test/resources/us20.ged";
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
            test.US20(curFam, Fams, indis);
        }
        String expected = "US20";
        assertTrue(errorContain(test.getErrorInfo(), expected));
    }

    @Test
    public void TestUS20F() throws Exception {
        String falseTestFile;
        if (win_System) {
            falseTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            falseTestFile = "src/test/resources/ControlGroup.ged";
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
            test.US20(curFam, Fams, indis);
        }
        String expected = "US20";
        assertFalse(errorContain(test.getErrorInfo(), expected));
    }


    @Test
    public void TestUS29T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us29.ged";
        } else {
            trueTestFile = "src/test/resources/us01.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams, indis);
        String expected = "";
        assertTrue(errorContain(test.getAllDeceasedIndi(), expected));
    }

    @Test
    public void TestUS29F() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            trueTestFile = "src/test/resources/ControlGroup.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams, indis);
        String expected = "";
        assertTrue(errorContain(test.getAllDeceasedIndi(), expected));
    }


    @Test
    public void TestUS30T() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\us30.ged";
        } else {
            trueTestFile = "src/test/resources/us01.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams, indis);
        String expected = "";
        assertTrue(errorContain(test.getAllMarriedLivingInfo(), expected));
    }

    @Test
    public void TestUS30F() throws Exception {
        String trueTestFile;
        if (win_System) {
            trueTestFile = "src\\test\\resources\\ControlGroup.ged";
        } else {
            trueTestFile = "src/test/resources/ControlGroup.ged";
        }
        readGedcomFile read = new readGedcomFile();
        read.readFile(trueTestFile);
        UserStories test = new UserStories();
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        test.US01(Fams, indis);
        String expected = "";
        assertTrue(errorContain(test.getAllMarriedLivingInfo(), expected));
    }

}
