package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

//Correct gender for role(Yining Wen)
public class us21 {
    private Set<String> ErrorInfo;

    public us21(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    public String US21(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if(husband.getGender() != 'M'){
            errStr ="ERROR: FAMILY: US21: The family: " + _Fam.getId() + " the husband: " + husband.getId() +" gender is not male";
            this.ErrorInfo.add(errStr);
        }
        if(wife.getGender() != 'F'){
            errStr ="ERROR: FAMILY: US21: The family: " + _Fam.getId() + " the wife: 23" + wife.getId() +" gender is not female";
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}
