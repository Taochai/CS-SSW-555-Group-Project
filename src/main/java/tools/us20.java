package tools;

import objects.Family;
import objects.Individual;
import java.text.ParseException;
import java.util.*;

public class us20 {

    private Set<String> ErrorInfo;

    public us20(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    // US20: Aunts and uncles should not marry their nieces or nephews(Zhe Sun)
    public String US20(Individual indis, Map<String, Family> _Fams, Map<String, Individual> _indis) throws ParseException {

        String errStr = "";
        // get myself's ID
        String selfID = indis.getId();

        // assume me a relative young person get married in this relationship
        // assign myself a appellation whether I am niece or nephew
        String appellation = (indis.getGender() == 'M') ? "niece": "nephew";

        // if I have a spouse then go ahead, or rather there is no need to go further
        if (indis.getSpouse().size() != 0) {

            // to see I belong to which family, in another I am which family's children
            Set<String> myFamilies = _indis.get(selfID).getChild();

            // generate a list to store all my fathers
            List<String> myfathers = new ArrayList<>();
            for (String fID: myFamilies) {
                myfathers.add(_Fams.get(fID).getHusbandID());
            }

            // generate a list to store all my mothers
            List<String> mymothers = new ArrayList<>();
            for (String fID: myFamilies) {
                mymothers.add(_Fams.get(fID).getWifeID());
            }
            // iterate all my spouse, to get my spouse's parents
            for (String spouse:indis.getSpouse()) {
                // get children of my spouse's parents to see if any of my parents is a child of my spouse's parent
                // in another word, just to see whether my parents are brother/sister to my spouse
                Set<String> childrenOfSpousesParents = _Fams.get(spouse).getChildren();
                myfathers.containsAll(childrenOfSpousesParents);
                mymothers.containsAll(childrenOfSpousesParents);
                // get my spouse's ID and assign my spouse a appellation
                String theother = (selfID == _Fams.get(spouse).getHusbandID())? _Fams.get(spouse).getWifeID():_Fams.get(spouse).getHusbandID();
                String spouseappellation = "";
                if (_indis.get(theother).getGender() != '\0')
                    if (_indis.get(theother).getGender() == 'M'){
                        spouseappellation = "uncle";
                    } else {
                        spouseappellation = "aunt";
                    }
                // if the intersection is not null, my father is my spouse's brother
                if (myfathers.size() != 0) {
                    errStr = "ERROR: FAMILY: US20: " + spouse + " : " + appellation + ": " + selfID + " married " + spouseappellation + ": " + theother;
                    this.ErrorInfo.add(errStr);
                }
                // if the intersection is not null, my mother is my spouse's sister
                if (mymothers.size() != 0) {
                    errStr = "ERROR: FAMILY: US20: " + spouse + " : " + appellation + ": " + selfID + " married " + spouseappellation + ": " + theother;
                    this.ErrorInfo.add(errStr);
                }
            }
        }
        return  errStr;
    }
}

