package tools;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import objects.Family;
import objects.Individual;

public class us25 {
    private Set<String> ErrorInfo;

    public us25() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    public String US25(Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException{
        String errStr = "";
        Iterator<Map.Entry<String, Family>> entries = _Fams.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Family> entry = entries.next();
            Family curFam = entry.getValue();

            Map<String, Map<Date, Individual>> nameBirthdayMap = new HashMap<>();
            for (String indiId : curFam.getChildren()) {
                Individual indi = _indis.get(indiId);
                Map<Date, Individual> birthdayMap = nameBirthdayMap.get(indi.getName());
                if (birthdayMap == null) {
                    nameBirthdayMap.put(indi.getName(), birthdayMap = new HashMap<>());
                }
                Individual prevIndi = birthdayMap.get(indi.getBirthday());
                if (prevIndi == null) {
                    birthdayMap.put(indi.getBirthday(), indi);
                } else {
                    errStr = "ERROR: FAMILY: US25: The person with id " + indi.getId() + " has the same name " + indi.getName() + " and birthday " + Formatdate.dateToString(indi.getBirthday()) + " with person with id " + prevIndi.getId() + " in family with id " + curFam.getId();
                    this.ErrorInfo.add(errStr);
                }
            }
        }

        return errStr;
    }
}
