package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us17 {

    private Set<String> ErrorInfo;

    public us17(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // us17   No marriages to children(Yining Wen)
    public String US17(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
        Set<String> children = _Fam.getChildren();
        if(children != null){
            for(String child : children) {
                Individual childid = _indis.get(child);
                Iterator<String> childspouse = childid.getSpouse().iterator(); // traversing over HashSet
                while(childspouse.hasNext()) {
                    String spouseFamId = childspouse.next();
                    if (spouseFamId.equals(_Fam.getId())) {
                        errStr = "ERROR: FAMILY: US17: Family: " + _Fam.getId() + "'s member " + childid.getName() + " married parent";
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
        }
        return errStr;
    }
}

