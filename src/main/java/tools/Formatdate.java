/*
 * @Description:
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/CS-SSW-555-Group-Project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:13:18
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatdate {
    static Date stringtodate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date1 = dateFormat.parse(date);
        return date1;
    }

    static String dateToString(Date date) throws ParseException {
        if (date == null) {
            return "NA";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = dateFormat.format(date);
        return date1;
    }
}
