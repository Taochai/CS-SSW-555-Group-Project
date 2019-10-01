/*
 * @Description:
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */
package sit.ssw555;

import java.util.Map;
import sit.ssw555.tools.*;


/**
 * main function
 */
public class main {
    public static void main(String[] args) throws Exception {
        readGedcomFile read = new readGedcomFile();
        // C:\Users\jason\Documents\Git\SSW-CS-555-Group-project\CS555\My-Family-17-Sep-2019-579.ged
        // G:\Courses Info\SSW 555 Agile Dev\Sprint1\SSW-CS-555-Group-project\CS555\My-Family-17-Sep-2019-579.ged
        read.readFile("D:\\github\\CS-SSW-555-Group-Project-master\\src\\main\\resources\\TestFamilyTreeWithmanyissues");
//        read.readFile("/Users/michaelwen/Documents/555/homework/CS-SSW-555-Group-Project/src/main/resources/TestFamilyTreeWithmanyissues" );
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        userStories test = new userStories();
//        test.AllUserStory(indis,Fams);
        test.IterateFam(Fams,indis);
        test.IterateInds(Fams,indis);
        test.US01(Fams,indis);
        test.US10(Fams,indis);

//        System.out.println(test.getError().size());
//        System.out.println(test.US01(Fams,indis));
        //TODO sth wrong over here: ^
    }
}
