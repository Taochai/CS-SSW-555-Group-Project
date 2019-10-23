package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us08 {

    private Set<String> ErrorInfo;

    public us08(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    //  US08: Birth before marriage of parents(Yining Wen)
    public String US08(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String errStr = "";
//        String wrongname = "";
        Iterator itr = _Fam.getChildren().iterator(); // traversing over HashSet
        while(itr.hasNext()) {
            String curchild = (String) itr.next();
            Individual child = _indis.get(curchild);

            Calendar rightNow = Calendar.getInstance();
            Calendar rightafter = Calendar.getInstance();
            Date marr = _Fam.getMarried();
            rightNow.setTime(marr);
            rightNow.add(Calendar.MONTH, -9);//9 months before marry
            Date marr9 = rightNow.getTime();

            if(_Fam.getDivorced() != null){
                Date divo = _Fam.getDivorced();
                rightafter.setTime(divo);
                rightafter.add(Calendar.MONTH, +9);//9 months after
                Date divo9 = rightafter.getTime();
                if (child.getBirthday().after(divo9)) {
//                wrongname += child.getName();
                    errStr =  "ANOMALY: FAMILY: US08: " + _Fam.getId() + ": Child " +  child.getId() + " born " + Formatdate.dateToString(child.getBirthday()) + " after divorce on " + Formatdate.dateToString(_Fam.getDivorced());
                    this.ErrorInfo.add(errStr);
                }
            }

            if (child.getBirthday().before(marr9)) {
//                wrongname += child.getName();
                errStr =  "ANOMALY: FAMILY: US08: " + _Fam.getId() + ": Child " +  child.getId() + " born " + Formatdate.dateToString(child.getBirthday()) + " before marriage on " + Formatdate.dateToString(_Fam.getMarried());
                this.ErrorInfo.add(errStr);
//                errStr = "error: US08: " + wrongname + "'s birthday is earlier than parents wedding day";
            }
        }
        return errStr;
    }
}

