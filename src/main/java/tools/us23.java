package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.util.*;

public class us23 {
    private Set<String> ErrorInfo;

    public us23(){
        ErrorInfo = new HashSet<>();
    }

    public Set<String> getError(){
        return this.ErrorInfo;
    }

    //US14 No more than five siblings should be born at the same time (Jiaxian Xing)
    public String US23(Map<String, Individual> _indis) throws ParseException {
        String errStr = "";
        List<List<String>> duplicateID = new ArrayList<>();
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();

        while(entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            List<String> newL = ifDuplicate(_indis,entry.getValue());
            if(newL.size()!=0){
                duplicateID.add(newL);
            }
        }
        for(int i=0;i<duplicateID.size();i++){
            errStr = "";
            for(int j=0;j<duplicateID.get(i).size();j++){
                errStr += duplicateID.get(i).get(j)+" ";
            }
            errStr = "ERROR: INDIVIDUAL: US23: " + errStr +" have same Name & Birthday.";
            this.ErrorInfo.add(errStr);
        }

        return errStr;
    }
    public List ifDuplicate(Map<String, Individual> _indis,Individual _indi){
        List<String> l = new ArrayList<>();
        Iterator<Map.Entry<String, Individual>> entries = _indis.entrySet().iterator();

        while(entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            if(entry.getValue().getName().equals(_indi.getName()) && entry.getValue().getBirthday().equals(_indi.getBirthday()) && !entry.getValue().getId().equals(_indi.getId()) ){

                l.add(entry.getKey());
            }
        }
        return l;
    }

}

