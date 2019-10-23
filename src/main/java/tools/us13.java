package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us13 {

    private Set<String> ErrorInfo;

    public us13(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    //    Jiaxian Xing
    //US13 Birth dates of siblings should be more than 8 months apart or less than 2 days apart (twins may be born one day apart, e.g. 11:59 PM and 12:02 AM the following calendar day)
    public String US13(Family _Fam, Map<String, Individual> _indis){
        String errStr = "";
        Map<String, Date> siblings = new HashMap<>();
        //put every child <id,birthday> in HashMap;
        for(String siblingId: _Fam.getChildren()){
            Individual curInd = _indis.get(siblingId);
            Date curIndBd = curInd.getBirthday();
            siblings.put(siblingId, curIndBd);
        }
        //iterate through every pair of child.
        for(String siblingId1: _Fam.getChildren()){
            Date SiblingBd1 = siblings.get(siblingId1);
            for(String siblingId2: _Fam.getChildren()) {//TODO:should start from siblingId1 to end!
                Date SiblingBd2 = siblings.get(siblingId2);

                if(SiblingBd1.before(SiblingBd2)){
                    Date temp = SiblingBd1;
                    SiblingBd1 = SiblingBd2;
                    SiblingBd2= temp;
                }
                long diff = SiblingBd1.getTime() - SiblingBd2.getTime();
                int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                int diffMonth = (int) (diffDays / 30);
                int diffYear = (int) (diffMonth / 12);
                if(diffYear == 0){
                    if (diffMonth < 8) {
                        if (diffMonth == 0) {
                            if (diffDays > 2){
                                errStr = "ERROR: US13: FamilyID:"+_Fam .getId()+" children:"+siblingId1+" and children:"+siblingId2+"birthday difference greater than 2 days AND less than 8 months ";
                                this.ErrorInfo.add(errStr);
                            }
                        }
                        else {
                            errStr = "ERROR: US13: FamilyID:"+_Fam .getId()+" children:"+siblingId1+" and children:"+siblingId2+"birthday difference less than 8 months AND greater than 2 days";
                            this.ErrorInfo.add(errStr);
                        }
                    }
                }
            }
        }
        return errStr;
    }
}

