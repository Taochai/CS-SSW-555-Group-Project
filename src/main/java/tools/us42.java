package tools;

import objects.Family;
import objects.Individual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class us42 {//    List line numbers from GEDCOM source file when reporting errors
    Set<String> errInfo;

    public us42() {
        this.errInfo = new HashSet<>();
    }

    public Set<String> getError() {
        return this.errInfo;
    }

    public void US42(Set<String> errDate)  {
        for(String errDateStr: errDate){
            String tmpStr = "US42: This date is illegal:" + errDateStr;
            this.errInfo.add(tmpStr);
        }
    }
}
