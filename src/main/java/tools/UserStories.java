/*
 * @Description: This file contains all the user stories
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/CS-SSW-555-Group-Project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:12:43
 */
package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.*;

public class UserStories {

    private Set<String> ErrorInfo;

    public Set<String> getErrorInfo() {
        return ErrorInfo;
    }

    public UserStories() {
        ErrorInfo = new TreeSet<String>();
    }

    public void AllUserStory(Map _Fams, Map _indis) throws ParseException {
        this.IterateFam(_Fams, _indis);
        this.IterateInds(_Fams, _indis);

    }

    public void IterateFam(Map _Fams, Map _indis) throws ParseException {
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();

            this.US02(curFam, _indis);
            this.US04(curFam);
            this.US05(curFam, _indis);
            this.US06(curFam, _indis);
            this.US08(curFam, _indis);
            this.US09(curFam, _indis);
            this.US10(_Fams, _indis);
            this.US12(curFam, _indis);
            this.US13(curFam, _indis);
            this.US14(curFam, _indis);
            this.US15(curFam);
            this.US16(curFam, _indis);
            this.US17(curFam, _indis);
            this.US18(curFam, _indis);
            this.US19(curFam, _Fams, _indis);
            this.US20(curFam, _Fams, _indis);
        }
    }

    public void IterateInds(Map _Fams, Map _indis) throws ParseException {
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            this.US01(_Fams, _indis);
            this.US03(curIndis);
            this.US07(curIndis);
            this.US11(_Fams, _indis);


        }
    }

    public void printErrorInfo() {
        for (String a : this.getErrorInfo()) {
            if (a.equals("")) continue;
            System.out.println(a);
        }
    }

    // US01: Dates before current date(Jiaxian Xing)
    public void US01(Map _Fams, Map _indis) throws ParseException {
        String errStr;
        Date today = new Date();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            if (curFam.getDivorced() != null) {
                if (curFam.getDivorced().after(today)) {
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() + ": Divorced date " + Formatdate.dateToString(curFam.getDivorced()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);
                }
            }
            if (curFam.getMarried() != null) {
                if (curFam.getMarried().after(today)) {
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() + ": Married date " + Formatdate.dateToString(curFam.getMarried()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);
                }
            }
        }
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
            if (curInd.getBirthday() != null) {
                if (curInd.getBirthday().after(today)) {
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Birthday " + Formatdate.dateToString(curInd.getBirthday()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);
                }
            }
            if (curInd.getDeath() != null) {
                if (curInd.getDeath().after(today)) {
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Death " + Formatdate.dateToString(curInd.getDeath()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);
                }
            }
        }
    }

    // US02: Birth before marriage(Ge Chang)
    public void US02(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr;
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getBirthday() != null && _Fam.getMarried() != null && husband.getBirthday().after(_Fam.getMarried())) {
            errStr = "ERROR: FAMILY: US02: " + _Fam.getId() + ": husband's birthday " + Formatdate.dateToString(husband.getBirthday()) + " after marriage " + Formatdate.dateToString(_Fam.getMarried());
            this.ErrorInfo.add(errStr);
        }
        if (wife.getBirthday() != null && _Fam.getMarried() != null && wife.getBirthday().after(_Fam.getMarried())) {
            errStr = "ERROR: FAMILY: US02: " + _Fam.getId() + ": wife's birthday " + Formatdate.dateToString(wife.getBirthday()) + " after marriage " + Formatdate.dateToString(_Fam.getMarried());
            this.ErrorInfo.add(errStr);
        }
    }

    // US03: Birth before death(Ge Chang)
    public void US03(Individual _indi) throws ParseException {
        String errStr = "";
        if (_indi.getDeath() != null && !_indi.getBirthday().equals(_indi.getDeath()) && _indi.getBirthday().after(_indi.getDeath())) {
            errStr = "ERROR: INDIVIDUAL: US03: " + _indi.getId() + ": Died " + Formatdate.dateToString(_indi.getDeath()) + " before born " + Formatdate.dateToString(_indi.getBirthday());
            this.ErrorInfo.add(errStr);
        }
    }

    // US04: Marriage before divorce(Zhe Sun)
    public void US04(Family _Fam) throws ParseException {
        String errStr;
        if (_Fam.getMarried() != null && _Fam.getDivorced() != null && _Fam.getMarried().after(_Fam.getDivorced())) {
            errStr = "ERROR: FAMILY: US04: " + _Fam.getId() + ": Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " before married " + Formatdate.dateToString(_Fam.getMarried());
            this.ErrorInfo.add(errStr);
        }
    }

    // US05: Marriage before death(Tao Chai)
    public void US05(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr;
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        //
        if (husband.getDeath() != null && wife.getDeath() != null && _Fam.getMarried() != null && (_Fam.getMarried().after(husband.getDeath()) || _Fam.getMarried().after(husband.getDeath()))) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath()) + " and after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath()) + " too";
            this.ErrorInfo.add(errStr);
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getMarried().after(wife.getDeath())) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath());
            this.ErrorInfo.add(errStr);
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getMarried().after(husband.getDeath())) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath());
            this.ErrorInfo.add(errStr);
        }
    }

    // US06: Divorce before death(Tao Chai)
    public void US06(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr;
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
    }

    // US07: Less than 150 years old(Zhe Sun)
    public void US07(Individual _indi) throws ParseException {
        String errStr;
        if (_indi.getBirthday() != null && _indi.getDeath() != null && _indi.getDeath().after(_indi.getBirthday()) && CalculateAge.getAge(_indi.getBirthday(), _indi.getDeath()) > 150) {
            errStr = "ERROR: INDIVIDUAL: US07: " + _indi.getId() + ": More than 150 years old - Birth date " + Formatdate.dateToString(_indi.getBirthday()) + ": Death " + Formatdate.dateToString(_indi.getDeath());
            this.ErrorInfo.add(errStr);
        } else if (_indi.getBirthday() != null && _indi.getDeath() == null && CalculateAge.getAge(_indi.getBirthday()) > 150) {
            errStr = "ERROR: INDIVIDUAL: US07: " + _indi.getId() + ": More than 150 years old - Birth date " + Formatdate.dateToString(_indi.getBirthday());
            this.ErrorInfo.add(errStr);
        }
    }

    //  US08: Birth before marriage of parents(Yining Wen)
    public void US08(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr;
        Iterator itr = _Fam.getChildren().iterator(); // traversing over HashSet
        while (itr.hasNext()) {
            String curchild = (String) itr.next();
            Individual child = _indis.get(curchild);

            Calendar rightNow = Calendar.getInstance();
            Calendar rightafter = Calendar.getInstance();
            Date marr = _Fam.getMarried();
            rightNow.setTime(marr);
            rightNow.add(Calendar.MONTH, -9);//9 months before marry
            Date marr9 = rightNow.getTime();

            if (_Fam.getDivorced() != null) {
                Date divo = _Fam.getDivorced();
                rightafter.setTime(divo);
                rightafter.add(Calendar.MONTH, +9);//9 months after
                Date divo9 = rightafter.getTime();
                if (child.getBirthday().after(divo9)) {
                    errStr = "ANOMALY: FAMILY: US08: " + _Fam.getId() + ": Child " + child.getId() + " born " + Formatdate.dateToString(child.getBirthday()) + " after divorce on " + Formatdate.dateToString(_Fam.getDivorced());
                    this.ErrorInfo.add(errStr);
                }
            }

            if (child.getBirthday().before(marr9)) {
                errStr = "ANOMALY: FAMILY: US08: " + _Fam.getId() + ": Child " + child.getId() + " born " + Formatdate.dateToString(child.getBirthday()) + " before marriage on " + Formatdate.dateToString(_Fam.getMarried());
                this.ErrorInfo.add(errStr);
            }
        }
    }

    //    US09: Birth before death of parents(Yining Wen)
    public void US09(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr;
        String wname = "";
        Iterator itr = _Fam.getChildren().iterator(); // traversing over HashSet
        while (itr.hasNext()) {
            String curchild = (String) itr.next();
            Individual child = _indis.get(curchild);
            Individual husband = _indis.get(_Fam.getHusbandID());
            Individual wife = _indis.get(_Fam.getWifeID());
            if (husband.getDeath() != null) {
                Date hdeath = husband.getDeath();
                Calendar hrightNow = Calendar.getInstance();
                hrightNow.setTime(hdeath);
                hrightNow.add(Calendar.MONTH, -9);//结婚前9个月
                Date hd9 = hrightNow.getTime();
                if (child.getBirthday().after(hd9)) {
                    errStr = "ERROR: INDIVIDUAL: US09: " + child.getId() + " Birthday " + Formatdate.dateToString(child.getBirthday()) + " before father's death on " + Formatdate.dateToString(husband.getDeath());
                    this.ErrorInfo.add(errStr);
                }
            }
            if (wife.getDeath() != null) {
                Date wd = wife.getDeath();
                if (child.getBirthday().after(wd)) {
                    errStr = "ERROR: INDIVIDUAL: US09: " + child.getId() + " Birthday " + Formatdate.dateToString(child.getBirthday()) + " before mother's death on " + Formatdate.dateToString(wife.getDeath());
                    this.ErrorInfo.add(errStr);
                }
            }
        }
    }

    // US10: Marriage after 14(Jiaxian Xing)
    public void US10(Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr;
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
                if (ageMarried < 14) {
                    errStr = "ERROR: FAMILY: US10: " + curFam.getId() + " Married " + Formatdate.dateToString(curFam.getMarried()) + " but husband: " + husband.getId() + " " + husband.getName() + "born on " + Formatdate.dateToString(husband.getBirthday()) + " age < 14 when married";
                    this.ErrorInfo.add(errStr);
                }
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
                if (ageMarried < 14) {
                    errStr = "ERROR: FAMILY: US10: " + curFam.getId() + " Married " + Formatdate.dateToString(curFam.getMarried()) + " but wife: " + wife.getId() + " " + wife.getName() + "born on " + Formatdate.dateToString(wife.getBirthday()) + " age < 14 when married";
                    this.ErrorInfo.add(errStr);
                }
            }
        }
    }

    // US11: No bigamy -> Marriage should not occur during marriage to another spouse (Ge Chang)
    public void US11(Map<String, Family> _Fams, Map<String, Individual> _indis) {
        String errStr;
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            Individual curIndi = entry.getValue();
            if (curIndi.getSpouse().size() > 1) {
                for (String famId : curIndi.getSpouse()) {
                    Family fam = _Fams.get(famId);
                    for (String otherFamId : curIndi.getSpouse()) {
                        Family otherFam = _Fams.get(otherFamId);
                        if (famId.equals(otherFamId)) {
                            continue;
                        }
                        if (fam.getMarried().before(otherFam.getMarried())) {
                            if (fam.getDivorced() == null || fam.getDivorced().after(otherFam.getMarried())) {
                                errStr = "ERROR: INDIVIDUAL: US11: The person with id " + curIndi.getId() + " is married to the person " + (curIndi.getId().equals(otherFam.getHusbandID()) ? otherFam.getWifeID() : otherFam.getHusbandID()) + " with family id " + otherFam.getId() + " while the person is still married to person with id " + (curIndi.getId().equals(fam.getHusbandID()) ? fam.getWifeID() : fam.getHusbandID()) + " in family " + fam.getId();
                                this.ErrorInfo.add(errStr);
                            }
                        }
                        if (otherFam.getMarried().before(fam.getMarried())) {
                            if (otherFam.getDivorced() == null || otherFam.getDivorced().after(fam.getMarried())) {
                                errStr = "ERROR: INDIVIDUAL: US11: The person with id " + curIndi.getId() + " is married to the person " + (curIndi.getId().equals(fam.getHusbandID()) ? fam.getWifeID() : fam.getHusbandID()) + " with family id " + fam.getId() + " while the person is still married to person with id " + (curIndi.getId().equals(otherFam.getHusbandID()) ? otherFam.getWifeID() : otherFam.getHusbandID()) + " in family " + otherFam.getId();
                                this.ErrorInfo.add(errStr);
                            }
                        }
                    }
                }
            }
        }
    }

    // US12: Parents not too old(Ge Chang)
    public void US12(Family _Fam, Map<String, Individual> _indis) {
        String errStr;
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
                errStr = "ERROR: INDIVIDUAL: US12: " + child.getId() + " is more than 80 years younger than Father: " + husband.getId();
                this.ErrorInfo.add(errStr);
            }

            if (child.getBirthday().after(motherLast)) {
                errStr = "ERROR: INDIVIDUAL: US12: " + child.getId() + " is more than 60 years younger than Mother: " + wife.getId();
                this.ErrorInfo.add(errStr);
            }
        }
    }

    // Jiaxian Xing
    // US13 Birth dates of siblings should be more than 8 months apart or less than 2 days apart
    // (twins may be born one day apart, e.g. 11:59 PM and 12:02 AM the following calendar day)
    public void US13(Family _Fam, Map<String, Individual> _indis) {
        String errStr;
        Map<String, Date> siblings = new HashMap<>();
        //put every child <id,birthday> in HashMap;
        for (String siblingId : _Fam.getChildren()) {
            Individual curInd = _indis.get(siblingId);
            Date curIndBd = curInd.getBirthday();
            siblings.put(siblingId, curIndBd);
        }
        //iterate through every pair of child.
        for (String siblingId1 : _Fam.getChildren()) {
            Date SiblingBd1 = siblings.get(siblingId1);
            for (String siblingId2 : _Fam.getChildren()) {//TODO:should start from siblingId1 to end!
                Date SiblingBd2 = siblings.get(siblingId2);

                if (SiblingBd1.before(SiblingBd2)) {
                    Date temp = SiblingBd1;
                    SiblingBd1 = SiblingBd2;
                    SiblingBd2 = temp;
                }
                long diff = SiblingBd1.getTime() - SiblingBd2.getTime();
                int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                int diffMonth = diffDays / 30;
                int diffYear = diffMonth / 12;
                if (diffYear == 0) {
                    if (diffMonth < 8) {
                        if (diffMonth == 0) {
                            if (diffDays > 2) {
                                errStr = "ERROR: INDIVIDUAL: US13: FamilyID: " + _Fam.getId() + " children: " + siblingId1 + " and children: " + siblingId2 + " birthday difference greater than 2 days AND less than 8 months ";
                                this.ErrorInfo.add(errStr);
                            }
                        } else {
                            errStr = "ERROR: INDIVIDUAL: US13: FamilyID: " + _Fam.getId() + " children: " + siblingId1 + " and children: " + siblingId2 + " birthday difference less than 8 months AND greater than 2 days";
                            this.ErrorInfo.add(errStr);
                        }
                    }
                }
            }
        }
    }

    //US14 No more than five siblings should be born at the same time (Jiaxian Xing)
    public void US14(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr;
        //put every child <birthday> in HashSet;
        Map<Date, Integer> siblings = new HashMap<>();
        for (String siblingId : _Fam.getChildren()) {
            Individual curInd = _indis.get(siblingId);
            Date curIndBd = curInd.getBirthday();
            if (siblings.containsKey(curIndBd)) {
                int count = siblings.get(curIndBd);
                siblings.replace(curIndBd, count + 1);
            } else {
                siblings.put(curIndBd, 1);
            }
        }
        //iterate through every child.
        for (Map.Entry<Date, Integer> entry : siblings.entrySet()) {
            Date birthday = entry.getKey();
            Integer count = entry.getValue();
            if (count > 5) {
                errStr = "ERROR: FAMILY: US14: FamilyID: " + _Fam.getId() + " has more than five child is born in date:" + Formatdate.dateToString(birthday);
                this.ErrorInfo.add(errStr);
            }
        }
    }

    //US15:  fewer than 15 siblings in a family
    public void US15(Family _Fam) {
        String errStr;
        if (_Fam.getChildren().size() >= 15) {
            errStr = "ERROR: FAMILY: US15: " + _Fam.getId() + " has more or equal than 15 siblings.";
            this.ErrorInfo.add(errStr);
        }
    }

    //US16: Family male should have same last name.
    public void US16(Family _Fam, Map<String, Individual> _indis) {
        String errStr;
        String fatherLastName = _Fam.getFatherLastName();
        for (String indiID : _Fam.getChildren()) {
            Individual child = _indis.get(indiID);
            if (child.getGender() == 'M' && !child.getLastName().equals(fatherLastName)) {
                errStr = "ERROR: FAMILY: US16: " + _Fam.getId() + " male members don`t have same last name.";
                this.ErrorInfo.add(errStr);
            }
        }
    }

    //    No marriages to children(Yining Wen)
    public void US17(Family _Fam, Map<String, Individual> _indis) {
        String errStr;
        Set<String> children = _Fam.getChildren();
        if (children != null) {
            for (String child : children) {
                Individual childid = _indis.get(child);
                Iterator<String> childspouse = childid.getSpouse().iterator(); // traversing over HashSet
                while (childspouse.hasNext()) {
                    String spouseFamId = childspouse.next();
                    if (spouseFamId.equals(_Fam.getId())) {
                        errStr = "ERROR: FAMILY: US17: Family: " + _Fam.getId() + "'s member " + childid.getName() + " married parent";
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
        }
    }

    // Siblings should not marry(Yining Wen)
    public void US18(Family _Fam, Map<String, Individual> _indis) {
        String errStr;
        Set<String> children = _Fam.getChildren();
        Set<String> allspouse = new HashSet<>();

        for (String childID : children) {
            for (String eachSpouseFamID : _indis.get(childID).getSpouse()) {
                if (allspouse.contains(eachSpouseFamID)) {
                    errStr = "ERROR: FAMILY: US18: " + " FamilyID: " + _Fam.getId() + " has two children that married each other";
                    this.ErrorInfo.add(errStr);
                } else {
                    allspouse.add(eachSpouseFamID);
                }
            }
        }
    }


    // US19: First cousins should not marry one another(Zhe Sun)
    public void US19(Family _Fam, Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr;
        String famID = _Fam.getId();
        String husID = _Fam.getHusbandID();
        String wifeID = _Fam.getWifeID();
        boolean husSafe = false;
        boolean wifeSafe = false;
        Set<String> husParents = null;
        Set<String> husGrandParents = null;
        Set<String> wifeParents = null;
        Set<String> wifeGrandParents = null;

        // if husband has a family record
        if (_indis.get(husID).getChild().isEmpty()) {
            // no record means safe
            husSafe = true;
        } else {
            // get husband's family ID set
            husParents = _indis.get(husID).getChild();
        }

        // if wife has a family record
        if (_indis.get(wifeID).getChild().isEmpty()) {
            // no record means safe
            wifeSafe = true;
        } else {
            // get wife's family ID set
            wifeParents = _indis.get(wifeID).getChild();
        }

        // to see once one of the couple is safe so the relationship is safe, then we can finish
        if (husSafe || wifeSafe) {
            return;

            // husband and wife both have a record of their family
        } else {

            // to get wife's all grandParents family IDs
            for (String wfam : wifeParents) {

                // get wife's mother ID
                String wifeMID = _Fams.get(wfam).getWifeID();

                // get wife's father ID
                String wifeFID = _Fams.get(wfam).getHusbandID();

                // to get wife's grandParents family IDs
                // if wife's mother does not have a record of her parents
                if (_indis.get(wifeMID).getChild().isEmpty()) {

                    // if wife's father does not have a record of his parents
                    if (_indis.get(wifeFID).getChild().isEmpty()) {
                        // then wife is safe since she has no relationship with husband's family
                        return;
                    } else {
                        wifeGrandParents = _indis.get(wifeFID).getChild();
                    }
                    // if wife's mother has a record of her parents
                } else {
                    // record wife's mother's family ID set
                    wifeGrandParents = _indis.get(wifeMID).getChild();

                    // if wife's father has a record of his parents
                    if (!_indis.get(wifeFID).getChild().isEmpty()) {
                        wifeGrandParents.addAll(_indis.get(wifeFID).getChild());
                    }
                }
            }

            // to get husband's all grandParents family IDs
            for (String hfam : husParents) {

                // get husband's mother ID
                String husMID = _Fams.get(hfam).getWifeID();

                // get husband's father ID
                String husFID = _Fams.get(hfam).getHusbandID();

                // to get husband's all grandParents family IDs
                // if husband's mother does not have a record of her parents
                if (_indis.get(husMID).getChild().isEmpty()) {

                    // if husband's father does not have a record of his parents
                    if (_indis.get(husFID).getChild().isEmpty()) {
                        // then husband is safe since he has no relationship with wife's family
                        return;
                    } else {
                        husGrandParents = _indis.get(husFID).getChild();
                    }
                    // if husband's mother has a record of her parents
                } else {
                    // record husband's mother's family ID set
                    husGrandParents = _indis.get(husMID).getChild();

                    // if husband's father has a record of his parents
                    if (!_indis.get(husFID).getChild().isEmpty()) {
                        husGrandParents.addAll(_indis.get(husFID).getChild());
                    }
                }
            }

            if (husGrandParents.size() != 0 && wifeGrandParents.size() != 0) {
                husGrandParents.retainAll(wifeGrandParents);
                if (husGrandParents.size() != 0) {
                    errStr = "ERROR: FAMILY: US19: " + famID + " first cousins should not marry one another; but " + wifeID + " and " + husID + " did this";
                    this.ErrorInfo.add(errStr);
                }
            }
        }
    }


    // US20: Aunts and uncles should not marry their nieces or nephews(Zhe Sun)
    public void US20(Family _Fam, Map<String, Family> _Fams, Map<String, Individual> _indis) {

        String errStr;
        String famID = _Fam.getId();
        String husID = _Fam.getHusbandID();
        String wifeID = _Fam.getWifeID();
        boolean husSafe = false;
        boolean wifeSafe = false;
        Set<String> husParents = null;
        Set<String> husGrandParents;
        Set<String> wifeParents = null;
        Set<String> wifeGrandParents;

        // if husband has a family record
        if (_indis.get(husID).getChild().isEmpty()) {
            // no record means safe
            husSafe = true;
        } else {
            // get husband's family ID set
            husParents = _indis.get(husID).getChild();
        }

        // if wife has a family record
        if (_indis.get(wifeID).getChild().isEmpty()) {
            // no record means safe
            wifeSafe = true;
        } else {
            // get wife's family ID set
            wifeParents = _indis.get(wifeID).getChild();
        }

        // to see once one of the couple is safe so the relationship is safe, then we can finish
        if (husSafe || wifeSafe) {
            return;
        } else {
            // husband and wife both have a record of their family

            // assumption ONE: niece and uncle
            for (String wfam : wifeParents) {

                // get wife's mother ID
                String wifeMID = _Fams.get(wfam).getWifeID();

                // get wife's father ID
                String wifeFID = _Fams.get(wfam).getHusbandID();

                // if wife's mother does not have a record of her parents
                if (_indis.get(wifeMID).getChild().isEmpty()) {

                    // if wife's father does not have a record of his parents
                    if (_indis.get(wifeFID).getChild().isEmpty()) {
                        // then wife is safe since she has no relationship with husband's family
                        return;
                    } else {
                        wifeGrandParents = _indis.get(wifeFID).getChild();
                    }
                    // if wife's mother has a record of her parents
                } else {
                    // record wife's mother's family ID set
                    wifeGrandParents = _indis.get(wifeMID).getChild();

                    // if wife's father has a record of his parents
                    if (!_indis.get(wifeFID).getChild().isEmpty()) {
                        wifeGrandParents.addAll(_indis.get(wifeFID).getChild());
                    }
                }

                // to see if they have common parents
                for (String husFam : husParents) {
                    if (wifeGrandParents.contains(husFam)) {
                        errStr = "ERROR: FAMILY: US20: " + famID + " niece: " + wifeID + " married uncle: " + husID;
                        this.ErrorInfo.add(errStr);
                        return;
                    }
                }
            }


            // assumption TWO: nephew and aunt
            for (String hfam : husParents) {

                // get husband's mother ID
                String husMID = _Fams.get(hfam).getWifeID();

                // get husband's father ID
                String husFID = _Fams.get(hfam).getHusbandID();

                // if husband's mother does not have a record of her parents
                if (_indis.get(husMID).getChild().isEmpty()) {

                    // if husband's father does not have a record of his parents
                    if (_indis.get(husFID).getChild().isEmpty()) {
                        // then husband is safe since he has no relationship with wife's family
                        return;
                    } else {
                        husGrandParents = _indis.get(husFID).getChild();
                    }
                    // if husband's mother has a record of her parents
                } else {
                    // record husband's mother's family ID set
                    husGrandParents = _indis.get(husMID).getChild();

                    // if husband's father has a record of his parents
                    if (!_indis.get(husFID).getChild().isEmpty()) {
                        husGrandParents.addAll(_indis.get(husFID).getChild());
                    }
                }

                // to see if they have common parents
                for (String wifeFam : wifeParents) {
                    if (husGrandParents.contains(wifeFam)) {
                        errStr = "ERROR: FAMILY: US20: " + famID + " nephew: " + husID + " married aunt: " + wifeID;
                        this.ErrorInfo.add(errStr);
                        break;
                    }
                }
            }
        }
    }
}
