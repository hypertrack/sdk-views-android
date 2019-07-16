# HyperTrack Quickstart for Android Views SDK

## Integrate the Vies SDK
 - [Add Hypertrack SDK](#step-1-add-hypertrack-views-sdk)
 - [Start tracking](#step-2-initialize-sdk)
 - [Utility methods (optional)](#step-3-optional-utility-methods)

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
