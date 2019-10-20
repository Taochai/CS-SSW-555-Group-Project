/*
 * @Description: Family class
 * @Author: Tao Chai, Zhe Sun, Yining Wen, Jiaxian Xing, Ge Chang
 * @Github: https://github.com/Taochai/SSW-CS-555-Group-project
 * @Date: 2019-09-30 16:31:26
 * @LastEditors: Zhe Sun
 * @LastEditTime: 2019-10-19 22:12:17
 */
package objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

public class Family {
        private String id;
        private Date Married;
        private Date Divorced;
        private String HusbandID;
        private String HusbandName;
        private String WifeID;
        private String WifeName;
        private Set<String> Children;

        public Family() {
            this.Married = null;
            this.Divorced = null;
            this.Children = new HashSet<>();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getMarried() {
            return Married;
        }

        public void setMarried(Date married) {
            this.Married = married;
        }

        public Date getDivorced() {
            return Divorced;
        }

        public void setDivorced(Date divorced) {
            this.Divorced = divorced;
        }

        public String getHusbandID() {
            return HusbandID;
        }

        public void setHusbandID(String husbandID) {
            this.HusbandID = husbandID;
        }

        public String getHusbandName() {
            return HusbandName;
        }

        public void setHusbandName(String husbandName) {
            this.HusbandName = husbandName;
        }

        public String getWifeID() {
            return WifeID;
        }

        public void setWifeID(String wifeID) {
            this.WifeID = wifeID;
        }

        public String getWifeName() {
            return WifeName;
        }

        public void setWifeName(String wifeName) {
            this.WifeName = wifeName;
        }

        public Set<String> getChildren() {
            return Children;
        }

        public void setChildren(HashSet<String> _children) {
            this.Children = _children;
        }

        public void addChildren(String children) {
            this.Children.add(children);
        }
    public String getFatherLastName(){

//        System.out.println(this.getHusbandID()+"******"+this.HusbandName);
        String[] nameArr = this.HusbandName.split(" ");
//        System.out.println(nameArr[nameArr.length-1]);
        return nameArr[nameArr.length-1];
    }
        @Override
        public String toString() {
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            String married_date = (Married == null) ? "NA" : fm.format(Married);
            String divorced_date = (Divorced == null) ? "NA" : fm.format(Divorced);
            return "Family id=" + id + "\t| Married=" + married_date + "\t| Divorced=" + divorced_date + "\t| HusbandID="
                    + HusbandID + "\t| HusbandName=" + HusbandName + "\t| WifeID=" + WifeID + "\t| WifeName=" + WifeName
                    + "\t| Children=" + Children;
        }

}
