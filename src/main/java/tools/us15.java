package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us15 {

    private Set<String> ErrorInfo;

    public us15(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
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
}

