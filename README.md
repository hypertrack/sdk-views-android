# HyperTrack Quickstart for Android Views SDK

## Integrate the Vies SDK
 - [Add Hypertrack SDK](#step-1-add-hypertrack-views-sdk)
 - [Instantiate SDK](#step-2-instantiate-sdk)
 - [Get state](#step-3-get-state)
 - [Subscribe to updates](#step-4-subscribe-to-updates)

#### Step 1. Add Hypertrack Views SDK
Add following lines to your applications `build.gradle`:
```
// Import the SDK within your repositories block
repositories {
    maven {
        name 'hypertrack'
        url 'http://m2.hypertrack.com'
    }
    ...
}

//Add HyperTrack Vies SDK as a dependency
dependencies {
    implementation 'com.hypertrack:hypertrack-views:0.1.0'
    ...
}
```

#### Step 2. Instantiate SDK.
Pass `Context` reference to get SDK instance.
```
   HyperTrackData hypertrackView = HyperTrackData.getInstance(this, PUBLISHABLE_KEY);
```

#### Step 3. Get state.
Get current state of tracked device
```
   hypertrackView.getDeviceMovementStatus(deviceId,
                                  new Consumer<MovementStatus>() {
                                      @Override
                                      public void accept(MovementStatus movementStatus) {
                                          Log.d(TAG, "Got movement status data " + movementStatus);
                                      }
                                  });;
```
In callback, that you pass as a second argument, you'll receive [MovementStatus](http://hypertrack-views-javadoc.s3-website-us-west-2.amazonaws.com)
object that encapsulates various data describing device state.
Check out [docs](http://hypertrack-views-javadoc.s3-website-us-west-2.amazonaws.com) for data that is available.

#### Step 4. Subscribe to updates.
You can receive device state changes updates
```
   hypertrackView.subscribeToDeviceUpdates(deviceId,
   new DeviceUpdatesHandler() {
           @Override
           public void onLocationUpdateReceived(@NonNull Location location) {
               Log.d(TAG, "onLocationUpdateReceived: " + location);
           }

           @Override
           public void onBatteryStateUpdateReceived(@BatteryState int i) {
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
```
Likewise in case of one-time status query you'll receive updates object in a listener, that you pass
into this method. Check out [documentation](http://hypertrack-views-javadoc.s3-website-us-west-2.amazonaws.com)
for available update object properties.
Make sure you've stop updates, once you're done since you can end up with leaked websocket otherwise.

```
   hypertrackView.stopAllUpdates();
```

#### You are all set