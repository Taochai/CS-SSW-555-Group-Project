package tools;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import objects.Family;
import objects.Individual;

public class us29 {
    private Set<String> ErrorInfo;

    public us29() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    public String US29(Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        Iterator<Map.Entry<String, Family>> entries = _Fams.entrySet().iterator();
        out: while (entries.hasNext()) {
            Map.Entry<String, Family> entry = entries.next();
            Family curFam = entry.getValue();
            if (curFam.getChildren().size() == 0) {
            	errStr = "ERROR: FAMILY: US29: No child in Family with id " + curFam.getId();
            	//ErrorInfo.add(errStr);
            	continue;
            }
            if (curFam.getChildren().size() == 1) {
            	errStr = "ERROR: FAMILY: US29: Only one child in Family with id " + curFam.getId();
            	//ErrorInfo.add(errStr);
            	continue;
            }

            List<Individual> indiList = new ArrayList<>();
            for (String indiId : curFam.getChildren()) {
                Individual indi = _indis.get(indiId);
                if (indi.getBirthday() == null) {
                	errStr = "ERROR: FAMILY: US29: Individual with id " + indi.getId() + " don't have birthday in Family with id " + curFam.getId();
                	//ErrorInfo.add(errStr);
                	continue out;
                }
                int index = 0;
                for (Individual i : indiList) {
                	if (i.getBirthday().equals(indi.getBirthday())) {
                    	errStr = "ERROR: FAMILY: US29: Individual with id " + i.getId() + " and Individual with id " + indi.getId() + " has the same birthday " + Formatdate.dateToString(i.getBirthday()) + " in Family with id " + curFam.getId();
                    	//ErrorInfo.add(errStr);
                    	continue out;
                	}
                    if (i.getBirthday().after(indi.getBirthday())) {
                        break;
                    }
                    index++;
                }
                indiList.add(index, indi);
            }
            
            errStr = "Siblings in family with id" + curFam.getId();
            for (Individual indi : indiList) {
                errStr += "\n\tid: " + indi.getId() + " birthday: " + Formatdate.dateToString(indi.getBirthday());
            }
            ErrorInfo.add(errStr);
        }

        return errStr;
    }
}
