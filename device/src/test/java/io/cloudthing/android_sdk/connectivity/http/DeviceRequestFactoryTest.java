package io.cloudthing.android_sdk.connectivity.http;

import android.content.Context;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by kleptoman on 02.12.16.
 */
@PrepareForTest({Base64.class, JSONObject.class})
@RunWith(PowerMockRunner.class)
public class DeviceRequestFactoryTest {

    private Context context;
    private static final String TENANT = "tenant";
    private static final String DEVICE_ID = "deviceId";
    private static final String TOKEN = "token";

    @Before
    public void SetUp() {
        context = mock(Context.class);
    }

    @Test
    public void initListeners() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        requestFactory.errorListener = null;
        requestFactory.listener = null;
        requestFactory.initListeners();

        assertNotNull(requestFactory.listener);
        assertNotNull(requestFactory.errorListener);
    }

    @Test
    public void generateHeaders() throws Exception {
        PowerMockito.mockStatic(Base64.class);
        Mockito.when(Base64.encodeToString((DEVICE_ID + ":" + TOKEN).getBytes(), Base64.DEFAULT)).thenReturn("ZGV2aWNlSWQ6dG9rZW4=");

        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        Map<String, String> headers = requestFactory.generateHeaders();
        assertEquals(2l, headers.size());
        assertTrue(headers.containsKey("Content-Type"));
        assertTrue(headers.containsKey("Authorization"));
        assertEquals("application/json", headers.get("Content-Type"));
        assertEquals("Basic ZGV2aWNlSWQ6dG9rZW4=", headers.get("Authorization"));
    }

    @Test
    public void getUrl() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        assertEquals("https://tenant.cloudthing.io:444/v1/deviceId/data", requestFactory.getUrl());
    }

    @Test
    public void setListener() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {

            }
        };
        requestFactory.setListener(listener);
        assertEquals(listener, requestFactory.listener);
    }

    @Test
    public void setErrorListener() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        requestFactory.setErrorListener(errorListener);
        assertEquals(errorListener, requestFactory.errorListener);
    }

    @Test
    public void getRequestBody() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        requestFactory.putData("dataId", "dataValue");
        JSONObject json = requestFactory.getRequestBody();
        assertNotNull(json);
    }

    @Test(expected = IllegalStateException.class)
    public void clearData() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        requestFactory.putData("dataId", "dataValue");

        requestFactory.clearData();
        requestFactory.getRequestBody();
    }

    @Test
    public void putData() throws Exception {
        DeviceRequestFactory requestFactory = new ImplDeviceRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
        requestFactory.putData("dataId", "dataValue");
        Map<String, String> data = requestFactory.getData();
        assertEquals(1l, data.size());
        assertTrue(data.containsKey("dataId"));
        assertEquals("dataValue", data.get("dataId"));

        requestFactory.putData("dataId", "newDataValue");
        assertEquals(1l, data.size());
        assertTrue(data.containsKey("dataId"));
        assertEquals("newDataValue", data.get("dataId"));

        requestFactory.putData("newDataId", "dataValue");
        assertEquals(2l, data.size());
        assertTrue(data.containsKey("dataId"));
        assertTrue(data.containsKey("newDataId"));
        assertEquals("dataValue", data.get("newDataId"));


    }

    private class ImplDeviceRequestFactory extends DeviceRequestFactory {

        protected ImplDeviceRequestFactory(Context ctx, String deviceId, String token, String tenant) {
            super(ctx, deviceId, token, tenant);
        }

        @Override
        public Request getRequest() {
            return null;
        }
    }

}