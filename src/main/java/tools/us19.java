package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us19 {

    private Set<String> ErrorInfo;

    public us19(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US19: First cousins should not marry one another(Zhe Sun)
    public String US19(Individual indis, Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        // If this person has a spouse then we do the judgement
        // or rather there is no need to do the judgement
        if (indis.getSpouse().size() != 0){

            // iterate all the spouse, to see in each relationship
            // whether the marriage meet the requirement did not marry cousin
            // Actually spouse below is a family ID pointing to that one relationship
            for (String spouse:indis.getSpouse()) {

                // get the husband ID in present relationship to be checked
                String husID = _Fams.get(spouse).getHusbandID();

                // get the wife ID in present relationship to be checked
                String wifeID = _Fams.get(spouse).getWifeID();

                // get the husband's parent's family ID, to see husband belongs to which family
                Set<String> husFirstGenFam = _indis.get(husID).getChild();

                // get the wife's parent's family ID, to see wife belongs to which family
                Set<String> wifeFirstGenFam = _indis.get(wifeID).getChild();

                // get the ID of husband's fathers and mothers
                List<String> hussfathers = new ArrayList<>();
                List<String> hussmothers = new ArrayList<>();

                for (String id: husFirstGenFam) {
                    hussfathers.add(_Fams.get(id).getHusbandID());
                    hussmothers.add(_Fams.get(id).getWifeID());
                }

                // get the family ID of husband's grandparents
                Set<String> famIdOfHusband = new HashSet<>();

                // get all the grand family ID from father side
                for (String father: hussfathers) {
                    for (String fID: _indis.get(father).getChild()) {
                        famIdOfHusband.add(fID);
                    }
                }

                // get all the grand family ID from mother side
                for (String mother: hussmothers) {
                    for (String fID: _indis.get(mother).getChild()) {
                        famIdOfHusband.add(fID);
                    }
                }

                // get the ID of wife's fathers and mothers
                List<String> wifesfathers = new ArrayList<>();
                List<String> wifesmothers = new ArrayList<>();

                for (String id: wifeFirstGenFam) {
                    wifesfathers.add(_Fams.get(id).getHusbandID());
                    wifesmothers.add(_Fams.get(id).getWifeID());
                }

                // get the family ID of wife's grandparents
                Set<String> famIdOfWife = new HashSet<>();

                // get all the grand family ID from father side
                for (String father: wifesfathers) {
                    for (String fID: _indis.get(father).getChild()) {
                        famIdOfWife.add(fID);
                    }
                }

                // get all the grand family ID from mother side
                for (String mother: wifesmothers) {
                    for (String fID: _indis.get(mother).getChild()) {
                        famIdOfWife.add(fID);
                    }
                }

                // to see if they have common grandparents
                famIdOfHusband.retainAll(famIdOfWife);

                // if they have common grandparents then print out the error message
                if (famIdOfHusband.size() != 0){
                    errStr = "ERROR: FAMILY: US19: " + indis.getId() + " married cousin " + wifeID;
                }

                this.ErrorInfo.add(errStr);
            }
        }
        return  errStr;
    }
}

