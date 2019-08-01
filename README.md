# Android Views SDK

The Android Views SDK is used for getting live location and movement data for devices and trips directly to your Android app. This module subscribes to HyperTrack's GraphQL server end points to get data streams and then renders it in useful callbacks for app developers to build beautiful tracking experiences. This helps developers creating live location views go serverless. Their app users can directly get data securely and privately from the HyperTrack servers.
## [Integrate the Views SDK](integrate-the-views-sdk)
## [FAQ](faq)

## Integrate the Views SDK
 - [Add Views SDK](#step-1-add-views-sdk)
 - [Instantiate](#step-2-instantiate)
 - [Get state](#step-3-get-state)
 - [Subscribe to updates](#step-4-subscribe-to-updates)

#### Step 1. Add Views SDK
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

//Add HyperTrack Views SDK as a dependency
dependencies {
    implementation 'com.hypertrack:hypertrack-views:0.2.0'
    ...
}
```

#### Step 2. Instantiate
Pass `Context` reference to get SDK instance.
```
   HyperTrackViews hypertrackView = HyperTrackViews.getInstance(this, PUBLISHABLE_KEY);
```

#### Step 3. Get state
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

#### Step 4. Subscribe to updates
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


## FAQ
- [What API levels (Android versions) are supported](#supported-versions)


#### Supported versions
Currently we do support all of the Android versions starting from API 19 (Android 4.4 Kit Kat)
