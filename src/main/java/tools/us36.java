package tools;

import objects.Individual;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class us36 {
    private Set<String> AllRecentlyDiedIndi;

    public us36() {
        this.AllRecentlyDiedIndi = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllRecentlyDiedIndi;
    }

    // US36: List recent deaths; List all people in a GEDCOM file who died in the last 30 days
    public void US36(Individual indi) throws ParseException {
        String messagesStr;
        String indiID = indi.getId();
        String indiName = indi.getName();
        if (indi.getAlive() != null && !indi.getAlive() && indi.getDeath() != null){
        	Date deathDay = indi.getDeath();
        	if (deathDay.before(new Date()) && (deathDay.getTime() + (1000L*60*60*24*30) > new Date().getTime())) {
        		messagesStr = "\t" + indiID + ": " + indiName + " is dead in the last 30 days on " + ((indi.getDeath() != null)? Formatdate.dateToString(indi.getDeath()): "Unknown");
        		this.AllRecentlyDiedIndi.add(messagesStr);
        	}
        }

    }

}