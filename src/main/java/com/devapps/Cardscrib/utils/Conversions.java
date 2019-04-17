package com.devapps.Cardscrib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Ram on 7/27/2016.
 */
public class Conversions {

    private static Gson _Gson;

    public static Gson getGson() {
        if (_Gson == null) {
            _Gson = new GsonBuilder().disableHtmlEscaping().create();
        }
        return _Gson;
    }

    /**
     * Convert Object to Json String
     * @param object
     * @return
     */
    public static String ObjectToJson(Object object) {
        String result = "";
        try {
            result = getGson().toJson(object, object.getClass());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;
    }

    /**
     * Convert Json String to Object
     * @param json
     * @param type
     * @return
     */
    public static Object JsonToObject(String json, Type type) {
        Object object = null;
        try {
            object = getGson().fromJson(json, type);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return object;
    }

    /**
     * Convert String to Integer
     * @param value
     * @return
     */
    public static int StringToInt(String value) {
        int result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
}
