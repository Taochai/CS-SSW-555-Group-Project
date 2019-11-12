package tools;

import objects.*;
import java.util.*;

public class us35 {
    private Set<String> ErrorInfo;

    public us35() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    // us35:List recent births
    //List all people in a GEDCOM file who were born in the last 30 days
    public String US35(Map _Fams, Map _indis) {
        String errStr = "";

        //check family member is in individual list
        //all individual roles (spouse, child) specified in family records should have corresponding entries in the corresponding  individual's records.
        Iterator<Map.Entry<String, Family>> entries1 = _Fams.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();
        }

        //check individual role is in family
        //All family roles (spouse, child) specified in an individual record should have corresponding entries in the corresponding family records
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
        }
        return errStr;
    }
}
