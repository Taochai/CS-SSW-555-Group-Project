package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us04 {

    private Set<String> ErrorInfo;

    public us04(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US04: Marriage before divorce(Zhe Sun)
    public String US04(Family _Fam) throws ParseException {
        String errStr = "";
        if (_Fam.getMarried() != null && _Fam.getDivorced() != null && _Fam.getMarried().after(_Fam.getDivorced())){
            errStr = "ERROR: FAMILY: US04: " + _Fam.getId() + ": Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " before married " + Formatdate.dateToString(_Fam.getMarried());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}

