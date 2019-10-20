/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/CS-SSW-555-Group-Project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:13:28
 */
package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ExportErrInfoToTxtFile {

    /**
     *
     * @param win_System if the user's OS is Windows then it is true, or rather false
     *                   used to assign different root to create the txt file
     * @param errors the error information which is a set
     * @throws IOException
     */
    public static void exportFile(boolean win_System, Set<String> errors) throws IOException {

        // create a file named "ErrorInfo.txt"
        File fileGenerator = null;
        FileWriter fw = null;

        //
        if (win_System) {
            fileGenerator = new File(".\\", "ErrorInfo.txt");
            fw = new FileWriter(".\\ErrorInfo.txt");
        } else {
            fileGenerator = new File("./", "ErrorInfo.txt");
            fw = new FileWriter("./ErrorInfo.txt");
        }
        try {
            // if the file not there, then we create it
            // if not we overwrite it
            if (!fileGenerator.isFile()) {
                fileGenerator.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write the error messages to the file
        BufferedWriter writer = new BufferedWriter(fw);
        for (String l : errors) {
            if (l.equals(""))
                continue;
            ;
            writer.write(l + "\r\n");
        }
        writer.close();
    }
}
