package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.*;

public class us24 {

    private Set<String> ErrorInfo;

    public us24() {
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.ErrorInfo;
    }

    // US24: Unique families by spouses(Jiaxian Xing)
    //No more than one family with the same spouses by name and the same marriage date should appear in a GEDCOM file
    public String US24(Map _Fams, Map _indis) throws ParseException {
        String errStr = "";

        //change the HashMap to ArrayList to loop check the unique fam
        ArrayList<Family> marriage = new ArrayList<>();
        //iterator through Family
        for (Map.Entry<String, Family> entry : (Iterable<Map.Entry<String, Family>>) _Fams.entrySet()) {
            Family curFam = entry.getValue();
//            String curFamId = curFam.getId();
//            Date curFamMarriageDate = curFam.getMarried();
            marriage.add(curFam);
        }
        //check if any family marriage at same day
        for(int i = 0; i < marriage.size(); i++){
            Family curFam1 = marriage.get(i);
            for(int j = i+1; j < marriage.size(); j++){
                Family curFam2 = marriage.get(j);
                //the same marriage date
//                System.out.println(curFam1.getMarried()+" "+curFam2.getMarried());
                if(curFam1.getMarried().compareTo(curFam2.getMarried()) == 0){
                    //the same spouses by name:husband
//                    System.out.println(curFam1.getHusbandName()+" "+curFam2.getHusbandName());
                    if(curFam1.getHusbandName() ==curFam2.getHusbandName()){
                        errStr = "ERROR: FAMILY: US24: "+"Family ID: "+curFam1.getId()+" and "+curFam2.getId()+" with the same HUSBAND by name "+curFam1.getHusbandName()+" and the same marriage date: "+Formatdate.dateToString((curFam1.getMarried()));
                        this.ErrorInfo.add(errStr);
                    }
                    //the same spouses by name:wife
                    if(curFam1.getWifeName() == curFam2.getWifeName()){
                        errStr = "ERROR: FAMILY: US24: "+"Family ID: "+curFam1.getId()+" and "+curFam2.getId()+" with the same WIFE by name "+curFam1.getWifeName()+" and the same marriage date: "+Formatdate.dateToString((curFam1.getMarried()));
                        this.ErrorInfo.add(errStr);
                    }
                }
            }
        }

        return errStr;
    }
}
