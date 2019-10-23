package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us05 {

    private Set<String> ErrorInfo;

    public us05(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US05: Marriage before death(Tao Chai)
    public String US05(Family _Fam, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Individual husband = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        //
        if (husband.getDeath() != null && wife.getDeath()!= null && _Fam.getMarried() != null && (_Fam.getMarried().after(husband.getDeath()) || _Fam.getMarried().after(wife.getDeath()))) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath()) + " and after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath()) + " too";
            this.ErrorInfo.add(errStr);
        } else if (husband.getDeath() == null && wife.getDeath() != null && _Fam.getMarried() != null && _Fam.getMarried().after(wife.getDeath())) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after wife's (" + wife.getId() + ") death on " + Formatdate.dateToString(wife.getDeath());
            this.ErrorInfo.add(errStr);
        } else if (wife.getDeath() == null && husband.getDeath() != null && _Fam.getMarried() != null && _Fam.getMarried().after(husband.getDeath())) {
            errStr = "ERROR: FAMILY: US05: " + _Fam.getId() + " Married " + Formatdate.dateToString(_Fam.getMarried()) + " after husband's (" + husband.getId() + ") death on " + Formatdate.dateToString(husband.getDeath());
            this.ErrorInfo.add(errStr);
        }
        return errStr;
    }
}

