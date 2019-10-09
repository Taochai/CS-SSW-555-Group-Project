/*
 * @Description: This script contains read gedcom file; set indi & fam attributes; print indi & fam table functions.
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 13:18:32
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 13:18:32
 */
package tools;

import objects.Family;
import objects.Individual;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class readGedcomFile {
    Map<String, Individual> Indis;
    Map<String, Family> Fams;
    Boolean ifBIRT = false;
    Boolean ifDEAT = false;
    Boolean ifMARR = false;
    Boolean ifDIV = false;
    Boolean ifIndi = false;
    Boolean ifFam = false;
    public readGedcomFile(){
        this.Indis = new HashMap<>();
        this.Fams = new HashMap<>();
    }
    Map<String,String> tagLevelMap = new HashMap<String, String>(){
        {
            put("INDI","0");
            put("NAME","1");
            put("SEX","1");
            put("BIRT","1");
            put("DEAT","1");
            put("FAMC","1");
            put("FAMS","1");
            put("FAM","0");
            put("MARR","1");
            put("HUSB","1");
            put("WIFE","1");
            put("CHIL","1");
            put("DIV","1");
            put("DATE","2");
            put("HEAD","0");
            put("TRLR","0");
            put("NOTE","0");
        }
    };
    public void readFile(String file) throws IOException, ParseException {
        FileInputStream fileInput = new FileInputStream(file);
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(fileInput));
        List<String> outputStrArr = new ArrayList<>();

        String inputLine ;
        Individual tmpIndi = null;
        Family tmpFam = null;


        while((inputLine = bufferRead.readLine())!=null){
            inputLine = inputLine.replace("@","");
            String[] strArr = inputLine.split("\\s");
            String level = strArr[0];
            String tag = null;
            String arguments = null;
            if(strArr.length == 3){
                if(level.equals("0")){
                    if((strArr[1].equals("INDI") || strArr[1].equals("FAM"))){
                        continue;
                    }
                    if((strArr[2].equals("INDI") || strArr[2].equals("FAM"))){
                        tag = strArr[2];
                        arguments = strArr[1];
                        if(tag.equals("INDI")){
                            this.ifFam = false;
                            this.ifIndi = true;
                            if(tmpIndi!=null){
                                this.Indis.put(tmpIndi.getId(),tmpIndi);
                            }
                            tmpIndi = new Individual();
                            tmpIndi.setId(arguments);
                        }
                        if(tag.equals("FAM")){
                            this.ifFam = true;
                            this.ifIndi = false;
                            if(tmpFam!=null){
                                this.Fams.put(tmpFam.getId(),tmpFam);
                            }
                            tmpFam = new Family();
                            tmpFam.setId(arguments);
                        }
                        continue;
                    }

                }
            }
            if(strArr.length==2){
                tag = strArr[1];
            }
            if(strArr.length>2){
                tag = strArr[1];
                if(strArr.length==3){
                    arguments = strArr[2];
                }
                else {
                    arguments = strArr[2];
                    for(int i=3;i<strArr.length;i++){
                        arguments +=" "+strArr[i];
                    }
                }
            }
            if(tagLevelMap.containsKey(tag) && tagLevelMap.get(tag).equals(level) ){
                if(ifIndi){
                    if(tag.equals("BIRT")){
                        this.ifBIRT = true;
                    }
                    if(tag.equals("DEAT")){
                        this.ifDEAT = true;
                    }
                    setIndiAttributes(tag,arguments,tmpIndi);
                }
                if(ifFam){
                    if(tag.equals("MARR")){
                        this.ifMARR = true;
                    }
                    if(tag.equals("DIV")){
                        this.ifDIV = true;
                    }
                    setFamAttributes(tag,arguments,tmpFam);
                }

            }
        }
        if(tmpIndi!=null) this.Indis.put(tmpIndi.getId(),tmpIndi);
        if(tmpFam!=null)  this.Fams.put(tmpFam.getId(),tmpFam);

    }
    public void setIndiAttributes(String tag, String arguments, Individual indi) throws ParseException {
        if(tag.equals("NAME")){
            arguments.replace(" ","/");
            indi.setName(arguments);
        }
        else if(tag.equals("SEX")){
            indi.setGender(arguments.charAt(0));
        }
        else if(tag.equals("DATE")){
            if(this.ifBIRT){
                Date date = Formatdate.stringtodate(arguments);
                indi.setBirthday(date);   //// need function transform date to DATE type;
                this.ifBIRT = false;
            }
            if(this.ifDEAT){
                Date date = Formatdate.stringtodate(arguments);
                indi.setDeath(date);   //// need function transform date to DATE type;
                this.ifDEAT = false;
            }
        }
        else if(tag.equals("FAMC")){
            // arguments is Family_ID
            indi.addChild(arguments);
        }
        else if(tag.equals("FAMS")){
            indi.addSpouse(arguments);
        }
    }
    public void setFamAttributes(String tag, String arguments, Family fam) throws ParseException {
        if(tag.equals("HUSB")){
            fam.setHusbandID(arguments);
        }
        else if(tag.equals("WIFE")){
            fam.setWifeID(arguments);
        }
        else if(tag.equals("CHIL")){
            fam.addChildren(arguments);
        }
        else if(tag.equals("DATE")){
            if(this.ifMARR){
                Date date = Formatdate.stringtodate(arguments);
                fam.setMarried(date);//// need function transform date to DATE type;
                this.ifMARR = false;
            }
            if(this.ifDIV){
                Date date = Formatdate.stringtodate(arguments);
                fam.setDivorced(date);//// need function transform date to DATE type;
                this.ifDIV = false;
            }
        }
    }
    public Map printIndi() throws Exception {
        ConsoleTable tI = new ConsoleTable(9, true);
        tI.appendRow();
        tI.appendColum("ID").appendColum("Name").appendColum("Gender").appendColum("Birthday").appendColum("Age").appendColum("Alive").appendColum("Death").appendColum("Child").appendColum("Spouse");
        Iterator<Map.Entry<String, Individual>> entries = this.Indis.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Individual> entry = entries.next();
            Individual curindi = entry.getValue();
            curindi.setAlive();
            String child = "";
            for(String famC :curindi.getChild()){
                child += famC+",";
            }
            String spouse = "";
            for(String famS :curindi.getSpouse()){
                spouse +=famS+",";
            }
            tI.appendRow();
            tI.appendColum(curindi.getId()).appendColum(curindi.getName()).appendColum(curindi.getGender()).appendColum(Formatdate.dateToString(curindi.getBirthday())).appendColum(CalculateAge.getAge(curindi.getBirthday())).appendColum(curindi.getAlive()).appendColum(Formatdate.dateToString(curindi.getDeath())).appendColum(child).appendColum(spouse);
        }
        System.out.println("Individuals:");
        System.out.println(tI.toString());
        return this.Indis;
    }
    public Map printFam() throws Exception {
        ConsoleTable tF = new ConsoleTable(8, true);

        tF.appendRow();
        tF.appendColum("ID").appendColum("Married").appendColum("Divorced").appendColum("Husband ID").appendColum("Husband Name").appendColum("Wife ID").appendColum("Wife Name").appendColum("Children");

        Iterator<Map.Entry<String, Family>> entries1 = this.Fams.entrySet().iterator();
        while(entries1.hasNext()){
            Map.Entry<String, Family> entry = entries1.next();
            Family curFam = entry.getValue();

            String child = "";
            for(String CId :curFam.getChildren()){
                child += CId+",";
            }

            tF.appendRow();
            tF.appendColum(curFam.getId()).appendColum(Formatdate.dateToString(curFam.getMarried())).appendColum(Formatdate.dateToString(curFam.getDivorced())).appendColum(curFam.getHusbandID()).appendColum(this.Indis.get(curFam.getHusbandID()).getName()).appendColum(curFam.getWifeID()).appendColum(this.Indis.get(curFam.getWifeID()).getName()).appendColum(child);
        }
        System.out.println("Family:");
        System.out.println(tF.toString());
        return this.Fams;
    }
}
