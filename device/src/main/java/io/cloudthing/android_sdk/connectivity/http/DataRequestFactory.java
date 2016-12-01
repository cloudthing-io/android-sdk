package io.cloudthing.android_sdk.connectivity.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by kleptoman on 02.09.16.
 */
public class DataRequestFactory extends DeviceRequestFactory {

    public DataRequestFactory(Context ctx, String deviceId, String token, String tenant) {
        super(ctx, deviceId, token, tenant);
    }

    @Override
    public Request getRequest() {
        try {
            return new SimpleDataRequest(getUrl(), getRequestBody(), listener, errorListener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
