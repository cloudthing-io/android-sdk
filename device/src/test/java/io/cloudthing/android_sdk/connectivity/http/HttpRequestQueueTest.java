package io.cloudthing.android_sdk.connectivity.http;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created by kleptoman on 02.12.16.
 */
@RunWith(JUnit4.class)
public class HttpRequestQueueTest {

    private Context context;

    @Before
    public void SetUp() {
        context = mock(Context.class);
    }

    @Test
    public void getInstance() throws Exception {
        HttpRequestQueue queue = HttpRequestQueue.getInstance(context);
        assertNotNull(queue);

        assertEquals(queue, HttpRequestQueue.getInstance(context));
    }

    //TODO need to do better implementation of HttpRequestQueue, so we can mock Network for Volley
    @Test
    public void getRequestQueue() throws Exception {

    }

    //TODO ditto
    @Test
    public void setRequestQueue() throws Exception {

    }

    //TODO ditto
    @Test
    public void addToRequestQueue() throws Exception {

    }

}