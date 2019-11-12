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

        //check individual
        for (Map.Entry<String, Individual> entry : (Iterable<Map.Entry<String, Individual>>) _indis.entrySet()) {
            Individual curInd = entry.getValue();
            if(withinXXDays(curInd.getBirthday(),30)){
                errStr = "OOPS: US35: Individual: " + "The Person:" + curInd.getId()+curInd.getName() + " born within last 30 days from today";
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }

    public static boolean withinXXDays(Date time, int days){
        Date now = new Date();
        long diff = now.getTime() -  time.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                    if (diffDays <= days){
                        return true;
                    }
                    else return false;
    }
}
