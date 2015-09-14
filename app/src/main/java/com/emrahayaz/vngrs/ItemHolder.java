package com.emrahayaz.vngrs;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by poseydon on 13.09.2015.
 */
public class ItemHolder {
    public String name;
    public String date;
    public String id;
    public String address;

    public ItemHolder(JSONObject jsonObject){

        try {
            this.name=jsonObject.getString("name");
            this.id=jsonObject.getString("id");
            this.address=jsonObject.getJSONObject("location").getString("address");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ItemHolder(String name){
        this.name=name;
    }
}
