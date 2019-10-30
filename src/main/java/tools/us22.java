package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.*;

public class us22 {
    private Set<String> ErrorInfo;

    public us22(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    //US14 No more than five siblings should be born at the same time (Jiaxian Xing)
    public String US22(Map<String,Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Set<String> individualsID = new HashSet<>();
        Set<String> sameIndiID = new HashSet<>();
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();

        while(entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            String eachIndiID = entry.getKey().replace("*","");
            if(!individualsID.contains(eachIndiID)){
                    individualsID.add(eachIndiID);
            }else {
                sameIndiID.add(eachIndiID);
            }
        }
        if(sameIndiID.size()!=0){
            String errorID ="";
            for(String errID:sameIndiID){
                errorID += errID+" ";
            }
            errStr = "ERROR: INDIVIDUAL: US22: "+errorID+" have more than one individuals";
            this.ErrorInfo.add(errStr);
        }
        //Check if family ID same
        Set<String> FamsID = new HashSet<>();
        Set<String> sameFamID = new HashSet<>();
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while(entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            String eachFamID = entry.getKey().replace("*","");
            if(!FamsID.contains(eachFamID)){
                FamsID.add(eachFamID);
            }else {
                sameFamID.add(eachFamID);
            }
        }
        if(sameFamID.size()!=0){
            String errorID ="";
            for(String errID:sameFamID){
                errorID += errID+" ";
            }
            errStr = "ERROR: Family: US22: "+errorID+" have more than one Families";
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}
