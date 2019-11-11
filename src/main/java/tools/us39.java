package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static tools.CalculateAge.isWithinXXDays;

public class us39 {
    private Set<String> AllUpcommingAnniversaries;

    public us39() {
        this.AllUpcommingAnniversaries = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllUpcommingAnniversaries;
    }

    // US39: List upcoming anniversaries: List all living couples in a GEDCOM file whose marriage anniversaries occur in the next 30 days (Zhe Sun)
    public void US39(Family _Fam) throws ParseException {
        String messagesStr;
        String famID = _Fam.getId();

        if (_Fam.getMarried() != null && _Fam.getDivorced() == null && isWithinXXDays(_Fam.getMarried(),30)){
            messagesStr = "\tFamily: " + famID + " married on " + Formatdate.dateToString((_Fam.getMarried())) + " which its anniversary will occurs in the next 30 days.";
            this.AllUpcommingAnniversaries.add(messagesStr);
        }
    }
}
