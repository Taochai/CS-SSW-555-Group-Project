package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class us37 {
    private Set<String> AllRecentSurvivorsInfo;

    public us37() {
        this.AllRecentSurvivorsInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllRecentSurvivorsInfo;
    }

    // US37: List all living spouses and descendants of people in a GEDCOM file who died in the last 30 days
    public void US37(Family _Fam, Map<String, Individual> _indis) throws ParseException{
        String messageStr = "";
        Individual hus = _indis.get(_Fam.getHusbandID());
        Individual wife = _indis.get(_Fam.getWifeID());
        messageStr += "Servivors in family " + _Fam.getId() + ":\n";
        if (isDiedInTheLast30Days(hus)) {
        	messageStr += "\tHusband " + hus.getId() + " died in last 30 days on " + Formatdate.dateToString(hus.getDeath()) + "\n";
        	if (isAlive(wife)) {
        		messageStr += "\tWife " + wife.getId() + " is survivor\n";
        	}
        	for (String childId : _Fam.getChildren()) {
        		Individual child = _indis.get(childId);
        		if (isAlive(child)) {
            		messageStr += "\tChild " + child.getId() + " is survivor\n";
        		}
        	}
        } else if (isDiedInTheLast30Days(wife)) {
        	messageStr += "\tWife " + wife.getId() + " died in last 30 days on " + Formatdate.dateToString(wife.getDeath()) + "\n";
        	if (isAlive(hus)) {
        		messageStr += "\tHusband " + hus.getId() + " is survivor\n";
			}
        	for (String childId : _Fam.getChildren()) {
        		Individual child = _indis.get(childId);
        		if (isAlive(child)) {
            		messageStr += "\tChild " + child.getId() + " is survivor\n";
        		}
        	}
		} else {
			messageStr += "\tNo one died in family " + _Fam.getId() + " in last 30 days\n";
		}
        
        this.AllRecentSurvivorsInfo.add(messageStr);
    }

    private boolean isDiedInTheLast30Days(Individual indi) {
    	if (indi.getAlive() != null && !indi.getAlive() && indi.getDeath() != null) {
    		Date deathDay = indi.getDeath();
        	if (deathDay.before(new Date()) && (deathDay.getTime() + (1000L*60*60*24*30) > new Date().getTime())) {
        		return true;
        	}
    	}
    	return false;
    }
    
    private boolean isAlive(Individual indi) {
    	return indi.getAlive() != null && indi.getAlive() && indi.getDeath() == null;
    }
}
