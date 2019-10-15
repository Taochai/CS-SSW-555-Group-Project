/*
 * @Description:
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */

import java.util.Map;
import tools.readGedcomFile;
import tools.userStories;


/**
 * main function
 */
public class main {
    public static void main(String[] args) throws Exception {
        readGedcomFile read = new readGedcomFile();
        // C:\Users\jason\Documents\Git\SSW-CS-555-Group-project\CS555\My-Family-17-Sep-2019-579.ged
        // G:\Courses Info\SSW 555 Agile Dev\Sprint1\SSW-CS-555-Group-project\CS555\My-Family-17-Sep-2019-579.ged

//        read.readFile("src\\main\\resources\\My-Family-17-Sep-2019-579.ged");
//        read.readFile("src\\main\\resources\\TestFamilyTreeWithmanyissues.ged");
//        read.readFile("src\\main\\resources\\TestGEDCOM.ged");
//        read.readFile("src\\main\\resources\\testOne.ged");
//        read.readFile("src\\main\\resources\\testOne.ged");
//        read.readFile("src\\main\\resources\\testUs1314.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/UserStory17.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/My-Family-17-Sep-2019-579.ged");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/testOne.ged");
        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/US18.ged");
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        userStories test = new userStories();
        test.IterateFam(Fams,indis);
        test.IterateInds(Fams,indis);
        for (String a : test.getError()) {
            System.out.println(a);
        }
        System.out.println(test.US01(Fams, indis));
        System.out.println(test.US10(Fams, indis));
        System.out.println(test.US11(Fams, indis));
    }
}
