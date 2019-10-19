package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us07 {

    private Set<String> ErrorInfo;

    public us07(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US07: Less than 150 years old(Zhe Sun)
    public String US07(Individual _indi) throws ParseException {
        String errStr = "";
        if (_indi.getBirthday() != null && _indi.getDeath() != null && _indi.getDeath().after(_indi.getBirthday()) && CalculateAge.getAge(_indi.getBirthday(), _indi.getDeath()) > 150) {
            errStr = "ERROR: INDIVIDUAL: US07: " + _indi.getId() + ": More than 150 years old - Birth date " + Formatdate.dateToString(_indi.getBirthday()) + ": Death " + Formatdate.dateToString(_indi.getDeath());
            this.ErrorInfo.add(errStr);
        }
        else if (_indi.getBirthday() != null && _indi.getDeath() == null && CalculateAge.getAge(_indi.getBirthday()) > 150) {
            errStr = "ERROR: INDIVIDUAL: US07: " + _indi.getId() + ": More than 150 years old - Birth date " + Formatdate.dateToString(_indi.getBirthday());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}

