package tools;

import objects.Family;
import objects.Individual;
import java.util.*;

public class us26 {

    private Set<String> ErrorInfo;

    public us26() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    // US26: Corresponding entries(Jiaxian Xing)
    //All family roles (spouse, child) specified in an individual record should have corresponding entries in the corresponding family records.
    // Likewise, all individual roles (spouse, child) specified in family records should have corresponding entries in the corresponding  individual's records.
    // I.e. the information in the individual and family records should be consistent.
    public String US26(Map _Fams, Map _indis) {
        String errStr = "";

        //check family member is in individual list
        //all individual roles (spouse, child) specified in family records should have corresponding entries in the corresponding  individual's records.
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            String curFamWife = curFam.getWifeID();
            String curFamHusband = curFam.getHusbandID();
            Set<String> curFamChildrenSet = curFam.getChildren();
            //no wife in Indi list
            if(!_indis.containsKey(curFamWife)){
                errStr = "ERROR: Individual: US26: "+"in family:"+ curFam.getId()+ "wife id: "+ curFamWife +" is not exist in Individual list";
                this.ErrorInfo.add(errStr);
            }
            else {
                Individual IndiBelongTo = (Individual) _indis.get(curFamWife);
                boolean containInCorrespondingIndi = false;
                for(String eachFam :IndiBelongTo.getSpouse()){
                    if(curFam.getId().equals(eachFam)){
                        containInCorrespondingIndi = true;
                    }
                }
                if(!containInCorrespondingIndi){
                    errStr = "ERROR: Individual: US26: "+"in family:"+ curFam.getId()+ " the wife id: "+ curFamWife +" is not record she is spouse to current family:"+curFam.getId();
                    this.ErrorInfo.add(errStr);
                }
            }
            //no husband in Indi list
            if(!_indis.containsKey(curFamHusband)){
                errStr = "ERROR: Individual: US26: "+"in family:"+ curFam.getId()+ "husband id: "+ curFamHusband +" is not exist in Individual list";
                this.ErrorInfo.add(errStr);
            }
            else {
                Individual IndiBelongTo = (Individual) _indis.get(curFamHusband);
                boolean containInCorrespondingIndi = false;
                for (String eachFam : IndiBelongTo.getSpouse()) {
                    if (curFam.getId().equals(eachFam)) {
                        containInCorrespondingIndi = true;
                    }
                }
                if (!containInCorrespondingIndi) {
                    errStr = "ERROR: Individual: US26: " + "in family:" + curFam.getId() + " the husband id: " + curFamHusband + " is not record he is spouse to current family:" + curFam.getId();
                    this.ErrorInfo.add(errStr);
                }
            }
            //no child in Indi list
            for (String child : curFamChildrenSet) {
                if(!_indis.containsKey(child)){
                    errStr = "ERROR: Individual: US26: "+"in family:"+ curFam.getId()+ "children id: "+ child +" is not exist in Individual list";
                    this.ErrorInfo.add(errStr);
                }
                else {
                    Individual IndiBelongTo = (Individual) _indis.get(child);
                    boolean containInCorrespondingIndi = false;
                    for (String eachFam : IndiBelongTo.getChild()) {
                        if (curFam.getId().equals(eachFam)) {
                            containInCorrespondingIndi = true;
                        }
                    }
                    if (!containInCorrespondingIndi) {
                        errStr = "ERROR: Individual: US26: " + "in family:" + curFam.getId() + " the chile id: " + child + " is not record the child is children to current family";
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
        }


        //check individual role is in family
        //All family roles (spouse, child) specified in an individual record should have corresponding entries in the corresponding family records
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
            Set<String> curIndiChildren = curInd.getChild();
            Set<String> curIndiSpouse = curInd.getSpouse();
            //the family this indi child to is exist in Fam
            for (String FamChildTo : curIndiChildren) {
                if(!_Fams.containsKey(FamChildTo)){
                    errStr = "ERROR: Family: US26: "+"the Individual:"+ curInd.getId()+ " the family:"+ FamChildTo +" which this indi as a children is not exist in Family list";
                    this.ErrorInfo.add(errStr);
                }
                else{
                    Family FamBelongTo = (Family) _Fams.get(FamChildTo);
                    boolean containInCorrespondingFam = false;
                    for (String eachIndi : FamBelongTo.getChildren()) {
                        if (curInd.getId().equals(eachIndi)) {
                            containInCorrespondingFam = true;
                        }
                    }
                    if (!containInCorrespondingFam) {
                        errStr = "ERROR: Family: US26: "+ " in family:"+ FamChildTo +" which this indi"+ curInd.getId() +" as a children is not record this indi is children to current family:";
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
            for (String FamSuposeTo : curIndiSpouse) {
                if(!_Fams.containsKey(FamSuposeTo)){
                    errStr = "ERROR: Family: US26: "+"the Individual:"+ curInd.getId()+ " the family:"+ FamSuposeTo +" which this indi as a spouse is not exist in Family list";
                    this.ErrorInfo.add(errStr);
                }
                else{
                    Family FamBelongTo = (Family) _Fams.get(FamSuposeTo);
                    boolean containInCorrespondingFam = false;
                    for (String eachIndi : FamBelongTo.getChildren()) {
                        if (curInd.getId().equals(eachIndi)) {
                            containInCorrespondingFam = true;
                        }
                    }
                    if (!containInCorrespondingFam) {
                        errStr = "ERROR: Family: US26: "+ " in family:"+ FamSuposeTo +" which this indi"+ curInd.getId() +" as a spouse is not record this indi is children to current family:";
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
        }
        return errStr;
    }
}
