package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us27 {

    private Set<String> ErrorInfo;

    public us27(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US27: Include individual ages(Yining Wen)
    public String US27(Individual _indi) throws ParseException {
        String errStr = "";
        if (_indi.getBirthday() == null) {
            errStr = "ERROR: INDIVIDUAL: US27: Can't get" + _indi.getId()+ "'s age";
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}

