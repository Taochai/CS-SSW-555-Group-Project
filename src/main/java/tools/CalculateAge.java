/*
 * @Description: to calculate age
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */
package tools;

import objects.Individual;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//US27: Include individual ages(Yining Wen)
public class CalculateAge {
    public static void main(String[] args) {
        try {
//            int  age = getAge(parse("1960-09-27"), parse("2060-09-27"));
            int  age = getAge(parse("1960-09-27"));           //由出生日期获得年龄***
            System.out.println("age:"+age);

            Date d1 = parse("1960-09-27");
            Date d2 = parse("2061-08-27");

            int yearr = getAge(d1, d2);
            System.out.println(yearr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //change date(String) to date(Date)
    private static Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }
    //以上是把日期改成Date


    static int getAge(Date birthDay)  {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        //US27 changed, send the
        if(birthDay != null){
            cal.setTime(birthDay);
        }
        //
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            }else{
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    static int getAge(Date DOB, Date DOD)  {
        long bod = DOB.getTime();
        long dod = DOD.getTime();
        return (int) ((dod - bod) /1000/60/60/24/365);
    }

    // return true if the birthday/anniversary date input is in the coming XX days or rather return false
    static boolean isWithinXXDays(Date time, int days) {
        Calendar cal = Calendar.getInstance();
        Date calDate = cal.getTime();

        Map<Integer, Integer> monthAccordingDateMap = new HashMap<Integer, Integer>() {
            {
                put(1, 31);
                put(2, 28);
                put(3, 31);
                put(4, 30);
                put(5, 31);
                put(6, 30);
                put(7, 31);
                put(8, 31);
                put(9, 30);
                put(10, 31);
                put(11, 30);
                put(12, 31);
            }
        };
        // birthday should be before present date then we can do the calculation.
        if (calDate.after(time)) {
            int monthNow = cal.get(Calendar.MONTH) + 1;  //当前月份
            int dayOfMonthNow = cal.get(Calendar.DATE); //当前日期

            if (time != null) {
                cal.setTime(time);
            }
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DATE);

            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth && (dayOfMonthBirth - dayOfMonthNow) < days) return true;
            } else if (monthNow > monthBirth) {
                return false;
            } else {// monthBirth > monthNow
                if ((monthBirth - monthNow) <= 1 && ((monthAccordingDateMap.get(monthNow) - dayOfMonthNow) + dayOfMonthBirth) < days) {
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }
        return false;
    }

    static int getAgeByDate(Date DOB, Date givenDate)  {
        Calendar cal = Calendar.getInstance();

        //get the given date information
        cal.setTime(givenDate);
        int yearGivenDate = cal.get(Calendar.YEAR);
        int monthGivenDate = cal.get(Calendar.MONTH);
        int dayOfMonthGivenDate = cal.get(Calendar.DAY_OF_MONTH);

        //get the birthday date information
        cal.setTime(DOB);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        //calculate the age base on the given date.
        int ageByDate = yearGivenDate - yearBirth;
        if (monthGivenDate <= monthBirth) {
            if (monthGivenDate == monthBirth) {
                if (dayOfMonthGivenDate < dayOfMonthBirth) ageByDate--;
            } else {
                ageByDate--;
            }
        }
        return ageByDate;
    }
}
