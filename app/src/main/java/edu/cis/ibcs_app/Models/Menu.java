package edu.cis.ibcs_app.Models;
import java.util.ArrayList;

public class Menu {
        ArrayList<MenuItem> eatriumItems;
        String adminID;
        public Menu() {
            eatriumItems = new ArrayList<MenuItem>();
        }
        public Menu(ArrayList<MenuItem> eatriumItems, String adminID){
            this.eatriumItems = eatriumItems;
            this.adminID = adminID;
        }
        public ArrayList<MenuItem> getEatriumItems() {
            return eatriumItems;
        }
        public String getAdminID() {
            return adminID;
        }
        public void setEatriumItems(ArrayList<MenuItem> eatriumItems) {
            this.eatriumItems = eatriumItems;
        }
        public void setAdminID(String adminID) {
            this.adminID = adminID;
        }
        public void addEatriumItems(MenuItem newItem){
            eatriumItems.add(newItem);
        }
        @Override
        public String toString() {
            return "Menu{" +
                    "eatriumItems=" + eatriumItems +
                    ", adminID='" + adminID + '\'' +
                    '}';
        }
}
