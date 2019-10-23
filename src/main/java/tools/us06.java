package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us06 {

    private Set<String> ErrorInfo;

    public us06(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US06: Divorce before death(Tao Chai)
    public String US06(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        if (husband.getDeath() != null && wife.getDeath() != null && _Fam.getDivorced() != null && (_Fam.getDivorced().after(husband.getDeath()) && (_Fam.getDivorced().after(wife.getDeath())))) {
            errStr = "ERROR: FAMILY: US06: " + _Fam.getId() + " Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath()) + " and after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath()) + " too";
            this.ErrorInfo.add(errStr);
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getDivorced() != null && _Fam.getDivorced().after(wife.getDeath())) {
            errStr = "ERROR: FAMILY: US06: " + _Fam.getId() + " Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath());
            this.ErrorInfo.add(errStr);
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getDivorced() != null && _Fam.getDivorced().after(husband.getDeath())) {
            errStr = "ERROR: FAMILY: US06: " + _Fam.getId() + " Divorced " + Formatdate.dateToString(_Fam.getDivorced()) + " after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}

