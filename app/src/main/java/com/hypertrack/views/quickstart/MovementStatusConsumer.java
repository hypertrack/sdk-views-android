package com.hypertrack.views.quickstart;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.hypertrack.sdk.views.dao.MovementStatus;

class MovementStatusConsumer implements Consumer<MovementStatus> {
    private static final String TAG = "MovementStatusConsumer";
    @Override
    public void accept(MovementStatus movementStatus) {
        Log.d(TAG, "accept: " + movementStatus);
    }
}
