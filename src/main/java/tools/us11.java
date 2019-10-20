package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us11 {
    private Set<String> ErrorInfo;

    public us11() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    public String US11(Map<String, Family> _Fams, Map<String, Individual> _indis) {
        String errStr = "";
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            Individual curIndi = entry.getValue();
            if (curIndi.getSpouse().size() > 1) {
                for (String famId : curIndi.getSpouse()) {
                    Family fam = _Fams.get(famId);
                    for (String otherFamId : curIndi.getSpouse()) {
                        Family otherFam = _Fams.get(otherFamId);
                        if (famId.equals(otherFamId)) {
                            continue;
                        }
                        if (fam.getMarried().before(otherFam.getMarried())) {
                            if (fam.getDivorced() == null || fam.getDivorced().after(otherFam.getMarried())) {
                                errStr = "ERROR: INDIVIDUAL: US11: The person with id " + curIndi.getId() + " is married to the person " + (curIndi.getId().equals(otherFam.getHusbandID()) ? otherFam.getWifeID() : otherFam.getHusbandID()) + " with family id " + otherFam.getId() + " while the person is still married to person with id " + (curIndi.getId().equals(fam.getHusbandID()) ? fam.getWifeID() : fam.getHusbandID()) + " in family " + fam.getId();
                                this.ErrorInfo.add(errStr);
                            }
                        }
                        if (otherFam.getMarried().before(fam.getMarried())) {
                            if (otherFam.getDivorced() == null || otherFam.getDivorced().after(fam.getMarried())) {
                                errStr = "ERROR: INDIVIDUAL: US11: The person with id " + curIndi.getId() + " is married to the person " + (curIndi.getId().equals(fam.getHusbandID()) ? fam.getWifeID() : fam.getHusbandID()) + " with family id " + fam.getId() + " while the person is still married to person with id " + (curIndi.getId().equals(otherFam.getHusbandID()) ? otherFam.getWifeID() : otherFam.getHusbandID()) + " in family " + otherFam.getId();
                                this.ErrorInfo.add(errStr);
                            }
                        }
                    }
                }
            }
        }
        return errStr;
    }
}

