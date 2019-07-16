# Android Views SDK

The Android Views SDK is used for getting live location and movement data for devices and trips directly to the app via HyperTrack's GraphQL server end point. This way, developers creating live location views using HyperTrack data can go serverless and their app users can directly get data securely and privately from the HyperTrack servers.

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
    implementation 'com.hypertrack:hypertrack-views:0.1.0'
    ...
}
```

#### Step 2. Instantiate
Pass `Context` reference to get SDK instance.
```
   HyperTrackData hypertrackView = HyperTrackData.getInstance(this, PUBLISHABLE_KEY);
```

#### Step 3. Get state
Get current state of tracked device
```
   hypertrackView.getDeviceMovementStatus(deviceId, MyMovementStatusConsumer.getInstance());
```

#### Step 4. Subscribe to updates
You can receive device state changes updates
```
   hypertrackView.subscribeToDeviceUpdates(deviceId, MyDeviceUpdatesHandler.getInstance());
```
Make sure you've stop updates, once you're done since you can end up with leaked websocket otherwise.

```
   hypertrackView.stopAllUpdates();
```

#### You are all set
