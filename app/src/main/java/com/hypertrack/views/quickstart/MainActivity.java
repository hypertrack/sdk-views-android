package com.hypertrack.views.quickstart;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hypertrack.sdk.views.DeviceUpdatesHandler;
import com.hypertrack.sdk.views.HyperTrackViews;
import com.hypertrack.sdk.views.dao.Location;
import com.hypertrack.sdk.views.dao.MovementStatus;
import com.hypertrack.sdk.views.dao.StatusUpdate;
import com.hypertrack.sdk.views.dao.Trip;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PUBLISHABLE_KEY = null; // declare your key here
    private HyperTrackViews mHyperTrackData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        mHyperTrackData = HyperTrackViews.getInstance(this, PUBLISHABLE_KEY);
        mHyperTrackData.getDeviceMovementStatus("A4005505-D469-481E-AD2E-123456789AB",
                new Consumer<MovementStatus>() {
                    @Override
                    public void accept(MovementStatus movementStatus) {
                        Log.d(TAG, "Got movement status data " + movementStatus);
                    }
                });
        mHyperTrackData.subscribeToDeviceUpdates("A4005505-D469-481E-AD2E-123456789AB",
                new DeviceUpdatesHandler() {
                    @Override
                    public void onLocationUpdateReceived(@NonNull Location location) {
                        Log.d(TAG, "onLocationUpdateReceived: " + location);
                    }

                    @Override
                    public void onBatteryStateUpdateReceived(@MovementStatus.BatteryState int i) {
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
                        Log.d(TAG, "onCompleted: " + deviceId);

                    }
                }
        );

    }

    @Override
    protected void onPause() {
        super.onPause();

        mHyperTrackData.stopAllUpdates();
    }

}
