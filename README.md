# Android Views SDK

The Android Views SDK is used for getting live location and movement data for devices and trips directly to your Android app. This module subscribes to HyperTrack's GraphQL server end points to get data streams and then renders it in useful callbacks for app developers to build beautiful tracking experiences. This helps developers creating live location views go serverless. Their app users can directly get data securely and privately from the HyperTrack servers.
* [Integrate the Views SDK](#integrate-the-views-sdk)
* [FAQ](#frequently-asked-questions)

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
    implementation 'com.hypertrack:hypertrack-views:0.3.1'
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
                                  });
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


## Frequently Asked Questions
- [What API levels (Android versions) are supported](#supported-versions)
- [How do I get the DeviceID](#get-device-id)
- [The views SDK doesn't give me views!](#not-only-views-in-views)
- [How to manage multiple devices?](#track-multiple-devices)
- [How do I subscribe to a specific Trip via onTripUpdateReceived?
   Will I get an update for creation, re-calculation, and delay of Trips?](#trips-tracking)
- [What does 0 `BatteryState` means?](#battery-state-constants)
- [What's the library size?](#sdk-size)

#### Supported versions
Currently we do support all of the Android versions starting from API 19 (Android 4.4 Kit Kat)


#### Get device id
Device id is a way to reference particular device, that is tracked by HyperTrack SDK.
Generally, it's your application logic which devices should be displayed and how to filter/prioritize/select
the one you need to track. If you have doubts regarding how to integrate HyperTrack SDK - look into
corresponding [quickstart section](https://github.com/hypertrack/quickstart-android#integrate-the-sdk).
Once integration is done, device id can be obtained via `HyperTrack.getDevcieid()` call on initialized SDK.

#### Not Only Views in Views
Although view is associated with something, that should be visual, here it means view on data, that
you retrieve from [HyperTrack SDK](https://github.com/hypertrack/quickstart-android). Tricky part is
that application success nowadays is highly dependends on quality of user experience, that is hardly
achievable without fine-grained tuning of UI elements. So using real-time data pushed to your device,
you can build exactly the same views you really want. Anyway, ready to use UI elements will be added
to upcoming library releases.

#### Track multiple devices
You can subscribe to more than one device by executing `subscribeToDeviceUpdates` multiple times with
different device ids. Subscription updates consumer can be the same (e.g. map fragment), since each
update has device id field for identification.

#### Trips Tracking
Each trip has a device id, for which it was created, pass that id to `subscribeToDeviceUpdates` and
you'll receive all the trip recalculations and delays into `onTripUpdateReceived` callback.


#### Battery State Constants
Using enums in Android is [discouraged](https://developer.android.com/topic/performance/reduce-apk-size#remove-enums),
so we're using numerical values to represent BatteryState. Check out
[reference](http://hypertrack-views-javadoc.s3-website-us-west-2.amazonaws.com/constant-values.html#com.hypertrack.sdk.views.dao.MovementStatus.BATTERY_NORMAL)
for exact values meaning.

#### SDK size

SDK _aar_ file size is 211.2Kb. But due to transitive dependencies, integration sample apk size increment is slightly less than 1.3Mb (from 1619142 bytes to 2972068 bytes).
Enabling minification results in 1.62Mb apk (vs 1.05Mb bytes for minified binary without library), so size increment is 585Kb. So 1.3Mb and 0.57Mb are upper bounds,
but real size increment is going to be smaller, if some of transitive dependencies (e.g. support library or gson) are already used in your app.
So apk size increment mainly depends on code minification and is somewhere between limits shown below:

| minification  |    Upper bound  |   lower bound  |
|---------------|-----------------|----------------|
|    disabled   |      1.3Mb      |     0.211Mb    |
|    enabled    |      0.57Mb     |       n/a      |