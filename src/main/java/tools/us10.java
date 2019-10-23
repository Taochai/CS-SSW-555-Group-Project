package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us10 {

    private Set<String> ErrorInfo;

    public us10(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US10: Marriage after 14(Jiaxian Xing)
    public String US10(Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
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
}

