/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/CS-SSW-555-Group-Project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:12:02
 */

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import tools.ExportErrInfoToTxtFile;
import tools.UserStories;
import tools.readGedcomFile;

public class main {


    /**
     * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE *
     * ***************************************************
     * <p>
     * IF YOU ARE UNDER WINDOWS SYSTEM, THEN ASSIGN:  win_System -> true
     * or rather assign:  win_System -> false
     * <p>
     * ***************************************************
     * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE * NOTE *
     */
    private static boolean win_System = true;

    // created to print out the elements in the set and skip empty string
    private static void printSet(Set<String> s) {
        for (String error:s) {
            if (error.equals("")) continue;
            System.out.println(error);
        }
    }

    public static void main(String[] args) throws Exception {
        readGedcomFile read = new readGedcomFile();

        // initialize a set to store all the error info got from each test file
        Set<String> result = new TreeSet<>();
        result.clear();

        // if needed add more test file paths to this string array so as to output more error info messages
        String[] winFilePaths = {
                "src\\main\\resources\\us19.ged",
        };
        String[] linuxfFilePaths = {
                "src/main/resources/us11.ged",
        };

        // examine every test file specified in the winFilePaths array or linuxfFilePaths array
        for (String path : (win_System) ? winFilePaths : linuxfFilePaths) {
            System.out.println("read this file root: ----> " + path);
            // read file
            read.readFile(path);

            Map indis = read.printIndi();
            Map Fams = read.printFam();

            UserStories test = new UserStories();
            test.IterateFam(Fams, indis);
            test.IterateInds(Fams, indis);

            // add error messages to the final result set collection
            result.addAll(test.getErrorInfo());
        }

        // print all the error messages
        printSet(result);

        // export all the error message to an "ErrorInfo.txt" file at the root of the project
        ExportErrInfoToTxtFile output = new ExportErrInfoToTxtFile();
        output.exportFile(win_System, result);
    }
}
