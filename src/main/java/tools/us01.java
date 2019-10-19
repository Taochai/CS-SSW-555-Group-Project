package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us01 {

    private Set<String> ErrorInfo;
    public us01(){
        ErrorInfo = new HashSet<>();
    }
    public Set<String> getError(){
        return this.ErrorInfo;
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
                    this.ErrorInfo.add(errStr);}
            }
            if(curFam.getMarried() != null){
                if(curFam.getMarried().after(today)){
                    errStr = "ERROR: FAMILY: US01: " + curFam.getId() +": Married date " + Formatdate.dateToString(curFam.getMarried()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);}
            }
        }
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
            if (curInd.getBirthday() != null) {
                if (curInd.getBirthday().after(today)){
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Birthday "+ Formatdate.dateToString(curInd.getBirthday()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);}
            }
            if (curInd.getDeath() != null) {
                if (curInd.getDeath().after(today)){
                    errStr = "ERROR: INDIVIDUAL: US01: " + curInd.getId() + ": Death "+ Formatdate.dateToString(curInd.getDeath()) + " occurs in the future";
                    this.ErrorInfo.add(errStr);}
            }
        }
        return errStr;
    }
}
