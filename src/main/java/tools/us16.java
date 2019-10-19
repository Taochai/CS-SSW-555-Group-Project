package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us16 {

    private Set<String> ErrorInfo;

    public us16(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
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
}

