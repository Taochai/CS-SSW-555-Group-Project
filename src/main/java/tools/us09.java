package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us09 {

    private Set<String> ErrorInfo;

    public us09(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    //    US09: Birth before death of parents(Yining Wen)
    public String US09(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
        String wname = "";
        Iterator itr = _Fam.getChildren().iterator(); // traversing over HashSet
        while(itr.hasNext()) {
            String curchild = (String) itr.next();
            Individual child = _indis.get(curchild);
            Individual husband = _indis.get(_Fam.getHusbandID());
            Individual wife = _indis.get(_Fam.getWifeID());
            if(husband.getDeath() != null) {
                Date hdeath = husband.getDeath();
                Calendar hrightNow = Calendar.getInstance();
                hrightNow.setTime(hdeath);
                hrightNow.add(Calendar.MONTH, -9);//结婚前9个月
                Date hd9 = hrightNow.getTime();
                if(child.getBirthday().after(hd9)){
                    errStr = "ERROR: INDIVIDUAL: US09: " + child.getId() + " Birthday " + Formatdate.dateToString(child.getBirthday()) +" after father's death on " + Formatdate.dateToString(husband.getDeath());
                    this.ErrorInfo.add(errStr);
                }
            }
            if(wife.getDeath() != null) {
                Date wd = wife.getDeath();
                if(child.getBirthday().after(wd)) {
                    errStr = "ERROR: INDIVIDUAL: US09: " + child.getId() + " Birthday " + Formatdate.dateToString(child.getBirthday()) +" after mother's death on " + Formatdate.dateToString(wife.getDeath());
                    this.ErrorInfo.add(errStr);
                }
            }
        }
        return errStr;
    }
}

