package tools;

import objects.Individual;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static tools.CalculateAge.isWithinXXDays;

public class us38 {
    private Set<String> AllComingBirthdayPeolple;

    public us38() {
        this.AllComingBirthdayPeolple = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllComingBirthdayPeolple;
    }

    // US38: List upcoming birthdays: List all living people in a GEDCOM file whose birthdays occur in the next 30 days (Zhe Sun)
    public void US38(Individual indi) throws ParseException {
        String messagesStr;
        String indiID = indi.getId();
        String indiName = indi.getName();
        if (indi.getAlive() && indi.getBirthday() != null && isWithinXXDays(indi.getBirthday(),30)){
            messagesStr = "\t" + indiID + ": " + indiName + "'s birthday is on " + Formatdate.dateToString((indi.getBirthday())) + " and birthday will occur in the next 30 days";
            this.AllComingBirthdayPeolple.add(messagesStr);
        }
    }

}
