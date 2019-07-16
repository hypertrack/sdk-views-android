package com.hypertrack.views.quickstart;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hypertrack.sdk.views.DeviceUpdatesHandler;
import com.hypertrack.sdk.views.dao.Location;
import com.hypertrack.sdk.views.dao.StatusUpdate;
import com.hypertrack.sdk.views.dao.Trip;

class MyDeviceUpdatesHandler implements DeviceUpdatesHandler {
    private static final String TAG = "MyDeviceUpdatesHandler";
    @Override
    public void onLocationUpdateReceived(@NonNull Location location) {
        Log.d(TAG, "onLocationUpdateReceived: " + location);
    }

    @Override
    public void onBatteryStateUpdateReceived(int i) {

        Log.d(TAG, "onBatteryStateUpdateReceived: " + i);
    }

    @Override
    public void onStatusUpdateReceived(@NonNull StatusUpdate statusUpdate) {
        Log.d(TAG, "onStatusUpdateReceived: " + statusUpdate);
    }

    @Override
    public void onTripUpdateReceived(@NonNull Trip trip) {

        Log.d(TAG, "onTripUpdateReceived: " + trip);
    }

    @Override
    public void onError(Exception e, String deviceId) {

        Log.w(TAG, "onError: ", e);
    }

    @Override
    public void onCompleted(String deviceId) {

        Log.d(TAG, "onCompleted: " + deviceId );
    }
}
