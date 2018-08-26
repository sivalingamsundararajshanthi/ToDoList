/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

package edu.niu.cs.z1829451.assignment4;

/**
 * Created by ss siva on 4/26/2018.
 */

public class Check {

    private int id;
    private String item;

    public Check(int newId, String newItem){
        setId(newId);
        setItem(newItem);
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setItem(String newItem) {
        item = newItem;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }
}
