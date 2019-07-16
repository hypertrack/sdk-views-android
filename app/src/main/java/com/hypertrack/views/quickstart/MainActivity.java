package com.hypertrack.views.quickstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hypertrack.sdk.views.HyperTrackData;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PUBLISHABLE_KEY = null; // declare your key here
    private HyperTrackData mHyperTrackData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        mHyperTrackData = HyperTrackData.getInstance(this, PUBLISHABLE_KEY);
        mHyperTrackData.subscribeToDeviceUpdates("A4005505-D469-481E-AD2E-123456789AB", new MyDeviceUpdatesHandler());
        mHyperTrackData.getDeviceMovementStatus("A4005505-D469-481E-AD2E-123456789AB", new MovementStatusConsumer());

    }

    @Override
    protected void onPause() {
        super.onPause();

        mHyperTrackData.stopAllUpdates();
    }

}
