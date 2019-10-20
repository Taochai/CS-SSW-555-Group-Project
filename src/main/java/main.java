/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/CS-SSW-555-Group-Project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:12:02
 */
import java.util.Map;

import tools.ExportErrInfoToTxtFile;
import tools.UserStories;
import tools.readGedcomFile;

public class main {


    /**
     *  NOTE * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE *
     *  ***************************************************
     *
     *  IF YOU ARE UNDER WINDOWS SYSTEM, THEN ASSIGN --> win_System -> true
     *  or rather assign:  win_System -> false
     *
     *  ***************************************************
     *   NOTE * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE *
     */
    private static boolean win_System = true;

    public static void main(String[] args) throws Exception {
        readGedcomFile read = new readGedcomFile();
        // C:\Users\jason\Documents\Git\SSW-CS-555-Group-project\CS555\My-Family-17-Sep-2019-579.ged
        // G:\Courses Info\SSW 555 Agile Dev\Sprint1\SSW-CS-555-Group-project\CS555\My-Family-17-Sep-2019-579.ged
//        read.readFile("src\\main\\resources\\My-Family-17-Sep-2019-579.ged");
//        read.readFile("src\\main\\resources\\TestFamilyTreeWithmanyissues.ged");
        read.readFile("src\\test\\resources\\us20\\testyi.ged");
//        read.readFile("src\\main\\resources\\TestGEDCOM.ged");
//        read.readFile("src\\main\\resources\\testTwo.ged");
//        read.readFile("src\\main\\resources\\testOne.ged");
//        read.readFile("src\\main\\resources\\testUs1314.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/TestFamilyTreeWithmanyissues.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/TestGEDCOM.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/UserStory17.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/testOne.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/US18.ged");
//        read.readFile(("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/testTwo.ged"));

        Map indis = read.printIndi();
        Map Fams = read.printFam();

        UserStories test = new UserStories();
        test.IterateFam(Fams,indis);
        test.IterateInds(Fams,indis);

        test.printErrorInfo();

        // export all the error message to an "ErrorInfo.txt" file at the root of the project
        ExportErrInfoToTxtFile output = new ExportErrInfoToTxtFile();
        output.exportFile(win_System, test.getErrorInfo());
    }
}
