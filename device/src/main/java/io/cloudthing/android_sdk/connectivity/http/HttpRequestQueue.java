package io.cloudthing.android_sdk.connectivity.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by kleptoman on 02.09.16.
 */
public class HttpRequestQueue {

    private static HttpRequestQueue instance;
    private static Context ctx;

    private RequestQueue requestQueue;

    public static HttpRequestQueue getInstance(Context context) {
        synchronized (HttpRequestQueue.class) {
            if (instance == null) {
                instance = new HttpRequestQueue(context);
            }
        }
        return instance;
    }

    private HttpRequestQueue(Context context) {
        ctx = context;
    }

    public RequestQueue getRequestQueue() {
        synchronized (HttpRequestQueue.class) {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }
        }
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        synchronized (HttpRequestQueue.class) {
            this.requestQueue = requestQueue;
        }
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
