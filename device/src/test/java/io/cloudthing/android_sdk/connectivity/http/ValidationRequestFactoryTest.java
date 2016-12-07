package io.cloudthing.android_sdk.connectivity.http;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.VolleyLog;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;

/**
 * Created by kleptoman on 02.12.16.
 */
@PrepareForTest({Base64.class, JSONObject.class, Log.class, VolleyLog.class})
@RunWith(PowerMockRunner.class)
public class ValidationRequestFactoryTest {

    private Context context;
    private static final String TENANT = "tenant";
    private static final String DEVICE_ID = "deviceId";
    private static final String TOKEN = "token";

    @Before
    public void SetUp() {
        context = mock(Context.class);
    }

    //TODO resolve problems with VolleyLog (different test setup?)
    @Test
    public void getRequest() throws Exception {
//        ValidationRequestFactory requestFactory = new ValidationRequestFactory(context, DEVICE_ID, TOKEN, TENANT);
//        requestFactory.listener = null;
//
//        Request request = requestFactory.getRequest();
//        assertNotNull(requestFactory.listener);
//        assertNotNull(request);
//        assertEquals(Request.Method.POST, request.getMethod());
//        assertEquals("https://tenant.cloudthing.io:444/v1/deviceId/data", request.getUrl());
//
//        PowerMockito.mockStatic(Base64.class);
//        Mockito.when(Base64.encodeToString((DEVICE_ID + ":" + TOKEN).getBytes(), Base64.DEFAULT)).thenReturn("ZGV2aWNlSWQ6dG9rZW4=");
//
//        Map<String, String> headers = request.getHeaders();
//        assertEquals(2l, headers.size());
//        assertTrue(headers.containsKey("Content-Type"));
//        assertTrue(headers.containsKey("Authorization"));
//        assertEquals("application/json", headers.get("Content-Type"));
//        assertEquals("Basic ZGV2aWNlSWQ6dG9rZW4=", headers.get("Authorization"));

    }

}