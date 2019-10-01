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
        read.readFile("src\\main\\resources\\My-Family-17-Sep-2019-579.ged");
        Map indis = read.printIndi();
        Map Fams = read.printFam();
        userStories test = new userStories();
        test.IterateFam(Fams,indis);
        test.IterateInds(Fams,indis);
//        System.out.println(test.US01(Fams,indis));
        //TODO sth wrong over here: ^
        System.out.println(test.US10(Fams,indis));
    }
}
