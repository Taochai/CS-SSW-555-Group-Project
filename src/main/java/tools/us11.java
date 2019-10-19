package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us11 {

    private Set<String> ErrorInfo;

    public us11(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US11: No bigamy(Ge Chang)
    public String US11(Map<String, Family> _Fams, Map<String, Individual> _indis) {
        String errStr = "";
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            Individual curIndi = entry.getValue();
            if (curIndi.getSpouse().size() > 1) {
                errStr = "ERROR: INDIVIDUAL: US11: "  + curIndi.getId() + " married in Family: " + curIndi.getSpouse();
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }
}

