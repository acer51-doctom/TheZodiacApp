# TRANSLATE.md

If you wanna help translating, you will need git and Android Studio.

## Download part.
1. Download Android Studio IDE.
2. Install git.
3. Clone this repository and cd into it.
3.1. Make a new branch following this scheme: `language/<language_REGION>`. Don't put any region code for international.

## On-machine part.
1. Create a folder in `app/res/` and name it `values-<country_code>`. Create a file named `strings.xml` in it.
1.1. OR, you can just right click the res folder, New > Android Resource Directory
1.2. In the left column, choose "Locale"
1.3. Click the 2 arrows button that points to the **right**.
1.4. Select the language.
1.5. Select the region. (e.g: German)
1.6. Go into the folder created. It usually follows this scheme: `values-<country-code>`
1.7. Open the file named `strings.xml`
2. Using the following template (just copy and paste into the file), translate into it. You can also use the language editor (basically a GUI front end if you figure it out).

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- App name -->
    <string name="title_app"></string>

    <!-- UI strings for the main screen -->
    <string name="tropical_sign_result"></string>
    <string name="sidereal_sign_result"></string>
    <string name="current_birthdate"></string>
    <string name="button_select_birthdate"></string>
    <string name="button_learn_more"></string>
    <string name="button_hide_details"></string>
    <string name="disclaimer"></string>
    <string name="unknown_sign_result"></string>

    <!-- Strings for the DatePicker dialog -->
    <string name="dialog_ok"></string>
    <string name="dialog_cancel"></string>

    <!-- Strings for the "Learn More" popup -->
    <string name="popup_info_title"></string>
    <string name="popup_info_p1"></string>
    <string name="popup_info_p2"></string>
    <string name="popup_info_p3"></string>
    <string name="popup_show_all_zodiacs"></string>
    <string name="popup_close"></string>
    <string name="popup_list_title"></string>

    <!-- Names of the Zodiac Signs -->
    <string name="aries_name"></string>
    <string name="taurus_name"></string>
    <string name="gemini_name"></string>
    <string name="cancer_name"></string>
    <string name="leo_name"></string>
    <string name="virgo_name"></string>
    <string name="libra_name"></string>
    <string name="scorpio_name"></string>
    <string name="sagittarius_name"></string>
    <string name="capricorn_name"></string>
    <string name="aquarius_name"></string>
    <string name="pisces_name"></string>

    <!-- Descriptions of the Zodiac Signs -->
    <string name="aries_description"></string>
    <string name="taurus_description"></string>
    <string name="gemini_description"></string>
    <string name="cancer_description"></string>
    <string name="leo_description"></string>
    <string name="virgo_description"></string>
    <string name="libra_description"></string>
    <string name="scorpio_description"></string>
    <string name="sagittarius_description"></string>
    <string name="capricorn_description"></string>
    <string name="aquarius_description"></string>
    <string name="pisces_description"></string>
</resources>
```

Base yourself off of the English translations to translate.