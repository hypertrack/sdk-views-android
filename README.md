# Android Views SDK

The Android Views SDK is used for getting live location and movement data for devices and trips directly to your Android app. This module subscribes to HyperTrack's GraphQL server end points to get data streams and then renders it in useful callbacks for app developers to build beautiful tracking experiences. This helps developers creating live location views go serverless. Their app users can directly get data securely and privately from the HyperTrack servers.
* [Integrate the Views SDK](#integrate-the-views-sdk)
* [FAQ](#frequently-asked-questions)

## Integrate the Views SDK

Check the [Integration Guide](https://hypertrack.com/docs/guides/build-custom-views-with-data/#add-views-sdk)

## Frequently Asked Questions
- [What API levels (Android versions) are supported](#supported-versions)
- [How do I get the DeviceID](#get-device-id)
- [The views SDK doesn't give me views!](#not-only-views-in-views)
- [How to manage multiple devices?](#track-multiple-devices)
- [How do I subscribe to a specific Trip via onTripUpdateReceived?
   Will I get an update for creation, re-calculation, and delay of Trips?](#trips-tracking)
- [What does 0 `BatteryState` means?](#battery-state-constants)

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
[reference](https://hypertrack.github.io/sdk-views-android/javadoc/0.8.7/constant-values.html#com.hypertrack.sdk.views.dao.MovementStatus.BATTERY_NORMAL)
for exact values meaning.
