package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us14 {

    private Set<String> ErrorInfo;

    public us14(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    //US14 No more than five siblings should be born at the same time (Jiaxian Xing)
    public String US14(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        //put every child <birthday> in HashSet;
        Map<Date,Integer> siblings = new HashMap<>();
        for(String siblingId: _Fam.getChildren()){
            Individual curInd = _indis.get(siblingId);
            Date curIndBd = curInd.getBirthday();
            if(siblings.containsKey(curIndBd)){
                int count = siblings.get(curIndBd);
                siblings.replace(curIndBd,count+1);
            }
            else {
                siblings.put(curIndBd,1);
            }
        }
        //iterate through every child.
        for (Map.Entry<Date, Integer> entry : siblings.entrySet()) {
            Date birthday = entry.getKey();
            Integer count = entry.getValue();
            if(count > 5){
                errStr = "ERROR: US14: FamilyID:"+_Fam .getId()+" has more than five child is born"+" in date:"+Formatdate.dateToString(birthday);
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }
}

