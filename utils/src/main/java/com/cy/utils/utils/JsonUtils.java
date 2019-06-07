package com.cy.utils.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/01/05 16:30
 * desc：
 * ************************************************************
 */

public class JsonUtils  {


    private JSONObject jsonObject;

    public JsonUtils(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public String getString(String key, String def) {
        return (String) get(key, def);

    }

    public boolean getBoolean(String key, boolean def) {
        return (boolean) get(key, def);

    }

    public int getInt(String key, int def) {
        return (int) get(key, def);

    }

    public double getDouble(String key, double def) {
        return (double) get(key, def);

    }

    public long getLong(String key, long def) {
        return (long) get(key, def);
    }

    public JSONArray getJSONArray(String key, JSONArray def) {
        return (JSONArray) get(key, def);

    }

    public JSONObject getJSONObject(String key, JSONObject def) {
        return (JSONObject) get(key, def);

    }

    public Object get(String key, Object def) {
        if (jsonObject==null) return def;
        Object obj = null;
        try {
        	obj = jsonObject.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("JsonUtils","no"+key+"or value");
        }
        return obj==null?def:obj;
    }


}
