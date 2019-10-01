/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */
package sit.ssw555.tools;

import sit.ssw555.objects.Family;
import sit.ssw555.objects.Individual;
import sit.ssw555.tools.CalculateAge.*;
import java.text.ParseException;
import java.util.*;

public class userStories {
    public void IterateFam(Map _Fams, Map _indis) {
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            //System.out.println(this.US05(curFam, _indis));
            //System.out.println(this.US06(curFam,_indis));
            System.out.println(this.US02(curFam, _indis));
        }
    }

    public void IterateInds(Map _Fam, Map _indis) {
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            System.out.println(this.US03(curIndis));
        }
    }


    // US07: Less then 150 years old(Zhe Sun)
    public String US07(Individual _indi) throws Exception {
        String errStr = "";
        if (_indi.getBirthday() != null && CalculateAge.getAge(_indi.getBirthday())<150) {
            errStr = "Error US07";
        }
        return errStr;

    }



    // US06: Divorce before death
    public String US06(Family _Fam, Map<String, Individual> _indis) {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getDeath() != null && wife.getDeath() != null && _Fam.getDivorced() != null && (_Fam.getDivorced().after(husband.getDeath()) || _Fam.getDivorced().after(husband.getDeath()))) {
            errStr = "Error US06";
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getDivorced() != null && _Fam.getDivorced().after(wife.getDeath())) {
            errStr = "Error US06";
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getDivorced() != null && _Fam.getDivorced().after(husband.getDeath())) {
            errStr = "Error US06";
        }
        return errStr;
    }

    // US05: Marriage before death(Tao Chai)
    public String US05(Family _Fam, Map<String, Individual> _indis) {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getDeath() != null && wife.getDeath()!= null && _Fam.getMarried() != null && (_Fam.getMarried().after(husband.getDeath()) || _Fam.getMarried().after(husband.getDeath()))) {
            errStr = "Error US05";
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getMarried().after(wife.getDeath())) {
            errStr = "Error US05";
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getMarried().after(husband.getDeath())) {
            errStr = "Error US05";
        }
        return errStr;
    }

    // US04: Marriage before divorce(Zhe Sun)
    public String US04(Family _Fam) {
        String errStr = "";
        if (_Fam.getMarried() != null && _Fam.getDivorced() != null && _Fam.getMarried().before(_Fam.getDivorced())){
            errStr = "Error US04";
        }
        return errStr;
    }

    // US03: Birth before death(Ge Chang)
    public String US03(Individual _indi) {
        String errStr = "";
        if (_indi.getDeath() != null && !_indi.getBirthday().equals(_indi.getDeath()) && _indi.getBirthday().after(_indi.getDeath())) {
            errStr = "Error US03";
        }
        return errStr;
    }

    // US02: Birth before marriage(Ge Chang)
    public String US02(Family _Fam, Map<String, Individual> _indis) {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (!(husband.getBirthday().before(_Fam.getMarried()) && wife.getBirthday().before(_Fam.getMarried()))) {
            errStr = "Error US02";
        }
        return errStr;
    }

    // US10: Marriage after 14(Jiaxian Xing)
    public String US10(Map<String, Family> _Fams, Map<String, Individual> _indis) {
        String errStr = "";
        Date today = new Date();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            if (curFam.getMarried() != null) {
                Individual husband = _indis.get(curFam.getHusbandID());
                Individual wife = _indis.get(curFam.getWifeID());
                Calendar cal = Calendar.getInstance();
                cal.setTime(curFam.getMarried());
                int yearNow = cal.get(Calendar.YEAR);//
                int monthNow = cal.get(Calendar.MONTH);  //
                int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //
                cal.setTime(husband.getBirthday());
                int yearBirth = cal.get(Calendar.YEAR);
                int monthBirth = cal.get(Calendar.MONTH);
                int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
                int ageMarried = yearNow - yearBirth;   //
                if (monthNow <= monthBirth) {
                    if (monthNow == monthBirth) {
                        if (dayOfMonthNow < dayOfMonthBirth) ageMarried--;//
                    } else {
                        ageMarried--;//
                    }
                }
                if (ageMarried < 14)
                    errStr = "Err us10 Fam:" + curFam.getId() + " husband: " + husband.getId() + husband.getName() + " ageMarried < 14";
                cal.setTime(wife.getBirthday());
                yearBirth = cal.get(Calendar.YEAR);
                monthBirth = cal.get(Calendar.MONTH);
                dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
                ageMarried = yearNow - yearBirth;   //
                if (monthNow <= monthBirth) {
                    if (monthNow == monthBirth) {
                        if (dayOfMonthNow < dayOfMonthBirth) ageMarried--;//
                    } else {
                        ageMarried--;//
                    }
                }
                if (ageMarried < 14)
                    errStr = "Err us10 Fam:" + curFam.getId() + " wife: " + wife.getId() + wife.getName() + " ageMarried < 14";
            }
        }
        return errStr;
    }

}
