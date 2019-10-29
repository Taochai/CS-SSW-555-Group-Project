/*
 * @Description:
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */

import java.util.Iterator;
import java.util.Map;

import objects.Family;
import objects.Individual;
import tools.*;

/**
 * main function
 */
public class main {
    public static void main(String[] args) throws Exception {
       String outputStr = "";
//        outputStr = readDemoFileUS01("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS02("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS03("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS04("src/main/resources/testOne.ged",outputStr);
//        outputStr = readDemoFileUS05("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS06("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS07("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS08("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS09("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS10("src/main/resources/TestGEDCOM.ged",outputStr);
//        outputStr = readDemoFileUS11("src/main/resources/testOne.ged",outputStr);
//        outputStr = readDemoFileUS12("src/main/resources/testOne.ged",outputStr);
//        outputStr = readDemoFileUS13("src/main/resources/testOne.ged",outputStr);
//        outputStr = readDemoFileUS14("src/main/resources/testOne.ged",outputStr);
//        outputStr = readDemoFileUS15("src/main/resources/testUS15.ged",outputStr);
//        outputStr = readDemoFileUS16("src/main/resources/testOne.ged",outputStr);
//        outputStr = readDemoFileUS17("src/main/resources/UserStory17.ged",outputStr);
//        outputStr = readDemoFileUS18("src/main/resources/US18.ged",outputStr);
//        outputStr = readDemoFileUS19("src/main/resources/US18.ged",outputStr);
//        outputStr = readDemoFileUS20("src/main/resources/testTwo.ged",outputStr);
//        outputStr = readDemoFileUS21("src/main/resources/testTwo.ged",outputStr);
//        outputStr = readDemoFileUS22("src/main/resources/test22.ged",outputStr);
        outputStr = readDemoFileUS25("src/main/resources/US25.ged",outputStr);
        outputStr = readDemoFileUS28("src/main/resources/US28.ged",outputStr);
        //outputStr = readDemoFileUS23("src/main/resources/test23True.ged",outputStr);

//        outputStr = readDemoFileUS27("src/main/resources/US27.ged",outputStr);
        outputTxt text = new outputTxt();
        text.log(outputStr);
        System.out.print(outputStr);
    }
//    public static String readTestFile(String _testFileName, String _errorStr) throws Exception {
//        readGedcomFile read = new readGedcomFile();
//        read.readFile(_testFileName);
//        Map indis = read.printIndi();
//        Map Fams = read.printFam();
//        userStories test = new userStories();
//        test.IterateFam(Fams,indis);
//        test.IterateInds(Fams,indis);
//
//        test.US01(Fams, indis);
//        test.US10(Fams, indis);
//        test.US11(Fams, indis);
////        String out = "";
//        for (String a : test.getError()) {
//            _errorStr +=  a+"\n";//this will getting slow when string goes long. Or you can use StringBuilder!
//        }
//        return _errorStr;
//    }
    public static String readDemoFileUS01(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map indis = read.getIndi();
        Map Fams = read.getFam();

        us01 test = new us01();
        test.US01(Fams, indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS02(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us02 test = new us02();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US02(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS03(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
//        Map _Fams = read.getFam();//don't need that

        us03 test = new us03();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US03(curIndis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS04(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
//        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us04 test = new us04();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US04(curFam);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS05(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us05 test = new us05();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US05(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS06(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us06 test = new us06();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US06(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS07(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
//        Map _Fams = read.getFam();

        us07 test = new us07();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US07(curIndis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS08(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();
        us08 test = new us08();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US08(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS09(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us09 test = new us09();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US09(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS10(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us10 test = new us10();
        test.US10(_Fams, _indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS11(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us11 test = new us11();
        test.US11(_Fams, _indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS12(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us12 test = new us12();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US12(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS13(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us13 test = new us13();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US13(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS14(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us14 test = new us14();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US14(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS15(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
//        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us15 test = new us15();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US15(curFam);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS16(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us16 test = new us16();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US16(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS17(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us17 test = new us17();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US17(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS18(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us18 test = new us18();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US18(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS19(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us19 test = new us19();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US19(curIndis,_Fams, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS20(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us20 test = new us20();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US20(curIndis,_Fams, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS21(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us21 test = new us21();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            test.US21(curFam, _indis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }
    public static String readDemoFileUS22(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();
//        read.printIndi();
//        read.printFam();
        us22 test = new us22();
        test.US22(_Fams,_indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }
    public static String readDemoFileUS23(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        read.printIndi();

        us23 test = new us23();
        test.US23(_indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS25(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us25 test = new us25();
        test.US25(_Fams, _indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }
//
    public static String readDemoFileUS27(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();

        us27 test = new us27();
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            test.US27(curIndis);
        }

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

    public static String readDemoFileUS28(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map _indis = read.getIndi();
        Map _Fams = read.getFam();

        us28 test = new us28();
        test.US28(_Fams, _indis);

        StringBuilder Str = new StringBuilder(_errorStr);
        for (String a : test.getError()) {
            Str.append(a+"\n");
        }
        return Str.toString();
    }

}
