package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us18 {

    private Set<String> ErrorInfo;

    public us18(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // Siblings should not marry(Yining Wen)
    public String US18(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
        Set<String> children = _Fam.getChildren();
        Set<String> husbandchildfamID = _indis.get(_Fam.getHusbandID()).getChild();
        Set<String> wifechildfamID = _indis.get(_Fam.getWifeID()).getChild();
        Set<String> allspouse = new HashSet<>();

        for(String childID: children){
            for(String eachSpouseFamID: _indis.get(childID).getSpouse()){
                if(allspouse.contains(eachSpouseFamID)){
                    errStr = "ERROR: FAMILY: US18: "+" FamilyID: "+_Fam.getId()+" has two children that married each other" ;
                    this.ErrorInfo.add(errStr);
                }else{
                    allspouse.add(eachSpouseFamID);
                }
            }
        }
        return errStr;
    }
}

