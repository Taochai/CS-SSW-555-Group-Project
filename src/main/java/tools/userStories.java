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
            this.US12(curFam,_indis);
            this.US17(curFam,_indis);
            this.US18(curFam, _indis);
            this.US13(curFam,_indis);
            this.US14(curFam, _indis);
            this.US15(curFam);
            this.US16(curFam,_indis);
        }
    }

    public void IterateInds(Map _Fam, Map _indis) throws ParseException {
        Iterator<Map.Entry<String, Individual>> entries1 = _indis.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Individual> entry = entries1.next();
            Individual curIndis = entry.getValue();
            this.US03(curIndis);
            this.US07(curIndis);
            this.US19(curIndis, _Fam, _indis);
            this.US20(curIndis,_Fam,_indis);
        }
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
                if(curFam.getDivorced().after(today)){
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() +": Divorced date " + Formatdate.dateToString(curFam.getDivorced()) + " occurs in the future";

                this.ErrorInfo.add(errStr);


            }
            if(curFam.getMarried() != null){
                if(curFam.getMarried().after(today)){
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() +": Married date " + Formatdate.dateToString(curFam.getMarried()) + " occurs in the future";
                this.ErrorInfo.add(errStr);
            }
        }
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
            if (curInd.getBirthday() != null) {
                if (curInd.getBirthday().after(today)){
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Birthday "+ Formatdate.dateToString(curInd.getBirthday()) + " occurs in the future";
                this.ErrorInfo.add(errStr);
            }
            if (curInd.getDeath() != null) {
                if (curInd.getDeath().after(today))
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Death "+ Formatdate.dateToString(curInd.getDeath()) + " occurs in the future";
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }

    // US02: Birth before marriage(Ge Chang)
    public String US02(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getBirthday() != null && _Fam.getMarried() != null && husband.getBirthday().after(_Fam.getMarried())) {
            errStr = "ERROR: FAMILY: US02: " +  _Fam.getId() + ": husband's birthday " + Formatdate.dateToString(husband.getBirthday()) + " after marriage " + Formatdate.dateToString(_Fam.getMarried());
        }
        if (wife.getBirthday() != null && _Fam.getMarried() != null && wife.getBirthday().after(_Fam.getMarried())){
            errStr = "ERROR: FAMILY: US02: " +  _Fam.getId() + ": wife's birthday " + Formatdate.dateToString(wife.getBirthday()) + " after marriage " + Formatdate.dateToString(_Fam.getMarried());
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

    // US04: Marriage before divorce(Zhe Sun)
    public String US04(Family _Fam) throws ParseException {
        String errStr = "";
        if (_Fam.getMarried() != null && _Fam.getDivorced() != null && _Fam.getMarried().after(_Fam.getDivorced())){
            errStr = "ERROR: FAMILY: US04: " + _Fam.getId() + ": Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " before married " + Formatdate.dateToString(_Fam.getMarried());
        }
        this.ErrorInfo.add(errStr);
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

    //    Jiaxian Xing
    //US13 Birth dates of siblings should be more than 8 months apart or less than 2 days apart (twins may be born one day apart, e.g. 11:59 PM and 12:02 AM the following calendar day)
    public String US13(Family _Fam, Map<String, Individual> _indis){
        String errStr = "";
        Map<String, Date> siblings = new HashMap<>();
        //put every child <id,birthday> in HashMap;
        for(String siblingId: _Fam.getChildren()){
            Individual curInd = _indis.get(siblingId);
            Date curIndBd = curInd.getBirthday();
            siblings.put(siblingId, curIndBd);
        }
        //iterate through every pair of child.
        for(String siblingId1: _Fam.getChildren()){
            Date SiblingBd1 = siblings.get(siblingId1);
            for(String siblingId2: _Fam.getChildren()) {//TODO:should start from siblingId1 to end!
                Date SiblingBd2 = siblings.get(siblingId2);

                if(SiblingBd1.before(SiblingBd2)){
                    Date temp = SiblingBd1;
                    SiblingBd1 = SiblingBd2;
                    SiblingBd2= temp;
                }
                long diff = SiblingBd1.getTime() - SiblingBd2.getTime();
                int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                int diffMonth = (int) (diffDays / 30);
                int diffYear = (int) (diffMonth / 12);
                if(diffYear == 0){
                    if (diffMonth < 8) {
                        if (diffMonth == 0) {
                            if (diffDays > 2)
                                errStr = "ERROR: US13: FamilyID:"+_Fam .getId()+" children:"+siblingId1+" and children:"+siblingId2+"birthday difference greater than 2 days AND less than 8 months ";
                            this.ErrorInfo.add(errStr);
                        }
                        else {
                            errStr = "ERROR: US13: FamilyID:"+_Fam .getId()+" children:"+siblingId1+" and children:"+siblingId2+"birthday difference less than 8 months AND greater than 2 days";
                            this.ErrorInfo.add(errStr);
                        }
                    }
                }
            }
        }
        return errStr;
    }

    //US14 No more than five siblings should be born at the same time (Jiaxian Xing)
    public String US14(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        //put every child <birthday> in HashSet;
        Map<Date,Integer> siblings = new HashMap<>();
        for(String siblingId: _Fam.getChildren()){
            Individual curInd = _indis.get(siblingId);
            Date curIndBd = curInd.getBirthday();
            if(siblings.containsKey(curIndBd)){
                int count = siblings.get(curIndBd);
                siblings.replace(curIndBd,count+1);
            }
            else {
                siblings.put(curIndBd,1);
            }
        }
        //iterate through every child.
        for (Map.Entry<Date, Integer> entry : siblings.entrySet()) {
            Date birthday = entry.getKey();
            Integer count = entry.getValue();
            if(count > 5){
                errStr = "ERROR: US14: FamilyID:"+_Fam .getId()+"has more than five child is born"+" in date:"+Formatdate.dateToString(birthday);
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }

    //US15:  fewer than 15 siblings in a family
    public String US15(Family _Fam){
        String errStr = "";
        if(_Fam.getChildren().size()>=15){
            errStr = "ERROR: FAMILY: US15: " +  _Fam.getId() + " has more or equal than 15 siblings.";
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }

    //US16: Family male should have same last name.
    public String US16(Family _Fam,Map<String, Individual> _indis){
        String errStr = "";
        String fatherLastName = _Fam.getFatherLastName();
        for(String indiID: _Fam.getChildren()){
            Individual child =  _indis.get(indiID);
            if(child.getGender()=='M'&& !child.getLastName().equals(fatherLastName)){
                errStr ="ERROR: FAMILY: US16: " + _Fam.getId() +" male members don`t have same last name.";
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }

    //    No marriages to children(Yining Wen)
    public String US17(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
        Set<String> children = _Fam.getChildren();
        if(children != null){
            for(String child : children) {
                Individual childid = _indis.get(child);
                Iterator<String> childspouse = childid.getSpouse().iterator(); // traversing over HashSet
                while(childspouse.hasNext()) {
                    String spouseFamId = childspouse.next();
                    if (spouseFamId.equals(_Fam.getId())) {
                        errStr = "ERROR: FAMILY: US17: Family: " + _Fam.getId() + "'s member " + childid.getName() + " married parent";
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
        }
        return errStr;
    }

    // Siblings should not marry(Yining Wen)
    public String US18(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
        Set<String> children = _Fam.getChildren();
        Set<String> husbandchildfamID = _indis.get(_Fam.getHusbandID()).getChild();
        Set<String> wifechildfamID = _indis.get(_Fam.getWifeID()).getChild();
        Set<String> allspouse = new HashSet<>();

        for(String childID: children){
            for(String eachSpouseFamID: _indis.get(childID).getSpouse()){
                if(allspouse.contains(eachSpouseFamID)){
                    errStr = "ERROR: FAMILY: US18: "+" FamilyID: "+_Fam.getId()+" has two children that married each other" ;
                    this.ErrorInfo.add(errStr);
                }else{
                    allspouse.add(eachSpouseFamID);
                }
            }
        }

//
        return errStr;
    }

    // US19: First cousins should not marry one another(Zhe Sun)
    public String US19(Individual indis, Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        // If this person has a spouse then we do the judgement
        // or rather there is no need to do the judgement
        if (indis.getSpouse().size() != 0){

            // iterate all the spouse, to see in each relationship
            // whether the marriage meet the requirement did not marry cousin
            // Actually spouse below is a family ID pointing to that one relationship
            for (String spouse:indis.getSpouse()) {

                // get the husband ID in present relationship to be checked
                String husID = _Fams.get(spouse).getHusbandID();

                // get the wife ID in present relationship to be checked
                String wifeID = _Fams.get(spouse).getWifeID();

                // get the husband's parent's family ID, to see husband belongs to which family
                Set<String> husFirstGenFam = _indis.get(husID).getChild();

                // get the wife's parent's family ID, to see wife belongs to which family
                Set<String> wifeFirstGenFam = _indis.get(wifeID).getChild();

                // get the ID of husband's fathers and mothers
                List<String> hussfathers = new ArrayList<>();
                List<String> hussmothers = new ArrayList<>();

                for (String id: husFirstGenFam) {
                    hussfathers.add(_Fams.get(id).getHusbandID());
                    hussmothers.add(_Fams.get(id).getWifeID());
                }

                // get the family ID of husband's grandparents
                Set<String> famIdOfHusband = new HashSet<>();

                // get all the grand family ID from father side
                for (String father: hussfathers) {
                    for (String fID: _indis.get(father).getChild()) {
                        famIdOfHusband.add(fID);
                    }
                }

                // get all the grand family ID from mother side
                for (String mother: hussmothers) {
                    for (String fID: _indis.get(mother).getChild()) {
                        famIdOfHusband.add(fID);
                    }
                }

                // get the ID of wife's fathers and mothers
                List<String> wifesfathers = new ArrayList<>();
                List<String> wifesmothers = new ArrayList<>();

                for (String id: wifeFirstGenFam) {
                    wifesfathers.add(_Fams.get(id).getHusbandID());
                    wifesmothers.add(_Fams.get(id).getWifeID());
                }

                // get the family ID of wife's grandparents
                Set<String> famIdOfWife = new HashSet<>();

                // get all the grand family ID from father side
                for (String father: wifesfathers) {
                    for (String fID: _indis.get(father).getChild()) {
                        famIdOfWife.add(fID);
                    }
                }

                // get all the grand family ID from mother side
                for (String mother: wifesmothers) {
                    for (String fID: _indis.get(mother).getChild()) {
                        famIdOfWife.add(fID);
                    }
                }

                // to see if they have common grandparents
                famIdOfHusband.retainAll(famIdOfWife);

                // if they have common grandparents then print out the error message
                if (famIdOfHusband.size() != 0){
                    errStr = "ERROR: FAMILY: US19: " + indis.getId() + " married cousin " + wifeID;
                }

                this.ErrorInfo.add(errStr);
            }
        }
        return  errStr;
    }

    // US20: Aunts and uncles should not marry their nieces or nephews(Zhe Sun)
    public String US20(Individual indis, Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {

        String errStr = "";
        // get myself's ID
        String selfID = indis.getId();

        // assume me a relative young person get married in this relationship
        // assign myself a appellation whether I am niece or nephew
        String appellation = (indis.getGender() == 'M') ? "niece": "nephew";

        // if I have a spouse then go ahead, or rather there is no need to go further
        if (indis.getSpouse().size() != 0) {

            // to see I belong to which family, in another I am which family's children
            Set<String> myFamilies = _indis.get(selfID).getChild();

            // generate a list to store all my fathers
            List<String> myfathers = new ArrayList<>();
            for (String fID: myFamilies) {
                myfathers.add(_Fams.get(fID).getHusbandID());
            }

            // generate a list to store all my mothers
            List<String> mymothers = new ArrayList<>();
            for (String fID: myFamilies) {
                mymothers.add(_Fams.get(fID).getWifeID());
            }
            // iterate all my spouse, to get my spouse's parents
            for (String spouse:indis.getSpouse()) {
                // get children of my spouse's parents to see if any of my parents is a child of my spouse's parent
                // in another word, just to see whether my parents are brother/sister to my spouse
                Set<String> childrenOfSpousesParents = _Fams.get(spouse).getChildren();
                myfathers.containsAll(childrenOfSpousesParents);
                mymothers.containsAll(childrenOfSpousesParents);
                // get my spouse's ID and assign my spouse a appellation
                String theother = (selfID == _Fams.get(spouse).getHusbandID())? _Fams.get(spouse).getWifeID():_Fams.get(spouse).getHusbandID();
                String spouseappellation = "";
                if (_indis.get(theother).getGender() != '\0')
                    if (_indis.get(theother).getGender() == 'M'){
                        spouseappellation = "uncle";
                    } else {
                        spouseappellation = "aunt";
                    }
                // if the intersection is not null, my father is my spouse's brother
                if (myfathers.size() != 0) {
                    errStr = "ERROR: FAMILY: US20: " + spouse + " : " + appellation + ": " + selfID + " married " + spouseappellation + ": " + theother;
                    this.ErrorInfo.add(errStr);
                }
                // if the intersection is not null, my mother is my spouse's sister
                if (mymothers.size() != 0) {
                    errStr = "ERROR: FAMILY: US20: " + spouse + " : " + appellation + ": " + selfID + " married " + spouseappellation + ": " + theother;
                    this.ErrorInfo.add(errStr);
                }
            }
        }
        return  errStr;
    }

}
