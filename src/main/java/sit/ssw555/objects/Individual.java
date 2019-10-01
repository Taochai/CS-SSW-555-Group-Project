/*
 * @Description: Individual class
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 16:31:12
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-09-30 20:44:29
 */
package sit.ssw555.objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

public class Individual {
    private String id;
    private String Name;
    private char Gender;
    private Date Birthday;
    private Boolean Alive;
    private Date Death;
    private Set<String> Child;
    private Set<String> Spouse;

    public Individual() {
        this.Birthday = null;
        this.Death = null;
        this.Child = new HashSet<String>();
        this.Spouse = new HashSet<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public char getGender() {
        return Gender;
    }

    public void setGender(char gender) {
        this.Gender = gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        this.Birthday = birthday;
    }

    public Boolean getAlive() {
        return Alive;
    }

    public void setAlive() {
        if (this.Death == null) {
            this.Alive = true;
        } else {
            this.Alive = false;
        }

    }

    public Date getDeath() {
        return Death;
    }

    public void setDeath(Date death) {
        this.Death = death;
    }

    public Set<String> getChild() {
        return Child;
    }

    public void setChild(HashSet<String> child) {
        this.Child = child;
    }

    public void addChild(String child) {
        this.Child.add(child);
    }

    public Set<String> getSpouse() {
        return Spouse;
    }

    public void addSpouse(String spouse) {
        this.Spouse.add(spouse);
    }

    @Override
    public String toString() {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String deadDate = (Death == null) ? "NA" : fm.format(Death).toString();
        String birDate = (Birthday == null) ? "NA" : fm.format(Birthday).toString();
        setAlive();
        return "Individual id=" + id + "\t| Name=" + Name + "\t| Gender=" + Gender + "\t| Birthday=" + birDate
                + "\t| Alive=" + Alive + "\t| Death=" + deadDate + "\t| Child=" + Child + "\t| Spouse=" + Spouse;
    }
}
