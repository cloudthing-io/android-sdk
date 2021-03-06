package io.cloudthing.android_sdk.connectivity.mqtt;

import android.content.Context;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import io.cloudthing.android_sdk.data.ICloudThingMessage;

/**
 * Created by kleptoman on 14.09.16.
 */
public class ClientWrapper {

    private static final int QUALITY_OF_SERVICE = 0;
    private static final boolean RETAINED = false;

    private final MqttClient client;
    private MqttConnectOptions connOpts;

    private final String tenant;
    private final String deviceId;
    private final String token;

    private MqttCallback callback;

    public ClientWrapper(String tenant, String deviceId, String token, Context context) {
        this.tenant = tenant;
        this.deviceId = deviceId;
        this.token = token;
        this.client = ClientFactory.getInstance().getClient(context, tenant);
    }

    public void connect() throws MqttException {
        generateOptions();
        this.client.connect(this.connOpts);
    }

    public void disconnect() throws MqttException {
        this.client.disconnect();
    }

    public void subscribe(String topic) throws MqttException {
        this.client.subscribe(topic);
    }

    public void unsubscribe(String topic) throws MqttException {
        this.client.unsubscribe(topic);
    }

    public void publish(String topic, ICloudThingMessage message) throws MqttException {
        this.client.publish(topic, message.toBytes(), QUALITY_OF_SERVICE, RETAINED);
    }

    public boolean isConnected() {
        return this.client.isConnected();
    }

    private void generateOptions() {
        connOpts = new MqttConnectOptions();

        connOpts.setUserName(this.tenant + ":" + deviceId);
        connOpts.setPassword(token.toCharArray());
        connOpts.setCleanSession(true);
    }

    public MqttCallback getCallback() {
        return callback;
    }

    public void setCallback(MqttCallback callback) {
        this.callback = callback;
        this.client.setCallback(callback);
    }
}
