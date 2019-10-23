package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us12 {

    private Set<String> ErrorInfo;

    public us12(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US12: Parents not too old(Ge Chang)
    public String US12(Family _Fam, Map<String,Individual> _indis) {
        String errStr = "";
        Calendar calendar = Calendar.getInstance();
        Individual husband = _indis.get(_Fam.getHusbandID());
        calendar.setTime(husband.getBirthday());
        calendar.add(Calendar.YEAR, 80);
        Date fatherLast = calendar.getTime();

        Individual wife = _indis.get(_Fam.getWifeID());
        calendar.setTime(wife.getBirthday());
        calendar.add(Calendar.YEAR, 60);
        Date motherLast = calendar.getTime();

        Iterator<String> itr = _Fam.getChildren().iterator();
        while (itr.hasNext()) {
            String curchild = itr.next();
            Individual child = _indis.get(curchild);
            if (child.getBirthday().after(fatherLast)) {
                errStr = "ERROR: INDIVIDUAL: US12: "  + child.getId() + " is more than 80 years younger than Father: " + husband.getId();
                this.ErrorInfo.add(errStr);
            }

            if (child.getBirthday().after(motherLast)) {
                errStr = "ERROR: INDIVIDUAL: US12: "  + child.getId() + " is more than 60 years younger than Mother: " + wife.getId();
                this.ErrorInfo.add(errStr);
            }
        }
        return errStr;
    }
}

