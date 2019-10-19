package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us03 {

    private Set<String> ErrorInfo;

    public us03(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US03: Birth before death(Ge Chang)
    public String US03(Individual _indi) throws ParseException {
        String errStr = "";
        if (_indi.getDeath() != null && !_indi.getBirthday().equals(_indi.getDeath()) && _indi.getBirthday().after(_indi.getDeath())) {
            errStr = "ERROR: INDIVIDUAL: US03: " + _indi.getId() + ": Died " + Formatdate.dateToString(_indi.getDeath()) + " before born " + Formatdate.dateToString(_indi.getBirthday());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}

