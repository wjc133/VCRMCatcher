package com.elite.tools.catcher.core.parse;

import com.elite.tools.catcher.core.domain.PhoneData;
import com.elite.tools.catcher.core.domain.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by df on 16/5/15.
 */
public class JsonParser {
    private static Gson gson = new Gson();

    public static ServerResponse getServerResponse(String jsonStr) {
        return gson.fromJson(jsonStr, ServerResponse.class);
    }

    public static List<PhoneData> getPhoneDatas(String dataStr) {
        return gson.fromJson(dataStr, new TypeToken<List<PhoneData>>() {
        }.getType());
    }
}
