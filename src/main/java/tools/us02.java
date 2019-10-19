package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us02 {

    private Set<String> ErrorInfo;

    public us02(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
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
}

