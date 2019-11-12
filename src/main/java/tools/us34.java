package tools;

import objects.*;
import java.util.*;
import static tools.CalculateAge.*;

public class us34 {
    private Set<String> ErrorInfo;

    public us34() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    // us34:List large age differences
    //List all couples who were married when the older spouse was more than twice as old as the younger spouse
    public String US34(Map _Fams, Map _indis) {
        String errStr = "";

        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
            Individual curFamHusband = (Individual) _indis.get(curFam.getHusbandID());
            Individual curFamWife = (Individual) _indis.get(curFam.getWifeID());
            Date famMarryDate = curFam.getMarried();
//            int husbandMarryAge = getAge(curFamHusband.getBirthday(),famMarryDate);
//            int wifeMarryAge = getAge(curFamWife.getBirthday(),famMarryDate);
            if()
        }

//        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
//            Individual curInd = entry.getValue();
//        }
        return errStr;
    }
}
