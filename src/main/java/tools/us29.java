package tools;

import objects.Individual;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class us29 {
    private Set<String> AllDeceasedIndi;

    public us29() {
        this.AllDeceasedIndi = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllDeceasedIndi;
    }

    // US29: List deceased; List all deceased individuals in a GEDCOM file (Zhe Sun)
    public void US29(Individual indi) throws ParseException {
        String messagesStr;
        String indiID = indi.getId();
        String indiName = indi.getName();
        if (indi.getAlive() != null && !indi.getAlive()){
            messagesStr = "\t" + indiID + ": " + indiName + " is dead on " + ((indi.getDeath() != null)? Formatdate.dateToString(indi.getDeath()): "Unknown");
            this.AllDeceasedIndi.add(messagesStr);
        }

    }

}
=======
package tools;

import objects.Individual;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class us29 {
    private Set<String> AllDeceasedIndi;

    public us29() {
        this.AllDeceasedIndi = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllDeceasedIndi;
    }

    // US29: List deceased; List all deceased individuals in a GEDCOM file (Zhe Sun)
    public void US29(Individual indi) throws ParseException {
        String messagesStr;
        String indiID = indi.getId();
        String indiName = indi.getName();
        if (indi.getAlive() != null && !indi.getAlive()){
            messagesStr = "\t" + indiID + ": " + indiName + " is dead on " + ((indi.getDeath() != null)? Formatdate.dateToString(indi.getDeath()): "Unknown");
            this.AllDeceasedIndi.add(messagesStr);
        }

    }

}
>>>>>>> origin/Sprint3
