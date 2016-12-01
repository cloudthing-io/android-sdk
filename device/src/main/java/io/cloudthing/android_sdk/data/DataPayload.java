package io.cloudthing.android_sdk.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kleptoman on 29.11.16.
 */

public final class DataPayload {

    private static final String BODY_TEMPLATE = "{\"r\":[%s]}";
    private static final String DATA_OBJ_TEMPLATE = "{'k':'%s','v':%s}";

    private Map<String, String> data = new HashMap<>();

    public void clearData() {
        data.clear();
    }

    public void putData(String dataId, String dataValue) {
        data.put(dataId, dataValue);
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        int iter = 0;
        for (Map.Entry<String, String> dataEntry: data.entrySet()) {
            if (iter != 0) {
                sBuilder.append(',');
            }
            sBuilder.append(String.format(DATA_OBJ_TEMPLATE, dataEntry.getKey(), dataEntry.getValue()));
            iter++;
        }

        return String.format(BODY_TEMPLATE, sBuilder.toString());
    }
}
