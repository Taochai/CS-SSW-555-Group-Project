package tools;

import objects.Individual;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class us31 {
    private Set<String> AllLivingSingle;

    public us31() {
        this.AllLivingSingle = new HashSet<>();
    }

    public Set<String> getError() {
        return this.AllLivingSingle;
    }

    public void US31(Individual indi) throws ParseException {
        String messagesStr;
        String indiID = indi.getId();
        String indiName = indi.getName();
        if(indi.getAlive() == true && indi.getSpouse().isEmpty() == true){
            if(CalculateAge.getAge(indi.getBirthday()) > 30) {
                messagesStr = "\t" + indiID + ": " + indiName + " is a living single person.";
                this.AllLivingSingle.add(messagesStr);
            }
        }
    }
}
