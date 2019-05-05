
# 1. Creating the Emulator for Running tests

## Install dependencies:

```
./sdkmanager "platform-tools" "platforms;android-18" "emulator"
./sdkmanager "system-images;android-18;default;armeabi-v7a"
```

## Create the emulator:

`./avdmanager create avd -n test_avd --package "system-images;android-18;default;armeabi-v7a" --tag "default" --force --device "pixel"`

## Boot the emulator:

`./emulator -avd test_avd -noaudio -no-boot-anim -accel off -no-window`

## Make sure the emulator is booted and unlock it:

Adapted from https://android.stackexchange.com/questions/83726/how-to-adb-wait-for-device-until-the-home-screen-shows-up

```
adb wait-for-device shell <<ENDSCRIPT
echo -n "Waiting for device to boot "
echo "" > /data/local/tmp/zero
getprop dev.bootcomplete > /data/local/tmp/bootcomplete
while cmp /data/local/tmp/zero /data/local/tmp/bootcomplete; do 
{
    echo -n "."
    sleep 1
    getprop dev.bootcomplete > /data/local/tmp/bootcomplete
}; done
echo "Booted."
exit
ENDSCRIPT

echo "Waiting 30 secs for us to be really booted"
sleep 30

echo "Unlocking screen"
adb shell "input keyevent 82"
```

Now the emulator is ready for running any tests.

# 2. Important Note

1. We expect 2 servers to be implemented. One server exclusively for testing purposes (in a blank state). The second server should be the final production server (http://gazelle.cc.gatech.edu:9306/ptt/).

2. The front-end tests require that a separate backend server for testing (implemented by Team Backend 4) is up and running. We request DevOps Team to set up this additional server (implemented by Backend 4) to be in a blank state that will be used only for testing purposes. This server is only needed during the Front-end test task and can be deleted later. 

3. We have come up with a solution where DevOps can pass the URL of the backend server via command-line at build time for running the front-end tests as well as generating the deployment APK. These instructions are below. 

# 3. Front-end tests + Deployment APK

## How to Run Front-end Tests

Make sure to have your emulator started (as instructed in the previous section). While running the tests, you can pass the URL of the *Test Backend Server* to be used for running the front-end tests. From the Project folder at `/PTTMobile4`, run:

```
./gradlew connectedAndroidTest task runProgram '-Purl="http://URL:PORT/ptt/"'
```

This command will install the app on the emulator and run the tests.

*NOTE:* The backend URL sent here is different from the Production Backend URL. 

## Build

Once the front-end tests above have passed, from the previous step, the app can be built for deployment. First navigate to the Project folder at `/PTTMobile4`. In the Project folder, run:

```
./gradlew buildDebug task runProgram '-Purl="http://gazelle.cc.gatech.edu:9306/ptt/"'
```

It will build the app at: 

`PTTMobile4/app/build/outputs/apk/debug/app-debug.apk`

*Note:* This APK *SHOULD* be used for deployment. This APK is built using the production server URL.


## Install (optional check to make sure it can be installed)

Make sure to have your emulator booted up as instructed above. From the Project folder at `/PTTMobile4`, run:

```
./gradlew installDebug task runProgram '-Purl="http://gazelle.cc.gatech.edu:9306/ptt/"'
```

This command will install the app on your emulator.
