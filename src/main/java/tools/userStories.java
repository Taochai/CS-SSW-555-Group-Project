/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */
package tools;

import objects.Family;
import objects.Individual;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.*;

public class userStories {
    private Set<String> ErrorInfo;
    public userStories(){
        ErrorInfo = new HashSet<>();
    }
    public void AllUserStory(Map _Fams, Map _indis) throws ParseException {
//        this.ErrorInfo = new HashSet<>();
        this.IterateFam(_Fams,_indis);
        this.IterateInds(_Fams,_indis);
//        this.US10(_Fams,_indis);

    }
    public Set<String> getError(){
        return this.ErrorInfo;
    }

    public void IterateFam(Map _Fams, Map _indis) throws ParseException {
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            this.US05(curFam, _indis);
            this.US06(curFam,_indis);
            this.US04(curFam);
            this.US02(curFam,_indis);
            this.US08(curFam,_indis);
            this.US09(curFam,_indis);
            this.US12(curFam, _indis);
        }
    }

    public void IterateInds(Map _Fam, Map _indis) throws ParseException {
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            this.US03(curIndis);
            this.US07(curIndis);
        }
    }

    // US12: Parents not too old(Ge Chang)
    public String US12(Family _Fam, Map<String,Individual> _indis) {
        String errStr = "";
        Calendar calendar = Calendar.getInstance();
        Individual husband = _indis.get(_Fam.getHusbandID());
        calendar.setTime(husband.getBirthday());
        calendar.add(Calendar.YEAR, 80);
        Date fatherLast = calendar.getTime();

        Individual wife = _indis.get(_Fam.getWifeID());
        calendar.setTime(wife.getBirthday());
        calendar.add(Calendar.YEAR, 60);
        Date motherLast = calendar.getTime();

        Iterator<String> itr = _Fam.getChildren().iterator();
        while (itr.hasNext()) {
            String curchild = itr.next();
            Individual child = _indis.get(curchild);
            if (child.getBirthday().after(fatherLast)) {
                errStr = "ERROR: INDIVIDUAL: US12: "  + child.getId() + " is more than 80 years younger than Father: " + husband.getId();
                this.ErrorInfo.add(errStr);
            }

            if (child.getBirthday().after(motherLast)) {
                errStr = "ERROR: INDIVIDUAL: US12: "  + child.getId() + " is more than 60 years younger than Mother: " + wife.getId();
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }

    // US11: No bigamy(Ge Chang)
    public String US11(Map<String, Family> _Fams, Map<String, Individual> _indis) {
        String errStr = "";
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            Individual curIndi = entry.getValue();
            if (curIndi.getSpouse().size() > 1) {
                errStr = "ERROR: INDIVIDUAL: US11: "  + curIndi.getId() + " married in Family: " + curIndi.getSpouse();
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }

    // US10: Marriage after 14(Jiaxian Xing)
    public String US10(Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
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
                    errStr = "ERROR: FAMILY: US10: "  + curFam.getId() + " Married " + Formatdate.dateToString(curFam.getMarried()) +" but husband: " + husband.getId() + " " + husband.getName() + "born on " + Formatdate.dateToString(husband.getBirthday()) + " age < 14 when married";
                this.ErrorInfo.add("Error US10");
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
                    errStr = "ERROR: FAMILY: US10: "  + curFam.getId() + " Married " + Formatdate.dateToString(curFam.getMarried()) +" but wife: " + wife.getId() + " " + wife.getName() + "born on " + Formatdate.dateToString(wife.getBirthday()) + " age < 14 when married";
                this.ErrorInfo.add("Error US10");
            }
        }
        return errStr;
    }

    //    US09: Birth before death of parents(Yining Wen)
    public String US09(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
        String wname = "";
        Iterator itr = _Fam.getChildren().iterator(); // traversing over HashSet
        while(itr.hasNext()) {
            String curchild = (String) itr.next();
            Individual child = _indis.get(curchild);
            Individual husband = _indis.get(_Fam.getHusbandID());
            Individual wife = _indis.get(_Fam.getWifeID());
            if(husband.getDeath() != null) {
                Date hdeath = husband.getDeath();
                Calendar hrightNow = Calendar.getInstance();
                hrightNow.setTime(hdeath);
                hrightNow.add(Calendar.MONTH, -9);//结婚前9个月
                Date hd9 = hrightNow.getTime();
                if(child.getBirthday().before(hd9)){
                    errStr = "ERROR: INDIVIDUAL: US09: " + child.getId() + " Birthday " + Formatdate.dateToString(child.getBirthday()) +" before father's death on " + Formatdate.dateToString(husband.getDeath());
                    this.ErrorInfo.add(errStr);
                }
            }
            if(wife.getDeath() != null) {
                Date wd = wife.getDeath();
                if(child.getBirthday().before(wd)) {
                    errStr = "ERROR: INDIVIDUAL: US09: " + child.getId() + " Birthday " + Formatdate.dateToString(child.getBirthday()) +" before mother's death on " + Formatdate.dateToString(wife.getDeath());
                }
            }
        }
        return errStr;
    }

    //  US08: Birth before marriage of parents(Yining Wen)
    public String US08(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
//        String wrongname = "";
        Iterator itr = _Fam.getChildren().iterator(); // traversing over HashSet
        while(itr.hasNext()) {
            String curchild = (String) itr.next();
            Individual child = _indis.get(curchild);

            Calendar rightNow = Calendar.getInstance();
            Calendar rightafter = Calendar.getInstance();
            Date marr = _Fam.getMarried();
            rightNow.setTime(marr);
            rightNow.add(Calendar.MONTH, -9);//9 months before marry
            Date marr9 = rightNow.getTime();

            if(_Fam.getDivorced() != null){
                Date divo = _Fam.getDivorced();
                rightafter.setTime(divo);
                rightafter.add(Calendar.MONTH, +9);//9 months after
                Date divo9 = rightafter.getTime();
                if (child.getBirthday().after(divo9)) {
//                wrongname += child.getName();
                    errStr =  "ANOMALY: FAMILY: US08: " + _Fam.getId() + ": Child " +  child.getId() + " born " + Formatdate.dateToString(child.getBirthday()) + " after divorce on " + Formatdate.dateToString(_Fam.getDivorced());
                    this.ErrorInfo.add(errStr);
                }
            }

            if (child.getBirthday().before(marr9)) {
//                wrongname += child.getName();
                errStr =  "ANOMALY: FAMILY: US08: " + _Fam.getId() + ": Child " +  child.getId() + " born " + Formatdate.dateToString(child.getBirthday()) + " before marriage on " + Formatdate.dateToString(_Fam.getMarried());
                this.ErrorInfo.add(errStr);
//                errStr = "error: US08: " + wrongname + "'s birthday is earlier than parents wedding day";
            }
        }
        return errStr;
    }

    // US07: Less than 150 years old(Zhe Sun)
    public String US07(Individual _indi) throws ParseException {
        String errStr = "";
        if (_indi.getBirthday() != null && _indi.getDeath() != null && _indi.getDeath().after(_indi.getBirthday()) && CalculateAge.getAge(_indi.getBirthday(), _indi.getDeath()) > 150) {
            errStr = "ERROR: INDIVIDUAL: US07: " + _indi.getId() + ": More than 150 years old - Birth date " + Formatdate.dateToString(_indi.getBirthday()) + ": Death " + Formatdate.dateToString(_indi.getDeath());
        } else if (_indi.getBirthday() != null && _indi.getDeath() == null && CalculateAge.getAge(_indi.getBirthday()) > 150) {
            errStr = "ERROR: INDIVIDUAL: US07: " + _indi.getId() + ": More than 150 years old - Birth date " + Formatdate.dateToString(_indi.getBirthday());
        }
        this.ErrorInfo.add(errStr);
        return errStr;
    }




    // US06: Divorce before death(Tao Chai)
    public String US06(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getDeath() != null && wife.getDeath() != null && _Fam.getDivorced() != null && (_Fam.getDivorced().after(husband.getDeath()) && (_Fam.getDivorced().after(wife.getDeath())))) {
            errStr = "ERROR: FAMILY: US06: " + _Fam.getId() + " Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath()) + " and after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath()) + " too";
            this.ErrorInfo.add(errStr);
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getDivorced() != null && _Fam.getDivorced().after(wife.getDeath())) {
            errStr = "ERROR: FAMILY: US06: " + _Fam.getId() + " Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath());
            this.ErrorInfo.add(errStr);
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getDivorced() != null && _Fam.getDivorced().after(husband.getDeath())) {
            errStr = "ERROR: FAMILY: US06: " + _Fam.getId() + " Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }


    // US05: Marriage before death(Tao Chai)
    public String US05(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        //
        if (husband.getDeath() != null && wife.getDeath()!= null && _Fam.getMarried() != null && (_Fam.getMarried().after(husband.getDeath()) || _Fam.getMarried().after(husband.getDeath()))) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath()) + " and after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath()) + " too";
            this.ErrorInfo.add(errStr);
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getMarried().after(wife.getDeath())) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath());
            this.ErrorInfo.add(errStr);
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getMarried().after(husband.getDeath())) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }

    // US04: Marriage before divorce(Zhe Sun)
    public String US04(Family _Fam) throws ParseException {
        String errStr = "";
        if (_Fam.getMarried() != null && _Fam.getDivorced() != null && _Fam.getMarried().after(_Fam.getDivorced())){
            errStr = "ERROR: FAMILY: US04: " + _Fam.getId() + ": Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " before married " + Formatdate.dateToString(_Fam.getMarried());
        }
        this.ErrorInfo.add(errStr);
        return errStr;
    }

    // US03: Birth before death(Ge Chang)
    public String US03(Individual _indi) throws ParseException {
        String errStr = "";
        if (_indi.getDeath() != null && !_indi.getBirthday().equals(_indi.getDeath()) && _indi.getBirthday().after(_indi.getDeath())) {
            errStr = "ERROR: INDIVIDUAL: US03: " + _indi.getId() + ": Died " + Formatdate.dateToString(_indi.getDeath()) + " before born " + Formatdate.dateToString(_indi.getBirthday());
        }
        this.ErrorInfo.add(errStr);
        return errStr;
    }

    // US02: Birth before marriage(Ge Chang)
    public String US02(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getBirthday().after(_Fam.getMarried())) {
            errStr = "ERROR: FAMILY: US02: " + _Fam.getId() + ": husband's birthday " + Formatdate.dateToString(husband.getBirthday()) + " after marriage " + Formatdate.dateToString(_Fam.getMarried());
        }
        if (wife.getBirthday().after(_Fam.getMarried())) {
            errStr = "ERROR: FAMILY: US02: " + _Fam.getId() + ": wife's birthday " + Formatdate.dateToString(wife.getBirthday()) + " after marriage " + Formatdate.dateToString(_Fam.getMarried());
        }
        this.ErrorInfo.add(errStr);
        return errStr;
    }


    // US01: Dates before current date(Jiaxian Xing)
    public String US01(Map _Fams, Map _indis) throws ParseException {
        String errStr = "";
        Date today = new Date();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            if(curFam.getDivorced() != null){
                if(curFam.getDivorced().after(today))
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() +": Divorced date " + Formatdate.dateToString(curFam.getDivorced()) + " occurs in the future";
                this.ErrorInfo.add("Error US01");
            }
            if(curFam.getMarried() != null){
                if(curFam.getMarried().after(today))
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() +": Married date " + Formatdate.dateToString(curFam.getMarried()) + " occurs in the future";
                this.ErrorInfo.add("Error US01");
            }
        }
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
            if (curInd.getBirthday() != null) {
                if (curInd.getBirthday().after(today))
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Birthday "+ Formatdate.dateToString(curInd.getBirthday()) + " occurs in the future";
                this.ErrorInfo.add("Error US01");
            }
            if (curInd.getDeath() != null) {
                if (curInd.getDeath().after(today))
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Death "+ Formatdate.dateToString(curInd.getDeath()) + " occurs in the future";
                this.ErrorInfo.add("Error US01");
            }
        }
        return errStr;
    }
}
