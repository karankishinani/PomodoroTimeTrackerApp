
## Build

First navigate to the Project folder at `/PTTMobile4`. In the Project folder, run:

```
sh
./gradlew buildDebug
```

It will build a debug version of the app at: 

`PTTMobile4/app/build/outputs/apk/debug/app-debug.apk`


## Install 

Make sure to have your emulator opened or Android phone connected via USB in developer mode. From the Project folder, run:

```
sh
./gradlew installDebug
```

This command will install the app on your emulator/phone.


