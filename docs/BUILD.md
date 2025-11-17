# BUILD.md

Hey! So you're one of the few people that likes to struggle with technology and wanna
<br> suffer by compiling your own things and maybe operating system! (I'm watching you Gentoo users.) Well, this guide will help you into compiling this app.
<br> <br> So first, you will need the following:

## Requirements

### Computer
- Android Studio
- Signing key for your own APK (can be generated with Android Studio)
- At least 4GB of RAM
- A decent CPU
- A relatively new OS that supports Android Studio

### Android
- An Android device with at least Android 8.0 Oreo
- Not some sort of trashy phone worth less then 100$ that can't run anything or barely. Please use something decent.

## Guide

1. Install Android Studio if not done yet.
2. Clone this repo via any way you want.
3. Go through the steps of Android Studio for first set up.
4. Install the device emulation images if prompted.
5. Open the repo in Android Studio.
6. Once you get here, go to Build > Generate Signed App Bundle or APK
> [!IMPORTANT]
> Generating a signed App Bundle or APK is required to be installed on any Android device. If you do not wish to sign the APK, please use the Device Emulator from Android Studio.

7. Choose APK.
8. Go to your file manager and navigate to the cloned repo. Create a "keys" folder.
9. Go back to Android Studio and create a new key with the "Create Key" button.
10.  Fill in what it asks you for.
11. Choose a variant. I recommend: **Release**. (May change in the future.)
> [!TIP]
> **Hey! Are you contributing to this app? I recommend you choose the Debug flavor instead!**

12. Wait for it to build. There should be a small indicator in the bottom bar saying "Gradle Build Running". Wait until it's not there.
13. After building, you normally have a notification saying that the build finished. Click on the locate button.
> [!TIP]
> **Hey!** Did the notifcation go away before you could click it? **If so,** go tho this path in your File Manager: <br> `<root_of_the_repo>/app/<chosen_flavor>/` and you should find the APK file!

14. Send it to your device using whatever way you want. I recommend file transfer.


And there you go! You have compiled The Zodiac App! <br>
Enjoy!