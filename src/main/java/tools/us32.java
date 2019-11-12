package tools;

import objects.Family;
import objects.Individual;

import java.io.File;
import java.text.ParseException;
import java.util.*;

public class us32 {
    private Set<String> AllMultipleBirths;

    public us32() {
        this.AllMultipleBirths = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllMultipleBirths;
    }

    public void US32(Family _Fam, Map<String,Individual> _indis) throws ParseException {
        String messagesStr;
        String child1;
        if(_Fam.getChildren().size() >=2){
            Set<String> children = _Fam.getChildren();
            Iterator<String>child = children.iterator();
            child1 = child.next();
            for(String child2 : children){
                Individual childID1 = _indis.get(child1);
                Individual childID2 = _indis.get(child2);
                Calendar rightNow = Calendar.getInstance();
                Calendar rightafter = Calendar.getInstance();
                Date ChildBD = childID1.getBirthday();
                rightNow.setTime(ChildBD);
                rightNow.add(Calendar.DATE, -1);//
                Date Day = rightNow.getTime();
                rightafter.setTime(ChildBD);
                rightafter.add(Calendar.DATE, 1);//
                Date Day1 = rightafter.getTime();
                if(childID1 != childID2) {
                    if(childID2.getBirthday().equals(ChildBD) || childID2.getBirthday().equals(Day) || childID2.getBirthday().equals(Day1)) {
                        messagesStr = "\t" + _Fam.getId() + " has multiple births:  " + childID1.getName() + "and " + childID2.getName();
                        this.AllMultipleBirths.add(messagesStr);
                    }
                }
            }
        }
    }
}
