/*
 * @Description:
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import tools.readGedcomFile;
import tools.userStories;
import tools.outputTxt;

/**
 * main function
 */
public class main {
    public static void main(String[] args) throws Exception {
       String outputStr = "";
        outputStr = readTestFile("src\\main\\resources\\TestFamilyTreeWithmanyissues.ged",outputStr);
        outputStr = readTestFile("src\\main\\resources\\testGEDCOM.ged",outputStr);
        outputStr = readTestFile("src\\main\\resources\\testOne.ged",outputStr);
       outputStr = readTestFile("src\\main\\resources\\testUS15.ged",outputStr);
        outputStr = readTestFile("src\\main\\resources\\testTwo.ged",outputStr);
        outputStr = readTestFile("src\\main\\resources\\US18.ged",outputStr);
        outputStr = readTestFile("src\\main\\resources\\UserStory17.ged",outputStr);
        outputTxt text = new outputTxt();
        text.log(outputStr);
    }
    public static String readTestFile(String _testFileName, String _errorStr) throws Exception {
        readGedcomFile read = new readGedcomFile();
        read.readFile(_testFileName);
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        userStories test = new userStories();
        test.IterateFam(Fams,indis);
        test.IterateInds(Fams,indis);

        test.US01(Fams, indis);
        test.US10(Fams, indis);
        test.US11(Fams, indis);
//        String out = "";
        for (String a : test.getError()) {
            _errorStr +=  a+"\n";
        }
        return _errorStr;
    }
}
