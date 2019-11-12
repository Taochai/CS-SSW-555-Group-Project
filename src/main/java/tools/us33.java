package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class us33 {
    private Set<String> AllOrphan;

    public us33() {
        this.AllOrphan = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllOrphan;
    }

    public void US33(Map<String, Family> _Fams, Map<String, Individual> _Indis) throws ParseException {
        String errStr = "";
        Iterator<Map.Entry<String,Family>> entries = _Fams.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String,Family> entry = entries.next();
            String familyID = entry.getKey();
            Family eachFamily = entry.getValue();
            ifOrphan(eachFamily,_Indis);
        }
    }
    public void ifOrphan(Family fam, Map<String,Individual> indis){
        // 1.parents dead 2. age less than 18;
        Individual father = indis.get(fam.getHusbandID());
        Individual mother = indis.get(fam.getWifeID());

        if(!father.getAlive() && ! mother.getAlive()){
            for(String childID: fam.getChildren()){
                if(CalculateAge.getAge(indis.get(childID).getBirthday())<18){
                    String tmpStr = "INDIVIDUAL: US33: ID: "+childID+" is an orphan!";
                    this.AllOrphan.add(tmpStr);
                }
            }
        }

    }
}

